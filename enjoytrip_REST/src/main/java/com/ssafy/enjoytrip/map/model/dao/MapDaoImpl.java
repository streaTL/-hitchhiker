package com.ssafy.enjoytrip.map.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.map.model.SidoDto;
import com.ssafy.enjoytrip.map.model.attractionInfoDto;
import com.ssafy.enjoytrip.util.DBUtil;

@Repository
public class MapDaoImpl implements MapDao{
	
	public DBUtil dbUtil;
	
	@Autowired
	public MapDaoImpl(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	@Override
	public List<SidoDto> sido() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SidoDto> sidos = new ArrayList<>();
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from sido \n");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				SidoDto sidoDto = new SidoDto();
				sidoDto.setSidoCode(rs.getInt("sido_code"));
				sidoDto.setSidoName(rs.getString("sido_name"));
				sidos.add(sidoDto);
			}
			
		}finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return sidos;
	}

	@Override
	public List<attractionInfoDto> attrInfo(String areaCode, String contentTypeId, String keyword) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<attractionInfoDto> attrInfos = new ArrayList<>();
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from attraction_info \n");
			sql.append("where sido_code = ? and content_type_id = ? and title like ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, areaCode);
			pstmt.setString(2, contentTypeId);
			pstmt.setString(3, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			while(rs.next()){
				attractionInfoDto attractionInfoDto = new attractionInfoDto();
				attractionInfoDto.setTitle(rs.getString("title"));
				attractionInfoDto.setLatitude(rs.getDouble("latitude"));
				attractionInfoDto.setLongitude(rs.getDouble("longitude"));
				attrInfos.add(attractionInfoDto);
			}
			
		}finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return attrInfos;
	}

	@Override
	public List<attractionInfoDto> attrDetailInfo(String contentId) {
		// TODO Auto-generated method stub
		return null;
	}
}
