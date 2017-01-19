package com.main;

import org.jibble.pircbot.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Thinh-Laptop on 19.01.2017.
 */
public class PircBot extends org.jibble.pircbot.PircBot{

    public ArrayList<String> curse = new ArrayList<>();
    public Random r = new Random(10);

    public PircBot(String name) {
        this.setName(name);
        this.setLogin("["+name+"]");

        curse.add("Sag mal, ist Dein Clown-Kostüm in der Reinigung?");
        curse.add("Schau mal im Lexikon unter Arsch nach. Da ist dein Gesicht abgebildet.");
        curse.add("Geh doch auf der Autobahn ein bischen spielen.");
        curse.add("Wenn ich Dein Gesicht hätte, würde ich lachend in eine Kreissäge laufen.");
        curse.add("Hier haste 50 Pfennig. Damit kannste eine Parkuhr mit deinem Müll vollquatschen.");
        curse.add("Du bist ganz cool.");
        curse.add("Du hast doch gerade soviele Gehirnzellen, um nicht ins Wohnzimmer zu kacken");
        curse.add("Mit deinem Arsch könnte sich ganz China sattfresssen.");
        curse.add("Das einzig Positive in Deinem Leben war doch der AIDS-Test");
        curse.add("Sorry, daß ich Dich unterbreche, aber es hat mich nicht im geringsten interessiert!");
        curse.add("Wir geht es eigentlich deiner Mutter?");
        curse.add("hdf");
    }

    public void onMessage(String channel, String sender,
                          String login, String hostname, String message) {
        sendMessage(channel,"@" + sender + " " +curse.get(r.nextInt(curse.size())));

        if (message.equalsIgnoreCase("thinh")) {
            String time = new java.util.Date().toString();
            sendMessage(channel, sender + ": just wrote my name huiiii");

        }
    }
}
