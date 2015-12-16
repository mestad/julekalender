import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by Aase on 06.12.2015.
 */
public class tell2Tall {
//    public BigInteger limit = BigInteger.valueOf("12345678987654321")
        public static String limit = "32";


    public static void main(String[] args) {
        BigInteger counter = BigInteger.ZERO
        for (int i = 0; i < limit.size(); i++) {
            String digit = limit.substring(i, i+1)
            int restDigits = limit.size() - i - 1
            BigInteger restNumber = BigInteger.ZERO
            if (restDigits > 0)
                restNumber = new BigInteger(limit.substring(i, limit.size()))
            BigInteger part = BigInteger.valueOf(restDigits)
                    .multiply(BigInteger.valueOf(10).pow(restDigits))
            if (digit.equals("1")) {
                counter = counter.add(part)
            } else if (digit.equals("2")) {
                counter = counter.add(part)
                if (restDigits > 0)
                    counter = counter.add(restNumber)
                else
                    counter = counter.add(BigInteger.ONE)
            } else {
                if (restDigits > 0)
                    counter = counter
                            .add(part.multiply(BigInteger.valueOf(digit.toInteger())))
                            .add(BigInteger.valueOf(restDigits -1)
                            .multiply(BigInteger.valueOf(10).pow(restDigits - 1)))
            }

        }
        println "svar: " + counter
    }


    private static boolean matches(String line, String regex) {
        Pattern p = Pattern.compile(regex)
        Matcher m = p.matcher(line)
        return m.find()
    }
}
