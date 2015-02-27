package com.atex.blogping.service;

import com.atex.blogping.dao.BlogpingDAO;
import com.atex.blogping.dto.WeblogDTO;
import com.atex.blogping.jaxb.Changes;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
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

    @Test
    public void shouldReturnTwoChanges() {
        // given
        given(blogpingDAO.getWeblogs()).willReturn(twoChanges());

        // when
        Changes changes = service.serveChanges(mockHttpRequest());

        // then
        assertTrue(StringUtils.isNotEmpty(changes.getUpdated()));
        assertEquals(0, changes.getCount());
        assertEquals(VERSION_STRING, changes.getVersion());

        assertFalse(changes.getWeblogs().isEmpty());
        assertEquals(2, changes.getWeblogs().size());
        assertEquals("allotria", changes.getWeblogs().get(0).getName());
        assertEquals("http://www.allotria.ch", changes.getWeblogs().get(0).getUrl());
        assertEquals("kopr", changes.getWeblogs().get(1).getName());
        assertEquals("http://www.kopr.se", changes.getWeblogs().get(1).getUrl());
        verify(blogpingDAO).getWeblogs();
    }

    @Test
    public void shouldIncrementCount() {
        // given
        given(blogpingDAO.getWeblogs()).willReturn(emptyChangesList()).willReturn(emptyChangesList());

        // when
        Changes changes = service.serveChanges(mockHttpRequest());
        int count1 = changes.getCount();
        changes = service.serveChanges(mockHttpRequest());
        int count2 = changes.getCount();

        // then
        assertTrue(count1 + 1 == count2);
        verify(blogpingDAO, times(2)).getWeblogs();
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

    private List<WeblogDTO> twoChanges() {
        List<WeblogDTO> weblogs = new ArrayList<>();
        weblogs.add(new WeblogDTO("allotria", "http://www.allotria.ch"));
        weblogs.add(new WeblogDTO("kopr", "http://www.kopr.se"));
        return weblogs;
    }
}