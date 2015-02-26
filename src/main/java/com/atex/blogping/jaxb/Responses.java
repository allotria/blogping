package com.atex.blogping.jaxb;

public interface Responses {
    final Response OK = new Response("Thanks for the ping.", 0);
    final Response NAME_TOO_LONG = new Response("Can't accept the ping because the weblogName was too long.", 1);
    final Response NAME_MUST_NOT_BE_EMPTY = new Response("Can't accept the ping because the weblog name must not be empty.", 1);
    final Response URL_MUST_START_WITH_HTTP_S = new Response("Can't accept the ping because the URL must begin with http:// or https://.", 1);
    final Response URL_TOO_LONG = new Response("Can't accept the ping because the weblogUrl was too long.", 1);
    final Response URL_MUST_NOT_BE_EMPTY = new Response("Can't accept the ping because the weblog URL must not be empty.", 1);
    final Response INTERNAL_SERVER_ERROR = new Response("Internal Server Error", 1);
}
