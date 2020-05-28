package mltools.units.knn;

import mltools.DataPair;
import mltools.Format;
import mltools.MLToolsException;
import mltools.units.Unit;

import java.util.*;

public class KNN implements Unit {


    List<DataPair> data;
    int k;

    public KNN(List<DataPair> data, int k) {
        this.data = data;
        this.k = k;
    }

    public Float predict(Float[] cord) {
        Comparator<DataPair> comparator = (a, b) -> {
            Float[] af = a.getVals(), bf = b.getVals();

            float aSum = 0, bSum = 0;
            for(int i = 0; i < af.length; i++){
                aSum += Math.pow(af[i]-cord[i],2);
                bSum += Math.pow(bf[i]-cord[i],2);
            }
            /*System.out.println("A: "+af[0]+","+af[1]+" B: "+bf[0]+","+bf[1]);
            System.out.println("ASUM: "+aSum+", BSUM: "+bSum);
            System.out.println();*/
            if(aSum == bSum) return 0;
            return (aSum > bSum)? 1 : -1;
        };

        Set<DataPair> set = new TreeSet<>(comparator);


        for(DataPair pair: data){
            set.add(pair);

        }

        Iterator<DataPair> iterator = set.iterator();

        float sum = 0.0f;
        int maxIndex =Math.min(k,set.size());
        for(int i = 0; i < maxIndex; i++){   //check k not greater than length of set, rare case but could happen
            sum += iterator.next().getRes();
        }
        return sum / (float) k;

    }

    public void test(List<DataPair> data) {
        int successCount = 0;
        for(DataPair pair: data){
            float hypValue = predict(pair.getVals());
            if((hypValue < 0.5 && pair.getRes() == 0) || hypValue > 0.5 && pair.getRes() == 1){
                successCount++;
            }
        }

        System.out.println("Testing gave an accuracy of: "+ (float) successCount / (float) data.size());
    }
}
