
public class SynchronizedRaceCondition {
    public static void main(String[] args) {
        Incrementer counter = new Incrementer();
        Runnable task = () -> {
            for(int i = 1; i<=10000; i++)
                counter.increment();
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start(); t2.start();
        try {
            t1.join(); t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Synchronized count is " + Incrementer.counter);
    }
}
class Incrementer {
    public static int counter = 0;
    synchronized public void increment() {
        counter += 1;
    }
}
