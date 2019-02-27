import java.util.Arrays;
import java.util.Collections;
//UNFINISHED
public class Karatsuba_Mult {
    private static int[] a = {3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6,2,6,4,3,3,8,3,2,7,9,5,0,2,8,8,4,1,9,7,1,6,9,3,9,9,3,7,5,1,0,5,8,2,0,9,7,4,9,4,4,5,9,2};
    private static int[] b = {2,7,1,8,2,8,1,8,2,8,4,5,9,0,4,5,2,3,5,3,6,0,2,8,7,4,7,1,3,5,2,6,6,2,4,9,7,7,5,7,2,4,7,0,9,3,6,9,9,9,5,9,5,7,4,9,6,6,9,6,7,6,2,7};
    private static int[] result;
    private static int[] aa = {9}, bb = {1,0,0,0,0,0};

    public static void main(String[] args) {
        int[] multiply = subtract(bb,aa);//multiply(aa,bb);
        for(int i : multiply)
            System.out.print(i);
    }
    private static int[] multiply(int[] x, int[] y){
        if(x.length == 1 && y.length == 1){
            int tmp = x[0] * y[0];
            if (tmp > 9){
                return new int[]{tmp/10,tmp%10};
            }else{
                return new int[]{tmp};
            }
        }else{
            int n = x.length;
            int[] a = Arrays.copyOfRange(x,0,n/2);
            int[] b = Arrays.copyOfRange(x, n/2, n);
            int[] c = Arrays.copyOfRange(y,0,n/2);
            int[] d = Arrays.copyOfRange(y, n/2, n);
            int[] ac = multiply(a,c);
            int[] bd = multiply(b,d);
            int[] adbc = add(multiply(a,d),multiply(b,c));
            int[] firstPart = multiply(genpow10Array(n),ac);
            int[] secondPart = multiply(genpow10Array(n/2),adbc);
            return add(add(firstPart, secondPart),bd);
        }
    }

    private static int[] genpow10Array(int n){
        int[] result = new int[n+1];
        result[0] = 1;
        for(int i = 1; i < result.length; i++){
            result[i] = 0;
        }
        return result;
    }
    private static int[] add(int[] x, int[] y){//passed
        int[] result = new int[Math.max(x.length, y.length)+1];
        for(int i = 0;i < result.length; i++){
            if(x.length - 1 - i >= 0){
                result[result.length-1-i] += x[x.length - 1 - i];
            }
            if(y.length - 1 - i >= 0){
                result[result.length-1-i] += y[y.length - 1 - i];
            }
            if(result[result.length-1-i] > 9){
                result[result.length-2-i] += result[result.length-1-i]/10;
                result[result.length-1-i] %= 10;
            }
        }
        int startidx = 0;
        while(startidx < result.length && result[startidx] == 0)startidx++;
        if(startidx == result.length)
            return new int[]{0};
        return Arrays.copyOfRange(result, startidx, result.length);
    }

    private static int[] subtract(int[] x, int[] y){ //x-y
        //if(y.length > x.length)
          //  return negative(subtract(y,x));

        int[] result = new int[Math.max(x.length, y.length)];
        for(int i = 0; i < result.length; i++){
            result[result.length-1-i] = (x.length-1-i >= 0? x[x.length - i - 1] : 0) - (y.length-1-i >= 0?y[y.length - 1 - i]:0);
            if((result[result.length-1-i] < 0 && result.length-1-i >= 0)){
                result[result.length-1-i] += 10;
                result[result.length-2-i]--;
            }
        }
        int startidx = 0;
        while(startidx < result.length && result[startidx] == 0)startidx++;
        if(startidx == result.length)
            return new int[]{0};
        return Arrays.copyOfRange(result, startidx, result.length);
    }

    private static int[] negative(int[] x){
        for(int i = 0;i < x.length; i++)
            x[i] = -x[i];
        return x;
    }
}
