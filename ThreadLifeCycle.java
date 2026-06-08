
public class ThreadLifeCycle {
    
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread t1 = new Thread(() -> {
            System.out.println("Name of the current thread is: " + Thread.currentThread().getName());
            System.out.println("Current state of main thread is: " + mainThread.getState()); // TimedWaiting, Running, Waiting
        });
        System.out.println("State of t1 thread: " + t1.getState()); //New
        t1.start();
        System.out.println("State of t1 thread after start: " + t1.getState()); //Runnable
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("State of t1 thread after sleep: " + t1.getState()); //Terminated
    }
}
