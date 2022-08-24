package com.david.spring.firstApp.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future> all = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i=0;i<100;i++){
            Future<Integer> future = executor.submit(new CalculateFuture(i,i));
            all.add(future);
        }
        for (int i=0;i<100;i++){
            System.out.println(all.get(i).get());
        }
    }
}
