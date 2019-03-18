public class pi_calc {
    private static double deltaX = 0.00001;//0.00001
    public static void main(String[] args){
        long ms1 = System.currentTimeMillis();
        //System.out.println("dX = "+ deltaX +": "+getPi(deltaX));
        System.out.println("Taylor: "+getTaylorPi(Long.MAX_VALUE/2));
        System.out.println("Took "+ (int)(System.currentTimeMillis() - ms1) + "ms.");
    }
    private static double getPi(double dX){
        double sum = 0;
        for(long i = 0;i < (1/dX)/2; i++){
            double x1 = i * dX;
            double x2 = x1 + dX;
            double y1 = getY(x1), y2 = getY(x2);
            sum += getDist(x1, x2, y1, y2);
            if(i % ((1/dX)/2/10) == 0){
                System.out.println(i / ((1/dX)/10)*20+"%");
            }
        }
        return sum * 6;
    }
    private static double getDist(double x1, double x2, double y1, double y2){
        return Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
    }
    private static double getY(double x){
        return Math.sqrt(1-Math.pow(x,2));
    }


    private static double getTaylorPi(long n){
        double sum = 0;
        boolean add = true;
        for(long i = 1; i < n; i+=2){
            if(add) {
                sum += (1.0 / i);
            }else {
                sum -= (1.0 / i);
            }
            add = !add;
        }
        return sum * 4;
    }
}
