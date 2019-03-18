public class PowerMethod {
    //example: A = {{4,0,15},{3,-2,3},{0,3,0}}; x0 = {{1},{0},{0}}
    private static float[][] x0 = {{1}, {0}};
    private static float[][] A = {{4,6}, {6,4}};
    private static int k = 7;

    public static void main(String[] args) {

        float[][] Axk;
        float[][] xk = x0;
        float mu = 0;
        for (int i = 0; i < k; i++) {
            Axk = matrix_mult(A, xk);
            mu = findMatrixMax(Axk);
            System.out.println("mu" + i + ": " + mu);
            xk = const_mult(Axk, 1/mu);
            System.out.println("x+1:");
            printMat(xk);
        }
    }

    private static float[][] matrix_mult(float[][] a, float[][] b) {
        if (a[0].length != b.length)
            throw new IllegalArgumentException("matrix size mismatch in multiply");
        float[][] product = new float[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    product[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return product;
    }

    private static float findMatrixMax(float[][] a) {
        float max = 0;
        for (float[] i : a)
            for (float j : i)
                if (Math.abs(j) > Math.abs(max))
                    max = j;
        return max;

    }

    private static float[][] const_mult(float[][] a, float c) {
        float[][] result = new float[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++)
                result[i][j] = c * a[i][j];
        }
        return result;
    }


    private static void printMat(float[][] a){
        for(int row = 0;row < a.length; row++){
            for(int col = 0;col < a[0].length; col++){
                System.out.print(a[row][col] + " ");
            }
            System.out.println();
        }
    }


}
