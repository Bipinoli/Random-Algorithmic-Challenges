import java.io.*;
import java.lang.*;

public class TopologicalSorting
{
    public static void main (String[] args)throws IOException 
    {
        // check if topological sort is working properly
        
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        
        while(t-- > 0)
        {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            String st[] = read.readLine().trim().split("\\s+");
            int edg = Integer.parseInt(st[0]);
            int nov = Integer.parseInt(st[1]);

            for(int i = 0; i < nov+1; i++)
                list.add(i, new ArrayList<Integer>());
                
            String s[] = read.readLine().trim().split("\\s+");
            int p = 0;
            for(int i = 1; i <= edg; i++)
            {    int u = Integer.parseInt(s[p++]);
                 int v = Integer.parseInt(s[p++]);
                 list.get(u).add(v);
                
            }
            
            
            int res[] = new int[10001];
             res = new TopologicalSort().topoSort(list, nov);
            boolean valid = true;
            
            for(int i = 0; i < nov; i++)
            {
                int n = list.get(res[i]).size();
                for(int j = 0; j < list.get(res[i]).size(); j++)
                {
                    for(int k = i+1; k < nov; k++)
                    {
                        if(res[k]==list.get(res[i]).get(j))
                            n--;
                    }
                }
                if(n!=0)
                {
                    valid = false;
                    break;
                }
            }
            if(valid == true)
                System.out.println("0");
            else
                System.out.println("1");
		}
    }
}



class TopologicalSort
{
    static int[] topoSort(ArrayList<ArrayList<Integer>> list, int N)
    {
        ArrayList<ArrayList<Integer>> rList = new ArrayList<>();
        for (int i=0; i<N; i++)
            rList.add(new ArrayList<Integer>());
        for (int i=0; i<N; i++) {
            for (int v: list.get(i)) {
                rList.get(v).add(i);
            }
        }
        
        ArrayList<Integer> topSorted = new ArrayList<>();
        boolean[] seen = new boolean[N];
        for (int i=0; i<N; i++) {
            if (!seen[i])
                dfs(i, list, N, seen, topSorted);
        }
        
        int[] retval = new int[N];
        for (int i=0; i<N; i++)
            retval[i] = topSorted.get(i);
            
        return retval;
    }
    
    
    
    private static void dfs(int root, ArrayList<ArrayList<Integer>> list, int N, boolean[] seen, ArrayList<Integer> topSorted) {
        if (seen[root]) return;
        seen[root] = true;
        
        for (int v: list.get(root)) {
            if (!seen[v])
                dfs(v, list, N, seen, topSorted);
        }
        
        topSorted.add(root);
    }
}
