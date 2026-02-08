package LibrarySystem;

import java.util.ArrayList;
import java.util.Collections;

public class Book extends LibraryMaterial {
	
	String nameOfBook;
	String ISBN;
	Date dueDate;
	double feeByDay = 50;
	private static int bookCount = 0;
	
	Book(String nameOfBook, String ISBN, double cost){
		this.nameOfBook = nameOfBook;
		this.ISBN = ISBN;
		this.setCost(cost);
		this.dueDate = new Date();
		bookCount++;
	}
	
	static void authenticateISBN(String ISBN) throws ISBNMismatchException {
		char[] chars = ISBN.toCharArray();
		ArrayList<Integer> intISBN = new ArrayList<>();
		String temp = "";
		for(char c : chars) {
			try {
				temp = "" + c;
				int i = Integer.parseInt(temp) ;
				intISBN.add(i);
			}
			catch(NumberFormatException e){
				// skips if not int
			}
		}
		if(intISBN.size() != 13) {
			throw new ISBNMismatchException("Exception: ISBN must be 13 digits.");
		}
		else {
			int sum = 0;
			for(int i = 1; i < 14 ;i++) {
				if(i%2 == 0) {
					sum += intISBN.get(i-1) * 3;
				}
				else {
					sum += intISBN.get(i-1);
				}
			}
			if(sum%10 != 0) {
				throw new ISBNMismatchException("Exception:  Invalid ISBN-13 - checksum failed.");
			}
		}
		
	}
	
	@Override
	public double calculateCost() {
		return super.getCost();
	}
	
	void displayInfo() {
		System.out.println("Book Name: " + nameOfBook);
		System.out.println("ISBN: " + ISBN);
		System.out.println("The cost is " + calculateCost());
	}
	
	static int getCount() {
		return bookCount;
	}

}
