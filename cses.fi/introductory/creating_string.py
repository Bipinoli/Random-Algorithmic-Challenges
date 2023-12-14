s = list(input())
n = len(s)


def permute(n, lst):
    if n == 1:
        return lst
    retval = []
    for (i, v) in enumerate(lst):
        sub_sol = permute(n-1, lst[:i] + lst[i+1:])
        for x in sub_sol:
            retval.append(v + x)
    return retval


ans = sorted(set(permute(n, s)))
print(len(ans))
for a in ans:
    print(a)