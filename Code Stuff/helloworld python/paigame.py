import pygame, sys, random
from pygame.locals import *
pygame.init()
 
# Colours
BACKGROUND = (255, 225, 0)
 
# Game Setup
FPS = 60
fpsClock = pygame.time.Clock()
WINDOW_WIDTH = 600
WINDOW_HEIGHT = 600
WINDOW = pygame.display.set_mode((WINDOW_WIDTH, WINDOW_HEIGHT))
pygame.display.set_caption('garvitee')
 
# The main function that controls the game
def main () :
  looping = True

  xPos = 100
  yPos = 100
  width = 100
  height = 100
  xSpeed = 5
  ySpeed = 5
  gravity = 1
  col = (50,50,200)

  # The main game loop
  while looping :
    # Get inputs
    press = pygame.key.get_pressed()

    for event in pygame.event.get() :
      if event.type == QUIT :
        pygame.quit()
        sys.exit()
    """   
    #vert movement
    if(press[K_w]):
      yPos -= speed # - goes up for y value
    elif(press[K_s]):
      yPos += speed # + goes down for y value
    #horiz movement
    elif(press[K_d]):
      xPos += speed
    elif(press[K_a]):
      xPos -= speed
    #else:
      #yPos += grav
    if(event.type == pygame.KEYDOWN and event.key == K_r):
      xPos = 100
      yPos = 100
    #recycle player
    if(xPos > WINDOW_WIDTH+width):
      xPos = 0-width
    """

    #horiz movement
    if(press[K_d]):
      xPos += 10
    elif(press[K_a]):
      xPos += -10

    #jumping w/ gravity
    if(yPos > 0 and event.type == pygame.KEYDOWN and event.key == K_w):
      ySpeed = -20
      col = (random.randint(0,255),random.randint(0,255),random.randint(0,255))
    elif(yPos < WINDOW_HEIGHT-height):
      ySpeed += gravity
    else:
      yPos = WINDOW_HEIGHT-height
      ySpeed = 0
    yPos+=ySpeed

    #recycle player
    if(xPos > WINDOW_WIDTH):
      xPos = 0-width
    elif(xPos+width < 0):
      xPos = WINDOW_WIDTH
      

    # Processing
    rec1 = pygame.Rect(xPos,yPos,width,height)
 
    # Render elements of the game
    WINDOW.fill(BACKGROUND)
    ### Code

    pygame.draw.rect(WINDOW,col,rec1)

    ### Code
    pygame.display.update()
    fpsClock.tick(FPS)
 
main()
"""
    #pygame.draw.rect(surface, color, Rectangle object, width) #width optional
    #pygame.draw.circle(surface, colour, center, radius, width) # width is optional
    #pygame.draw.line(surface, colour, (startX, startY), (endX, endY), width)
    #pygame.draw.polygon(surface, colour, points, width) # width is optional
"""