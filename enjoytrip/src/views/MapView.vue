<template>
  <div>
    <section class="page-section map-section" style="margin-top: 120px" id="map1">
      <div class="row">
        <!-- 관광지 검색 start -->
        <form
          class="d-flex my-3 ms-auto me-auto"
          style="width: 80%"
          onsubmit="return false;"
          role="search"
        >
          <select id="search-area" class="form-select me-2" v-model="serchArea">
            <option value="0" selected>검색 할 지역 선택</option>
          </select>
          <select id="search-content-id" class="form-select me-2" v-model="searchContentId">
            <option value="0" selected>관광지 유형</option>
            <option value="12">관광지</option>
            <option value="14">문화시설</option>
            <option value="15">축제공연행사</option>
            <option value="25">여행코스</option>
            <option value="28">레포츠</option>
            <option value="32">숙박</option>
            <option value="38">쇼핑</option>
            <option value="39">음식점</option>
          </select>
          <input
            id="search-keyword"
            class="form-control me-2"
            type="search"
            placeholder="검색어"
            aria-label="검색어"
            v-model="keyword"
            @keyup.enter="search"
          />
          <button id="btn-search" class="btn btn-outline-success" type="button" @click="search">
            검색
          </button>
        </form>
      </div>
    </section>
    <section class="map-section" id="map2" style="display: flex; justify-content: center">
      <div id="map" class="mb-5 me-3"></div>
      <div style="display: flex; flex-direction: column">
        <div style="float: left; height: 445px; width: 450px; overflow-y: scroll">
          <plan-component
            v-for="(plan, index) in plans"
            :key="index"
            :plan="plan"
            :index="index"
          ></plan-component>
        </div>
        <div class="mt-3">
          <button type="button" class="btn btn-primary" style="width: 200px" @click="deleteplans">
            목록삭제
          </button>
          <button
            type="button"
            class="btn btn-primary"
            style="float: right; width: 200px"
            data-bs-toggle="modal"
            data-bs-target="#exampleModal"
            data-bs-whatever="@getbootstrap"
          >
            세부내용
          </button>
          <div
            class="modal fade"
            id="exampleModal"
            tabindex="-1"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
          >
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h1 class="modal-title fs-5" id="exampleModalLabel">여행 계획 작성!</h1>
                  <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="modal"
                    aria-label="Close"
                  ></button>
                </div>
                <div class="modal-body">
                  <form>
                    <div class="mb-3">
                      <label for="recipient-name" class="col-form-label">여행이름:</label>
                      <input
                        type="text"
                        class="form-control"
                        id="recipient-name"
                        v-model="planName"
                      />
                    </div>
                    <div>
                      <div class="row" style="display: flex">
                        <div class="col">시작</div>
                        <div class="col">종료</div>
                      </div>
                      <div class="row" style="display: flex">
                        <datepicker
                          class="col"
                          :bootstrap-styling="true"
                          style="float: left"
                          v-model="startDate"
                          :language="ko"
                          format="yyyy/MM/dd"
                        >
                          <div slot="beforeCalendarHeader" class="calendar-header">시작 날짜</div>
                        </datepicker>
                        <datepicker
                          class="col"
                          :bootstrap-styling="true"
                          style="float: right"
                          v-model="endDate"
                          :language="ko"
                          format="yyyy/MM/dd"
                        >
                          <div slot="beforeCalendarHeader" class="calendar-header">
                            종료 날짜
                          </div></datepicker
                        >
                      </div>
                    </div>
                    <div class="mb-3">
                      <label for="message-text" class="col-form-label">상세설명:</label>
                      <textarea
                        class="form-control"
                        id="message-text"
                        v-model="planDesc"
                      ></textarea>
                    </div>
                  </form>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                    Close
                  </button>
                  <button type="button" class="btn btn-primary" @click="writePlan">작성</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <div style="display: flex; justify-content: center">
      <div class="mb-auto ms-3 me-3 row" style="flex-wrap: wrap; width: 80%; position: relative">
        <list-component v-for="(trip, index) in trips" :key="index" :trip="trip"></list-component>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import ListComponent from "@/components/common/ListComponent.vue";
import PlanComponent from "@/components/common/PlanComponent.vue";
import { mapState } from "vuex";
import { mapMutations } from "vuex";
import Datepicker from "vuejs-datepicker";
import { ko } from "vuejs-datepicker/dist/locale";
import http from "@/api/http";
import moment from "moment";

export default {
  name: "MapView",
  components: { ListComponent, PlanComponent, Datepicker },
  data() {
    return {
      serchArea: "0",
      searchContentId: "0",
      keyword: "",
      map: null,
      positions: [],
      markers: [],
      trips: [],
      startDate: "",
      endDate: "",
      ko: ko,
      planName: "",
      customFormat: "yyyy-MM-dd",
      planDesc: "",
    };
  },
  computed: {
    ...mapState(["plans", "userInfo"]),
  },
  watch: {
    startDate(newVal) {
      this.startDate = moment(newVal).format("YYYY/MM/DD");
    },
    endDate(newVal) {
      this.endDate = moment(newVal).format("YYYY/MM/DD");
    },
  },
  created() {},
  mounted() {
    this.DELETE_PLANS();
    // let areaUrl =
    //   "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
    //   "rr9isP1Ir1kiNMbiyyfIl60qNH9dBAdVX78tq8pYoRYcR2cvdl2a3r815EPgPMopJM%2FAD%2BrdnBCkhxfGc39YFg%3D%3D" +
    //   "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";

    // fetch(areaUrl, { method: "GET" }).then(function (response) { return response.json() }).then(function (data) { makeOption(data); });
    axios.get("http://192.168.31.65/map/sido").then(({ data }) => this.makeOption(data));

    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  methods: {
    search() {
      let searchUrl = "http://192.168.31.65/map/attrInfo";
      // `https://apis.data.go.kr/B551011/KorService1/searchKeyword1?serviceKey=` +
      // "rr9isP1Ir1kiNMbiyyfIl60qNH9dBAdVX78tq8pYoRYcR2cvdl2a3r815EPgPMopJM%2FAD%2BrdnBCkhxfGc39YFg%3D%3D" +
      // `&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
      /* let searchUrl = "${root}/map/attrInfo"; */

      let areaCode = document.getElementById("search-area").value;
      let contentTypeId = document.getElementById("search-content-id").value;
      let keyword = document.getElementById("search-keyword").value;

      // if (parseInt(areaCode)) searchUrl += `&areaCode=` + this.serchArea;
      // if (parseInt(contentTypeId))
      // searchUrl += `&contentTypeId=` + this.searchContentId;
      if (!keyword) {
        keyword = "";
      }
      // else searchUrl += `&keyword=` + this.keyword;

      /*  fetch(searchUrl, {
        	  method: "GET",
        	  headers: {
        	    "Content-Type": "application/json",
        	    "areaCode" : areaCode,
        	    "contentTypeId" : contentTypeId,
        	    "keyword" : keyword,
        	  },
        	})
          .then((response) => response.json())
          .then((data) => makeList(data)); */
      console.log(keyword);
      axios
        .get(
          searchUrl,
          {
            params: {
              areaCode,
              contentTypeId,
              keyword,
            },
          },

          {
            headers: {
              "Content-Type": "application/json;charset=utf-8",
            },
          }
        )
        .then(({ data }) => {
          console.log(data);
          this.makeList(data);
        });
    },
    makeList(data) {
      this.positions = [];

      if (data.length == 0) {
        alert("결과가 없습니다.");
        return;
      }
      let trips = data;
      console.log(trips);
      //   let tripList = ``;

      this.trips = trips;

      trips.forEach((area) => {
        console.log(area.homepage);
        let markerInfo = {
          title: area.title,
          latlng: new kakao.maps.LatLng(area.latitude, area.longitude),
          homepage: area.homepage,
        };
        this.positions.push(markerInfo);
      });

      this.loadMaker();
    },
    loadScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=baf19827023c65893054deaf497df480&autoload=false";
      console.log(window);
      /* global kakao */
      script.onload = () => window.kakao.maps.load(this.loadMap);

      document.head.append(script);
    },
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
      };

      this.map = new window.kakao.maps.Map(container, options);
      //   this.loadMaker();
    },
    loadMaker() {
      // 현재 표시되어있는 marker들이 있다면 marker에 등록된 map을 없애준다.
      console.log("1111");
      this.deleteMarker();
      console.log("2222");
      // 마커 이미지를 생성합니다
      //   const imgSrc = require("@/assets/map/markerStar.png");
      // 마커 이미지의 이미지 크기 입니다
      //   const imgSize = new kakao.maps.Size(24, 35);
      //   const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

      this.positions.forEach((position) => {
        console.log(position.homepage);
        const marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: position.latlng, // 마커를 표시할 위치
          title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          //   image: markerImage, // 마커의 이미지
        });

        // 마커에 표시할 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
          content: '<span class="info-title">' + position.title + "</span>", // 인포윈도우에 표시할 내용
        });

        // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
        // 이벤트 리스너로는 클로저를 만들어 등록합니다
        // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
        kakao.maps.event.addListener(
          marker,
          "mouseover",
          this.makeOverListener(this.map, marker, infowindow)
        );
        kakao.maps.event.addListener(marker, "mouseout", this.makeOutListener(infowindow));

        this.markers.push(marker);
      });
      console.log("마커수 ::: " + this.markers.length);

      // 4. 지도를 이동시켜주기
      // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
      const bounds = this.positions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
      );

      this.map.setBounds(bounds);
    },
    makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);
      };
    },

    // 인포윈도우를 닫는 클로저를 만드는 함수입니다
    makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    },

    deleteMarker() {
      console.log("마커 싹 지우자!!!", this.markers.length);
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          console.log(item);
          item.setMap(null);
        });
      }
      console.log("마커 싹 지우자!!!222", this.markers.length);
    },
    makeOption(data) {
      let areas = data;
      console.log(areas);
      // console.log(areas);
      let sel = document.getElementById("search-area");
      areas.forEach((area) => {
        let opt = document.createElement("option");
        opt.setAttribute("value", area.sidoCode);
        opt.appendChild(document.createTextNode(area.sidoName));

        sel.appendChild(opt);
      });
    },
    ...mapMutations(["DELETE_PLANS"]),
    deleteplans() {
      if (confirm("모든 목록을 삭제할까요?")) {
        this.DELETE_PLANS();
      }
    },
    writePlan() {
      console.log({
        planName: this.planName,
        startDate: this.startDate,
        endDate: this.endDate,
        userId: this.userInfo,
        planDesc: this.planDesc,
        planDetail: this.plans,
      });
      console.log(this.plans);
      if (this.userInfo == "") {
        alert("로그인이 필요합니다");
      } else if (this.plans.length == 0) {
        alert("여행지를 추가하세요");
      } else if (this.planName == "") {
        alert("계획명을 입력하세요");
      } else if (this.startDate == "") {
        alert("시작일을 입력하세요");
      } else if (this.endDate == "") {
        alert("종료일을 입력하세요");
      } else if (this.planDesc == "") {
        alert("여행설명을 입력하세요");
      } else {
        http.post("/plan/write", {
          planName: this.planName,
          startDate: this.startDate,
          endDate: this.endDate,
          userId: this.userInfo,
          planDesc: this.planDesc,
          planDetail: this.plans,
        });
        alert("등록되었습니다!");
        this.$router.go();
      }
    },
  },
};
</script>

<style scoped>
#map {
  width: 55%;
  height: 500px;
  float: left;
}
</style>

<style>
.info-title {
  display: block;
  text-align: center;
  height: 24px;
  line-height: 22px;
  border-radius: 4px;
  padding: 0px 10px;
  max-width: 100%;
  font-size: 12px;
}
</style>
