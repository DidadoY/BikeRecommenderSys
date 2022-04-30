import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TxtWritter {

    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    public void addToMap(int key, ArrayList<Integer> visited ){
            map.put(key, visited);
    }

    public void txtWrite() throws Exception {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./data/users.txt"));

            for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
                String y = Integer.toString(entry.getKey());
                bw.write(y + ":");
                boolean first = true;
                for (Integer x : entry.getValue()) {
                    String c = Integer.toString(x);
                    if (first) {
                        bw.write(c);
                        first = false;
                    } else bw.write("," + c);
                }
                bw.write("\n");
            }

            bw.close();

        } catch (Exception e) {
            throw e;
        }
    }
}
