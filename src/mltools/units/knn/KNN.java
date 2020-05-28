package mltools.units.knn;

import mltools.DataPair;
import mltools.Format;
import mltools.MLToolsException;
import mltools.units.Unit;

import java.util.List;

public class KNN implements Unit {


    List<DataPair> data;
    int k;

    public KNN(List<DataPair> data, int k) {
        this.data = data;
    }

    public Float predict(Float[] cord) {
        
        return 0.0f;
    }

    public void test(List<DataPair> data) {

    }
}
