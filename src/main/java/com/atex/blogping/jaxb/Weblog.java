package com.atex.blogping.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name", "url", "when"})
public class Weblog {

    private String name;
    private String url;
    private long when;

    public Weblog() {
        // to make JAXB happy :-)
    }

    public Weblog(String name, String url, long when) {
        this.name = name;
        this.url = url;
        this.when = when;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlAttribute
    public long getWhen() {
        return when;
    }

    public void setWhen(long when) {
        this.when = when;
    }
}
