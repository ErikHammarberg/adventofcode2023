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
            if (lines[i].isBlank() || i +1 == lines.length) {
                var mapLines = Arrays.copyOfRange(lines, passStart, i);
                passStart = i +2 ;
                var mapperwrapper = new MapperWrapper(mapLines);
                seeds = stepMapper(seeds, mapperwrapper);
            }
        }
        return Arrays.stream(seeds).mapToLong(i -> i).min().getAsLong();

    }


    Long[] stepMapper (Long[] seeds, MapperWrapper mapper) {
        return Arrays.stream(seeds).map(i -> mapper.mapValue(i)).toList().toArray(new Long[0]);
    }

    class MapperWrapper {
        List<Mapper> mappers;

        public MapperWrapper (String[] args){
            mappers = Arrays.stream(args).map(s -> new Mapper(s)).toList();
        }

        public long mapValue(long oldValue) {
            return mappers.stream().map(m -> m.mapValue(oldValue)).filter(i -> i > 0).findFirst().orElse(oldValue);
        }
    }

    class Mapper {

        long sourceValBegin;
        long destValBegin;
        long range;


        // Dest Source Range
        public Mapper(String arg) {
            var nums = parseInts(arg);
            this.destValBegin = nums[0];
            this.sourceValBegin = nums[1];
            this.range = nums[2];
        }

        public long mapValue(long oldValue) {
            if (oldValue < sourceValBegin || oldValue > sourceValBegin + range - 1) {
                return -666;
            }
            long difference = oldValue - sourceValBegin;
            return destValBegin + difference;
        }
    }


    public static Long[] parseInts(String args) {
        return  Arrays.stream(args.split(" ")).map(Long::parseLong).toList().toArray(new Long[0]);
    }
}
