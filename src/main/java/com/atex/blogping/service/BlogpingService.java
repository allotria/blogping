package com.atex.blogping.service;

import com.atex.blogping.dao.BlogpingDAO;
import com.atex.blogping.dto.WeblogDTO;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.servlet.RequestParameters;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
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
    public String pingSiteFormGet(@QueryParam("name") String name,
                                  @QueryParam("url") String url,
                                  @QueryParam("changesURL") String changesURL) {

            // For this implementation we ignore the 'changesURL' parameter

        saveWeblog(name, url);

        return "Thanks for the ping with GET.";
    }


    @POST
    public String pingSiteFormPost(@FormParam("name") String name,
                                   @FormParam("url") String url,
                                   @FormParam("changesURL") String changesURL) {

        // changesURL is ignored here as well

        saveWeblog(name, url);

        return "Thanks for the ping with POST.";
    }

    private void saveWeblog(String name, String url) {
        blogpingDAO.saveWeblog(new WeblogDTO(name, url));
    }
}
