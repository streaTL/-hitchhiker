package com.ssafy.enjoytrip.plan.model.service;

import java.util.List;

import com.ssafy.enjoytrip.plan.model.PlanDetailDto;
import com.ssafy.enjoytrip.plan.model.PlanDto;

public interface PlanService {

	List<PlanDetailDto> getPlans();
	List<PlanDetailDto> getPlanDetail(int planId);
	void writePlan(PlanDto planDto);
	void deletePlan(int planId);
	void likePlan(int planId);

}
