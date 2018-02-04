package net.therap.logparser.controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import net.therap.logparser.services.LogModelGenerator;
import net.therap.logparser.services.InputValidator;
import net.therap.logparser.ui.LogViewer;
import net.therap.logparser.models.LogModel;
import net.therap.logparser.models.LogSlotModel;
import net.therap.logparser.services.LogSlotModelComparator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by zihad on 2/1/18.
 */
public class LogController
{
    private String logFileName;
    private List<LogModel> logModelList;
    private HashMap<Integer, LogSlotModel> availableLogSlots;
    private LogViewer logViewer;
    private boolean isSort = false;
    /*Called when only file name is available*/
    public LogController(String logFileName)
    {
        this.logFileName = logFileName;
        logModelList = new ArrayList<LogModel>();
        availableLogSlots = new HashMap<Integer, LogSlotModel>();
        logViewer = new LogViewer(); // assigning view with the controller
    }

    /*Called when filename and operation type is available*/
    public LogController(String logFileName, boolean isSort)
    {
        this.logFileName = logFileName;
        this.isSort = isSort;
        logModelList = new ArrayList<LogModel>();
        availableLogSlots = new HashMap<Integer, LogSlotModel>();
        logViewer = new LogViewer(); // assigning view with the controller
    }

    /* called only to show error to user*/
    public LogController()
    {
        logViewer = new LogViewer(); // assigning view with the controller
    }

    /*Features the parsing operation from inout file, then processing and generating output data*/
    public void startParsingOperation()
    {
        startFileInput();
        arrangeDataInLogSlots();
        logViewer.showTableHeaders();
        if (isSort)
            generateAndShowSortedLogSlots();
        else
            generateAndShowUnSortedLogSlots();
    }


    /*
    This method takes input from the given file
    * */
    private void startFileInput()
    {
        try
        {
            FileReader fp = new FileReader(logFileName);
            BufferedReader reader = new BufferedReader(fp);

            String data;
            while ((data = reader.readLine())!=null)
            {
                if (new InputValidator(data).isValidInput())
                {
                    LogModel singleModel = new LogModelGenerator(data).getProcessedModel(data);
                    if (singleModel!=null)
                        logModelList.add(singleModel);
                }
            }

        }
        catch (Exception e)
        {
            logViewer.showErrorMessage(e.toString());
        }
    }

/* Specify logModels in specific time slots */
    public void arrangeDataInLogSlots()
    {
        for (LogModel logModel: logModelList)
        {
            if (!availableLogSlots.containsKey(logModel.getLogHour()))
            {
                availableLogSlots.put(logModel.getLogHour(), new LogSlotModel(logModel.getLogHour()));
            }
            LogSlotModel specificLogSlot = availableLogSlots.get(logModel.getLogHour());
            specificLogSlot.addLogModelToLogSlot(logModel);
        }

    }

    /*this method generates and shows unsorted logSlotSummary*/
    public void generateAndShowUnSortedLogSlots()
    {
        TreeMap<Integer, LogSlotModel> availableSortedLogSlots = new TreeMap<Integer, LogSlotModel>(availableLogSlots);
        Set<Entry<Integer, LogSlotModel>> mappings = availableSortedLogSlots.entrySet();
        for (Entry<Integer, LogSlotModel> log : mappings)
        {
            LogSlotModel logSlotModel = log.getValue();
            logViewer.showLogSlotInfo(logSlotModel.getLogSlotInfo());
        }
    }



    /*this method generates and shows sorted logSlotSummary*/

    public void generateAndShowSortedLogSlots()
    {
        ArrayList<LogSlotModel> sortedLogSlotModels = new ArrayList<LogSlotModel>();
        for (LogSlotModel logSlotModel: availableLogSlots.values())
        {
            sortedLogSlotModels.add(logSlotModel);
        }
        Collections.sort(sortedLogSlotModels,new LogSlotModelComparator());

        for (Iterator iterator = sortedLogSlotModels.iterator();iterator.hasNext();)
        {
            LogSlotModel logSlotModel= (LogSlotModel) iterator.next();
            logViewer.showLogSlotInfo(logSlotModel.getLogSlotInfo());
        }

    }

    /*called when there is an error in the input command*/

    public void generateErrorMessage(String message)
    {
        logViewer.showErrorMessage(message);
    }



}
