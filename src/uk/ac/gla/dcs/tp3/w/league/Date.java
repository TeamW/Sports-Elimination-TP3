package uk.ac.gla.dcs.tp3.w.league;

/**
 * A simple date class that supports leap years and conversions between month
 * name and number of days in the month.
 * 
 * @author Team W
 * @version 1.0
 */
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

	/**
	 * Does as it says on the tin. Returns true if the date is before the passed
	 * in date, false otherwise. Assumes both dates are valid.
	 * 
	 * @param date
	 *            Date being tested against this date.
	 * @return Returns true if this date is before given date, false otherwise.
	 */
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

	/**
	 * A simple method which takes in integer representation of the date
	 * elements and returns a boolean representing whether or not the date is
	 * valid. Supports leap years.
	 * 
	 * @param d
	 *            Day of date being tested
	 * @param m
	 *            Month of date being tested
	 * @param y
	 *            Year of date being tested
	 * @return True if date is valid, false otherwise.
	 */
	private static boolean validDate(int d, int m, int y) {
		if (m < 1 || m > 12 || y < 1 || d < 1)
			return false;
		int numberOfDays = Month.daysInMonth(m, y);
		if (d > numberOfDays)
			return false;
		return true;
	}

	public String toString() {
		return String.format("%d/%d/%d", day, month, year);
	}

}
