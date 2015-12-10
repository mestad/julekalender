

/**
 * Created by vsxxame on 01.12.2015.
 */
class Aksjer_kalender1012 {


    public static final main(String[] args) {
        Transaction best
        Transaction newBest
        Transaction secondCandidate
        int counter = 0
        BigDecimal firstLine = new BigDecimal("100000")
        BigDecimal secondLine = new BigDecimal("10000")
        BigDecimal thirdLine = new BigDecimal("10000")
        new File("..\\resources\\0212_aksjer.txt").eachLine {line ->
            firstLine = secondLine
            secondLine = thirdLine
            thirdLine = new BigDecimal(line)
            if (isLocalMinimum(firstLine, secondLine, thirdLine)) {
                if (best == null) {
                    best = new Transaction(secondLine, thirdLine)
                } else if (newBest == null) {
                    newBest = new Transaction(secondLine, thirdLine, )
                } else if (newBest.buy.compareTo(secondLine) > 0) {
                    newBest.recalculateNewBuy(secondLine)
                }


            }

            (best, newBest, kurs) = luke2_mailloop(line)
            println (best.toString() + " " + newBest.toString() + " " + kurs + " teller: " + counter++)
        }
        println "Resultat: " + best.toString() + " nestbest: " + newBest.toString()
    }

    private Boolean isLocalMaximum(BigDecimal a, BigDecimal b, BigDecimal c) {
        return (a.compareTo(b) <= 0 && c.compareTo(b) <= 0)
    }

    private Boolean isLocalMinimum(BigDecimal a, BigDecimal b, BigDecimal c) {
        return (a.compareTo(b) >= 0 && c.compareTo(b) >= 0)
    }

    private static List luke2_mailloop(String line) {
        Transaction best, newBest
        BigDecimal kurs = new BigDecimal(line)
        if (best == null) best = new Transaction(kurs, kurs, BigDecimal.ZERO)
        if (newBest == null) newBest = new Transaction(kurs, kurs, BigDecimal.ZERO)
        if (newBest.buy.compareTo(kurs) > 0) newBest.recalculateNewBuy(kurs)
        if (best.profit.compareTo(newBest.profit) < 0) best = new Transaction(newBest)
        if (best.sell.compareTo(kurs) < 0) {
            best.recalculateNewSell(kurs)
            newBest.recalculateNewSell(kurs)
        }
        [best, newBest, kurs]
    }

    static class Transaction {
        BigDecimal buy
        BigDecimal sell
        BigDecimal profit

        Transaction(Transaction old) {
            buy = old.buy
            sell = old.sell
            profit = old.profit
        }

        Transaction(BigDecimal buy, BigDecimal sell, BigDecimal profit) {
            this.buy = buy
            this.sell = sell
            this.profit = profit
        }

        Transaction(BigDecimal buy, BigDecimal sell) {
            this.buy = buy
            this.sell = sell
            this.profit = sell.subtract(profit)
        }

        void recalculateNewBuy(BigDecimal newBuy) {
            buy = newBuy
            sell = newBuy
            profit = sell.subtract(buy)
        }

        void recalculateNewSell(BigDecimal newSell) {
            sell = newSell
            profit = sell.subtract(buy)
        }




        @Override
        public String toString() {
            return "Transaction{" +
                    "buy=" + buy +
                    ", sell=" + sell +
                    ", profit=" + profit +
                    '}';
        }
    }



}
