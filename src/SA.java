import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

public class SA {
	double[][] dist;
	
	public SA(double[][] arr )
	{
		dist=arr;
	}
	public ArrayList<Integer> SimmulatedAnnealing(int n,double Iter,double T0, double TIter)
	{
		double T=T0;
		ArrayList<Integer> X = RandPerm(n);
		double fitX= fitness(X);
		for(int idx=0;idx<Iter;idx++)
		{
			ArrayList<Integer> NX = Swap(X);
			double fitNX= fitness(NX);
			if(fitNX<fitX || Probability(fitNX,fitX,T))
			{
				X=NX;
				fitX=fitNX;
			}
			T*=CoolingRate(T0,TIter,Iter);
		}
		
		
		return X;
	}
	
	double CoolingRate(double T0,double TIter, double Iter)
	{
		return Math.pow(TIter/T0, 1/Iter);
	}
	
	public boolean Probability(double fitNX, double fitX, double T)
	{
		double deltaDiff=fitX-fitNX;
		if(deltaDiff>0)
			deltaDiff*=-1;
		double prob = Math.exp(deltaDiff/T);
		prob*=100;
		double num = RandomNum(10000)/100.0;
		
		return num<prob;
	}
	
	public ArrayList<Integer> Swap(ArrayList<Integer> arr)
	{
		ArrayList<Integer> narr = (ArrayList<Integer>) arr.clone();
		int i=0,j=0;
		
		while(j==i)
		{
			i=RandomNum(arr.size());
			j=RandomNum(arr.size());
		}
		
		Integer Temp = narr.get(i);
		narr.set(i, narr.get(j));
		narr.set(j, Temp);
		return narr;
	}
	
	public double fitness(ArrayList<Integer> Perm)
	{
		double total=0;
		for(int idx=0;idx<Perm.size()-1;idx++)
			total+=dist[Perm.get(idx)][Perm.get(idx+1)];
		
		total+=dist[Perm.get(0)][Perm.get(Perm.size()-1)];
		return total;
	}
	
	public ArrayList<Integer> RandPerm(int n)
	{
		Integer[] bol = new Integer[n];
		for(int idx=0;idx<n;idx++)
			bol[idx]=0;
		int count=0;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		boolean p=false;
		int idx =0;
		while(count!=n)
		{
			if(!p)
				idx = RandomNum(n);
			
			if(bol[idx]==0)
			{
				bol[idx]=1;
				count++;
				arr.add(idx);
				p=false;
				
			}
			else
			{
				p=true;
				idx++;
				idx%=n;
			}
			
		}
		return arr;
		
	}
	
	public Integer[] OneToN (int n)
	{
		Integer[] arr = new Integer[n];
		for(int idx=0;idx<=n;idx++)
			arr[idx]=idx+1;
		return arr;
		
	}
	
	public int RandomNum(int n)
	{
		Random r = new Random();
		return r.nextInt(n);
	}
}
