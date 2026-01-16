
def is_square(s):
    # square means s = y + y
    L = len(s)
    if L % 2:
        return False
    half = L // 2
    return s[:half] == s[half:]

T, k = map(int, input().split())

for _ in range(T):
    N = int(input())
    S = input().strip()
    L = 3 * N

    if N % 2 == 1:
        print(-1)
        continue

    if is_square(S):
        print(1)
        print(" ".join(["1"] * L))
        continue

    blocks = [S[3*i:3*i+3] for i in range(N)]
    half = N // 2
    labels = [2] * L  # default op 2

    for i in range(half):
        A = blocks[i]
        B = blocks[i + half]
        pair = {A, B}

        if A == B:
            x = "C"
        elif pair == {"COW", "OWC"}:
            x = "C"
        elif pair == {"COW", "WCO"}:
            x = "W"
        else:  # {"OWC","WCO"}
            x = "O"

        # mark the chosen letter in both blocks as op 1
        labels[3*i + A.index(x)] = 1
        labels[3*(i + half) + B.index(x)] = 1

    print(2)
    print(" ".join(map(str, labels)))