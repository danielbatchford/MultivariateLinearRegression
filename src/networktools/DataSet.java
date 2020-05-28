package networktools;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

class DataSet {

    Map<Float[], Integer> data;

    DataSet(){
        data = new HashMap<>();
    }

    void addEntry(Float[] input, Integer expected){
        data.put(input,expected);
    }
}
