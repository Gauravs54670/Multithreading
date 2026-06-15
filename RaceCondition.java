class Counter {
    public int counter = 0;
    public void increment() {
        counter++;
    }
}
public class RaceCondition {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Runnable task = () -> {
            for(int i = 1; i<=100000; i++){
                counter.increment();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println(counter.counter);
    }
}
