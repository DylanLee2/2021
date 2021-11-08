//
import java.io.*;
import java.util.StringTokenizer;
public class starter {
	
	public static long n;
	public static long sum;
	public static BufferedReader br;
	public static StringTokenizer st;

	public static void missingNumber() throws IOException{
		st = new StringTokenizer(br.readLine());
		sum = (n*(n+1))/2; // adds all numbers within n
		for(int i = 0; i < n-1; i++){
			sum-=Long.parseLong(st.nextToken());
		}
		n = sum;
		System.out.println(n);

		/*
		boolean[] hasNum = new boolean[startingNumber-1];

		int missingNum = 0;
		for(int i = 0; i < otherNumbers.length; i++){
			if(i < startingNumber)
				hasNum[i] = true;
		}
		for(int i = 0; i < otherNumbers.length; i++){
			if(!hasNum[i]){
				missingNum = otherNumbers[i];
				break;
			}
		}
		return missingNum;
		*/
	}

	public static void main(String args[]) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Long.parseLong(br.readLine());
		missingNumber();

	}

}