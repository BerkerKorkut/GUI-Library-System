package LibrarySystem;

public class OnlineArticle extends LibraryMaterial {

	String nameOfArticle;
	String DOI;
	String publisher;
	Date accessDate;
	Date dueDate;
	private static int articleCount = 0;
	
	OnlineArticle(String nameOfArticle, String DOI, String publisher){
		this.nameOfArticle = nameOfArticle;
		this.DOI = DOI;
		this.publisher = publisher;
		this.dueDate = new Date();
		this.accessDate = new Date();
		articleCount++;
	}
	
	@Override
	public double calculateCost() {
		if(publisher.equals("ACM")) {
			this.setCost(150);
			return 150;
		}
		else if(publisher.equals("IEEE")) {
			this.setCost(200);
			return 200;
		}
		else {
			this.setCost(100);
			return 100;
		}
	}
	
	void displayInfo() {
		System.out.println("Article Name: " + nameOfArticle);
		System.out.println("DOI: " + DOI);
		System.out.println("The cost is " + calculateCost());
	}
	
	static int getCount() {
		return articleCount;
	}
}
