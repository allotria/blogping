package com.atex.blogping;


import junit.framework.Assert;
import org.junit.Test;

public class HelloAtexServletTest {

    @Test
    public void greeting_from_atex() {
        HelloAtexServlet target = new HelloAtexServlet();
        Assert.assertTrue(target.greeting().contains("Atex"));
    }

}
