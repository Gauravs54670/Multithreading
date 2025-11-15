import java.util.concurrent.locks.ReentrantLock;

class TryLockWaiting{
    private final ReentrantLock reentrantLock = new ReentrantLock();
    public void tryLockWaiting(String threadName) {
        if(reentrantLock.tryLock()) {
            System.out.println("Waiting starts");
            try {
                System.out.println(threadName+" start waiting");
                Thread.sleep(15*1000);
                System.out.println("Waiting Done");
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            finally {
                reentrantLock.unlock();
            }
        }
        else System.out.println("Locked is acquired "+threadName+" is terminating itself");
    }
}
public class ReentrantLock2 {
    public static void main(String[] args) {
        TryLockWaiting tryLock = new TryLockWaiting();
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            tryLock.tryLockWaiting(threadName);
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
