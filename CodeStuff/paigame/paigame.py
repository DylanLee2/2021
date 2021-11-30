import pygame, sys, random
from pygame.locals import *
from pygame import image as img
pygame.init()
 
#BACKGROUND = (255, 225, 0)
 
walkRight = [img.load('R1.png'),img.load('R2.png'),img.load('R3.png'),img.load('R4.png'),img.load('R5.png'),img.load('R6.png'),img.load('R7.png'),img.load('R8.png'),img.load('R9.png')]
walkLeft = [img.load('L1.png'),img.load('L2.png'),img.load('L3.png'),img.load('L4.png'),img.load('L5.png'),img.load('L6.png'),img.load('L7.png'),img.load('L8.png'),img.load('L9.png')]
bg = img.load('bg.jpg')
char = img.load('standing.png')

# Game Setup
FPS = 27
fpsClock = pygame.time.Clock()
WINDOW_WIDTH = 60
WINDOW_HEIGHT = 600
WINDOW = pygame.display.set_mode((WINDOW_WIDTH, WINDOW_HEIGHT))
pygame.display.set_caption('garvitee')
 
# The main function that controls the game
def main () :

  xPos = 100; yPos = 100
  width = 64; height = 64
  xSpeed = 5; ySpeed = 5
  left = False; right = False
  jumpCount = 10
  walkCount = 0
  isJump = False
  gravity = 1
  

  def redrawGameWindow():
    global walkCount
    WINDOW.blit(bg,(0,0))

    if(walkCount+1 >= 27):
      walkCount = 0
    if(left):
      WINDOW.blit(walkLeft[walkCount//3], (xPos,yPos))
      walkCount+=1
    elif(right):
      WINDOW.blit(walkRight[walkCount//3], (xPos,yPos))
      walkCount+=1
    pygame.display.update()
    

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
      xPos += 10
      left = False
      right = True
    elif(press[K_a]):
      xPos += -10
      left = True
      right = False
    else:
      left = False
      right = False
      walkCount = 0

    #jumping w/ gravity
    #event.type == pygame.KEYDOWN and event.key == K_w
    if(yPos > 0 and press[K_w]):
      ySpeed = -20
      #col = (random.randint(0,255),random.randint(0,255),random.randint(0,255))
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
    #rec1 = pygame.Rect(xPos,yPos,width,height)

    
    # Render elements of the game
    #WINDOW.fill(BACKGROUND)
    #pygame.draw.rect(WINDOW,(50,50,200),rec1)

    redrawGameWindow()
    #pygame.display.update()
    fpsClock.tick(FPS)
 
main()
"""
    #pygame.draw.rect(surface, color, Rectangle object, width) #width optional
    #pygame.draw.circle(surface, colour, center, radius, width) # width is optional
    #pygame.draw.line(surface, colour, (startX, startY), (endX, endY), width)
    #pygame.draw.polygon(surface, colour, points, width) # width is optional
"""