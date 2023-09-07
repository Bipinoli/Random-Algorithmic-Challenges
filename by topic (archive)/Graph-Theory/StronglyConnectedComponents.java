import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class StronglyConnectedComponents
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		while(t-- > 0) {
			ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
			
			int nov = sc.nextInt();
			int edg = sc.nextInt();
			
			for (int i=0; i<nov+1; i++) 
				list.add(i, new ArrayList<Integer>());
				
			for (int i=1; i<=edg; i++) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				
				list.get(u).add(v);
			}
			
			System.out.print("SCC #: ");
			System.out.println(SCC.numOfSCC(list, nov+1));
			for (ArrayList<Integer> lst: SCC.getSCC(list, nov+1)) {
				for (int i: lst) {
					System.out.print("" + i + ",");
				}
				System.out.println();
			}
		}
	}
}



class SCC
{
    
	public static ArrayList<ArrayList<Integer>> getSCC(ArrayList<ArrayList<Integer>> list, int N) {
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
        
        ArrayList<ArrayList<Integer>> retval = new ArrayList<ArrayList<Integer>>();
        Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        for (int i=0; i<N-1; i++) {
        	int key = minFound[i];
        	if (!map.containsKey(key)) map.put(key, new ArrayList<Integer>());
        	map.get(key).add(i);
        }
        for (Integer key: map.keySet()) {
        	retval.add(map.get(key));
        }
        return retval;
	}
	
	
    public static int numOfSCC(ArrayList<ArrayList<Integer>> list, int N)
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


    private static int dfs(int root, ArrayList<ArrayList<Integer>> list, int N, int[] seenOrder, int[] minFound, Integer seenCount, boolean[] dead) {
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

    
    private static ArrayList<Integer> trace(int root, ArrayList<ArrayList<Integer>> list, int N) {
    	ArrayList<Integer> reachable = new ArrayList<Integer>();
    	Queue<Integer> q = new LinkedList<Integer>();
    	
    	boolean[] seen = new boolean[N];
    	q.add(root);
    	
    	while (!q.isEmpty()) {
    		int rt = q.remove();
    		seen[rt] = true;
    		reachable.add(rt);
    		
    		for (int v: list.get(rt)) {
    			if (!seen[v])
    				q.add(v);
    		}	
    	}
    	
    	return reachable;
    }
}

