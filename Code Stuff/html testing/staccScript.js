var speed = 0;
var counter = 0;
var xValues = [];
var player = document.getElementById("player");
var score = document.getElementById("score");
var play = setInterval(function(){game();},10);
document.onkeyup = keyPress;

function keyPress(e){
  let key = e.keyCode;
  if(key==32){ 
    // make new rectangle
    if(counter==0)
      speed=3;
    counter++;
    score.innerHTML = "Score: "+counter;
  }
}

function game(){
  let xPos = parseInt(window.getComputedStyle(player).getPropertyValue("left"),10);
  player.style.left = String(xPos+speed)+"px";
  
  if((xPos<0 && speed<0) || (xPos>300 && speed>0)){
     speed=speed*-1;
  }
}