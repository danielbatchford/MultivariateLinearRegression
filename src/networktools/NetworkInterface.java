package networktools;

import java.nio.file.Path;

interface NetworkInterface {

    void train();

    void save(Path filePath, Format format);

    void load(String filePath, Format format) throws NetworkToolsException;

    void load(String filePath, Format format, boolean omitHeader) throws NetworkToolsException;

    void predict();

    void status();

}
