import pygame, sys, random
from pygame.locals import *
from pygame import image as img
pygame.init()
 
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

  #sprite = pygame.image.load('iamges/sprite.png').convert_alpha()

  xPos = 100; yPos = 100
  width = 64; height = 64
  xSpeed = 0; ySpeed = 0
  accel = 1

  playing = True
  # Main game loop
  while playing :
    # Get inputs
    press = pygame.key.get_pressed()
    for event in pygame.event.get() :
      if event.type == QUIT :
        pygame.quit()
        sys.exit()

    #horiz movement
    if(press[K_d]):
      xSpeed = 10
    elif(press[K_a]):
      xPos += -10
    else:
      if(xSpeed>0):
        xSpeed-=accel
      elif(xSpeed<0):
        xSpeed+=accel
    xPos+=xSpeed

    #jumping w/ gravity
    #event.type == pygame.KEYDOWN and event.key == K_w
    if(yPos > 0 and press[K_w]):
      ySpeed = -20
      #col = (random.randint(0,255),random.randint(0,255),random.randint(0,255))
    elif(yPos < WINDOW_HEIGHT-height):
      ySpeed += accel
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
    #WINDOW.blit(sprite,(xPos,yPos))
    pygame.draw.rect(WINDOW,(50,50,200),rec1)
    pygame.display.update()
    fpsClock.tick(FPS)
 
main()
"""
    #pygame.draw.rect(surface, color, Rectangle object, width) #width optional
    #pygame.draw.circle(surface, colour, center, radius, width) # width is optional
    #pygame.draw.line(surface, colour, (startX, startY), (endX, endY), width)
    #pygame.draw.polygon(surface, colour, points, width) # width is optional
"""