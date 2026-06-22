import java.util.concurrent.atomic.AtomicInteger;

class LikeCounter {
    private final AtomicInteger likesCount = new AtomicInteger(0);
    public void incrementLike() {
        int currentLikeCount;
        int newLikeCount;
        while(true) {
//            get the current count of the like atomic variable
            currentLikeCount = likesCount.get();
//            increment the count of the current count of atomic variable
            newLikeCount = currentLikeCount + 1;
//            assigning the new like count to the variable
            if(likesCount.compareAndSet(currentLikeCount, newLikeCount))
                return;
            System.out.println("Conflict detected. Re-Trying...");
        }
    }
    public int getLikesCount() { return likesCount.get(); }
    
}
public class PostLikeCounter {
    public static void main(String[] args) {
        LikeCounter counter = new LikeCounter();
        Thread t1 = new Thread(() -> {
            for(int i = 0; i<10; i++)
                counter.incrementLike();
        });
        Thread t2 = new Thread(() -> {
            for(int i = 0; i<10; i++)
                counter.incrementLike();
        });
        Thread t3 = new Thread(() -> {
            for(int i = 0; i<10; i++)
                counter.incrementLike();
        });
        Thread t4 = new Thread(() -> {
            for(int i = 0; i<10; i++)
                counter.incrementLike();
        });
        Thread t5 = new Thread(() -> {
            for(int i = 0; i<10; i++)
                counter.incrementLike();
        });
        Thread t6 = new Thread(() -> {
            for(int i = 0; i<10; i++)
                counter.incrementLike();
        });
        Thread t7 = new Thread(() -> {
            for(int i = 0; i<10; i++)
                counter.incrementLike();
        });
        Thread t8 = new Thread(() -> {
            for(int i = 0; i<10; i++)
                counter.incrementLike();
        });
        Thread t9 = new Thread(() -> {
            for(int i = 0; i<10; i++)
                counter.incrementLike();
        });
        Thread t10 = new Thread(() -> {
            for(int i = 0; i<10; i++)
                counter.incrementLike();
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        try {
            Thread.sleep(3000);
        }
        catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Total likes : "+counter.getLikesCount());
    }
}
