import java.io.*;
import java.lang.*;

public class DetectCycleInGraph
{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while(t-- > 0)
        {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            int nov = sc.nextInt();
            int edg = sc.nextInt();
            for(int i = 0; i < nov+1; i++)
                list.add(i, new ArrayList<Integer>());
            for(int i = 1; i <= edg; i++)
            {
                int u = sc.nextInt();
                int v = sc.nextInt();
                list.get(u).add(v);
            }
            if(new DetectCycle().isCyclic(list, nov) == true)
                System.out.println("1");
            else System.out.println("0");
        }
    }
}



class DetectCycle
{
    static boolean isCyclic(ArrayList<ArrayList<Integer>> list, int V)
    {
        boolean[] dead = new boolean[V];
        boolean[] dirty = new boolean[V];
        
        for (int i=0; i<V; i++) {
            if (!dead[i]) {
                if (dfs(i, dead, dirty, list, V))
                    return true;
            }
        }
        return false;
    }
    
    static boolean dfs(int root, boolean[] dead, boolean[] dirty, ArrayList<ArrayList<Integer>> list, int V) {
        if (dirty[root]) return true; // cycle
        dirty[root] = true;
        for (int v : list.get(root)) {
            if (!dead[v]) {
                if (dfs(v, dead, dirty, list, V))
                    return true;
            }
        }
        dead[root] = true;
        return false;
    }
}
