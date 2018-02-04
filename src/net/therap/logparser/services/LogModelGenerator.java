package net.therap.logparser.services;

import net.therap.logparser.models.LogModel;
import net.therap.logparser.models.Method;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zihad on 2/4/18.
 */
public class LogModelGenerator
{
    private String inputData;
    public LogModelGenerator(String inputData)
    {
        this.inputData = inputData;
    }

    public LogModel getProcessedModel(String data)
    {
        Date logDate = getLogDateInfo(data);
        Method logMethod = getLogMethod(data);
        URI logURI = getLogURI(data);
        int logResponseTime = getLogResponseTime(data);

        if (logDate!=null && logMethod!=null && logURI !=null && logResponseTime!=-1)
        {
            return new LogModel(logDate, logMethod, logURI, logResponseTime);
        }

        return null;
    }

    private Date getLogDateInfo(String data)
    {
        Pattern dateFinder = Pattern.compile("(.+?) \\[.*");
        if (dateFinder.matcher(data).matches())
        {
            Matcher matcher = dateFinder.matcher(data);
            matcher.find();
            return getParsedDate(matcher.group(1));
        }
        else
        {
            return null;
        }
    }


    private Method getLogMethod(String data)
    {
        Pattern dateFinder = Pattern.compile(".*URI=.*, (.?), time=.*");
        if (dateFinder.matcher(data).matches())
        {
            Matcher matcher = dateFinder.matcher(data);
            matcher.find();
            //System.out.println(matcher.group(1));
            char methodType =matcher.group(1).charAt(0);
            if (methodType == 'P')
                return Method.POST;
            else if (methodType =='G')
                return Method.GET;
            else
                return null;
        }
        else
        {
            return null;
        }

    }


    private URI getLogURI(String data)
    {
        Pattern dateFinder = Pattern.compile(".*URI=\\[(.*?)\\].*");
        if (dateFinder.matcher(data).matches())
        {
            Matcher matcher = dateFinder.matcher(data);
            matcher.find();
            try
            {
                URI logURI = new URI(matcher.group(1));
                return logURI;
            }
            catch (URISyntaxException ex)
            {
                //show error message
                return null;
            }

        }
        else
        {
            return null;
        }

    }


    private int getLogResponseTime(String data)
    {
        Pattern dateFinder = Pattern.compile(".*time=(.*?)ms");
        if (dateFinder.matcher(data).matches())
        {
            Matcher matcher = dateFinder.matcher(data);
            matcher.find();
            try
            {
                int logResponseTime = Integer.parseInt(matcher.group(1));
                return logResponseTime;
            }
            catch (Exception e)
            {
                //show error message
                return -1;
            }

        }
        else
        {
            return -1;
        }

    }


    private Date getParsedDate(String dateValue)
    {
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
            Date date = dateFormat.parse(dateValue);
            return date;
        }
        catch (Exception e)
        {
            //will print exception message from the view class
            return null;
        }

    }

}
