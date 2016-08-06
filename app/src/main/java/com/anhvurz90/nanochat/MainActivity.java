package com.anhvurz90.nanochat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;

import java.util.ArrayList;
import java.util.List;

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

        Query recent = ref.limitToLast(3);
        FirebaseListAdapter<ChatMessage> adapter = new FirebaseListAdapter<ChatMessage>(
                this, ChatMessage.class, android.R.layout.two_line_list_item, recent
        ) {
            @Override
            protected void populateView(View view, ChatMessage message, int position) {
                ((TextView) view.findViewById(android.R.id.text1)).setText(message.getName());
                ((TextView) view.findViewById(android.R.id.text2)).setText(message.getMessage());
            }
        };

        messageLst.setAdapter(adapter);

    }
}
