public class MyThread extends Thread{
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

    public static void main(String[] args) {
        MyThread demo = new MyThread();
        demo.start();
        System.out.println("Hello World");
    }
}
