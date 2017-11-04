/*
	TASK: combo
	LANG: C++14
	USER: bipilol2
*/
#include <iostream>
#include <vector>
#include <set>
#include <algorithm>
using namespace std;

int increase(int a, int i, int N) {
	int p = a + i;
	while (p > N)
		p -= N;
	return p;
} 

int decrease(int a, int i, int N) {
	int p = a - i;
	while (p <= 0)
		p = N + p;
	return p;
}

int interSect(int a, int b, int N) {
	vector<int> l1, l2, v;
	for (int i=1; i<=2; i++) {
		int p = decrease(a, i, N);
		l1.push_back(p);
		p = decrease(b, i, N);
		l2.push_back(p);
		p = increase(a, i, N);
		l1.push_back(p);
		p = increase(b, i, N);
		l2.push_back(p);
	}
	l1.push_back(a); 
	l2.push_back(b);

	for (int i=0; i<5; i++)
		for (int j=0; j<5; j++) {
			if (l1[i] == l2[j]) {
				bool found = false;
				for (int k=0; k<v.size(); k++) {
					if (v[k] == l1[i]) {
						found = true;
						break;
					}
				}
				if (!found) {
					v.push_back(l1[i]);
				}
			}
		}
	return v.size();
}

int main() {
	freopen("combo.in", "r", stdin);
	freopen("combo.out", "w", stdout);
	int N, a1, b1, c1, a2, b2, c2;
	cin >> N >> a1 >> b1 >> c1 >> a2 >> b2 >> c2;
	int ans;
	if (N < 5) 
		ans = N*N*N*2 - interSect(a1,a2,N)*interSect(b1,b2,N)*interSect(c1,c2,N);
	else 
		ans =  5*5*5*2 - interSect(a1,a2,N)*interSect(b1,b2,N)*interSect(c1,c2,N);
	cout << ans << endl;
}