package com.gorest.models;

import com.opencsv.bean.CsvBindByName;

public class users {

    @CsvBindByName(column = "testType") // ⚠️ Must match header exactly
    private String testType;

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "email")
    private String email;

    @CsvBindByName(column = "gender")
    private String gender;

    @CsvBindByName(column = "status")
    private String status;

    // Optional: Used for GET response deserialization
    private int id;

    // Getters and Setters
    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // ✅ toString() for debugging
    @Override
    public String toString() {
        return "users{" +
                "testType='" + testType + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
