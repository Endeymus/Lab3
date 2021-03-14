package sample;

public class MatrixParser {
    public double[] parseVector(String vector) {
        String[] input = vector.replaceAll(",",".").split(" ");
        double[] tmp = new double[input.length];

        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = Double.parseDouble(input[i]);
        }
        return tmp;
    }

    public double[][] parseMatrix(String matrix) {
        String[] lines = matrix.split("\n");
        double[][] tmp = new double[lines.length][lines.length];

        for (int i = 0; i < lines.length; i++) {
            tmp[i] = parseVector(lines[i]);
        }

        return tmp;
    }
}
