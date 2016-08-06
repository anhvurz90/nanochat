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

        final List<ChatMessage> messages = new ArrayList<>();
        final ArrayAdapter<ChatMessage> adapter = new ArrayAdapter<ChatMessage>(
                this, android.R.layout.two_line_list_item, messages) {
            @Override
            public View getView(int position, View view, ViewGroup parent) {
                if (view == null) {
                    view = getLayoutInflater().inflate(android.R.layout.two_line_list_item, parent, false);
                }

                ChatMessage message = messages.get(position);
                ((TextView) view.findViewById(android.R.id.text1)).setText(message.getName());
                ((TextView) view.findViewById(android.R.id.text2)).setText(message.getMessage());

                return view;
            }
        };

        messageLst.setAdapter(adapter);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatMessage msg = dataSnapshot.getValue(ChatMessage.class);
                messages.add(msg);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
