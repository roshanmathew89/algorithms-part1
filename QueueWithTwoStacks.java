import java.util.Iterator;

public class QueueWithTwoStacks<T> implements Queue<T> {

	Stack<T> inbound  = new StackBasedOnResizedArray<T>();
	Stack<T> outbound = new StackBasedOnResizedArray<T>();
	
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				
				return false;
			}

			@Override
			public T next() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	public void enqueue(T s) {
        inbound.push(s);	// only push new items to inbound	
	}

	public T dequeue() throws EmptyQueueException {
		try {
		if(outbound.isEmpty()) {
			while(!inbound.isEmpty()) {
				outbound.push(inbound.pop());
			}
		}
		
		return outbound.pop();
		}catch (EmptyStackException e) {
			throw new EmptyQueueException();
		}
	}

	public boolean isEmpty() {
		return inbound.isEmpty() && outbound.isEmpty();
	}
	
	
	

}
