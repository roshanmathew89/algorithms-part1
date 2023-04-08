
public interface Stack<T> extends Iterable<T> {
	
	void push(T s);
	
    T pop() throws EmptyStackException;
	
	public boolean isEmpty();

} 
