package com.corelogic.ks;

/*
 * Given a knapsack of capacity C, what is the optimal way to fill it with n items.
 * 
 * a. C - capacity of the knapsack
 * b. 1 <= i <= n - items
 * c. Each item i, has a value of vi and size of si
 * d. j < C - smaller capacity/slots
 * 
 * Strategy:
 * Decompose the above problem in such way that you can find solutions to the smaller/sub-problems.
 * In this case, that would be to find out how to fill a knapsack of capacity j, where j < C.
 * 
 * M(j) = max[M(j-1), max[M(j-si) + vi]]
 * 
 * M(j-1)
 * Assume you are not filling the "j" slot in the knapsack
 * Find the optimal way to fill j-1 slots (recursion)
 * 
 * M(j-si) + vi
 * Assume you are filling the "j" slot in the knapsack with one of the i items
 * Find the optimal way to fill j-si slots, where si is the space taken up by
 * the ith item.
 * You also need to add the value of the ith item - vi
 * Note, you also don't know which item i, will produce the optimal choice, so you maximize over i
 * 
 * The solution will further take the max of these two terms.
 * 
 */

public class IntegerKnapsack {
	
	private static int[] items = {1,2,3};
	private static int[] values = {10,20,30};
	private static int[] space = {1,1,1};
	private static int capacity = 3;
	
	public static void main(String[]  args) {
		int maximumValue = IntegerKnapsack.computeJ(capacity);
		System.out.println("Maximum Value: " + maximumValue);
	}
	
	public static int computeJ(int j) {
		System.out.println("Compute(" + j + ")");
		if (j < 2) {
			return 0;
		}
		int v1 = computeJ(j-1);
		int v2 = computeMaximumAcrossItems(j);
		return Math.max(v1,v2);
	}
	
	public static int computeMaximumAcrossItems(int j) {
		int maximumValue = 0;
		for(int i = 0; i < items.length; i++) {
			System.out.println("i:" + i);
			int value  = computeJ(j - space[i]) + values[i];
			if (value > maximumValue) {
				maximumValue = value;
			}
		}
		return maximumValue;
	}
}