public class ThreadMethods {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main Thread execution starts...");
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " started execution");
            try {
                Thread.sleep(5000);
            }
            catch(Exception e) {
                e.printStackTrace();
                
            }
            System.out.println(threadName + " completed execution");
        };
        Thread t1 = new Thread(task, "Execution 1");
        Thread t2 = new Thread(task, "Execution 2");
        t1.start();
        t1.join(2000);
        t2.start();
        System.out.println("Main thread execution completed.");
    }
}