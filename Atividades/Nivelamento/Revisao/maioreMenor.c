#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void mostraMaioreMenor(int x[],int tamanho){
    printf("%d",tamanho);
    int maior=x[0];
    int menor=x[0];
    for(int i=0;i<tamanho;i++){
        if(x[i]>maior)maior=x[i];
        else if(x[i]<menor)menor=x[i];
    }
    printf("MAIOR: %d",maior);
    printf("MENOR: %d",menor);
}
int main(){
    int numeros[5];
    int tamanho=0;
    for(int i=0;i<5;i++){
        scanf("%d",&numeros[i]);
        tamanho++;
    }
    mostraMaioreMenor(numeros,tamanho);
}