import java.util.ArrayList;
import java.util.Scanner;

public class LeastCommonAncestor {
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> tree = new ArrayList<ArrayList<Integer>>();
		int n = 10;
		for (int i=0; i<n; i++) {
			tree.add(new ArrayList<Integer>());
		}
		// root = 1
		// edges:
		// [(1,2),(1,3),(1,4),(2,5),(2,6),(4,7),(4,8),(6,9),(6,10)]
		tree.get(0).add(1);
		tree.get(1).add(0);
		tree.get(0).add(2);
		tree.get(2).add(0);
		tree.get(0).add(3);
		tree.get(3).add(0);
		tree.get(1).add(4);
		tree.get(4).add(1);
		tree.get(1).add(5);
		tree.get(5).add(1);
		tree.get(3).add(6);
		tree.get(6).add(3);
		tree.get(3).add(7);
		tree.get(7).add(3);
		tree.get(5).add(8);
		tree.get(8).add(5);
		tree.get(5).add(9);
		tree.get(9).add(5);
		
		LCA lca = new LCA(tree, 0);
		lca.display();
		
		Scanner scanner = new Scanner(System.in);
		
		
		while(true) {
			System.out.println("\nEnter u and v for [u,v] to find least common ancestor betn those nodes");
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			System.out.println("lca: " + lca.leastAncestor(u, v));
		}
	}
}


class LCA {
	private ArrayList<ArrayList<Integer>> tree;
	private ArrayList<Integer> height;
	private ArrayList<Integer> seenOrder;
	private SPTable<Pair> sparseTable;
	private int[] firstSeen;
	
	public LCA(ArrayList<ArrayList<Integer>> tree, int root) {
		this.tree = tree;
		this.height = new ArrayList<Integer>();
		this.seenOrder = new ArrayList<Integer>();
		this.firstSeen = new int[tree.size()];
		
		dfs(0, -1, -1);
		
		Pair[] pairs = new Pair[height.size()];
		for (int i=0; i<height.size(); i++) {
			pairs[i] = new Pair(height.get(i), seenOrder.get(i));
		}
		sparseTable = new SPTable<Pair>(pairs.length, pairs, new RangeMinimum());
	}
	
	public int leastAncestor(int u, int v) {
		int i = firstSeen[u];
		int j = firstSeen[v];
		
		if (i > j) {
			int temp = i;
			i = j;
			j = temp;
		}
		
		return sparseTable.rangeQuery(i, j).seenOrder;
	}
	
	
	private void dfs(int root, int parent, int parentHeight) {
		// in-order traversal
		
		firstSeen[root] = seenOrder.size();
		seenOrder.add(root);
		height.add(parentHeight+1);
		
		for (int child: tree.get(root)) {
			if (child != parent) {
				dfs(child, root, parentHeight+1);
				
				seenOrder.add(root);
				height.add(parentHeight+1);
			}
		}
	}
	
	
	public void display() {
		System.out.print("seen order: ");
		for (int v: seenOrder)
			System.out.print(v + " ");
		
		System.out.print("\nheight: ");
		for (int v: height)
			System.out.print(v + " ");
		
		System.out.print("\nfirst Seen: ");
		for (int v: firstSeen)
			System.out.print(v + " ");
	}
}




class SPTable<T> {
	private int n;
	private int depth;
	private ArrayList<ArrayList<T>> tree = new ArrayList<ArrayList<T>>();
	private RangeComparator<T> comparator;
	
	public SPTable(int n, T[] array, RangeComparator<T> comparator) {
		this.comparator = comparator;
		this.n = n;
		this.depth = (int) log2(n) + 1;
		
		// constructing Sparse Table
		for (int d=0; d<this.depth; d++) {
			tree.add(new ArrayList<T>());
			if (d == 0) {
				for (int i=0; i<n; i++)
					tree.get(d).add(array[i]);
				continue;
			}
			for (int i=0; (i + (int)Math.pow(2, d-1)) < tree.get(d-1).size(); i++) {
				T a = tree.get(d-1).get(i);
				T b = tree.get(d-1).get(i + (int)Math.pow(2, d-1));
				tree.get(d).add(this.comparator.compare(a, b));
			}
		}
	}
	
	
	
	
	public T rangeQuery(int l, int r) {
		// [l,r] scope
		int gap = r-l + 1;
		int pow = 0;
		T retval = this.tree.get(0).get(l);
		while (gap > 0) {
			if ((gap & 1) == 1) {
				int depth = pow;
				retval = this.comparator.compare(retval, this.tree.get(depth).get(l));
				l += Math.pow(2, pow);
			}
			gap >>= 1;
			pow += 1;
		}
		return retval;
	}
	

	
	public void displayTree() {
		for (int i=0; i<this.depth; i++) {
			for (T a: this.tree.get(i))
				System.out.print("" + a + ", ");
			System.out.println();
		}
	}
	
	
	private double log2(int a) {
		return Math.log10(a) / Math.log10(2);
	}
}


interface RangeComparator<T> {
	public T compare(T a, T b);
}


class Pair {
	public int height, seenOrder;
	public Pair(int height, int seenOrder) {
		this.height = height;
		this.seenOrder = seenOrder;
	}
}


class RangeMinimum implements RangeComparator<Pair> {
	public Pair compare(Pair a, Pair b) {
		if (a.height < b.height)
			return a;
		return b;
	}
}
