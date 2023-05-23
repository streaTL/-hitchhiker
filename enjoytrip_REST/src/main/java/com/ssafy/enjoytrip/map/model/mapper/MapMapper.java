package com.ssafy.enjoytrip.map.model.mapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.map.model.SidoDto;
import com.ssafy.enjoytrip.map.model.attractionInfoDto;

@Mapper
public interface MapMapper {
	List<SidoDto> sido() throws SQLException;
	List<attractionInfoDto> attrInfo(String areaCode, String contentTypeId, String keyword) throws SQLException;
	List<attractionInfoDto> attrDetailInfo(String contentId);
	List<attractionInfoDto> randAttrInfo();
	List<HashMap<String, String>>  getChart();
}
