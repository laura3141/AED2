public class FibonacciRecursivo {
    static int fibonacci(int x){
        int fib=1;
        if(x>1){
            fib=fibonacci(x-1)+fibonacci(x-2);  
        }
        return fib;
    } 
    public static void main(String[] args) {
        int m=fibonacci(4);
        System.out.println(m);
    }
}
