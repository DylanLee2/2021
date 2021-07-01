#include <iostream>
#include <vector>
#include <stdlib.h>
using namespace std;

//for vectors, pushback() adds to the back, erase() removes specific element
int main() 
{
    /*
    vector<int> deck;
    
    for(int value = 0; value < 13; value++){
        for(int copy = 0; copy < 4; copy++)
            deck.push_back(value);
    }

    bool game = true;
    int you = 0, opponent = 0;
    while(game){
        cout<<"You: "<<you<<" , Opponent: "<<opponent;
        string choice;
        cout<<"hit or stay";
        cin>>choice;
        if(choice == "hit"){
            iterator randy = rand()%deck.size();
            you += deck[randy];
            deck.erase(randy);
        }
        if(opponent < 17){
            iterator randy = rand()%deck.size();
            opponent += deck[randy];
            deck.erase(randy); 
        }
        if(choice == "stay" && opponent >= 17){
            if(you == opponent || (you > 21 && opponent >21))
                cout<<"Tie!";
            else if((you == 21 && opponent < 21) || (you > opponent))
                cout<<"You win!";
            else
                cout<<"You lose :(";
            game = false;
        }
    }
    */
   
    /*
    string hmm[4];
    hmm = {"yes","no","maybe","so"};
    int a = 69;
    int b = 420;
    int c = a * b;
    string yes = "ae";
    for(int i=0;i<4;i++)
        cout<<hmm[i]<<"\n";
    return 0;
    */
   return 0;
}