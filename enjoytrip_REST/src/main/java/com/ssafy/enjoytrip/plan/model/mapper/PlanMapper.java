package com.ssafy.enjoytrip.plan.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.plan.model.PlanDetailDto;
import com.ssafy.enjoytrip.plan.model.PlanDto;

@Mapper
public interface PlanMapper {

	List<PlanDetailDto> getPlans();
	List<PlanDetailDto> getPlanDetail(int planId);
	void writePlan(PlanDto planDto);
	void writePlanDetail(PlanDto planDto);
	void deletePlan(int planId);
	void likePlan(int planId);

}
