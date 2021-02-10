package com.example.androidlabs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {

    ListView chatList;
    EditText editText;
    List<Message> listMessage = new ArrayList<>();
    Button sendBtn;
    Button receiveBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        chatList = (ListView)findViewById(R.id.chatList);
        editText = (EditText)findViewById(R.id.editText);
        sendBtn = (Button)findViewById(R.id.sendBtn);
        receiveBtn = (Button)findViewById(R.id.receiveBtn);



        sendBtn.setOnClickListener(c -> {
            String message = editText.getText().toString();
            Message msg = new Message(message, true);
            listMessage.add(msg);
            editText.setText("");
            ChatRoomAdapter cra = new ChatRoomAdapter(listMessage, getApplicationContext());
            chatList.setAdapter(cra);
        });

        receiveBtn.setOnClickListener(c -> {
            String message = editText.getText().toString();
            Message msg = new Message(message, false);
            listMessage.add(msg);
            editText.setText("");
            ChatRoomAdapter cra = new ChatRoomAdapter(listMessage, getApplicationContext());
            chatList.setAdapter(cra);
        });

    }
}