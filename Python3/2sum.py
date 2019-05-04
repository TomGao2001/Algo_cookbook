class HashTable2Sum: #biggest 99999662302, smallest -99999887310
    def __init__(self, m):
        self.m = m
        self.table = [list() for i in range(m)]

    def insert(self, x):
        bucket = self.h(x)
        lst = self.table[bucket]
        lst.append(x)

    def h(self, x):
        return x % self.m

    def search(self, x):
        bucket = self.h(x)
        for item in self.table[bucket]:
            if item == x:
                return True
        return False


A = list()
result = [False] * 20001 #20001
ht = HashTable2Sum(500009) #500009
with open("2sum.txt") as f:
    for line in f:
        ht.insert(int(line))
        A.append(int(line))
print("Import done")

for t in range(-10000, 10001, 1): #-10000, 10001, 1
    print("summing " + str(t))
    for x in A:
        if (t-x) is not x:
            if ht.search(t-x):
                result[t+10000] = True
                continue

sum = 0
for bool in result:
    if bool:
        sum += 1
print(sum)
