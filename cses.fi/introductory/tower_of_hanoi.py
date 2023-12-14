def hanoi(n, src, mid, dest):
    if n == 1:
        return [(src, dest)]
    return hanoi(n-1, src, dest, mid) + [(src, dest)] + hanoi(n-1, mid, src, dest)
    

n = int(input())
solution = hanoi(n, 1, 2, 3)
print(len(solution))
for s in solution:
    print(f"{s[0]} {s[1]}")
    