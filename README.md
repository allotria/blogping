=================================
1. Work assignment 
=================================

The goal of the test is to show how you would normally take on a java-based programming task.
The assessment will be based on the following criterieas:

 - Clean design
 - Reuse (3:rd part products)
 - Maintainability
 - Testability/Testing
 - Coding style
 - Requirement analysis

You decide how "deep" you go in your implementation to satisfy the criterias.


=====================================
2. Assignment description - Blog Ping
=====================================

Write a Java client and server for the REST version of the "blog ping" protocol.

Client:
 - Command-Line or web interface
 - Calls a blogping server using the REST-api
 - Handles errors

Server:
 - Accepts blogping messages using REST
 - Serves a changes.xml over HTTP
 - Handles concurrent connections

The blogping and changes.xml format is documented here: http://www.weblogs.com/api.html (note that only the REST interface is necessary for this assignment).


=================================
3. Provided code base
=================================

You will need Maven and a recent JDK. You can verify that your development evnironment is sound by running:

    mvn jetty:run

open then your browser of choice and point it to http://localhost:8080/hello. You should see a greeting message.

The provided base code includes both integration and unit tests. You can run integration test form the command line, just type:

    mvn verify
    
The servlet (BlogpingServlet) and the integration test (BlogpingIT) are just provided as an example, use them as a starting point or throw them away and start from scratch. Good luck. 
