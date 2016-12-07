import java.util.Scanner;

public class Rule30Threaded{
	public static final int THREAD_COUNT = 10;
	public static long startTime;
    public static long endTime;
	public static long threadedDuration;

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        execute(size);
	}

	public static void execute(int size){
		Rule30[] workers = new Rule30[THREAD_COUNT];
		int seed = 1;
		int tc;

		if(size < THREAD_COUNT){
			tc = size;
		}
		else{
			tc = THREAD_COUNT;
		}

		startTime = System.currentTimeMillis();

		int intPerThread = size/tc;
		int[] cell = new int[size];
		cell[size/2] = seed;
	
		for(int x=0; x<size; x++){
			int start = 0;
			int end = start + intPerThread - 1;
			for(int y=0; y < tc; y++){
				if(end> size)
					end = size - 1;
				workers[y] = new Rule30(cell, start, end);
				start = end + 1;
				end = start + intPerThread - 1;
			}

			workers[0].output(cell);

			for(int z=0; z < tc; z++){
				workers[z].start();
			}

			for(int a=0; a < tc; a++){
				while (workers[a].isAlive()) {
                    try {
                        workers[a].join();
                    } catch (InterruptedException e) {
                        System.err.println("thread interrupted: " + e.getMessage());
                    }
                }
			}

			for(int b=0; b < tc; b++){
				workers[b].update(cell);
			}
		}
		endTime = System.currentTimeMillis();
		threadedDuration = (endTime-startTime);
		System.out.println("TIME CONSUMED(multithreading): " + threadedDuration + "ms");
	}
}