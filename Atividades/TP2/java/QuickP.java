import java.io.*;
import java.util.Scanner;

//Classe Ordenaçao por Seleçao
public class QuickP{
    //Atributos estaticos relativos ao numero de comparaçoes e ao tempo de execução do algoritimo
    static int numeroC=0;
    static int numeroM=0;
    static String tempoE;

   //funcao para ordenar os 10 primeiros jogadores pela ordem alfabetica do estado de nascimento
    static Jogador [] quicksortRec(Jogador jogadores[], int esq, int dir) {
        int i = esq, j = dir;
        Jogador pivo = jogadores[(dir+esq)/2];
        while (i <= j) {
            numeroC++;
            while (jogadores[i].getEstadoNascimento().compareTo(pivo.getEstadoNascimento())<=0){
                numeroC=numeroC+2;
                if(jogadores[i].getEstadoNascimento().compareTo(pivo.getEstadoNascimento())==0){
                    numeroC++;
                    if(jogadores[i].getNome().compareTo(pivo.getNome())<0) i++;
                    else break;
                }
                else i++;
            }   
            while (pivo.getEstadoNascimento().compareTo(jogadores[j].getEstadoNascimento())<=0){
            numeroC=numeroC+2;
                if (pivo.getEstadoNascimento().compareTo(jogadores[j].getEstadoNascimento())==0){
                    numeroC++;
                    if(pivo.getNome().compareTo(jogadores[j].getNome())<0) j--;
                    else break;
                }
                else j--;
            }
            if (i <= j) {
                numeroM++;
                Jogador aux=jogadores[j];
                jogadores[j]=jogadores[i];
                jogadores[i]=aux;
                i++;
                j--;
            }
        }
        if (esq < j){
            quicksortRec(jogadores, esq, j);
        } 
        if (i < dir&&i<10) quicksortRec(jogadores, i, dir);
        return jogadores;
    }
    
    //Método para impressão dos atributos de jogadores presentes no array 
    public static void imprimiArray(Jogador jogadores[],int tam){
        for(int i=0;i<tam;i++){
              System.out.println("["+jogadores[i].getId()+" ## "+jogadores[i].getNome()+" ## "+jogadores[i].getAltura()+" ## "+jogadores[i].getPeso()+" ## "+jogadores[i].getAnoNascimento()+" ## "+jogadores[i].getUniversidade()+" ## "+jogadores[i].getCidadeNascimento()+" ## "+jogadores[i].getEstadoNascimento()+"]");
        }
    }

    //Método para criar um arquivo Log
    public static void criaLog(){
        try (FileWriter fileWriter = new FileWriter("808756_quicksortParcial.txt")) {
            fileWriter.write("808756\t"+numeroC+"\t"+numeroM*3+"\t"+tempoE+"\t");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Inicio da main
    public static void main(String[] args) {
        double inicio=System.nanoTime();
        Scanner scanner= new Scanner(System.in);
        String id;
        Jogador jogadores[]=new Jogador[4000];
        Jogador jogadoresSub[]=new Jogador[4000];
        int c=0;
        //Criaçao do subarray com os ids digitados
        do{
            id=scanner.nextLine();
            if(!id.equals("FIM")){
                int identificador=Integer.parseInt(id);
                jogadores=Jogador.ler();
                jogadoresSub[c]=jogadores[identificador];
                c++;
            }
                        
        }while(!id.equals("FIM"));
       //Chamada do metodo para ordenaçao do subarray
        Jogador jogadoresO[]=new Jogador[4000];
        jogadoresO=quicksortRec(jogadoresSub,0,c-1);
        imprimiArray(jogadoresO, 10);
        
        //Calculo final do tempo e criaçao do log
        double fim=System.nanoTime();
        double tempoExecucao=fim-inicio;
        tempoE=Double.toString(tempoExecucao);
        criaLog();
        scanner.close();
    }
}
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
        String nomeArquivo = "/tmp/players.csv";
        Jogador jogadores[]=new Jogador[4000];
        int qtJ=-1; //inicializado com -1 para que o loop while seja executado ignorando a primeira linha
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
            leitor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jogadores;
    }
      
}


