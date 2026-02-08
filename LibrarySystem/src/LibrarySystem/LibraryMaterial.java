package LibrarySystem;

public abstract class LibraryMaterial implements LibraryData {

	private double cost;
	double fee = 0;
	
	double getCost() {
		return cost;
	}
	void setCost(double cost){
		this.cost = cost;
	}
	
}
