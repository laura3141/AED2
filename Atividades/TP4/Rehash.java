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

//Classe arvore binaria
public class Rehash{
    static int numeroC;
    static String tempoE;

    Jogador tabela[];
    int total;
    final Jogador NULO = null;

    public Rehash() {
        this(25);
    }

    public Rehash(int total) {
        this.total=total;
        this.tabela = new Jogador[this.total];
        for (int i = 0; i < total; i++) {
            tabela[i] = NULO;
        }
    }
    public int h(Jogador jogador) {
        return jogador.getAltura() % total;
    }
    public int reh(Jogador jogador) {
        return (jogador.getAltura()+1)% total;
    }

    public boolean inserir(Jogador jogador){
        boolean sucesso=false;
        int i=h(jogador);
        numeroC++;
        if(tabela[i]==null){
            tabela[i]=jogador;
            sucesso=true;
        }
        else{
            i=reh(jogador);
            numeroC++;
            if(tabela[i]==null){
                tabela[i]=jogador;
                sucesso=true;
            }
        }
        return sucesso;
    }

    boolean pesquisar(String nome){
        boolean contem=false;
        for(int i=0;i<total;i++){
            numeroC++;
            if(tabela[i]!=null){
                numeroC++;
                if(tabela[i].getNome().equals(nome)){
                    contem=true;
                    i=total;
                }
            }
        }
        return contem;
    }
    void printa(){
        for(int i=0;i<total;i++){
            if (tabela[i] != null) {
                System.out.println(i+" "+tabela[i].getNome());
            }
        }
    }
    //Método para criar um arquivo Log
    public static void criaLog(){
        try (FileWriter fileWriter = new FileWriter("808756_hashRehash.txt")) {
            fileWriter.write("808756\t"+numeroC+"\t"+tempoE+"\t");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        double inicio=System.nanoTime();
        Scanner scanner= new Scanner(System.in);
        String id="",nome;
        Rehash hash=new Rehash();
        Jogador jogadores[]=new Jogador[4000];
        jogadores=Jogador.ler();// Leitura dos jogadores no csv
        //Primeira parte de entradas
        do{
            id=scanner.next();
            if(!id.equals("FIM")){
                int identificador=Integer.parseInt(id);
                hash.inserir(jogadores[identificador]);
            }            
        }while(!id.equals("FIM"));
        scanner.nextLine();
        do{
            nome=scanner.nextLine();
            if(!nome.equals("FIM")){
                System.out.print(nome+" ");
                if(hash.pesquisar(nome)) System.out.println("SIM");
                else System.out.println("NAO");
            }
        }while(!nome.equals("FIM"));
        scanner.close();
        double fim=System.nanoTime();
        double tempoExecucao=(fim-inicio)/1000000000;
        tempoE=Double.toString(tempoExecucao);
        criaLog();
    }
}    
    

