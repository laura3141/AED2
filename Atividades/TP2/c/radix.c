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

//Funcao para encontrar o maior elemento do array 
Jogador getMax(Jogador jogadores[], int n) {
    Jogador maior = jogadores[0];
    for (int i = 1; i < n; i++) {
        numeroC++;
        if(maior.id < jogadores[i].id){
            numeroM++;
            maior = jogadores[i];
        }
    }
    return maior;
}
void radixsort(Jogador *array, int n) {
    //Array para contar o numero de ocorrencias de cada elemento
    Jogador max = getMax(array, n);
    for (int exp = 1; max.id/exp > 0; exp *= 10) {
            radcountingSort(array, n, exp);
    }
}
void radcountingSort(Jogador *array, int n, int exp) {
    int count[10];
    Jogador output[n];

    // Inicializar cada posição do array de contagem
    for (int i = 0; i < 10; count[i] = 0, i++);

    // Agora, o count[i] contém o número de elementos iguais a i
    for (int i = 0; i < n; i++) {
        count[(array[i].id / exp) % 10]++;
    }

    // Agora, o count[i] contém o número de elementos menores ou iguais a i
    for (int i = 1; i < 10; i++) {
        count[i] += count[i - 1];
    }

    // Ordenando os jogadores
    for (int i = n - 1; i >= 0; i--) {
        numeroM++;
        output[count[(array[i].id / exp) % 10] - 1] = array[i];
        count[(array[i].id / exp) % 10]--;
    }

    // Copiando para o array original
    for (int i = 0; i < n; i++) {
        numeroM++;
        array[i] = output[i];
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
    FILE *arq=fopen("808756_radixsort.txt","w");
    fprintf(arq,"808756\t%d\t%d\t%lf",numeroC,numeroM,tempoE);
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
    radixsort(subJogadores,qtsub);
    imprime(qtsub,subJogadores);
    fim=clock();
    criaLog();
   
    
}