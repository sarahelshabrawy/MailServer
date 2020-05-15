package eg.edu.alexu.csd.datastructure.mailServer;

public class single_linkedlist implements ILinkedList {
    private static class Node {
        private Object data;
        private Node next;
        private Node(Object element) {
            this.data = element;
            this.next = null;
        }
    }
    private Node head;
    private int size=0 ;
    single_linkedlist() {
        head = null ;
    }

    public void add(int index, Object element) {
        if(index>=0) {
            Node node = new Node(element);
            node.data = element ;
            node.next = null;
            if(index==0) {
                node.next = head;
                head = node;
            }else {
                Node n = head;
                for(int i=0;i<index-1;i++) {
                    n=n.next;
                }
                node.next = n.next;
                n.next = node;
            }
            size++;
        }else
            throw new ArrayIndexOutOfBoundsException("Invalid index");
    }

    public void add(Object element) {
        Node node = new Node(element);
        node.data = element ;
        node.next = null;
        if(head==null) {
            head =node;
        }else {
            Node n = head;
            while(n.next!=null) {
                n = n.next;
            }
            n.next = node ;
        }
        size++;
    }

    public Object get(int index) {
        if(index>=size || index <0) {
            throw new ArrayIndexOutOfBoundsException("List is out of size");
        }else if(index==0) {
            Node n = head ;
            return n.data;
        }else {
            Node n = head ;
            for(int i=0 ; i<index ;i++) {
                n=n.next;
            }
            return n.data;
        }
    }

    public void set(int index, Object element) {
        if(index>=size || index <0) {
            throw new ArrayIndexOutOfBoundsException("List is out of size");
        }else if(index==0) {
            Node n = head ;
            n.data = element;
        }else {
            Node n = head ;
            for(int i=0 ; i<index ;i++) {
                n=n.next;
            }
            n.data = element;
        }

    }

    public void clear() {
        if(size>0) {
            while(size>0){
                remove(0);
            }
        }
    }

    public boolean isEmpty() {
        return head == null;

    }

    public void remove(int index) {
        if(size>0 && index>=0 && index<size) {
            if(size==1) {
                head = null ;
            }else if(index == 0) {
                head = head.next;
            }else {
                Node n = head;
                Node n1;
                for(int i=0;i<index-1;i++) {
                    n=n.next;
                }
                n1 = n.next;
                n.next = n1.next;
                n1 = null ;
            }
            size--;
        }else
            throw new ArrayIndexOutOfBoundsException("Invalid index");
    }

    public int size() {
        return size;
    }

    public ILinkedList sublist(int fromIndex, int toIndex) {
        single_linkedlist sublist = new single_linkedlist();
        //ILinkedList sublist = (ILinkedList) new LinkedList<Object>();
        Node n2 = head;
        for(int i=0 ;i<fromIndex ; i++) {
            if(n2.next == null)
                throw new ArrayIndexOutOfBoundsException("List is out of size");
            else
                n2 = n2.next ;
        }
        for(int i=fromIndex ;i<=toIndex ; i++) {
            if(n2.next == null)
                throw new ArrayIndexOutOfBoundsException("List is out of size");
            else {
                sublist.add(n2.data);
                n2 = n2.next ;
            }
        }
        return  sublist ;
    }

    public boolean contains(Object o) {
        if(size==0) {
            throw new ArrayIndexOutOfBoundsException("List is empty");
        }else {
            Node n = head;
            boolean flag = false;
            while(n!=null) {
                if(n.data==o) {
                    flag = true;
                    break;
                }else {
                    n = n.next;
                }
            }
            return flag;
        }
    }
    public single_linkedlist arraytolist (Object[][] arr)
    {
        single_linkedlist mylist = new single_linkedlist();
        for (Object[] ints : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                mylist.add(ints[j]);
            }
        }
        return mylist;
    }


    //enhanced listtoarray
    public Object[][] listTo2DArray (){
        Object[][] myarray = new Object [size/2][2];
        Node current = head;
        for (int i = 0; i < size/2 ; i++) {
            for (int j = 0; j < 2 ; j++) {
                myarray[i][j] = current.data;
                current = current.next;
            }
        }
        return myarray;
    }
    public Object[] listTo1DArray() {
        Object[] myArray = new Object [size];
        Node current = head;
        for (int i = 0; i < size() ; i++) {
            myArray[i] = current.data;
            current = current.next;
        }
        return myArray;
    }
    public void print (){
        single_linkedlist.Node current = head;
        System.out.println("PRINTING STARTS");
        System.out.println("size is " + size());
        for (int i = 0; i < size ; i++) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("PRINTING ENDSSSSSSSSSS !!!");
    }
}
