package com.example.adebola.sherlockholmes;

/**
 * Created by Adebola on 2017-03-25.
 */
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/// App is waiting on the server. A key word was detected. the whole String from server
//  hey/how

//Service Intent to check the Server for command. Do this in the background while
// the program is running

public class ServerService extends IntentService {

    //Notification builder for each notification
    NotificationCompat.Builder notification;

    //Notification ID number. so the system knows how to handle each notifications
    private static final int uniqueID = 45612;


    private static final String  TAG = "ServerService";

    private static final int Max_udpMessage_len = 1000;
    public static String EXTRA_MESSAGE;

    String eventText;



    //Constructor for the Service intent
    public ServerService() {
        super("ServerService");

        //Building the notification
        notification = new NotificationCompat.Builder(this);
        //we want to cancel the notification after the user has seen it and clicked
        notification.setAutoCancel(true);
    }



    @Override
    protected void onHandleIntent(Intent intent) {

        // Run Service to check the server for input while program runs
        Log.i(TAG, "Service Started");
        DatagramSocket dsocket = socketHandler.getSocket();

        try{
            byte[] event = new byte[Max_udpMessage_len];
            DatagramPacket packet = new DatagramPacket(event, event.toString().length());

            //To continuously listen and receive for data
            while (true) {

                Log.i(TAG, "Inside the While loop Now");
                Log.i(TAG, "My port is: "+ dsocket.getPort());
                dsocket.receive(packet);

                eventText = new String(packet.getData());
                //Log.i("UDP packet is received", eventText);


                Log.i(TAG, "Calling Notification Now");
                //History.setText(eventText);
                sendNotification(eventText);

            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if (dsocket != null){
                dsocket.close();
            }
        }
    }

    //Method to build the notification and
    public void sendNotification (String eventText){

        //Notification Image
        notification.setSmallIcon(R.drawable.sherlockholmes);

        //Notification Sound
        notification.setTicker("this is the Ticket");
        //Time of the notification
        notification.setWhen(System.currentTimeMillis());



        notification.setContentTitle("URGENT!!! CHILD IN TROUBLE");
        notification.setContentText(eventText);


        //What happens when the user clicks on the notification. send the user to the mainpage
        Intent intent1 = new Intent(this, History.class);
        //Giving the device access the intent in our app
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //send out the Notification and issue it(sending it to device)
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //building it and sending it out

        //Sends out notification only when event occurs
            nm.notify(uniqueID, notification.build());

    }

    public String getEvent(){
        return eventText;
    }
}


