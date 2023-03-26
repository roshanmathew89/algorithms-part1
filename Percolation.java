import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private WeightedQuickUnionUF uf = null;
	private WeightedQuickUnionUF uffull = null;
    // creates n-by-n grid, with all sites initially blocked
	private boolean[][] open = null;
	private int openSites=0;
	private int n=0;
	private int virtualTop=0;
	private int virtualBottom=0;
    public Percolation(int n) {
    	if(n<=0) throw new IllegalArgumentException();
    	this.n=n;
    	open = new boolean[n][n];  
    	virtualTop=0;
    	virtualBottom=(n*n)+1;
    	uf = new WeightedQuickUnionUF((n*n)+2);
    	uffull = new WeightedQuickUnionUF((n*n)+1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    	check(row,col);
    	
    	if(!isOpen(row, col)) {    		
    		open[row-1][col-1]=true;
    		openSites++;
    		int c = convert(row,col);
    		if(row == 1) {
    			uf.union(virtualTop,c);
    			uffull.union(virtualTop, c);
    		}
    		
    		if(row == n) {
    			uf.union(virtualBottom,convert(row,col));
    		}
    		
    		
    		
    		int leftRow = row, leftCol = col-1, rightRow=row, rightCol=col+1;
    		int topRow  = row-1, topCol=col, bottomRow=row+1, bottomCol=col;
    		
    		if(isBound(leftRow, leftCol)   && isOpen(leftRow, leftCol)) {
    			 uf.union(convert(leftRow, leftCol),c);
    			 uffull.union(convert(leftRow, leftCol),c);
    		}	 
    		
    		if(isBound(rightRow, rightCol) && isOpen(rightRow, rightCol)) {
    			uf.union(convert(rightRow, rightCol),c);
    			uffull.union(convert(rightRow, rightCol),c);
    		}	
    		
    		if(isBound(topRow, topCol)     && isOpen(topRow, topCol)) { 
    			uf.union(convert(topRow, topCol),c); 
    			uffull.union(convert(topRow, topCol),c);
    		}	
    		
    		if(isBound(bottomRow, bottomCol)  && isOpen(bottomRow, bottomCol)) {
    			uf.union(convert(bottomRow, bottomCol),c); 
    			uffull.union(convert(bottomRow, bottomCol),c);
    		}	
    		
    		
    	}
    }

    private boolean isBound(int row,int col) {
    	return (row>=1 && row<=n && col>=1 && col<=n);
    }
    
    private void check(int row,int col) {
    	if(!isBound(row, col)) throw new IllegalArgumentException();
    }
    
    private int convert(int row,int col) {
       return (row-1)*n+col;	
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	check(row,col);
    	return open[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
    	check(row,col);
    	
    	return isOpen(row,col) && (uffull.find(convert(row,col)) == uffull.find(virtualTop));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
    	return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
    	return uf.find(virtualBottom) == uf.find(virtualTop);
    }

    // test client (optional)
    public static void main(String[] args) {
    	
    }
    

}