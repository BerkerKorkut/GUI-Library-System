package LibrarySystem;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum Menu {
	
	ADDBOOK(1, "Add a new book"){
		public void choose() {
			
			JDialog newBook = new JDialog((Frame) null, "Add New Book", true);
			newBook.setLayout(new GridLayout(4, 2));
			newBook.setSize(400, 200);
			JTextField bookNameField = new JTextField();
			JTextField ISBNfield = new JTextField();
			JTextField priceField = new JTextField();
			JButton submit = new JButton("Submit");
			submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					String name = bookNameField.getText();
					String isbn = ISBNfield.getText();
					double cost = 0;
					try {
						Book.authenticateISBN(isbn);
					}
					catch(ISBNMismatchException e) {
						JLabel message = new JLabel(e.getMessage());
						JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
					}
					String costString = priceField.getText();
					try {
						cost = Double.parseDouble(costString);
					}
					catch(Exception e) {
						JLabel message = new JLabel("Price has to be a number");
						JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
					}
					
					bookArray.add(new Book(name, isbn, cost));
					JLabel message = new JLabel("A new book is added to the library with name '" + bookArray.get(Book.getCount()-1).nameOfBook 
							+ "' and ISBN# " + bookArray.get(Book.getCount()-1).ISBN);
					JOptionPane.showMessageDialog(null, message, "Book Added", JOptionPane.INFORMATION_MESSAGE);
					newBook.dispose();
				}
			});
			newBook.add(new JLabel("Book Name: "));
			newBook.add(bookNameField);
			newBook.add(new JLabel("ISBN: "));
			newBook.add(ISBNfield);
			newBook.add(new JLabel("Price: "));
			newBook.add(priceField);
			newBook.add(new JPanel());
			newBook.add(submit);
			newBook.setVisible(true);
				
			
			/*
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter book name: ");
			String name = scan.nextLine();
			String isbn = "";
			boolean loop = true;
			
			do {
				try {
					System.out.println("Enter ISBN: ");
					isbn = scan.nextLine();
					Book.authenticateISBN(isbn);
					loop= false;
				}
				catch(ISBNMismatchException e) {
					System.out.println(e.getMessage());
				}
				
			}while(loop);
			
			
			
			System.out.println("Enter the price of the book: ");
			double cost = scan.nextDouble();
			scan.nextLine();
			bookArray.add(new Book(name, isbn, cost));
			System.out.println("A new book is added to the library with name '" + bookArray.get(Book.getCount()-1).nameOfBook 
					+ "' and ISBN# " + bookArray.get(Book.getCount()-1).ISBN);
			System.out.println("Total number of books in the library: " + Book.getCount());
			Library();
			*/
			
		}
	}, 
	
	ADDARTICLE(2, "Add a new Online Article"){
		public void choose() {
			
			
			
			JDialog newArticle = new JDialog((Frame) null, "Add New Article", true);
			newArticle.setLayout(new GridLayout(4, 2));
			newArticle.setSize(400, 200);
			JTextField articleNameField = new JTextField();
			JTextField DOIfield = new JTextField();
			JTextField publisherField = new JTextField();
			JButton submit = new JButton("Submit");
			submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					String name = articleNameField.getText();
					String doi = DOIfield.getText();
					String publisher = publisherField.getText();
					
					articleArray.add(new OnlineArticle(name, doi, publisher));
					JLabel message = new JLabel("A new article is added to the library with name '" + articleArray.get(OnlineArticle.getCount()-1).nameOfArticle 
							+ "' and DOI# " + articleArray.get(OnlineArticle.getCount()-1).DOI);
					JOptionPane.showMessageDialog(null, message, "Article Added", JOptionPane.INFORMATION_MESSAGE);
					newArticle.dispose();
				}
			});
			newArticle.add(new JLabel("Article Name: "));
			newArticle.add(articleNameField);
			newArticle.add(new JLabel("DOI: "));
			newArticle.add(DOIfield);
			newArticle.add(new JLabel("Publisher: "));
			newArticle.add(publisherField);
			newArticle.add(new JPanel());
			newArticle.add(submit);
			newArticle.setVisible(true);
			
			
			/*
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter article name: ");
			String name = scan.nextLine();
			System.out.println("Enter DOI: ");
			String doi = scan.nextLine();
			System.out.println("Enter publisher: ");
			String publisher = scan.nextLine();
			articleArray.add(new OnlineArticle(name, doi, publisher));
			System.out.println("A new article is added to the library with name '" + articleArray.get(OnlineArticle.getCount()-1).nameOfArticle 
					+ "' and DOI# " + articleArray.get(OnlineArticle.getCount()-1).DOI);
			System.out.println("Total number of online articles in the library: " + OnlineArticle.getCount());
			Library();
			*/
			
		}
	},
	
	CREATEACCOUNT(3, "Create a member account"){
		
		public void choose() {
			
			JDialog createAccount = new JDialog((Frame) null, "Create Member Account", true);
			createAccount.setLayout(new GridLayout(4, 2));
			createAccount.setSize(400, 200);
			JTextField nameField = new JTextField();
			JTextField IDfield = new JTextField();
			String[] memberTypes = {"Regular Member", "Student Member", "Academic Member"};
			JComboBox memberTypeChoice = new JComboBox<>(memberTypes);
			JButton submit = new JButton("Submit");
			submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					String name = nameField.getText();
					String id = IDfield.getText();
					String memberType = (String) memberTypeChoice.getSelectedItem();
					if(memberType.equals("Regular Member")) {
						memberArray.add(new RegularMember(name, id, 1, 1));
						JLabel message = new JLabel("A new regular account is created with name '" + memberArray.get(RegularMember.getCount()-1).memberName 
								+ "' and ID: '" + memberArray.get(RegularMember.getCount()-1).Id + "'");
						JOptionPane.showMessageDialog(null, message, "Account Created", JOptionPane.INFORMATION_MESSAGE);
						createAccount.dispose();
					}
					else if(memberType.equals("Student Member")) {
						memberArray.add(new Student(name, id));
						JLabel message = new JLabel("A new student account is created with name '" + memberArray.get(Student.getCount()-1).memberName 
								+ "' and ID: '" + memberArray.get(Student.getCount()-1).Id + "'");
						JOptionPane.showMessageDialog(null, message, "Account Created", JOptionPane.INFORMATION_MESSAGE);
						createAccount.dispose();
					}
					else if(memberType.equals("Academic Member")) {
						memberArray.add(new Academic(name, id));
						JLabel message = new JLabel("A new academic account is created with name '" + memberArray.get(Academic.getCount()-1).memberName 
								+ "' and ID: '" + memberArray.get(Academic.getCount()-1).Id + "'");
						JOptionPane.showMessageDialog(null, message, "Account Created", JOptionPane.INFORMATION_MESSAGE);
						createAccount.dispose();
					}
				}
			});
			createAccount.add(new JLabel("Select Member Type: "));
			createAccount.add(memberTypeChoice);
			createAccount.add(new JLabel("Name: "));
			createAccount.add(nameField);
			createAccount.add(new JLabel("ID: "));
			createAccount.add(IDfield);
			createAccount.add(new JPanel());
			createAccount.add(submit);
			createAccount.setVisible(true);
			
			
			/*
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter the type of member you would like to create:");
			System.out.println("1. Regular Member");
			System.out.println("2. Student Member");
			System.out.println("3. Academic Member");
			int memberType = scan.nextInt();
			scan.nextLine();
			if(memberType == 1) {
				System.out.println("Enter new member name: ");
				String name = scan.nextLine();
				System.out.println("Enter new member id: ");
				String id = scan.nextLine();
				memberArray.add(new RegularMember(name, id, 1, 1));
				System.out.println("A new regular account is created with name '" + memberArray.get(RegularMember.getCount()-1).memberName 
						+ "' and ID: '" + memberArray.get(RegularMember.getCount()-1).Id + "'");
			}
			else if(memberType == 2) {
				System.out.println("Enter new member name: ");
				String name = scan.nextLine();
				System.out.println("Enter new member id: ");
				String id = scan.nextLine();
				memberArray.add(new Student(name, id));
				System.out.println("A new student account is created with name '" + memberArray.get(Student.getCount()-1).memberName 
						+ "' and ID: '" + memberArray.get(Student.getCount()-1).Id + "'");	
			}
			else if(memberType == 3) {
				System.out.println("Enter new member name: ");
				String name = scan.nextLine();
				System.out.println("Enter new member id: ");
				String id = scan.nextLine();
				memberArray.add(new Academic(name, id));
				System.out.println("A new academic account is created with name '" + memberArray.get(Academic.getCount()-1).memberName 
						+ "' and ID: '" + memberArray.get(Academic.getCount()-1).Id + "'");
			}
			else {
				System.out.println("Choose a valid option");
				choose();
			}
			
			Library();
			*/
			
		}
	},
	
	CHECKOUT(4, "Check out a book"){
		public void choose() {
			JDialog checkoutBook = new JDialog((Frame) null, "Check Out a Book", true);
			checkoutBook.setLayout(new GridLayout(6, 2));
			checkoutBook.setSize(400, 200);
			JTextField IDfield = new JTextField();
			JTextField ISBNfield = new JTextField();
			JTextField yearField = new JTextField();
			JTextField monthField = new JTextField();
			JTextField dayField = new JTextField();
			JButton submit = new JButton("Submit");
			submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					String id = IDfield.getText();
					String isbn = ISBNfield.getText();
					int year = 33;
					int month = 33;
					int day = 33;
					try {
						year = Integer.parseInt(yearField.getText());
						month = Integer.parseInt(monthField.getText());
						day = Integer.parseInt(dayField.getText());
					}
					catch(Exception e) {
						JLabel message = new JLabel("Year, Month and Day values have to be numbers");
						JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
					}					
					Iterator<RegularMember> memberIterator = memberArray.iterator();
					Iterator<Book> bookIterator = bookArray.iterator();
					RegularMember m = null;
					Book b = null;
					boolean containMember = false;
					boolean containBook = false;
					
					while(memberIterator.hasNext()) {
						m = memberIterator.next();
						if(m.Id.equals(id)) {
							containMember = true;
							break;
						}
					}
					if(containMember) {
						while(bookIterator.hasNext()) {
							b = bookIterator.next();
							if(b.ISBN.equals(isbn)) {
								containBook = true;
								break;
							}
						}
						if(containBook) {
							try {
								if(Date.DateValidator(day, month, year)) {
									b.dueDate.setDate(day, month, year);
									m.addBook(b);
									checkoutBook.dispose();
								}
								else {
									throw new NotValidDateException("Exception: Invalid date: " + day
											+ "/" + month + "/" + year);
								}
							}
							catch(NotValidDateException e) {
								JLabel message = new JLabel(e.getMessage());
								JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
							}
							
						}
						else {
							JLabel message = new JLabel("There is no book with that ISBN");
							JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JLabel message = new JLabel("There is no account with that ID");
						JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			checkoutBook.add(new JLabel("Member ID: "));
			checkoutBook.add(IDfield);
			checkoutBook.add(new JLabel("Book ISBN: "));
			checkoutBook.add(ISBNfield);
			checkoutBook.add(new JLabel("Due Year: "));
			checkoutBook.add(yearField);
			checkoutBook.add(new JLabel("Due Month: "));
			checkoutBook.add(monthField);
			checkoutBook.add(new JLabel("Due Day: "));
			checkoutBook.add(dayField);
			checkoutBook.add(new JPanel());
			checkoutBook.add(submit);
			checkoutBook.setVisible(true);
			
			
			/*
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter a member ID number: ");
			String id = scan.nextLine();
			
			Iterator<RegularMember> memberIterator = memberArray.iterator();
			Iterator<Book> bookIterator = bookArray.iterator();
			RegularMember m = null;
			Book b = null;
			boolean containMember = false;
			boolean containBook = false;
			boolean loop = true;
			
			while(memberIterator.hasNext()) {
				m = memberIterator.next();
				if(m.Id.equals(id)) {
					containMember = true;
					break;
				}
			}
			if(containMember) {
				System.out.println("Search for a Book with ISBN: ");
				String isbn = scan.nextLine();
				while(bookIterator.hasNext()) {
					b = bookIterator.next();
					if(b.ISBN.equals(isbn)) {
						containBook = true;
						break;
					}
				}
				if(containBook) {
					
					do {
						try {
							System.out.println("Enter a due year: ");
							int year = scan.nextInt();
							scan.nextLine();
							System.out.println("Enter a due month: ");
							int month = scan.nextInt();
							scan.nextLine();
							System.out.println("Enter a due day: ");
							int day = scan.nextInt();
							scan.nextLine();
							
							if(Date.DateValidator(day, month, year)) {
								b.dueDate.setDate(day, month, year);
								m.addBook(b);
								loop = false;
							}
							else {
								throw new NotValidDateException("Exception: Invalid date: " + day
										+ "/" + month + "/" + year);
							}
							
						}
						catch(NotValidDateException e) {
							System.out.println(e.getMessage());
						}
					}while(loop);
					
				}
				else {
					System.out.println("There is no book with that ISBN");
				}
			}
			else {
				System.out.println("There is no account with that ID");
			}
			
			Library();
			*/
			
		}
	},
	
	RETURNBOOK(5, "Return a book"){
		public void choose() {
			JDialog returnBook = new JDialog((Frame) null, "Return a Book", true);
			returnBook.setLayout(new GridLayout(3, 2));
			returnBook.setSize(400, 200);
			JTextField IDfield = new JTextField();
			JTextField ISBNfield = new JTextField();
			JButton submit = new JButton("Submit");
			submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					String id = IDfield.getText();
					String isbn = ISBNfield.getText();					
					RegularMember m = null;
					Book b = null;
					Iterator<RegularMember> memberIterator = memberArray.iterator();
					boolean containMember = false;
					boolean containBook = false;
					while(memberIterator.hasNext()) {
						m = memberIterator.next();
						if(m.Id.equals(id)) {
							containMember = true;
							break;
						}
					}
					if(containMember) {
						Iterator<Book> bookIterator = m.getBooks().iterator();
						while(bookIterator.hasNext()) {
							b = bookIterator.next();
							if(b.ISBN.equals(isbn)) {
								containBook = true;
								break;
							}
						}
						if(containBook) {
							m.returnBook(b);
							returnBook.dispose();
						}
						else {
							JLabel message = new JLabel("There is no book with that ISBN");
							JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JLabel message = new JLabel("There is no account with that ID");
						JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
					}
					
				}
			});					
			returnBook.add(new JLabel("Member ID: "));
			returnBook.add(IDfield);
			returnBook.add(new JLabel("Book ISBN: "));
			returnBook.add(ISBNfield);
			returnBook.add(new JPanel());
			returnBook.add(submit);
			returnBook.setVisible(true);
			
			
			/*
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter a member ID number: ");
			String id = scan.nextLine();
			
			RegularMember m = null;
			Book b = null;
			Iterator<RegularMember> memberIterator = memberArray.iterator();
			boolean containMember = false;
			boolean containBook = false;
			
			while(memberIterator.hasNext()) {
				m = memberIterator.next();
				if(m.Id.equals(id)) {
					containMember = true;
					break;
				}
			}
			if(containMember) {
				Iterator<Book> bookIterator = m.getBooks().iterator();
				System.out.println("Enter the ISBN number of the book being returned: ");
				String isbn = scan.nextLine();
				while(bookIterator.hasNext()) {
					b = bookIterator.next();
					if(b.ISBN.equals(isbn)) {
						containBook = true;
						break;
					}
				}
				if(containBook) {
					m.returnBook(b);
				}
				else {
					System.out.println("There is no book with that ISBN");
				}
			}
			else {
				System.out.println("There is no account with that ID");
			}
			
			Library();
			*/
			
		}
	},
	
	GIVEACCESS(6, "Add access to a new online article"){
		public void choose() {
			JDialog giveAccess = new JDialog((Frame) null, "Give Access to an Online Article", true);
			giveAccess.setLayout(new GridLayout(6, 2));
			giveAccess.setSize(400, 200);
			JTextField IDfield = new JTextField();
			JTextField DOIfield = new JTextField();
			JTextField yearField = new JTextField();
			JTextField monthField = new JTextField();
			JTextField dayField = new JTextField();
			JButton submit = new JButton("Submit");
			submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					String id = IDfield.getText();
					String doi = DOIfield.getText();
					int year = 33;
					int month = 33;
					int day = 33;
					try {
						year = Integer.parseInt(yearField.getText());
						month = Integer.parseInt(monthField.getText());
						day = Integer.parseInt(dayField.getText());
					}
					catch(Exception e) {
						JLabel message = new JLabel("Year, Month and Day values have to be numbers");
						JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
					}
					Iterator<RegularMember> memberIterator = memberArray.iterator();
					Iterator<OnlineArticle> articleIterator = articleArray.iterator();
					RegularMember m = null;
					OnlineArticle a = null;
					boolean containMember = false;
					boolean containArticle = false;
					while(memberIterator.hasNext()) {
						m = memberIterator.next();
						if(m.Id.equals(id)) {
							containMember = true;
							break;
						}
					}
					if(containMember) {
						while(articleIterator.hasNext()) {
							a = articleIterator.next();
							if(a.DOI.equals(doi)) {
								containArticle = true;
								break;
							}
						}
						if(containArticle) {
							try {							
								if(Date.DateValidator(day, month, year)) {
									a.dueDate.setDate(day, month, year);
									m.addArticle(a);
									giveAccess.dispose();
								}
								else {
									throw new NotValidDateException("Exception: Invalid date: " + day
											+ "/" + month + "/" + year);
								}
							}
							catch(NotValidDateException e) {
								JLabel message = new JLabel(e.getMessage());
								JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
							}
							
						}
						else {
							JLabel message = new JLabel("There is no article with that DOI number");
							JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JLabel message = new JLabel("There is no account with that ID");
						JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
					}
				}
			});																	
			giveAccess.add(new JLabel("Member ID: "));
			giveAccess.add(IDfield);
			giveAccess.add(new JLabel("Book ISBN: "));
			giveAccess.add(DOIfield);
			giveAccess.add(new JLabel("Due Year: "));
			giveAccess.add(yearField);
			giveAccess.add(new JLabel("Due Month: "));
			giveAccess.add(monthField);
			giveAccess.add(new JLabel("Due Day: "));
			giveAccess.add(dayField);
			giveAccess.add(new JPanel());
			giveAccess.add(submit);
			giveAccess.setVisible(true);
			
			
			/*
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter a member ID number: ");
			String id = scan.nextLine();
			
			Iterator<RegularMember> memberIterator = memberArray.iterator();
			Iterator<OnlineArticle> articleIterator = articleArray.iterator();
			RegularMember m = null;
			OnlineArticle a = null;
			boolean containMember = false;
			boolean containArticle = false;
			boolean loop = true;
			
			while(memberIterator.hasNext()) {
				m = memberIterator.next();
				if(m.Id.equals(id)) {
					containMember = true;
					break;
				}
			}
			if(containMember) {
				System.out.println("Search for an article with DOI number: ");
				String doi = scan.nextLine();
				while(articleIterator.hasNext()) {
					a = articleIterator.next();
					if(a.DOI.equals(doi)) {
						containArticle = true;
						break;
					}
				}
				if(containArticle) {
					do {
						try {
							System.out.println("Enter a due year: ");
							int year = scan.nextInt();
							scan.nextLine();
							System.out.println("Enter a due month: ");
							int month = scan.nextInt();
							scan.nextLine();
							System.out.println("Enter a due day: ");
							int day = scan.nextInt();
							scan.nextLine();
							
							if(Date.DateValidator(day, month, year)) {
								a.dueDate.setDate(day, month, year);
								m.addArticle(a);
								loop = false;
							}
							else {
								throw new NotValidDateException("Exception: Invalid date: " + day
										+ "/" + month + "/" + year);
							}
							
						}
						catch(NotValidDateException e) {
							System.out.println(e.getMessage());
						}
					}while(loop);
					
				}
				else {
					System.out.println("There is no article with that DOI number");
				}
			}
			else {
				System.out.println("There is no account with that ID");
			}
			
			Library();
			*/
			
		}
	},
	
	ENDACCESS(7, "End an online article access"){
		public void choose() {
			JDialog endAccess = new JDialog((Frame) null, "End Access of an Article", true);
			endAccess.setLayout(new GridLayout(3, 2));
			endAccess.setSize(400, 200);
			JTextField IDfield = new JTextField();
			JTextField DOIfield = new JTextField();
			JButton submit = new JButton("Submit");
			submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					String id = IDfield.getText();
					String doi = DOIfield.getText();
					RegularMember m = null;
					OnlineArticle a = null;
					Iterator<RegularMember> memberIterator = memberArray.iterator();
					boolean containMember = false;
					boolean containArticle = false;
					while(memberIterator.hasNext()) {
						m = memberIterator.next();
						if(m.Id.equals(id)) {
							containMember = true;
							break;
						}
					}
					if(containMember) {
						Iterator<OnlineArticle> articleIterator = m.getArticles().iterator();
						while(articleIterator.hasNext()) {
							a = articleIterator.next();
							if(a.DOI.equals(doi)) {
								containArticle = true;
								break;
							}
						}
						if(containArticle) {
							m.endArticleAccess(a);
							endAccess.dispose();
						}
						else {
							JLabel message = new JLabel("There is no article with that DOI");
							JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JLabel message = new JLabel("There is no account with that ID");
						JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
					}								
				}
			});																		
			endAccess.add(new JLabel("Member ID: "));
			endAccess.add(IDfield);
			endAccess.add(new JLabel("Online Article DOI: "));
			endAccess.add(DOIfield);
			endAccess.add(new JPanel());
			endAccess.add(submit);
			endAccess.setVisible(true);
			
			
			/*
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter a member ID number: ");
			String id = scan.nextLine();
			
			RegularMember m = null;
			OnlineArticle a = null;
			Iterator<RegularMember> memberIterator = memberArray.iterator();
			boolean containMember = false;
			boolean containArticle = false;
			
			while(memberIterator.hasNext()) {
				m = memberIterator.next();
				if(m.Id.equals(id)) {
					containMember = true;
					break;
				}
			}
			if(containMember) {
				Iterator<OnlineArticle> articleIterator = m.getArticles().iterator();
				System.out.println("Search for an article DOI: ");
				String doi = scan.nextLine();
				while(articleIterator.hasNext()) {
					a = articleIterator.next();
					if(a.DOI.equals(doi)) {
						containArticle = true;
						break;
					}
				}
				if(containArticle) {
					m.endArticleAccess(a);
				}
				else {
					System.out.println("There is no article with that DOI");
				}
			}
			else {
				System.out.println("There is no account with that ID");
			}
			
			Library();
			*/
			
		}
	},
	
	DISPLAYACCOUNTS(8, "Display all accounts"){
		public void choose() {
			Iterator<RegularMember> memberIterator = memberArray.iterator();
			JDialog infoPage = new JDialog((Frame) null, "All Member Accounts", true);
			infoPage.setLayout(new BorderLayout());
			infoPage.setSize(400,400);
			JButton close = new JButton("Close");
			close.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					infoPage.dispose();
				}
			});
			ArrayList<String> accountInfo = new ArrayList<>();
			while(memberIterator.hasNext()) {
				RegularMember m = memberIterator.next();
				Iterator<Book> bookIterator = m.getBooks().iterator();
				Iterator<OnlineArticle> articleIterator = m.getArticles().iterator();
				m.calculateCost();
				accountInfo.add("User Name: " + m.memberName);
				accountInfo.add("User ID: " + m.Id);
				accountInfo.add("");
				accountInfo.add("Checked Out Books:");
				if(m.getBooks().isEmpty()) {
					accountInfo.add("None");
				}
				else {
					while(bookIterator.hasNext()) {
						Book b = bookIterator.next();
						accountInfo.add("Book Name: " + b.nameOfBook);
						accountInfo.add("ISBN: " + b.ISBN);
						accountInfo.add("The book titled '" + b.nameOfBook + "' has an overdue charge of " + b.fee);
						accountInfo.add("");
					}
				}
				accountInfo.add("");
				accountInfo.add("Obtained Online Articles:");
				if(m.getArticles().isEmpty()) {
					accountInfo.add("None");
				}
				else {
					while(articleIterator.hasNext()) {
						OnlineArticle a = articleIterator.next();
						accountInfo.add("Article Name: " + a.nameOfArticle);
						accountInfo.add("DOI: " + a.DOI);
						accountInfo.add("The article titled '" + a.nameOfArticle + "' has an overdue charge of " + a.fee);
						accountInfo.add("");
					}
				}
				accountInfo.add("");
				accountInfo.add("");
			}
			String[] infoArray = accountInfo.toArray(new String[0]);
			JList accountInfos = new JList(infoArray);
			infoPage.add(accountInfos);
			infoPage.add(close, BorderLayout.SOUTH);
			infoPage.setVisible(true);
			
			
			/*
			Iterator<RegularMember> memberIterator = memberArray.iterator();
			while(memberIterator.hasNext()) {
				RegularMember m = memberIterator.next();
				m.displayAccount();
				System.out.println("");
				System.out.println("");
			}
			
			Library();
			*/
			
		}
		
	},
	
	DISPLAYDATABASE(9, "Display library database"){
		public void choose() {
			JDialog database = new JDialog((Frame) null, "Library Database", true);
			database.setLayout(new BorderLayout());
			database.setSize(400,400);
			JButton close = new JButton("Close");
			close.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					database.dispose();
				}
			});
			ArrayList<String> dataList = new ArrayList<>();
			for(Book b : bookArray) {
				dataList.add("Book Name: " + b.nameOfBook);
				dataList.add("ISBN: " + b.ISBN);
				dataList.add("The cost is " + b.calculateCost());
				dataList.add("");												
			}
			for(OnlineArticle a : articleArray) {
				dataList.add("Article Name: " + a.nameOfArticle);
				dataList.add("DOI: " + a.DOI);
				dataList.add("The cost is " + a.calculateCost());
				dataList.add("");
			}
			String[] dataArray = dataList.toArray(new String[0]);
			JList data = new JList(dataArray);
			database.add(data);
			database.add(close, BorderLayout.SOUTH);
			database.setVisible(true);
			
			
			/*
			for(Book b : bookArray) {
				b.displayInfo();
				System.out.println("");
			}
			for(OnlineArticle a : articleArray) {
				a.displayInfo();
				System.out.println("");
			}
			
			Library();
			*/
			
		}
		
	},
	
	OVERDUEMEMBERS(10, "Members with overdue payments (descending order)"){
		public void choose() {
			Collections.sort(memberArray);
			Collections.reverse(memberArray);
			ArrayList<String> overdueMembers = new ArrayList<>();
			for(RegularMember member : memberArray) {
				overdueMembers.add("User Name: " + member.memberName + ", ID: " + member.Id 
						+ ", Cost: " + member.calculateCost());
			}
			overdueMembers.add("");
			
			
			String[] overdueMemberArray = overdueMembers.toArray(new String[0]);
			JList memberInfos = new JList(overdueMemberArray);
			JDialog infoPage = new JDialog((Frame) null, "Overdue Payments (Descending)", true);
			infoPage.setLayout(new BorderLayout());
			infoPage.setSize(400,400);
			JButton close = new JButton("Close");
			close.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					infoPage.dispose();
				}
			});
			infoPage.add(memberInfos);
			infoPage.add(close, BorderLayout.SOUTH);
			infoPage.setVisible(true);
			
			
			/*
			Collections.sort(memberArray);
			Collections.reverse(memberArray);
			for(RegularMember member : memberArray) {
				System.out.println("Member " + member.memberName + " with user id " + member.Id 
						+ " is due to pay " + member.calculateCost());
			}
			System.out.println("");
			
			Library();
			*/
			
		}
	},
	
	EXIT(11, "Exit"){
		public void choose() {
			
			System.exit(0);
			
			/*
			System.out.println("Goodbye!");
			*/
			
		}
	};
	
	abstract public void choose();
	
	static void Library() {
		Scanner scan = new Scanner(System.in);
		boolean chosen = false;
		System.out.println("Library Management System Menu");
		for(Menu option : Menu.values()) {
			System.out.println("(" + option.number + ") " + option.description);
		}
		int choice = scan.nextInt();
		scan.nextLine();
		for(Menu option : Menu.values()) {
			if(choice == option.number) {
				chosen = true;
				option.choose();
			}
		}
		if(!chosen) {
			System.out.println("Select a valid option from menu");
			Library();
		}
	}

	static ArrayList<RegularMember> memberArray = new ArrayList<>();
	static ArrayList<Book> bookArray = new ArrayList<>();
	static ArrayList<OnlineArticle> articleArray = new ArrayList<>();
	
	final int number;
	final String description;
	
	Menu(int number, String description){
		this.number = number;
		this.description = description;
	}
	
}

public class Main {
	
	public static void main(String[] args) {
		
		//Menu.Library();
		
		JFrame frame = new JFrame("Library Management System");
		frame.setLayout(new BorderLayout());
		ArrayList<String> listOptions = new ArrayList<>();
		for(Menu option : Menu.values()) {
			listOptions.add(option.description);
		}
		String[] optionsArray = listOptions.toArray(new String[11]);
		JList menuOptions = new JList(optionsArray);
		menuOptions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String selected = (String) menuOptions.getSelectedValue();
				if(selected != null) {
					for(Menu option : Menu.values()) {
						if(selected.equals(option.description)) {
							option.choose();
						}
					}
				}
			}
		});
		
		
 		frame.add(menuOptions);
 		frame.add(ok, BorderLayout.SOUTH);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setVisible(true);
		
	}

}
