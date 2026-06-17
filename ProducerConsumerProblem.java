
class Box {
    Integer item;
    boolean flag = false;
    synchronized void produce(int value) {
        while(flag) {
            try {
                wait();
            }
            catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        item = value;
        flag = true;
        System.out.println("Producer produces the value " + item);
        notify();
    }
    synchronized void consumer() {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Consumer consumes the value " + item);
        flag = false;
        item = null;
        notify();
    }
}
public class ProducerConsumerProblem {
    public static void main(String[] args) {
        Box box = new Box();
        Thread t1 = new Thread(() -> {
            for (int i = 1; i<=20; i++) {
                try{
                    Thread.sleep(800);
                }
            catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
            }
                box.produce(i);
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i = 1; i<=20; i++) {
                try{
                    Thread.sleep(800);
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                box.consumer();
            }
        });
        t1.start(); t2.start();
    }
}
