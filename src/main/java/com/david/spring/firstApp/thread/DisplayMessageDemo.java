package com.david.spring.firstApp.thread;

public class DisplayMessageDemo {

    public static void main(String[] args){
        for (int i=0;i<100;i++){
            DisplayMessage message = new DisplayMessage();
            message.start();
        }
    }
}
