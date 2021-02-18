package com.example.androidlabs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {

    ListView chatList;
    EditText editText;
    List<Message> messageList = new ArrayList<>();
    Button sendBtn;
    Button receiveBtn;
    MyOpener db;
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        chatList = (ListView)findViewById(R.id.chatList);
        editText = (EditText)findViewById(R.id.editMessage);
        sendBtn = (Button)findViewById(R.id.sendBtn);
        receiveBtn = (Button)findViewById(R.id.receiveBtn);
        db = new MyOpener(this);

        sendBtn.setOnClickListener(c -> {
            String message = String.valueOf(editText.getText());
            db.addMessage(message, false);
            editText.setText("");
            messageList.clear();
            viewMessage();
        });

        receiveBtn.setOnClickListener(c -> {
            String message = String.valueOf(editText.getText());
            db.addMessage(message, true);
            editText.setText("");
            messageList.clear();
            viewMessage();
        });

    }
    private void viewMessage(){
        Cursor c = db.printCursor();
        c.getCount();
            while (c.moveToNext()){
                Message message = new Message(c.getString(1), c.getInt(2) == 0);
                messageList.add(message);
                ChatRoomAdapter cra = new ChatRoomAdapter(messageList, getApplicationContext());
                chatList.setAdapter(cra);
            }
    }

}