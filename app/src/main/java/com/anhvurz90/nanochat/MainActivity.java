package com.anhvurz90.nanochat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendBtn = (Button) findViewById(R.id.sendBtn);
        EditText messageTxt = (EditText) findViewById((R.id.messageTxt));
        ListView messageLst = (ListView) findViewById(R.id.messageLst);
    }
}
