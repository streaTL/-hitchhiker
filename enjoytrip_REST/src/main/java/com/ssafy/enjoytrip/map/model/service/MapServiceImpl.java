package com.ssafy.enjoytrip.map.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.map.model.SidoDto;
import com.ssafy.enjoytrip.map.model.attractionInfoDto;
import com.ssafy.enjoytrip.map.model.dao.MapDao;
import com.ssafy.enjoytrip.map.model.dao.MapDaoImpl;
import com.ssafy.enjoytrip.map.model.mapper.MapMapper;

@Service
public class MapServiceImpl implements MapService {
	
	@Autowired
	private MapMapper mapMapper;
	
	
	@Override
	public List<SidoDto> sido() throws SQLException  {
		return mapMapper.sido();
	}

	@Override
	public List<attractionInfoDto> attrInfo(String areaCode, String contentTypeId, String keyword) throws Exception {
		return mapMapper.attrInfo(areaCode, contentTypeId, keyword);
	}

	@Override
	public List<attractionInfoDto> attrDetailInfo(String contentId) {
		return mapMapper.attrDetailInfo(contentId);
	}

	@Override
	public List<attractionInfoDto> randAttrInfo() {
		return mapMapper.randAttrInfo();
	}

}
