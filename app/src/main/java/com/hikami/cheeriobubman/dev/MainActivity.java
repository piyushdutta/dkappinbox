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
import android.util.Log;
import com.clevertap.android.sdk.SyncListener;
import org.json.JSONObject;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.net.Uri;
import android.content.Intent;

public class MainActivity<jsobj> extends AppCompatActivity implements CTInboxListener{
    SyncListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final EditText username;
        final EditText phone;
        final EditText id;
        setContentView(R.layout.activity_main);

         username = findViewById(R.id.UserName);
         phone = findViewById(R.id.phonetxt);
         id = findViewById(R.id.identity);
        Button buttonOne = findViewById(R.id.loginbutton);
        Button buttonTwo = findViewById(R.id.getctidbutton);
        CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.DEBUG);
        CleverTapAPI.createNotificationChannel(getApplicationContext(),"DK","Your Channel Name","Your Channel Description",NotificationManager.IMPORTANCE_MAX,true);

        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        String url = "https://jokes/thisshouldwork";
        Log.d("its working", "I am in");
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

//        buttonOne.setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v) {
//                HashMap<String, Object> jsobj = new HashMap<String, Object>();
////                jsobj.put("$set","[\"Jeans\",\"Perfume\"]");
////                CleverTapAPI.getDefaultInstance(getApplicationContext()).pushEvent("Happy event");
//                // Do not call onUserLogin directly in the onCreate() lifecycle method
//
//// each of the below mentioned fields are optional
//// with the exception of one of Identity, Email, FBID or GPID
//                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
//                profileUpdate.put("Name", "Jack Montana");    // String
//                profileUpdate.put("Identity", 61026032);      // String or number
//                profileUpdate.put("Tester", "hello" ); // Email address of the user
//                profileUpdate.put("Phone", "+14155551234");   // Phone (with the country code, starting with +)
//                profileUpdate.put("Gender", "M");             // Can be either M or F
//                profileUpdate.put("DOB", new Date());         // Date of Birth. Set the Date object to the appropriate value first
//
////                ArrayList<String> stuff = new ArrayList<String>();
////                stuff.add("bag");
////                stuff.add("shoes");
////                profileUpdate.put("MyStuff", stuff);                        //ArrayList of Strings
////
////                String[] otherStuff = {"Jeans","Perfume"};
////                profileUpdate.put("MyStuff", otherStuff);                   //String Arra
//
//
//                CleverTapAPI.getDefaultInstance(getApplicationContext()).onUserLogin(profileUpdate);
//                CleverTapAPI.getDefaultInstance(getApplicationContext()).pushEvent("Happy event");
//                HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
//                prodViewedAction.put("Product Name", "Casio Chronograph Watch");
//                prodViewedAction.put("Category", "Mens Accessories");
//                prodViewedAction.put("Price", 59.99);
//                prodViewedAction.put("Date", new java.util.Date());
//
//                CleverTapAPI.getDefaultInstance(getApplicationContext()).pushEvent("DateTest", prodViewedAction);
//
//            }
//        });

        buttonOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SyncListener listener1 = new SyncListener() {
                    @Override
                    public void profileDataUpdated(JSONObject updates) {

                    }

                    @Override
                    public void profileDidInitialize(String CleverTapID) {
//                        Branch branch = Branch.getInstance();
                        Log.e("BRANCH SDK", "I am in");
                        Log.e("ClevertapTest", "Clevertap id is " + CleverTapID);

//                        branch.setRequestMetadata("$clevertap_attribution_id",
//                                CleverTapID);
                        CleverTapAPI.getDefaultInstance(getApplicationContext()).pushEvent("come on event");
                    }
                };

                CleverTapAPI.getDefaultInstance(getApplicationContext()).setSyncListener(listener1);


                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
                profileUpdate.put("Name", username.getText().toString());    // String
                profileUpdate.put("Identity", id.getText().toString());      // String or number
                profileUpdate.put("Tester", "hello" ); // Email address of the user
                profileUpdate.put("Phone", phone.getText().toString());   // Phone (with the country code, starting with +)
                profileUpdate.put("Gender", "M");             // Can be either M or F
                profileUpdate.put("DOB", new Date());         // Date of Birth. Set the Date object to the appropriate value first

                CleverTapAPI.getDefaultInstance(getApplicationContext()).onUserLogin(profileUpdate);

            }
        });



        buttonTwo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                inboxDidInitialize();
            }
        });
        if (clevertapDefaultInstance != null) {
            clevertapDefaultInstance.removeMultiValueForKey("MyStuff","bag");
            clevertapDefaultInstance.addMultiValueForKey("MyStuff","bag");
            //Set the Notification Inbox Listener
            clevertapDefaultInstance.setCTNotificationInboxListener(this);
            //Initialize the inbox and wait for callbacks on overridden methods
            clevertapDefaultInstance.initializeInbox();
            clevertapDefaultInstance.setCTNotificationInboxListener(this);
        }


    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        listener = new SyncListener() {
//            @Override
//            public void profileDataUpdated(JSONObject updates) {
//
//            }
//
//            @Override
//            public void profileDidInitialize(String CleverTapID) {
////                Branch branch = Branch.getInstance();
//                Log.e("BRANCH SDK", "I am in");
//                Log.e("ClevertapTest", "Clevertap id is " + CleverTapID);
//
////                branch.setRequestMetadata("$clevertap_attribution_id",
////                        CleverTapID);
//
//
//                // Branch init
////                branch.initSession(branchReferralInitListener, MainActivity.this.getIntent().getData(), MainActivity.this);
//            }
//        };
//
//        CleverTapAPI.getDefaultInstance(getApplicationContext()).setSyncListener(listener);
//
//    }

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
