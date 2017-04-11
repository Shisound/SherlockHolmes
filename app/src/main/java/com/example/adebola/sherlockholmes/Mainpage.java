package com.example.adebola.sherlockholmes;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.net.Socket;
import java.util.logging.SocketHandler;

public class Mainpage extends AppCompatActivity {


    //Tag name for checking the Log
    private static final String TAG = "MainPage";
        Intent intent4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
      //  intent4 = new Intent(this, History.class);
       // ServerService service = new ServerService();
        //Intent for the Service... This runs the ServerService when the App is Logged in
        Intent ServiceIntent = new Intent(this, ServerService.class);
        startService(ServiceIntent);

        //Printout Logs of the activity on MainPage
        Log.i(TAG, "OnCreate");
    }


    @Override
    protected void onStart() {
        super.onStart();

        //Printout Logs of the activity on MainPage
        Log.i(TAG, "OnStart");
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Printout Logs of the activity on MainPage
        Log.i(TAG, "OnPause");
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Printout Logs of the activity on MainPage
        Log.i(TAG, "OnResume");
    }


    // Shows history of the event when the History button is clicked
    public void History(View view){
        //Intent class does something when this method is called. Takes two parameters
        //the context and the class. MainPage is entered when user clicks button

        Log.i(TAG, "History Button Clicked");

          //Start the Next Activity that contains the history
          startActivity(intent4);
        Log.i(TAG, "After the History activity is started");
    }
}
