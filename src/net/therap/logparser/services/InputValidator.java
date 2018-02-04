package net.therap.logparser.services;

import java.util.regex.Pattern;

/**
 * Created by zihad on 2/4/18.
 */
public class InputValidator
{
    private String inputData;

    public InputValidator(String inputData)
    {
        this.inputData = inputData;
    }
    /*
    * This method returns true if the input has a HTTP method value. either GET or POST
    * */
    public boolean isValidInput()
    {
        // data input pattern that checks if the opereation has either a GET method or POST method, has a date, a response time and an URL
        Pattern regExp = Pattern.compile(".*\\[.*\\].*URI=\\[.*\\].*( G| P).*time=.*");

        if (regExp.matcher(inputData).matches())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
