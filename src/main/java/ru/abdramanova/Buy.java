package ru.abdramanova;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

public class Buy {
    public static void main(String[] args) {
        int consumersCount = Integer.parseInt(args[0].trim());
        List<Consumer> consumers = new ArrayList<>(consumersCount);
        Phaser phaser = new Phaser();
        for (int i = 1; i <= consumersCount; ++i){
            consumers.add(new Consumer("Consumer " + i, phaser));
        }

        for (int i = 0; i < consumersCount; ++i){
            try {
                consumers.get(i).getThread().join();
            }catch (InterruptedException e){
                System.out.println("Something went wrong");
            }
        }
    }
}
