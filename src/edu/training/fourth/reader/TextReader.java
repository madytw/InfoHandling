package edu.training.fourth.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Created by Roman on 11.11.2016.
 */
public class TextReader {
    private static final String FILE = "data/text.txt";
    private static final Logger LOG = LogManager.getLogger();

    public String readText(){
        try{
            String res = Files.lines(Paths.get(FILE)).map(c -> c.concat("\n")).collect(Collectors.joining());
            return res;
        }catch (IOException e){
            LOG.fatal(e);
            throw new RuntimeException(e);
        }
    }
}
