import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {//PASSED
    private static int pivotChoice = 0; //0: first element, 1: last element, 2: median-of-three (8,2,4,5,7,1)->use 4
    private static int length = 10000;
    private static int[] initial = new int[length];
    private static int[] ints = new int[length];
    private static long comparisons = 0;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("QuickSort.txt"));
        for(int i = 0;i < length; i++)
            initial[i] = Integer.parseInt(sc.nextLine());
        sc.close();
        System.out.println("import done");

        ints = Arrays.copyOf(initial, length);
        QSort(0,length-1);
        System.out.println("Total comparisons (first element): "+comparisons);

        comparisons = 0;
        pivotChoice = 1;
        ints = Arrays.copyOf(initial, length);
        QSort(0,length-1);
        System.out.println("Total comparisons (last element): "+comparisons);

        comparisons = 0;
        pivotChoice = 2;
        ints = Arrays.copyOf(initial, length);
        QSort(0,length-1);
        System.out.println("Total comparisons (median-o'-three): "+comparisons);


    }
    private static void QSort(int start, int end){
        if(end - start < 1) return;
        int newPos = partition(start, end, choosePivotPos(start, end));
        QSort(start, newPos-1);
        QSort(newPos+1, end);
    }
    private static int choosePivotPos(int start, int end){//returns VALUE
        switch(pivotChoice){
            case 0:
                return start;
            case 1:
                return end;
            default:
                int[] chooseMedianPivot = new int[3];
                chooseMedianPivot[0] = ints[start];
                chooseMedianPivot[2] = ints[end];
                chooseMedianPivot[1] = ints[start + (end - start) / 2];
                Arrays.sort(chooseMedianPivot);
                if(chooseMedianPivot[1] == ints[start])
                    return start;
                else if(chooseMedianPivot[1] == ints[end])
                    return end;
                else
                    return (start + (end - start) / 2);
        }
    }

    private static int partition(int start, int end, int pivotIdx){
        comparisons += (end - start);
        int pivot = ints[pivotIdx];
        swap(start, pivotIdx);

        int i = start + 1;
        for(int j = start + 1; j <= end; j++){
            if(ints[j] < pivot){
                swap(i,j);
                i++;
            }
        }
        swap(start, i-1);
        return i-1;
    }

    private static void swap(int idxa, int idxb){
        int a = ints[idxa];
        ints[idxa] = ints[idxb];
        ints[idxb] = a;
    }
}
