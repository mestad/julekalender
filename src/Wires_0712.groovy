import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by vsxxame on 03.12.2015.
 */
class Wires_0712 {
    /**
     * Shift: >> x
     * AND: &
     * OR: |
     * XOR: ^
     * inversion: ~
     * @param args
     */
    public static void main(String[] args) {
        new File("c:\\")


    }

    private static Wire readLine(String line) {
        def fields = line.split(" ")
        if (fields[0].equals("NOT")) {
            return createNOTWire(fields)
        } else if (isWire(fields[0])) {
            //AND, XOR, OR, LSHIFT, RSHIFT
            return createBinaryWire(fields)
        } else if (isNumber(fields[0])) {
            //Value
            return createValueWire()
        }

    }

    private static Boolean isWire(String field) {
        Pattern pattern = Pattern.compile("[a-z]{1,}")
        Matcher matcher = pattern.matcher(field)
        return matcher.matches()
    }

    private static Boolean isNumber(String field) {
        Pattern pattern = Pattern.compile("\\d{1,}")
        Matcher matcher = pattern.matcher(field)
        return matcher.matches()
    }

    private static Wire createNOTWire(String[] fields) {
        Wire wire = new Wire()
        wire.operation = Action.NONE
        wire.name = fields[2]
        wire.signal = fields[0].toInteger()
        return wire
    }

    private static Wire createBinaryWire(String[] fields) {
        Wire wire = new Wire()
        wire.operation = Action.fromValue(fields[1])
        wire.name = fields[4]
        wire.inputs.add(fields[0])
        wire.inputs.add(fields[2])
        return wire
    }

    private static Wire createValueWire(String[] fields) {
        Wire wire = new Wire()
        wire.operation = Action.NOT
        wire.inputs.add(fields[1])
        wire.name = fields[3]
        return wire
    }

    private static int inverseComplement(int number) {
        return (65535 - number)
    }

    public static class Wire {
        public int signal
        public String name
        public List<String> inputs
        public Action operation
    }

    public static enum Action {
        AND, OR, XOR, LSHIFT, RSHIFT, NOT, NONE

        public static Action fromValue(String value) {
            if (value.equals("AND")) return AND
            if (value.equals("OR")) return OR
            if (value.equals("XOR")) return XOR
            if (value.equals("RSHIFT")) return RSHIFT
            if (value.equals("LSHIFT")) return LSHIFT
            throw new RuntimeException("Couldn't find " + value)
        }
    }

    private static void testCode() {
        int x = 123
        int y = 456
        println("Bitwise left shift " + (x << 2))
        println("Bitwise right shift " + (y >> 2))
        println("X og Y " + (x & y))
        println("X eller Y " + (x | y))
        println("Not X " + (~x))
        println("Not Y " + (~y))

        int f = x << 2
        int g = y >> 2
        int d = x & y
        int e = x | y
        int h = inverseComplement(x)
        int i = inverseComplement(y)

        println "d " + d
        println "e " + e
        println "f " + f
        println "g " + g
        println "h " + h
        println "i " + i
        println "x " + x
        println "y " + y
    }
}
