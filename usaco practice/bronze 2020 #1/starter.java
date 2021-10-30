//Dylan Lee 10/29/21
public class starter {
	
	public static void abc(int[] nums){
		int[] abc = new int[3];
		// method: sort nums
		// a < b < c < a+b+c
		// a would be sorted[0], b is sorted[1], c is sorted[sorted.length-1]-b-a
		int[] sorted = nums;
		for(int i = 0; i < nums.length; i++){
			for(int j = i+1; j < nums.length; j++){
				if(sorted[i]>sorted[j]){
					int swap = sorted[i];
					sorted[i] = sorted[j];
					sorted[j] = swap;
				}
			}
		}
		abc[0] = sorted[0]; // a
		abc[1] = sorted[1]; // b
		abc[2] = sorted[sorted.length-1]-abc[1]-abc[0]; // c

		//print abc
		for(int i : abc)
			System.out.print(i + " ");
	}
	public static void main(String args[]) {
		int[] nums = {2,2,11,4,9,7,9};
		// outputs 2 2 7
		//         a b c
		abc(nums);
	}

}