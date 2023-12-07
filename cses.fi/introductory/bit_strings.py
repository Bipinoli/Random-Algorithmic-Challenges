'''
Idea: 
how many numbers can be encoded by the given number of bits?
- ans: 2 ** n

'''

def F(n):
    return int( (2**n) % 1000000007 )

n = int(input())
print(F(n))