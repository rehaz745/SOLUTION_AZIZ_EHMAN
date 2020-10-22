package com.find.closest.pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

//Program to find two items that consume most of your budget.
//whose sum is less than given number x 
class Findpair { 
	
	// Function to find pairs
	// with sum less than x. 
	static void findPair(Map giftMap, double giftBudget) 
	{ 
		Double[] arr = (Double[]) giftMap.keySet().toArray(new Double[0]); 
		Arrays.sort(arr);
		int n = arr.length;
		
		int l = 0, r = n - 1; 
		double closest = 0;
		double item1 = 0, item2 = 0;
	
		while (l < r) 
		{ 
			
			// If current left and current 
			// right have sum smaller than x, 
			// the all elements from l+1 to r 
			// form a pair with current l. 
			
			if (arr[l] + arr[r] < giftBudget) 
			{ 
				if(closest < arr[l] + arr[r]) {
					closest = arr[l] + arr[r];
					item1 = arr[l];
					item2 = arr[r];
				}
			
				l++; 
			} 
	
			// Move to smaller value 
			else
				r--; 
		} 
		if(closest>0) { 
			System.out.println(giftMap.get(item1)+" , "+item1); 
			System.out.println(giftMap.get(item2)+" , "+item2); 
		}else {
			System.out.println(" No solutions"); 
		}
		
	} 
	
	// Driver method 
	public static void main(String[] args) throws IOException 
	{ 
		
		String delimiter = ",";
        Map<Double, String> map = new HashMap<>();
        try(Stream<String> lines = Files.lines(Paths.get(args[0]))){
            lines.filter(line -> line.contains(delimiter)).forEach(
                line -> map.putIfAbsent(Double.valueOf(line.split(delimiter)[1]), line.split(delimiter)[0])
            );
        }

        System.out.println(map); 
		findPair(map, Double.valueOf(args[1]));
        
	} 
} 

