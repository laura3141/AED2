#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool isPalindromo(char a[],int c){
    bool eh;
    if(c>=strlen(a)/2){
        eh=true;
    }
    else{
        if(a[c]==a[strlen(a)-1-c])
            eh=isPalindromo(a,c+1);
        else return eh=false;
    }
    return eh;
}

int main(){
    char palavra[1000];
    char final[]="FIM";
    int fim;
     do{
        
            scanf(" %[^\r\n]%*c", palavra);
            fflush(stdin);
            fim=strcmp(palavra,final);
            if(fim!=0){
                if(isPalindromo(palavra,0))printf("SIM");
                else printf("NAO");
                printf("\n");
            }
           
        }while(fim!=0); 
}