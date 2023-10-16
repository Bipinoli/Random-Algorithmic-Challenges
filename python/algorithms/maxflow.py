from collections import deque

def bfs(src, sink, n, connections, capacity):
    inf = 1e9 + 7
    inval = -1
    before = [None] * n
    before[src] = inval

    q = deque()
    q.append((src, inf))
    while len(q) > 0:
        cur, flow = q.popleft()
        for next in connections[cur]:
            if before[next] == None and capacity[cur][next] > 0:
                before[next] = cur
                new_flow = min(flow, capacity[cur][next])
                if next == sink:
                    return new_flow, before
                q.append((next, new_flow))
    return 0, before


def maxflow(src, sink, n, connections, capacity):
    flow = 0
    while True:
        new_flow, path = bfs(src, sink, n, connections, capacity)
        if new_flow == 0:
            break
        flow += new_flow
        cur = sink
        while cur != src:
            prev = path[cur]
            capacity[prev][cur] -= new_flow
            capacity[cur][prev] += new_flow
            cur = prev
    return flow
