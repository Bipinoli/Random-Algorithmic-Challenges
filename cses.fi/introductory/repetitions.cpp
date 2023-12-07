#include <bits/stdc++.h>
using namespace std;
 
int main() {
 
    string s;
    cin >> s;
 
    int ans = 1;
 
    int cur = 0;
    char curChar = s[0];
    for (int i=0; i<s.size(); i++) {
 
        if (s[i] == curChar)
            cur += 1;
 
        else if (s[i] != curChar) {
            cur = 1;
            curChar = s[i];
        }
 
        ans = max(ans, cur);
    }
 
    cout << ans << endl;
}
