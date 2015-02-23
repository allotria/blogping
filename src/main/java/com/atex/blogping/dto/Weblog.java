package com.atex.blogping.dto;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name", "url", "when"})
public class Weblog {

    private String name;
    private String url;
    private int when;

    public Weblog() {
        // to make JAXB happy :-)
    }

    public Weblog(String name, String url, int when) {
        this.name = name;
        this.url = url;
        this.when = when;
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

    public int getWhen() {
        return when;
    }

    public void setWhen(int when) {
        this.when = when;
    }
}
