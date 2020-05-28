package mltools.units;

import mltools.DataPair;

import java.util.List;

public interface Unit {

    void test(List<DataPair> data);

    float predict(Float[] cord);
}
