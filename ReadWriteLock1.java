import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedResource {
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private float amount = 5000;
    public void checkBalance(String accountHolderName) {
        reentrantReadWriteLock.readLock().lock();
        System.out.println(accountHolderName+" is attempting to read the balance");
        try {
            System.out.println(accountHolderName+" is reading balance");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println(accountHolderName+" is done checking balance");
            reentrantReadWriteLock.readLock().unlock();
        }
    }
    public void updateBalance(String accountHolderName, float newAmount) {
        reentrantReadWriteLock.writeLock().lock();
        System.out.println(accountHolderName+" is attempting to update the balance");
        try {
            System.out.println(accountHolderName+" is updating the balance");
            Thread.sleep(3000);
            float temp = amount;
            amount = newAmount;
            System.out.println("Balance updated from "+temp+" to "+amount);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println(accountHolderName+" is done updating balance");
            reentrantReadWriteLock.writeLock().unlock();
        }
    }
}
public class ReadWriteLock1 {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Thread t1 = new Thread(() -> resource.checkBalance("Gaurav"));
        Thread t2 = new Thread(() -> resource.checkBalance("Shambhavi"));
        Thread t3 = new Thread(() -> resource.checkBalance("Shiva"));
        Thread t6 = new Thread(() -> resource.updateBalance("Gaurav",10000));
        Thread t7 = new Thread(() -> resource.updateBalance("Garima",45122));
        Thread t8 = new Thread(() -> resource.updateBalance("Shiva",57885));
        Thread t4 = new Thread(() -> resource.checkBalance("Garima"));
        Thread t5 = new Thread(() -> resource.checkBalance("CM"));
        t1.start();t2.start();t3.start();t4.start();t5.start();t6.start();t7.start();t8.start();
        try {
            t1.join();t2.join();t3.join();t4.join();t5.join();t6.join();t7.join();t8.join();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+" is executed");
    }
}
