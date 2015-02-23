package com.atex.blogping.service;

import com.atex.blogping.dao.BlogpingDAO;
import com.atex.blogping.dto.Changes;
import com.atex.blogping.dto.Weblog;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Singleton
@Path("changes.xml")
public class ChangesService {

    private final BlogpingDAO blogpingDAO;

    @Inject
    public ChangesService(BlogpingDAO blogpingDAO) {
        this.blogpingDAO = blogpingDAO;
    }

    @GET
    @Produces("application/xml")
    public Changes serveChanges(@Context HttpServletRequest httpServletRequest) {

        Changes changes = new Changes();
        changes.getWeblogs().add(new Weblog("foo", "http://foo.bar/blog", 1));
        return null;
    }
}
