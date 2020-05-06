package com.example.broadcastproj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String BROADCAST_ACTION = null ;
    TextView textView;
    Button strtBtn;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.button);
        strtBtn = findViewById(R.id.txtView);

    }

    @Override
    protected void onStart() {
        super.onStart();

        Receiver localListener = new Receiver();
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListener,intentFilter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        Receiver localListener = new Receiver();
        this.unregisterReceiver(localListener);
    }

    public void onClick(View view){

        if (view.getId() == R.id.button){
            BackgroundService.startAction(this.getApplicationContext());
        }
    }

    public class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String currentText = textView.getText().toString();
            String message = intent.getStringExtra("value");
            currentText += "\nReceived " + message;
            textView.setText(currentText);
        }
    }
}

