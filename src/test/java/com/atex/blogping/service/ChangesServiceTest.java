package com.atex.blogping.service;

import com.atex.blogping.dao.BlogpingDAO;
import com.atex.blogping.dto.WeblogDTO;
import com.atex.blogping.jaxb.Changes;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChangesServiceTest {

    public static final String VERSION_STRING = "2";
    @InjectMocks
    private ChangesService service;

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
    public void shouldReturnEmptyChanges() {
        // given
        given(blogpingDAO.getWeblogs()).willReturn(emptyChangesList());

        // when
        Changes changes = service.serveChanges(mockHttpRequest());

        // then
        assertTrue(StringUtils.isNotEmpty(changes.getUpdated()));
        assertEquals(0, changes.getCount());
        assertEquals(VERSION_STRING, changes.getVersion());

        assertTrue(changes.getWeblogs().isEmpty());
        verify(blogpingDAO).getWeblogs();
    }

    @Test
    public void shouldReturnOneChange() {
        // given
        given(blogpingDAO.getWeblogs()).willReturn(oneChange());

        // when
        Changes changes = service.serveChanges(mockHttpRequest());

        // then
        assertTrue(StringUtils.isNotEmpty(changes.getUpdated()));
        assertEquals(0, changes.getCount());
        assertEquals(VERSION_STRING, changes.getVersion());

        assertFalse(changes.getWeblogs().isEmpty());
        assertEquals(1, changes.getWeblogs().size());
        assertEquals("allotria", changes.getWeblogs().get(0).getName());
        assertEquals("http://www.allotria.ch", changes.getWeblogs().get(0).getUrl());
        verify(blogpingDAO).getWeblogs();
    }

    private HttpServletRequest mockHttpRequest() {
        return mock(HttpServletRequest.class);
    }

    private List<WeblogDTO> emptyChangesList() {
        return new ArrayList<>();
    }

    private List<WeblogDTO> oneChange() {
        List<WeblogDTO> one = new ArrayList<>();
        WeblogDTO dto = new WeblogDTO("allotria", "http://www.allotria.ch");
        one.add(dto);
        return one;
    }
}