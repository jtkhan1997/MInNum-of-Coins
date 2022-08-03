//Jalal Khan
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Project3 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = null;
		try {
			scan = new Scanner(new File("input.txt"));
		} catch(FileNotFoundException e) {
			System.out.println("Not found");
			System.exit(1);
		}
		int numCoins = scan.nextInt();
		int[] coinVal = new int[numCoins];
		for(int i = 0; i < numCoins; i ++) {
			coinVal[i] = scan.nextInt();
		}
		int target = scan.nextInt();
		int cashOut = cashiersAlg(numCoins, coinVal, target);
		System.out.println(cashOut);
		int dynamicOut = dynAlg(numCoins, coinVal, target);
		System.out.println(dynamicOut);
	}

	private static int dynAlg(int numCoins, int[] coinVal, int target) {
		// TODO Auto-generated method stub
		int counter = 0;
		//create a matrix for dynamic program
		//make target+1 to account for 0 column
		int[][] minCoin = new int[numCoins][target+1];
		//base cases
		//MinCoin[i][0] = 0
		if(target == 0) {
			return 0;
		}
		for(int i = 0; i < numCoins; i++) {
			minCoin[i][0] = 0;
		}
		for(int i = 0; i < target+1; i++) {
			minCoin[0][i] = i;
		}
		
		for(int i = 1; i < numCoins; i ++) {
			//System.out.println();
			for(int j = 1; j < target+1; j++) {
				if(j >= coinVal[i]) {
					minCoin[i][j] = min((1+ minCoin[i][j-coinVal[i]]), (minCoin[i-1][j]));
				} else {
					minCoin[i][j] = minCoin[i-1][j];
				}
				//System.out.print(minCoin[i][j] + " " );
			}
		}
		//System.out.println(minCoin[numCoins-1][target]);
		counter = minCoin[numCoins-1][target];
		return counter;
	}

	private static int min(int i, int j) {
		if( i <= j) {
			return i;
		} else return j;
	}

	private static int cashiersAlg(int numCoins, int[] coinVal, int target) {
		// TODO Auto-generated method stub
		int counter  = 0;
		//pretty simple, keeps checking if highest coin is within target, and if its not
		//reduce numCoins to get next highest coin value
		while(target != 0) {
			if(coinVal[numCoins-1] <= target) {
				target -= coinVal[numCoins-1];
				counter++;
			} else {
				numCoins--;
			}
		}
		return counter;
	}

}
