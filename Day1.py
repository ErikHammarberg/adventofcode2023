
import re
import functools

def calculateValue( input ):
    match = re.search(r"^[a-zA-Z]*(\d)\w*", input)
    print(match)
    print(match.group(1))
    match2 = re.search(r"(\d)(?!.*\d)", input)
    print(match2.group(1))

    return int(match.group(1) + match2.group(1))

def doStuffOne(filenamee):
    file = open(filenamee, 'r')
    lines = file.readlines()

    nums = map(calculateValue, lines)
    #print(list(nums))
    res = functools.reduce(lambda x, y: x + y, nums)
    return res

res = doStuffOne('day1-ex.txt')
print(res)
resAct = doStuffOne('day1-input.txt')
print(resAct)