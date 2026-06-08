import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
    public int calculateSum(int number) {
        try {
            Thread.sleep(1200);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        int answer = 0;
        for(int i = number; i>0; i--) {
            answer += i;
        }
        return answer;
    }
}
public class ExecutorServiceExample{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startingTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
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
        List<Future<Integer>> futureSum = new ArrayList<>();
        for(int i = 1; i<=20; i++) {
            int number = i;
            Future<Integer> future = executorService.submit(
                    () -> resource.calculateSum(number));
            futureSum.add(future);
        }
        for(int i = 0; i<futureSum.size(); i++) {
            System.out.println("Sum of " + (i+1)+" is " + futureSum.get(i).get());
        }
        int sum2 = 0;
        for(Future<Integer> a : futureSum) {
            sum2 += a.get();
        }
        executorService.shutdown();
        try {
            boolean isAllTaskDone = executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
            while (!isAllTaskDone) {
                System.out.println("Tasks are not done yet");
                isAllTaskDone = executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        long currentTime = System.currentTimeMillis();
        System.out.println("Total time taken is " + (currentTime-startingTime)/1000+" seconds");
    }

}
