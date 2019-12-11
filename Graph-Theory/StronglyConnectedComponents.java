import java.util.*;
import java.lang.*;
import java.io.*;

public class StronglyConnectedComponents
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		while(t-- > 0) {
			ArrayList<ArrayList<Integer>> list = new ArrayList<>();
			
			int nov = sc.nextInt();
			int edg = sc.nextInt();
			
			for (int i=0; i<nov+1; i++) 
				list.add(i, new ArrayList<Integer>());
				
			for (int i=1; i<=edg; i++) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				
				list.get(u).add(v);
			}
			
			System.out.println(new SCC().numOfSCC(list, nov+1));
		}
	}
}



class SCC
{
    
    public int numOfSCC(ArrayList<ArrayList<Integer>> list, int N)
    {
        int[] seenOrder = new int[N];
        int[] minFound = new int[N];
        boolean[] dead = new boolean[N];
        for (int i=0; i<N; i++)
            seenOrder[i] = -1;
        
        Integer seenCount = 0;
        
        for (int i=0; i<N; i++) {
            if (seenOrder[i] == -1) {
                dfs(i, list, N, seenOrder, minFound, seenCount, dead);
            }
        }
        
        int retval = 0;
        for (int i=0; i<N-1; i++) {
            if (seenOrder[i] == minFound[i])
                retval++;
        }
        return retval;
    }
    
    private int dfs(int root, ArrayList<ArrayList<Integer>> list, int N, int[] seenOrder, int[] minFound, Integer seenCount, boolean[] dead) {
        if (seenOrder[root] != -1) {
           return seenOrder[root];     
        }
        seenOrder[root] = ++seenCount;
        minFound[root] = seenOrder[root];
        for (int v: list.get(root)) {
            if (!dead[v])
                minFound[root] = Math.min(minFound[root], dfs(v, list, N, seenOrder, minFound, seenCount, dead));
        }
        dead[root] = true;
        return minFound[root];
    }
}
