package com.example.broadcastproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    public static String BROADCAST_ACTION="com.example.broadcastproj.BROADCAST_MESSAGE";
    TextView textView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    Button button;
   private Reciever localListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        textView1=findViewById(R.id.textView2);
        textView2=findViewById(R.id.textView3);
        textView3=findViewById(R.id.textView4);
        button=findViewById(R.id.button);

    }

    @Override
    protected void onStart() {
        super.onStart();
         localListener= new Reciever();
        IntentFilter intentFilter=new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListener,intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(localListener);
    }

    public void onClick(View view){
        if (view.getId()==R.id.button){
            BackgroundServices.startAction(this.getApplicationContext());
        }
    }

    public class Reciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String currentText=textView.getText().toString();
            String message = intent.getStringExtra("value");
            currentText +="\nRecieved "+message;
            textView.setText(currentText);
        }
    }
}
