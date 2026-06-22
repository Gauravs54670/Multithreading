import java.util.concurrent.atomic.AtomicReferenceArray;

class BookMultipleSeats {
//    an atomic reference array with 5 seats capacity
    private final AtomicReferenceArray<String> seatBookingAtomicReferenceArray =
        new AtomicReferenceArray<>(5);
    public BookMultipleSeats() {
        for(int i = 0; i<seatBookingAtomicReferenceArray.length(); i++)
            seatBookingAtomicReferenceArray.set(i, "EMPTY");
    }
    public boolean bookMultipleSeats(int seatNumber, String customerName) {
        if(seatNumber < 0 || seatNumber > seatBookingAtomicReferenceArray.length()) {
            System.out.println("Invalid Seat Number.");
            return false;
        }
//        perform Atomic Compare and Set operation on specific seat number (index) of Atomic Reference Array
        boolean result = seatBookingAtomicReferenceArray.compareAndSet(
                seatNumber, "EMPTY", customerName);
        if(result) System.out.println("Seat acquired by " + customerName +" at seat number " + seatNumber);
        else {
            String currentSeatOwner = seatBookingAtomicReferenceArray.get(seatNumber);
            System.out.println("Failed to acquire seat. Seat is acquired by " + currentSeatOwner);
        }
        return result;
    }
    public void printSeatBookingStatus() {
        System.out.println("Current Seat Booking Status : ");
        for(int i = 0; i<seatBookingAtomicReferenceArray.length(); i++) {
            System.out.println("Seat " + i + " : " + seatBookingAtomicReferenceArray.get(i));
        }
    }
}
public class AtomicReferenceArrayClass {
    public static void main(String[] args) {
        BookMultipleSeats bookMultipleSeats = new BookMultipleSeats();
        Thread t1 = new Thread(() -> {
            bookMultipleSeats.bookMultipleSeats(0, "AMAN");
        });
        Thread t2 = new Thread(() -> {
            bookMultipleSeats.bookMultipleSeats(1, "RAHUL");
        });
        Thread t3 = new Thread(() -> {
            bookMultipleSeats.bookMultipleSeats(2, "ADITYA");
        });
        Thread t4 = new Thread(() -> {
            bookMultipleSeats.bookMultipleSeats(3, "ROHAN");
        });
        Thread t5 = new Thread(() -> {
            bookMultipleSeats.bookMultipleSeats(4, "ROSHAN");
        });
        Thread t6 = new Thread(() -> {
            bookMultipleSeats.bookMultipleSeats(0, "RAHUL JR.");
        });
        Thread t7 = new Thread(() -> {
            bookMultipleSeats.bookMultipleSeats(1, "ADITYA JR.");
        });
        Thread t8 = new Thread(() -> {
            bookMultipleSeats.bookMultipleSeats(2, "ROHAN JR.");
        });
        Thread t9 = new Thread(() -> {
            bookMultipleSeats.bookMultipleSeats(3, "ROSHAN JR.");
        });
        Thread t10 = new Thread(() -> {
            bookMultipleSeats.bookMultipleSeats(4, "ROSHAN KR");
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
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bookMultipleSeats.printSeatBookingStatus();
    }
}
