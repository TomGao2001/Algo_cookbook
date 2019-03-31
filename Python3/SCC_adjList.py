import threading
import sys

class Graph:

    def __init__(self, numV):
        self.max = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        self.numV = numV
        self.graph = [list() for i in range(numV)]
        self.t = -1
        self.s = -1

    def addEdge(self, u, v):
        self.graph[u].append(v)

    def rev(self):
        g = Graph(self.numV)
        for i in range(len(self.graph)):
            for j in self.graph[i]:
                g.addEdge(j,i)
        return g

    def print(self):
        for i in range(len(self.graph)):
            print(i,end=": ")
            for j in self.graph[i]:
                print(j, end=", ")
            print("")

    def updateList(self, num):
        if self.max[0] < num:
            self.max[0] = num
        self.max.sort()

    def printMaxList(self):
        for num in self.max:
            print(num, end = " ")

    def grev_pass(self, grev):
        ftime = [-1] * (self.numV)
        explored = [False] * (self.numV)
        for i in range(self.numV-1, -1, -1):
            if explored[i] == False:
                self.DFS_grev(grev, i, explored, ftime)
        return ftime

    def DFS_grev(self, g, i, explored, ftime):
        explored[i] = True
        for j in g[i]:
            if explored[j] == False:
                self.DFS_grev(g, j, explored, ftime)
        self.t += 1
        ftime[i] = self.t

    def g_pass(self, order):
        leaders = [-1] * (self.numV)
        explored = [False] * (self.numV)
        current_idx = max(order)
        while current_idx > 0:
            if((self.numV - current_idx) % 1000 == 0):
                print("Node " + str(self.numV - current_idx))
            node = order.index(current_idx)
            current_idx-=1
            if explored[node] == False:
                self.s = node
                self.DFS_g(self.graph, node, explored, leaders)
        return leaders

    def DFS_g(self, g, i, explored, leaders):
        explored[i] = True
        leaders[i] = self.s
        for j in g[i]:
            if explored[j] == False:
                self.DFS_g(g, j, explored, leaders)

def main():
    g = Graph(875714)
    with open("SCC.txt") as f:
        for line in f:
            tmp = line.split(" ")
            g.addEdge(int(tmp[0]) - 1, int(tmp[1]) - 1)

    print("import done")
    order = g.grev_pass(g.rev().graph)
    print("order determined")

    #good so far
    result = g.g_pass(order)
    print("result ready")
    for i in range(g.numV):
        g.updateList(result.count(i))
    g.printMaxList()
    f = open("result.txt", "w+")
    f.write(str(g.max))
    f.close()


if __name__ == '__main__':
    threading.stack_size(67108864)
    sys.setrecursionlimit(2 ** 20)
    thread = threading.Thread(target=main)
    thread.start()


#875714, 9

