import java.io.*;
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
//No da arvore de inteiros
class No{
    No dir,esq;
    int elemento;
    No2 outraArvore;
    No(int elemento) {
        this.elemento = elemento;
        dir = esq = null;
        outraArvore=null;
    }
    No(int elemento, No esq, No dir) {
        this.elemento = elemento;
        this.dir = dir;
        this.esq = esq;
    }
}
//No da arvore de strings
class No2{
    No2 dir;
    No2 esq;
    String elemento;
    No2(String  elemento) {
        this.elemento = elemento;
        dir = esq = null;
    }

    No2(String  elemento, No2 esq, No2 dir) {
        this.elemento = elemento;
        this.dir = dir;
        this.esq = esq;
    }
}

class arvoreBinaria{
    //atributos estaticos para auxiliar na criacao do log
    static int numeroC;
    static String tempoE;
    No raiz;
    public arvoreBinaria(){
        raiz=null;
    }
    public void inserir(int x){
        raiz=inserir(x,raiz);
    }
    //inserir os inteiros 
    public No inserir(int x,No i){
        if(i==null){
            i=new No(x);
        }
        else{
            if(i.elemento==x){
                System.out.println("Esse elemento ja existe na arvore");
            }
            else if(i.elemento>x){
                i.esq=inserir(x,i.esq);
            }
            else if(i.elemento<x){
                i.dir=inserir(x,i.dir);
            }
        }
        return i;
    }
    //metodo para inserir o nome na segunda arvore
    public void inserirNome(Jogador jogador){
        No inteiro;
        inteiro=buscaNoInteiro(jogador,raiz);
        raiz=inteiro;
    }
    //busca o no inteiro e insere o nome na sua subarvore
    public No buscaNoInteiro(Jogador jogador,No i){
        if(i!=null){
            numeroC++;
            if(i.elemento==jogador.getAltura()%15){
                i.outraArvore=inserir(jogador.getNome(),i.outraArvore);
            }
            else{
                numeroC++;
                if(i.elemento>jogador.getAltura()%15){
                    i.esq=buscaNoInteiro(jogador, i.esq);
                }
                else i.dir=buscaNoInteiro(jogador, i.dir);
            }
        }
        return i;
    }

    public No2 inserir(String x,No2 i){
        numeroC++;
        if(i==null){
            i=new No2(x);
        }
        else{
            numeroC++;
            if(x.compareTo(i.elemento)<0)i.esq=inserir(x,i.esq);
            else if(x.compareTo(i.elemento)>0)i.dir=inserir(x,i.dir);
        }
        return i;
    }

    //Metodo para buscar um nome
    void busca(String nome) {
        System.out.print(nome+" raiz");
        numeroC++;
        if (busca(raiz, nome)) {
            System.out.print(" SIM");
        } else {
            System.out.print(" NAO");
        }
    }
     //Metodo para acessar casa no inteiro
    boolean busca(No i, String nome) {
        boolean tem = false;
        numeroC++;
        if (i != null) {
            if (pesquisaNome(i.outraArvore, nome)) {
                tem = true;
            } else {
                System.out.print(" esq");
                numeroC++;
                if (busca(i.esq, nome)) {
                    tem = true;
                } else {
                    System.out.print(" dir");
                    tem = busca(i.dir, nome);
                }
            }
        }
        return tem;
    }
     //Metodo para percorrer as subarvores
    boolean pesquisaNome(No2 i, String nome) {
        boolean tem = false;
        numeroC++;
        if (i != null) {
            numeroC++;
            if(i.elemento.compareTo(nome) == 0) {
                tem = true;
            } else {
                System.out.print(" ESQ");
                numeroC++;
                if (pesquisaNome(i.esq, nome)) {
                    tem = true;
                } else {
                    System.out.print(" DIR");
                    tem = pesquisaNome(i.dir, nome);
                }
            }
        }
        return tem;
    }
    //Método para criar um arquivo Log
    public static void criaLog(){
        try (FileWriter fileWriter = new FileWriter("808756_arvoreArvore.txt")) {
            fileWriter.write("808756\t"+numeroC+"\t"+tempoE+"\t");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class ArvoredeArvore{
    public static void main(String[] args) {
        double inicio=System.nanoTime();
        Scanner scanner= new Scanner(System.in);
        String id="",nome;
        arvoreBinaria arvoreInt=new arvoreBinaria();
        Jogador jogadores[]=new Jogador[4000];
        jogadores=Jogador.ler();// Leitura dos jogadores no csv

        //Criando a arvore base dos inteiros
        arvoreInt.inserir(7);arvoreInt.inserir(3);arvoreInt.inserir(11);arvoreInt.inserir(1);arvoreInt.inserir(5);arvoreInt.inserir(9);arvoreInt.inserir(13);arvoreInt.inserir(0);arvoreInt.inserir(2);
        arvoreInt.inserir(4);arvoreInt.inserir(6);arvoreInt.inserir(8);arvoreInt.inserir(10);arvoreInt.inserir(12);arvoreInt.inserir(14);
        
        //Primeira parte de entradas
        do{
            id=scanner.next();
            if(!id.equals("FIM")){
                int identificador=Integer.parseInt(id);
                arvoreInt.inserirNome(jogadores[identificador]);
            }            
        }while(!id.equals("FIM"));
        scanner.nextLine();
        //Entrada dos nomes
        do{
            nome=scanner.nextLine();
            arvoreInt.busca(nome);
            System.out.println(" ");
        }while(!nome.equals("FIM"));
        scanner.close();
        double fim=System.nanoTime();
        double tempoExecucao=fim-inicio;
        arvoreInt.tempoE=Double.toString(tempoExecucao);
        arvoreInt.criaLog();
    }
}    
    

