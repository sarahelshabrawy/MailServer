package eg.edu.alexu.csd.datastructure.mailServer;

public class LinkedList implements ILinkedList{

	   private doubleNode head;
	    private doubleNode tail;
	    private int size;
	    public LinkedList() {
	        size = 0;
	        head = new doubleNode(null, null,null);
	        tail = new doubleNode(null,null,head);
	        head.next=tail;
	    }
	    private class doubleNode {
	        Object element;
	        doubleNode next;
	        doubleNode prev;

	        public doubleNode(Object element, doubleNode next, doubleNode prev) {
	            this.element = element;
	            this.next = next;
	            this.prev = prev;
	        }
	        public doubleNode() {
	        }
	    }


	    public void add(int index, Object element) {
	        doubleNode current = head.next;
	        if (index>size || index <0)
	            throw new RuntimeException("Invalid Index !");
	        else if (size == index)
	            add(element);
	        else {
	            for (int i = 0; i < index; i++)
	                current = current.next;
	            doubleNode addedNode = new doubleNode(element, current, current.prev);
	            current.prev.next = addedNode;
	            current.prev = addedNode;//it was current.next.prev >> error
	            size++;
	        }
	    }

	    public void add(Object element) {
	        doubleNode addedNode = new doubleNode(element,tail,tail.prev); // three steps
	        tail.prev.next = addedNode;
	        tail.prev = addedNode;//You forgot this step..
	        size++;
	    }

	    public Object get(int index) {
	        doubleNode current = head.next;
	        if (index>=size || index <0)
	            throw new RuntimeException("Invalid Index !");
	        for (int i = 0; i < index ; i++) {
	                current = current.next;
	        }
	        return current.element;
	    }

	   
	    public void set(int index, Object element) {
	        doubleNode current = head.next;
	        if (index>=size || index <0)
	            throw new RuntimeException("Invalid Index !");
	        for ( int i = 0; i < index ; i++) {
	                current = current.next;
	        }
	            current.element = element;
	        }

	    
	    public void clear() { 
	       head.next= tail;
	       tail.prev = head;
	       size = 0;
	    }

	   
	    public boolean isEmpty() {

	        return (size==0);
	    }

	  
	    public void remove(int index) { // revise if you want to decide from where to start the loop depending of the index (nearer to tail or head)
	        doubleNode deletedItem = head.next;
	       if (index>=size || index <0)
	           throw new RuntimeException("Invalid Index !");
	       for ( int i = 0; i < index ; i++)
	               deletedItem = deletedItem.next;
	            deletedItem.prev.next=deletedItem.next;
	            deletedItem.next.prev = deletedItem.prev;
	            deletedItem.next=null;
	            deletedItem.prev=null;
	            deletedItem.element = null;
	            size--;
	    }

	    
	    public int size() {
	        return size;
	    }

	   
	    public ILinkedList sublist(int fromIndex, int toIndex) {
	        doubleNode current = head.next;
	        LinkedList sublist = new LinkedList();
	        if (fromIndex>=size || fromIndex <0 || toIndex >= size || toIndex < fromIndex )
	            throw new RuntimeException("Invalid Index !");
	        for ( int i = 0; i < fromIndex ; i++) {
	                current = current.next;
	        }
	        for ( int i = fromIndex; i <= toIndex ; i++) {
	                sublist.add(current.element);
	                current = current.next;
	        }

	        return sublist;
	    }

	    
	    public boolean contains(Object o) {
	        doubleNode current = head.next;
	        for ( int i = 0; i < size; i++) {
	            if(current.element==o)
	                return true;
	            else
	                current = current.next;
	        }
	        return false;
	    }
	    public void print (){
	        doubleNode current = head.next;
	        System.out.println("PRINTING STARTS");
	        System.out.println("size is " + size());
	        for (int i = 0; i < size ; i++) {
	            System.out.println(current.element);
	            current = current.next;
	        }
	        System.out.println("PRINTING ENDSSSSSSSSSS !!!");
	}	
	    
/////////////////////////////////////////////////////////////////////////////
	    public LinkedList arraytolist (Object[][] arr)
	    {
	    	LinkedList mylist = new LinkedList();
	        for (Object[] ints : arr) {
	            for (int j = 0; j < arr[0].length; j++) {
	                mylist.add(ints[j]);
	            }
	        }
	        return mylist;
	    }
	    public Object[][] listtoarray (LinkedList mylist){
	    	Object[][] myarray = new Object [(mylist.size()/2)][2];
	        for (int i = 0; i < mylist.size()/2 ; i++) {
	            for (int j = 0; j < 2 ; j++) {
	                myarray[i][j] = (mylist.get(j+i*2));
	            }
	        }
	        return myarray;
	    }

}