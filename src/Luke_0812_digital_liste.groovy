import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by vsxxame on 03.12.2015.
 */
class Luke_0812_digital_liste {
    /**
     * \\
     * \*
     * \xdd
     * "
     */
    public static void main(String[] args) {
        int counter = 0
//        new File("c:\\aase\\scripts\\src\\test_list_0812.txt").eachLine { line ->
        new File("c:\\aase\\scripts\\src\\list_0812.txt").eachLine { line ->

            def count = countExtraCharactersInLine(line)
            println (line + " - count: " + count)

            counter += count
        }
        println counter

    }

    private static Integer countExtraCharactersInLine(String line) {
        return addEscapes(line).length() - line.length()
    }

    private static Integer countCharactersInLine(String line) {
        int total = line.length()
        String strippedLine = line.substring(1, line.length() - 1)
        strippedLine = removeHexes(strippedLine)
        strippedLine = removeEscapes(strippedLine)
        strippedLine = removeOtherEscapes(strippedLine)
        return (total - strippedLine.length())
    }

    private static String addEscapes(String line) {
        String newLine = line.replace("\\", "\\\\")
        newLine = newLine.replace("\"", "\\\"")
        return "\"" + newLine + "\""
    }

    private static String removeHexes(String line) {
        Pattern p = Pattern.compile("\\\\x(\\d|[a-h])(\\d|[a-h])")
        Matcher m = p.matcher(line);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "w");
        }
        m.appendTail(sb);
        return sb.toString()
    }

    private static String removeEscapes(String line) {
        Pattern p = Pattern.compile("\\\\\\\\")
        Matcher m = p.matcher(line);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "w");
        }
        m.appendTail(sb);
        return sb.toString()
    }

    private static String removeOtherEscapes(String line) {
        Pattern p = Pattern.compile("\\\\")
        Matcher m = p.matcher(line);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString()
    }


}
