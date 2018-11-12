package com.algorithm.dynamiclink;

import java.util.ArrayList;
import java.util.List;

public class SpearmanUtil {

    public static final Double ZERO=new Double(0);

    public static Double spearman(List list1,List list2){
        if (list1==null||list2==null||list1.isEmpty()||list2.isEmpty()||list1.size()!=list2.size()){
            return ZERO;
        }
        Double result=new Double(0);
        List<Double> doubleList1=new ArrayList<>();
        List<Integer> index1=new ArrayList<>();
        List<Double> doubleList2=new ArrayList<>();
        List<Integer> index2=new ArrayList<>();
        int size = list1.size();
        Double sum1=new Double(0);
        Double sum2=new Double(0);
        try {
            for (int i = 0; i < size; i++) {
                Double double1 = Double.valueOf(list1.get(i).toString());
                doubleList1.add(double1);
                index1.add(i);
                sum1+=double1;
                Double double2 = Double.valueOf(list2.get(i).toString());
                doubleList2.add(double2);
                index2.add(i);
                sum2+=double2;
            }
        }
        catch (Exception e){
            return ZERO;
        }
        Double avg1=sum1/size;
        Double avg2=sum2/size;
        sort(doubleList1,index1);
        sort(doubleList2,index2);
        Double sum=new Double(0);
        for (int i=0;i<size;i++){
            int d = i - index2.indexOf(index1.get(i));
            sum+=(d*d);
        }
        if (sum!=0){
            Double numerator=new Double(6*sum);
            Double denominator=new Double(size*(size*size-1));
            result=1-numerator/denominator;
        }else {
            Double numerator=new Double(0);
            Double denominator=new Double(0);
            Double xSum=new Double(0);
            Double ySum=new Double(0);
            for (int i=0;i<size;i++){
                numerator+=(doubleList1.get(i)-avg1)*(doubleList2.get(i)-avg2);
                xSum+=(doubleList1.get(i)-avg1)*(doubleList1.get(i)-avg1);
                ySum+=(doubleList2.get(i)-avg2)*(doubleList2.get(i)-avg2);
            }
            denominator=Math.sqrt(xSum*ySum);
            result=numerator/denominator;
        }
        return result;
    }

    private static void sort(List<Double> list,List<Integer> indexList){
        Boolean flag = true;
        int size = list.size();
        for (int i=0;i<size-1&&flag;i++){
            flag=false;
            for (int j=0;j<size-i-1;j++){
                if (list.get(j)>list.get(j+1)){
                    swap(list,j,j+1);
                    swap(indexList,j,j+1);
                    flag=true;
                }
            }
        }

    }

    private static void swap(List list,Integer index1,Integer index2){
        Object o = list.get(index1);
        list.set(index1,list.get(index2));
        list.set(index2,o);
    }

}
