

/**
 * Created by vsxxame on 01.12.2015.
 */
class Aksjer_kalender1012 {


    public static void main(String[] args) {
        Transaction first, second
        int counter = 0
        List<Transaction> possibleTransactions = new ArrayList<>()
        BigDecimal firstLine = new BigDecimal("100000")
        BigDecimal secondLine = new BigDecimal("10000")
        BigDecimal thirdLine = new BigDecimal("10000")
        Extreme firstMin, secondMin, firstMax,secondMax
        first = new Transaction(firstLine, secondLine)
        second = new Transaction(firstLine, secondLine)
        SuperTransaction firstSuper = new SuperTransaction(new Transaction(first), new Transaction(second))
        SuperTransaction secondSuper = new SuperTransaction(new Transaction(first), new Transaction(second))
        int lineCounter = 0;
        new File("c:\\aase\\scripts\\src\\aksjer_kalender1012.txt").eachLine {line ->
            BigDecimal kurs = new BigDecimal(line)
            firstLine = secondLine
            secondLine = thirdLine
            thirdLine = kurs
            lineCounter++
            if (isLocalMinimum(firstLine, secondLine, thirdLine)) {
                if (firstMin == null) firstMin = new Extreme(secondLine, lineCounter);
                if (secondMin == null) secondMin = new Extreme(secondLine, lineCounter);
                if (secondLine.compareTo(firstMin.kurs) < 0) {
                    secondMin = firstMin
                    firstMin = new Extreme(secondLine, lineCounter);
                } else if (secondLine.compareTo(secondMin.kurs) < 0) {
                    secondMin = new Extreme(secondLine, lineCounter);
                }
            } else if (isLocalMaximum(firstLine, secondLine, thirdLine)) {
                if (secondLine.compareTo(BigDecimal.valueOf(10000)) == 0) {

                } else {
                    if (firstMax == null) firstMax = new Extreme(secondLine, lineCounter);
                    if (secondMax == null) secondMax = new Extreme(secondLine, lineCounter);
                    if (secondLine.compareTo(firstMax.kurs) > 0) {
                        secondMin = firstMax
                        firstMax = new Extreme(secondLine, lineCounter);
                    } else if (secondLine.compareTo(secondMax.kurs) > 0) {
                        secondMax = new Extreme(secondLine, lineCounter);
                    }
                }
            }
        }
        if (thirdLine.compareTo(firstMin.kurs) < 0) {
            secondMin = firstMin
            firstMin = thirdLine
        } else if (thirdLine.compareTo(secondMin.kurs) < 0) {
            secondMin = thirdLine
        }
        if (thirdLine.compareTo(firstMax.kurs) > 0) {
            secondMin = firstMax
            firstMax = thirdLine
        } else if (thirdLine.compareTo(secondMax.kurs) > 0) {
            secondMax = thirdLine
        }
        println "Firstmin: " + firstMin + " secondMin: " + secondMin + " firstMax: " + firstMax + " secondMax: " + secondMax
//        println "Resultat: " + best.toString() + " nestbest: " + newBest.toString()
    }

    public static class Extreme {
        BigDecimal kurs
        int index

        Extreme(BigDecimal kurs, int index) {
            this.kurs = kurs
            this.index = index
        }


        @Override
        public String toString() {
            return "Extreme{" +
                    "kurs=" + kurs +
                    ", index=" + index +
                    '}';
        }
    }

    private static void countTransactions(String line) {
        BigDecimal thirdLine
        int counter
        Transaction first
        BigDecimal firstLine
        BigDecimal secondLine
        firstLine = secondLine
        secondLine = thirdLine
        thirdLine = new BigDecimal(line)
        if (isLocalMinimum(firstLine, secondLine, thirdLine)) {
            first = new Transaction(secondLine, thirdLine)
        }
        if (isLocalMaximum(firstLine, secondLine, thirdLine)) {
            first.recalculateNewSell(secondLine)
            counter++
//            possibleTransactions.add(first)
            println("Transaksjon: " + first + " - " + counter)
        }
    }

    private static Boolean isLocalMaximum(BigDecimal a, BigDecimal b, BigDecimal c) {
        return (a.compareTo(b) <= 0 && c.compareTo(b) <= 0)
    }

    private static Boolean isLocalMinimum(BigDecimal a, BigDecimal b, BigDecimal c) {
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

    static class SuperTransaction {
        public Transaction first
        public Transaction second
        public BigDecimal totalProfit

        SuperTransaction(Transaction first, Transaction second) {
            this.first = first
            this.second = second
            this.totalProfit = first.profit + second.profit
        }
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
            this.profit = sell.subtract(buy)
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
