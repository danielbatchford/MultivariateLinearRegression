import mltools.DataPair;
import mltools.Format;
import mltools.MLToolsException;
import mltools.Parser;
import mltools.units.knn.KNN;
import mltools.units.regression.MultivariateLinearRegression;
import mltools.units.regression.Regression;

import java.util.List;

public class Main {

    public static void main(String[] args) throws MLToolsException {
        Parser p = new Parser();
        List<DataPair> data = p.load("./trainingdata/sample.txt", Format.TEXT, false);
        Regression r = new MultivariateLinearRegression(data, 0.05f);
        KNN k = new KNN(data);
        r.learn();


        r.test(data);


    }
}
