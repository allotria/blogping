package com.atex.blogping.http;


import com.atex.blogping.dao.BlogpingDAO;
import com.atex.blogping.dao.impl.ListBasedBlogpingDAOImpl;
import com.atex.blogping.service.BlogpingService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.spi.container.servlet.ServletContainer;

public class ServletContextListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {

        return Guice.createInjector(new JerseyServletModule() {
            @Override
            protected void configureServlets() {
                bind(BlogpingService.class);
                bind(ServletContainer.class).in(Singleton.class);
                bind(BlogpingDAO.class).to(ListBasedBlogpingDAOImpl.class);

                // (2) Change to using the GuiceContainer
                serve("/*").with(GuiceContainer.class); // <<<<---
            }
        });
    }
}