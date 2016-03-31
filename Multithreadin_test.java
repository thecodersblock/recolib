package reco2;

class RunnableThread implements Runnable {
  
	Thread runner;
	int a,b,c;
	boolean uis = false,vis = false;
	public RunnableThread() {
		
	}

	public RunnableThread(String threadName) {
	
		runner = new Thread(this, threadName); // (1) Create a new thread.
		if(threadName == "U")
		{
			System.out.println("U Matrix is created");
			uis = true;
			runner.start();
		}
		
		if(threadName == "V")
		{
			System.out.println("V Matrix is created");
			vis = true;
			runner.start();
		}
		
		//System.out.println(runner.getName());
		 // (2) Start the thread.
	}

	public void run() {
	
		if(uis)
		{
			System.out.println("Doing the work of U ...");
		}
		
		if(vis)
		{
			System.out.println("Doing the work of v...");
		}
	
		//	System.out.println(Thread.currentThread());
	}
}

public class Multithreadin_test {
	
	
	public static void main(String[] args) {
		//Thread thread1 = new Thread(new RunnableThread(), "thread1");
		//Thread thread2 = new Thread(new RunnableThread(), "thread2");
		RunnableThread thread1 = new RunnableThread("U");
		RunnableThread thread2 = new RunnableThread("V");
		// Start the threads
		//thread1.start();
		//thread2.start();
		
		//System.out.println(Thread.currentThread());
	}
}
