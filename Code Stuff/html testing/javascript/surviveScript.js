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
const displayScore = document.getElementById("score");
const displayAmmo = document.getElementById("dispAmmo");

class Player{
  constructor(x,y,w,h,type){
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.hp = 3;
    this.maxSpeed = 4;
    this.speedX = 0;
    this.speedY = 0;
    this.type = type;
    if(this.type == "player")
      this.image = document.getElementById("player");
    else if(this.type == "enemy")
      this.image = document.getElementById("enemy");
    else if(this.type == "ammo")
      this.image = document.getElementById("ammo");
  }
  draw(){
    c.drawImage(this.image,this.x,this.y,this.w,this.h);
  }
  move(moveX, moveY){
    this.x += moveX;
    this.y += moveY;
    this.draw();
  }
  updatePosition(){
    this.x += this.speedX;
    this.y += this.speedY;
    this.draw();
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
    c.fillStyle = this.color;
    c.fillRect(this.x,this.y,this.w,this.h);
  }
}

document.onkeydown = keyPress;
document.onkeyup = stop;
document.onclick = shoot;
var p = new Player(250,250,50,70,"player");
p.draw();
const bullets = [];
const enemies = [];
const ammopack = [];

var ammo = 10;
var score = 0;
var msScore = 0;
var alive = true;
var paused = false;
displayAmmo.innerHTML = "Ammo: "+ammo;

function spawn(){
  if(alive && !paused){
    setInterval(()=>{
      let enemyWidth = Math.random()*(80-40)+40; // width & height are 40-80
      let enemyHeight = enemyWidth // to make a circle
      let enemyX, enemyY;
      if(Math.random<0.5){
        enemyX = Math.random() < 0.5 ? 0-40 : canvas.width;
        enemyY = Math.random()*canvas.height;
      }
      else{
        enemyX = Math.random()*canvas.width;
        enemyY = Math.random() < 0.5 ? 0-40 : canvas.height;
      }
      enemies.push(new Player(enemyX,enemyY,enemyWidth,enemyHeight,"enemy"))

    },1500);
    setInterval(()=>{
      let ammoX = Math.random()*canvas.width;
      let ammoY = Math.random()*canvas.height;
      ammopack.push(new Player(ammoX,ammoY,20,30,"ammo"));
    },8000);
  }
}

function shoot(e){
  if(alive && !paused && ammo>0){
    let mouseX = e.pageX-(canvas.width*.175);
    let mouseY = e.pageY-(canvas.width*.2);
    let playerX = p.x+(p.w/2);
    let playerY = p.y+(p.h/2);
    const angle = Math.atan2(mouseY-playerY,mouseX-playerX);
    bullets.push(new Projectile(playerX,playerY,10,10,"white"));
    bullets[bullets.length-1].speedX = Math.cos(angle)*8;
    bullets[bullets.length-1].speedY = Math.sin(angle)*8;
    ammo--;
    displayAmmo.innerHTML = "Ammo: "+ammo;
  }
}

function stop(){
  p.speedX = 0;
  p.speedY = 0;
}

function keyPress(e){
  let key = e.keyCode;
  if(key == 80 && paused == false)
    paused = true;
  else if(key == 80 && paused == true)
    paused = false;
  if(alive && !paused){
    //console.log(key);
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
}
  /*
  barriers that block movement
  penetrating bullets that take 3 ammo (press e to toggle normal/penetrating bullets)
  melee attacks
  power ups (eg speed boost, infinite ammo for a period of time, etc.)
  soundfx if possible
  ammo & score icon
  */

  function isColliding(a,b){
    if(a.x>=b.x && a.x+a.w<=b.x+b.w && a.y>=b.y && a.y+a.h<=b.y+b.h)
      return true;
    return false;
  }

  function update(){
    if(alive && !paused){
      //c.clearRect(0,0,600,600);
      c.fillStyle = 'rgba(1,1,1,0.4)'
      c.fillRect(0,0,canvas.width,canvas.height);
      p.updatePosition();
      for(let i = 0; i < ammopack.length; i++){
        ammopack[i].draw();
        if(isColliding(ammopack[i],p)){
          ammo += 3;
          displayAmmo.innerHTML = "Ammo: "+ammo;
          ammopack.splice(i,1);
          i--;
        }
      }
      for(var i = 0; i < bullets.length; i++){
        bullets[i].updatePosition();
        if(bullets[i].x+bullets[i].w < 0 || bullets[i].x > canvas.width || bullets[i].y+bullets[i].h < 0 || bullets[i].y>canvas.height){
          bullets.splice(i,1);
        } 
      }
      for(var i = 0; i < enemies.length; i++){
        enemies[i].updatePosition();
        let angle = Math.atan2(p.y-enemies[i].y+(p.h/2),p.x-enemies[i].x+(p.w/2));
        enemies[i].speedX = Math.cos(angle);
        enemies[i].speedY = Math.sin(angle);
        if(isColliding(enemies[i],p)){
          console.log("player hit")
          alive = false;
        }
        for(var j = 0; j < bullets.length; j++){
          if(isColliding(bullets[j],enemies[i])){
            enemies.splice(i,1);
            bullets.splice(j,1);
          }
        }
      }
      msScore++;
      if(msScore == 6){
        msScore = 0;
        score++;
      }
      displayScore.innerHTML = "Score: "+score;
      if(p.x > 600)
        p.x = -50;
      else if(p.x < -50)
        p.x = 600;
      if(p.y > 600)
        p.y = -50;
      else if(p.y < -50)
        p.y = 600;
  }
}

var game = setInterval(update,20); // basically canvas.pause
spawn();