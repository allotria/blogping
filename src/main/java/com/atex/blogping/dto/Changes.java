package com.atex.blogping.dto;

import javax.xml.bind.annotation.XmlRootElement;
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
public class Changes {

    private List<Weblog> weblogs = new ArrayList<>();

    public List<Weblog> getWeblogs() {
        return weblogs;
    }

    public void setWeblogs(List<Weblog> weblogs) {
        this.weblogs = weblogs;
    }
}
