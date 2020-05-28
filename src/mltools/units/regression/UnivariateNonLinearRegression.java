package mltools.units.regression;

import mltools.DataPair;
import mltools.units.Unit;

import java.util.List;


public class UnivariateNonLinearRegression extends Regression implements Unit { //Assuming Linear Seperable eg line, plane, only binary classification


    public UnivariateNonLinearRegression(List<DataPair> data, float learningRate) {
        super(data, learningRate);
    }


    @Override
    public void learn(boolean displayLearning) {
        System.out.println("Initialising Learning Sequence");

        for (int i = 0, size = data.size(); i < size; i++) {
            DataPair pair = data.get(i);
            float loss = pair.getRes() - h(pair.getVals());

            coeff[0] += learningRate * loss;
            Float[] vals = pair.getVals();

            for (int j = 0, max = coeff.length - 1; j < max; j++) {
                coeff[j + 1] += learningRate * loss * vals[j]; //CHANGE THIS FUNCTION AND OTHER THEN DONE?
            }
            if (displayLearning) System.out.println("Loss at data row " + (i + 1) + " = " + loss);
        }
    }

    @Override
    public void learn() {
        learn(false);
    }

    @Override
    public float predict(Float[] val) {
        return h(val);
    }


    private float h(Float[] X) { //CHANGE THIS FUNTION AND OTHER THEN DONE?
        float sum = coeff[0];
        for (int i = 0; i < X.length; i++) {
            sum += coeff[i + 1] * X[i];
        }
        return (float) (1 / (1 + Math.exp(-1 * sum)));
    }

    @Override
    public void test(List<DataPair> data) {

        int successCount = 0;
        for (DataPair pair : data) {
            if ((h(pair.getVals()) >= 0.5 && pair.getRes() == 1) || h(pair.getVals()) <= 0.5 && pair.getRes() == 0)
                successCount++;
        }
        System.out.println("Testing gave an accuracy of: " + (float) successCount / (float) data.size());
    }
}
