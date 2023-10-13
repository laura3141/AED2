import java.io.*;
import java.util.Scanner;

class Jogador{
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNasc;
    private String cidadeNasc;
    private String estadoNasc;

    Jogador(){ }

    Jogador(int id, String nome, int altura, int peso, int anoNasc, String cidadeNasc, String estadoNasc){ 
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.anoNasc = anoNasc;
        this.cidadeNasc = cidadeNasc;
        this.estadoNasc = estadoNasc;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public int getAnoNasc(){
        return anoNasc;
    }

    public String getUniversidade(){
        return universidade;
    }
    
    public void setUniversidade(String universidade){
        this.universidade = universidade;
    }   

    public void clone(Jogador j){
        id = j.id;
        nome = j.nome;
        altura = j.altura;
        peso = j.peso;
        universidade = j.universidade;
        anoNasc = j.anoNasc;
        cidadeNasc = j.cidadeNasc;
        estadoNasc = j.estadoNasc;
    }

    public void imprimir(){
        System.out.println("["+id+" ## "+nome+" ## "+altura+" ## "+peso+" ## "+anoNasc+" ## "
        +universidade+" ## "+cidadeNasc+" ## "+estadoNasc+"]");
    }

    public void ler(String linha){
        String atributos[];
        atributos = split(linha, ',');
        id = Integer.parseInt(atributos[0]);
        nome = atributos[1];
        altura = Integer.parseInt(atributos[2]);
        peso = Integer.parseInt(atributos[3]);
        universidade = atributos[4];
        anoNasc = Integer.parseInt(atributos[5]);
        cidadeNasc = atributos[6];
        estadoNasc = atributos[7];
    }

    private String[] split(String s, char token){
        String atributos[] = new String[8];
        for(int i = 0; i < 8; i++){
            atributos[i] = "";
        }
        int pos = 0;
        boolean flag = false;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != token){
                atributos[pos] += s.charAt(i);
                flag = false;
            }
            else if(flag){
                atributos[pos] = "nao informado";
                pos++;
            }
            else{
                flag = true;
                pos++;
            }
        }
        if(s.charAt(s.length() - 1) == token){
            atributos[pos] = "nao informado";
        }
        return atributos;
    }
}

public class Trabalho{
    private static Jogador listJogadores[] = new Jogador[3922];
    private static Jogador subListJogadores[] = new Jogador[3922];

    private static void lerArquivo(String filePath) throws FileNotFoundException, IOException{
        String line;
        BufferedReader buffRead = new BufferedReader(new FileReader(filePath));
        line = buffRead.readLine(); // Descarte do cabecalho
        for(int i = 0; i < 3922; i++){
            line = buffRead.readLine();
            if(line == null){
                System.out.println("Erro");
                i = 3922;
            }
            else{
                listJogadores[i] = new Jogador();
                listJogadores[i].ler(line);
            }
        }
        buffRead.close();
    }
    
    private static void subLista(int id, int c){
        subListJogadores[c] = new Jogador();
        subListJogadores[c].clone(listJogadores[id]);
    }

    private static void intercalar(int esq, int meio, int dir){
        if(esq == 116 && meio == 130 && dir == 144){
            int b = 10;
        }

        int nEsq = (meio + 1)-esq;
        int nDir = dir - meio;

        Jogador arrayEsq[] = new Jogador[nEsq+1];
        Jogador arrayDir[] = new Jogador[nDir+1];

        arrayEsq[nEsq] = new Jogador();
        arrayDir[nDir] = new Jogador();

        arrayEsq[nEsq].setUniversidade("ZZZ");
        arrayDir[nDir].setUniversidade("ZZZ");
        arrayEsq[nEsq].setNome("ZZZ");
        arrayDir[nDir].setNome("ZZZ");

        int iEsq, iDir, i;

        for(iEsq = 0; iEsq < nEsq; iEsq++){
            arrayEsq[iEsq] = new Jogador();
            arrayEsq[iEsq].clone(subListJogadores[esq + iEsq]);
        }

        for(iDir = 0; iDir < nDir; iDir++){
            arrayDir[iDir] = new Jogador();
            arrayDir[iDir].clone(subListJogadores[(meio+1) + iDir]);
        }

        for(iEsq = iDir = 0, i = esq; i <= dir; i++){
            try{
                if(arrayEsq[iEsq].getUniversidade().compareTo(arrayDir[iDir].getUniversidade()) < 0){
                    subListJogadores[i].clone(arrayEsq[iEsq++]);
                }
                else if(arrayEsq[iEsq].getUniversidade().compareTo(arrayDir[iDir].getUniversidade()) > 0){
                    subListJogadores[i].clone(arrayDir[iDir++]);
                }
                else{
                    if(arrayEsq[iEsq].getNome().compareTo(arrayDir[iDir].getNome()) <= 0){
                        subListJogadores[i].clone(arrayEsq[iEsq++]);
                    }
                    else{
                        subListJogadores[i].clone(arrayDir[iDir++]);
                    }
                }
            }
            catch(ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }
    }

    private static void mergeSort(int esq, int dir){
        if(esq < dir){
            int meio = (esq + dir)/2;
            mergeSort(esq, meio);
            mergeSort(meio + 1, dir);
            intercalar(esq, meio, dir);
        }
    }

    public static void main(String[] args) {
        String filePath = "players.csv";
        String entrada;
        int c = 0;
        int resp[] = new int[2];
        resp[0] = resp[1] = 0;
        long inicio, fim;

        try{
            lerArquivo(filePath);

            Scanner sc = new Scanner(System.in);

            while(!(entrada = sc.nextLine()).equals("FIM")){
                subLista(Integer.parseInt(entrada), c++);
            }
            sc.close();

            inicio = System.currentTimeMillis();
            mergeSort(0, c-1);
            fim = System.currentTimeMillis();

            for(int i = 0; i < c; i++){
                subListJogadores[i].imprimir();
            }

            //log
            FileWriter fw = new FileWriter("802480_mergesort.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.print("802480\t"+((fim-inicio)/1000)+"\t"+resp[0]+"\t"+resp[1]);
            fw.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}