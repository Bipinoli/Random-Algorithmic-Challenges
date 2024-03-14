# reference: https://cp-algorithms.com/data_structures/disjoint_set_union.html

class UnionFind:
    def __init__(self, member_count):
        self.leaders = list(range(member_count))
        self.size = [1] * member_count

    def add_member(self):
        self.leaders.append(len(self.leaders))
        self.size.append(1)

    def union(self, a, b):
        '''
        Union of groups where a and b belong
        New leader is chosen based on the size
        '''
        a = self.find(a)
        b = self.find(b)
        if a != b:
            if self.size[a] < self.size[b]:
                a, b = b, a
            self.leaders[b] = a
            self.size[a] += self.size[b]

    def find(self, a):
        '''
        Find the leader of the group where 'a' belongs
        Using path compression idea to improve efficiency
        '''
        if self.leaders[a] == a:
            return a
        self.leaders[a] = self.find(self.leaders[a])
        return self.leaders[a]


if __name__ == "__main__":

    # Test 1
    print("--- TEST 1 ---")
    uf = UnionFind(member_count=8)
    assert uf.find(5) == 5
    uf.union(0, 4)
    uf.union(5, 4)
    uf.union(2, 3)
    uf.union(3, 7)
    assert uf.find(4) == uf.find(5)
    assert uf.find(2) == uf.find(7)
    assert uf.find(2) != uf.find(5)
    print("two sets -- passed!")

    uf.union(3, 4)
    assert uf.find(2) == uf.find(5)
    print("union of two sets -- passed!")

    uf.add_member()
    assert uf.find(8) == 8
    uf.union(8, 0)
    assert uf.find(5) == uf.find(8)
    print("add member -- passed!")

    print("--------")
    print("All tests passed!")
