package com.Log;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Thinh-Laptop on 03.08.2016.
 * Verbosity Level
 * DEBUG
 * INFORMATION
 */
public class Logger {

    public enum Level{
        DEBUG(0),
        INFO(1);

        private final int level ;
        Level(int i){
            level = i ;
        }
    }

    private static int mLevel ;

    public static void setLevel(Level i){
        mLevel = i.level ;
    }

    public static int getLevel(){return mLevel ; }

    public static void debug(String msg){
        if(mLevel >= Level.DEBUG.level)
            print(Level.DEBUG.toString().charAt(0) + ": " + msg,System.out) ;
    }

    public static void info(String msg){
        if(mLevel >= Level.INFO.level)
            print(Level.INFO.toString().charAt(0) + ": " + msg,System.out) ;
    }

    private static void print(String msg, PrintStream out) {
        out.println(new SimpleDateFormat("HH:mm.ss").format(new Date()) + " " +
                msg);
    }
}
