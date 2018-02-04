package net.therap.logparser.models;

/**
 * Created by zihad on 2/4/18.
 */

public class LogTimeRangeModel
{
    private int logSlotStartTime,logSlotEndTime;

    public LogTimeRangeModel(int startTime)
    {
        this.logSlotStartTime = startTime;
        this.logSlotEndTime = startTime+1;
    }

    public int getLogSlotStartTime() {
        return logSlotStartTime;
    }

    public int getLogSlotEndTime() {
        return logSlotEndTime;
    }
}