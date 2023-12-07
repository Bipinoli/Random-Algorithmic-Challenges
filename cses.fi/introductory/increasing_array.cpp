#include <bits/stdc++.h>
using namespace std;
 
int main() {
 
    int n;
    cin >> n;
 
    long long ans = 0;
    
    int prev;
    cin >> prev;
 
    for (int i=1; i<n; i++) {
        int a; 
        cin >> a;
        if (a < prev)
            ans += prev - a;
        else 
            prev = a;
    }
 
    cout << ans << endl;
}
