//Dylan Lee 6/17/21
#include <iostream>
#include <cstdlib>
<<<<<<< Updated upstream
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
=======
>>>>>>> Stashed changes
using namespace std;

int main(){ //8 ball

  cout<<"Magic 8-ball\n\n";
<<<<<<< Updated upstream
  srand(time(0));
  cout<<"Enter a wish: ";
=======
  cout<<"What is your wish?";
>>>>>>> Stashed changes
  string wish;
  cin>>wish;
  int response = rand()%10; //0-9

  if(response == 0)
    cout<<"It is certain";
  else if(response == 1)
    cout<<"Maybe";
  else if(response == 2)
    cout<<"You have to";
  else if(response == 3)
    cout<<"YES! GO FOR IT!";
  else if(response == 4)
    cout<<"No, don't do it";
  else if(response == 5)
    cout<<"Thats unpredicatable. Sorry.";
  else if(response == 6)
    cout<<"You shouldn't know this now";
  else if(response == 7)
    cout<<"Without a doubt";
  else if(response == 8)
    cout<<"Yes... but actually no";
  else if(response == 9)
    cout<<"Very doubtful";

  return 0;
}