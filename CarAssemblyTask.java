import java.util.concurrent.locks.ReentrantLock;

class Demo implements Runnable {
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " is waiting to access the assembly line...");
        
        lock.lock(); // Mechanic acquires the lock
        try {
            System.out.println(">>> " + threadName + " ACQUIRES the lock and begins assembling the car.");
            
            System.out.println(threadName + ": Engine Assembles.");
            Thread.sleep(3500); 
            
            System.out.println(threadName + ": Structure Assembles.");
            Thread.sleep(3500);
            
            System.out.println(threadName + ": Car check.");
            Thread.sleep(3500);
            
            System.out.println(threadName + ": Car Reviewed.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("<<< " + threadName + " RELEASES the lock.");
            lock.unlock(); // Mechanic releases the lock
        }
    }
}

public class CarAssemblyTask {
    public static void main(String[] args) {
        Demo task = new Demo();
        
        // Creating two threads (mechanics) sharing the same task (blueprint)
        Thread mechanic1 = new Thread(task, "Mechanic-1");
        Thread mechanic2 = new Thread(task, "Mechanic-2");
        
        mechanic1.start();
        mechanic2.start();
    }
}
