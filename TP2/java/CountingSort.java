import java.io.*;
import java.util.Scanner;

public class CountingSort{
//Atributos estaticos relativos ao numero de comparaçoes e ao tempo de execução do algoritimo
static int numeroC;
static int numeroM;
static String tempoE;

public static Jogador[] sort(Jogador jogadores[], int n) {
    // Encontre o jogador mais alto
    Jogador maior = getMaior(jogadores, n);

    // Array para contar o número de ocorrências de cada elemento
    int[] count = new int[maior.getAltura() + 1];
    Jogador[] ordenado = new Jogador[n];

    // Inicializar cada posição do array de contagem
    for (int i = 0; i < count.length; count[i] = 0, i++);

    // Agora, o count[i] contém o número de elementos iguais a i
    for (int i = 0; i < n; count[jogadores[i].getAltura()]++, i++);

    // Agora, o count[i] contém o número de elementos menores ou iguais a i
    for (int i = 1; i < count.length; count[i] += count[i - 1], i++);

    // Ordenando
    for (int i = n - 1; i >= 0; ordenado[count[jogadores[i].getAltura()] - 1] = jogadores[i], count[jogadores[i].getAltura()]--, i--);

    // Copiando para o array original
    for (int i = 0; i < n; jogadores[i] = ordenado[i], i++);
    return ordenado;
}

public static Jogador getMaior(Jogador jogadores[], int n) {
    Jogador maior = jogadores[0];

    for (int i = 1; i < n; i++) {
        if (maior.getAltura() < jogadores[i].getAltura()) {
            maior = jogadores[i];
        } else if (maior.getAltura() == jogadores[i].getAltura()) {
            int comparacao = maior.getNome().compareTo(jogadores[i].getNome());
            if (comparacao < 0) {
                maior = jogadores[i];
            }
        }
    }

    return maior;
}
    //Método para impressão dos atributos de jogadores presentes no array 
     public static void imprimiArray(Jogador jogadores[],int tam){
        for(int i=0;i<tam;i++){
              System.out.println("["+jogadores[i].getId()+" ## "+jogadores[i].getNome()+" ## "+jogadores[i].getAltura()+" ## "+jogadores[i].getPeso()+" ## "+jogadores[i].getAnoNascimento()+" ## "+jogadores[i].getUniversidade()+" ## "+jogadores[i].getCidadeNascimento()+" ## "+jogadores[i].getEstadoNascimento()+"]");
        }
    }

    //Método para criar um arquivo Log
    public static void criaLog(){
        try (FileWriter fileWriter = new FileWriter("808756_insercao.txt")) {
            fileWriter.write("808756\t"+numeroC+"\t"+numeroM+"\t"+tempoE+"\t");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Inicio da main
    public static void main(String[] args) {
        
        double inicio=System.nanoTime();
        Scanner scanner= new Scanner(System.in);
        String id="";
        Jogador jogadores[]=new Jogador[4000];
        Jogador jogadoresSub[]=new Jogador[4000];
        int c=0;
        //Criaçao do subarray com os ids digitados
        do{
            id=scanner.next();
            if(!id.equals("FIM")){
                int identificador=Integer.parseInt(id);
                jogadores=Jogador.ler();
                jogadoresSub[c]=jogadores[identificador];
                c++;
            }            
        }while(!id.equals("FIM"));
       //Chamada do metodo para ordenaçao do subarray
        Jogador jogadoresO[]=new Jogador[4000];
        jogadoresO=sort(jogadoresSub, c);
        imprimiArray(jogadoresO, c);
        
        //Calculo final do tempo e criaçao do log
        double  fim=System.nanoTime();
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
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jogadores;
    }
      
}


