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
//Classe no
class NoAN{
    NoAN dir;
    NoAN esq;
    Jogador elemento;
    boolean cor;
    NoAN(Jogador elemento) {
        this.elemento = elemento;
        dir = esq = null;
        cor=false;
    }
    NoAN(Jogador elemento,boolean cor) {
        this.elemento = elemento;
        dir = esq = null;
        this.cor=cor;
    }

    NoAN(Jogador elemento, NoAN esq, NoAN dir) {
        this.elemento = elemento;
        this.dir = dir;
        this.esq = esq;
    }
}
//Classe arvore binaria
public class AlviNegra{
    static int numeroC;
    static String tempoE;
    NoAN raiz;

    public AlviNegra(){
        this.raiz=null;
    }
    public void inserir(Jogador elemento) throws Exception {
        // Se a arvore estiver vazia
        numeroC++;
        if (raiz == null) {
            raiz = new NoAN(elemento);
            // Senao, se a arvore tiver um elemento
            } else if (raiz.esq == null && raiz.dir == null) {
                numeroC++;
                if (elemento.getNome().compareTo(raiz.elemento.getNome())<0) {
                    raiz.esq = new NoAN(elemento);
                    numeroC++;
                } else {
                    raiz.dir = new NoAN(elemento);
                
                }
            // Senao, se a arvore tiver dois elementos (raiz e dir)
            } else if (raiz.esq == null) {
            if  (elemento.getNome().compareTo(raiz.elemento.getNome())<0) {
                raiz.esq = new NoAN(elemento);
                numeroC++;
    
            } else if  (elemento.getNome().compareTo(raiz.dir.elemento.getNome())<0) {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = elemento;
                numeroC++;
    
            } else {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = elemento;
                
            }
            raiz.esq.cor = raiz.dir.cor = false;
    
            // Senao, se a arvore tiver dois elementos (raiz e esq)
            } else if (raiz.dir == null) {
                numeroC++;
            if  (elemento.getNome().compareTo(raiz.elemento.getNome())>0) {
                raiz.dir = new NoAN(elemento);
                
    
            } else if (elemento.getNome().compareTo(raiz.esq.elemento.getNome())>0) {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = elemento;
                
    
            } else {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = elemento;
                
            }
            raiz.esq.cor = raiz.dir.cor = false;
    
            // Senao, a arvore tem tres ou mais elementos
            } else {
            
            inserir(elemento, null, null, null, raiz);
            }
            raiz.cor = false;
        }

    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if (pai.cor == true) {
           // 4 tipos de reequilibrios e acoplamento
            if (pai.elemento.getNome().compareTo(avo.elemento.getNome())>0){ // rotacao a esquerda ou direita-esquerda
                if (i.elemento.getNome().compareTo(pai.elemento.getNome())>0) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }
            } else { // rotacao a direita ou esquerda-direita
                if (i.elemento.getNome().compareTo(pai.elemento.getNome())<0) { 
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }
            if (bisavo == null) {
                raiz = avo;
            } else if (avo.elemento.getNome().compareTo(bisavo.elemento.getNome())<0) {
                bisavo.esq = avo;
            } else {
                bisavo.dir = avo;
            }
            // reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
            } // if(pai.cor == true)
    }

    private void inserir(Jogador elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
        if (i == null) {
        if (elemento.getNome().compareTo(pai.elemento.getNome())<0) {
            i = pai.esq = new NoAN(elemento, true);
        } else {
            i = pai.dir = new NoAN(elemento, true);
        }
        if (pai.cor == true) {
            balancear(bisavo, avo, pai, i);
        }
        } else {
        // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
        if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
            i.cor = true;
            i.esq.cor = i.dir.cor = false;
            if (i == raiz) {
                i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (elemento.getNome().compareTo(i.elemento.getNome())<0) {
                inserir(elemento, avo, pai, i, i.esq);
            } else if (elemento.getNome().compareTo(i.elemento.getNome())>0) {
                inserir(elemento, avo, pai, i, i.dir);
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
    }

    private NoAN rotacaoDir(NoAN no) {
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;
        noEsq.dir = no;
        no.esq = noEsqDir;
        return noEsq;
    }

    private NoAN rotacaoEsq(NoAN no) {
        
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private NoAN rotacaoDirEsq(NoAN no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private NoAN rotacaoEsqDir(NoAN no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }
    public boolean pesquisar(String x){
        System.out.print(x+" raiz ");
        boolean contem=pesquisar(x,raiz);
        return contem;
    }

    public boolean pesquisar(String x,NoAN i){
        boolean contem=false;
        if(i!=null){
            numeroC++;
            if(i.elemento.getNome().compareTo(x)<0){
                System.out.print(" dir");
                contem=pesquisar(x, i.dir);
            }
            else if(i.elemento.getNome().compareTo(x)>0){
                numeroC++;
                System.out.print(" esq");
                contem=pesquisar(x, i.esq);
            }
            else{
                numeroC=numeroC+1;
                contem=true;
            }       
        }
        return contem;
    }
    void caminharCentral(NoAN i){
        if(i!=null){
            caminharCentral(i.esq);
            System.out.println(i.elemento.getNome());
            caminharCentral(i.dir);
        }
    }
    //Método para criar um arquivo Log
    public static void criaLog(){
        try (FileWriter fileWriter = new FileWriter("808756_alvinegra.txt")) {
            fileWriter.write("808756\t"+numeroC+"\t"+tempoE+"\t");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)throws Exception {
        double inicio=System.nanoTime();
        Scanner scanner= new Scanner(System.in);
        String id="",nome;
        AlviNegra arvore=new AlviNegra();
        Jogador jogadores[]=new Jogador[4000];
        jogadores=Jogador.ler();// Leitura dos jogadores no csv
        //Primeira parte de entradas
        do{
            id=scanner.next();
            if(!id.equals("FIM")){
                int identificador=Integer.parseInt(id);
                arvore.inserir(jogadores[identificador]);
            }            
        }while(!id.equals("FIM"));
        scanner.nextLine();
        do{
            nome=scanner.nextLine();
            if(arvore.pesquisar(nome))System.out.println(" SIM");
            else System.out.println(" NAO");
        }while(!nome.equals("FIM"));
        scanner.close();
        double  fim=System.nanoTime();
        double tempoExecucao=fim-inicio;
        tempoE=Double.toString(tempoExecucao);
        
    }
        
}  
    

