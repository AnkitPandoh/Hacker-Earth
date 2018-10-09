n,x = map(int,input().split(" "))
arr = []
arr.extend(map(int,input().split(" ")))
print(arr)
for i in range(0,n):
    x = x - arr[i]
    if x <= 0:
        print(i+1)
        break