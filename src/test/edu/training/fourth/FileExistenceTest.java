package test.edu.training.fourth;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Created by Roman on 13.11.2016.
 */
public class FileExistenceTest {
    @Test
    public void fileExistenceTest()
    {
        File file=new File("data\\text.txt");
        boolean expected=true;
        boolean actual=file.exists();
        Assert.assertEquals(expected, actual);
    }
}
