package com.atex.blogping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlogpingServlet extends HttpServlet
{
    private String name;
    private String url;

    /**
     * Example code. Feel free to modify!
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().startsWith("/pingSiteForm")) {
            handlePostForm(req, resp);
        } else if (req.getRequestURI().startsWith("/changes.xml")) {
            handleChangesXML(req, resp);
        }

    }

    /**
     * Example code. Feel free to modify!
     */
    private void handlePostForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        name = req.getParameter("name");
        url = req.getParameter("url");
        resp.getWriter().write("Thanks for the ping.");
    }

    /**
     * Example code. Feel free to modify!
     */
    private void handleChangesXML(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/xml");
        resp.getWriter().write("<weblogUpdates version=\"2\" updated=\"Mon, 10 Oct 2005 14:10:00 GMT\" count=\"1384779\">\n" +
                "    <weblog name=" + name + " url=" + url + " \n" +
                "               rssUrl=\"http://www.weblogs.com/rss.xml\" when=\"1\"/>" +
                "</weblogUpdates>");
    }
}
