#include <bits/stdc++.h>
using namespace std;
 
long long solve(long long y, long long x) {
 
    if (y > x) {
 
        if (y%2 == 0) {
            return y*y - (x - 1);
        }
        else {
            return (y-1)*(y-1) + x;
        }
 
    }
 
    else {
        if (x%2 == 0) {
            return (x-1)*(x-1) + y;
        }
        else {
            return x*x - (y-1);
        }
    }
}
 
int main() {
 
    int t;
    cin >> t;
    
    while (t--) {
 
        long long y, x;
        cin >> y >> x;
 
        cout << solve(y, x) << endl;
    }
}