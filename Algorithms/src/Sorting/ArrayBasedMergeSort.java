package Sorting;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayBasedMergeSort{

    public static  void merge(int[] first, int[] second, int[] tobesorted, Comparator<Integer> comp){
        int i = 0; int j = 0;
        while (i+j < tobesorted.length){
            if (j == second.length || (i < first.length && comp.compare(first[i], second[j]) < 0)){
                tobesorted[i+j] = first[i++]; // first copies then increments
            } else{
                tobesorted[i+j] = second[j++];
            }
        }


    }

    public static void mergeSort(int[] arr, Comparator<Integer> comp){

        int n = arr.length;

        if (n < 2) return;
        int  mid = n/2;

        int[] first = Arrays.copyOfRange(arr, 0 , mid);
        int[] second = Arrays.copyOfRange(arr, mid, n);

        mergeSort(first, comp);
        mergeSort(second, comp);

        merge(first, second, arr, comp);

    }


    public static void main(String args[]){
        int[] array = {3, 2, 4, 3, 23, 1, 45, 2, 35, 96, 45, 76, 34};

        mergeSort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                        return (o1.intValue() - o2.intValue());
                    }
            });

        for (int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }

}
