package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.*;
        import java.util.Calendar;

import static eg.edu.alexu.csd.datastructure.mailServer.App.indexList;

public class Filter implements IFilter {
    public static class filterPair{
        EFilterTypes filterTypes;
        String target;
        filterPair(EFilterTypes filterTypes,String target){
            this.filterTypes = filterTypes;
            this.target = target;
        }
    }

    File filters;
    Stack filterPairStack;

    public Filter( Stack filterPairStack ) {
        this.filterPairStack = filterPairStack;
    }

    public LinkedList GetFilterResults() {
        if(indexList.isEmpty())
            return null;
        Mail mail = (Mail) (indexList).get(0);
        File mainFolder = new File(mail.directory).getParentFile().getParentFile(); // ex:inbox,drafts,etc..
        filters = new File(mainFolder.getPath() + "//filters//indexfile.txt");//check with bothaina
        while (!filterPairStack.isEmpty()) {
            filterPair temp = (filterPair) filterPairStack.pop();
            setFilter(temp.filterTypes, temp.target);
        }
        try {
            writeToFiltersFile(indexList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    return indexList;
    }
    public void setFilter(EFilterTypes filterName,String target){

        switch (filterName){
            case Subject:
            case To:
            case From:
            case Priority:
                indexList = StringFilter(filterName,target);
                break;
            case Attachments :
                indexList = HasAttachmentsFilter();
                break;
            case Date:
               indexList = DateFilter(target);
                //doesn't throw an exception on default because this will be no filter case
            // It will display all mails without any filter
        }

    }
    public LinkedList StringFilter(EFilterTypes filterName,String target){//1)Subject 2)From 3)To 4)Priority

        LinkedList filteredMails = new LinkedList();
        for (int i = 0; i < indexList.size() ; i++) {
            Mail temp = (Mail) indexList.get(i);
            if(target.equalsIgnoreCase((String) temp.getAttribute(filterName))){
                filteredMails.add(temp);
            }
        }
        return filteredMails;
    }
    public LinkedList HasAttachmentsFilter(){   //5)
        LinkedList filteredMails = new LinkedList();

        for (int i = 0; i < indexList.size() ; i++) {
            Mail temp = (Mail) indexList.get(i);
            if(temp.AttachmentsSize!=-1){
                filteredMails.add(temp);
            }
        }
        return filteredMails;
    }

    /**
     *
     * @param within 1) within a day from specified date  2)within a week   3) within a month   4)within a year
     */
    public LinkedList DateFilter(String within){//0)
        LinkedList filteredMails = new LinkedList();
        Calendar from = Calendar.getInstance();
        from.getTime();

        switch (within){
            case "1 Day" : from.add(Calendar.DAY_OF_YEAR,-1);
                break;
            case "1 Week" :from.add(Calendar.WEEK_OF_YEAR,-1);
                break;
            case "1 Month" : from.add(Calendar.MONTH,-1);
                break;
            case "1 Year" : from.add(Calendar.YEAR,-1);
                break;
            default: throw new RuntimeException("Invalid FilterNumber !");
        }

        for (int i = 0; i < indexList.size() ; i++) {
            Mail temp = (Mail) indexList.get(i);
            Calendar calendar = Calendar.getInstance();
            from.setTime(temp.date);
            if(calendar.after(from)){
                filteredMails.add(temp);
            }
        }
        return filteredMails;
    }
    public void writeToFiltersFile(LinkedList filteredMails) throws IOException {
App myApp = new App();
        for (int i = 0; i < filteredMails.size() ; i++) {
            Mail temp = (Mail) filteredMails.get(i);

            myApp.write_index_file(filters.getPath(),temp,temp.To,new File(temp.directory),Double.toString(temp.AttachmentsSize),false);
        }
    }

}
