#include <stdio.h>
#include <string.h>
#include <stdbool.h>
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
typedef struct FilaCircular{
    int primeiro;
    int ultimo;
    Jogador jogadores[6];
}FilaCircular;

void inicializar(FilaCircular *fila){
    fila->primeiro=0;
    fila->ultimo=0;
}
bool isCheia(FilaCircular fila){
    bool estaCheia=false;
    if((fila.ultimo+1)%6==fila.primeiro)estaCheia=true;
    return estaCheia;
}
bool isVazia(FilaCircular fila){
    bool estaVazia=false;
    if((fila.ultimo==fila.primeiro))estaVazia=true;
    return estaVazia;
}
void inserir(FilaCircular *fila,Jogador lido){
    
    fila->jogadores[fila->ultimo]=lido;
    fila->ultimo=(fila->ultimo+1)%6;
}

Jogador remover(FilaCircular *fila){
    Jogador aux=fila->jogadores[fila->primeiro];
    fila->primeiro=(fila->primeiro+1)%6;
    return aux;
}
//metodo para imprimir os atributos do jogador com id recebido
void imprime(FilaCircular fila){
    for(int i=fila.primeiro;i!=fila.ultimo;i=(i+1)%6){
        int j=0;
        printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",
        j,
        fila.jogadores[i].nome,
        fila.jogadores[i].altura,
        fila.jogadores[i].peso,
        fila.jogadores[i].anoNascimento,
        fila.jogadores[i].universidade,
        fila.jogadores[i].cidadeNascimento,
        fila.jogadores[i].estadoNascimento);
        j++;
    }
}
float calculaMedia(FilaCircular fila){
    int soma=0;
    int j=0;
    for(int i=fila.primeiro;i!=fila.ultimo;i=(i+1)%6){
        soma=soma+fila.jogadores[i].altura;
        j++;
    }
    float media=(float)soma/j;
    return media;
}

int main(){
    FilaCircular fila;
    inicializar(&fila);
    Jogador jogadores[3922];
    char id[10];
    char comandos[100];
    int nOperacoes=0;
    float media[200];
    Jogador jogadoresR[100];
    int c=0,qtR=0;
    //construçao do subarray com os ids digitados
    do {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0) {
            int identificador = atoi(id);
            ler(jogadores);
            if(isCheia(fila)){
                remover(&fila);
            }
            Jogador aux=jogadores[identificador];
            inserir(&fila,aux);
            media[c]=calculaMedia(fila);
            c++;
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
            jogadoresR[qtR]=remover(&fila);
            qtR++;
            media[c]=3141;
            c++;
        }
        else if(strcmp(tokens[0],"I")==0){
            int identificador = atoi(tokens[1]);
            if(isCheia(fila)){
                remover(&fila);
            }
            Jogador aux=jogadores[identificador];
            inserir(&fila,aux);
            media[c]=calculaMedia(fila);
            c++;
        }
        
    }
    int j=0;
    for(int i=0;i<c;i++){
        if(media[i]==3141){
            printf("(R) %s \n",jogadoresR[j].nome);
            j++;
        }
        else{
            printf("%.f\n",media[i]);
        }
        
    }
    imprime(fila);

}