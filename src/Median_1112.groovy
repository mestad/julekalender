/**
 * Created by Aase on 06.12.2015.
 */
public class Median_1112 {
    static int MAX_ROWS = 1000

    public static void main(String[] args) {
        println parseBinary("0b10")
        new File("C:\\aase\\euler\\src\\median_1112.txt").eachLine {line ->
        }
    }

    private static Line parseLine(String line) {
        if (line.contains("0b")) {
            return parseBinary(line)
        } else if (line.isInteger()) {
            return line.toInteger()
        } else {
            return parseRomanLiteral(line)
        }
    }

    static def parseBinary(String line) {
        String work = line.substring(2).reverse()
        int acc = 0
        for (int i = 0; i < work.length(); i++) {
            int digit = work.substring(i,i+1).toInteger()
            if (digit == 1) {
                acc += (2 ** i)
            }
        }
        return acc
    }

    static def parseRomanLiteral(String line) {
        int acc = 0
        int i = 0
        while (i < line.length()) {
            String literal = line.substring(i,i+1)
            String possibleMinus;
            if (i < line.length() - 1) {
                possibleMinus = line.substring(i, i+2)
            }
            if ()

        }
    }

    public enum ROMAN {
        M(1000),
        D(500),
        C(100),
        L(50),
        X(10),
        V(5),
        I(1);

        public int value;


    }

    public static class Line {
        public String representation
        public int value

        Line(String representation, int value) {
            this.representation = representation
            this.value = value
        }


        @Override
        public String toString() {
            return "Line{" +
                    "representation='" + representation + '\'' +
                    ", value=" + value +
                    '}';
        }
    }
}
