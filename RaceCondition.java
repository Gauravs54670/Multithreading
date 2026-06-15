public class RaceCondition {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Runnable task = () -> {
            for(int i = 1; i<=1000; i++)
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
        System.out.println("Count of counter is " + Counter.counter);
    }
}
class Counter {
    public static int counter = 0;
    public void increment() {
        counter += 1;
    }
}