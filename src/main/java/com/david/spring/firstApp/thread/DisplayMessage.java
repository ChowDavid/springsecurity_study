package com.david.spring.firstApp.thread;

import java.time.LocalDateTime;

public class DisplayMessage extends Thread{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Running..."+ LocalDateTime.now());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" Ended..."+ LocalDateTime.now());
    }
}
