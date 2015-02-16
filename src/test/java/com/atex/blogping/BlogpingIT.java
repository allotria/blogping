package com.atex.blogping;


import junit.framework.Assert;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.junit.Test;

import java.io.IOException;

public class BlogpingIT {

    private HttpClient client = new HttpClient();

    @Test
    public void send_update_and_verify_response() throws IOException {
        String name = "AtexBlog";
        String url = "http://www.atex.com";

        GetMethod ping = new GetMethod("http://localhost:8080/pingSiteForm?name=" + name
            + "&url=" + URIUtil.encodeWithinQuery(url));

        try {
            client.executeMethod(ping);
            Assert.assertEquals("Thanks for the ping.", ping.getResponseBodyAsString());
        } finally {
            ping.releaseConnection();
        }

        GetMethod changes = new GetMethod("http://localhost:8080/changes.xml");

        try {
            client.executeMethod(changes);
            Assert.assertTrue(changes.getResponseBodyAsString().contains(name));
            Assert.assertTrue(changes.getResponseBodyAsString().contains(url));
        } finally {
            changes.releaseConnection();
        }
    }


}
