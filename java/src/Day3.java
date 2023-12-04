import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Day3 {


    public void prepareNumbers(String input) {
        var rows = input.split("\n");
        var pat = Pattern.compile("\\d+");
        for (int y = 0; y<rows.length; y++) {
            var row = rows[y];
            var matcher = pat.matcher(row);
            while (matcher.find()) {
                var number = new Num();
                number.value = Integer.parseInt(matcher.group());
                for (int i = matcher.start(); i < matcher.end(); i++) {
                    numbers.put(new Point(i, y), number);
                }
            }
        }
    }

    public long extractNeigboor(String input, boolean second) {
        long res = 0;
        var rows = input.split("\n");

        var pat = second ? Pattern.compile("[*]") : Pattern.compile("[^0-9.]");
        for (int y = 0; y < rows.length; y++) {
            var row = rows[y];
            var matcher = pat.matcher(row);
            while (matcher.find()) {
                int x = matcher.start();
                if (second) {
                    res += findGears(x, y);
                }else {
                    res += find(x, y);
                }
            }
        }
        return res;
    }

    public int find(int x, int y) {
        var res = 0;
        for (int xx = x-1; xx <= x+1 ; xx++) {
            for ( int yy = y-1; yy <= y+1; yy++) {
                var num = numbers.get(new Point(xx, yy));
                if (num != null && !num.taken) {
                    num.taken = true;
                    res += num.value;
                }
            }
        }
        return res;
    }

    public int findGears(int x, int y) {
        var res = 0;
        Num first = null;
        Num second = null;
        for (int xx = x-1; xx <= x+1 ; xx++) {
            for ( int yy = y-1; yy <= y+1; yy++) {
                var num = numbers.get(new Point(xx, yy));
                if (num == null || first == num || second == num)
                    continue;
                if (first == null) {
                    first = num;
                    continue;
                }
                if (first != num && (second == null || second == num)) {
                    second = num;
                    continue;
                }
                return 0;
            }
        }
        if (first != null && second != null) {
            return first.value * second.value;
        }
        return 0;
    }

    Map<Point, Num> numbers = new HashMap();

    static class Num {
        int value;
        boolean taken;
    }

    static record Point(int x, int y){}
}
