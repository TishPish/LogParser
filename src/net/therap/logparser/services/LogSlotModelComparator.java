package net.therap.logparser.services;

import net.therap.logparser.models.LogSlotModel;
import net.therap.logparser.models.LogTimeRangeModel;

import java.util.Comparator;

/**
 * Created by zihad on 2/4/18.
 */
public class LogSlotModelComparator implements Comparator<LogSlotModel>
{
    @Override
    public int compare(LogSlotModel o1, LogSlotModel o2)
    {
        if ((o1.getLogSlotGETCount()+o1.getLogSlotPOSTCount())<(o2.getLogSlotGETCount()+o2.getLogSlotPOSTCount()))
            return 1;
        else if ((o1.getLogSlotGETCount()+o1.getLogSlotPOSTCount())>(o2.getLogSlotGETCount()+o2.getLogSlotPOSTCount()))
            return -1;
        else
        {
            LogTimeRangeModel ob1Log = o1.getLogTimeRange();
            LogTimeRangeModel ob2Log = o2.getLogTimeRange();
            if (ob1Log.getLogSlotStartTime()<ob2Log.getLogSlotStartTime())
                return -1;
            else
                return 1;
        }
    }
}
