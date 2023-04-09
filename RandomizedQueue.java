import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	
	private int size=0;
	private Node head;
	private Node tail;
    // construct an empty randomized queue
    public RandomizedQueue() {
    	head=tail=null;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
    	return size == 0;
    }

    // return the number of items on the randomized queue
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
    // add the item
    public void enqueue(Item item) {
    	if(item == null) throw new IllegalArgumentException();
    	
    	if(size == 0) {
    		head = tail = new Node(item, null,null);
    		size++;
    		return;
    	}
    	
    	Node newNode = new Node(item, null,null);
    	tail.next=newNode;
    	newNode.prev=tail;
    	tail=newNode;    	
    	size++;
    }

    // remove and return a random item
    public Item dequeue() {
    	if(isEmpty()) throw new NoSuchElementException();    	
    	int index = StdRandom.uniformInt(0, size);
    	Node removedNode= remove(index);
    	return removedNode.value;
    	
    }

    // return a random item (but do not remove it)
    public Item sample() {
    	if(isEmpty()) throw new NoSuchElementException();    	
    	int index = StdRandom.uniformInt(0, size);
    	Node current = head;
    	while(index-- > 0 && current != null) {
    		current=current.next;
    	}
    	return current.value;
    }
    
    
    private Node remove(int index) {
    	Node removed = null;
    	//System.out.println("index:"+index+", "+this);
    	if(index==0) {
    		removed = head;
    		if(size == 1) {
    			head=tail=null;
    		}else {
    			head=head.next;
    			head.prev=null;
    		}    		
    		size--;
    		return removed;
    		
    	}
    	
    	if(index==size-1) {
    		removed = tail;
    		tail=tail.prev;
    		tail.next=null;
    		size--;
    		return removed;
    	}
    	
    	removed = head;
    	int i=index;
    	while(index > 0) {
    		removed=removed.next;
    		index--;
    	}
    	
    	removed.prev.next=removed.next;
    	removed.next.prev=removed.prev;
    	size--;    	
    	return removed;    	
    }
    
    private RandomizedQueue<Item> copy() {
    	if(isEmpty()) return null;
    	
    	Node nHead   = new Node(head.value,null,null);
    	Node nc      = nHead;
    	Node current = head.next;
    	while(current != null) {
    		nc.next=new Node(current.value,null,nc);
    		nc=nc.next;
    		current=current.next;
    	
    	}
    	
    	RandomizedQueue<Item> clone = new RandomizedQueue<Item>();
    	clone.size=size;
    	clone.head=nHead;
    	clone.tail=nc;
    	return clone;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
    	
    	Iterator<Item> randomIterator = new Iterator<Item>() {
    		RandomizedQueue<Item> clone  = copy();
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return clone != null && !clone.isEmpty();
			}

			@Override
			public Item next() {
				return clone.dequeue();
			}   		
		};

		return randomIterator;
    	
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
    	
    	RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>(); 	
		//Test the DS
    	long st = System.currentTimeMillis();
        int rangeMin=1;
        int rangeMax=10000;
        int operationCount= 2000000;
        int v=0,opType=0;
        int i=0;
        try {
    		for(i=0;i<operationCount;i++) {
    			opType = StdRandom.uniformInt(1, 4+1);
    			switch (opType) {
    			case 1:	
    				v= StdRandom.uniformInt(rangeMin, rangeMax + 1);
    				rq.enqueue(v);
    				break;
    				
    			case 2:	
    				if(!rq.isEmpty()) {rq.dequeue();}
    				break;	
    			case 3:	
    				if(!rq.isEmpty()) {rq.sample();}
    				break;
//    			case 4:	
//    				int count =0;
//    				for(int mv : rq) {count++;}
//    				if(count != rq.size()) {
//    					throw new Exception("Mismatch count="+count+", size:"+rq.size);
//    				}
//    				
//    				break;		
    			default:
    				break;
    			}
    		}        	
        }finally {
        	
        }
        

    }
    


}