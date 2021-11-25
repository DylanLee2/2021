const canvas = document.getElementById("canvas");
const c = canvas.getContext("2d");
var score = document.getElementById("score");
var restart = document.getElementById("restart");
document.onkeydown = keyPress;
document.onkeyup = stop;
var scoreNum = 0;
var alive = true;

class Player{
  constructor(x,y,w,h,color,speedX,speedY){
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.color = color;
    this.speedX = speedX;
    this.speedY = speedY;
  }
  move(moveX,moveY){
    this.x += moveX;
    this.y += moveY;
    c.fillStyle = this.color;
    c.fillRect(this.x,this.y,this.w,this.h);
  }
  updatePosition(){
    this.x += this.speedX;
    this.y += this.speedY;
    c.fillStyle = this.color;
    c.fillRect(this.x,this.y,this.w,this.h);
  }
  draw(){
    // strokeStyle, rect, and stroke is drawing an outline, fillStyle and fillRect fills
    c.fillStyle = this.color;
    c.fillRect(this.x,this.y,this.w,this.h);
  }
}

class Brick{
  constructor(x,y,w,h){
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    // parameters are 0-360 for hue, saturation%, brightness%
    this.color = `hsl(${Math.random()*360},50%,50%)`;
    this.hp = Math.ceil(Math.random()*2)+1; // 1-3 hp
  }
  draw(){
    // strokeStyle, rect, and stroke is drawing an outline, fillStyle and fillRect fills
    c.fillStyle = this.color;
    c.fillRect(this.x,this.y,this.w,this.h);
  }
}

function stop(){
  p.speedX = 0;
}

function keyPress(e){
  // http://gcctech.org/csc/javascript/javascript_keycodes.htm keycode list
  let key = e.keyCode;
  if(alive){
    if(p.x<1 || p.x+p.w>canvas.width)
      stop();
    if(key == 65 && p.x>0) // a
      p.speedX = -speed;
    else if(key == 68 && (p.x+p.w)<canvas.width) // d
      p.speedX = speed;
  }
  
  else if(!alive){
    if(key == 82) // press r to restart
      newGame(); // and possibly include save score
  }
  
}

function newGame(){
  alert("restart"); // placeholder
}

function update(){ // game loop
  if(alive){
    c.fillStyle = 'rgba(1,1,1,0.4)'
    c.fillRect(0,0,canvas.width,canvas.height);
    p.updatePosition();
    ball.updatePosition();
    for(let i = 0; i < bricks.length; i++){
      bricks[i].draw();
    }
    if((ball.y+ball.h == p.y) && (ball.x>=p.x && ball.x+ball.w<=p.x+p.w) && Math.abs(ball.speedY)<50){
      ball.speedX = p.speedX;
      ball.speedY *= -1;
    }
    if(ball.x < 0 || ball.x+ball.w > canvas.width){
      ball.speedX *= -1;
    }

    if(ball.y > canvas.height){ // lose (show score)
      alive = false;
    }
    else if(ball.y<0){ // win (next level)
      alert("passed");
    }
    for(let i = 0; i < bricks.length; i++){
      // ball hits brick vertically
      if(ball.x>bricks[i].x && ball.x<bricks[i].x+bricks[i].w && ball.y == bricks[i].y+bricks[i].h && Math.abs(ball.speedY)<50){
        bricks[i].hp-=1;
        ball.speedY *= -1;
        scoreNum++;
        score.innerHTML = "Score: "+scoreNum;
      }
      // ball hist brick horizontally
      else if(ball.y>bricks[i].y && ball.y<bricks[i].y+bricks[i].h && (ball.x==bricks[i].x || ball.x==bricks[i].x+bricks[i].w)){
        bricks[i].hp-=1;
        ball.speedX *= -1;
        scoreNum++;
        score.innerHTML = "Score: "+scoreNum;
      }
      if(bricks[i].hp < 1){
        bricks.splice(i,1);
        i--;
      }
    }
  }
}

c.fillStyle = 'rgb(1,1,1)'
c.fillRect(0,0,canvas.width,canvas.height);

var speed = 7;
var ballSpeed = 5;

const p = new Player(200,550,200,25,"blue",0,0);
p.draw();

var bricks = [];
bricks.push(new Brick(0-(Math.random()*40),0,100,50));
var counter = 0;
for(let i = 1; i < 14; i++){
  bricks.push(new Brick(bricks[i-1].x+bricks[i-1].w,bricks[i-1].y,100,50));
  if(bricks[i].x>600){
    bricks[i].x=0-(Math.random()*40);
    bricks[i].y=bricks[i-1].y+bricks[i-1].h;
    counter=0;
  }
  counter++;
}

var ball = new Player(300,400,20,20,"white",0,ballSpeed);
ball.draw();

setInterval(update,20);