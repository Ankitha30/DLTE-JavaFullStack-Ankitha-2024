package org.example;

import java.util.concurrent.*;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadScheduler {
    public static void main(String[] args) {
        TransactionAnalysis transactionAnalysis =  new TransactionAnalysis();
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        ScheduledFuture future =service.scheduleAtFixedRate(transactionAnalysis,2,5, TimeUnit.SECONDS);
        service.schedule(new Runnable() {
            @Override
            public void run() {
                future.cancel(true);
                service.shutdown();
            }
        }, 30,TimeUnit.SECONDS);
    }


}
/*
public static void main(String[] args) {
        CardRecords cardRecords=new CardRecords();
        ScheduledExecutorService service= Executors.newScheduledThreadPool(1);
        ScheduledFuture future = service.scheduleAtFixedRate(cardRecords,2,3, TimeUnit.SECONDS);
        service.schedule(new Runnable() {
            @Override
            public void run() {
                future.cancel(true);
                service.shutdown();
            }
        },15,TimeUnit.SECONDS);
    }
 */