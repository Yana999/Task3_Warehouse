package ru.abdramanova;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Buy {
    public static void main(String[] args) {
        int consumersCount = Integer.parseInt(args[0].trim());
        List<Consumer> consumers = new ArrayList<>(consumersCount);
        CyclicBarrier barrier = new CyclicBarrier(consumersCount);
        for (int i = 1; i <= consumersCount; ++i){
            consumers.add(new Consumer("Consumer " + i, barrier));
        }

        for (int i = 0; i < consumersCount; ++i){
            try {
                consumers.get(i).getThread().join();
            }catch (InterruptedException e){
                System.out.println("Выполнение одного из потоков неожиданно завершилось");
            }
        }
    }
}
