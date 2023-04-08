import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QueueTestClient {
	
	public static void main(String[] args) throws EmptyQueueException {
		Queue<String> queue = null;
		
		
		 //queue = new  QueueBasedOnLinkedList<String>();
		   queue = new  QueueBasedOnResizedArray<String>();
		  
		while(!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if("-".equals(s)) {
				StdOut.print(queue.dequeue());
			}else if("~".equals(s)) {
				System.out.println();
				for(String v : queue) {
					System.out.print(v+",");
				}
				System.out.println();
			}else {
				queue.enqueue(s);
			}
				
		}
	}

}
