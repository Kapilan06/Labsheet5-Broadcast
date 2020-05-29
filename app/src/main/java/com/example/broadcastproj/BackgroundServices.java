package com.example.broadcastproj;

import androidx.annotation.Nullable;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

public class BackgroundServices extends IntentService {


    public BackgroundServices() {
        super("BackgroundServices");
    }

    public static void startAction(Context context){
        Intent intent=new Intent(context, BackgroundServices.class);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent !=null){

            for (int i=0; i<5; i++){
                Intent localBroadcastIntent = new Intent(MainActivity.BROADCAST_ACTION);
                localBroadcastIntent.putExtra("value","Broadcast "+ (i+1));
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                sendBroadcast(localBroadcastIntent);
            }
        }
    }
}
