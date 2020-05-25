package calculadora;

public class Calculadora {

    private int ans;

    public Calculadora(){
        ans = 0;
    }


    public int add (int a, int b){
        ans = a+b;
        return ans;
    }

    public int substract (int a, int b){
        ans = a-b;
        return ans;
    }

    public int add (int v){
        ans += v;
        return ans;
    }

    public int substract (int v){
        ans -= v;
        return ans;
    }

    public double multiply(double a, double b){
        return a*b;
    }

    public int div (int a, int b){
        if (b ==0){
            throw new ArithmeticException("Can't divide 0");
        }
        ans = a/b;
        return ans;
    }

    public int ans(){
        return ans;
    }

    public void clear (){
        ans = 0;
    }

    public void thisMethodNeedToWorkFast(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
