package com.example.androidlabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

class ChatRoomAdapter extends BaseAdapter {

        private List<Message> messages;
        private Context context;
        private LayoutInflater inflater;

        public ChatRoomAdapter(List<Message> messages, Context context) {
            this.messages = messages;
            this.context = context;
            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return messages.size();
        }

        @Override
        public Object getItem(int position) {
            return messages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null){
                if (messages.get(position).sent()){
                    view = inflater.inflate(R.layout.activity_send, null);

                }else {
                    view = inflater.inflate(R.layout.activity_receive, null);
                }
                TextView messageText = (TextView)view.findViewById(R.id.textViewMessage);
                messageText.setText(messages.get(position).message);
            }
            return view;
        }
    }