package mltools.units.regression;

import mltools.DataPair;
import mltools.Format;
import mltools.MLToolsException;
import mltools.units.Unit;

import java.util.List;

public abstract class Regression implements Unit {

    List<DataPair> data;

    protected Regression(List<DataPair> data) {
        this.data = data;
    }

    public Float predict(Float[] cord) {
        return 0.0f;
    }

    public void learn(){

    }

    public void learn(boolean displayLearning) {

    }

    public void saveCoefficients(String fileName, Format format) throws MLToolsException {

    }

    public void loadCoefficients(String filename, Format format) throws MLToolsException {

    }

    public void test(List<DataPair> data) {

    }
}
