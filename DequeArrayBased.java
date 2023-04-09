import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import edu.princeton.cs.algs4.StdRandom;

public class DequeArrayBased<Item> implements Iterable<Item> {

	private Object[] frontElements = null;
	private int frontSize = 0;
	private int frontHead = 0;
	private int frontTail = -1;
	
	private Object[] backElements  = null;
	private int backSize  = 0;	
	private int backHead  = 0;
	private int backTail  = -1;
	
    // construct an empty deque
    public DequeArrayBased() {
    	frontElements = new Object[16];
    	backElements  = new Object[16];
    }

    // is the deque empty?
    public boolean isEmpty() {
    	return !(getFrontSize() > 0 || getBackSize() > 0);
    }

    // return the number of items on the deque
    public int size() {
    	return getFrontSize() + getBackSize();
    }

    @Override
    public String toString() {    	
    	return String.format("frontlen=%s, fh=%s, ft=%s, fs=%s, backlen=%s, bh=%s, bt=%s, bs=%s", frontElements.length, frontHead, frontTail,frontSize,backElements.length,backHead,backTail,backSize);
    }
    // add the item to the front
    public void addFirst(Item item) {
    	if(item == null) throw new IllegalArgumentException();
    	resizeFront();
    	frontElements[++frontTail] = item;
    	++frontSize;

    }    
    
    private void resizeFront() {
    	int size = getFrontSize();
    	if( size == frontElements.length || frontTail+1>=frontElements.length) {//double
        	Object[] newElements = new Object[frontElements.length*2];
        	System.arraycopy(frontElements, frontHead, newElements, 0, size);
        	frontElements=newElements;frontTail=size-1;frontHead=0;
    	
    	}else if(size == (frontElements.length/4)) {//half it if size is quarter 
    		int newLen = frontElements.length/2;
        	newLen     = newLen==0?16:newLen;
    		Object[] newElements = new Object[newLen];
        	System.arraycopy(frontElements, frontHead, newElements, 0, size);
        	frontElements=newElements;frontTail=size-1;frontHead=0;

    	}
    }
    
    private void resizeBack() {
    	int size = getBackSize();
    	if( size == backElements.length || backTail+1>=backElements.length) {//double
        	Object[] newElements = new Object[backElements.length*2];
        	System.arraycopy(backElements, backHead, newElements, 0, size);
        	backElements=newElements;backTail=size-1;backHead=0;
    	}else if(size == (backElements.length/4)) {//half it if size is quarter 
        	int newLen = backElements.length/2;
        	newLen     = newLen==0?16:newLen;
    		Object[] newElements = new Object[newLen];
        	System.arraycopy(backElements, backHead, newElements, 0, size);
        	backElements=newElements;backTail=size-1;backHead=0;
    	}
    }
    
    private int getFrontSize() {
    	return frontSize;
    }
    
    private int getBackSize() {
    	return backSize;
    }

    // add the item to the back
    public void addLast(Item item) {
    	if(item == null) throw new IllegalArgumentException();
    	resizeBack();
    	backElements[++backTail] = item;
    	++backSize;

    }

    // remove and return the item from the front
    public Item removeFirst() {

    	if(getFrontSize() > 0) {
    		Item r = (Item) frontElements[frontTail];
    		frontElements[frontTail]=null; // remove loitering
    		frontTail--;
    		if(--frontSize == 0) { frontHead=0;frontTail=-1;}
    		resizeFront();
    		return r;
    	}
    	
    	if(getBackSize() > 0)  {
    		Item r = (Item) backElements[backHead];
    		backElements[backHead]=null;
    		backHead++;
    		if(--backSize == 0) {backHead=0;backTail=-1;}
    		resizeBack();
    		return r;
    	}
    	//return null;
        throw new NoSuchElementException();    	
    }

    // remove and return the item from the back
    public Item removeLast() {
    	
    	if(getBackSize() > 0)  {
    		Item r = (Item) backElements[backTail];
    		backElements[backTail]=null;
    		backTail--;
    		if(--backSize == 0) {backHead=0;backTail=-1;}
    		resizeBack();
    		return r;
    	}
    	
    	if(getFrontSize() > 0) {
    		Item r = (Item) frontElements[frontHead];
    		frontElements[frontHead]=null;
    		frontHead++;
    		if(--frontSize == 0) {frontHead=0;frontTail=-1;}
    		resizeFront();
    		return r;
    	}  	

    	// return null;
    	throw new NoSuchElementException(); 
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
    	Iterator<Item> deQueueIterator = new Iterator<Item>() {

    		private int ft = frontTail;
    		private int bh = backHead;
    		private int fs = frontSize;
    		private int bs = backSize;
    		
			@Override
			public boolean hasNext() {
				
				return fs>0 || bs>0;
			}

			@Override
			public Item next() {
				if(fs>0) {--fs; return (Item)frontElements[ft--];}				
				if(bs>0) {--bs; return (Item)backElements[bh++];}				
		    	throw new NoSuchElementException();    				
		   }
			
		   public void remove() {
			   throw new UnsupportedOperationException();
		   }
	    };
	    
    	return deQueueIterator;
    }

    // unit testing (required)
    public static void main(String[] args)  {
		//Test the DS
    	long st = System.currentTimeMillis();
    	DequeArrayBased<Integer> deque = new DequeArrayBased<Integer>();
        int rangeMin=1;
        int rangeMax=10000;
        int operationCount= 2000000;
        int v=0,opType=0;
        int i=0;
        try {
    		for(i=0;i<operationCount;i++) {
    			opType = StdRandom.uniformInt(1, 4 + 1);

    			switch (opType) {
    			case 1:	
    				v= StdRandom.uniformInt(rangeMin, rangeMax + 1);
    				deque.addFirst(v);
    				break;
    				
    			case 2:	
    				v = StdRandom.uniformInt(rangeMin, rangeMax + 1);
    				deque.addLast(v);
    				break;	
    			case 3:	
    				v = StdRandom.uniformInt(rangeMin, rangeMax + 1);
    				if(!deque.isEmpty())    deque.removeLast();

    				break;	
    			case 4:	
    				v = StdRandom.uniformInt(rangeMin, rangeMax + 1);
    				if(!deque.isEmpty()) deque.removeFirst();

    				break;					
    			default:
    				break;
    			}
    		}

        	
        }finally {
        	
        }
//        
        System.out.println("Done. TimeTaken:"+TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis()-st));
    	System.out.println(String.format("i=%s, deque=%s", i,deque));
    }
    
    

}