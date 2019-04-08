/*
	USACO
	TASK: barn1
	LANG: C++14
	USER: bipilol1
*/
#include <iostream>
#include <algorithm>
using namespace std;

const int INF = 1e9+7;
int arr[203];
int dp[203][203][53];

void init(int C, int M) {
	sort(arr, arr+C);
	for (int i=0; i<=C; i++)
		for (int j=0; j<=C; j++)
			for (int k=0; k<=M; k++)
				dp[i][j][k] = -1;
}

int findAns(int i, int j, int k) {
	if (dp[i][j][k] != -1)
		return dp[i][j][k];
	if (i > j) {
		dp[i][j][k] = 0;
		return 0;
	}
	if (k == 0) {
		dp[i][j][k] = INF;
		return INF;
	} 
	int ans = INF;
	for (int l=i; l<=j; l++) 
		ans = min(ans, findAns(l+1, j, k-1) + arr[l]-arr[i]+1);
	dp[i][j][k] = ans;
	return ans;
}

int main() {
	freopen("barn1.in", "r", stdin);
	freopen("barn1.out", "w", stdout);
	int M, S, C;
	cin >> M >> S >> C;
	for (int i=0; i<C; i++)
		cin >> arr[i];
	init(C, M);
	cout << findAns(0, C-1, M) << endl;
}
