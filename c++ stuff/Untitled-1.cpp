#include <iostream>
#include <cstdlib>
#include <vector>
using namespace std;
//blackjack?
int main(){

    vector <int> deck;
    for(int i = 0; i < 13; i++){
        for(int j = 0; j < 4; j++)
            deck.push_back(i);
    }

    bool game = true;
    int you = 0, opponent = 0;

    while(game){
        string inp;
        cout<<"hit or stay";
        cin>>inp;
        if(inp == "hit"){
            you += deck[rand()%deck.size()];
        }
        if(opponent < 17){
            opponent += deck[rand()%deck.size()];
        }
        else if(inp == "stay" && opponent >= 17){

        }
    }

}