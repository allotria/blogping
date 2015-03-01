package com.atex.blogping.dao.impl;


import com.atex.blogping.dao.BlogpingDAO;
import com.atex.blogping.dto.WeblogDTO;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class QueueBackedBlogpingDAOTest {

    private BlogpingDAO dao;


    @Test
    public void shouldStoreWithoutException() {

        givenBlogpingDAO();
        WeblogDTO weblog = givenNewWeblog("foo", "http://www.url.com");

        when().saveWeblog(weblog);

        // then
        assertEquals(weblog, dao.getWeblogs().get(0));

    }

    @Test
    public void shouldRetrieveInReverseOrder() {
        givenBlogpingDAO();
        WeblogDTO one = givenNewWeblog("one", "http://www.one.com");
        WeblogDTO two = givenNewWeblog("two", "http://www.two.com");
        WeblogDTO three = givenNewWeblog("three", "http://www.three.com");

        when().saveWeblog(one);
        when().saveWeblog(two);
        when().saveWeblog(three);

        //then
        assertArrayEquals(new WeblogDTO[]{three, two, one}, dao.getWeblogs().toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNull() {
        givenBlogpingDAO();

        when().saveWeblog(null);
    }


    private WeblogDTO givenNewWeblog(String name, String url) {
        return new WeblogDTO(name, url);
    }


    private void givenBlogpingDAO() {
        dao = new QueueBackedBlogpingDAO();
    }

    private BlogpingDAO when() {
        return dao;
    }

}