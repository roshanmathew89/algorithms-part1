
public interface Queue<T>  extends Iterable<T> {
	
	void enqueue(T s);
	
    T dequeue() throws EmptyQueueException;
	
	public boolean isEmpty();

}
