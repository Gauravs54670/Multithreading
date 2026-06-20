import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

class SeatBooking {
    AtomicReference<String> seat = new AtomicReference<>("EMPTY");
    boolean bookSeat(String name) {
        System.out.println(Thread.currentThread().getName() + " attempting to book seat.");
        if(!Objects.equals(seat.get(), "EMPTY")) {
            System.out.println(Thread.currentThread().getName() + " Seat already booked.");
            return false;
        }
        boolean value = seat.compareAndSet("EMPTY", name);
        if(value) System.out.println(Thread.currentThread().getName() + " Seat booked successfully.");
        else System.out.println(Thread.currentThread().getName() + " Seat already booked.");
        return value;
    }
}
public class AtomicReferenceSeatBooking {
    public static void main(String[] args) {
        SeatBooking seatBooking = new SeatBooking();
        Thread t1 = new Thread(() -> {
            boolean value = seatBooking.bookSeat("AMAN");
            if(value) System.out.println("Seat booked to AMAN");
        });
        Thread t2 = new Thread(() -> {
            boolean value = seatBooking.bookSeat("RAHUL");
            if(value) System.out.println("Seat booked to RAHUL");
        });
        Thread t3 = new Thread(() -> {
            boolean value = seatBooking.bookSeat("ADITYA");
            if(value) System.out.println("Seat booked to ADITYA");
        });
        Thread t4 = new Thread(() -> {
            boolean value = seatBooking.bookSeat("ROHAN");
            if(value) System.out.println("Seat booked to ROHAN");
        });
        Thread t5 = new Thread(() -> {
            boolean value = seatBooking.bookSeat("ROSHAN");
            if(value) System.out.println("Seat booked to ROSHAN");
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
