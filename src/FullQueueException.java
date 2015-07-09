
public class FullQueueException extends Exception{
	public FullQueueException(){
		super();
	}
	public FullQueueException(String message){
		super(message);
	}
	
	public FullQueueException(String message, Throwable cause){
		super(message, cause);
	}
	public FullQueueException(Throwable cause){
		super(cause);
	}
}
