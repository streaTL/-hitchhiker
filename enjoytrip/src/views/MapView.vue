<template>
  <div>
    <section class="page-section map-section mt-5" id="map1">
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
          <select
            id="search-content-id"
            class="form-select me-2"
            v-model="searchContentId"
          >
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
          <button
            id="btn-search"
            class="btn btn-outline-success"
            type="button"
            @click="search"
          >
            검색
          </button>
        </form>
      </div>
    </section>
    <section class="map-section" id="map2">
      <div id="map" class="mb-5 ms-auto me-auto"></div>
    </section>
    <div style="display: flex; justify-content: center">
      <div class="mb-auto ms-3 me-3 row" style="flex-wrap: wrap; width: 80%">
        <list-component
          v-for="(trip, index) in trips"
          :key="index"
          :trip="trip"
        ></list-component>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import ListComponent from "@/components/common/ListComponent.vue";
export default {
  name: "MapView",
  components: { ListComponent },
  data() {
    return {
      serchArea: "0",
      searchContentId: "0",
      keyword: "",
      map: null,
      positions: [],
      markers: [],
      trips: [],
    };
  },
  created() {},
  mounted() {
    // let areaUrl =
    //   "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
    //   "rr9isP1Ir1kiNMbiyyfIl60qNH9dBAdVX78tq8pYoRYcR2cvdl2a3r815EPgPMopJM%2FAD%2BrdnBCkhxfGc39YFg%3D%3D" +
    //   "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";

    // fetch(areaUrl, { method: "GET" }).then(function (response) { return response.json() }).then(function (data) { makeOption(data); });
    axios
      .get("http://localhost:80/map/sido")
      .then(({ data }) => this.makeOption(data));

    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  methods: {
    search() {
      let searchUrl = "http://localhost:80/map/attrInfo";
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
        alert("검색어 입력 필수!!!");
        return;
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

      axios
        .get(searchUrl, {
          headers: {
            areaCode,
            contentTypeId,
            keyword,
          },
        })
        .then(({ data }) => this.makeList(data));
    },
    makeList(data) {
      this.positions = [];
      let trips = data;
      console.log(trips);
      //   let tripList = ``;

      this.trips = trips;

      trips.forEach((area) => {
        let markerInfo = {
          title: area.title,
          latlng: new kakao.maps.LatLng(area.latitude, area.longitude),
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
        const marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: position.latlng, // 마커를 표시할 위치
          title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          //   image: markerImage, // 마커의 이미지
        });
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
    deleteMarker() {
      console.log("마커 싹 지우자!!!", this.markers.length);
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          console.log(item);
          item.setMap(null);
          console.log(11, item);
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
  },
};
</script>

<style scoped>
#map {
  width: 80%;
  height: 500px;
}
</style>
