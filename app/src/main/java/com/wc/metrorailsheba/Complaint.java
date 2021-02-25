package com.wc.metrorailsheba;

public class Complaint {
    private String email, trainNo, complaint;

    public Complaint(String email, String trainNo, String complaint) {
        this.email = email;
        this.trainNo = trainNo;
        this.complaint = complaint;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
}
