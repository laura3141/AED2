import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

class LeituraHTML {
   public static String getHtml(String endereco){
         URL url;
         InputStream is = null;
         BufferedReader br;
         String resp = "", line;

         try {
         url = new URL(endereco);
         is = url.openStream();
         br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

         while ((line = br.readLine()) != null) {
            resp += line + "\n";
         }
         } catch (MalformedURLException mue) {
         mue.printStackTrace();
         } catch (IOException ioe) {
         ioe.printStackTrace();
         } 

         try {
         is.close();
         } catch (IOException ioe) {


         }

         return resp;
         }
         static void quantidades(String html,int x[]){
         for(int i=0;i<html.length();i++){
         if(html.charAt(i)=='a')x[0]++;
         else if(html.charAt(i)=='e')x[1]++;
         else if(html.charAt(i)=='i')x[2]++;
            else if(html.charAt(i)=='o')x[3]++;
            else if(html.charAt(i)=='u')x[4]++;

            

         else if(html.charAt(i)==225)x[5]++;


         else if(html.charAt(i)==233)x[6]++;


         else if(html.charAt(i)==237)x[7]++;


         else if(html.charAt(i)==243)x[8]++;

         else if(html.charAt(i)==250)x[9]++;


         else if(html.charAt(i) == 224){
            x[10]++;
         }
         else if(html.charAt(i) == 232){
            x[11]++;
         }
         else if(html.charAt(i) == 236){
         x[12]++;
         }
         else if(html.charAt(i) == 242){
            x[13]++;
         } 
         else if(html.charAt(i)==249)x[14]++;

         else if(html.charAt(i)==227)x[15]++;
         else if(html.charAt(i)==245)x[16]++;
         else if(html.charAt(i)==226)x[17]++;
         else if(html.charAt(i)==234)x[18]++;
         else if(html.charAt(i)==238)x[19]++;
         else if(html.charAt(i)==244)x[20]++;
         else if(html.charAt(i)==251)x[21]++;

         else if((html.charAt(i)>96&&html.charAt(i)<123))x[22]++;
         else if(html.charAt(i)==60&&html.charAt(i+1)=='b'&&html.charAt(i+2)=='r'&&html.charAt(i+3)==62){
            x[23]++;
            x[22]=x[22]-2;
         }
         else if(html.charAt(i)==60&&html.charAt(i+1)=='t'&&html.charAt(i+2)=='a'&&html.charAt(i+3)=='b'&&html.charAt(i+4)=='l'&&html.charAt(i+5)=='e'&&html.charAt(i+6)==62){
            x[24]++;
            x[0]--;
            x[1]--;
            x[22]=x[22]-3;
         }
      }
   }
   public static void main(String[] args) {
      String endereco, html,nome;
      int array[]=new int[25];
      
      do{
         
         nome=MyIO.readLine();
         if(!nome.equals("FIM")){
            endereco =MyIO.readLine();
            html = getHtml(endereco);
            quantidades(html,array);
            MyIO.print("a"+"("+array[0]+") ");
            System.out.print("e"+"("+array[1]+") ");
            System.out.print("i"+"("+array[2]+") ");
            System.out.print("o"+"("+array[3]+") ");
            System.out.print("u"+"("+array[4]+") ");
            System.out.print(((char)225)+"("+array[5]+") ");
            System.out.print(((char)233)+"("+array[6]+") ");
            System.out.print(((char)237)+"("+array[7]+") ");
            System.out.print(((char)243)+"("+array[8]+") ");
            System.out.print(((char)250)+"("+array[9]+") ");
            System.out.print(((char)224)+"("+array[10]+") ");
            System.out.print(((char)232)+"("+array[11]+") ");
            System.out.print(((char)236)+"("+array[12]+") ");
            System.out.print(((char)242)+"("+array[13]+") ");
            System.out.print(((char)249)+"("+array[14]+") ");
            System.out.print(((char)227)+"("+array[15]+") ");
            System.out.print(((char)245)+"("+array[16]+") ");
            System.out.print(((char)226)+"("+array[17]+") ");
            System.out.print(((char)234)+"("+array[18]+") ");
            System.out.print(((char)238)+"("+array[19]+") ");
            System.out.print(((char)244)+"("+array[20]+") ");
            System.out.print(((char)251)+"("+array[21]+") ");
            System.out.print("consoante"+"("+array[22]+") ");
            System.out.print("<br>"+"("+array[23]+") ");
            System.out.print("<table>"+"("+array[24]+") ");
            System.out.print(nome);
            System.out.println("");
            for(int i=0;i<25;i++){
               array[i]=0;
            }
         }
      }while(!nome.equals("FIM"));
     
      
   }
}