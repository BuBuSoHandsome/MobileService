package com;

import java.util.List;
import java.util.concurrent.Callable;

public class CallableTask implements Callable<Integer> {

    private List<String> stringList;

    public CallableTask(List<String> stringList){
        this.stringList =stringList;
    }

    @Override
    public Integer call() throws Exception {
        Integer num = 0;
        for(int i=0;i<stringList.size();i++){
            num++;
        }
        return num;
    }


}
