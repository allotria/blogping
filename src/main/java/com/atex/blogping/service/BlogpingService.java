package com.atex.blogping.service;

import com.atex.blogping.dao.BlogpingDAO;
import com.atex.blogping.dto.WeblogDTO;
import com.atex.blogping.jaxb.Response;
import com.atex.blogping.jaxb.Responses;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.ws.rs.*;

@Singleton
@Path("pingSiteForm")
public class BlogpingService {

    private final BlogpingDAO blogpingDAO;


    @Inject
    public BlogpingService(BlogpingDAO blogpingDAO) {
        this.blogpingDAO = blogpingDAO;
    }


    @GET
    @Produces("application/xml")
    public Response pingSiteFormGet(@QueryParam("name") String name,
                                    @QueryParam("url") String url,
                                    @QueryParam("changesURL") String changesURL) {

        // For this implementation we ignore the 'changesURL' parameter

        saveWeblog(name, url);

        return Responses.OK;
    }


    @POST
    @Produces("application/xml")
    public Response pingSiteFormPost(@FormParam("name") String name,
                                     @FormParam("url") String url,
                                     @FormParam("changesURL") String changesURL) {

        // changesURL is ignoredhere as well

        saveWeblog(name, url);

        return Responses.OK;
    }

    private void saveWeblog(String name, String url) {
        blogpingDAO.saveWeblog(new WeblogDTO(name, url));
    }
}
