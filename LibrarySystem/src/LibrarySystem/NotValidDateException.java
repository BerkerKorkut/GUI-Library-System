package LibrarySystem;

public class NotValidDateException extends Exception {

	NotValidDateException(){
		super();
	}
	
	NotValidDateException(String message){
		super(message);
	}
	
	NotValidDateException(Throwable cause){
		super(cause);
	}
	
	NotValidDateException(String message, Throwable cause){
		super(message, cause);
	}
	
}
