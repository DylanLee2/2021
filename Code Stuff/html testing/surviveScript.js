// https://www.w3schools.com/graphics/canvas_intro.asp
// ^website for html canvas

/*
var canvas = document.getElementById("canvas");

var txt = canvas.getContext("2d");
txt.font = "30px Times New Roman";
txt.strokeText("Score: ", 300, 50);

var rec = canvas.getContext("2d");
// Create gradient
var grd = rec.createLinearGradient(0, 0, 200, 0);
grd.addColorStop(0, "red");
grd.addColorStop(1, "white");
//Fill with gradient
rec.fillStyle = grd;
rec.fillRect(0,0,150,75);

var rec2 = canvas.getContext("2d");
rec2.fillStyle = "red";
rec2.fillRect(100,400,50,50);


// var memeGif = document.getElementById("arthur");
// var image = canvas.getContext("2d");
// image.drawImage(memeGif,30,30);
// var grandBlueGif = document.getElementById("grandblue");
// image.drawImage(grandBlueGif,50,50);
*/
/*
const canvas = document.getElementById("canvas");
var player = canvas.getContext("2d");
var x = 50;
var speed = 2;
player.fillStyle = "blue";
player.fillRect(x,200,100,100);
setInterval(update,10);

function update(){
  player.clearRect(0,0,600,600);
  x+=speed;
  player.fillRect(x,200,100,100);
  if(x>600)
    x=0;
}
*/

const canvas = document.getElementById("canvas");
const c = canvas.getContext("2d");

class Player{

  constructor(x,y,w,h){
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.hp = 3;
    this.maxSpeed = 4;
    this.speedX = 0;
    this.speedY = 0;
    this.image = document.getElementById("player");
  }

  draw(){
    c.drawImage(this.image,this.x,this.y,this.w,this.h);
  }
  
  move(moveX, moveY){
    this.x = this.x + moveX;
    this.y = this.y + moveY;
    c.drawImage(this.image,this.x,this.y,this.w,this.h);
  }

  updatePosition(){
    this.x += this.speedX;
    this.y += this.speedY;
    c.drawImage(this.image,this.x,this.y,this.w,this.h);
  }
  
}

class Projectile{
  constructor(x,y,w,h,color){
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.speedX = 0;
    this.speedY = 0;
    this.color = color;
  }

  draw(){
    c.fillStyle = this.color;
    c.fillRect(this.x,this.y,this.w,this.h);
  }

  updatePosition(){
    this.x += this.speedX;
    this.y += this.speedY;
    c.fillRect(this.x,this.y,this.w,this.h);
  }

}
/*
class Enemy{
  constructor(x,y,w,h){
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }
}
*/
document.onkeydown = keyPress;
document.onkeyup = stop;
document.onclick = shoot;
var p = new Player(250,250,70,65);
p.draw();
var bullet = new Projectile(-10,-10,10,10,"blue");
bullet.draw();

function shoot(e){
  let ex = e.pageX-205;
  let why = e.pageY-120;
  bullet = new Projectile(ex,why,10,10,"blue");
}

function stop(){
  p.speedX = 0;
  p.speedY = 0;
}

function keyPress(e){
  let key = e.keyCode;
  //w = 87, up arrow = 38
  //s = 83, down arrow = 40
  if(key == 87 || key == 38)
    p.speedY = -p.maxSpeed;
  else if(key == 83 || key == 40)
    p.speedY = p.maxSpeed;
  //a = 65, left arrow = 37
  //d = 68, right arrow = 39
  if(key == 65 || key == 37)
    p.speedX = -p.maxSpeed; 
  else if(key ==  68|| key == 39)
    p.speedX = p.maxSpeed;
}

function update(){
  c.clearRect(0,0,600,600);
  p.updatePosition();
  bullet.updatePosition();
  if(p.x > 600)
    p.x = -50;
  else if(p.x < -50)
    p.x = 600;
  if(p.y > 600)
    p.y = -50;
  else if(p.y < -50)
    p.y = 600;
}
/*
addEventListener("click", (event)=>{
  bullet = new Projectile(event.clientX, event.clientY, 10,10, "red")
  bullet.draw();
})
*/
setInterval(update,20); // basically canvas.pause