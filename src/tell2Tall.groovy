import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by Aase on 06.12.2015.
 */
public class tell2Tall {
    public static String limit = "12345678987654321"
//        public static String limit = "322";


    public static void main(String[] args) {

        println "svar: " + count2s(limit)
    }

    private static BigInteger count2s(String number) {
        if (number.length() == 1) {
            if (number.equals("2")) return BigInteger.ONE
            else return BigInteger.ZERO
        } else {
            BigInteger toerRest = BigInteger.ZERO
            int digit = number.substring(0,1).toInteger()
            if (digit == 2) {
                toerRest = new BigInteger(number.substring(1,number.length())).add(BigInteger.ONE)
            } else if (digit > 2) {
                toerRest = BigInteger.TEN.pow(number.length()-1)
            }
            return count2s(number.substring(1,number.length()))
                            .add(toerRest)
                            .add(BigInteger.valueOf(number.length()-1).multiply(BigInteger.valueOf(digit).multiply(BigInteger.TEN.pow(number.length()-2))))
        }
    }


    private static boolean matches(String line, String regex) {
        Pattern p = Pattern.compile(regex)
        Matcher m = p.matcher(line)
        return m.find()
    }

    //2,12,22,32, +10
}
