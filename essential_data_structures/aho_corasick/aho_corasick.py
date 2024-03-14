# reference(FSM + failure link): https://www.youtube.com/watch?v=O7_w001f58c
# reference(output link): https://www.youtube.com/watch?v=OFKxWFew_L0
# reference: https://cp-algorithms.com/string/aho_corasick.html

class Vertex:
    def __init__(self, alphabet_count=26):
        self.next = [-1] * alphabet_count
        self.failure_link = -1
        self.output_link = -1
        self.is_output = False
        self.value = "*"

    def alpha2index(alphabet):
        return ord(alphabet) - ord('A')


class AhoCorasick:
    def __init__(self, key_words=[], alphabet_count=26, alpha2indexFunc=Vertex.alpha2index):
        self.alphabet_count = alphabet_count
        self.alpha2index = alpha2indexFunc
        self.vertices = [Vertex(alphabet_count=alphabet_count)]
        for kw in key_words:
            self.build_trie(kw)

    def build_trie(self, s: str):
        cur = 0
        for c in s:
            i = self.alpha2index(c)
            if self.vertices[cur].next[i] == -1:
                new_v = Vertex(alphabet_count=self.alphabet_count)
                new_v.value = c
                self.vertices.append(new_v)
                self.vertices[cur].next[i] = len(self.vertices) - 1
            cur = self.vertices[cur].next[i]
        self.vertices[cur].is_output = True

    def match(self, text: str):
        pass

    def to_string(self, root=0, padding=0):
        retval = f"- ({self.vertices[root].value})" if self.vertices[root].is_output else f"- {
            self.vertices[root].value}"
        for nxt in self.vertices[root].next:
            if nxt != -1:
                retval += '\n' + '---' * \
                    (padding + 1) + self.to_string(nxt, padding+1)
        return retval


if __name__ == "__main__":

    key_words = ["A", "AG", "C", "CAA", "GAG", "GC", "GCA"]
    ac = AhoCorasick(key_words=key_words)
    print(ac.to_string())
    exit(1)

    key_words = ["A", "AG", "C", "CAA", "GAG", "GC", "GCA"]
    ac = AhoCorasick(key_words=key_words)
    matches = ac.match("GCAA")
    assert len(matches) == 5
    assert 'A' in matches
    assert 'C' in matches
    assert 'CAA' in matches
    assert 'GC' in matches
    assert 'GCA' in matches

    print("All tests passed!")
