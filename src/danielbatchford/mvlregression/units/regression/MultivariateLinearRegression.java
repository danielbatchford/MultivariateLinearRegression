package danielbatchford.mvlregression.units.regression;

import danielbatchford.mvlregression.DataPair;
import danielbatchford.mvlregression.units.Unit;

import java.util.List;


public class MultivariateLinearRegression extends Regression implements Unit {


    public MultivariateLinearRegression(List<DataPair> data, float learningRate) {
        super(data, learningRate);

    }

    @Override
    public void learn(boolean displayLearning) { //Figure out way to not be able to run test without learn
        System.out.println("Initialising Learning Sequence");

        for (int i = 0, size = data.size(); i < size; i++) {
            DataPair pair = data.get(i);
            float loss = pair.getRes() - h(pair.getVals());

            coeff[0] += learningRate * loss;
            Float[] vals = pair.getVals();

            for (int j = 0, max = coeff.length - 1; j < max; j++) {
                coeff[j + 1] += learningRate * loss * vals[j];
            }

            if (displayLearning) System.out.println("Loss at data row " + (i + 1) + " = " + loss);
        }
    }

    public void learn() {
        learn(false);
    }

    public float h(Float[] X) {
        float sum = coeff[0];
        for (int i = 0; i < X.length; i++) {
            sum += coeff[i + 1] * X[i];
        }
        return (float) (1 / (1 + Math.exp(-1 * sum)));
    }

    @Override
    public float predict(Float[] val) {
        return h(val);
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
