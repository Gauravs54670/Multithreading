import java.util.concurrent.locks.ReentrantLock;

class WaitIndefinitely {
    private final ReentrantLock reentrantLock = new ReentrantLock();
    public void waiting(String threadName) {
        reentrantLock.lock();
        try {
            System.out.println(threadName+" is using the lock");
            Thread.sleep(15 * 1000);
            System.out.println(threadName+" is done waiting now another thread can wait");
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        finally {
            reentrantLock.unlock();
        }
        System.out.println("Waiting done...");
    }
}
public class ReentrantLock1 {
    public static void main(String[] args) {
        WaitIndefinitely wt = new WaitIndefinitely();
        Runnable task = () -> {
              String threadName = Thread.currentThread().getName();
              wt.waiting(threadName);
        };
        Thread t1 = new Thread(task,"Thread 1");
        Thread t2 = new Thread(task, "Thread 2");
        Thread t3 = new Thread(task, "Thread 3");
        Thread t4 = new Thread(task, "Thread 4");
        t1.start();t2.start();t3.start();t4.start();
        try {
            t1.join();t2.join();t3.join();t4.join();
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName()+" is executed");
    }
}
