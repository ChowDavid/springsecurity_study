package com.david.spring.firstApp.thread;

import org.junit.jupiter.api.Test;

public class DisplayMessageTest {

    @Test
    public void runMutli(){
        for (int i=0;i<100;i++){
            DisplayMessage message = new DisplayMessage();
            message.start();
        }
    }
}
