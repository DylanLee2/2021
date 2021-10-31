// Dylan Lee 10/30/21
public class starter {

	public static int weirdAlgorithm(int n){
		if(n==1) // base case
			return n;
		System.out.print(n + " ");
		if(n%2 == 0) // n is even
			return weirdAlgorithm(n/2);
		return weirdAlgorithm((n*3)+1); // n is odd
	}

	public static void main(String args[]) {
		int n = 3;
		System.out.println(weirdAlgorithm(n));
	}
// 5 minutes to make
}