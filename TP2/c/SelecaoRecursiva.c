#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>

//Variaveis globais para definiçao do numero de comparacoes, movimentaçoes e do tempo de execucao do algoritimo
int numeroC,numeroM;
double tempoE;

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

//metodo para ordenar os jogadores com base na ordem alfabetica do nome
void selecaoRecursiva(Jogador jogadores[],int tam,int i){
    if(i<tam-1){
        int menor=i;
        for(int j=i+1;j<tam;j++){
            numeroC++;
            if(jogadores[j].nome[0]<jogadores[menor].nome[0])menor=j;
            else
            {   
                numeroC++;
                if(jogadores[j].nome[0]==jogadores[menor].nome[0]){
                    int posChar=1;
                    numeroC++;
                    while(jogadores[j].nome[posChar]==jogadores[menor].nome[posChar]){
                        numeroC++;
                        posChar++;
                    }
                    numeroC++;
                    if(jogadores[j].nome[posChar]<jogadores[menor].nome[posChar])menor=j;
                }
            }
        }
        numeroM++;
        Jogador aux=jogadores[i];
        jogadores[i]=jogadores[menor];
        jogadores[menor]=aux;
        selecaoRecursiva(jogadores,tam,i+1);
    }

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


//metodo para imprimir os atributos do jogador com id recebido
void imprime(int tam,Jogador jogadores[]){
    for(int i=0;i<tam;i++){
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

//cria um arquivo de log
void criaLog(){
    FILE *arq=fopen("808756_selecaoRecursica.txt","w");
    fprintf(arq,"808756\t%d\t%d\t%lf",numeroC,numeroM*3,tempoE);
    fclose(arq);
}

int main(){
    clock_t inicio,fim;
    inicio=clock();
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
    
    selecaoRecursiva(subJogadores,qtsub,0);
    imprime(qtsub,subJogadores);
    fim=clock();
    tempoE=fim-inicio;
    criaLog();
}