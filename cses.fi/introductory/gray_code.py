
def solve(n):
    if n == 1: 
        return ["1", "0"]

    if n == 2:
        return ["00", "01", "11", "10"]
    
    retval = []

    sub_solution = solve(n-1)

    for b in sub_solution:
        retval.append("0" + b)
    for b in reversed(sub_solution):
        retval.append("1" + b)

    return retval



n = int(input()) 
for s in solve(n):
    print(s)


    
