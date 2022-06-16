import java.util.Arrays;

// dylan lee 4/19/22
public class starter {
			
	public static void main(String args[]) {
		int[] a = new int[]{1,2,3,4,5};
		int[] b = new int[a.length];
		Arrays.fill(b,1);

		for(int i=0; i<a.length; i++)
			for(int j=0; j<a.length; j++)
				b[i] = (j!=i)?b[i]*a[j] : b[i];
		System.out.println(Arrays.toString(b));
	}

}