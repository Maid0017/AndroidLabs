package com.example.androidlabs;

public class Message {

        public String message;
        public boolean sent;

    public Message(String message, boolean sent) {
            this.message = message;
            this.sent = sent;
        }

        public boolean sent() {
            return sent;
        }

    }
