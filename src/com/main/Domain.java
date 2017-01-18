package com.main;

/**
 * Created by Thinh on 17.06.2016.
 */

import com.Log.LogFormatter;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**The following lists the Log Levels in descending order:
        SEVERE (highest)
        WARNING
        INFO
        CONFIG
        FINE
        FINER
        FINEST
**/

public class Domain {

    private final static Logger log = Logger.getLogger(Domain.class.getName());
    //static private FileHandler fileTxt ;
    static private ConsoleHandler console ;
    static private LogFormatter formatterTxt;



    public static void main(String[] args) {
        System.out.println("Test startet");

        log.setUseParentHandlers(false);  // disable root Console Logger

        /*try {
            fileTxt = new FileHandler("Logging.txt"); //now does write onto FILE
        }catch(IOException e)
        {LOGGER.severe(e.toString());}*/

        console = new ConsoleHandler();

        formatterTxt = new LogFormatter();
        console.setFormatter(formatterTxt);
        log.addHandler(console);

        log.setLevel(Level.FINER);



        try {
            HTTPResponse.processHTTP();
        }catch (Exception e){
            log.log( Level.SEVERE, e.toString(), e );
            return;
        }
        return ;


    }

    public static int mult(int n,int m){
        return n*m ;
    }

}
