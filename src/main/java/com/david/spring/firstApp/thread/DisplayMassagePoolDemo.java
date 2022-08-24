package com.david.spring.firstApp.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DisplayMassagePoolDemo {
    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i=0;i<100;i++){
            DisplayMessage message = new DisplayMessage();
            executor.execute(message);
        }
        executor.shutdown();
    }
}
