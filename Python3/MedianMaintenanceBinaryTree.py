class Node:
    def __init__(self, value = None, color = "RED"):
        self.parent = None
        self.LChild = None
        self.RChild = None
        self.value = value
        self.color = color

class RBTree:
    def __init__(self, rootVal):
        self.tree = list()
        self.root = Node(value=rootVal, color = "BLACK")
        self.tree.append(self.root)

    def insert(self, value):
        currentParent = None
        currentNode = self.root
        while currentNode is not None:
            if currentNode.value < value:
                currentParent = currentNode
                currentNode = currentNode.RChild
            if currentNode.value > value:
                currentParent = currentNode
                currentNode = currentNode.LChild



    def left_rotate(self, x):

    def right_rotate(self, x):

    def getMedian(self):
        return
    #stream of numbers, first in

    #output mod10000
tree = RBTree
sum = 0
with open("MedianTest.txt") as f:
    for line in f:
        tree.insert(value = int(line))
        sum += tree.getMedian()
        sum %= 10000
        print(sum)

