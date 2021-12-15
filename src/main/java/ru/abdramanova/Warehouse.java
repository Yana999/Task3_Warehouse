package ru.abdramanova;

public class Warehouse {
    private volatile static int productCount = 1000;
    private volatile static boolean empty = false;

    public synchronized static int buyProduct(int numProducts){
        if(numProducts < productCount){
            productCount -= numProducts;
            return numProducts;
        }else if(numProducts != 0){
            numProducts = productCount;
            productCount = 0;
            empty = true;
            return numProducts;
        }else {

            return 0;
        }
    }

    public static boolean IsEmpty(){
        return empty;
    }

}
