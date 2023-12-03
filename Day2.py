import re
import functools


# Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green

def eval_value(color, num):
    match color:
        case 'red':
            return num <= 12
        case 'green':
            return num <= 13
        case 'blue':
            return num <= 14
        case _:
            print('Stuff is wierd')
            return False


def check_hand(hand):
    draws = hand.split(',')
    for combo in draws:
        match = re.search(r"(\d+) ([a-z]+)", combo)
        if not eval_value(match.group(2), int(match.group(1))):
            return False
    return True


def check_line(hands_string):
    first = hands_string.split(':')
    match = re.search(r'Game (\d+)', first[0])
    id = match.group(1)
    hands = first[1].split(';')
    for hand in hands:
        if not check_hand(hand):
            return 0
    return int(id)


def dostuff_one(filenamee):
    file = open(filenamee, 'r')
    lines = file.readlines()
    ints = map(check_line, lines)
    return functools.reduce(lambda a, b: a + b, ints)

res1test = dostuff_one('day2-ex.txt')
print(res1test)

res1 = dostuff_one('day2-input.txt')
print(res1)