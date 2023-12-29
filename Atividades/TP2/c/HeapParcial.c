#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>

typedef struct Jogador{
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[50];
}Jogador;

 //Metodo heapsort para ordenar apenas os 10 primeiros jogadores com base na altura
void sort(Jogador jogadores[],int n,int k) {

    for(int tam=2;tam<=n;tam++){
        construir(jogadores,tam);
    }
    int ultimaPos=n-1;
    while(ultimaPos>0&&k<10){
        Jogador aux=jogadores[ultimaPos];
        jogadores[ultimaPos]=jogadores[0];
        jogadores[0]=aux;
        ultimaPos--;
        reconstruir(jogadores, ultimaPos);
        k++;
    }
}
//Metodo para construir o heap invertido
void construir(Jogador jogadores[],int tam){
    for(int i=tam-1;i>0&&(jogadores[i].altura<jogadores[(i-1)/2].altura||(jogadores[i].altura==jogadores[(i-1)/2].altura&&strcmp(jogadores[i].nome,jogadores[(i-1)/2].nome)<0));i=(i-1)/2){
        Jogador aux=jogadores[i];
        jogadores[i]=jogadores[(i-1)/2];
        jogadores[(i-1)/2]=aux;
    }
}
//Metodo para reconstruir o heap apos a troca do indice 0 com o ultimo
void reconstruir(Jogador jogadores[],int tam){
    int i = 0;
    while(2*i<tam){
        int filho = getMenorFilho(i, tam,jogadores);
        if(jogadores[i].altura>jogadores[filho].altura||(jogadores[i].altura==jogadores[filho].altura&&strcmp(jogadores[i].nome,jogadores[filho].nome)>0)){
    
            Jogador aux=jogadores[i];
            jogadores[i]=jogadores[filho];
            jogadores[filho]=aux;
            i = filho;
        }else{//os demais estao certos
            i = tam;
        }
    }
}
//Metodo para retornar o menor filho 
 int getMenorFilho(int i, int tamHeap,Jogador jogadores[]){
    int filho;
   
    if (2*i+1 == tamHeap ||(jogadores[2*i+1].altura<jogadores[ 2*i+2].altura||(jogadores[ 2*i+1].altura==jogadores[ 2*i+2].altura&&strcmp(jogadores[ 2*i+1].nome,jogadores[ 2*i+2].nome)<0))){
        filho = 2*i+1;
    } else {
        filho = 2*i + 2;
    }
    return filho;
}

//metodo para dividir a string linha em substrings entre as virgulas
void split(char linha[], char substrings[8][100]) {
    int qtSubstrings = 0;
    int cS = 0;//posicao da substring atual
    int c = 0;//posicao da linha
    //inicializacao da matriz substrings para fins de controle
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 100; j++) {
            substrings[i][j] = '\0';
        }
    }
    //loop que repete ate que a string linha seja totalmente percorrida
    while (linha[c] != '\0') {
        if (linha[c] != ',') {
            while (linha[c] != ',' && linha[c] != '\0') {
                if(linha[c] == '\n')c++;//ignorar as quebras de linha
                else{
                    substrings[qtSubstrings][cS] = linha[c];
                    c++;
                    cS++;
                }
            }
            cS = 0;
            qtSubstrings++;
        } else {
            //condicional para caso o campo esteja vazio
            if(linha[c+1] == ','||linha[c+1] == '\n'||linha[c+1] == '\0'){
                strcpy(substrings[qtSubstrings],"nao informado");
                 qtSubstrings++;
            }
            c++; 
        }
    }
}
//metodo para realizar a leitura de um arquivo e guardar as informacoes em um array de jogadores
void ler(Jogador jogadores[]){
    FILE *file=fopen("/tmp/players.csv","r");
    char linha[200];
    int qtJogadores=-1;//inicializacao negativa para que a primeira linha seja ignorada

    while(fgets(linha, sizeof(linha), file) != NULL){
        char substrings[8][100];
        if(qtJogadores>=0){
            split(linha,substrings);
            //conversao de strings para inteiros
            int ID = atoi(substrings[0]);
            int h = atoi(substrings[2]);
            int p = atoi(substrings[3]);
            int ano = atoi(substrings[5]);

            jogadores[qtJogadores].id=ID;
            strcpy(jogadores[qtJogadores].nome, substrings[1]);
            jogadores[qtJogadores].altura=h;
            jogadores[qtJogadores].peso=p;
            strcpy(jogadores[qtJogadores].universidade, substrings[4]);
            jogadores[qtJogadores].anoNascimento=ano;
            strcpy(jogadores[qtJogadores].cidadeNascimento, substrings[6]);
            strcpy(jogadores[qtJogadores].estadoNascimento, substrings[7]);
            qtJogadores++;
          
        }
        else qtJogadores++;
    }
     fclose(file);
}


//metodo para imprimir os atributos do jogador 
void imprime(int tam,Jogador jogadores[]){
    for(int i=tam-1;i>tam-1-10;i--){
        printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
           jogadores[i].id,
           jogadores[i].nome,
           jogadores[i].altura,
           jogadores[i].peso,
           jogadores[i].anoNascimento,
           jogadores[i].universidade,
           jogadores[i].cidadeNascimento,
           jogadores[i].estadoNascimento);
    }
}

int main(){
    char id[10];
    Jogador jogadores[3922];
    Jogador subJogadores[500];
    int qtsub=0;
    do {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0) {
            int identificador = atoi(id);
            ler(jogadores);
            subJogadores[qtsub]=jogadores[identificador];
            qtsub++;
        }
    } while (strcmp(id, "FIM") != 0);
    sort(subJogadores,qtsub,0);
    imprime(qtsub,subJogadores);
    
}