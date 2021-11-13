var num = 1

var butt = document.getElementById("button");
butt.onclick=myFunction;

function myFunction(){
  //document.getElementsByTagName("BODY")[0].style.backgroundColor = "yellow";
  alert(num);
}

document.getElementById("button").onclick=function(){
  alert(num);
}