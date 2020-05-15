package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.*;

import static eg.edu.alexu.csd.datastructure.mailServer.App.indexList;

public class Sort implements ISort {

   String sortType;
    EFilterTypes filterType;
    Sort(String sortType){
        this.sortType = sortType;
    }

    /* This function takes last element as pivot,
     places the pivot element at its correct
     position in sorted array, and places all
     smaller (smaller than pivot) to left of
     pivot and all greater elements to right
     of pivot */
    public LinkedList GetSortResults(){
        LinkedList newList = new LinkedList();
        if (indexList.isEmpty())return null ;
        Object[] array = indexList.listTo1DArray(); //arrayofmails


        switch (sortType){
            case "Subject ( A to Z )":
                filterType = EFilterTypes.Subject;
                sortString(array);
                newList.Arraytolist(array);
                break;
            case "Date ( Lastest )":
                filterType = EFilterTypes.Date;
                sort_date(array);
                newList.Arraytolist(array);
                break;
            case "Sender ( A to Z )":
                filterType = EFilterTypes.From;
                sortString(array);
                newList.Arraytolist(array);
                break;
            case "Attachment Size":
                filterType = EFilterTypes.Attachments;
                sortString(array);
                newList.Arraytolist(array);
                break;
            case "Sender ( Z to A )":
                filterType = EFilterTypes.From;
                sortString(array);
                newList.Arraytolist(array);
                newList.ReverseList();
                break;
            case "Date ( Oldest )":
                filterType = EFilterTypes.Date;
                sort_date(array);
                newList.Arraytolist(array);
                newList.ReverseList();
                break;


        }
        return newList;
    }

    /**
     * This method is specially for sorting the body of mail we keep track of the original index to view it in original text highlighted
     * @param arr 2D array of two columns where the first column represents the word
     *         and the second represents its index in text to keep track of its original index after sorting
     *
     * @return body mail sorted
     */
    public static int partitionBodyOfMail(Object[][] arr, int low, int high)
    {
        String pivot = (String) arr[high][0];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if(((String)arr[j][0]).compareToIgnoreCase(pivot)<0)
            {
                i++;
                // swap arr[i] and arr[j]
                Object[] temp =  arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        Object[] temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }
    /* The main function that implements QuickSort()
         arr[] --> Array to be sorted,
         low  --> Starting index,
         high  --> Ending index */

    public static void sortBodyOfMail(Object[][] arr)
    {
        Stack sort = new Stack();
        int len = arr.length-1;
        int pi = partitionBodyOfMail(arr,0, len);
        sort.push(new Point( pi + 1, len));
        sort.push(new Point(0, pi - 1));
        while(!sort.isEmpty()) {
            Point temp = (Point) sort.pop();
            if (temp.x < temp.y) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
                pi = partitionBodyOfMail(arr, temp.x, temp.y);
                // sort elements before
                // partition and after partition
                sort.push(new Point( pi + 1, temp.y));
                sort.push(new Point( temp.x, pi - 1));
            }
        }
    }
/***************************************************************************************************************************************

    /**
     *
     * @return date sorted
     */


    public int partition_date(Object[] arr, int low, int high )
    {
        Mail pivot = (Mail) arr[high];

        int i = (low-1);
        int j;
        for (j=low; j<high; j++)
        {
            Mail current = (Mail) arr[j];
            if(current.date.before(pivot.date))
            {
                i++;
                Object temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        return i+1;
    }
    public void sort_date(Object[] arr)  {
        Stack sort = new Stack();
        int len = arr.length-1;
        int pi;
        if(len<=1)
            return ;
        pi = partition_date(arr,0, len);
        sort.push(new Point( pi + 1, len));
        sort.push(new Point(0, pi - 1));
        while(!sort.isEmpty()) {
            Point temp = (Point) sort.pop();
            if (temp.x < temp.y) {
                pi = partition_date(arr, temp.x, temp.y);
                sort.push(new Point( pi + 1, temp.y));
                sort.push(new Point( temp.x, pi - 1));
            }
        }
//		return arr;
    }

    /**
     *
     * @return string sorted
     */
        public int partitionString(Object[] arr, int low, int high )
    {
        Mail pivot = (Mail) arr[high];

        int i = (low-1);
        int j;
        for (j=low; j<high; j++)
        {
            Mail current = (Mail) arr[j];
            if(((String)current.getAttribute(filterType)).compareToIgnoreCase((String)pivot.getAttribute(filterType))<0)
            {
                i++;
                Object temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        return i+1;
    }
    public void sortString(Object[] arr)  {
        Stack sort = new Stack();
        int len = arr.length-1;
        int pi = partitionString(arr,0, len);
        sort.push(new Point( pi + 1, len));
        sort.push(new Point(0, pi - 1));
        while(!sort.isEmpty()) {
            Point temp = (Point) sort.pop();
            if (temp.x < temp.y) {
                pi = partitionString(arr, temp.x, temp.y);
                sort.push(new Point( pi + 1, temp.y));
                sort.push(new Point( temp.x, pi - 1));
            }
        }
    }
    /**********************************
     *
     */
    public int partitionInteger(Object[] arr, int low, int high )
    {
        Mail pivot = (Mail) arr[high];

        int i = (low-1);
        int j;
        for (j=low; j<high; j++)
        {
            Mail current = (Mail) arr[j];
            if(current.AttachmentsSize<pivot.AttachmentsSize)
            {
                i++;
                Object temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        return i+1;
    }
    public void sortInteger(Object[] arr)  {
        Stack sort = new Stack();
        int len = arr.length-1;
        int pi = partitionInteger(arr,0, len);
        sort.push(new Point( pi + 1, len));
        sort.push(new Point(0, pi - 1));
        while(!sort.isEmpty()) {
            Point temp = (Point) sort.pop();
            if (temp.x < temp.y) {
                pi = partitionInteger(arr, temp.x, temp.y);
                sort.push(new Point( pi + 1, temp.y));
                sort.push(new Point( temp.x, pi - 1));
            }

        }
    }
}
