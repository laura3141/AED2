#include <stdio.h>
#include <string.h>
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
void imprime(int identificador,Jogador jogadores[]){
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
           jogadores[identificador].id,
           jogadores[identificador].nome,
           jogadores[identificador].altura,
           jogadores[identificador].peso,
           jogadores[identificador].anoNascimento,
           jogadores[identificador].universidade,
           jogadores[identificador].cidadeNascimento,
           jogadores[identificador].estadoNascimento);
}
int main(){
    char id[50];
    Jogador jogadores[3922];
    do {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0) {
            int identificador = atoi(id);
            ler(jogadores);
            imprime(identificador,jogadores);
        }
    } while (strcmp(id, "FIM") != 0);
}