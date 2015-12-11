
/**
 * Created by Aase on 06.12.2015.
 */
public class Median_1112 {

    public static void main(String[] args) {
//        println parseBinary("0b10")
//        println parseRomanLiteral("XXX")
//        println parseRomanLiteral("IL")
//        println parseRomanLiteral("MIL")
        List<Line> lines = new ArrayList<Line>()
        new File("C:\\aase\\scripts\\src\\median_1112.txt").eachLine {line ->
            lines.add(parseLine(line))
        }
        def sorted = lines.toSorted { Line a, Line b -> a.value <=> b.value }
        int index = sorted.size() / 2
        int counter= 0
        for (Line element : sorted) {
            println element
        }
        println((Line) sorted.get(index + 1))
        println((Line) sorted.get(5000))
        println((Line) sorted.get(5001))
    }


    private static Line parseLine(String line) {
        if (line.contains("0b")) {
            return parseBinary(line)
        } else if (line.isInteger()) {
            return new Line(line, line.toInteger())
        } else {
            return parseRomanLiteral(line)
        }
    }

    static Line parseBinary(String line) {
        String work = line.substring(2).reverse()
        int acc = 0
        for (int i = 0; i < work.length(); i++) {
            int digit = work.substring(i,i+1).toInteger()
            if (digit == 1) {
                acc += (2 ** i)
            }
        }
        return new Line(line, acc)
    }

    static Line parseRomanLiteral(String line) {
        int acc = 0
        int i = 0
        while (i < line.length()) {
            String literal = line.substring(i,i+1)
            String possibleMinus;
            if (i < line.length() - 1) {
                possibleMinus = line.substring(i, i+2)
            }
            if (possibleMinus != null && getEnum(possibleMinus) != null) {
                acc += getEnum(possibleMinus).value
                i = i + 2
            } else {
                acc += getEnum(literal).value
                i++
            }

        }
        return new Line(line, acc)
    }

    private static ROMAN getEnum(String candidate) {
        try  {
            return ROMAN.valueOf(candidate)
        } catch (IllegalArgumentException e) {
            return null
        }
    }

    public enum ROMAN {
        M(1000),
        D(500),
        C(100),
        L(50),
        X(10),
        V(5),
        I(1),
        IV(4),
        IX(9),
        IL(49),
        IC(99),
        ID(499),
        IM(999),
        VL(45),
        VC(95),
        VD(495),
        VM(995),
        XL(40),
        XC(90),
        XD(490),
        XM(990),
        LD(450),
        LM(950),
        CD(400),
        CM(900);

        public int value;

        ROMAN(int value) {
            this.value = value
        }
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
