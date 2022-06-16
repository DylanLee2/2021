public class starter {
			
	public static void main(String args[]) {
		int[] a=new int[]{1,2,3,4,5};
		int k=12;
		System.out.println(addsUp(a,k));
	}

	public static boolean addsUp(int[] a, int k){
		for(int i:a)
			for(int j:a)
				if(i+j==k) return true;
		return false;
	}

}