import java.util.EmptyStackException;
import java.util.Iterator;

public class QueueBasedOnResizedArray<T> implements Queue<T> {
	
	private int size=0;
	private Object[] elements;
	private int first=0;
	private int end =0;
	
	public QueueBasedOnResizedArray(int size) {
		elements =new Object[size];
	}
	
	public QueueBasedOnResizedArray() {
		elements =new Object[5];
	}
	
	public void enqueue(T s) {
		if(size == elements.length) resize(elements.length<<1);
		elements[end++]=s;
		size++;
		
	}
	
	public T dequeue() throws EmptyQueueException {
		if(isEmpty()) throw new EmptyQueueException();
		
		T item = (T) elements[first];
		elements[first++]=null;
		size--;
		
		if(size>0 && size == elements.length/4) resize(elements.length/2);
		return item;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	private void resize(int capacity) {
		
		Object[] elementsCopy = new Object[capacity];
		System.arraycopy(elements, first, elementsCopy, 0, size);
		elements=elementsCopy;
		first=0;
		end=size;
		System.out.println("Resized to :"+elements.length+" first:"+first+", end:"+end);
		
	}
	
	private class QueueArrayIterator<T> implements Iterator<T>{
		
		private Object[] elements;
		private int size;
		private int first;
		
		public QueueArrayIterator(QueueBasedOnResizedArray<T> queue) {
		  size=queue.size;
		  first=queue.first;
		  elements=queue.elements;
		}
		
		public boolean hasNext() {
			return size>0;
		}
		
		public T next() {			
			size--;
			return (T) elements[first++];
		}
	}

	public Iterator<T> iterator() {
		return new QueueArrayIterator<T>(this);
	}

}
