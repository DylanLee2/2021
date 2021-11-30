from math import *
from graphics import *

win = GraphWin("first python script", 500, 500)    
c = Circle(Point(50,50), 60)
c.draw(win)

#arrays
N = 5
arr = [0]*N
print(arr)
# output: [0, 0, 0, 0, 0]

#2d arrays
rows, cols = (5, 5)
arr = [[0]*cols]*rows
print(arr)
#output: [[0, 0, 0, 0, 0], [0, 0, 0, 0, 0], [0, 0, 0, 0, 0], [0, 0, 0, 0, 0], [0, 0, 0, 0, 0]]


inp = input("Yes: ")

while(inp == "a"):
    e = Circle(win.getMouse(), 30)
    #e.setOutline(color_rgb())
    e.draw(win)

win.getMouse() # pause for click in windowwin.close()
win.close()