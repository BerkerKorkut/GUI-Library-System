package LibrarySystem;

public class ISBNMismatchException extends Exception {

	ISBNMismatchException(){
		super();
	}
	
	ISBNMismatchException(String message){
		super(message);
	}
	
	ISBNMismatchException(Throwable cause){
		super(cause);
	}
	
	ISBNMismatchException(String message, Throwable cause){
		super(message, cause);
	}
	
}
