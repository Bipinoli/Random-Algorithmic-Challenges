def KMPSearch(text, pattern, debug=False):
    assert len(pattern) > 0 and len(text) > 0
    lps = constructLPS(pattern)
    if debug:
        print("lps: ", lps)
    retval = []
    i, j = 0, 0
    while i < len(text):
        if j == len(pattern):
            startIndex = i - len(pattern)
            retval.append(startIndex)
            j = 0
        if text[i] == pattern[j]:
            i += 1
            j += 1
        else:
            if j > 0:
                j = lps[j-1]
            else:
                i += 1
    if j == len(pattern):
        startIndex = i - len(pattern)
        retval.append(startIndex)
    if debug:
        print("kmp search: ", retval)
    return retval

    
def constructLPS(text):
    lps = [0] * len(text)
    for i in range(1, len(text)):
        length = lps[i-1]
        if text[length] == text[i]:
            lps[i] = length + 1
        else:
            found = False
            while length > 0:
                length = lps[length-1]
                if text[length] == text[i]:
                    lps[i] = length + 1
                    found = True
                    break
            if not found:
                lps[i] = 0
    return lps


tests = [
    ('abcdabc', 'abc', [0, 4]),
    ('aaaaaabbaaaaaaaaaabaaaabaaaaaabaaaaaaaa', 'aaaaaabaaaaaaaa', [24]),
    ('abc', 'f', []),
    ('afbbabbbf', 'bbabbb', [2]),
    ("ababcaababcaabc", "ababcaabc", [6]),
]

for i in range(len(tests)):
    text = tests[i][0]
    pattern = tests[i][1]
    expectedOutput = tests[i][2]
    result = KMPSearch(text, pattern)
    if result == expectedOutput:
        print(f"Test #{i+1} Passed!")
    else:
        print(f"Test #{i+1} Failed!")
        print("-" * 20)
        print("text: ", text)
        print("pattern: ", pattern)
        print("expected: ", expectedOutput)
        KMPSearch(text, pattern, debug=True)
        raise Exception("stop fix this first!")
print("All tests passed!")
