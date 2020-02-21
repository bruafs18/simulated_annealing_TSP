import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*double[][] gph = TSP.ReadArrayFile("src/TSP_100.txt"," ");
		ArrayList<Integer> perm = PermGer(gph.length);
		System.out.println(perm);
		System.out.println(Fitness(perm, gph));
		ArrayList<Integer> perm2 = HC(20, perm,gph);
		System.out.println(perm2);
		System.out.println(Fitness(perm2, gph));
		ArrayList<Integer> perm1 = RRHC(1000,300000,gph);
		
		System.out.println(perm1);
		System.out.println(Fitness(perm1, gph));
		*/
		double[][] gph = TSP.ReadArrayFile("src/TSP_100.txt"," ");
		SA Run = new SA(gph);
			ArrayList<Integer> perm1 =Run.SimmulatedAnnealing(gph.length, 1000000, 20, 5.5);
			
		System.out.println(perm1);
		System.out.println(Run.fitness(perm1));
	}
}