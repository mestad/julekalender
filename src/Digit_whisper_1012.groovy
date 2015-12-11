
/**
 * Created by Aase on 06.12.2015.
 */
public class Digit_whisper_1012 {
    public static final int ITERATIONS = 50

    public static void main(String[] args) {
        String seed = "1321131112"
        def timeStart = new Date()
        def timeStop = new Date()
        for (int i = 0; i < ITERATIONS; i++) {
            timeStart = new Date()
            seed = iterateOnSeed(seed)
            timeStop = new Date()
            println ("i : " + i + " tid: " + (timeStop.getTime() - timeStart.getTime()))
        }
        println ("Result: " + seed.length())
    }

    private static String iterateOnSeed(String seed) {
        StringBuilder acc = new StringBuilder()
        int index = 0
        String lastCharacter = "a"
        int digitCounter = 0
        while (index < seed.length()) {
            String digit = seed.substring(index, index + 1)
            if (digit.equals(lastCharacter)) {
                digitCounter++
            } else {
                if (!lastCharacter.equals("a")) {
                    acc.append(digitCounter.toString() + lastCharacter)
                }
                digitCounter = 1
                lastCharacter = digit
            }
            index++
        }
        acc.append(digitCounter.toString() + lastCharacter)
        return acc
    }
}
