// segment tree problem: https://codeforces.com/contest/863/problem/E

import java.util.*;

public class SegmentTreeProblem {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		Map<Integer, Boolean> exists = new HashMap<Integer, Boolean>();
		ArrayList<ArrayList<Integer>> ranges = new ArrayList<ArrayList<Integer>>();
		for (int i=0; i<n; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			if (!exists.containsKey(a)) {
				exists.put(a, true);
				list.add(a);
			}
			if (!exists.containsKey(b)) {
				exists.put(b, true);
				list.add(b);
			}
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(a);
			temp.add(b);
			ranges.add(temp);
		}
		
		SegmentTree segTree = new SegmentTree(list);
		for (int i=0; i<ranges.size(); i++) {
			int l = ranges.get(i).get(0);
			int r = ranges.get(i).get(1);
			segTree.update1Query(l, r);
		}
		
//		segTree.displayTree();
		
		int ans = -1;
		for (int i=0; i<ranges.size(); i++) {
			int l = ranges.get(i).get(0);
			int r = ranges.get(i).get(1);
			int min = segTree.getMinQuery(l, r);
			if (min > 1) {
				ans = i+1;
				break;
			}
		}
		System.out.println(ans);
	}
}



class SegmentTree {
	private ArrayList<Integer> list;
	private ArrayList<Boolean> rangeNode;
	private Tree tree;
	
	public SegmentTree(ArrayList<Integer> list) {
		Collections.sort(list);
		this.list = new ArrayList<Integer>();
		this.rangeNode = new ArrayList<Boolean>();
		this.list.add(list.get(0));
		this.rangeNode.add(false);
		for (int i=1; i<list.size(); i++) {
			if (list.get(i) - list.get(i-1) > 1) {
				this.list.add(list.get(i-1)+1);
				this.rangeNode.add(true);
			}
			this.list.add(list.get(i));
			this.rangeNode.add(false);
		}
		this.buildTree();
	}

	
	public void update1Query(int l, int r) {
		this.update1(this.tree, l, r);
	}
	
	
	public int getMinQuery(int l, int r) {
		return getMin(tree, l, r);
	}
	
	
	private int getMin(Tree root, int l, int r) {
		if (l < root.l)
			l = root.l;
		if (r > root.r)
			r = root.r;
		
		if (r < root.l || root.r < l)
			return Integer.MAX_VALUE;
		
		int retval = 0;
		if (root.l <= l && r <= root.r)
			retval += root.value;
		
		if (root.left == null)
			return retval;
		
		return retval + Math.min(getMin(root.left, l, r), getMin(root.right, l, r));
	}
	
	
	private void update1(Tree root, int l, int r) {
		if (r < root.l || l > root.r) 
			return;
		if (l <= root.l && root.r <= r) {
			root.value += 1;
			return;
		}
		if (root.left != null) 
			update1(root.left, l, r);
		if (root.right != null)
			update1(root.right, l, r);
	}
	
	
	private void buildTree() {
		this.tree = new Tree();
		int posL = 0;
		int posR = this.list.size()-1;
		this.buildSubTree(tree, posL, posR);
	}
	
	private void buildSubTree(Tree root, int posL, int posR) {
		root.l = this.list.get(posL);
		root.r = this.list.get(posR);
		root.value = 0;
		if (posL == posR) {
			return;
		}
		root.left = new Tree();
		root.right = new Tree();
		int mid = (posL + posR) / 2;
		buildSubTree(root.left, posL, mid);
		buildSubTree(root.right, mid+1, posR);
	}
	
	public void displayTree() {
		Queue<Tree> q = new LinkedList<Tree>();
		q.add(this.tree);
		while (!q.isEmpty()) {
			Tree root = q.remove();
			System.out.println("[" + root.l + "," + root.r + "] => " + root.value);
			if (root.left != null) 
				q.add(root.left);
			if (root.right != null)
				q.add(root.right);
		}
	}
}


class Tree {
	public int value;
	public int l, r;
	public Tree left, right;
}
