public class Retangulo{
    private double base;
    private double altura;
    Retangulo(){
        
    }
    Retangulo(double altura,double base){
        this.base=base;
        this.altura=altura;
    }
    double  getArea(){
        return base*altura;
    }
    double  getPerimetro(){
        return base*2+altura*2;
    }
    double  getDiagonal(){
        return base;
    }
}
