package com.ssafy.enjoytrip.map.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.map.model.attractionInfoDto;
import com.ssafy.enjoytrip.map.model.service.MapService;

@RestController
@RequestMapping("/map")
public class MapController {
    
	public MapService mapService;
	
	@Autowired
	public MapController(MapService mapService) {
		this.mapService = mapService;
	}
	
//	@GetMapping("/sido")
//	@ResponseBody
//	public List<SidoDto> sido() throws Exception {
//		List<SidoDto> sidos = mapService.sido();
//		return sidos;
//	}
	
	@GetMapping("/sido")
	public ResponseEntity<?> sido() throws Exception{
		return new ResponseEntity<>(mapService.sido(), HttpStatus.OK);
	}

	@GetMapping("/ramAttrInfo")
	@ResponseBody
	public ResponseEntity<?>  randAttrInfo() throws Exception {
		List<attractionInfoDto> attrInfos = mapService.randAttrInfo();
		return new ResponseEntity<>(attrInfos, HttpStatus.OK);
	}
	
	@GetMapping("/attrInfo")
	@ResponseBody
	public ResponseEntity<?>  attrInfo(@RequestHeader String areaCode,
			@RequestHeader String contentTypeId,
			@RequestHeader String keyword) throws Exception {
		List<attractionInfoDto> attrInfos = mapService.attrInfo(areaCode, contentTypeId, keyword);
		return new ResponseEntity<>(attrInfos, HttpStatus.OK);
	}
	
	@GetMapping("/attrDesc/{contentId}")
	@ResponseBody
	public ResponseEntity<?>  attrDesc(@PathVariable String contentId) throws Exception {
		List<attractionInfoDto> attrDesc = mapService.attrDetailInfo(contentId);
		return new ResponseEntity<>(attrDesc, HttpStatus.OK);
	}
}
