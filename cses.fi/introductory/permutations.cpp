#include <bits/stdc++.h>
using namespace std;
 
string solve(int n) {
    if (n == 1)
        return "1";
    if (n == 2 || n == 3)
        return "NO SOLUTION";
    
    vector<string> ret1, ret2;
    int i;
    for (i=1; i<=n/2; i++) 
        ret1.push_back(to_string(i));
    for ( ; i<=n; i++)
        ret2.push_back(to_string(i));
 
 
    string retval;
    for (int i=0; i<ret1.size(); i++) {
        retval += ret2[i];
        retval += " ";
        retval += ret1[i];
        retval += " ";
    }
 
    if (n%2 != 0)
        retval += ret2[ret2.size()-1];
 
    return retval;
}
 
int main() {
 
    int n;
    cin >> n;
 
    cout << solve(n) << endl;
}