package danielbatchford.mvlregression;

import java.util.List;

interface ParserInterface {

    List<DataPair> load(String filePath, Format format) throws MLToolsException;

    List<DataPair> load(String filePath, Format format, boolean omitHeader) throws MLToolsException;


}
