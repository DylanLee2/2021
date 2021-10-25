import pygame, sys, random
from pygame.locals import *
pygame.init()
 
BACKGROUND = (205, 225, 225)
FPS = 60
fpsClock = pygame.time.Clock()
WINDOW_WIDTH = 600; WINDOW_HEIGHT = 600
WINDOW = pygame.display.set_mode((WINDOW_WIDTH, WINDOW_HEIGHT))
pygame.display.set_caption('staq')

#def leftAndRight(left,right):

def main ():
  counter = 0 #used as a score and counter#
  xPos = 150; yPos = 550
  width = 300; height = 50
  xSpeed = 5; ySpeed = 5
  left = True; right = False #game starts going left#
  gravity = 1
  xPositions = [150]
  recs = [pygame.Rect(xPositions[0],yPos,width,height)]
  
  playing = True
  while playing :
    
    for event in pygame.event.get() :
      if event.type == QUIT :
        pygame.quit()
        sys.exit()

    #left & right animation#
    if((xPositions[counter] >= 0) and (left)):
      xSpeed = -3
      if(xPositions[counter] <= 0):
        left = False
        right = True
    elif((xPositions[counter] <= WINDOW_WIDTH-width) and (right)):
      xSpeed = 3
      if(xPositions[counter]+width >= WINDOW_WIDTH):
        left = True
        right = False
    xPositions[counter]+=xSpeed

    #stop rectangle#
    if(event.type == pygame.KEYDOWN and event.key == K_r):
      print("next rec")
      print(counter)
      xPositions.append(xPositions[counter])
      recs.append(pygame.Rect(xPositions[counter],yPos-height*2,width,height))
      counter+=1 #next rectangle#
    press = pygame.key.get_pressed()

    WINDOW.fill(BACKGROUND)
    for x in range(len(recs)):
      pygame.draw.rect(WINDOW,(50,50,200),recs[x])
    # pygame.draw.rect(WINDOW,(50,50,200),recs[counter])
    pygame.display.update()
    fpsClock.tick(FPS)
 
main()
"""
    #pygame.draw.rect(surface, color, Rectangle object, width) #width optional
    #pygame.draw.circle(surface, colour, center, radius, width) # width is optional
    #pygame.draw.line(surface, colour, (startX, startY), (endX, endY), width)
    #pygame.draw.polygon(surface, colour, points, width) # width is optional
"""