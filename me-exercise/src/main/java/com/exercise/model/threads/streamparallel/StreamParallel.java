package com.exercise.model.threads.streamparallel;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
class ListVO{
    List<Integer> list1;
    List<Integer> list2;
}
/**
 *  结果不准确，与加载顺序有一定关系
 */
public class StreamParallel {

    public static ListVO list(){
        ListVO listVO=new ListVO();
        List<Integer> numList=new ArrayList<>();
        int num=1000;
        for(int i=1;i<=num;i++){
            numList.add(i);
        }

        List<Integer> numList2=new ArrayList<>();
        for(int i=0;i<=num;i++){
            numList2.add(i);
        }
        listVO.setList1(numList);
        listVO.setList2(numList2);
        return listVO;
    }
    public static void main(String[] args) {


        System.out.println("==求和测试====================================");
        //求和顺序流
        ListVO listVO = StreamParallel.list();
        long startTime = System.nanoTime();
        long startCurrentTimeMillis = System.currentTimeMillis();
        Integer sum = listVO.list1.stream().reduce(0, Integer::sum);
        long endTime = System.nanoTime();
        long endCurrentTimeMillis = System.currentTimeMillis();
        System.out.println("求和顺序流"+"=>"+"sum="+sum+";"+"nTime:"+(endTime-startTime));
        System.out.println("求和顺序流"+"=>"+"sum="+sum+";"+"mTime:"+(endCurrentTimeMillis-startCurrentTimeMillis));

        //求和并行流
        ListVO listVO2 = StreamParallel.list();
        long startTime2 = System.nanoTime();
        long startCurrentTimeMillis2 = System.currentTimeMillis();
        Integer sum2 = listVO2.list1.stream().parallel().reduce(0, Integer::sum);
        long endTime2 = System.nanoTime();
        long endCurrentTimeMillis2 = System.currentTimeMillis();
        System.out.println("求和并行流"+"=>"+"sum="+sum2+";"+"nTime:"+(endTime2-startTime2));
        System.out.println("求和并行流"+"=>"+"sum="+sum2+";"+"mTime:"+(endCurrentTimeMillis2-startCurrentTimeMillis2));

        //求和循环
        ListVO listVO3 = StreamParallel.list();
        long startTime3 = System.nanoTime();
        long startCurrentTimeMillis3 = System.currentTimeMillis();
        Integer sum3 = 0;
        for(Integer n:listVO3.list1){
            sum3+=n;
        }
        long endTime3 = System.nanoTime();
        long endCurrentTimeMillis3 = System.currentTimeMillis();
        System.out.println("求和循环一"+"=>"+"sum="+sum3+";"+"nTime:"+(endTime3-startTime3));
        System.out.println("求和循环一"+"=>"+"sum="+sum3+";"+"mTime:"+(endCurrentTimeMillis3-startCurrentTimeMillis3));

        //求和循环
        ListVO listVO4 = StreamParallel.list();
        long startTime4 = System.nanoTime();
        long startCurrentTimeMillis4 = System.currentTimeMillis();
        Integer sum4 = 0;
        for(int i=0;i<listVO4.list1.size();i++){
            sum4+=listVO4.list1.get(i);
        }
        long endTime4 = System.nanoTime();
        long endCurrentTimeMillis4 = System.currentTimeMillis();
        System.out.println("求和循环二"+"=>"+"sum="+sum4+";"+"nTime:"+(endTime4-startTime4));
        System.out.println("求和循环二"+"=>"+"sum="+sum4+";"+"mTime:"+(endCurrentTimeMillis4-startCurrentTimeMillis4));

        System.out.println("==组合测试====================================");
        Integer num=1000;
        //组合顺序流
        ListVO listVO11 = StreamParallel.list();
        long startCurrentTimeMillis11 = System.currentTimeMillis();
        List<Integer> integerList11=new ArrayList<>();
        listVO11.list1.forEach(fore->{
            List<Integer> list = listVO11.list2.stream().filter(fl -> fl == (num - fore)).collect(Collectors.toList());
            integerList11.add(fore + list.get(0));
        });
        long endCurrentTimeMillis11 = System.currentTimeMillis();
        System.out.println("组合顺序流"+"=>"+"mTime:"+(endCurrentTimeMillis11-startCurrentTimeMillis11)+"; "+"sum="+ integerList11 +";");

        //组合并行流
        ListVO listVO12 = StreamParallel.list();
        long startCurrentTimeMillis12 = System.currentTimeMillis();
        List<Integer> integerList12=new ArrayList<>();
        listVO12.list1.forEach(fore->{
            List<Integer> list = listVO12.list2.stream().parallel().filter(fl -> fl == (num - fore)).collect(Collectors.toList());
            integerList12.add(fore + list.get(0));
        });
        long endCurrentTimeMillis12 = System.currentTimeMillis();
        System.out.println("组合并行流"+"=>"+"mTime:"+(endCurrentTimeMillis12-startCurrentTimeMillis12)+"; "+"sum="+ integerList12 +";");

        //组合循环一
        ListVO listVO13 = StreamParallel.list();
        long startCurrentTimeMillis13 = System.currentTimeMillis();
        List<Integer> integerList13=new ArrayList<>();
        for(Integer m:listVO13.list1){
            for(Integer n:listVO13.list2){
                if(m==(num-n)){
                    integerList13.add(m+n);
                }
            }
        }
        long endCurrentTimeMillis13 = System.currentTimeMillis();
        System.out.println("组合循环一"+"=>"+"mTime:"+(endCurrentTimeMillis13-startCurrentTimeMillis13)+"; "+"sum="+ integerList13 +";");

        //组合循环二
        ListVO listVO14 = StreamParallel.list();
        long startCurrentTimeMillis14 = System.currentTimeMillis();
        List<Integer> integerList14=new ArrayList<>();
        for(int i=0;i<listVO14.list1.size();i++){
            for(int j=0;j<listVO14.list2.size();j++){
                if(listVO14.list1.get(i)==(num-listVO14.list2.get(j))){
                    integerList14.add(listVO14.list1.get(i)+listVO14.list2.get(j));
                }
            }
        }
        long endCurrentTimeMillis14 = System.currentTimeMillis();
        System.out.println("组合循环二"+"=>"+"mTime:"+(endCurrentTimeMillis14-startCurrentTimeMillis14)+"; "+"sum="+ integerList14 +";");


    }
}
