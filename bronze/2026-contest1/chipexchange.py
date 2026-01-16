
def exchange(A, B, C_a, C_b, F_a):
    A += B // C_b * C_a
    B = B % C_b

    if A >= F_a:
        return 0
    
    # optimal to create A chips
    if C_a >= C_b:
        newA = F_a - A
        newB = C_b - B - 1
        return newA + newB

    # optimal to create B chips
    else:
        newA = (F_a - A - 1) % C_a
        newB = (F_a - A + C_a - 1) // C_a * C_b - B
        return newA + newB

T = int(input())
for _ in range(T):
    A, B, C_a, C_b, F_a = map(int, input().split())
    print(exchange(A, B, C_a, C_b, F_a))