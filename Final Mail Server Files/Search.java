package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.*;
import java.io.File;


public class Search {


    public Search(Folder folder, String target){
        Operations_Folder op = new Operations_Folder();
        File file = new File(folder.destination);
        File[] directories = file.listFiles((current, name) -> new File(current, name).isDirectory());
        assert directories != null;
        LinkedList indexList = new LinkedList();
        for (File myFile : directories) {
            File sortedText = new File(myFile + "//sorted.txt");
            Object[] searchResults;
            searchResults = binarySearch(op.sortedFileReader(sortedText), target);
            LinkedList mailIndexList = new LinkedList();
             //list of mails
            if (searchResults!=null&&searchResults.length != 0) {
                mailIndexList = op.readIndexFile(new Folder(myFile.getPath()), searchResults);
                indexList.add(mailIndexList.get(0));
            }
        }
                App.indexList = indexList;
    }
    Object[] binarySearch(Object[][] arr, String x) {
        Stack search = new Stack();
        search.push(new Point(0, arr.length - 1));
        while (!search.isEmpty()) {
            Point temp = (Point) search.pop();
            if (temp.y >= temp.x) {
                int mid = temp.x + (temp.y - temp.x) / 2;

                // If the element is present at the middle itself
                if (((String)arr[mid][0]).equalsIgnoreCase(x))
                    return findAllOccurrences(arr,mid);//return its original index in the UNSORTED array

                // If  is smaller than mid, then it can only
                // be present in left subarray
                if (((String)arr[mid][0]).compareToIgnoreCase(x) > 0)
                    search.push(new Point(temp.x, mid - 1));

                // Else the element can only be present in right
                // subarray
                search.push(new Point(mid + 1, temp.y));
            }

        }
        // We reach here when element is not present in array
        return null;
    }
    public Object[] findAllOccurrences  (Object[][] arr,int mid){
        single_linkedlist indices = new single_linkedlist();
        String target = (String) arr[mid][0];
        for (int i = mid; i < arr.length ; i++) {
            if (((String)arr[i][0]).equalsIgnoreCase(target)){
                indices.add(arr[i][1]);
            }
            else break;
        }
        for (int i = mid-1; i > 0 ; i--) {
            if (((String)arr[i][0]).equalsIgnoreCase(target)){
                indices.add(arr[i][1]);
            }
            else break;
        }
        return indices.listTo1DArray();
    }



}
