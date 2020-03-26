package com.hikami.cheeriobubman.dev;

import androidx.appcompat.app.AppCompatActivity;
import com.clevertap.android.sdk.CleverTapAPI;
import android.os.Bundle;
import android.app.NotificationManager;
import android.view.View;
import android.widget.Button;
import com.clevertap.android.sdk.CTInboxActivity;
import com.clevertap.android.sdk.CTInboxListener;
import com.clevertap.android.sdk.CTInboxStyleConfig;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import java.util.*;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements CTInboxListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonOne = findViewById(R.id.Event);
        Button buttonTwo = findViewById(R.id.inbox);

        CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.DEBUG);
        CleverTapAPI.createNotificationChannel(getApplicationContext(),"DK","Your Channel Name","Your Channel Description",NotificationManager.IMPORTANCE_MAX,true);
        buttonOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                CleverTapAPI.getDefaultInstance(getApplicationContext()).pushEvent("Happy event");
            }
        });
        buttonTwo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                inboxDidInitialize();
            }
        });
        if (clevertapDefaultInstance != null) {
            //Set the Notification Inbox Listener
            clevertapDefaultInstance.setCTNotificationInboxListener(this);
            //Initialize the inbox and wait for callbacks on overridden methods
            clevertapDefaultInstance.initializeInbox();
            clevertapDefaultInstance.setCTNotificationInboxListener(this);
        }


    }

    @Override
    public void inboxDidInitialize() {

        Button buttonTwo = findViewById(R.id.inbox);
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                ArrayList<String> tabs = new ArrayList<>();
                tabs.add("Promotions");
                tabs.add("Offers");
                tabs.add("Others");//We support upto 2 tabs only. Additional tabs will be ignored

                CTInboxStyleConfig styleConfig = new CTInboxStyleConfig();
                styleConfig.setTabs(tabs);//Do not use this if you don't want to use tabs
                styleConfig.setTabBackgroundColor("#FF0000");//provide Hex code in string ONLY
                styleConfig.setSelectedTabIndicatorColor("#0000FF");
                styleConfig.setSelectedTabColor("#000000");
                styleConfig.setUnselectedTabColor("#FFFFFF");
                styleConfig.setBackButtonColor("#FF0000");
                styleConfig.setNavBarTitleColor("#FF0000");
                styleConfig.setNavBarTitle("MY INBOX");
                styleConfig.setNavBarColor("#FFFFFF");
                styleConfig.setInboxBackgroundColor("#00FF00");

                CleverTapAPI.getDefaultInstance(getApplicationContext()).showAppInbox(styleConfig); //Opens activity tith Tabs
                //OR
                CleverTapAPI.getDefaultInstance(getApplicationContext()).showAppInbox();//Opens Activity with default style config
            }
        });

    }



    @Override
    public void inboxMessagesDidUpdate() {

    }
}
