const canvas = document.getElementById("canvas");
const c = canvas.getContext("2d");
document.onkeydown = keyPress;

// main area for game


function keyPress(e){ // react off of keypress
  let key = e.keyCode;

}

function update(){ // game loop

}

setInterval(update,20); // set to speed in ms

////////////////////////////////////////////////////////////////////////
// graphics classes
class Rectangle{
  constructor(x,y,w,h,color){
    this.x=x;
    this.y=y;
    this.w=w;
    this.h=h;
    this.color=color;
  }
  draw(){
    c.strokeStyle = this.color;
    c.rec(this.x,this.y,this.w,this.h);
    c.stroke();
  }
  fill(){
    c.fillStyle = this.color;
    c.fillRect(this.x,this.y,this.w,this.h);
  }
  move(moveX,moveY){
    this.x+=moveX;
    this.y+=moveY;
    this.fill();
  }
  setColor(newCol){
    this.color=newCol;
  }
  setPos(newX,newY){
    this.x=x;
    this.y=y;
    this.fill();
  }
  /*
  contains(r){
    if(r.x)
  }
  */
}