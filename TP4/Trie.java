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
class No {
    public char elemento;
    public final int tamanho = 255;
    public No[] prox;
    public boolean folha;
    
    public No (){
        this(' ');
    }

    public No (char elemento){
        this.elemento = elemento;
        prox = new No [tamanho];
        for (int i = 0; i < tamanho; i++) prox[i] = null;
        folha = false;
    }

    public static int hash (char x){
        return (int)x;
    }
}
//Classe arvore binaria
public class Trie{
    static int numeroC;
    static String tempoE;
    No raiz;

    public Trie(){
        raiz = new No();
    }

    public void inserir(String s) throws Exception {
        inserir(s, raiz, 0);
    }

    private void inserir(String s, No no, int i) throws Exception {
        //se nao tiver a letra procurada no array prox

        if(no.prox[s.charAt(i)] == null){
            no.prox[s.charAt(i)] = new No(s.charAt(i));

            if(i == s.length() - 1){//fim da palavra
                no.prox[s.charAt(i)].folha = true;
            }else{
                inserir(s, no.prox[s.charAt(i)], i + 1);//chama recursivamente passando o no atual e um indice acrescido de 1
            }

        } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1){
            inserir(s, no.prox[s.charAt(i)], i + 1);//chama recursivamente passando o no atual e um indice acrescido de 1

        } else {
            throw new Exception("Erro ao inserir!");
        } 
    }
    public boolean busca(String s) throws Exception {
        return busca(s, raiz, 0);
    }

    private boolean busca(String s, No no, int i) throws Exception {
        
        boolean achou=true;
        if(no!=null&&i<s.length()){
            if(no.prox[s.charAt(i)]==null)achou=false;
            else{
                return achou=busca(s, no.prox[s.charAt(i)], i+1);
            }
        }
        return achou;
    }
    public void mostrar(){
        mostrar("", raiz);
    }

    public void mostrar(String s, No no) {
        if(no.folha == true){
            System.out.println("Palavra: " + (s + no.elemento));
        } else {
            for(int i = 0; i < no.prox.length; i++){
                if(no.prox[i] != null){
                    mostrar(s + no.elemento, no.prox[i]);
                }
            }
        }
    }
    public Trie merge(Trie arv)throws Exception{
        merge("", raiz,arv);
        return arv;
    }

    public Trie merge(String s, No no,Trie arv)throws Exception {
        if(no.folha == true){
            String palavra=s+no.elemento;
            arv.inserir(palavra.trim());
        }
        else {
            for(int i = 0; i < no.prox.length; i++){
                if(no.prox[i] != null){
                    merge(s + no.elemento, no.prox[i],arv);
                }
            }
        }
        return arv;
    }
    
    //Método para criar um arquivo Log
    public static void criaLog(){
        try (FileWriter fileWriter = new FileWriter("808756_treeSort.txt")) {
            fileWriter.write("808756\t"+numeroC+"\t"+tempoE+"\t");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)throws Exception {
        double inicio=System.nanoTime();
        Scanner scanner= new Scanner(System.in);
        String id="",nome,id2="";
        Trie arvore=new Trie();
        Trie arvore2=new Trie();
        Trie arvore3=new Trie();
        Jogador jogadores[]=new Jogador[4000];
        jogadores=Jogador.ler();// Leitura dos jogadores no csv

        //Primeira parte de entradas
        do{
            id=scanner.next();
            if(!id.equals("FIM")){
                int identificador=Integer.parseInt(id);
                arvore.inserir(jogadores[identificador].getNome());
            }            
        }while(!id.equals("FIM"));
        scanner.nextLine();
        do{
            id2=scanner.next();
            if(!id2.equals("FIM")){
                int identificador=Integer.parseInt(id2);
                if(!arvore.busca(jogadores[identificador].getNome())){
                    arvore2.inserir(jogadores[identificador].getNome());
                }
            }            
        }while(!id2.equals("FIM"));
        //Fazendo o merge
        arvore3=arvore.merge(arvore3);
        arvore3=arvore2.merge(arvore3);
        scanner.nextLine();

        do{
            nome=scanner.nextLine();
            if(!nome.equals("FIM")){
                System.out.print(nome+" ");
                if(arvore3.busca(nome))System.out.println(" SIM");
                else System.out.println(" NAO");
            }
        }while(!nome.equals("FIM"));
        scanner.close();
        double  fim=System.nanoTime();
        double tempoExecucao=fim-inicio;
        tempoE=Double.toString(tempoExecucao);
        criaLog();
    }
}    
    

