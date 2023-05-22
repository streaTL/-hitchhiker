package com.ssafy.enjoytrip.plan.model;

public class PlanDetailDto {
	int planDetailId;
	int contentId;
	int day;
	int order;

	public int getPlanDetailId() {
		return planDetailId;
	}

	public void setPlanDetailId(int planDetailId) {
		this.planDetailId = planDetailId;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "PlanDetailDto [planDetailId=" + planDetailId + ", contentId=" + contentId + ", day=" + day + ", order="
				+ order + "]";
	}

}
