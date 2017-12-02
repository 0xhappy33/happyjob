package com.happycity.project.jobme.data.model;

/**
 * Created by Ha Truong on 11/23/2017.
 * Class Job
 * into the com.happycity.project.jobme.TAG_DATA.model
 *
 */

public class Job {
    private String title;
    private String location;
    private String type;
    private String description;
    private String company;
    private String url;
    private String logo;
    private String createdAt;


    /*
    * Construct Job
    * @param String owner
    * @param String content
    * @param String createdTime
    * */


    public Job(String title, String location, String type, String description, String company, String url, String logo, String createdAt) {
        this.title = title;
        this.location = location;
        this.type = type;
        this.description = description;
        this.company = company;
        this.url = url;
        this.logo = logo;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public Job setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Job setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getType() {
        return type;
    }

    public Job setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Job setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public Job setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Job setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public Job setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Job setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
