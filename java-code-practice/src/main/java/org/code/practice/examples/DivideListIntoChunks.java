package org.code.practice.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DivideListIntoChunks {

    public static void main(String[] args){

        List<Integer> intList = Arrays.asList(1,2,3,4,5,6,7,8);

        int chunkSize = 2;
        List<List<Integer>> outList = new ArrayList<>();

        //Solution 1 : using for loop

       /* int counter = 0;
        List<Integer> chunk = new ArrayList<>();
        for(Integer i : intList){
            chunk.add(i);
            counter++;
            if(counter == chunkSize){
                outList.add(chunk);
                counter = 0;
                chunk = new ArrayList<>();
            }
        }
        */


        //Solution 2 : using Java Stream API

        AtomicInteger counter = new AtomicInteger();
        intList.stream()
                .collect(Collectors.groupingBy(x -> counter.getAndIncrement() / chunkSize))
                .values().forEach(outList::add);

        System.out.println(outList);

    }
}
