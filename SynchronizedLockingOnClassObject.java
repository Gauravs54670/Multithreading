
public class SynchronizedLockingOnClassObject {
    public static void main(String[] args) {
        SynchronizedClass sync = new SynchronizedClass();
        Thread t1 = new Thread(sync::method1);
        Thread t2 = new Thread(sync::method2);
        t1.start(); t2.start();
    }
}
class SynchronizedClass {
    synchronized public void method1() {
        System.out.println("Method 1 execution starts.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Method 1 execution ends.");
    }
    synchronized public void method2() {
        System.out.println("Method 2 execution starts.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Method 2 execution ends.");    }
}
