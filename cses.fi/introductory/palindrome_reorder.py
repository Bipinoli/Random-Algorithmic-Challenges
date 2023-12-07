def run(s):
    chars = {}
    types = []
    for c in s:
        if c not in chars:
            chars[c] = 0
            types.append(c)
        chars[c] += 1

    odds_exist = False
    ans = ""
    for t in types:
        if chars[t] % 2 == 0:
            ans = (t * (int(chars[t] / 2))) + ans + (t * (int(chars[t] / 2)))
        else:
            if odds_exist:
                return "NO SOLUTION"
            else:
                odds_exist = True
                half = ans[ : int(len(ans) / 2)]
                ans = half + t * chars[t] + half[::-1]
    return ans
        



s = input()
print(run(s))