// solution of: http://codeforces.com/contest/271/problem/D

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StringHashing {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();
		String goodness = scanner.next();
		int maxBadAllowed = scanner.nextInt();
		
		
		StringHash sHash1 = new StringHash(s, 37, (int)(1e9+7));
		StringHash sHash2 = new StringHash(s, 57, (int)(1e9+9));
		
		int[] goodInPrefix = new int[s.length()];
		goodInPrefix[0] = goodness.charAt(s.charAt(0)-'a') == '1' ? 1 : 0;
		for (int i=1; i<s.length(); i++) {
			goodInPrefix[i] = goodness.charAt(s.charAt(i)-'a') == '1' ? goodInPrefix[i-1]+1 : goodInPrefix[i-1];
		}
		
		Map<Pair, Boolean> map = new HashMap<Pair, Boolean>();
		
		for (int i=0; i<s.length(); i++) {
			for (int j=i; j<s.length(); j++) {
				int good = i == 0 ? goodInPrefix[j] : goodInPrefix[j] - goodInPrefix[i-1];
				int bad = (j-i+1) - good;
				if (bad <= maxBadAllowed) {
					Pair p = new Pair(sHash1.getHash(i, j), sHash2.getHash(i, j));
					if (!map.containsKey(p)) {
						map.put(p, true);
					}
				}
			}
		}
		
		System.out.println(map.size());
	}
}

class Pair {
	public long hash1;
	public long hash2;
	
	public Pair(long hash1, long hash2) {
		this.hash1 = hash1;
		this.hash2 = hash2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (hash1 ^ (hash1 >>> 32));
		result = prime * result + (int) (hash2 ^ (hash2 >>> 32));
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
		Pair other = (Pair) obj;
		if (hash1 != other.hash1)
			return false;
		if (hash2 != other.hash2)
			return false;
		return true;
	}
	
}

class StringHash {
	private int p, m;
	private long[] pow;
	private long[] prefixHash;
	private String s;
	
	public StringHash(String str, int p, int m) {
		this.p = p;
		this.m = m;
		this.s = str.toLowerCase();
		pow = new long[s.length()];
		pow[0] = 1;
		for (int i=1; i<s.length(); i++) {
			pow[i] = (pow[i-1]*p)%m;
		}
		prefixHash = new long[s.length()];
		prefixHash[0] = s.charAt(0)-'a' + 1;
		for (int i=1; i<s.length(); i++) {
			prefixHash[i] = ((prefixHash[i-1]*p) + (s.charAt(i) - 'a' + 1))%m;
		}
	}
	
	public final long getHash(int i, int j) {
		if (i == 0) return prefixHash[j];
		long retval = prefixHash[j] - (prefixHash[i-1]*pow[j-(i-1)])%m;
		if (retval < 0) retval += m;
		return retval;
	}
}
