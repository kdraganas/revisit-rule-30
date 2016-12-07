import java.util.*;

public class RuleT30{
	public static final int THREAD_COUNT = 10;
	public static long startTime;
    public static long endTime;
	public static long nonThreadedDuration;

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        grid(size);
	}

	public static int rule(int a, int b, int c){
		if((a==1 && b==1 && c==1) || (a==1 && b==1 && c==0) || (a==1 && b==0 && c==1) || (a==0 && b==0 && c==0)) return 0;
		else return 1;
	}

	public static void grid(int i){
		startTime = System.currentTimeMillis();

		int[] cell = new int[i];
		int seed = 1;
		
		for(int a = 0; a < i; a++) 
			cell[a] = 0;
			
		cell[i/2] = seed;
		for(int a = 0; a < i; a++)
			System.out.print(cell[a]);
		System.out.println();
			
		for(int c = 1; c < i ; c++){
			int[] gens = new int[i];
			for(int x = 0; x < i; x++){
				if(x==0){
					gens[x] = rule(0,cell[x],cell[x+1]);
				}
				else if(x==i-1){
					gens[x] = rule(cell[x-1],cell[x],0);
				}
				else{
					gens[x] = rule(cell[x-1],cell[x],cell[x+1]);
				}
			}
			cell = gens;	
			for(int d = 0; d < i; d++)
				System.out.print(cell[d]);
			System.out.println();	
		}
		endTime = System.currentTimeMillis();
		nonThreadedDuration = (endTime-startTime);
		System.out.println("TIME CONSUMED(sequential processing): " + nonThreadedDuration + "ms");

	}
}