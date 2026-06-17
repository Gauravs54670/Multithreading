
public class CustomLocking {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Thread t1 = new Thread(bank::deposit);
        Thread t2 = new Thread(bank::withdraw);
        t1.start(); t2.start();
    }
}
class Bank {
    final Object lock1 = new Object();
    final Object lock2 = new Object();
    public void deposit() {
        synchronized (lock1) {
            System.out.println("Deposit of money starts.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Deposit of money ends.");
        }
    }
    public void withdraw() {
        synchronized (lock2) {
            System.out.println("Withdraw of money starts.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Withdraw of money ends.");
        }
    }
}
