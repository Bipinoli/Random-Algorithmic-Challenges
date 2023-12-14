n = int(input())
lst = [int(i) for i in input().split(' ')]


def solve(n, left, right, lst):
    if n == 0:
        return abs(left - right)
    lf = solve(n-1, left + lst[0], right, lst[1:])
    rt = solve(n-1, left, right + lst[0], lst[1:])
    return min(lf, rt)


print(solve(n, 0, 0, lst))