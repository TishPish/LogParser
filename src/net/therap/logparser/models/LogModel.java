package net.therap.logparser.models;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by zihad on 2/1/18.
 */
public class LogModel
{
    private Date logDate;
    private Method logMethod;
    private URI logURI;
    private int logResponseTime;

    public LogModel(Date logDate, Method logMethod, URI logURI, int logResponseTime)
    {
        this.logDate = logDate;
        this.logMethod = logMethod;
        this.logResponseTime= logResponseTime;
        this.logURI = logURI;
    }

    public Date getLogDate() {
        return logDate;
    }

    public int getLogResponseTime() {
        return logResponseTime;
    }

    public Method getLogMethod() {
        return logMethod;
    }

    public URI getLogURI() {
        return logURI;
    }

    public int getLogHour()
    {
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(logDate);   // assigns calendar to given date
        return calendar.get(Calendar.HOUR_OF_DAY);// gets hour in 24h format
    }

}
