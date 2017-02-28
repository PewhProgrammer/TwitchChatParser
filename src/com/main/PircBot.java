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
    public int tokenCount;

    public PircBot(String name) {
        this.setName(name);
        this.setLogin("["+name+"]");

        tokenCount = 0;

        /*curse.add("Sag mal, ist Dein Clown-Kostüm in der Reinigung?");
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
        curse.add("hdf");*/

        curse.add("Die Welt ist ein schöner Ort!");
        curse.add("Ich finde junge Welpen sehr niedlich");
        curse.add("Wer hat was gegen mutige Frauen? Ich nicht!");
        curse.add("Ich sah einmal einen Fuchs. Es war sehr zierlich.");
        curse.add("Wer das Licht sieht, sieht auch ein Funken Liebe!");
        curse.add("Liebe ist das stärkste Gefühl.");
        curse.add("Einsamkeit ist doof. Zweisamkeit ist toll.");
        curse.add("Rasant fliegt der Adler durch die Luft.");
        curse.add("Was nicht ist, kann noch werden!");
        curse.add("Es tut mir Leid. Ich bin sehr schüchtern.");
        curse.add("Wir geht es eigentlich deiner Mutter?");
        curse.add("Ich mag dich");
    }

    public void onMessage(String channel, String sender,
                          String login, String hostname, String message) {
        //sendMessage(channel,"@" + sender + " " +curse.get(r.nextInt(curse.size())));

        if (message.equalsIgnoreCase("thinh")) {
            String time = new java.util.Date().toString();
            sendMessage(channel, sender + ": just wrote my name huiiii");
        }

        if(sender.equals("hci_livestreaming") && message.startsWith("!LivestreamingMeetsHCI")){
            StringBuffer target = new StringBuffer(message);
            target.replace( 0 ,23 ,"");
            String[] Tokens = target.toString().split(";");
            //chatConnectable.send(ChatSendMethod.of(String.format("Ping! %d tokens detected from %s",Tokens.length,event.data.userName)));
            for(String s:Tokens){
                if(s.equals("NexXw5")) {
                    tokenCount++;
                    sendMessage(channel, sender + ": Ping! I found my token!!");
                    continue;
                }
            }
        }

        if(sender.equals("pewhtv")){
            if(message.startsWith("!print"))
            sendMessage(channel, String.format("@" + sender + ": Ping! I've counted %d occurences on twitch!",tokenCount));
            if(message.startsWith("!shutdown")){
                sendMessage(channel, "Ping! I'm going offline!!");
                System.exit(1);
            }
        }
    }

    protected void onJoin(String channel,
                          String sender,
                          String login,
                          String hostname){
        if(!sender.equals("pewhbot")) sendMessage(channel,"@" + sender + " Hello World! Ich bin bereit zum Parsen!");
    }
}
