import java.util.Iterator;


public class StackBasedOnLinkedList<T>  implements Stack<T> {
	

	private Node<T> first;
	
	public StackBasedOnLinkedList() {
		first=null;
	}
	
	public void push(T s) {
	    Node<T> newItem = new Node<T>(s,first);
	    first=newItem;
	}
	
	public T pop() throws EmptyStackException {
		if(isEmpty()) throw new EmptyStackException();
		
		Node<T> item = first;
		first=first.getNext();
		return (T) item.getItem();
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	

	
	private class StackLinkedListIterator<T> implements Iterator<T>{
		
		private Node<T> first;
		
		public StackLinkedListIterator(StackBasedOnLinkedList<T> stack) {
		  first=stack.first;	
		}
		
		public boolean hasNext() {
			return first != null;
		}
		
		public T next() {
			T item = first.getItem();
			first=first.getNext();
			return item;
		}
	}

	public Iterator<T> iterator() {
		return new StackLinkedListIterator<T>(this);
	}



}



class EmptyStackException extends Exception {	
	
	public String toString() {		
		return "EmptyStackException: pop() Operation on empty stack is not allowed.";
	}
}
