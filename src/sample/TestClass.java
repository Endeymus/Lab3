package sample;

public class TestClass {
    private static MatrixCalc calc = new MatrixCalc();
    private static MatrixParser parser = new MatrixParser();

    private static final double [] vector = {0, 0, 6, 0, 0};

    private static final double [][] V = {
            {0.2, 0.2, 0.2, 0.2, 0.2},
            {0.2, 0.2, 0.2, 0.2, 0.2},
            {0.2, 0.2, 0.2, 0.2, 0.2},
            {0.2, 0.2, 0.2, 0.2, 0.2},
            {0.2, 0.2, 0.2, 0.2, 0.2}
    };

    public static void main(String[] args) {
//        double[] net = calc.multiply(V, vector);
//        System.out.println(Arrays.toString(net));
//        double[] out = calc.logisticVector(net);
//        System.out.println(Arrays.toString(out));
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%.4f", 0.00));
        System.out.println(sb.toString());
    }

}
