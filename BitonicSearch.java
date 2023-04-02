
public class BitonicSearch {
	
	public static void main(String[] args) {
	   int[] a = new int[] {1,2,3,4,5,6,8,45,67,89,23,21,18,17,5,2,0};	   
	   int find=23;
	   int index=BitonicSearch.findValueInBiotonicArray(a, find);
       System.out.println(String.format("Found %s in index :%s",find,index));
		
	}

	public static int findValueInBiotonicArray(int[] a, int find) {
		int findMid=-1;
		int lo=0;
		int hi=a.length-1;
		int bitonicMid=-1;
		while(lo<=hi) {
			bitonicMid= lo + (hi-lo)/2;
			int mv = a[bitonicMid];
			int mlv= a[bitonicMid-1];
			int mrv= a[bitonicMid+1];
			
			if(mv>mlv && mv> mrv) {
				break;
			}else if(mv>mlv && mv<mrv) {
				lo=bitonicMid+1;
			}else {
				hi=bitonicMid-1;
			}
			
		}

		int mv = a[bitonicMid];
		boolean found=false;
		if(a[0] <= find && find <= mv) {
			lo=0;
			hi=bitonicMid;
			
			
			while(lo<=hi) {
				findMid= lo + (hi-lo)/2;
				mv = a[findMid];

				
				if(mv==find) {found=true;
					break;
				}else if(mv>find) {
					lo=findMid+1;
				}else {
					hi=findMid-1;
				}				
			}	
		}
		
		if(!found && a[bitonicMid+1] >= find && find >= a[a.length-1]) {
			lo=a.length-1;
			hi=bitonicMid;
			
			
			while(lo>=hi) {
				findMid= lo + (hi-lo)/2;
				mv = a[findMid];

				
				if(mv==find) {found=true;
					break;
				}else if(mv>find) {
					hi=findMid+1;
				}else {
					lo=findMid-1;
				}				
			}		
		}
		
        return found?findMid:-1;        
	
	}
}
