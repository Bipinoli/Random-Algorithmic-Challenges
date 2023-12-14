n = 8
free, reserved, queen = 0, 1, 8
board = []
for i in range(n):
    board.append([])
    for c in input():
        if c == ".":
            board[i].append(free)
        else:
            board[i].append(reserved)


def is_valid(x, y, state):
    if state[y][x] != free:
        return False
    direction = [(0,1), (0,-1), (1,0), (0,1), (-1,-1), (1,1), (-1,1),(1,-1)]
    for d in direction:
        cx, cy = x, y
        while cx >= 0 and cy >= 0 and cx < n and cy < n:
            if state[cy][cx] == queen:
                return False
            cx += d[0]
            cy += d[1]
    return True


def display():
    for i in range(n):
        for j in range(n):
            print(board[i][j], end="")
        print()




