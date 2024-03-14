# reference(FSM + failure link): https://www.youtube.com/watch?v=O7_w001f58c
# reference(output link): https://www.youtube.com/watch?v=OFKxWFew_L0
# reference: https://cp-algorithms.com/string/aho_corasick.html

class Vertex:
    def __init__(self, alphabet_count=26):
        self.next = [-1] * alphabet_count
        self.fail_link = [-1] * alphabet_count
        self.is_output = False
        self.value = ""
        self.parent = -1

    def alpha2index(alphabet):
        return ord(alphabet) - ord('A')


class AhoCorasick:
    def __init__(self, key_words=[], alphabet_count=26, alpha2indexFunc=Vertex.alpha2index):
        self.alphabet_count = alphabet_count
        self.alpha2index = alpha2indexFunc
        self.vertices = [self.root_vertex()]
        for kw in key_words:
            self.build_trie(kw)

    def root_vertex(self):
        root = Vertex(self.alphabet_count)
        root.fail_link = 0
        return root

    def build_trie(self, s: str):
        cur = 0
        for c in s:
            i = self.alpha2index(c)
            if self.vertices[cur].next[i] == -1:
                new_v = Vertex(alphabet_count=self.alphabet_count)
                new_v.value = self.vertices[cur].value + c
                new_v.parent = cur
                self.vertices.append(new_v)
                self.vertices[cur].next[i] = len(self.vertices) - 1
            cur = self.vertices[cur].next[i]
        self.vertices[cur].is_output = True

    def next(self, v, alphabet_index):
        i = alphabet_index
        if self.vertices[v].next[i] != -1:
            return self.vertices[v].next[i]
        return self.next(self.get_fail_link(v), i)

    def get_fail_link(self, v):
        alpahbet = self.vertices[v].value[-1]
        i = self.alpha2index(alpahbet)
        if self.vertices[v].fail_link[i] == -1:
            parent = self.vertices[v].parent
            if v == 0 or parent == 0:
                self.vertices[v].fail_link[i] = 0
            else:
                alpha = self.vertices[v].value[-1]
                fail_link = self.next(self.get_fail_link(parent), i)
                self.vertices[v].fail_link[i] = fail_link
        return self.vertices[v].fail_link[i]

    def matches_from_fail_traversal(self, v, matches=[]):
        if v == 0:
            return
        vertex = self.vertices[v]
        if vertex.is_output:
            matches.append(vertex.value)
        self.matches_from_fail_traversal(self.get_fail_link(v), matches)

    def match(self, text: str):
        retval = []
        state = 0
        for c in text:
            index = self.alpha2index(c)
            state = self.next(state, index)
            self.matches_from_fail_traversal(state, retval)
            vertex = self.vertices[state]
            if vertex.is_output:
                retval.append(vertex.value)
        return list(set(retval))

    def display_trie(self, root=0, padding=0):
        if root == 0:
            retval = "(root)"
        else:
            retval = f"- ({self.vertices[root].value})" if self.vertices[root].is_output else f"- {
                self.vertices[root].value}"
        for nxt in self.vertices[root].next:
            if nxt != -1:
                retval += '\n' + '---' * \
                    (padding + 1) + self.display_trie(nxt, padding+1)
        return retval


if __name__ == "__main__":

    key_words = ["A", "AG", "C", "CAA", "GAG", "GC", "GCA"]
    ac = AhoCorasick(key_words=key_words)
    print(ac.display_trie())

    key_words = ["A", "AG", "C", "CAA", "GAG", "GC", "GCA"]
    ac = AhoCorasick(key_words=key_words)
    matches = ac.match("GCAA")
    print(matches)
    assert len(matches) == 5
    assert 'A' in matches
    assert 'C' in matches
    assert 'CAA' in matches
    assert 'GC' in matches
    assert 'GCA' in matches

    print("All tests passed!")
