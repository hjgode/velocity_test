package com.wavelink.velocity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    myReceiver receiver;
    TextView txtLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLog=(TextView)findViewById(R.id.txtLog);
        txtLog.setMovementMethod(new ScrollingMovementMethod());

        receiver=new myReceiver();
        registerReceiver(receiver, new IntentFilter(getResources().getString(R.string.actionBARCODE)));
    }
    public class myReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            if(intent.getAction()== context.getResources().getString(R.string.actionBARCODE)){
                if (extras != null) {
                    String data = extras.getString(context.getResources().getString(R.string.datawedge_intent_key_data));
                    addLog("data=" + data);
                }
            }
        }

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    void addLog(final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("IntentReceiverTest", msg);
                String old=txtLog.getText().toString();
                old+="\n"+msg;
                txtLog.setText(old);
            }
        });
    }

}
