import networktools.Format;
import networktools.Network;
import networktools.NetworkToolsException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws NetworkToolsException {
        Network n = new Network(new int[]{3,3,5});
        n.load("./trainingdata/sample.txt",Format.TEXT,false);
    }
}
