package com.ssafy.enjoytrip.map.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.map.model.SidoDto;
import com.ssafy.enjoytrip.map.model.attractionInfoDto;

public interface MapDao {
	List<SidoDto> sido() throws SQLException;

	List<attractionInfoDto> attrInfo(String areaCode, String contentTypeId, String keyword) throws SQLException;
}
