package net.therap.logparser.models;

import net.therap.logparser.services.SlotTimeRangeGenerator;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zihad on 2/4/18.
 */
public class LogSlotModel
{
    private int logSlotGETCount,logSlotPOSTCount,logSlotUniqueURICount,logSlotResponseTime;
    private LogTimeRangeModel logTimeRange;
    List<LogModel> logSlotContents;
    HashMap<URI,Integer> logUniqueURIMap;

    public LogSlotModel(int logSlotStartTime)
    {
        logTimeRange = new LogTimeRangeModel(logSlotStartTime);
        this.logSlotGETCount = 0;
        this.logSlotPOSTCount =0;
        this.logSlotResponseTime=0;
        this.logSlotUniqueURICount = 0;

        this.logSlotContents = new ArrayList<LogModel>();
        logUniqueURIMap = new HashMap<URI,Integer>();
    }

    public void addLogModelToLogSlot(LogModel logModel)
    {
        logSlotContents.add(logModel);
        if (logModel.getLogMethod()== Method.GET)
            logSlotGETCount++;
        else
            logSlotPOSTCount++;

        logSlotResponseTime+= logModel.getLogResponseTime();
        if (isUniqueURI(logModel.getLogURI()))
        {
            logUniqueURIMap.put(logModel.getLogURI(), 1);
            logSlotUniqueURICount++;
        }
    }

    public LogTimeRangeModel getLogTimeRange() {
        return logTimeRange;
    }

    public boolean isUniqueURI(URI logURI)
    {
        if (logUniqueURIMap.containsKey(logURI))
            return false;
        return true;
    }

    public int getLogSlotResponseTime()
    {
        return logSlotResponseTime;
    }

    public int getLogSlotUniqueURICount()
    {
        return logSlotUniqueURICount;
    }

    public int getLogSlotPOSTCount()
    {
        return logSlotPOSTCount;
    }

    public int getLogSlotGETCount()
    {
        return logSlotGETCount;
    }

    public List<LogModel> getLogSlotContents()
    {
        return logSlotContents;
    }




    public String getLogSlotInfo()
    {
        return new SlotTimeRangeGenerator(logTimeRange).getTimeSlotInfo()+"\t|\t\t"+logSlotGETCount+
                "/"+logSlotPOSTCount+ "\t\t|\t\t\t"+logSlotUniqueURICount+"\t\t\t|\t\t"+logSlotResponseTime;
    }


}