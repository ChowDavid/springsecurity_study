package com.david.spring.firstApp.thread;

public class DisplayMassageRunnableDemo {

    public static void main(String[] args){
        for (int i=0;i<100;i++){
            Thread thread = new Thread(new DisplayMessageRunnable());
            thread.start();
        }
    }
}
