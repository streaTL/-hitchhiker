package com.ssafy.enjoytrip.map.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.map.model.SidoDto;
import com.ssafy.enjoytrip.map.model.attractionInfoDto;
import com.ssafy.enjoytrip.map.model.dao.MapDao;
import com.ssafy.enjoytrip.map.model.dao.MapDaoImpl;

@Service
public class MapServiceImpl implements MapService {
	
	public MapDao mapDao;
	
	public MapServiceImpl(MapDao mapDao) {
		this.mapDao = mapDao;
	}
	
	@Override
	public List<SidoDto> sido() throws SQLException  {
		return mapDao.sido();
	}

	@Override
	public List<attractionInfoDto> attrInfo(String areaCode, String contentTypeId, String keyword) throws Exception {
		// TODO Auto-generated method stub
		return mapDao.attrInfo(areaCode, contentTypeId, keyword);
	}

}
