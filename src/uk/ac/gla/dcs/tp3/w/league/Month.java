package uk.ac.gla.dcs.tp3.w.league;

import java.util.HashMap;
import java.util.Map;

public enum Month {
	JAN("January", 1, 31), FEB("February", 2, 28), MAR("March", 3, 31), APR(
			"April", 4, 30), MAY("May", 4, 31), JUN("June", 6, 30), JUL("July",
			7, 31), AUG("August", 8, 31), SEP("September", 9, 30), OCT(
			"October", 10, 31), NOV("November", 11, 30), DEC("December", 12, 31);

	private static final Map<Integer, Month> lookup = new HashMap<Integer, Month>();
	static {
		for (Month m : Month.values())
			lookup.put(m.getMonthNumber(), m);
	}

	private String name;
	private int monthNumber;
	private int numberOfDays;

	private Month(String s, int m, int days) {
		name = s;
		monthNumber = m;
		numberOfDays = days;
	}

	public String getName() {
		return name;
	}

	public int getMonthNumber() {
		return monthNumber;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public static int daysInMonth(int m, int year) {
		int days = lookup.get(m).getNumberOfDays();
		if (m == 2 && isLeap(year))
			days++;
		return days;
	}

	public static String getMonthName(int m) {
		return lookup.get(m).getName();
	}

	public static int getMonthNumber(String m) {
		return Month.valueOf(m).getMonthNumber();
	}

	private static boolean isLeap(int year) {
		return year % 4 == 0 && !(year % 100 == 0 && year % 400 != 0);
	}

}
