import re
import functools

def check_hand(hand):
    res_matrix = {'blue':0, 'red':0, 'green':0}
    draws = map(lambda s: s.split(','), hand.split(';'))
    for outercombo in draws:
        for combo in outercombo:
            match = re.search(r"(\d+) ([a-z]+)", combo)
            num = int(match.group(1))
            res_matrix[match.group(2)] = max(res_matrix[match.group(2)], num)

    return res_matrix


def check_line(hands_string):
    first = hands_string.split(':')
    match = re.search(r'Game (\d+)', first[0])
    id = match.group(1)
    rest = check_hand(first[1])
    return functools.reduce(lambda x, value: x * value, rest.values(), 1)


def dostuff_one(filenamee):
    file = open(filenamee, 'r')
    lines = file.readlines()
    ints = map(check_line, lines)
    return functools.reduce(lambda a, b: a + b, ints)


res1test = dostuff_one('day2-ex.txt')
print(res1test)

res1 = dostuff_one('day2-input.txt')
print(res1)