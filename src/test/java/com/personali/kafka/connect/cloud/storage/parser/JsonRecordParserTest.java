package com.personali.kafka.connect.cloud.storage.parser;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by or on 28/12/16.
 */
public class JsonRecordParserTest {

    @Test
    public void getStringField() throws Exception {
        JsonRecordParser jrp = new JsonRecordParser();
        String json = "{\"id\":\"1\", \"sub\":{}, \"event_time\":\"2016-01-01 10:00:00\"}";
        String value = jrp.getStringField(json, "id");
        assertThat(value, is("1"));

        json = "{\"id\":1, \"sub\":{}, \"event_time\":\"2016-01-01 10:00:00\"}";
        value = jrp.getStringField(json, "id");
        assertThat(value, is("1"));
    }

    @Test
    public void testGetDateFieldSuccess() throws Exception {
        JsonRecordParser jrp = new JsonRecordParser();
        String json = "{\"id\":1, \"sub\":{}, \"event_time\":\"2016-01-01 10:00:00\"}";
        Date event_time = jrp.getDateField(json, "event_time", "yyyy-MM-dd HH:mm:ss");
        assertThat(event_time, is((new GregorianCalendar(2016, Calendar.JANUARY, 1, 10,0,0)).getTime()));

        json = "{\"id\":1, \"sub\":{\"sub1\":1}, \"arr1\": [], \"event_time\":\"2016-01-01 10:00:00\"}";
        event_time = jrp.getDateField(json, "event_time", "yyyy-MM-dd HH:mm:ss");
        assertThat(event_time, is((new GregorianCalendar(2016, Calendar.JANUARY, 1, 10,0,0)).getTime()));
    }
}