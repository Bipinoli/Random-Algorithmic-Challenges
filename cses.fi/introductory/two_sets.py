'''
Idea:
f(n) = sum(1..n) = (n * (n+1)) / 2
If the set is divisible in 2 equal sums then F(n) = f(n) / 2 should be an integer
To obtain that value we start choosing the maximum we can choose
It is always possible to obtain the sum that way as we have 1, 2, 3, .. to fill whatever is left.
i.e we have a lot of flexibility 
'''

def F(n):
    return (n * (n + 1)) / 4

def possible(n):
    return F(n) == int(F(n))

def print_lst(lst):
    print(len(lst))
    for i in lst:
        print(i, end=" ")
    print()

def run():
    n = int(input())

    if not possible(n):
        print("NO")
        return
    
    sum = int(F(n))
    lst1 = []
    lst2 = []

    for k in range(n, 0, -1):
        if sum >= k:
            sum -= k
            lst1.append(k)
        else:
            lst2.append(k)

    print("YES")
    print_lst(lst1)
    print_lst(lst2)



run()

