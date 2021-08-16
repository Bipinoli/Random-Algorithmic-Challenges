'''
 Problem: Kth Ancestor of a Tree Node (Leetcode)
 https://leetcode.com/problems/kth-ancestor-of-a-tree-node/
'''

import math

class TreeAncestor:
    def __init__(self, n: int, parent: List[int]):
        self.n = n
        self.parent = parent
        self.initSparseTable()
        
    def initSparseTable(self):
        height = int(math.log(self.n, 2)) + 1
        self.spTable = [ [-1 for j in range(height) ] for i in range(self.n) ]
        for j in range(height):
            for i in range(self.n):
                if j == 0:
                    self.spTable[i][j] = self.parent[i]
                    continue
                if self.spTable[i][j-1] == -1:
                    self.spTable[i][j] = -1
                else:
                    self.spTable[i][j] = self.spTable[ self.spTable[i][j-1] ][j-1]
            

    def getKthAncestor(self, node: int, k: int) -> int:
        if k >= self.n:
            return -1
        i = 0
        parent = node
        while k > 0 and parent != -1:
            bit = k & 1
            if bit == 1:
                parent = self.spTable[parent][i]
            k = k >> 1
            i = i + 1
        return parent
            


# Your TreeAncestor object will be instantiated and called as such:
# obj = TreeAncestor(n, parent)
# param_1 = obj.getKthAncestor(node,k)
