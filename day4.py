# Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
import functools
import re
def one_line(input):
    clean = input.split(': ')
    parts = clean[1].split(' | ')
    facit = set(re.split(r"[ ]+", parts[0]))

    cards = re.split(r"[ ]+", parts[1].strip())
    matches = set(filter(lambda x: x in facit, cards))
    if len(matches) > 0:
        return pow(2, len(matches)-1)

    return 0




def do_in(infile):
    file = open(infile, 'r')
    lines = file.readlines()
    nums = map(one_line, lines)
    res = functools.reduce(lambda a,b: a+b, nums)
    print(res)


do_in('day4-ex.txt')

do_in('day4.txt') # 98694 is too high # 29991 is also too high # 19559 is too low