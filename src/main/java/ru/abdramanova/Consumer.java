package ru.abdramanova;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;

public class Consumer implements Runnable{
    private final String name;
    private int boughtProducts;
    private final Thread thread;
    private int numBuys;
    private final Phaser phaser;

    Consumer(String name, Phaser phaser){
        this.name = name;
        this.boughtProducts = 0;
        this.numBuys = 0;
        this.phaser = phaser;
        thread = new Thread(this, this.name);
        phaser.register();
        thread.start();
    }

    @Override
    public void run() {
        Random random = new Random();
        int a;
            do {
                a = Warehouse.buyProduct(random.nextInt(9) + 1);
                if (a > 0){
                    ++numBuys;
                    boughtProducts += a;
                }
                phaser.arriveAndAwaitAdvance();
            } while (!Warehouse.IsEmpty());
            System.out.println(name + " " + boughtProducts + " " + numBuys);
            phaser.arriveAndDeregister();
    }

    public Thread getThread(){
        return thread;
    }
}
