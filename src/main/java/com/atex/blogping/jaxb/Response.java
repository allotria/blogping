package com.atex.blogping.jaxb;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "response")
@XmlType(propOrder = {"message", "flerror"})
public class Response {

    private String message;
    private int flerror;

    public Response() {
        // to make JAXB happy
    }

    public Response(String message, int flerror) {
        this.message = message;
        this.flerror = flerror;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getFlerror() {
        return flerror;
    }

    public void setFlerror(int flerror) {
        this.flerror = flerror;
    }
}
