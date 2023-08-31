package pl.mroz.buddiesapi.domain.generation

import org.apache.commons.lang3.RandomUtils

import java.time.LocalDate
import java.time.Month
import java.time.temporal.ChronoUnit
import java.util.function.IntConsumer
import java.util.stream.IntStream

trait RandomBuilder {

    static UUID getRandomUUID() {
        return UUID.randomUUID()
    }

    static int getRandomInt(int min, int max) {
        return RandomUtils.nextInt(min, max)

    }

    static double getRandomDouble(double min, double max) {
        return RandomUtils.nextDouble(min, max)
    }

    static boolean getRandomBoolean() {
        return RandomUtils.nextBoolean()
    }

    static LocalDate getRandomDateInRange(LocalDate a, LocalDate b) {
        def daysBetween = ChronoUnit.DAYS.between(a,b).intValue()
        return a.plusDays(getRandomInt(0,daysBetween + 1))
    }

    static LocalDate getRandomDate() {
        return getRandomDateInRange(LocalDate.of(2010, Month.JANUARY,1), LocalDate.of(2040, Month.DECEMBER,31))

    }

    static <T extends Enum> T getRandomEnum(Class<T> enumClass) {
        List<T> enums = enumClass.enumConstants as List<T>
        Collections.shuffle(enums)
        return enums.first()
    }

    static void doNTimes(int n, IntConsumer consumer) {
        IntStream.range(0,n).forEach(consumer)
    }

}