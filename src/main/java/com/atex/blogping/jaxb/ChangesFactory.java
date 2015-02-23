package com.atex.blogping.jaxb;

import com.atex.blogping.dto.WeblogDTO;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

public class ChangesFactory {

    public static final Changes createChanges(List<WeblogDTO> weblogs) throws DatatypeConfigurationException {
        DateTime now = DateTime.now();

        Changes changes = new Changes();
        changes.setUpdated(toXmlGregorianCalendar(now));

        for (WeblogDTO dto : weblogs) {
            changes.getWeblogs().add(createWeblog(dto, now));
        }

        return changes;
    }

    private static XMLGregorianCalendar toXmlGregorianCalendar(DateTime now) throws DatatypeConfigurationException {
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(now.toGregorianCalendar());
    }

    private static Weblog createWeblog(final WeblogDTO dto, final DateTime updated) {
        Weblog log = new Weblog();
        log.setName(dto.getName());
        log.setUrl(dto.getUrl());

        log.setWhen(calculateTimeDifferenceInSeconds(dto.getCreated(), updated));
        return log;
    }

    private static long calculateTimeDifferenceInSeconds(DateTime weblogCreated, DateTime changesUpdated) {
        return new Duration(weblogCreated, changesUpdated).getStandardSeconds();
    }
}
