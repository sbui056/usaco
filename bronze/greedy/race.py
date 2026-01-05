
def min_time(K, X):
    """
    Returns the minimum number of seconds needed
    to run at least K meters, starting at speed 1,
    and ending with speed >= X.
    """

    curr_distance = 0
    slow_down_distance = 0

    time = 0
    speed = 1
    
    # Phase 1: speed < X (only speed up)
    while speed < X:
        curr_distance += speed
        time += 1
        if curr_distance >= K:
            return time
        speed += 1

    # Phase 2: speed >= X (speed up + slow down mirror)
    while True:
        curr_distance += speed
        time += 1
        if curr_distance + slow_down_distance >= K:
            return time

        slow_down_distance += speed
        time += 1
        if curr_distance + slow_down_distance >= K:
            return time

        speed += 1
    '''
    while True:
        # consider the new distance
        curr_distance += current_speed
        time += 1

        # see if total possible distance is enough
        if curr_distance + slow_down_distance >= K:
            return time

        # if above speed limit, then consider distance if slow down
        if current_speed >= X:
            slow_down_distance += current_speed
            time += 1

            if curr_distance + slow_down_distance >= K:
                return time

        # increase speed
        current_speed += 1
    '''

with open("race.in", "r") as fin:
    K, N = map(int, fin.readline().split())
    queries = [int(fin.readline()) for _ in range(N)]

with open("race.out", "w") as fout:
    for X in queries:
        fout.write(str(min_time(K, X)) + "\n")