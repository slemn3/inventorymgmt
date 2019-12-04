package com.lemparty.entity;

public class Query {

    private String batchName;
    private String season;

    public Query(String name, String season){
        this.batchName = name;
        this.season = season;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
