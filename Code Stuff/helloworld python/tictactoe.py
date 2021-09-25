from graphics import *

width = 500
height = 500
win = GraphWin("Tic Tac Toe", width, height)    

vertLines = []
horizLines = []
for i in range(3):
    vert = Line(Point(width*i/3, 0), Point(width*i/3, height))
    vertLines.append(vert)
    horiz = Line(Point(0, height*i/3), Point(width, height*i/3))
    horizLines.append(horiz)
    vertLines[i].draw(win)
    horizLines[i].draw(win)

game = True
gameResult = "Tie!"
turn = 0 # even indicates O's turn, odd indicates X's turn
rows, cols = (3, 3)
board = [[0]*cols]*rows # 3x3 array to check for win (game = False when either side wins)

while(game):
# for the 2D array, 1 indicates the O's and 2 inidcates the X's

    #if(win.getMouse().getX() < width/3 and win.getMouse().getY() < height/3): # box 1
     #   o = Circle(Point(width/7,height/7), width/9)
      #  o.draw(win)
        #board[0][0] = 1
        #if(turn%2 == 0):

    #p = win.getMouse()
    if(turn%2 == 0):
        o = Circle(Point((width-win.getMouse().getX())%(width/3), (height-win.getMouse().getY()%(height/3), width/9)))
        o.draw(win)

"""
    elif(turn%2 != 0)
        x1 =
        x2 =  
    turn+=1
"""

win.getMouse() # pause for click in windowwin.close()
win.close()