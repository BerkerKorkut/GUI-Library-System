package LibrarySystem;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class RegularMember implements LibraryData, Comparable<RegularMember> {
	
	String memberName;
	String Id;
	private ArrayList<Book> checkedOutBooks;
	private ArrayList<OnlineArticle> accessibleArticles;
	int bookQuota;
	int articleQuota;
	private static int memberCount = 0;
	int checkedOutBookCount = 0;
	int accessibleArticleCount = 0;
	
	RegularMember(String Name, String Id, int bookQuota, int articleQuota){
		this.memberName = Name;
		this.Id = Id;
		this.bookQuota = bookQuota;
		this.articleQuota= articleQuota;
		checkedOutBooks = new ArrayList<>();
		accessibleArticles = new ArrayList<>();
		memberCount++;
	}
	
	@Override
	public double calculateCost() {
		double cost = 0;
		int overdueDay = 0;
		LocalDate today = LocalDate.now();
		for(Book book : checkedOutBooks) {
			LocalDate due = book.dueDate.asLocalDate();
			if(today.isAfter(due)) {
				overdueDay = (int) ChronoUnit.DAYS.between(due, today);
				book.fee = (overdueDay * book.feeByDay);
				cost += overdueDay * book.feeByDay;
			}
		}
		for(OnlineArticle article : accessibleArticles) {
			LocalDate dueDate = article.dueDate.asLocalDate();
			overdueDay = (int) ChronoUnit.DAYS.between(dueDate, today);
			if(overdueDay > 0) {
				article.fee = (overdueDay * 10);
				cost += overdueDay * 10;
			}
		}
		
		return cost;
	}
	
	@Override
	public int compareTo(RegularMember m) {
		if(this.calculateCost() == m.calculateCost()) {
			return 0;
		}
		else if(this.calculateCost() > m.calculateCost()) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	void addBook(Book book) {
		if(checkedOutBookCount < bookQuota) {
			checkedOutBooks.add(book);
			checkedOutBookCount++;
			JLabel message = new JLabel("Book '" + book.nameOfBook + "' (ISBN: " + book.ISBN
					+ ") checked out by " + memberName + ".");
			JOptionPane.showMessageDialog(null, message, "Book Checked Out", JOptionPane.INFORMATION_MESSAGE);
			//System.out.println("The book with name '" + book.nameOfBook 
			//		+ "' (ISBN: " + book.ISBN + ") is checked out by user " + memberName);
		}
		else {
			JLabel message = new JLabel("The user's book check out quota is full.");
			JOptionPane.showMessageDialog(null, message, "Book Checked Out", JOptionPane.INFORMATION_MESSAGE);
			//System.out.println("The user's book check out quota is full.");
		}
	}
	
	void returnBook(Book book) {
		checkedOutBooks.remove(book);
		checkedOutBookCount--;
		JLabel message = new JLabel("Book '" + book.nameOfBook + "' (ISBN: " + book.ISBN
				+ ") returned by " + memberName + ".");
		JOptionPane.showMessageDialog(null, message, "Book Returned", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("The book with name '" + book.nameOfBook 
		//		+ "' is returned by user " + memberName);
	}
	
	void addArticle(OnlineArticle article) {
		if(accessibleArticleCount < articleQuota) {
			accessibleArticles.add(article);
			accessibleArticleCount++;
			JLabel message = new JLabel("Online Article '" + article.nameOfArticle + "' (DOI: " + article.DOI
					+ ") accessed by " + memberName + ".");
			JOptionPane.showMessageDialog(null, message, "Article Access Given", JOptionPane.INFORMATION_MESSAGE);
			//System.out.println("The online article titled '" + article.nameOfArticle 
			//		+ "' (DOI: " + article.DOI + ") is accessed by user " + memberName);
		}
		else {
			System.out.println("The user's online article access quota is full.");
		}
	}
	
	void endArticleAccess(OnlineArticle article) {
		accessibleArticles.remove(article);
		accessibleArticleCount--;
		JLabel message = new JLabel("Access of Online Article '" + article.nameOfArticle + "' (DOI: " + article.DOI
				+ ") ended by " + memberName + ".");
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
		//System.out.println("The online article titled '" + article.nameOfArticle 
		//		+ "' (DOI:" + article.DOI + ") is removed from user " + memberName 
		//		+ "'s access list");
	}
	
	ArrayList<Book> getBooks(){
		return checkedOutBooks;
	}
	
	ArrayList<OnlineArticle> getArticles(){
		return accessibleArticles;
	}
	
	static int getCount() {
		return memberCount;
	}
	
	void displayAccount() {
		Iterator<Book> bookIterator = checkedOutBooks.iterator();
		Iterator<OnlineArticle> articleIterator = accessibleArticles.iterator();
		this.calculateCost();
		ArrayList<String> accountInfo = new ArrayList<>();
		accountInfo.add("User Name: " + memberName);
		accountInfo.add("User ID: " + Id);
		accountInfo.add("");
		accountInfo.add("Checked Out Books:");
		//System.out.println("User Name: " + memberName);
		//System.out.println("User ID: " + Id);
		//System.out.println("");
		//System.out.println("Checked Out Books:");
		if(checkedOutBooks.isEmpty()) {
			accountInfo.add("None");
			//System.out.println("None");
		}
		else {
			while(bookIterator.hasNext()) {
				Book b = bookIterator.next();
				accountInfo.add("Book Name: " + b.nameOfBook);
				accountInfo.add("ISBN: " + b.ISBN);
				accountInfo.add("The book titled '" + b.nameOfBook + "' has an overdue charge of " + b.fee);
				accountInfo.add("");
				//System.out.println("Book Name: " + b.nameOfBook);
				//System.out.println("ISBN: " + b.ISBN);
				//System.out.println("The book titled '" + b.nameOfBook + "' has an overdue charge of " + b.fee);
				//System.out.println("");
			}
		}
		accountInfo.add("");
		accountInfo.add("Obtained Online Articles:");
		//System.out.println("");
		//System.out.println("Obtained Online Articles:");
		if(accessibleArticles.isEmpty()) {
			accountInfo.add("None");
			//System.out.println("None");
		}
		else {
			while(articleIterator.hasNext()) {
				OnlineArticle a = articleIterator.next();
				accountInfo.add("Article Name: " + a.nameOfArticle);
				accountInfo.add("DOI: " + a.DOI);
				accountInfo.add("The article titled '" + a.nameOfArticle + "' has an overdue charge of " + a.fee);
				accountInfo.add("");
				//System.out.println("Article Name: " + a.nameOfArticle);
				//System.out.println("DOI: " + a.DOI);
				//System.out.println("The article titled '" + a.nameOfArticle + "' has an overdue charge of " + a.fee);
				//System.out.println("");
			}
		}
		accountInfo.add("");
		accountInfo.add("");
		String[] infoArray = accountInfo.toArray(new String[0]);
		JList accountInfos = new JList(infoArray);
		JDialog infoPage = new JDialog((Frame) null, "All Member Accounts", true);
		infoPage.setLayout(new BorderLayout());
		infoPage.setSize(400,400);
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				infoPage.dispose();
			}
		});
		infoPage.add(accountInfos);
		infoPage.add(close, BorderLayout.SOUTH);
		infoPage.setVisible(true);
	}
	
}
