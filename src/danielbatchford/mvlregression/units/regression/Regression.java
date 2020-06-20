package danielbatchford.mvlregression.units.regression;

import danielbatchford.mvlregression.DataPair;
import danielbatchford.mvlregression.MLToolsException;
import danielbatchford.mvlregression.units.Unit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Regression implements Unit {

    final List<DataPair> data;
    final float learningRate;
    float[] coeff;

    protected Regression(List<DataPair> data, float learningRate) {
        this.data = data;
        this.learningRate = learningRate;
        coeff = new float[data.get(0).getNoOfDim() + 1]; //Plus 1 to account for constant
    }

    public void learn() {

    }

    public void learn(boolean displayLearning) {

    }

    public void saveCoefficients(String fileName) throws MLToolsException {
        try {
            PrintWriter writer = new PrintWriter(fileName);

            for (Float f : coeff) {
                writer.println(f);
            }
            writer.close();
            System.out.println("Coefficients successfully wrote to file " + fileName);
        } catch (FileNotFoundException e) {
            throw new MLToolsException("Cannot find the write path at " + fileName);
        }
    }


    public void loadCoefficients(String filename) throws MLToolsException {
        try {
            System.out.println("Loading coefficients from file " + filename);
            List<Float> tempCoeff = new ArrayList<>();
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                tempCoeff.add(scanner.nextFloat());
            }
            scanner.close();

            for (int i = 0, max = tempCoeff.size(); i < max; i++) {
                coeff[i] = tempCoeff.get(i);
            }
            System.out.println("Sucessfully loaded coefficients");
            return;

        } catch (FileNotFoundException e) {
            throw new MLToolsException("Cannot load coefficients from file " + filename + ", file not found");
        }
    }


    public void test(List<DataPair> data) {

    }
}
