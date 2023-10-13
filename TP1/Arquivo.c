#include <stdio.h>

int main(){
    int n;
    float x;
    
    scanf("%d",&n);
    FILE *file=fopen("teste2.txt","wb");
    int tam=n;
    while(n>0){
        scanf("%f",&x);
        fwrite(&x,sizeof(float),1,file);
        n--;
    }
    
    fclose(file);
    file=fopen("teste2.txt","rb");
        float num;
        int c=-1;
        for(int i=tam;i>0;i--){
            fseek(file,sizeof(float)*c,SEEK_END);
            fread(&num,sizeof(float),1, file);
             if(num/(int)num==1){
                printf("%d\n",(int)num);
            }
            else{
                float resto=num-(int)num;
                float c;
                c=resto*1000;
                printf("%f\n",resto);
                if(c>=1)
                    printf("%.1f\n",num);
                else if(c>=0.1)
                    printf("%.2f\n",num);
                else if(c>=0.01) printf("%.3f\n",num);
        }   
         c--;
        }
        fclose(file);
}