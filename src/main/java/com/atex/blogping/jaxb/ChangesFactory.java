package com.atex.blogping.jaxb;

import com.atex.blogping.dto.WeblogDTO;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

public class ChangesFactory {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("E, dd MMM yyyy HH:mm:ss 'GMT'");
    public static final String TIME_ZONE_ID_GMT = "GMT";

    public static final Changes createChanges(List<WeblogDTO> weblogs) {
        Changes changes = new Changes();

        DateTime now = getFormattedTimeNow(changes);

        for (WeblogDTO dto : weblogs) {
            changes.getWeblogs().add(createWeblog(dto, now));
        }

        return changes;
    }

    private static DateTime getFormattedTimeNow(Changes changes) {
        DateTime now = DateTime.now().withZone(DateTimeZone.forID(TIME_ZONE_ID_GMT));
        changes.setUpdated(now.toString(DATE_TIME_FORMATTER));
        return now;
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
