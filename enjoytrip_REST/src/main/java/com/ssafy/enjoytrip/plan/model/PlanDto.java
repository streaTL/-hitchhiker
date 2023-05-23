package com.ssafy.enjoytrip.plan.model;

import java.util.List;

public class PlanDto {
	int planId;
	String userId;
	String planName;
	String startDate;
	String endDate;
	String createDate;
	String updateDate;
	String planDesc;
	int likeCnt;

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	List<PlanDetailDto> planDetail;

	public List<PlanDetailDto> getPlanDetail() {
		return planDetail;
	}

	public void setPlanDetail(List<PlanDetailDto> planDetail) {
		this.planDetail = planDetail;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getPlanDesc() {
		return planDesc;
	}

	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
	}

	@Override
	public String toString() {
		return "PlanDto [planId=" + planId + ", userId=" + userId + ", planName=" + planName + ", startDate="
				+ startDate + ", endDate=" + endDate + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", planDesc=" + planDesc + ", likeCnt=" + likeCnt + ", planDetail=" + planDetail + "]";
	}

}
