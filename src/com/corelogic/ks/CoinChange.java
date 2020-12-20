package com.corelogic.ks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


/*
 * Coin Change problem is similar to the the Integer Knapsack problem in that, you
 * make the space si in the knapsack problem the vi (value/denomination of coin) from
 * the coin change problem.  You further negate (-1) the value vi in the knapsack problem.
 * This will force you to take as few items as possible (min instead of max).
 * 
 * C - total amount for which change is to be made
 * 1 <= i <= n - coin values/denominations
 * vi - value of ith denomination
 * 
 *
 * M(j) = Min-over-i[M(j-vi) + 1]
 * 
 * where M(j) is the minimum number of coins required to make change for amount j (< C).
 * 
 * Notes:
 * 
 * a. M(j-vi) is the recursive call to compute the number of coins required to make change
 *    for a value j-vi
 * b. 1 is the ith coin that will make up the value j
 * c. Since you don't know which i will be the optimal denomination choice, you do a min
 * 
 */

public class CoinChange {

	private static float[] values = {1.0f,2.0f,3.0f};
	private static float total = 4.0f;
	public static HashMap<String, Integer> coinTracker = 
							new HashMap<String,Integer>();
	
	public static void main(String[]  args) {
		coinTracker.put("1.0",new Integer(0));
		coinTracker.put("2.0",new Integer(0));
		coinTracker.put("3.0",new Integer(0));
		// coinTracker.put("0.25",new Integer(0));
		int numCoins = computeJMinCoins(total);
		System.out.println("numCoins: " + numCoins);
		
		Set<String> keys = coinTracker.keySet();
		Iterator<String> iter = keys.iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			Integer coinsByDenomination = coinTracker.get(key);
			System.out.println("Denomination:" + key + " - " + "Count:" + coinsByDenomination);
		}
		
	}
	
	public static int computeJMinCoins(float aTotal) {
		
		if (aTotal == 0.0f) {
			return 0;
		}
		
		int n1 = 0;
		int n2 = 0;
		float denomination = 0;
		int i = 0;
		for(i=0;i<values.length;i++) {
			n2 = 0;
			denomination = values[i];
			if (aTotal >= denomination) {
				n2 = computeJMinCoins(aTotal - denomination) + 1;
			}
			if (i==0) {
				n1 = n2;
				keepCount(denomination,n1);
			} else if (n2 != 0 && n2 < n1) {
				n1 = n2;
				keepCount(denomination,n1);
			}
		}
		return n1;
	}
	
	public static void keepCount(float denomination,int count) {
		coinTracker.put(String.valueOf(denomination),new Integer(count));
	}
	
	public static boolean isInitial(float aTotal) {
		if (aTotal == total) {
			return true;
		}
		return false;
	}
}