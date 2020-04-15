package com.fumiao.assistant.event;

public class LoginEvent {
    private int mMsg;
    public LoginEvent(int msg) {
        mMsg = msg;
    }
    public int getMsg(){
        return mMsg;
    }
}
