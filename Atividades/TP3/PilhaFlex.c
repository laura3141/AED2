#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>

typedef struct Jogador{
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[80];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[50];
}Jogador;
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
typedef struct Celula{
   struct Celula *prox;
    Jogador elemento;
}Celula;

typedef struct PilhaFlex{
    Celula *topo;
}PilhaFlex;

//Metodo para preencher uma nova celula
void novaCelula(Celula *celula, Jogador jogador){
    celula->prox=NULL;
    celula->elemento=jogador;
    
}
//Metodo para inicializar a pilha
void inicializar(PilhaFlex *pilha){
    pilha->topo=NULL;
}

//Metodo para inserir no fim
void inserirFim(PilhaFlex *pilha,Jogador lido){
    Celula *celula=(Celula*)malloc(sizeof(Celula));
    novaCelula(celula,lido);
    Celula* aux=pilha->topo;
    pilha->topo=celula;
    pilha->topo->prox=aux;
    aux=NULL;
}
//Metodo para remover no fim
Jogador removerFim(PilhaFlex *pilha){
    Jogador jogador=pilha->topo->elemento;
    Celula *aux=pilha->topo;
    pilha->topo=pilha->topo->prox;
    aux=NULL;
    return jogador;
}

//metodo calcular o numero de registros da pilha
int tamanho(PilhaFlex *pilha){
    Celula *i=pilha->topo;
    int c=0;
    while(i!=NULL){
        c++;
        i=i->prox;
    }
    return c;
}

//metodo para imprimir os atributos dos jogadores presentes na pilha 
void imprime(PilhaFlex *pilha,Celula *i,int j){
    if(i!=NULL){
        imprime(pilha,i->prox,j-1);
        printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",
        j,
        i->elemento.nome,
        i->elemento.altura,
        i->elemento.peso,
        i->elemento.anoNascimento,
        i->elemento.universidade,
        i->elemento.cidadeNascimento,
        i->elemento.estadoNascimento);
        i=i->prox;
    }
}

int main(){
    PilhaFlex pilha;
    inicializar(&pilha);
    Jogador jogadores[3922];
    char id[10];
    char comandos[100];
    int nOperacoes=0;
    Jogador jogadoresR[100];
    int qtR=0;
    //construçao do subarray com os ids digitados
    do {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0) {
            int identificador = atoi(id);
            ler(jogadores);
            Jogador aux=jogadores[identificador];
            inserirFim(&pilha,aux);
        }
    } while (strcmp(id, "FIM") != 0);
    scanf("%d", &nOperacoes);
    //loop para receber todas as operaçoes
    for(int i=0;i<nOperacoes;i++){
        fflush(stdin);
        scanf(" %[^\r\n]%*c", comandos);
        //Repartiçao da string pelo delimitador espaço em branco
        char *tokens[100]; 
        int token_count = 0;
        char *token = strtok(comandos, " ");
        while (token != NULL) {
            tokens[token_count] = token;
            token_count++;
            token = strtok(NULL, " ");
        }
        
        if(strcmp(tokens[0],"R")==0){
            jogadoresR[qtR]=removerFim(&pilha);
            qtR++;
        }
        else if(strcmp(tokens[0],"I")==0){
            int identificador = atoi(tokens[1]);
            Jogador aux=jogadores[identificador];
            inserirFim(&pilha,aux);
            
        }
    }
    //impressao dos removidos
    for(int i=0;i<qtR;i++){
        printf("(R) %s \n",jogadoresR[i].nome);
    }
    int tam=tamanho(&pilha);
    imprime(&pilha,pilha.topo,tam-1);

}