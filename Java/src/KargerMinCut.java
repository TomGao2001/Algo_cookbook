import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class KargerMinCut {
    private static int size = 200;
    private static int trials = 100;
    private static int minCut = Integer.MAX_VALUE;
    private static Random r = new Random();
    private static int[][] adjList = new int[size][size+10];//as numbers
    private static boolean[] stillAvailableList = new boolean[size];

    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("KargerMinCut.txt"));
        for(int i = 0;i < size; i++){
            stillAvailableList[i] = true;
            String[] s = sc.nextLine().split("\t");
            int idx = 0;
            for(int j = 1; j < s.length; j++){
                adjList[i][idx] = Integer.parseInt(s[j]) - 1;
                idx++;
            }
            for(int k = idx; k < adjList[0].length; k++){
                adjList[i][k] = -1;
            }
        }
        sc.close();

        for(int i = 0;i < trials; i++){
           minCut = Math.min(minCut, KargerTrial(adjList));
        }
        System.out.println(minCut);
    }

    private static int KargerTrial(int[][] inputMat){
        int[][] adjMat = inputMat.clone();
        while(getVerticesStillAvailable() > 2){
            int idxa = genA();
            int idxb = genB(adjMat[idxa]);
            removePair(adjMat, idxa, idxb);
        }
        return getConnections(adjMat);
    }

    private static int genA(){
        int[] list = new int[getVerticesStillAvailable()];
        int idx = 0;
        for(int i = 0;i < stillAvailableList.length; i++)
            if(stillAvailableList[i]) {
                list[idx] = i;
                idx++;
            }
        return genB(list);
    }

    private static int genB(int[] list){//-1 protection
        ArrayList<Integer> a = new ArrayList<>(list.length);
        for(int i : list){
            if(i != -1)
                a.add(i);
        }
        return a.get(r.nextInt(a.size()));
    }

    private static void removePair(int[][] inputMat, int a, int b){//preserve a

        int idx = 0;
        while(idx < inputMat[a].length && inputMat[a][idx] != -1) idx++;


        for(int i = 0;i < inputMat[b].length; i++){
            if(inputMat[b][i] != -1) {
                inputMat[a][idx] = inputMat[b][i]; //OOB on size
                inputMat[b][i] = -1;
                idx++;
            }
        }//add other side

        replaceAll(inputMat, b, a);
        removeSelfLoops(inputMat, a, b);
        stillAvailableList[b] = false;

    }

    private static void replaceAll(int[][] inputMat, int original, int replacement){//good
        for(int i = 0;i < inputMat.length; i++){
            for(int j = 0;j < inputMat[0].length; j++){
                if(inputMat[i][j] == original){
                    inputMat[i][j] = replacement;
                }
            }
        }
    }

    private static void removeSelfLoops(int[][] inputMat, int n, int otherone){//good?
        ArrayList<Integer> goodBois = new ArrayList<>(inputMat[n].length);
        for(int i = 0;i < inputMat[n].length; i++){
            if(inputMat[n][i] != n && inputMat[n][i] != otherone) {
                goodBois.add(inputMat[n][i]);
            }
            inputMat[n][i] = -1;
        }
        for(int i = 0;i < goodBois.size(); i++){
            inputMat[n][i] = goodBois.get(i);
        }
    }

    private static int getVerticesStillAvailable(){
        int sum = 0;
        for(boolean b : stillAvailableList)
            if(b)
                sum++;
        return sum;
    }

    private static int getConnections(int[][] finallist) {
        int idx = 0;
        while(!stillAvailableList[idx])
            idx++;

        int sum = 0;
        for (int i = 0; i < finallist[idx].length; i++)
            if (finallist[idx][i] != -1)
                sum++;
        return sum;
    }
}
