import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

/**
 * Created by Ritesh on 08-04-2017.
 */
public class TidyNumber {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc =  new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = sc.nextInt();

        Long[] numberList = new Long[T];
        for (int i = 0; i< T; i++)
            numberList[i]=sc.nextLong();

        for (int i = 0; i<T; i++)
            printLastTidy(i+1, numberList[i]);

    }

    private static void printLastTidy(int caseNo, long number){
        int len = 0;
        long d1, d2;
        Stack<Long> finalNumber = new Stack<>();

        while (number > 0){
            len++;
            d1 = number % 10;
            number /= 10;
            if (finalNumber.empty())
                finalNumber.push(d1);

            else{
                d2 = finalNumber.peek();
                if (d2 < d1) {
                    d1 -= 1;
                    finalNumber.replaceAll(new UnaryOperator<Long>() {
                        @Override
                        public Long apply(Long aLong) {
                            return 9l;
                        }
                    });
                    finalNumber.push(d1);
                }else {
                    finalNumber.push(d1);
                }
            }
        }
        long  total=0, i=1, num;
        while (!finalNumber.empty()){
            num = (long) (finalNumber.pop() * Math.pow(10, len - i));
            total += num;
           i += 1;
        }
        System.out.println("Case #" + caseNo + ": " +  total);
    }
}
