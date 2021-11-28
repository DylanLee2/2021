const canvas =  document.getElementById("canvas");
const c = canvas.getContext("2d");
document.onkeydown = keyPress;
document.onkeyup = fall;

class Player{
  constructor(x,y,w,h,color){
    this.x=x;
    this.y=y;
    this.w=w;
    this.h=h;
    this.color=color;
    this.speedY = 0;
  }
  draw(){
    c.fillStyle = this.color;
    c.fillRect(this.x,this.y,this.w,this.h);
  }
  move(moveX,moveY){
    this.x+=moveX;
    this.y+=moveY;
    this.draw();
  }
  setSpeedY(newSpeed){
    this.speedY = newSpeed;
  }
  updatePosition(){
    this.y += this.speedY;
    this.draw();
  }
}

class Obstacle{
  constructor(x1,y1,x2,y2,color){
    this.x1=x1;
    this.y1=y1;
    this.x2=x2;
    this.y2=y2;
    this.speedX = -10;
    this.color=color;
  }
  draw(){
    c.moveTo(this.x1,this.y1);
    c.lineTo(this.x2,this.y2);
    c.strokeStyle = this.color;
    c.stroke();
  }
  updatePosition(){
    this.x += this.speedX;
    this.draw();
  }
}

function keyPress(e){
  let key = e.keyCode;
  if(key == 32 && p.y>0){
    p.setSpeedY(-7);
    flying = true;
    accel = 0;
  }
}

function fall(){
  flying = false;
}

function update(){
  c.clearRect(0,0,canvas.width,canvas.height);
  p.updatePosition();
  console.log(p.y);
  if(p.y+p.h >= canvas.height-80){
    accel = 0;
    p.setSpeedY(0);
  }
  else if(!flying){
    accel += 0.3;
    p.setSpeedY(7);
  }
  if(o.length < 5){
    o.push(new Obstacle((Math.random()*canvas.width)+canvas.width/2,(Math.random()*canvas.height)+canvas.height/2,(Math.random()*canvas.width)+canvas.width/2,(Math.random()*canvas.height)+canvas.height/2,"red"));
  }
  for(let i = 0; i < o.length; i++){
    o[i].updatePosition();
  }
}

var p = new Player(150,310,40,40,"green");
p.draw();
var flying = false;
var accel = 0;
var score = 0;

var o = [];

setInterval(update,20);