package LibrarySystem;

import java.time.LocalDate;

public class Date {
	
	private int day;
	private int month;
	private int year;
	
	static boolean isLeapYear(int year) {
		if (year % 4 == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	static boolean DateValidator(int day, int month, int year) {
		
		if(month > 0 && month <13 ) {
			if(month == 1||month == 3||month == 5||month == 7||month == 8||month == 10||month == 12) {
				if(day > 0 && day <= 31) {
					return true;
				}
				else {
					return false;
				}
			}
			else if(month == 4||month == 6||month == 9||month == 11) {
				if(day > 0 && day <= 30) {
					return true;
				}
				else {
					return false;
				}
			}
			else if(month == 2) {
				if(isLeapYear(year)) {
					if(day > 0 && day <= 29) {
						return true;
					}
					else {
						return false;
					}
				}
				else if(day > 0 && day <= 28) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
			
		}
		else {
			return false;
		}
		
		
	}
	
	
	public void setDate(int day, int month, int year) {
		if(DateValidator(day, month, year)) {
			this.day = day;
			this.month = month;
			this.year = year;
		}
	}
	
	public String getDate() {
		return "" + day + "/" + month + "/" + year;
	}
	
	public LocalDate asLocalDate() {
		int year = this.year;
		int month = this.month;
		int day = this.day;
		
		return LocalDate.of(year, month, day);
		
	}
	
	
}
