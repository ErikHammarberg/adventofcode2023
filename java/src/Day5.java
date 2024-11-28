import java.util.*;

public class Day5 {


    /*
seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48
     */
    long solveOne(String input) {
        var lines = input.split("\\n");
        var seeds = parseInts(lines[0].split(": ")[1]);
        List<Mapper> mappers = new ArrayList<>();
        String[] results;
        int passStart = 3;
        for (int i = 3; i < lines.length; i++) {
            if (lines[i].isBlank()) {
                var mapLines = Arrays.copyOfRange(lines, passStart, i);
                passStart = i +2 ;
                mappers.add(new Mapper(mapLines));
            }
        }
        return Arrays.stream(seeds).mapToLong(i -> giveFromMappers(mappers, i)).min().getAsLong();

    }

    long giveFromMappers(List<Mapper> mappers, long seed) {
        return mappers.stream().reduce(seed, (interrim, map) -> map.mapper.getOrDefault(interrim, interrim), (a, b) -> b);
    }

    long[] stepMapper (long[] seeds, Mapper mapper) {
        return Arrays.stream(seeds).map(i -> mapper.mapper.getOrDefault(i, i)).toArray();
    }


    class Mapper {

        Map<Long, Long> mapper = new HashMap();
        // Dest Source Range
        public Mapper(String[] args) {
            for (String arg : args) {
                var nums = parseInts(arg);
                final long destBase = nums[0];
                final long sourseBase = nums[1];
                for(int i = 0; i < nums[2]; i++) {
                    mapper.put(sourseBase + i, destBase + i);
                }
            }
        }

    }

    public static Long[] parseInts(String args) {
        return  Arrays.stream(args.split(" ")).map(Long::parseLong).toList().toArray(new Long[0]);
    }
}
