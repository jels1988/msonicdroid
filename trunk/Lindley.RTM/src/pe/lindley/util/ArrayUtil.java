package pe.lindley.util;

public  class ArrayUtil {

	public static double round(double d, int c) {
		/*double p = (double)Math.pow(10,Rpl);
		  Rval = Rval * p;
		  double tmp = Math.round(Rval);
		  return (double)tmp/p;*/
		
		int temp=(int)((d*Math.pow(10,c)));
		return (((double)temp)/Math.pow(10,c));
		
		
		  }
	
	public static double getMaxValue(double[] numbers){  
		
		if(numbers.length<=0) return 0;
		
		double maxValue = numbers[0];  
		  
		  for (double d : numbers) {
			  if(d > maxValue){  
			      maxValue = d;  
			    }  
		  }
		  return maxValue;  
		}  
		  
		public static double getMinValue(double[] numbers){ 
		 if(numbers.length<=0) return 0;
		  double minValue = numbers[0];  
		  
		  for (double d : numbers) {
			  if(d < minValue){  
			      minValue = d;  
			    }  
		  	}
		  return minValue;  
		} 
		
		
		public static int getMaxValue(int[] numbers){  
			
			if(numbers.length<=0) return 0;
			
			int maxValue = numbers[0];  
			  
			  for (int d : numbers) {
				  if(d > maxValue){  
				      maxValue = d;  
				    }  
			  }
			  return maxValue;  
			}  
			  
			public static int getMinValue(int[] numbers){ 
			 if(numbers.length<=0) return 0;
			  int minValue = numbers[0];  
			  
			  for (int d : numbers) {
				  if(d < minValue){  
				      minValue = d;  
				    }  
			  	}
			  return minValue;  
			} 
}
