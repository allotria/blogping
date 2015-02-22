package com.atex.blogping.service;

import com.atex.blogping.dao.BlogpingDAO;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Singleton
@Path("pingSiteForm")
public class BlogpingService {

    private final BlogpingDAO blogpingDAO;


    @Inject
    public BlogpingService(BlogpingDAO blogpingDAO) {
        this.blogpingDAO = blogpingDAO;
    }


    @GET
    public String pingSiteFormGet(@Context HttpServletRequest httpServletRequest) {
        return "foo" + httpServletRequest.getParameter("foo");
    }

    @POST
    public String pingSiteFormPost(@Context HttpServletRequest httpServletRequest) {
        return "foo" + httpServletRequest.getParameter("foo");
    }

}
