
def check(A, B, C_a, C_b, F_a, new_chips):
    needed_a = F_a - A
    if needed_a <= 0:
        return True

    min_exchanges = B // C_b
    max_exchanges = (B + new_chips) // C_b

    if min_exchanges == max_exchanges:
        return A + min_exchanges * C_a >= F_a

    constant_part = A + B + 1 + new_chips - C_b
    exchange_gain = C_a - C_b

    worst_if_exchanges_stay_low = constant_part + min_exchanges * exchange_gain
    worst_if_exchanges_almost_max = constant_part + (max_exchanges - 1) * exchange_gain
    worst_if_all_B = A + max_exchanges * C_a

    return min(worst_if_exchanges_stay_low,
               worst_if_exchanges_almost_max,
               worst_if_all_B) >= F_a

def exchange(A, B, C_a, C_b, F_a):
    # BINARY SEARCH
    l = 0
    r = 10**18
    res = r

    while l <= r:
        mid = l + (r - l) // 2
        if check(A, B, C_a, C_b, F_a, mid):
            res = mid
            r = mid - 1
        else:
            l = mid + 1
    
    return res

T = int(input())
for _ in range(T):
    A, B, C_a, C_b, F_a = map(int, input().split())
    print(exchange(A, B, C_a, C_b, F_a))