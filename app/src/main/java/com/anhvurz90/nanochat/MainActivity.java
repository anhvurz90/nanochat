package com.anhvurz90.nanochat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendBtn = (Button) findViewById(R.id.sendBtn);
        final EditText messageTxt = (EditText) findViewById((R.id.messageTxt));
        ListView messageLst = (ListView) findViewById(R.id.messageLst);

        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase("https://nanochat-9e1d4.firebaseio.com/Chat");

        sendBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              ChatMessage chat = new ChatMessage("puf", messageTxt.getText().toString());
              ref.push().setValue(chat);
              messageTxt.setText("");
          }
        });
    }
}
