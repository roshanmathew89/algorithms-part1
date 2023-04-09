import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class Deque<Item> implements Iterable<Item> {

	
	private int size;
	private Node head;
	private Node tail;
    // construct an empty deque
    public Deque() {
    	head=tail=null;
    }

    // is the deque empty?
    public boolean isEmpty() {
    	return size==0;
    }

    // return the number of items on the deque
    public int size() {
    	return size;
    }

    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	StringBuilder sb = new StringBuilder();
    	Node current = head;
    	while(current != null) {
    		sb.append(current.value).append(',');
    		current=current.next;
    	}
    	return String.format("values=[%s] head=%s, h.n=%s, tail=%s , size=%s",sb.toString(), head,(head==null?"null":head.next),tail,size);
    }
    
    // add the item to the front
    public void addFirst(Item item) {
    	if(item == null) throw new IllegalArgumentException();
    	Node newNode = new Node(item, null, null);
    	
    	if(size == 0) {
    	    head=tail=newNode;
    	    size++;
    	    return;
    	}
    	
    	
    	newNode.next=head;
    	head.prev=newNode;
    	head=newNode;
    	size++;

    }    
    


    // add the item to the back
    public void addLast(Item item) {
    	if(item == null) throw new IllegalArgumentException();
    	Node newNode = new Node(item, null, null);

    	if(size == 0) {
    	    head=tail=newNode;
    	    size++;
    	    return;
    	}
    	
    	tail.next=newNode;
    	newNode.prev=tail;
    	tail=newNode;
    	size++;

    }

    // remove and return the item from the front
    public Item removeFirst() {    
        if(isEmpty())throw new NoSuchElementException();   
        Node removed = head;
        
        if(size == 1) {
           head=tail=null;
           size--;
           return removed.value;
        }
        
        head=head.next;
        if(head != null) head.prev=null;
        size--;        
        return removed.value;
    }

    // remove and return the item from the back
    public Item removeLast() {
    	 if(isEmpty())throw new NoSuchElementException();
         Node removed = tail;
         
         if(size == 1) {
             head=tail=null;
             size--;
             return removed.value;
         }
         
         tail=tail.prev;
         if(tail != null) tail.next=null;
         size--;        
         
         return removed.value;
    	 
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
    	Iterator<Item> deQueueIterator = new Iterator<Item>() {

    		private Node header = head;

    		
			@Override
			public boolean hasNext() {
				
				return header != null;
			}

			@Override
			public Item next() {
			  if(header == null) throw new NoSuchElementException();    	
			  Item next = header.value;
			  header=header.next;
			  return next;
		   }
			
		   public void remove() {
			   throw new UnsupportedOperationException();
		   }
	    };
	    
    	return deQueueIterator;
    }
    
    private class Node {
    	Item value;
    	Node next;
    	Node prev;
    	public Node(Item v , Node nx, Node pr) {
			value=v;
			next =nx;
			prev =pr;
		}
    }


    // unit testing (required)
    public static void main(String[] args) {
		//Test the DS
    	long st = System.currentTimeMillis();
    	Deque<Integer> deque = new Deque<Integer>();
        int rangeMin=1;
        int rangeMax=10000;
        int operationCount= 2000000;
        int v=0,opType=0;
        int i=0;
        //ArrayList<Integer> ta = new ArrayList<Integer>();
        try {
    		for(i=0;i<operationCount;i++) {
    			opType = StdRandom.uniformInt(1, 4 + 1);

    			switch (opType) {
    			case 1:	
    				v= StdRandom.uniformInt(rangeMin, rangeMax + 1);
    				deque.addFirst(v);
    				//ta.add(0,v);
    				break;
    				
    			case 2:	
    				v = StdRandom.uniformInt(rangeMin, rangeMax + 1);
    				deque.addLast(v);
    				//ta.add(v);
    				break;	
    			case 3:	
    				v = StdRandom.uniformInt(rangeMin, rangeMax + 1);
    				if(!deque.isEmpty())    deque.removeLast();
    				//if(!ta.isEmpty())       ta.remove(ta.size()-1);

    				break;	
    			case 4:	
    				v = StdRandom.uniformInt(rangeMin, rangeMax + 1);
    				if(!deque.isEmpty()) deque.removeFirst();
    				//if(!ta.isEmpty())       ta.remove(0);

    				break;	
//    			case 5:	
//    				int count=0;
//    				ArrayList<Integer> s = new ArrayList<Integer>();
//    				for(int mv : deque) s.add(mv);    				
//    				if(!s.equals(ta)) { 
//    					//throw new Exception("Mismatch");
//    				}
//
//    				break;		
    			default:
    				break;
    			}
    		}

        	
        }finally {
        	
        }        
        //System.out.println("Done. TimeTaken:"+TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis()-st));
   	    //System.out.println(String.format("i=%s, deque=%s", i,deque));
    }
    
    

}