package com.david.spring.firstApp.thread;

import java.util.concurrent.Callable;

public class CalculateFuture implements Callable<Integer> {

    private Integer x;
    private Integer y;

    CalculateFuture(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }
    @Override
    public Integer call() throws Exception {
        System.out.println(x+ " start...");
        Thread.sleep(1000);
        System.out.println(x+ " end...");
        return x+y;
    }
}
