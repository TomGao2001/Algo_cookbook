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
            print(self.A[i], end=" ")

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
                        result.append((u,v))
        return result



    def dijkstra(self, origin):
        X = list()
        X.append(origin)
        while len(X) is not self.numV:
            score = [1000000]
            for pair in self.getNextNodes(X):







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


if __name__ == '__main__':
    threading.stack_size(67108864)
    sys.setrecursionlimit(2 ** 20)
    thread = threading.Thread(target=main)
    thread.start()