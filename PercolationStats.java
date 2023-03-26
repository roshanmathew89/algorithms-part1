

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private int n;
	private int trials;
	private double[] averages;
	private static final double CONFIDENCE_95 = 1.96;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
    	if(n <= 0 || trials <=0) throw new IllegalArgumentException();
    	this.n=n;
    	this.trials=trials;
    	averages = new double[trials];
    	
   	   for(int i=0;i<trials;i++) {
    		
    		Percolation p = new Percolation(n);    		
    		while(!p.percolates()) {
    		   int row = StdRandom.uniformInt(1, n+1); //(int) ((n+1)*StdRandom.random());
    		   int col =StdRandom.uniformInt(1, n+1);
    		   p.open(row, col);  
    		      
    		}    		
    		double prob = ((double)p.numberOfOpenSites()/(n*n));
    		averages[i]=prob;
    		//System.out.println("open_sites:"+p.openSites+", prob"+prob);  		
    		
    	}
       
    }
    


    // sample mean of percolation threshold
    public double mean() {
    
    	return StdStats.mean(averages);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
       return StdStats.stddev(averages);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
    	
    	return mean() - ((CONFIDENCE_95*stddev())/Math.sqrt(trials));
    	
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
    	return mean() + ((CONFIDENCE_95*stddev())/Math.sqrt(trials));
    }
    


   // test client (see below)
   public static void main(String[] args) {
	   
	   int n = 0;
	   int trials = 0;
	   if(args.length == 2) {
		   n = Integer.parseInt(args[0]);
		   trials = Integer.parseInt(args[1]);
	   }else {
		   n = StdIn.readInt();
		   trials = StdIn.readInt();  
	   }	   	   
	   PercolationStats pStat = new PercolationStats(n, trials);   	
       System.out.println("mean                    = "+pStat.mean());
       System.out.println("stddev                  = "+pStat.stddev());
       System.out.println(String.format("95%% confidence interval = [%s, %s]",pStat.confidenceLo(),pStat.confidenceHi()));

	   
	   
	   
	   
   }

}