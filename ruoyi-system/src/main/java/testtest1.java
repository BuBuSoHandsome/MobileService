import com.CallableTask;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class testtest1 {

    private static final Integer MAX_NUMBER =  3;

    private static Integer countStep(Integer size){
        if(size<=0){
            throw  new RuntimeException();
        }
        return (size+MAX_NUMBER -1)/MAX_NUMBER;
    }

    public static void main(String[] args) {


        List<String>  stringList = Arrays.asList("aa","bb","cc","dd","aaa","bbb","ccc","ddd","aaaa","bbbb");

        int num = countStep(stringList.size());

        List<List<String>> splitList = Stream.iterate(0, n -> n + 1).
                limit(num).parallel().map(a -> stringList.stream().skip(a * MAX_NUMBER).limit(MAX_NUMBER).parallel().collect(Collectors.toList())).collect(Collectors.toList());

        System.out.println(num);
        System.out.println(splitList.get(0));
        System.out.println(splitList.get(1));
        System.out.println(splitList.get(2));
        System.out.println(splitList.get(3));


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                num,num , 60L, TimeUnit.SECONDS,new ArrayBlockingQueue(stringList.size()));

        Callable<Integer> callable = new CallableTask(splitList.get(0));
        threadPoolExecutor.submit(callable);
        threadPoolExecutor.shutdown();
    }

}
