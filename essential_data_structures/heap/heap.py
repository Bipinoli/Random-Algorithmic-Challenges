
class MinHeap:
    def __init__(self, lst=[]):
        self.q = []
        for item in lst:
            self.insert(item)

    def insert(self, a):
        self.q.append(a)
        i = len(self.q) - 1
        p_i = self.parent(i)
        while i > 0 and self.q[p_i] > self.q[i]:
            self.swap(i, p_i)
            i = p_i
            p_i = self.parent(i)

    def peek_min(self):
        if len(self.q) == 0:
            raise Exception("no item in the min-heap")
        return self.q[0]

    def remove_min(self):
        retval = self.peek_min()
        self.q[0] = self.q[len(self.q)-1]
        self.q.pop()
        i = 0
        while True:
            lc = self.left_child(i)
            rc = self.right_child(i)
            lft_val = self.q[lc] if lc < len(self.q) else float('inf')
            rgt_val = self.q[rc] if rc < len(self.q) else float('inf')
            smaller_c = lc if lft_val < rgt_val else rc
            if smaller_c >= len(self.q) or self.q[i] < self.q[smaller_c]:
                break
            self.swap(i, smaller_c)
            i = smaller_c
        return retval

    def swap(self, i, j):
        tmp = self.q[i]
        self.q[i] = self.q[j]
        self.q[j] = tmp

    def parent(self, i):
        return (i - 1) // 2

    def left_child(self, i):
        return 2 * i + 1

    def right_child(self, i):
        return 2 * i + 2


if __name__ == "__main__":
    # Test 1
    print("--- TEST 1 ---")
    data = [10, 2, 3, 5, 11, 22, 1]
    heap = MinHeap(lst=data)
    assert heap.q == [1, 5, 2, 10, 11, 22, 3]
    heap.insert(4)
    assert heap.q == [1, 4, 2, 5, 11, 22, 3, 10]
    print("heap insert -- passed!")
    assert heap.peek_min() == 1
    print("heap peek -- passed!")
    assert heap.remove_min() == 1
    assert heap.remove_min() == 2
    assert heap.remove_min() == 3
    assert heap.remove_min() == 4
    assert heap.remove_min() == 5
    print("heap remove min -- passed!")

    print("--- TEST 2 ---")
    data = list(reversed(range(10)))
    heap = MinHeap(lst=data)
    for i in range(10):
        assert heap.remove_min() == i
    print("minsort --- passed!")
    print("------------")
    print("All tests passed!")
