package com.atex.blogping.service;

import com.atex.blogping.dao.BlogpingDAO;
import com.atex.blogping.dto.WeblogDTO;
import com.atex.blogping.jaxb.Changes;
import com.atex.blogping.jaxb.ChangesFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.List;

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
        List<WeblogDTO> weblogs = blogpingDAO.getWeblogs();
        return ChangesFactory.createChanges(weblogs);
    }
}
