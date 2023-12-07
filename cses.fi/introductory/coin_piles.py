def F(a, b):
    if b < a:
        a, b = b, a
    if (a + b) % 3 != 0:
        return False
    if b > 2 * a:
        return False
    return True


t = int(input())
for _ in range(t):
    a, b = [int(i) for i in input().split(" ")]
    if F(a, b):
        print("YES")
    else:
        print("NO")
