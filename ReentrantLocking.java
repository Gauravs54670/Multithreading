import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Locking {
    Lock lock = new ReentrantLock();
    void printMethod() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " " +
                    "entered in the critical section.");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " " +
                    "leaves the critical section.");
        }
        finally {
            lock.unlock();
        }
    }
}

public class ReentrantLocking {
    public static void main(String[] args) {
        Locking locking = new Locking();
        Runnable task = locking::printMethod;
        Thread t1 = new Thread(task,"Thread A");
        Thread t2 = new Thread(task,"Thread B");
        Thread t3 = new Thread(task,"Thread C");
        Thread t4 = new Thread(task,"Thread D");
        t1.start(); t2.start(); t3.start(); t4.start();
    }
}
