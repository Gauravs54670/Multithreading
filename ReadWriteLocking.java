import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Transaction {
    private final ReentrantReadWriteLock readWriteLocking = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLocking.readLock();
    private final Lock writeLock = readWriteLocking.writeLock();
    private float balance = 5000;

    void checkBalance() {
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " attempting to check balance.");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "Done checking balance. " +
                    "Balance is " + balance);
        } finally {
            readLock.unlock();
        }
    }

    void updateBalance(float amount) {
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + " account balance is " + balance);
            System.out.println(Thread.currentThread().getName() + " attempting to update the balance.");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            float oldBalance = balance;
            balance = amount;
            System.out.println(Thread.currentThread().getName() + " previous balance is " + oldBalance);
            System.out.println(Thread.currentThread().getName() + " new balance is " + balance);
        } finally {
            writeLock.unlock();
        }
    }
}

public class ReadWriteLocking {
    public static void main(String[] args) {
        Transaction transaction = new Transaction();
        Thread r1 = new Thread(transaction::checkBalance, "Thread-1 checks balance.");
        Thread r2 = new Thread(transaction::checkBalance, "Thread-2 checks balance.");
        Thread r3 = new Thread(transaction::checkBalance, "Thread-3 checks balance.");
        Thread r4 = new Thread(transaction::checkBalance, "Thread-4 checks balance.");
        Thread r5 = new Thread(transaction::checkBalance, "Thread-5 checks balance.");

        Thread w1 = new Thread(() -> transaction.updateBalance(6000),"Thread-6 updates balance.");
        Thread w2 = new Thread(() -> transaction.updateBalance(7000),"Thread-7 updates balance.");
        Thread w3 = new Thread(() -> transaction.updateBalance(8000),"Thread-8 updates balance.");
        Thread w4 = new Thread(() -> transaction.updateBalance(9000),"Thread-9 updates balance.");
        Thread w5 = new Thread(() -> transaction.updateBalance(10000),"Thread-10 updates balance.");

        r1.start(); r2.start(); r3.start(); r4.start(); r5.start();
        w1.start(); w2.start(); w3.start(); w4.start(); w5.start();
    }
}
