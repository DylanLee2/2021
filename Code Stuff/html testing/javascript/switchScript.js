const canvas =  document.getElementById("canvas");
const c = canvas.getContext("2d");
document.onkeyup = keyPress;

class Player{
  constructor(x,y,w,h){
    this.x=x;
    this.y=y;
    this.w=w;
    this.h=h;
    this.image = document.getElementById("cube_p");
    this.speedY = 0;
  }
  draw(){
    c.drawImage(this.image,this.x,this.y,this.w,this.h);
  }
  switchImage(imgsrc){
    this.image = document.getElementById(imgsrc);
    this.draw();
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

class Rect{
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
  // space to jump
  if(key == 32 && p.y>0){
    cubeFace = "cubeo";
    p.switchImage(cubeFace + cubeColor);
    for(let i = 0; i < p.h*3; i++){
      setTimeout(function(){
        p.move(0,-0.5); 
      },2);
    }
    accel = 0;
  }
  // w for pink
  else if(key == 87){
    if(cubeColor != "p"){
      cubeColor = "p";
      p.switchImage(cubeFace + cubeColor);
    }
  }
  // e for blue
  else if(key == 69){
    if(cubeColor != "b"){
      cubeColor = "b";
      p.switchImage(cubeFace + cubeColor);
    }
  }
}

function update(){
  c.clearRect(0,0,canvas.width,canvas.height);
  plat.draw();
  p.updatePosition();
  //console.log(p.y);
  // cube hits floor
  if(p.y+p.h >= cutOffY){
    p.setSpeedY(0);
    cubeFace = "cube_";
    p.switchImage(cubeFace + cubeColor);
  }
  else{
    p.setSpeedY(3);
  }
  if(o.length < 5){
    o.push(new Obstacle((Math.random()*canvas.width)+canvas.width/2,(Math.random()*canvas.height)+canvas.height/2,(Math.random()*canvas.width)+canvas.width/2,(Math.random()*canvas.height)+canvas.height/2,"red"));
  }
  for(let i = 0; i < o.length; i++){
    o[i].updatePosition();
  }
  
}

var cutOffY = canvas.height-80;
var p = new Player(150,310,70,70);
var plat = new Rect(0,cutOffY,canvas.width,canvas.height-cutOffY,"green");
var score = 0;
var cubeFace = "cube_";
var cubeColor = "p";

var o = [];

setInterval(update,20);