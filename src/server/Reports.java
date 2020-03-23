package server;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles creating reports which include total revenue for that day,
 * the most popular menu item, hours worked by each employee, and total tips earned
 * in that one day.
 * 
 * @author Karimshan Nawaz
 *
 */
public class Reports {
	
	public static double totalTips = 0;
	public static double totalRevenue = 0;
	public static String mostPopularMenuItem = null;
	public static Map<Integer, Integer> hoursWorked = new HashMap<Integer, Integer>();
	
	
}
