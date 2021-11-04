package bubble;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
//import java.util.ListIterator;
//import java.util.Iterator;


public class bubbleSort {
	public static ArrayList<Integer> getNums () {
		ArrayList<Integer> num  = new ArrayList<>();
		try {
			File file = new File("src\\bubble\\nums");
			Scanner scan = new Scanner(file);
			while (scan.hasNext()) {
				num.add(scan.nextInt());
			}
		}
		catch (Exception e) {
			System.out.println("File does not exist");
		}
		return num;
	}
	public static void main (String[] args) {
		ArrayList<Integer> nums = getNums();
		System.out.println(nums);
		Scanner scan = new Scanner(System.in);
		System.out.println("b for bubble, i for insert, q for quick, m for merge, any for none.");
		String choo = scan.next();
		if (choo.equals("b")) {
			Bubble(nums);
		}
		if (choo.equals("i")) {
			Insert(nums);
		}
		if (choo.equals("q")); {
			Quick(nums, 0, nums.size()-1);
		}
		if (choo.equals("m")) {
			Merge(nums);
		}
	}
	public static void Bubble (ArrayList<Integer> nums) {
		int temp = 0;
		int comp = 1;
		while (comp != 0) {
			comp = 0;
			for (int i = 1; i<nums.size(); i++) {
				if (nums.get(i)<nums.get(i-1)) {
					temp = nums.get(i);
					nums.set(i, nums.get(i-1));
					nums.set(i-1, temp);
					comp++;
					System.out.println(nums);
				}
			}
		}
		System.out.println(nums+"   FINAL");
	}
	public static void Insert (ArrayList<Integer> nums) {

		int temp = 0;
		int atind = -1;
		ArrayList<Integer> newnum = new ArrayList<>();
		newnum.add(nums.get(0));
		for (int i = 1; i<nums.size(); i++) {
			atind = newnum.size();
			for (int j = newnum.size()-1; j>=0; j--) {
				if (nums.get(i)<newnum.get(j)) {
					atind = j;
				}
			}
			if (atind == -1) {
				newnum.add(nums.get(i));
			}
			else {
				newnum.add(atind,nums.get(i));
			}
			System.out.println(newnum);
		}
		System.out.print(newnum+"     FINAL");
	}
	public static void Quick (ArrayList<Integer> nums, int start, int end) {
		ArrayList<Integer> arr = nums;
		int pivind = 0;
		int temp = 0;
		int s = start;
		int e = end;
		if (end-start>1) {
			while (start<end) {
				int piv = arr.get(end-1);
				while (arr.get(start)<piv) {
					start ++;
					//System.out.println(arr.get(start));
				}
				while (arr.get(end)>piv) {
					end --;
					//System.out.println(arr.get(end));
				}
				temp = arr.get(end);
				arr.set(end, arr.get(start));
				arr.set(start, temp);
				pivind = start;
				start ++;
				end --;
			}
			System.out.println(nums);
			Quick(nums, s, pivind);
			Quick(nums, pivind+1, e);
		}
	}
	public static void Merge (ArrayList<Integer> nums) {
		ArrayList<ArrayList<Integer>> ree = new ArrayList<>();
		for (int i = 0; i<nums.size(); i++) {
			ArrayList<Integer> temp = new ArrayList<>(0);
			temp.add(nums.get(i));
			ree.add(temp);
		}
		sortMer(ree);
//		if (size>1) {
//			int mid = size/2;
//			ArrayList<Integer> Left = new ArrayList<>();
//			for (int i = 0; i<mid; i++) {
//				Left.add(nums.get(i));
//			}
//			ArrayList<Integer> Right = new ArrayList<>();
//			for (int i = mid; i<size; i++) {
//				Right.add(nums.get(i));
//			}
//			Merge(Left, Left.size());
//			Merge(Right, Right.size());
//		}
//		else {
//			//System.out.print(nums.get(0)+",");
//		}
	}
	public static void sortMer(ArrayList<ArrayList<Integer>> ree) {
		if (ree.size() > 1) {
			for (int i = 0; i<ree.size(); i++) {
				System.out.println(ree);
				int front = 0;
				int back = 0;
				if (ree.size() > 1) {
					ArrayList<Integer> temp = new ArrayList<>(0);
					//compare 2 parts of the array and combine
					while (front<ree.get(i).size() || back<ree.get(i+1).size()) {
						if (front<ree.get(i).size()) {
							if (ree.get(i).get(front)<ree.get(i+1).get(back)) {
								temp.add(ree.get(i).get(front));
								front++;
							}
							else if (ree.get(i+1).size() == 0) {
								for (int j = front; j<ree.get(i).size(); j++) {
									temp.add(ree.get(i).get(j));
								}
							}
						}
						else {
							temp.add(ree.get(i+1).get(back));
							back++;
						}
					}
					ree.set(i, temp);
				}
				ree.remove(i+1);
				System.out.println(ree);
			}
			System.out.println(ree);
			sortMer(ree);
		}
		else {
			System.out.println(ree);
		}
	}
}
