class RBTree:
    '''
    Red black tree rules:
    - Every node is RED or BLACK
    - Root is always BLACK
    - New insertions are always RED
    - Every root -> leaf path must have the same number of RED
    - No path can have 2 consecutive REDs
    - Nulls are BLACK
    '''
    class Node:

    def __init__(self, duplicate_allowed = True):
        self.duplicate_allowed = duplicate_allowed



def test():
    tree = RBTree(duplicate_allowed=True)


