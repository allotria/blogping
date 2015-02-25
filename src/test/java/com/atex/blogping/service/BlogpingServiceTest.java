package com.atex.blogping.service;

import com.atex.blogping.dao.BlogpingDAO;
import com.atex.blogping.dto.WeblogDTO;
import com.atex.blogping.jaxb.Response;
import com.atex.blogping.jaxb.Responses;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BlogpingServiceTest {

    @InjectMocks
    private BlogpingService service;

    @Mock
    private BlogpingDAO blogpingDAO;

    @Before
    public void init() {
        willDoNothing().given(blogpingDAO).saveWeblog(Matchers.<WeblogDTO>any());
    }

    @After
    public void noMoreInteractions() {
        verifyNoMoreInteractions(blogpingDAO);
    }

    @Test
    public void shouldAcceptRequestAndReturnOk() {
        Request valid = givenValidRequest();

        // when
        Response response = service.pingSiteFormGet(valid.name, valid.url, valid.changesURL);

        // then
        assertEquals(Responses.OK, response);
        verify(blogpingDAO).saveWeblog(Matchers.<WeblogDTO>any());
    }

    private Request givenValidRequest() {
        return new Request("allotria", "http://www.allotria.ch/blog", "http://www.allotria.ch/blog/atom.xml");
    }

    private static class Request {
        public final String name;
        public final String url;
        public final String changesURL;

        private Request(String name, String url, String changesURL) {
            this.name = name;
            this.url = url;
            this.changesURL = changesURL;
        }
    }
}