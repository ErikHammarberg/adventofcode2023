
import re
import functools

def calculateValue( input ):
    match = re.search(r"^[a-zA-Z]*(\d)\w*", input)
    print(match)
    print(match.group(1))
    match2 = re.search(r"(\d)(?!.*\d)", input)
    print(match2.group(1))

    return int(match.group(1) + match2.group(1))


def secondSolve(input):
    res = input
    litteralmap = {"one":1, "two":2, "three": 3, "four":4,
                   "five":5, "six":6, "seven":7, "eight":8, "nine":9,
                   "1":1, "2":2, "3":3,"4":4,"5":5,"6":6,"7":7,"8":8,"9":9}
    firstidx = 99999999
    first = input
    lastidx = -1
    last = input
    for key, value in litteralmap.items():
        try:
            idx = input.index(key)
            if idx < firstidx:
                firstidx = idx
                first = value
        finally:

        try:
            idx = input.rindex(key)
            if idx > lastidx:
                lastidx = idx
                last = value
        finally:


    return int(str(first) + str(last))





def doStuffOne(filenamee):
    file = open(filenamee, 'r')
    lines = file.readlines()

    nums = map(calculateValue, lines)
    #print(list(nums))
    res = functools.reduce(lambda x, y: x + y, nums)
    return res

def doStuffTwo(filenamee):
    file = open(filenamee, 'r')
    lines = file.readlines()

    nums = map(secondSolve, lines)

    #print(list(nums))
    res = functools.reduce(lambda x, y: x + y, nums)
    return res


#res = doStuffOne('day1-ex.txt')
#print(res)
#resAct = doStuffOne('day1-input.txt')
#print(resAct)

res2 = doStuffTwo('day1-ex2.txt')
print(res2)