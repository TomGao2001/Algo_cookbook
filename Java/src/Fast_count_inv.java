import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Fast_count_inv {
    private static int length = 100000;
    private static int[] ints = new int[length];
    private static long inversions = 0;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Fast_count_inv_array_100000.txt"));
        for(int i = 0;i < length; i++){
            String s = sc.nextLine();
            ints[i] = Integer.parseInt(s);
        }
        sc.close();
        System.out.println("import done");
        //sort_count_helper(ints);
        brute_force_n2(ints);
        System.out.println(inversions);
    }
    private static void sort_count_helper(int[] original){
        int[] tmp = new int[original.length];
        sort_count(original, tmp, 0, length - 1);
    }
    private static void sort_count(int[] original, int[] tmp, int left, int right){
        int mid = 0;
        if(right > left){
            mid = (left + right) / 2;
            //left, right
            sort_count(original, tmp, left, mid);
            sort_count(original, tmp, mid+1, right);
            //merge
            merge(original, tmp, left, mid+1, right);
        }
    }
    private static void merge(int[] original, int[] tmp, int left, int mid, int right){
        //------orig
        int i = left, j = mid, k = left;
        while((i < mid) && (j <= right)){
            if(original[i] < original[j]) {
                tmp[k] = original[i];
                i++;
            }else{
                tmp[k] = original[j];
                j++;
            }
            k++;
            inversions += (mid - i);
        }
        //for(k = left; k < right)

        while(i < mid)
            tmp[k++] = original[i++];
        while(j <= right)
            tmp[k++] = original[j++];
        for(i = left; i <= right; i++)
            original[i] = tmp[i];
    }


    private static void brute_force_n2(int[] original){//actual answer for 100000.txt: 2407905288
        for(int i = 0;i < length; i++){
            for(int j = i + 1; j < length; j++){
                if(original[i] > original[j])
                    inversions++;
            }
        }
    }

}
