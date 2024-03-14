# reference: https://cp-algorithms.com/data_structures/segment_tree.html

class SegTree:
    def __init__(self, lst, merge_func):
        self.merge_func = merge_func
        pass

    def update(self, index, value):
        pass

    def query(self, l, r):
        pass


if __name__ == "__main__":
    lst = [1, 3, -2, 8, 3, 4, 23]

    def sum_merge_func(a, b):
        return a + b

    def min_merge_func(a, b):
        return a if a < b else b

    print("TEST -- range sum")
    tree = SegTree(lst, sum_merge_func)
    assert tree.query(0, 3) == 2
    assert tree.query(2, 5) == 13
    tree.update(2, 0)
    assert tree.query(2, 3) == 0
    assert tree.query(1, 4) == 14
    print("test passed!\n")

    print("TEST -- range min")
    tree = SegTree(lst, min_merge_func)
    assert tree.query(0, 4) == -2
    assert tree.query(4, 6) == 3
    tree.update(5, -100)
    assert tree.query(2, 6) == -100
    print("test passed!\n")

    print("All tests passed!")
