package com.ssafy.enjoytrip.plan.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.plan.model.PlanDetailDto;
import com.ssafy.enjoytrip.plan.model.PlanDto;
import com.ssafy.enjoytrip.plan.model.mapper.PlanMapper;

@Service
public class PlanServiceImpl implements PlanService{

	private PlanMapper planMapper; 
	
	public PlanServiceImpl(PlanMapper planMapper) {
		this.planMapper = planMapper;
	}
	
	@Override
	public List<PlanDetailDto> getPlans() {
		return planMapper.getPlans();
	}

	@Override
	public List<PlanDetailDto> getPlanDetail(int planId) {
		return planMapper.getPlanDetail(planId);
	}

	@Override
	public void writePlan(PlanDto planDto) {
		planMapper.writePlan(planDto);
		planMapper.writePlanDetail(planDto);
	}

	@Override
	public void deletePlan(int planId) {
		planMapper.deletePlan(planId);
	}

	@Override
	public void likePlan(int planId) {
		planMapper.likePlan(planId);
	}

}
