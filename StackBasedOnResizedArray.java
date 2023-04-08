import java.util.Iterator;



public class StackBasedOnResizedArray<T> implements Stack<T> {
	
	private int size=0;
	private Object[] elements;
	
	public StackBasedOnResizedArray(int size) {
		elements = new Object[size];
	}
	
	public StackBasedOnResizedArray() {
		elements  = new Object[5];
	}
	
	public void push(T s) {
		if(size == elements.length) resize(elements.length<<1);
		elements[size++]=s;
	}
	
	public T pop() throws EmptyStackException {
		if(isEmpty()) throw new EmptyStackException();
		
		T item = (T) elements[--size];
		elements[size]=null;
		
		if(size>0 && size == elements.length/4) resize(elements.length/2);
		return item;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	private void resize(int capacity) {
		
		Object[] elementsCopy = new Object[capacity];
		System.arraycopy(elements, 0, elementsCopy, 0, size);
		elements=elementsCopy;
		System.out.println("Resized to :"+elements.length);
		
	}
	
	private class StackArrayIterator<T> implements Iterator<T>{
		
		private Object[] elements;
		private int size;
		public StackArrayIterator(StackBasedOnResizedArray<T> stack) {
		  size=stack.size;	
		  elements=stack.elements;
		}
		
		public boolean hasNext() {
			return size>0;
		}
		
		public T next() {			
			return (T) elements[--size];
		}
	}

	public Iterator<T> iterator() {
		return new StackArrayIterator<T>(this);
	}

}
