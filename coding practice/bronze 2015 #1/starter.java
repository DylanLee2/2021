// Dylan Lee 10/30/21
public class starter {
	
	public static int paintedDistance(int a, int b, int c, int d){
		// a < b and c < d meaning either a or c is smallest and b or d is biggest
		// distance will be the biggest minus smallest
		int smallestX = 0;
		int biggestX = 0;

		if(a < c)
			smallestX = a;
		else if(a > c);
			smallestX = c;
		// smallestX = (a<c)?a : (a>c)?c : 0;
		if(b < d)
			biggestX = d;
		else if(b > d)
			biggestX = b;
		// biggestX = (b<d)?d : (b>d)?b : 0;

		int distance = biggestX - smallestX;
		return distance;
	}

	public static void main(String args[]) {
		// test case
		int a = 7;
		int b = 10;
		int c = 4;
		int d = 8;
		System.out.println(paintedDistance(a,b,c,d));
		// returns 6
	}
// 8 minutes to solve
}