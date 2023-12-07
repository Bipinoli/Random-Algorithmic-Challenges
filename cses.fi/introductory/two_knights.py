'''
Idea:
for (k - 4) * (k - 4) inner board, the knight has 8 attacking squares
for knights on the strip outside we need to calculate individually
'''

def F(k):
    return ((k**2 - 9) * (k - 4)**2 + 4 * (k**2 - 3) + 4 * (k**2 - 4) + 4 * (k - 4) * (k**2 - 5) + 4 * (k**2 - 4) + 4 * (k **2 - 5) + 4 * (k - 4) * (k**2 - 7)) / 2

k = int(input())

for i in range(1, k+1):
    print(int(F(i)))
