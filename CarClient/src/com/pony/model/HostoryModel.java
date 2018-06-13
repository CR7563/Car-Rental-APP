package com.pony.model;

public class HostoryModel {

	private String Id;
	private String SchoolName;
	private String WorkCategoryName;
	private String StatusName;
	private String TrainCycle;
	private String ReservationDateTime;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getSchoolName() {
		return SchoolName;
	}

	public void setSchoolName(String schoolName) {
		SchoolName = schoolName;
	}

	public String getWorkCategoryName() {
		return WorkCategoryName;
	}

	public void setWorkCategoryName(String workCategoryName) {
		WorkCategoryName = workCategoryName;
	}

	public String getStatusName() {
		return StatusName;
	}

	public void setStatusName(String statusName) {
		StatusName = statusName;
	}

	public String getTrainCycle() {
		return TrainCycle;
	}

	public void setTrainCycle(String trainCycle) {
		TrainCycle = trainCycle;
	}

	public String getReservationDateTime() {
		return ReservationDateTime;
	}

	public void setReservationDateTime(String reservationDateTime) {
		ReservationDateTime = reservationDateTime;
	}

}
