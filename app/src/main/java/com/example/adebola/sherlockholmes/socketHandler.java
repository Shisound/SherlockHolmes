package com.example.adebola.sherlockholmes;

import android.util.Log;

import java.net.DatagramSocket;

/**
 * Created by Adebola on 2017-04-06.
 */

public class socketHandler {
    private static DatagramSocket socket;
    private static final Object TAG = "SocketHandler";
    public static synchronized void createSocket() {
        try {
            socket = new DatagramSocket();
        } catch (Exception e) {
        }
    }
    public static synchronized DatagramSocket getSocket(){

        return socket;
    }
    public static synchronized void setSocket(DatagramSocket s){
        socket = s;
    }
}
