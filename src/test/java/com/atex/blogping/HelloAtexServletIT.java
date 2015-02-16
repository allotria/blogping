package com.atex.blogping;

import junit.framework.Assert;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

import java.io.IOException;

public class HelloAtexServletIT
{

    @Test
    public void should_say_hello() throws IOException {
        HttpClient client = new HttpClient();
        HttpMethod get = new GetMethod("http://localhost:8080/hello");
        try {
            client.executeMethod(get);
            Assert.assertEquals("Hello Atex!", get.getResponseBodyAsString());
        } finally {
            get.releaseConnection();
        }
    }

}
