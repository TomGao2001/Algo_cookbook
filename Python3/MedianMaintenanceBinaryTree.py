class Node:
    def __init__(self, value = None, color = "RED"):
        self.parent = None
        self.LChild = None
        self.RChild = None
        self.value = value
        self.color = color

class RBTree:
    def __init__(self):
        self.tree = list()
        self.root = None

    def insert(self, value):
        currentParent = None
        currentNode = self.root
        if self.root is None:
            self.root = Node(value, "BLACK")
            self.tree.append(self.root)
            return
        self.root.color = "BLACK"
        while (currentNode is not None) and (self.root is not None):
            if currentNode.value < value:
                currentParent = currentNode
                currentNode = currentNode.RChild
            else:
                currentParent = currentNode
                currentNode = currentNode.LChild
        x = Node(value)
        x.parent = currentParent
        self.tree.append(x)
        if currentParent.value < value:
            currentParent.RChild = x
        else:
            currentParent.LChild = x
        #INSERT DONE
        if currentParent.color == "RED":
            self.resolve(x)


    def resolve(self, x):
        if x.parent is not None:
            if (x is not self.root) or (x.color == "RED" and x.parent.color == "RED"):#fix
                p = x.parent
                g = p.parent
                u = None
                if p.value < g.value:
                    u = g.RChild
                else:
                    u = g.LChild
                if u is None or u.color == "BLACK":
                    self.resolve2(x,p,g,u)
                else:
                    self.resolve1(x,p,g,u)

    def resolve1(self, x, p, g, u):
        if x is self.root:
            x.color = "BLACK"
            x.parent = None
            return
        else:
            p.color = "BLACK"
            u.color = "BLACK"
            g.color = "RED"
            self.resolve(g)


    def resolve2(self, x, p, g, u):
        self.root.color = "BLACK"
        self.root.parent = None
        if p.value < g.value:
            if x.value < p.value:#LL
                tmpColor = g.color
                g.color = p.color
                p.color = tmpColor
                self.RRot(g)
            else:#LR
                self.LRot(p)
                self.resolve2(p,x,g,u)
        else:
            if x.value > p.value:#RR
                tmpColor = g.color
                g.color = p.color
                p.color = tmpColor
                self.LRot(g)
            else:#RL
                self.RRot(p)
                self.resolve2(p,x,g,u)


    def RRot(self, A):
        parent = A.parent
        B = A.LChild
        t3 = B.RChild

        if parent is not None:
            if A.value < parent.value:#g to the left
                parent.LChild = B
            else:
                parent.RChild = B
            B.parent = parent
        else:
            self.root = B
            self.root.parent = None
        B.RChild = A
        A.parent = B
        A.LChild = t3
        if t3 is not None:
            t3.parent = A

    def LRot(self, A):
        parent = A.parent
        B = A.RChild
        t3 = B.LChild

        if parent is not None:
            if A.value < parent.value:  # g to the left
                parent.LChild = B
            else:
                parent.RChild = B

            B.parent = parent
        else:
            self.root = B
            self.root.parent = None
        B.LChild = A
        A.parent = B
        A.RChild = t3
        if t3 is not None:
            t3.parent = A

    def getMedian(self):
        llen = 0
        for node in self.tree:
            if node.value < self.root.value:
                llen += 1
        rlen = len(self.tree) - llen - 1
        if rlen == llen or rlen == llen + 1:
            return self.root.value
        elif llen < rlen:
            current = self.root.RChild
            while current.LChild is not None:
                current = current.LChild
            return current.value
        else:
            current = self.root.LChild
            while current.RChild is not None:
                current = current.RChild
            return current.value

    #output mod10000
#tree = RBTree()
#sum = 0
a = list()
sum = 0
with open("Median.txt") as f:
    for line in f:
        a.append(int(line))
        a.sort()
        if len(a) % 2 == 0:
            sum += a[int(len(a)/2-1)]
        else:
            sum += a[int((len(a)-1)/2)]

        sum %= 10000
        print(sum)
        '''tree.insert(value = int(line))
        sum += tree.getMedian()
        sum %= 10000
        print(sum)
        for node in tree.tree:
            print("Node "+str(node.value),end=" ")
            if node.color is "RED":
                print("R", end=" ")
            else:
                print("B", end=" ")
            if node.parent is not None:
                print("Par: " + str(node.parent.value), end=" ")
            else:
                print("Root", end=" ")
            if node.LChild is not None:
                print("L: " + str(node.LChild.value), end=" ")
            else:
                print("",end=" ")
            if node.RChild is not None:
                print("R: " + str(node.RChild.value))
            else:
                print()'''