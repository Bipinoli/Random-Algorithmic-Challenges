import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BridgeInGraph {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		
		while (t-- > 0) {
			
			int N, M;
			// N = num of vertices
			// M = num of edges
			
			N = scanner.nextInt();
			M = scanner.nextInt();
			
			ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
			for (int i=0; i<N; i++) {
				graph.add(new ArrayList<Integer>());
			}
			
			for (int i=0; i<M; i++) {
				int u, v;
				u = scanner.nextInt();
				v = scanner.nextInt();
				graph.get(u).add(v);
				graph.get(v).add(u);
			}
			
			System.out.print("Bridges found: [" );
			for (Edge e: BridgeFinder.findBridges(graph, N)) {
				System.out.print("[" + e.u + "," + e.v + "],");
			}
			System.out.println("]");
			
			
			/*
			 * Tests
			 * 
			  7
			  2 1
			  0 1
			  3 3
			  0 1 1 2 2 0
			  4 4
			  0 1 1 2 2 0 2 3
			  6 7
			  0 1 1 2 2 0 2 3 3 4 4 5 5 3
			  5 6
			  0 1 1 2 2 0 2 3 3 4 4 2
			  5 4
			  0 2 1 2 2 3 2 4
			  6 6
			  0 1 0 2 1 3 2 3 3 4 3 5
			  
			  Answers
			  [[0,1]]
			  []
			  [[2,3]]
			  [[2,3]]
			  []
			  [[0,2],[1,2],[2,3],[2,4]]
			  [[3,4],[3,5]]
			  
			 */
			
			
		}	
	}
}



class BridgeFinder {
	
	public static List<Edge> findBridges(ArrayList<ArrayList<Integer>> graph, int N) {
		Map<Edge, Boolean> visitedEdge = new HashMap<Edge, Boolean>();
		
		int[] seenOrder = new int[N];
		int[] minFound = new int[N];
		for (int i=0; i<N; i++)
			seenOrder[i] = -1;
		
		Integer seenCount = 0;
		
		for (int i=0; i<N; i++) {
			if (seenOrder[i] == -1) {
				dfs(i, graph, N, visitedEdge, seenOrder, minFound, seenCount);
			}
		}
		
		ArrayList<Edge> retval = new ArrayList<Edge>();
		
		for (Edge e: visitedEdge.keySet()) {
			if (seenOrder[e.u] >= seenOrder[e.v]) {
				int temp = e.u;
				e.u = e.v;
				e.v = temp;
			}
			if (minFound[e.v] > seenOrder[e.u]) {
				retval.add(e);
			}
		}
		
		return retval;
	}
	
	
	
	public static int dfs(int root, ArrayList<ArrayList<Integer>> graph, int N, Map<Edge, Boolean> visitedEdge, int[] seenOrder, int[] minFound, Integer seenCount) {
		if (seenOrder[root] != -1) 
			return seenOrder[root];
		seenOrder[root] = ++seenCount;
		minFound[root] = seenOrder[root];
		
		for (int v: graph.get(root)) {
			if (!visitedEdge.containsKey(new Edge(root, v)) && !visitedEdge.containsKey(new Edge(v, root))) {
				visitedEdge.put(new Edge(root,v), true);
				minFound[root] = Math.min(minFound[root], dfs(v, graph, N, visitedEdge, seenOrder, minFound, seenCount));
			}
		}
		
		return minFound[root];
	}
}




class Edge {
	
	public int u, v;
	public Edge(int u, int v) {
		this.u = u;
		this.v = v;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + u;
		result = prime * result + v;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (u != other.u)
			return false;
		if (v != other.v)
			return false;
		return true;
	}
}
