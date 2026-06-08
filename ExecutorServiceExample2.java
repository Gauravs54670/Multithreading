import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class SharedFactorialResource2{
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
public class ExecutorServiceExample2 {
    public static void main(String[] args) {
        long startingTime = System.currentTimeMillis();
        SharedFactorialResource2 resource = new SharedFactorialResource2();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<BigInteger>> callableFactorialList = new ArrayList<>();
        List<Callable<Integer>> callalbeSumList = new ArrayList<>();
        for(int i = 1; i<=20; i++) {
            int number = i;
            callableFactorialList.add(() -> resource.calculateFactorial(number));
            callalbeSumList.add(() -> resource.calculateSum(number));
        }
        try {
            List<Future<BigInteger>> factorialList = executorService.invokeAll(callableFactorialList);
            int n = 1;
            for(Future<BigInteger> future : factorialList) {
                System.out.println("Factorial of " + (n++) +" is " + future.get());
            }
            List<Future<Integer>> sumList = executorService.invokeAll(callalbeSumList);
            n = 1;
            for(Future<Integer> future : sumList) {
                System.out.println("Sum of " + (n++) + " is " + future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
        long endingTime = System.currentTimeMillis();
        System.out.println("Total time taken is " + (endingTime-startingTime)/1000+" seconds");
    }
}
