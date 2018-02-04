package net.therap.logparser.services;

import net.therap.logparser.models.LogTimeRangeModel;

/**
 * Created by zihad on 2/4/18.
 */
public class SlotTimeRangeGenerator
{
    LogTimeRangeModel logTimeRangeModel;
    public SlotTimeRangeGenerator(LogTimeRangeModel logTimeRangeModel)
    {
        this.logTimeRangeModel = logTimeRangeModel;
    }
    public String getTimeSlotInfo()
    {
        String startTimeString = getProcessedTime(logTimeRangeModel.getLogSlotStartTime());
        String endTimeString = getProcessedTime(logTimeRangeModel.getLogSlotEndTime());
        return startTimeString+" - "+endTimeString;
    }

    private String getProcessedTime(int timeValue)
    {
        //String convertedTime;
        if (timeValue==0 || timeValue==24)
            return "12.00 am";
        else if (timeValue<=11)
            return timeValue+".00 am";
        else if (timeValue%12==0 && timeValue<24)
            return timeValue+".00 pm";
        else
            return (timeValue%12)+".00 pm";
    }
}
