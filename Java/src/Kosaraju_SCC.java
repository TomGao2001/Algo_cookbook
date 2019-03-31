import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Kosaraju_SCC {
    //----------test-------actual---
    //-Nodes-----9---------875714---
    //-Edges----11--------5105043---
    //------------------------------
    private static int numNodes = 9;
    private static int numEdges = 11;
    private static int[] max = {0,0,0,0,0};

    private static int[][] graph = new int[numEdges][2];
    private static int[][] grev = new int[numEdges][2];

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        Scanner sc = new Scanner(new File("SCC_test.txt"));
        long j = 0;
        for(int i = 0;i < numEdges; i++){
            String[] s = sc.nextLine().split(" ");
            graph[i][0] = Integer.parseInt(s[0]);
            graph[i][1] = Integer.parseInt(s[1]);
            grev[i][1] = Integer.parseInt(s[0]);
            grev[i][0] = Integer.parseInt(s[1]);
        }
        sc.close();
        Arrays.sort(grev, Comparator.comparingInt(o -> o[0]));
        System.out.println("import done after "+(System.currentTimeMillis()-startTime)+" ms.");

        int[] times = finishingTime(grev);


        Arrays.sort(max);
        for(int i : max)
            System.out.print(i + " ");
    }
    private static int[] finishingTime(int[][] grev){//IN REVERSE!!
        boolean[] hasBeenTo = new boolean[numNodes];
        int[] result = new int[numNodes];
        int[] leader = new int[numNodes];
        int done = 0, source = -1;
        for(int i = numNodes; i >= 0; i--){

        }
        return result;
    }
    private static void DFS(int[][] graph, int node, boolean[] hasBeenTo, int[] leader, int s){
        hasBeenTo[node] = true;
        leader[node] = s;


    }
    private static void updateMaxCluster(int size){
        int min = Integer.MAX_VALUE, min_idx = -1;
        for(int i = 0;i < max.length; i++){
            if(max[i] < min){
                min = max[i];
                min_idx = i;
            }
        }
        if(size > min)
            max[min_idx] = size;
    }
    private static int[] getSearchs(int[][] graph, int target){

    }
}
