//Dylan Lee 10/28/21
public class starter {
	
	public static int ind1=-1;
	public static int ind2=-1;

	public static int maxDist(String cows){
		int maxDistance = 0;
		int index1 = -1;
		int index2 = -1;
		for(int i = 0; i < cows.length(); i++){
			if(cows.substring(i, i+1).equals("1") && index1==-1 && index2==-1){
				index2 = i;
				maxDistance = index2-index1-1;
				ind1 = index1;
				ind2 = index2;
			}
			else if(cows.substring(i, i+1).equals("1")){
				index1 = index2;
				index2 = i;
				if(index2-index1-1 > maxDistance){ // the max gets updated
					maxDistance = index2-index1-1;
					ind2 = index2;
					ind1 = index1;
				}
			}
		}

		if(cows.length()-index2-1 > maxDistance){
			maxDistance = cows.length()-index2-1;
			index1 = index2;
			index2 = cows.length();
			ind2 = index2;
			ind1 = index1;
		}
		//ind1 = index1;
		//ind2 = index2;
		return maxDistance;
	}

	public static String addCows(String cows){
		maxDist(cows);
		int average = (ind1+ind2)/2;
		String newCows = "";

		for(int i = 0; i < cows.length(); i++){
			if(i != average)
				newCows+=cows.substring(i, i+1);
			else if(i == average)
				newCows+="1";
		}
		return newCows;
	}

	public static void main(String args[]) {
		String cows = "000000100010100000";
		//System.out.println(maxDist(cows));
		System.out.println(addCows(cows));
	}

}