import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class MaxStack<T extends Comparable<T>> implements Stack<T> { 
	
	private Stack<T> stack;
	private Stack<T> maxStack;
	
	
	public MaxStack() {
		stack    = new StackBasedOnResizedArray<T>();
		maxStack = new StackBasedOnResizedArray<T>();
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return stack.iterator();
	}
	@Override
	public void push(T s) {
		stack.push(s);
		if(maxStack.isEmpty()) {
			maxStack.push(s);
		}else {
		   try {
			T currentMax = maxStack.pop(); // since peek is not implements so blah blah blah
			maxStack.push(currentMax);
			if(currentMax.compareTo(s) <= 0) {
				maxStack.push(s);
			}
		   } catch (EmptyStackException e) {}  
		   
		}		
	}
	
	@Override
	public T pop() throws EmptyStackException {
		T currentTop = stack.pop();
		if(!maxStack.isEmpty()) {
		   try {
			T currentMax = maxStack.pop(); // since peek is not implements so blah blah blah
			if(currentMax.compareTo(currentTop) != 0) {
				maxStack.push(currentMax);
			}
		   } catch (EmptyStackException e) {}  
		}		
		return currentTop;
	}
	
	@Override
	public boolean isEmpty() {		
		return stack.isEmpty();
	}
	
	public T getMax() throws EmptyStackException {
		T currentMax = maxStack.pop();
		maxStack.push(currentMax);
		return currentMax;
	}
	

	public static void main(String[] args) throws Exception {
		//Test the DS
		MaxStack<Integer> ms = new MaxStack<Integer>();
        int rangeMin=1;
        int rangeMax=1000000;
        int operationCount= 100000;
        
		for(int i=0;i<operationCount;i++) {
			int opType = ThreadLocalRandom.current().nextInt(1, 3 + 1);
			
			switch (opType) {
			case 1:	
				int v = ThreadLocalRandom.current().nextInt(rangeMin, rangeMax + 1);
				ms.push(v);
				break;
				
			case 2:	
				try{ms.pop();}catch (Exception e) {}
				break;	
			case 3:
				int max=rangeMin-1,cmax=rangeMin-1;	//min is 1			
				
				try {max=ms.getMax();}catch (Exception e) {}
				try {for(int mv : ms) {cmax=cmax<mv?mv:cmax;}}catch (Exception e) {}
				
				if(max>= rangeMin && cmax >= rangeMin && cmax != max) {
					throw new Exception(String.format("Failed max=%s , cmax=%s",max,cmax));}
				
				break; 
			default:
				break;
			}
		}		
	}
}
