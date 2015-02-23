package com.atex.blogping.dto;

import org.joda.time.DateTime;

public class WeblogDTO {

    private String name;
    private String url;
    private DateTime created;

    public WeblogDTO(String name, String url) {
        this.name = name;
        this.url = url;
        this.created = DateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DateTime getCreated() {
        return created;
    }
}
