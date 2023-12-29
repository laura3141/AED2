#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool contemValor(int x[],int a,int tam){
    bool contem=false;
    int i=0;
    if(a!=x[tam/2]){
        if(a<x[tam/2]){
            i=0;
            tam=tam/2;
        }
        else{
            i=tam/2+1;
        }
        for(i=0;i<tam;i++){
            if(x[i]==a)contem=true;
        }
    }
   else contem=true;
    return contem;
}

int main(){
    int array[5]={0,1,2,3,4};
    int num;
    printf("digite um numero: ");
    scanf("%d",&num);
    bool contem=contemValor(array,num,5);
    if(contem)puts("SIM");
    else printf("NAO");
}