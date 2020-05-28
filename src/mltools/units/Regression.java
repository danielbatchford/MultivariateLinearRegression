package mltools.units;

import mltools.DataPair;
import mltools.Format;
import mltools.MLToolsException;

import java.util.List;

public abstract class Regression {

    List<DataPair> data;

    protected Regression(List<DataPair> data) {
        this.data = data;
    }

    public Float predict(DataPair pair) {
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
