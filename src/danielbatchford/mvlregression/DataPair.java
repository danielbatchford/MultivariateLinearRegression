package danielbatchford.mvlregression;

public class DataPair {

    private final Float[] vals;
    private final Integer res;
    private final int noOfDim;

    DataPair(Float[] vals, Integer res) {
        this.vals = vals;
        this.res = res;
        this.noOfDim = vals.length;
    }

    public Float[] getVals() {
        return vals;
    }

    public Integer getRes() {
        return res;
    }

    public int getNoOfDim() {
        return noOfDim;
    }
}
