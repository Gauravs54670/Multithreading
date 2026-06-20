import java.util.concurrent.atomic.AtomicInteger;

class AtomicCounter {
//    int count = 0;
    AtomicInteger atomicInteger = new AtomicInteger(0);
    public void increment() {
//        count++;
        atomicInteger.incrementAndGet();
    }
}
public class AtomicIntegerCounter {
    public static void main(String[] args) {
        AtomicCounter atomicCounter = new AtomicCounter();
        Runnable task = () -> {
            for(int i = 1; i<=10000; i++) atomicCounter.increment();
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start(); t2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(atomicCounter.atomicInteger);
    }
}
