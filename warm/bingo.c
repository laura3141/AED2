#include <stdio.h>
#include <stdbool.h>

int main(){
    int k,n,u,numero;
    scanf("%d %d %d",&n,&k,&u);
    int cartela[n][k];
    int pontos[n];
    int sorteados[u];
    for(int i=0;i<n;i++){
        for(int j=0;j<k;j++){
            scanf("%d",&numero);
            cartela[i][j]=numero;
            pontos[i]=0;
        }
    }
    for(int i=0;i<u;i++){
        scanf("%d",&numero);
        sorteados[i]=numero;
    }
    int atual;
    bool vencedor=false;
    for(int i=0;i<u;i++){
        atual=sorteados[i];
        int p;
        for(p=0;p<n;p++){
            for(int j=0;j<k;j++){
                if(cartela[p][j]==atual)pontos[p]++;
                break;
            }
            if(pontos[p]==k){
                vencedor=true;
                printf("%d ganhou\n",p+1);
            }
        }
        if(vencedor)break;
    }

}