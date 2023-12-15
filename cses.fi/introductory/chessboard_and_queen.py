n = 8
FREE, RESERVED, INVALID = 0, 1, 8



def place_queen(x, row, board):
    direction = [(-1,1), (0,1), (1,1)]
    invalids = []
    for d in direction:
        cx, cy = x, row
        while cx >= 0 and cy >= 0 and cx < n and cy < n:
            if board[cy][cx] == FREE:
                board[cy][cx] = INVALID
                invalids.append((cy, cx))
            cx += d[0]
            cy += d[1]
    return list(set(invalids))




def solve(row, q, board):
    '''
        Putting queen row by row
        row = current row
        q = queens left
    '''
    if row == n:
        if q > 0:
            return 0
        else: 
            return 1
    
    ans = 0
    for x in range(n):
        if board[row][x] == FREE:
            invalids = place_queen(x, row, board)
            ans += solve(row+1, q-1, board)
            for pos in invalids:
                r, c = pos
                board[r][c] = FREE

    return ans


    
    
board = []
for i in range(n):
    board.append([])
    for c in input():
        if c == ".":
            board[i].append(FREE)
        else:
            board[i].append(RESERVED)



print(solve(0, n, board))