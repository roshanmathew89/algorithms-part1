import java.util.Iterator;

public class QueueBasedOnLinkedList<T>  implements Queue<T> {
	

	private Node<T> first;
	private Node<T> end;
	
	public QueueBasedOnLinkedList() {
	   first=null;
	   end=null;
	}
	
	public void enqueue(T s) {
	    Node<T> item = new Node(s, null);
	    if(first == null) {
	    	first=end=item;
	    }else {	    
	        end.setNext(item);
	        end=item;
	    }
	    
	}
	
	public T dequeue() throws EmptyQueueException {
		 if(isEmpty()) throw new EmptyQueueException();
		 
		 Node<T> item = first;
		 first        = first.getNext();
		 return (T) item.getItem();
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	private class QueueLinkedListIterator<T> implements Iterator<T>{
		
		private Node<T> first;
		
		public QueueLinkedListIterator(QueueBasedOnLinkedList<T> queue) {
		  first=queue.first;	
		}
		
		@Override
		public boolean hasNext() {
			return first != null;
		}
		
		@Override
		public T next() {
			T item = first.getItem();
			first=first.getNext();
			return item;
		}
	}
	

	@Override
	public Iterator<T> iterator() {
		return new QueueLinkedListIterator<T>(this);
	}

}

class EmptyQueueException extends Exception {	
	@Override
	public String toString() {		
		return "EmptyQueueException: Dequeue Operation on empty queue is not allowed.";
	}
}
