package com.ssafy.enjoytrip.map.model.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.enjoytrip.map.model.SidoDto;
import com.ssafy.enjoytrip.map.model.attractionInfoDto;

public interface MapService {
	List<SidoDto> sido() throws Exception;
	List<attractionInfoDto> attrInfo(String areaCode, String contentTypeId, String keyword) throws Exception;
	List<attractionInfoDto> attrDetailInfo(String contentId);

	List<attractionInfoDto> randAttrInfo();
	List<HashMap<String, String>>  getChart();

}
