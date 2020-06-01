import mltools.DataPair;
import mltools.Format;
import mltools.MLToolsException;
import mltools.Parser;
import mltools.units.regression.MultivariateLinearRegression;
import mltools.units.regression.Regression;

import java.util.List;

public class Main {

    public static void main(String[] args) throws MLToolsException {
        Parser p = new Parser();
        List<DataPair> data = p.load("./trainingdata/sample.txt", Format.TEXT, false);

        Regression r = new MultivariateLinearRegression(data, 0.01f);
        r.learn(false);
        r.test(data);

        r.saveCoefficients("./outputs/tempCoeff.txt",Format.TEXT);

        System.out.println(r.predict(new Float[]{20.0f,50.0f}));
    }

    //Make number of dimensions not rely on first row
}
