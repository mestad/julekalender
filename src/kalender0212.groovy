

/**
 * Created by vsxxame on 01.12.2015.
 */
class kalender0212 {


    public static final main(String[] args) {
        Transaction best
        Transaction newBest
        int counter = 0
        new File("..\\resources\\0212_aksjer.txt").eachLine {line ->
            BigDecimal kurs = new BigDecimal(line)
            if (best == null) best = new Transaction(kurs, kurs, BigDecimal.ZERO)
            if (newBest == null) newBest = new Transaction(kurs, kurs, BigDecimal.ZERO)
            if (newBest.buy.compareTo(kurs) > 0) newBest.recalculateNewBuy(kurs)
            if (best.profit.compareTo(newBest.profit) < 0) best = new Transaction(newBest)
            if (best.sell.compareTo(kurs) < 0) {
                best.recalculateNewSell(kurs)
                newBest.recalculateNewSell(kurs)
            }
            println (best.toString() + " " + newBest.toString() + " " + kurs + " teller: " + counter++)
        }
        println "Resultat: " + best.toString() + " nestbest: " + newBest.toString()
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
