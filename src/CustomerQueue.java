
/**
 * HOMEWORK FOUR
 * @author SHAKEEB Saleh
 * ID: 109239204
 * A QUEUE OF CUSTOEMRS
 */
public class CustomerQueue {
	/**
	 * CAPACITY - final size for array
	 * data is teh queue
	 * Front is pointer to the front of the queue
	 * rear is poointer for the rear
	 * size is the current number of elements within the queue
	 */
	public final int CAPACITY = 100;
	private Customer[] data;
	private int front;
	private int rear;
	private int size = 0;
	/**
	 * Default Consrtuctor
	 * creates an empty queue
	 */
	public CustomerQueue(){
		front = -1;
		rear = -1;
		data = new Customer[CAPACITY];
	}
	/**
	 * is empty
	 * shows yo is the queue is empty
	 * @return
	 */
	public boolean isEmpty(){
		return (front == -1);
	}
	/**
	 * enqueue
	 * adds an item to the rear of the queue
	 * p - a customer item
	 */
	public void enqueue(Customer item){
		try {
		if((rear+1)% CAPACITY == front){
			throw new FullQueueException(); 
		}
		if(front == -1){
			front = 0; 
			rear = 0;
			data[rear] = item;
			size++;
		} else {
			rear = (rear + 1)% CAPACITY;
			data[rear] = item;
			size++;
		}
		//this.toString();
		} catch (FullQueueException f){System.out.print("The Queue is Full");}
	}
	/**
	 * Dequeue
	 * removes an item from teh front of the queue
	 * @return cusotemr
	 */
	public Customer dequeue(){
		Customer result = null;
		try {
		if(this.isEmpty()){
			throw new EmptyQueueException(); // have to catch this
		}
		result = data[front];
		if(front == rear){
			front = -1; rear = -1;
			size--;
		} else {
			front = (front + 1)% CAPACITY;
			size--;
		}
		return result;
		} catch (EmptyQueueException e) {System.out.print("The Queue is Empty"); return result;}
	}
	/**
	 * peek
	 * access an item from teh fron of teh queue without removing it
	 * @return customer
	 */
	public Customer peek(){
			Customer result = null;
		try{
			if(this.isEmpty()){
			throw new EmptyQueueException();
			}
			result = data[front];
			return result;
		} catch (EmptyQueueException e){System.out.print("This queue is Empty"); return result;}
	}
	/**
	 * getsize
	 * retireves the size of teh queue
	 * @return
	 */
	public int getSize(){
		return size;
	}
	/**
	 * tostring
	 * returns teh object as a string
	 */
	public String toString(){
		String result = "";
		if (this.isEmpty()){
			result = "[]";
			return result;
		}
		for(int i = front; i<= rear; i++){
			result += data[i % CAPACITY].toString() + ", ";
		}
		String s2 = "[" + result;
		result = s2 + "]";
		return result;
	}

}
