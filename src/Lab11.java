import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

public class Lab11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] gph = TSP.ReadArrayFile("src/TSP_100.txt"," ");
		/*ArrayList<Integer> perm = PermGer(gph.length);
		System.out.println(perm);
		System.out.println(Fitness(perm, gph));
		ArrayList<Integer> perm2 = HC(20, perm,gph);
		System.out.println(perm2);
		System.out.println(Fitness(perm2, gph));*/
		ArrayList<Integer> perm1 = RRHC(1000,300000,gph);
		
		System.out.println(perm1);
		System.out.println(Fitness(perm1, gph));
		
		
	}

	static ArrayList<Integer> HC(int iter, ArrayList<Integer> perm,double gph [][])
	{
		for(int idx=0;idx<iter;idx++)
		{
			ArrayList<Integer> arr= SmallChange(perm);
			double n1 = Fitness(perm, gph);
			double n2 = Fitness(arr, gph);
			
			if(n2<n1)
				perm=arr;
		}
		
		return perm;
	}
	
	static ArrayList<Integer> RRHC(int n,int iter,double gph [][])
	{
		
		ArrayList<Integer> Best = new ArrayList<Integer>();
		for(int aux=0;aux<n;aux++)
		{
			ArrayList<Integer> perm = PermGer(gph.length);
			for(int idx=0;idx<iter;idx++)
			{
				ArrayList<Integer> arr= SmallChange(perm);
				double n1 = Fitness(perm, gph);
				double n2 = Fitness(arr, gph);
				
				if(n2<n1)
					perm=arr;
			}
			
			if(aux==0)
			{
				Best=perm;
			}
			else {
				double bst = Fitness(Best, gph);
				double n1 = Fitness(perm, gph);
				
				if(n1<bst)
					Best=perm;
			}
		}
		return Best;
	}
	
	static ArrayList<Integer> SmallChange(ArrayList<Integer> perm)
	{
		ArrayList<Integer> a = (ArrayList<Integer>)perm.clone();
		Random r = new Random();
		int i1 = r.nextInt(a.size());
		int i2 = r.nextInt(a.size());		
		int temp = a.get(i1);
		a.set(i1, a.get(i2));
		a.set(i2,temp);
		
		return a;
	}
	
	static double Fitness(ArrayList<Integer> perm,double gph [][])
	{
		int n = perm.size();
		double total=0;
		for(int idx=0;idx<n-1;idx++)
			total+=gph[perm.get(idx)][perm.get(idx+1)];
		total+=gph[perm.get(n-1)][perm.get(0)];
		return total;
	}
	
	
	
	static ArrayList<Integer> PermGer(int n)
	{
		ArrayList<Integer> narr= new ArrayList<Integer>();
		ArrayList<Integer> arr= new ArrayList<Integer>();
		for(int idx=0 ;idx<n;idx++ )
			arr.add(0);
		
		for(int idx=0;idx<n;idx++)
		{
			Random r = new Random();
			int novo = r.nextInt(n);
			
			if(arr.get(novo)==0)
			{
				arr.set(novo,1);
				narr.add(novo);
			}
			else {
				idx--;
			}
	
		}
		return narr;
	}
	
	
	
}
