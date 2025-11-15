import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class TryAndWaitLocking {
    private final ReentrantLock reentrantLock = new ReentrantLock(true);
    protected void waiting(String threadName) {
        try {
            System.out.println(threadName+" is trying to acquire the lock");
            if(reentrantLock.tryLock(8,TimeUnit.SECONDS)) {
                System.out.println("Lock is acquired by " + threadName);
                Thread.sleep(4 * 1000);
                System.out.println(threadName+" is done waiting");
            }
            else System.out.println(threadName+" failed to acquire the lock");
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        finally {
            reentrantLock.unlock();
        }
    }
}
public class ReentrantLock3 {
    public static void main(String[] args) {
        TryAndWaitLocking wt = new TryAndWaitLocking();
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            wt.waiting(threadName);
        };
        Thread t1 = new Thread(task,"Thread 1");
        Thread t2 = new Thread(task, "Thread 2");
        Thread t3 = new Thread(task, "Thread 3");
        Thread t4 = new Thread(task, "Thread 4");
        Thread t5 = new Thread(task,"Thread 5");
        t1.start();t2.start();t3.start();t4.start();t5.start();
        try {
            t1.join();t2.join();t3.join();t4.join();t5.join();
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName()+" is executed");
    }
}
