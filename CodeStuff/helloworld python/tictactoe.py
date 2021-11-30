from graphics import *

TK_SILENCE_DEPRECATION=1

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

turn = 0 # even indicates O's turn, odd indicates X's turn
rows, cols = (3, 3)
board = [[0]*cols]*rows # 3x3 array to check for win (game = False when either side wins)

while(game):
# for the 2D array, 1 indicates the O's and 2 inidcates the X's

    p = win.getMouse()
    if(p.getX() < width/3 and p.getY() < height/3): # box 1
        if(turn%2 == 0):
            o = Circle(Point(width/7,height/7), width/9)
            o.draw(win)
            board[0][0] = 1
        #elif(turn%2 == 0):
            leftLine = Line(Point(), Point())
            rightLine = Line(Point(), Point())
            leftLine.draw(win)
            rightLine.draw(win)
            board[0][0] = 2
        turn += 1

    

    #if(turn%2 == 0):
        #o = Circle(Point((width-p.getX()), (height-p.getY())), width/9)
        #o.draw(win)

gameResult = "Tie!"


#win.getMouse() # pause for click in windowwin.close()
#win.close()