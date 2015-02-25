package com.atex.blogping.jaxb;

import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;

/**
 * The changes.xml file conforms to the following XML structure:
 *
 * <weblogUpdates version="2" updated="Mon, 10 Oct 2005 14:10:00 GMT" count="1384779">
 *     <weblog name="Weblogs.com" url="http://www.weblogs.com" when="1"/>
 *     <weblog name="My Blog site" url="http://www.myblogsite.com" when="2"/>
 *     <weblog name="Another site" url="http://www.anothersite.com" when="3"/>
 * </weblogUpdates>
 */
@XmlRootElement(name = "weblogUpdates")
@XmlType(propOrder = {"version", "updated", "count", "weblogs"})
public class Changes {

    private String updated;
    private int count;
    private List<Weblog> weblogs = new ArrayList<>();

    @XmlAttribute
    public String getVersion() {
        return "2";
    }

    @XmlAttribute
    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @XmlAttribute
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @XmlElement(name = "weblog")
    public List<Weblog> getWeblogs() {
        return weblogs;
    }

    public void setWeblogs(List<Weblog> weblogs) {
        this.weblogs = weblogs;
    }
}
