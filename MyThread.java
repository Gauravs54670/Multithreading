class Demo extends Thread {
    @Override
    public void run() {
        for(int i = 0; i<5; i++) {
            System.out.println("count is " + (i+1));
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class MyThread {
    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.start();
    }
}