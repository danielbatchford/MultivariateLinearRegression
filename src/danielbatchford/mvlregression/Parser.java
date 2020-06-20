package danielbatchford.mvlregression;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser implements ParserInterface {

    @Override
    public List<DataPair> load(String filePath, Format format) throws MLToolsException {
        return load(filePath, format, false);
    }

    @Override
    public List<DataPair> load(String filePath, Format format, boolean OmitHeader) throws MLToolsException {


        switch (format) {
            case CSV:
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
                    List<DataPair> data = new ArrayList<>();
                    String delimiter = (Format.CSV == format) ? "," : " ";

                    while (scanner.hasNextLine()) {

                        String[] stringVals = scanner.nextLine().split(delimiter);
                        if (stringVals.length <= 1) {
                            System.out.println("Bad input at line " + lineNo + ", skipping.");
                            continue;
                        }
                        int length = stringVals.length;

                        Float[] floatVals = new Float[length - 1]; // can be optimised here as length only needs to be called once
                        for (int i = 0; i < length - 1; i++) {
                            floatVals[i] = Float.valueOf(stringVals[i]);
                        }

                        try {
                            data.add(new DataPair(floatVals, Integer.valueOf(stringVals[length - 1])));

                        } catch (NumberFormatException e) {
                            throw new MLToolsException("Data at line " + lineNo + " is in the incorrect format");
                        }

                        lineNo++;
                    }
                    scanner.close();
                    System.out.println("File read successfully, " + lineNo + " lines read.");
                    if (data.size() == 0) throw new MLToolsException("Data was of size 0");
                    return data;

                } catch (FileNotFoundException e) {
                    throw new MLToolsException("File cannot be found at file path " + filePath);
                }
            default:
                throw new MLToolsException("Invalid file format specified");
        }
    }
}
