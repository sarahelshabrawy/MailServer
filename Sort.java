package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.*;
import java.util.Arrays;

public class Sort implements ISort {
    /*private static class triple {
        int[] arr;
        int low;
        int high;
        triple(int[]arr,int low,int high){
            this.arr = arr;
            this.low = low;
            this.high = high;
        }
    }*/

    /* This function takes last element as pivot,
     places the pivot element at its correct
     position in sorted array, and places all
     smaller (smaller than pivot) to left of
     pivot and all greater elements to right
     of pivot */
    int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if (arr[j] < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }
    int partition(String[] arr, int low, int high)
    {
        String pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if(arr[j].compareToIgnoreCase(pivot)<0)
            {
                i++;
                // swap arr[i] and arr[j]
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        String temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }
    /* The main function that implements QuickSort()
         arr[] --> Array to be sorted,
         low  --> Starting index,
         high  --> Ending index */
    void sort(int[] arr)
    {
        Stack sort = new Stack();
        int len = arr.length-1;
        int pi = partition(arr,0, len);
        sort.push(new Point( pi + 1, len));
        sort.push(new Point(0, pi - 1));
        while(!sort.isEmpty()) {
            Point temp = (Point) sort.pop();
            if (temp.x < temp.y) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
                pi = partition(arr, temp.x, temp.y);
                // sort elements before
                // partition and after partition
                sort.push(new Point( pi + 1, temp.y));
                sort.push(new Point( temp.x, pi - 1));
            }
        }
    }
    void sort(String[] arr)
    {
        Stack sort = new Stack();
        int len = arr.length-1;
        int pi = partition(arr,0, len);
        sort.push(new Point( pi + 1, len));
        sort.push(new Point(0, pi - 1));
        while(!sort.isEmpty()) {
            Point temp = (Point) sort.pop();
            if (temp.x < temp.y) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
                pi = partition(arr, temp.x, temp.y);
                // sort elements before
                // partition and after partition
                sort.push(new Point( pi + 1, temp.y));
                sort.push(new Point( temp.x, pi - 1));
            }
        }
    }

    public static void main(String[] args) {
        int [] haha = {8,5,2,3,6,9,7,4,1,0,9,85,3};
        Sort trial = new Sort();
        trial.sort(haha);
        System.out.println(Arrays.toString(haha));
        String [] hihi = {"z", "x" ,"v" ,"f", "g" ,"j ","t", "w ","c" ,"b ","n", "gfhfd","1","%","haha","hihi"};
        trial.sort(hihi);
        System.out.println(Arrays.toString(hihi));
    }

}
