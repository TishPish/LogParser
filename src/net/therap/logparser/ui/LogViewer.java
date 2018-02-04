package net.therap.logparser.ui;

/**
 * Created by zihad on 2/1/18.
 */
public class LogViewer
{
    public LogViewer()
    {

    }

    public void showErrorMessage(String info) {
        System.err.println(info);
    }

    public void showLogSlotInfo(String data)
    {
        System.out.println(data);
    }
    public void showTableHeaders()
    {
        System.out.println("\tTime \t\t\t|\tGET/POST Count\t|\tUnique URL Count\t|\tTotal Response Time");
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    public void printMessage(String data1)
    {
        System.out.println(data1);
    }
}
