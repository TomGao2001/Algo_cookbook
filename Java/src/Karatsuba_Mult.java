import java.util.Arrays;
import java.util.Collections;

public class Karatsuba_Mult {
    private static int[] a = {3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6,2,6,4,3,3,8,3,2,7,9,5,0,2,8,8,4,1,9,7,1,6,9,3,9,9,3,7,5,1,0,5,8,2,0,9,7,4,9,4,4,5,9,2};
    private static int[] b = {2,7,1,8,2,8,1,8,2,8,4,5,9,0,4,5,2,3,5,3,6,0,2,8,7,4,7,1,3,5,2,6,6,2,4,9,7,7,5,7,2,4,7,0,9,3,6,9,9,9,5,9,5,7,4,9,6,6,9,6,7,6,2,7};
    private static int[] result;
    private static int[] aa = {9,9,9,9}, bb = {5,6,8};

    public static void main(String[] args) {
        result = add(aa,bb);
        for(int i : result)
            System.out.print(i);
        System.out.println();
    }
    /*private static int[] multiply(int[] x, int[] y){
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


        }

    }*/
    private static int[] add(int[] x, int[] y){
        int[] result = new int[Math.max(x.length, y.length)+1];
        for(int i = result.length - 1;i > 0; i--){
            result[i] = (x.length < i? 0 : x[i-1]) + (y.length < i? 0 : y[i-1]);
        }
        for(int i = result.length - 1;i >= 0; i--){
            if(result[i] > 9){
                result[i-1] += (result[i] / 10);
                result[i] %= 10;
            }
        }
        int startidx = 0;
        while(result[startidx] == 0)startidx++;
        return Arrays.copyOfRange(result, startidx, result.length);
    }
}
