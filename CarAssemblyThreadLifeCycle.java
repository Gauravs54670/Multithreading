class CarAssembly implements Runnable{
    @Override
    public void run() {
        String worker = Thread.currentThread().getName();
        System.out.println(worker + " is in " + Thread.currentThread().getState() + " state");
        System.out.println(worker + " start assembling the car...");
        try{
            Thread.sleep(5000); // worker doing the car work. in TIMED_WAITING state
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(worker + " done assembling the car.");
        System.out.println(worker + " is in " + Thread.currentThread().getState() + " state");
    }
}
public class CarAssemblyThreadLifeCycle {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread(); 
        System.out.println("Name of the current thread is: " + mainThread.getName()); // main thread
        System.out.println("Current state of main thread is: " + mainThread.getState()); // main thread state
        CarAssembly task = new CarAssembly(); // creating a task blueprint
        Thread worker1 = new Thread(task, "Worker1"); // a new thread unit for execution of the blueprint (task)
        Thread worker2 = new Thread(task, "Worker2"); // another new thread unit for execution of the same blueprint (task)
        System.out.println("State of worker1 thread: " + worker1.getState()); //Worker 1 thread state -> NEW
        System.out.println("State of worker2 thread: " + worker2.getState()); //Worker 2 thread state -> NEW
        worker1.start(); // starting the worker 1 (thread) for execution
        try{
            Thread.sleep(2000); // Pausing the main thread for 2 seconds.
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        worker2.start(); // starting the worker 2 (thread) for execution
        System.out.println("State of worker1 thread after start: " + worker1.getState()); //Worker 1 thread state -> RUNNABLE
        System.out.println("State of worker2 thread after start: " + worker2.getState()); //Worker 2 thread state -> RUNNABLE
        System.out.println("State of worker1 thread after sleep: " + worker1.getState()); 
        System.out.println("State of worker2 thread after sleep: " + worker2.getState());
    }
}
