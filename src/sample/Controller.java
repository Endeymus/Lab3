package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;

public class Controller {
    private static final String FORMAT_2f = "%.2f ";
    private static final String FORMAT_4f = "%10.4f ";
    private final MatrixParser parser = new MatrixParser();
    private final MatrixCalc matrixCalc = new MatrixCalc();
    @FXML
    private Spinner<Integer> size;
    @FXML
    private CheckBox checkBox;
    @FXML
    private TextArea textArea2;
    @FXML
    private TextArea textArea3;
    @FXML
    private TextField textField;
    @FXML
    private Spinner<Integer> applyWeight;
    @FXML
    private TextArea textArea1;

    @FXML
    private void fillMatrix() {
//        double [] vector = {0, 0, 6, 0, 0};
        textArea1.clear();
        textArea2.clear();

        textArea1.setText(out(fill(applyWeight.getValue())));
        textArea2.setText(out(fill(applyWeight.getValue())));
//        textField.setText(out(vector, FORMAT_2f));
    }

    private double[][] fill(int value) {
        double[][] filledMatrix = new double[value][value];

        for (double[] matrix : filledMatrix) {
            Arrays.fill(matrix, (1.0 / value));
        }
        return filledMatrix;
    }

    private String out(double[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (double[] doubles : matrix) {
            sb.append(out(doubles, FORMAT_2f));
        }
        return sb.toString();
    }

    private String out(double[] vector, String format) {
        StringBuilder sb = new StringBuilder();
        for (double v : vector) {
            sb.append(String.format(format, v));
        }
        sb.append("\n");
        return sb.toString();
    }

    public void clear() {
        textArea1.clear();
        textArea2.clear();
        textArea3.clear();
        textField.clear();
    }

    public void calculate(ActionEvent actionEvent) {
        try{
            if (!check(parser.parseMatrix(textArea1.getText())) || !check(parser.parseMatrix(textArea2.getText()))) {
                throw new Exception("Ошибка");
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Значение(я) больше 1");
            alert.showAndWait();
        }
        double[] net1 = new double[0];
        double[] out1 = new double[0];
        double[] net2 = new double[0];
        double[] out2 = new double[0];
/*        System.out.println(textArea1.getText());
        System.out.println(textArea2.getText());
        System.out.println(textField.getText());
        System.out.println(Arrays.toString(parser.parseVector(textField.getText())));
        System.out.println();
        for (double[] doubles : parser.parseMatrix(textArea1.getText())) {
            System.out.println(Arrays.toString(doubles));
        }
        System.out.println();
        for (double[] doubles : parser.parseMatrix(textArea2.getText())) {
            System.out.println(Arrays.toString(doubles));
        }
        System.out.println();
        System.out.println(Arrays.toString(matrixCalc.multiply(parser.parseMatrix(textArea1.getText()), parser.parseVector(textField.getText()))));
        System.out.println(Arrays.toString(matrixCalc.logisticVector(matrixCalc.multiply(parser.parseMatrix(textArea1.getText()), parser.parseVector(textField.getText())))));*/
        try {
            net1 = matrixCalc.multiply(parser.parseMatrix(textArea1.getText()), parser.parseVector(textField.getText()));
            out1 = matrixCalc.logisticVector(net1);
            net2 = matrixCalc.multiply(parser.parseMatrix(textArea2.getText()), out1);
            out2 = matrixCalc.logisticVector(net2);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Проверте корректность ввода данных!");
            alert.showAndWait();
            e.printStackTrace();
        }

        textArea3.clear();
        textArea3.appendText(out(net1, FORMAT_4f));
        textArea3.appendText(out(out1, FORMAT_4f));
        textArea3.appendText(out(net2, FORMAT_4f));
        textArea3.appendText(out(out2, FORMAT_4f));

    }

    private boolean check(double[][] matrix) {
        double res = 0;
        for (int i = 0; i < matrix.length; i++) {
            res = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                res+= matrix[j][i];
            }
            if (res > 1)
                return false;
        }
        return true;
    }

    public void weightAllow(ActionEvent actionEvent) {
        applyWeight.setDisable(!checkBox.isSelected());
    }

    public void fillZero(ActionEvent actionEvent) {
        int value = size.getValue();
        double[][] filledMatrix = new double[value][value];

        for (double[] matrix : filledMatrix) {
            Arrays.fill(matrix, 0.0);
        }
        textArea1.clear();
        textArea2.clear();

        textArea1.setText(out(filledMatrix));
        textArea2.setText(out(filledMatrix));
    }
}
