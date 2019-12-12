import java.util.ArrayList;
import java.util.Scanner;

public class SparseTable {
	public static void main(String[] args) {
		Integer[] array = new Integer[] {1, 3, 2, 5, 8, 7, 3, 4, 0};
		SPTable<Integer> sparseTree = new SPTable<Integer>(9, array, new RangeMinimum());
		sparseTree.displayTable();
		
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.print("range query: [l,r] = ");
			int l = scanner.nextInt();
			int r = scanner.nextInt();
			System.out.println("" + sparseTree.rangeQuery(l, r));
		}
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
		
		// constructing Sparse Tree
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
	

	
	public void displayTable() {
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


class RangeMinimum implements RangeComparator<Integer> {
	public Integer compare(Integer a, Integer b) {
		return Math.min(a, b);
	}
}

class RangeSum implements RangeComparator<Integer> {
	public Integer compare(Integer a, Integer b) {
		return a + b;
	}
}
