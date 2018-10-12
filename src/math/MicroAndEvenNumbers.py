import math as m

n, k = map(int, input().split(" "))
arr = list(map(int, input().split(" ")))
arr = [x for x in arr if x%2 == 0]
l = len(arr)
if l-k < 0:
    print(0)
else:
    print(int(m.factorial(l)/(m.factorial(k) * m.factorial(l-k))))
