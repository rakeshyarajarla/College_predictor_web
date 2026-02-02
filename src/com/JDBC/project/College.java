package com.JDBC.project;

public class College {
    private int id;
    private String instCode;
    private String collegeName;
    private String branchCode;
    private double fees;

    public College(int id, String instCode, String collegeName, String branchCode, double fees) {
        this.id = id;
        this.instCode = instCode;
        this.collegeName = collegeName;
        this.branchCode = branchCode;
        this.fees = fees;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "College{id=" + id + ", instCode='" + instCode + "', collegeName='" + collegeName + "', branchCode='"
                + branchCode + "', fees=" + fees + "}";
    }
}
