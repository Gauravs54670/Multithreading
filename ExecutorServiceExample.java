import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class SharedFactorialResource {
    public BigInteger calculateFactorial(int number) {
        try {
            Thread.sleep(1200);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        BigInteger result = BigInteger.ONE;
        for(int i = number; i>=1; i--) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
public class ExecutorServiceExample{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startingTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<BigInteger>> factorialValues = new ArrayList<>();
        SharedFactorialResource resource = new SharedFactorialResource();
        for(int i = 1; i<=20; i++) {
            int number = i;
            Future<BigInteger> bigIntegerFuture = executorService.submit(
                    () -> resource.calculateFactorial(number));
            factorialValues.add(bigIntegerFuture);
        }
        for(int i = 0; i<factorialValues.size(); i++)
            System.out.println("Factorial of " + (i+1) +" is " + factorialValues.get(i).get());
        BigInteger sum = BigInteger.ZERO;
        for(Future<BigInteger> values : factorialValues) {
            sum = sum.add(values.get());
        }
        System.out.println("Total sum of all factorial values are " + sum);
        executorService.shutdown();
        long currentTime = System.currentTimeMillis();
        System.out.println("Total time taken is " + (currentTime-startingTime)/1000+" seconds");
    }

}
