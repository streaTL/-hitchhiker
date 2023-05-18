package com.ssafy.enjoytrip.map.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.map.model.SidoDto;
import com.ssafy.enjoytrip.map.model.attractionInfoDto;
import com.ssafy.enjoytrip.map.model.service.MapService;
import com.ssafy.enjoytrip.map.model.service.MapServiceImpl;

@RestController
@RequestMapping("/map")
public class MapController {
    
	public MapService mapService;
	
	public MapController(MapService mapService) {
		this.mapService = mapService;
	}
	
	@GetMapping("/sido")
	@ResponseBody
	public List<SidoDto> sido() throws Exception {
		List<SidoDto> sidos = mapService.sido();
		return sidos;
	}
	
	@GetMapping("/attrInfo")
	@ResponseBody
	public ResponseEntity<?>  attrInfo(@RequestHeader String areaCode,
			@RequestHeader String contentTypeId,
			@RequestHeader String keyword) throws Exception {
		List<attractionInfoDto> attrInfos = mapService.attrInfo(areaCode, contentTypeId, keyword);
		return new ResponseEntity<>(attrInfos, HttpStatus.OK);
	}
}
