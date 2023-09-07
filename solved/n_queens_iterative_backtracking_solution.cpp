// Backtracking solution to 
// N queens problem

#include <bits/stdc++.h>
using namespace std;

bool valid(int r, int c, int board[]) {

    // dr , dc gives direction
    // offset gives length in that direction
    for (int dr = -1; dr <= 1; dr++) {
        for (int dc = -1; dc <= 1; dc++) {
            for (int offset = 1; offset < 8; offset++) {
                if (dr == 0 && dc == 0) continue;
                int ro = r + dr*offset;
                int col = c + dc*offset;
                if (ro >= 0 && ro < 8 && col >=0 && col < 8) {
                    if (board[ro] & (1 << (7-col))) {
                        return false;
                    }
                }
            }
        }
    }
    return true;
}

int main() {

    int board[8];
    // board represented as 8 integers
    // each integer represents a row 
    // bit 1 in row means the queen is at that position
    // eg: 2 = 00000010 -> queen is at second position for right

    for (int i=0; i<8; i++)
        board[i] = 0;

       
    // backtracking solution
    stack<pair<int,int>> stk;

    int q_count = 8;

    stk.push({0,0});
    board[0] = 1 << 7;
    q_count--;
    while (q_count) {

        int i = stk.top().first;

        bool found = false;
        for (int j=0; j<8; j++) {
            if (valid(i+1, j, board)) {
                stk.push({i+1, j});
                board[i+1] = 1 << (7-j);
                q_count--;
                found = true;
            }
        }

        if (!found) {
            // backtrack

            while (stk.size()) {
                int i = stk.top().first;
                int j = stk.top().second;
                stk.pop();
                board[i] = 0;
                q_count++;
                bool valid_found = false;
                for (int new_j = j+1; new_j < 8; new_j++) {
                    if (valid(i, new_j, board)) {
                        stk.push({i, new_j});
                        board[i] = 1 << (7 - new_j);
                        q_count--;
                        valid_found = true;
                        break;
                    }
                }
                if (valid_found)
                    break;
            }
        }
    }


    // display the solution
    for (int i=0; i<8; i++) {
        for (int j=0; j<8; j++) {
            if (board[i] & (1 << (7-j)) )
                cout << "q ";
            else
                cout << ". "; 
        }       
        cout << endl;
    }
}

/*** OUTPUT ****
q . . . . . . .
. . . . q . . .
. . . . . . . q
. . . . . q . .
. . q . . . . .
. . . . . . q .
. q . . . . . .
. . . q . . . .

***************/
