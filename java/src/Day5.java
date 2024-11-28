import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day5 {


    /*
seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48
     */
    int solveOne(String input) {
        var lines = input.split("\\n");
        var seeds = parseInts(lines[0]);
        String[] results;
        for (int i = 3; i < lines.length; i++) {
            if (lines[i].isBlank()) {
                // make sub-array and break.
            }
            
        }
    }


    class Mapper {

        Map<Integer, Integer> mapper = new HashMap();
        // Dest Source Range
        public void setup(String[] args) {
            for (String arg : args) {
                var nums = parseInts(arg);
                final int destBase = nums[0];
                final int sourseBase = nums[1];
                for(int i = 0; i < nums[2]; i++) {
                    mapper.put(sourseBase + i, destBase + i);
                }
            }
        }

    }

    public static int[] parseInts(String args) {
        Arrays.stream(args.split(" ")).map(Integer::parseInt);
    }
}
