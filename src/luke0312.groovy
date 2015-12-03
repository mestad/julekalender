package no.vps.fund.batch.merge

/**
 * Created by vsxxame on 03.12.2015.
 */
class luke0312 {
    public static int PROGRAMMERS_DATE = 256

    public static final main(String[] args) {
        Year year1 = new Year(1, 0)
        int counter = 0
        println("Is day Friday? " + year1.isDay256AFriday())
        println("Next year offset: " + year1.nextYearOffset())
        while (year1.year <= 2015) {
            if (year1.isDay256AFriday()) counter++
            year1 = new Year(year1)
        }
        println ("Siste år: " + year1.year + " teller: " + counter)
    }

    static class Year{
        int year
        int firstDayOffsetFromFriday

        Year(int year, int firstDayOffsetFromFriday) {
            this.year = year
            this.firstDayOffsetFromFriday = firstDayOffsetFromFriday
        }

        public boolean isDay256AFriday() {
            return (PROGRAMMERS_DATE + firstDayOffsetFromFriday) % 7 == 0
        }

        public int nextYearOffset() {
            if (isLeapYear()) {
                return (366 + firstDayOffsetFromFriday) % 7
            } else {
                return (365 + firstDayOffsetFromFriday) % 7
            }
        }

        private boolean isLeapYear() {
            if (year % 400 == 0) return true
            if (year % 100 == 0) return false
            if (year % 4 == 0) return true
            return false
        }

        public Year(Year lastYear) {
            year = lastYear.year + 1
            firstDayOffsetFromFriday = lastYear.nextYearOffset()
        }
    }

}
