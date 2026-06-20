import java.util.concurrent.locks.StampedLock;

class StampedReadWrite {
    Integer value = 0;
    StampedLock stampedLock = new StampedLock();
    public void readValue() {
        long stamp = stampedLock.tryOptimisticRead();
        int currentValue = value;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(!stampedLock.validate(stamp)) {
            System.out.println(Thread.currentThread().getName() + " optimistic read validation failed. Falling back to pessimistic read lock.");
            // fall-over logic
            // moving to pessimistic locking
            stamp = stampedLock.readLock();
            try {
                currentValue = value;
            }
            finally {
                stampedLock.unlock(stamp);
            }
        }
        System.out.println(Thread.currentThread().getName() +" " +
                    "Reads the value as " + currentValue);
    }
    public void write(int value) {
        long stamp = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + " acquired write lock.");
        try {
            this.value = value;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        finally {
            System.out.println(Thread.currentThread().getName() + " releasing write lock. Value set to " + value);
            stampedLock.unlock(stamp);
        }
    }
}
public class StampedLocking {
    public static void main(String[] args) {
        StampedReadWrite stampedReadWrite = new StampedReadWrite();
        Thread t1 = new Thread(stampedReadWrite::readValue);
        Thread t2 = new Thread(stampedReadWrite::readValue);
        Thread t3 = new Thread(stampedReadWrite::readValue);
        Thread t4 = new Thread(stampedReadWrite::readValue);
        Thread t5 = new Thread(stampedReadWrite::readValue);
        Thread t6 = new Thread(stampedReadWrite::readValue);
        Thread t7 = new Thread(stampedReadWrite::readValue);
        Thread t8 = new Thread(stampedReadWrite::readValue);
        Thread t9 = new Thread(stampedReadWrite::readValue);

        Thread tA = new Thread(() -> stampedReadWrite.write(10));
        Thread tB = new Thread(() -> stampedReadWrite.write(20));
        Thread tC = new Thread(() -> stampedReadWrite.write(30));
        Thread tD = new Thread(() -> stampedReadWrite.write(40));
        Thread tE = new Thread(() -> stampedReadWrite.write(50));

        t1.start(); t2.start(); t3.start(); t4.start();
        t5.start(); t6.start(); t7.start(); t8.start(); t9.start();
        tA.start(); tB.start(); tC.start(); tD.start(); tE.start();
    }
}


