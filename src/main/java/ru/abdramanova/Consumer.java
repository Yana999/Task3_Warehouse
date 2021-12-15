package ru.abdramanova;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Consumer implements Runnable{
    private final String name;
    private int boughtProducts;
    private final Thread thread;
    private int numBuys;
    private final CyclicBarrier barrier;

    Consumer(String name, CyclicBarrier barrier){
        this.name = name;
        this.boughtProducts = 0;
        this.numBuys = 0;
        this.barrier = barrier;
        thread = new Thread(this, this.name);
        thread.start();
    }

    @Override
    public void run() {
        Random random = new Random();
        int a;
        try {
            do {
                System.out.println(name);
                a = Warehouse.buyProduct(random.nextInt(9) + 1);
                boughtProducts += a;
                ++numBuys;
                barrier.await();
            } while (!Warehouse.IsEmpty());
            System.out.println(name + " " + boughtProducts + " " + numBuys);
        }catch (BrokenBarrierException | InterruptedException e){
            System.out.println("Something went wrong");
        }
    }

    public Thread getThread(){
        return thread;
    }
}
