// Dylan Lee 10/30/21
import java.io.*;
import java.util.*;
public class starter {

	public static long n;
	public static BufferedReader br;
	public static StringBuilder sb;

	public static void weirdAlgorithm(){
		while(n != 1){
			if(n%2 == 0)
				n/=2;
			else
				n=(n*3)+1;
			sb.append(" ").append(n);
		}
	}

	public static void main(String args[]) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Long.parseLong(br.readLine());
		sb = new StringBuilder();
		sb.append(n);
		weirdAlgorithm();
		System.out.print(sb.toString());
	}
// 5 minutes to make
}