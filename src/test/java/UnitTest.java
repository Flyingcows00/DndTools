import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;

public class UnitTest {

    @Test
    public void getMonsters() throws URISyntaxException, IOException {
        StringBuilder sb = new StringBuilder();
        PrintWriter pw = new PrintWriter("monsters.txt");
        String url = "http://www.dnd5eapi.co/api/monsters/";
        for (int i = 1; i <= 325; i++) {
            sb.append(IOUtils.toString(new URI(url + String.valueOf(i))));
        }
        pw.println(sb.toString());
        pw.close();
    }
}
