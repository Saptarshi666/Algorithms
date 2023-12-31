public class Program3 {
	 static int[][] a;
	    static int[][] sum;
		//static int max = Integer.MIN_VALUE;
		static int[] copysect;
	
		
	    public static int maxFoodCount (int[] sections) {
	        // Implement your dynamic programming algorithm here
	        // You may use helper functions as needed
	        //return 0;
	        a = new int [sections.length][sections.length];
	        sum = new int[sections.length][sections.length];
	        copysect = sections.clone();
	        for(int i = 0; i<sections.length; i++)
	        {
	        	for(int j = 0; j<sections.length; j++)
	        		sum[i][j] = -1;
	        }
	        for(int i = 0; i<sections.length; i++)
	        {
	        	for (int j = 0; j<sections.length; j++)
	        	{
	        		if(i==j)
	        			sum[i][j] = sections[i];
	        		else if(i<j)
	        			sum[i][j] = sum[i][j-1] + sections[j];
	        		else if(i>j)
	        			sum[i][j] = 0;
	        	}
	        }
	        for(int i = 0; i<sections.length; i++)
	        {
	        	for(int j = 0; j<sections.length; j++)
	        		a[i][j] = -1;
	        }
	      return  solve(0,sections.length-1);
	    
	       
	    }
	    static int solve(int start, int end)
	    {
	    	int max = Integer.MIN_VALUE;
	    	if(start == end)
	    	{
	    		a[start][end] = 0;
	    		return a[start][end];
	    	}
	    	if(a[start][end] != -1)
	    		return a[start][end];
	    	for(int i = 0; i<end-start; i++)
	    	{
	    		int temp1 = solve(start,start+i) + sum[start][start+i];
	    		int temp2 = solve(start+i + 1, end) + sum[start+i+1][end];
	    		int min = -10;
	    		if(temp1<temp2)
	    			min = temp1;
	    		else
	    			min = temp2;
	    		if(min > max)
	    		{
	    			max = min;
	    			a[start][end] = max;
	    			
	    		}
	    	}
	    	
	    	    	return a[start][end];
	    		
	    	}
	    	
	   
}
