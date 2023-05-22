package com.ssafy.enjoytrip.plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.plan.model.PlanDto;
import com.ssafy.enjoytrip.plan.model.service.PlanService;

@RestController
@RequestMapping("/plan")
public class PlanController {

	private PlanService planService;
	
	@Autowired
	public PlanController(PlanService planService) {
		this.planService = planService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getPlans(){
		return new ResponseEntity<>(planService.getPlans(), HttpStatus.OK);
	}
	
	@GetMapping("/list/{planId}")
	public ResponseEntity<?> getPlans(@PathVariable int planId){
		return new ResponseEntity<>(planService.getPlanDetail(planId), HttpStatus.OK);
	}
	
	@PostMapping("/write")
	public ResponseEntity<?> writePlan(@RequestBody PlanDto planDto){
		System.out.println(planDto);
		
		planService.writePlan(planDto);
		
		return new ResponseEntity<>(planDto,HttpStatus.OK);
	}
}
