package sample;

import java.util.Arrays;

public class MatrixCalc {
    public double logisticFunc(double var) {
        return (1 / (1 + Math.exp(-var)));
    }

    public double[] logisticVector(double[] vector) {
        double[] tmp = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            tmp[i] = logisticFunc(vector[i]);
        }
        return tmp;
    }

    public double[] multiply(double[][] matrix, double[] vector) {
        double[] result = new double[matrix.length];
        Arrays.fill(result, 0.0);
        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i < vector.length; i++) {
                result[j] += vector[j] * matrix[i][j];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        double[] vec = new double[] {0.5, 0.9975, 0.6225, 0.7306};
        System.out.println(Arrays.toString(new MatrixCalc().logisticVector(vec)));
    }
}
