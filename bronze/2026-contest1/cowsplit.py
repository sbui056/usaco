
def solution():
    N = int(input())
    S = input().strip()

    if N % 2 == 1:
        print(-1)
        return

    operations = [1] * (N*3)
    half1 = S[:N*3//2]
    half2 = S[N*3//2:]

    for i in range(0, N*3//2, 3):
        # case 1: all 3 chars match
        if half1[i:i+3] == half2[i:i+3]:
            pass
        # case 2: 2 chars match (CO* = *CO)
        elif half1[i:i+2] == half2[i+1:i+3]:
            operations[i+2] = 2
            operations[N*3//2 + i] = 2
        # case 3: 2 chars match (*CO = CO*)
        else:
            operations[i] = 2
            operations[N*3//2 + i+2] = 2

    print(max(operations))
    print(*operations)

T, k = map(int, input().split())
for _ in range(T):
    solution()