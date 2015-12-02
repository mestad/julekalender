

/**
 * Created by vsxxame on 01.12.2015.
 */
class kalender0112 {


    public static final main(String[] args) {
        int ok = 0;
        int notOk = 0;
        def File myFile = new File("c:\\aase\\kalender\\forste_desember.txt")
        myFile.eachLine() { line ->
            if (line.matches("^[a-z]{0,3}\\d{2,8}[A-Z]{3,}\$")) {
                ok++
            } else {
                notOk++
            }

        }
        println ("OK: " + ok + " Not ok: " + notOk)
    }


}
