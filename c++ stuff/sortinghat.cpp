#include <iostream>
int main() {

  int gryffindor = 0, hufflepuff = 0, ravenclaw = 0, slytherin = 0;
  int answer1, answer2, answer3, answer4;
  cout<<"The Sorting Hat Quiz!";

  cout<<"Q1) When I'm dead, I want people to remember me as:\n"; //Question 1
  cout<<"1) The Good\n";
  cout<<"2) The Great\n";
  cout<<"3) The Wise\n";
  cout<<"4) The Bold\n";
  cin>>answer1;
  if(answer1==1)
    hufflepuff++;
  else if(answer1==2)
    slytherin++;
  else if(answer1==3)
    ravenclaw++;
  else if(answer1==4)
    gryffindor++;
  else
    cout<<"Invalid input";

  cout<<"Q2) Dawn or Dusk?\n"; //Question 2
  cout<<"1) Dawn\n";
  cout<<"2) Dusk\n";
  cin>>answer2;
  if(answer2==1){
    gryffindor++;
    ravenclaw++;
  } 
  else if(answer2==2){
    hufflepuff++;
    slytherin++;
  } 
  else
    cout<<"Invalid input";

  cout<<"Q3) Which kind of instrument most pleases your ear?\n"; //Question 3
  cout<<"1) The violin\n";
  cout<<"2) The trumpet\n";
  cout<<"3) The piano\n";
  cout<<"4) The drum\n";
  cin>>answer3;
  if(answer3==1)
    slytherin++;
  else if(answer3==2)
    hufflepuff++;
  else if(answer3==3)
    ravenclaw++;
  else if(answer3==4)
    gryffindor++;
  else
    cout<<"Invalid input";

  cout<<"Q4) Which road tempts you most?\n"; //Question 4
  cout<<"1) The wide, sunny, grassy lane\n";
  cout<<"2) The narrow, dark, lantern-lit\n";
  cout<<"3) The twisting, leaf-strewn path through woods\n";
  cout<<"4) The cobbled street lined (ancient buildings)\n";
  cin>>answer4;
  if(answer4==1)
    hufflepuff++;
  else if(answer4==2)
    slytherin++;
  else if(answer4==3)
    gryffindor++;
  else if(answer4==4)
    ravenclaw++;
  else
    cout<<"Invalid input";

  int max = 0; //Determining process
  string house;
  if(gryffindor > max){
    max = gryffindor;
    house = "Gryffindor";
  }
  if(hufflepuff > max){
    max = hufflepuff;
    house = "Hufflepuff";
  }
  if(ravenclaw > max){
    max = ravenclaw;
    house = "Ravenclaw";
  }
  if(slytherin > max){
    max = slytherin;
    house = "Slytherin";
  }
  cout<<"You are a "<<house<<"!";

  return 0;
}