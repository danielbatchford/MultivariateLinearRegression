package networktools;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public class Network implements NetworkInterface {

    int[] layers;

    public Network(int[] layers) {
        this.layers = layers;
    }

    @Override
    public void train() {

    }

    @Override
    public void save(Path filePath, Format format) {

    }

    @Override
    public void load(String filePath, Format format) throws NetworkToolsException {
        load(filePath, format, false);
    }

    @Override
    public void load(String filePath, Format format, boolean OmitHeader) throws NetworkToolsException {

        DataSet data = new DataSet();
        switch (format) {
            case TEXT:

                try {
                    File file = new File(filePath);
                    Scanner scanner = new Scanner(file);

                    System.out.println("Reading from file at: " + filePath + ".");

                    int lineNo = 0;
                    if (OmitHeader) {
                        scanner.nextLine();
                        lineNo++;
                    }

                    while (scanner.hasNextLine()) {
                        String[] stringVals = scanner.next("\n").split(" ");

                        int length = stringVals.length;
                        Float[] floatVals = new Float[length]; // can be optimised here as length only needs to be called once
                        for (int i = 0; i < length - 1; i++) {
                            floatVals[i] = Float.valueOf(stringVals[i]);
                        }

                        try {
                            data.addEntry(floatVals, Integer.valueOf(stringVals[length - 1]));
                        } catch (NumberFormatException e) {
                            throw new NetworkToolsException("Data at line " + lineNo + " is in the incorrect format");
                        }

                        lineNo++;
                    }
                    System.out.println("File successfully Read, " + lineNo + " lines read.");

                } catch (FileNotFoundException e) {
                    throw new NetworkToolsException("File Cannot Be Read From.");
                }


                break;
            case DB:
                System.out.println("Not Implemented Yet");
                break;
            default:
                throw new NetworkToolsException("Invalid file format specified");
        }
        
    }


    @Override
    public void predict() {

    }

    @Override
    public void status() {

    }
}
