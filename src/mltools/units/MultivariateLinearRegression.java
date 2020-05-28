package mltools.units;

import mltools.DataPair;
import mltools.Format;
import mltools.MLToolsException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MultivariateLinearRegression extends Regression { //Assuming Linear Seperable eg line, plane, only binary classification

    float[] coeff;
    float learningRate;

    public MultivariateLinearRegression(List<DataPair> data, float learningRate) {
        super(data);
        this.learningRate = learningRate;
        coeff = new float[data.get(0).getNoOfDim() + 1]; //Plus 1 to account for constant

    }

    @Override
    public Float predict(DataPair pair) {
        return h(pair.getVals());
    }

    @Override
    public void learn(boolean displayLearning) {
        System.out.println("Initialising Learning Sequence");

        for (int i = 0, size = data.size(); i < size; i++) {
            DataPair pair = data.get(i);
            float loss = pair.getRes() - h(pair.getVals());

            coeff[0] += learningRate * loss;
            Float[] vals = pair.getVals();

            for (int j = 0, max = coeff.length - 1; j < max; j++) {
                coeff[j + 1] += learningRate * loss * vals[j];
            }
            if(displayLearning) System.out.println("Loss at data row " + (i + 1) + " = " + loss);
        }
    }

    public void learn(){
        learn(false);
    }

    private Float h(Float[] X) {
        float sum = coeff[0];
        for (int i = 0; i < X.length; i++) {
            sum += coeff[i + 1] * X[i];
        }
        return (float) (1 / (1 + Math.exp(-1 * sum)));
    }

    @Override
    public void saveCoefficients(String fileName, Format format) throws MLToolsException {
        switch (format) {
            case TEXT:
                try {
                    PrintWriter writer = new PrintWriter(fileName);

                    for (Float f : coeff) {
                        writer.println(f);
                    }
                    writer.close();
                } catch (FileNotFoundException e) {
                    throw new MLToolsException("Cannot find the write path at " + fileName);
                }
                break;
            case DB:
            case DATA:
                throw new MLToolsException("Format not implemented yet");
        }
    }

    @Override
    public void loadCoefficients(String filename, Format format) throws MLToolsException {

        switch (format) {
            case TEXT:
                try {
                    System.out.println("Loading coefficients from file " + filename);
                    List<Float> tempCoeff = new ArrayList<>();
                    File file = new File(filename);
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNext()) {
                        tempCoeff.add(scanner.nextFloat());
                    }
                    scanner.close();
                    System.out.println("Sucessfully loaded coefficients");

                } catch (FileNotFoundException e) {
                    throw new MLToolsException("Cannot load coefficients from file " + filename+", file not found");
                }

            case DB:
            case DATA:
                throw new MLToolsException("File formats not implemented yet");
        }
    }

    @Override
    public void test(List<DataPair> data) {

        int successCount = 0;
        int size = data.size();
        for(int i = 0; i < size; i++){
            DataPair pair = data.get(i);
            if((h(pair.getVals()) >= 0.5 && pair.getRes() == 1) || h(pair.getVals()) <= 0.5 && pair.getRes() == 0) successCount++;
        }
        System.out.println("Testing gave an accuracy of: "+ (float) successCount / (float) size);
    }
}
