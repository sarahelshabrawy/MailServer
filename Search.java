package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.*;
import java.util.Arrays;

public class Search {
    int binarySearch(String[] arr, String x) {
        Stack search = new Stack();
        search.push(new Point(0, arr.length - 1));
        while (!search.isEmpty()) {
            Point temp = (Point) search.pop();
            if (temp.y >= temp.x) {
                int mid = temp.x + (temp.y - temp.x) / 2;

                // If the element is present at the middle itself
                if (arr[mid].equalsIgnoreCase(x))
                    return mid;

                // If element is smaller than mid, then it can only
                // be present in left subarray
                if (arr[mid].compareToIgnoreCase(x) > 0)
                    search.push(new Point(temp.x, mid - 1));

                // Else the element can only be present in right
                // subarray
                search.push(new Point(mid + 1, temp.y));
            }

        }
        // We reach here when element is not present in array
        return -1;
    }

    public static void main(String[] args) {
        Search haha = new Search();
        Sort trial = new Sort();
        String [] hihi = {"z", "x" ,"v" ,"f", "g" ,"j ","t", "w ","c" ,"b ","n", "gfhfd","1","%","haha","hihi","hihi"};
        trial.sort(hihi);
        System.out.println(Arrays.toString(hihi));
        System.out.println(haha.binarySearch(hihi,"hihi"));
        System.out.println(haha.binarySearch(hihi,"haha"));

    }

}
