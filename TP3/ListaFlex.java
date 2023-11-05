import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Jogador {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    // Construtor vazio
    public Jogador() {
    }

    // Construtor com parâmetros
    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    // Métodos getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        if(universidade==null){
            universidade="nao informado";
        }
        this.universidade = universidade;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        if(cidadeNascimento==null){
            cidadeNascimento="nao informado";
        }
        this.cidadeNascimento = cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        if(estadoNascimento==null)estadoNascimento="nao informado";
        this.estadoNascimento = estadoNascimento;
    }

    // Método clone
    public Jogador clone() {
        Jogador resp=new Jogador();
        resp.id=this.id;
        resp.nome=this.nome;
        resp.altura=this.altura;
        resp.peso=this.peso;
        resp.universidade=this.universidade;
        resp.anoNascimento=this.anoNascimento;
        resp.cidadeNascimento=this.cidadeNascimento;
        resp.estadoNascimento=this.estadoNascimento;
        return resp;
    }

    // Método ler que funciona através da extração de dados pelo arquivo csv
    public static Jogador[] ler() {
        Jogador jogadores[]=new Jogador[4000];
        int qtJ=-1; 
        String nomeArquivo = "/tmp/players.csv";
        //inicializado com -1 para que o loop while seja executado ignorando a primeira linh
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;//guarda o conteudo da linha lida 
            while ((linha = leitor.readLine()) != null) {
                
                if(qtJ>=0){//Apartir da 2 linha
                    
                    jogadores[qtJ]=new Jogador();
                    Scanner sc= new Scanner(linha);
                    sc.useDelimiter(",");
                    int c=0;//contador de atributos
                    String atributos[]=new String[8];
                    while (sc.hasNext()) {
                        String str = sc.next();
                        atributos[c]=str;// armazena as substrings
                        c++;
                    }
                    if(atributos[6].length()==0){ 
                        atributos[6]=null;
                    }
                    if(atributos[4].length()==0){ 
                        atributos[4]=null;
                    }
                    jogadores[qtJ].setId(Integer.parseInt(atributos[0]));
                    jogadores[qtJ].setNome(atributos[1]);
                    jogadores[qtJ].setAltura(Integer.parseInt(atributos[2]));
                    jogadores[qtJ].setPeso(Integer.parseInt(atributos[3]));
                    jogadores[qtJ].setUniversidade(atributos[4]);
                    jogadores[qtJ].setAnoNascimento(Integer.parseInt(atributos[5]));
                    jogadores[qtJ].setCidadeNascimento(atributos[6]);
                    jogadores[qtJ].setEstadoNascimento(atributos[7]);
                    qtJ++;// incremento da quantidade de jogadores registrados
                    sc.close();
                    
                }
                else qtJ++;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jogadores;
    }
}
//Classe Celula
class Celula{
    public Celula prox;//APontador para uma outra Celula
    public Jogador elemento;
    public Celula(Jogador jogador){
        prox=null;
        this.elemento=jogador;
    }
    public Celula(){
        prox=null;
    }
} 
public class ListaFlex{
    private Celula primeiro,ultimo;

    public ListaFlex(){
        primeiro=new Celula();
        ultimo=primeiro;
    }
    //Método para realizar a insercao de um jogador no fim da lista
    public void inserirFinal(Jogador jogador){
        ultimo.prox=new Celula(jogador);
        ultimo=ultimo.prox;
    }
     //Método para realizar a insercao de um jogador no inicio da lista
    public void inserirInicio(Jogador jogador){
        Celula aux=new Celula(jogador);
        aux.prox=primeiro.prox;
        primeiro.prox=aux;
        if(primeiro==ultimo){
            ultimo=aux;
        }
        aux=null;
    }
     //Método para realizar a insercao de um jogador na lista
    public void inserir(Jogador jogador,int pos){
        Celula i=primeiro;
        int c=0;
        while(c<pos){
            i=i.prox;
            c++;
        }
        Celula aux=new Celula(jogador);
        aux.prox=i.prox;
        i.prox=aux;
        aux=i=null;
    }
    //Método para realizar a remocao de um jogador no inicio da lista
    public Jogador removerInicio(){
        Celula aux=primeiro;
        Jogador jogador=primeiro.prox.elemento;
        primeiro=primeiro.prox;
        aux=null;
        return jogador;
    }
    //Método para realizar a remocao de um jogador no fim da lista
    public Jogador removerFim(){
        Celula i=primeiro;
        while(i.prox!=ultimo){
            i=i.prox;
        }
        Jogador jogador=ultimo.elemento;
        ultimo=i;
        i=ultimo.prox=null;
        return jogador;
    }
    //Método para realizar a remocao de um jogador na lista
    public Jogador remover(int pos){
        Celula i=primeiro;
        int c=0;
        while(c<pos){
            i=i.prox;
            c++;
        }
        Celula aux=i.prox;
        Jogador jogador=aux.elemento;
        i.prox=aux.prox;
        aux=null;
        i=null;
        
        return jogador;
    }

    //metodo para imprimir a lista
    public void imprime(){
        int j=0;
        Celula i=primeiro.prox;
        while(i!=null){
            System.out.println("["+j+"] ## "+i.elemento.getNome()+" ## "+i.elemento.getAltura()+" ## "+i.elemento.getPeso()+" ## "+i.elemento.getAnoNascimento()+" ## "+i.elemento.getUniversidade()+" ## "+i.elemento.getCidadeNascimento()+" ## "+i.elemento.getEstadoNascimento()+" ##");
            i=i.prox;
            j++;
        }
    }
    public static void main(String[] args) {
            
        Scanner scanner= new Scanner(System.in);
        String id="",comandos;
        int nOperacoes,qtRemovidos=0;
        ListaFlex lista= new ListaFlex();
        Jogador jogadores[]=new Jogador[4000];
        Jogador removidos[]=new Jogador[100];
        jogadores=Jogador.ler();// Leitura dos jogadores no csv

        //Primeira parte de entradas
        do{
            id=scanner.next();
            if(!id.equals("FIM")){
                int identificador=Integer.parseInt(id);
                lista.inserirFinal(jogadores[identificador]);
            }            
        }while(!id.equals("FIM"));

        nOperacoes=scanner.nextInt();// numero de operacoes
        scanner.nextLine();
        for(int i=0;i<nOperacoes;i++){
            comandos=scanner.nextLine();// leitura de cada operacao
            String[] palavras = comandos.split(" ");
            //remocao
            if(palavras[0].equals("RI")){
                removidos[qtRemovidos]=lista.removerInicio();
                qtRemovidos++;
            }
            else if(palavras[0].equals("RF")){
                removidos[qtRemovidos]=lista.removerFim();
                qtRemovidos++;
            }
            else if(palavras[0].equals("R*")){
                int identificador=Integer.parseInt(palavras[1]);
                removidos[qtRemovidos]=lista.remover(identificador);
                qtRemovidos++;
            }
            else if(palavras[0].equals("II")){
                int identificador=Integer.parseInt(palavras[1]);
                lista.inserirInicio(jogadores[identificador]);
            }
            else if(palavras[0].equals("IF")){
                int identificador=Integer.parseInt(palavras[1]);
                lista.inserirFinal(jogadores[identificador]);
            }
            else if(palavras[0].equals("I*")){
                int pos=Integer.parseInt(palavras[1]);
                int identificador=Integer.parseInt(palavras[2]);
                lista.inserir(jogadores[identificador],pos);
            }
            
        }
        //impressao dos removidos
        for(int i=0;i<qtRemovidos;i++){
            System.out.println("(R) "+removidos[i].getNome());
        }
        lista.imprime();
        scanner.close();
    }
    
}
