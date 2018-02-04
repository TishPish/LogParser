package net.therap.logparser.app;

import net.therap.logparser.controller.LogController;

/**
 * Created by zihad on 2/1/18.
 */
public class LogParser
{
    static LogController controller;
    public static void main( String[] args)
    {
        if (args.length==1)
        {
            controller = new LogController(args[0]);
            controller.startParsingOperation();
        }
        else if (args.length==2)
        {
            if (args[1].matches("--sort"))
            {
                controller = new LogController(args[0],true);
                controller.startParsingOperation();
            }
            else // wrong command
            {
                controller = new LogController();
                controller.generateErrorMessage("Invalid command");
            }
        }
        else // empty command or more than 2 commands shows error
        {
            controller = new LogController();
            controller.generateErrorMessage("Inappropriate command");
        }


    }
}
