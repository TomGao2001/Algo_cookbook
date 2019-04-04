import threading, sys

class Graph:
    def __init__(self, numV):
        self.numV = numV
        self.graph = [list() for i in range(numV)]
        self.A = [0] * (numV)

    def addEdge(self, u, vLen):
        tmp = vLen.split(",")
        self.graph[u].append((int(tmp[0]), int(tmp[1])))

    def printOrder(self):
        for i in [7, 37, 59, 82, 99, 115, 133, 165, 188, 197]: #1-indexed
            print(self.A[i], end=",")

    def getLen(self, u, v):
        for tpl in self.graph[u]:
            if tpl[0] == v:
                return tpl[1]
        return -1

    def getNextNodes(self, X):
        result = list()
        for u in X:
            for v in range(1,self.numV + 1):
                if v not in X:
                    if self.getLen(u,v) is not -1:
                        result.append([u,v])
        return result

    def getMinCandidate(self, nextlist):
        minidx = -1
        min = 10**10
        for idx, pair in enumerate(nextlist):
            score = pair[2] + self.A[pair[0]]
            if score < min:
                min = score
                minidx = idx
        return minidx

    def dijkstra(self, origin):
        X = list()
        X.append(origin)
        while len(X) is not self.numV-1:
            next = self.getNextNodes(X)
            for pair in next:
                pair.append(self.getLen(pair[0], pair[1]))
            min = self.getMinCandidate(next)
            X.append(next[min][1])
            print("Adding node "+str(next[min][1]))
            self.A[next[min][1]] = self.A[next[min][0]] + next[min][2]


def main():
    g = Graph(201)
    with open("dijkstraData.txt") as f:
        for line in f:
            tmp = line.split("\t") #also
            u = int(tmp[0])
            for idx, item in enumerate(tmp):
                if (idx is not 0) and (item is not "\n"):
                    g.addEdge(u, item)
    print("import done")
    g.dijkstra(1)
    g.printOrder()
    #print(g.A)
    #NOT 4685 2610 6222 2052 6893 2834 2029 4399 2633 4483
    #some incorrect



if __name__ == '__main__':
    threading.stack_size(67108864)
    sys.setrecursionlimit(2 ** 20)
    thread = threading.Thread(target=main)
    thread.start()