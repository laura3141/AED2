#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool contemValor(int x[],int a,int tam){
    bool contem=false;
    for(int i=0;i<tam;i++){
        if(x[i]==a)contem=true;
    }
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