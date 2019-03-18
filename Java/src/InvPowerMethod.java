public class InvPowerMethod {
    private static float[][] x0 = {{1}, {0}, {0}};
    private static float[][] A = {{10,-8,-4}, {-8,13,4}, {-4,5,4}};
    private static float[] alphas = {21, 3.3f, 1.9f};

    public static void main(String[] args) {
        for(float alpha : alphas){
            System.out.println("Alpha = "+alpha+": ");
            
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
