package uk.ac.gla.dcs.tp3.w.parser;

public class Date {

	private int day;
	private int month;
	private int year;

	public Date() {
		day = 1;
		month = 1;
		year = 2012;
	}

	public Date(int d, int m, int y) {
		if (!validDate(d, m, y)) {
			System.out.println("Erroneous date.");
			return;
		}
		day = d;
		month = m;
		year = y;
	}

	public int getDay() {
		return day;
	}

	public void setDate(int d) {
		day = d;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int m) {
		month = m;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int y) {
		year = y;
	}

	public boolean before(Date date) {
		if (year < date.getYear())
			return true;
		else if (year == date.getYear())
			if (month < date.getMonth())
				return true;
			else if (month == date.getMonth())
				if (day < date.getDay())
					return true;
		return false;
	}

	private static boolean validDate(int d, int m, int y) {
		if (m < 1 || m > 12 || y < 1 || d < 1)
			return false;
		int numberOfDays = Month.daysInMonth(m, y);
		if (d > numberOfDays)
			return false;
		return true;
	}

	public static void main(String[] args) {
		Date d1 = new Date(1, 1, 2011);
		Date d2 = new Date(1, 1, 2011);
		System.out.println(d1.before(d2));
		System.out.println(Month.getMonthName(2));
		String month = "Feb";
		System.out.println(Month.getMonthNumber(month.toUpperCase()));
		System.out.println(Month.daysInMonth(2, 2010));
	}

}
