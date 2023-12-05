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

def two_line(input):
    clean = input.split(': ')
    parts = clean[1].split(' | ')
    facit = set(re.split(r"[ ]+", parts[0]))

    cards = re.split(r"[ ]+", parts[1].strip())
    matches = set(filter(lambda x: x in facit, cards))
    return len(matches)


def do_one(infile):
    file = open(infile, 'r')
    lines = file.readlines()
    nums = map(one_line, lines)
    res = functools.reduce(lambda a,b: a+b, nums)
    print(res)



def fetch_card(dic, num):
    val = dic.get(num)
    if val is None:
        dic[num] = 1
        return 1
    return val


def do_two(infile):
    file = open(infile, 'r')
    lines = file.readlines()
    cards = {}
    cardno = 1
    for l in lines:
        multiplier = fetch_card(cards, cardno)
        wins = two_line(l)
        for i in range(cardno+1, cardno+wins+1):
            cards[i] = fetch_card(cards, i) + multiplier
        cardno += 1

    res = functools.reduce(lambda s, p: p + s, cards.values(), 0)
    print(res)




#do_one('day4-ex.txt')

#do_one('day4.txt') # 98694 is too high # 29991 is also too high # 19559 is too low

do_two('day4-ex.txt')
do_two('day4.txt')