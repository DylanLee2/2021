//Dylan Lee 6/17/21
#include <iostream>
#include <cstdlib>

int main(){ //8 ball

  std::cout<<"Magic 8-ball\n\n";
  int response = std::rand()%10; //0-9

  if(response == 0)
    std::cout<<"It is certain";
  else if(response == 1)
    std::cout<<"Maybe";
  else if(response == 2)
    std::cout<<"You have to";
  else if(response == 3)
    std::cout<<"YES! GO FOR IT!";
  else if(response == 4)
    std::cout<<"No, don't do it";
  else if(response == 5)
    std::cout<<"Thats unpredicatable. Sorry.";
  else if(response == 6)
    std::cout<<"You shouldn't know this now";
  else if(response == 7)
    std::cout<<"Without a doubt";
  else if(response == 8)
    std::cout<<"Yes... but actually no";
  else if(response == 9)
    std::cout<<"Very doubtful";

  return 0;
}