# reference: https://cp-algorithms.com/data_structures/segment_tree.html

class SegTree:
    def __init__(self, lst, merge_func, padding_value):
        self.tree = [-1] * 2 * self.binary_container(len(lst))
        self.merge_func = merge_func
        self.padding_value = padding_value
        self.lst = lst
        self.build_tree()

    def binary_container(self, n):
        bits = 0
        while n > 0:
            bits += 1
            n >>= 1
        if 2**(bits - 1) == n:
            return n
        return 2**bits

    def build_tree(self):
        # tree[1] = root of the tree
        half = len(self.tree)//2
        i = half
        for item in self.lst:
            self.tree[i] = item
            i += 1
        while i < len(self.tree):
            self.tree[i] = self.padding_value
            i += 1
        for i in range(half-1, 0, -1):
            left = self.tree[2*i]
            right = self.tree[2*i + 1]
            self.tree[i] = self.merge_func(left, right)

    def update(self, index, value):
        half = len(self.tree)//2
        index = half + index
        self.tree[index] = value
        nd = index // 2
        while True:
            left = self.tree[2*nd]
            right = self.tree[2*nd + 1]
            self.tree[nd] = self.merge_func(left, right)
            if nd == 1:
                break
            nd = nd // 2

    def query(self, l, r, bl=None, br=None, nd=1):
        if l >= r:
            return self.padding_value
        if bl is None:
            bl = 0
        if br is None:
            br = len(self.tree) // 2
        if l == bl and r == br:
            return self.tree[nd]
        md = (bl + br) // 2
        left = self.query(l, min(r, md), bl, md, 2*nd)
        right = self.query(max(l, md), r, md, br, 2*nd+1)
        return self.merge_func(left, right)


if __name__ == "__main__":
    lst = [1, 3, -2, 8, 3, 4, 23]

    def sum_merge_func(a, b):
        return a + b

    def min_merge_func(a, b):
        return a if a < b else b

    print("TEST -- range sum")
    tree = SegTree(lst, sum_merge_func, padding_value=0)
    assert tree.query(0, 3) == 2
    assert tree.query(2, 5) == 9
    tree.update(2, 0)
    assert tree.query(2, 3) == 0
    assert tree.query(1, 4) == 11
    print("test passed!\n")

    print("TEST -- range min")
    tree = SegTree(lst, min_merge_func, padding_value=float('inf'))
    assert tree.query(0, 4) == -2
    assert tree.query(4, 6) == 3
    tree.update(5, -100)
    assert tree.query(2, 6) == -100
    print("test passed!\n")

    print("All tests passed!")
