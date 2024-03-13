import java.util.HashMap;
import java.util.Map;

public class BBS {

    public static void main(String[] args) {
        long p = 35267;
        long q = 32749;
        long x = 22814;
        double numberOfIterations = 20_000;


        String bitString = generate(p, q, x, numberOfIterations);
        singleBitTest(bitString);
        seriesTest(bitString);
        longSeriesTest(bitString);
        testPoker(bitString);
    }

    public static String generate(long p, long q, long x, double numberOfIterations) {
        long N = p * q;
        long x0 = (x * x) % N;
        StringBuilder bitBuilder = new StringBuilder();
        long xi = x0;
        for (int i = 0; i < numberOfIterations; i++) {
            xi = (xi * xi) % N;
            bitBuilder.append(xi & 1);
        }
        return bitBuilder.toString();
    }

    public static void testPoker(String bitString) {
        Map<String, Integer> sequenceCounts = new HashMap<>();

        for (int i = 0; i < bitString.length() - 3; i += 4) {
            String sequence = bitString.substring(i, i + 4);
            sequenceCounts.put(sequence, sequenceCounts.getOrDefault(sequence, 0) + 1);
        }

        int sum = sequenceCounts.values().stream()
                .mapToInt(val -> val * val)
                .sum();
        double result = (16.0 * sum / 5000) - 5000;

        System.out.println();
        if (result > 2.16 && result < 46.17) {
            System.out.println("[POSITIVE] Poker test | Value: " + result);
        } else {
            System.out.println("[NEGATIVE] Poker test | Value: " + result);
        }
    }

    public static void singleBitTest(String bitString) {
        int ones = 0;
        for (char bit : bitString.toCharArray()) {
            ones += (bit == '1') ? 1 : 0;
        }
        System.out.println();
        if (ones > 9725 && ones < 10275) {
            System.out.println("[POSITIVE] Single bit test | Value: " + ones);
        } else {
            System.out.println("[NEGATIVE] Single bit test | Value: " + ones);
        }
    }

    public static void seriesTest(String bitString) {
        int currentSeries = 1;
        char previousBit = bitString.charAt(0);

        int firstSeries = 0;
        int secondSeries = 0;
        int thirdSeries = 0;
        int fourthSeries = 0;
        int fifthSeries = 0;
        int sixthSeries = 0;
        boolean isNegative = false;

        for (int i = 1; i < bitString.length(); i++) {
            char bit = bitString.charAt(i); // Aktualny bit
            if (bit == previousBit) {
                currentSeries++;
            } else {
                switch (currentSeries) {
                    case 0 -> {
                        break;
                    }
                    case 1 -> firstSeries++;
                    case 2 -> secondSeries++;
                    case 3 -> thirdSeries++;
                    case 4 -> fourthSeries++;
                    case 5 -> fifthSeries++;
                    default -> sixthSeries++;
                }
                currentSeries = 0;
                previousBit = bit;
            }
        }

        if (firstSeries >= 2315 && firstSeries <= 2685) {
            System.out.println("[POSITIVE] 1: " + firstSeries);
        } else {
            System.out.println("[NEGATIVE] 1: " + firstSeries);
            isNegative = true;
        }

        if (secondSeries >= 1114 && secondSeries <= 1386) {
            System.out.println("[POSITIVE] 2: " + secondSeries);
        } else {
            System.out.println("[NEGATIVE] 2: " + secondSeries);
            isNegative = true;
        }

        if (thirdSeries >= 527 && thirdSeries <= 723) {
            System.out.println("[POSITIVE] 3: " + thirdSeries);
        } else {
            System.out.println("[NEGATIVE] 3: " + thirdSeries);
            isNegative = true;
        }

        if (fourthSeries >= 240 && fourthSeries <= 384) {
            System.out.println("[POSITIVE] 4: " + fourthSeries);
        } else {
            System.out.println("[NEGATIVE] 4: " + fourthSeries);
            isNegative = true;
        }

        if (fifthSeries >= 103 && fifthSeries <= 209) {
            System.out.println("[POSITIVE] 5: " + fifthSeries);
        } else {
            System.out.println("[NEGATIVE] 5: " + fifthSeries);
            isNegative = true;
        }

        if (sixthSeries >= 103 && fifthSeries <= 209) {
            System.out.println("[POSITIVE] 6: " + sixthSeries);
        } else {
            System.out.println("[NEGATIVE] 6: " + sixthSeries);
            isNegative = true;
        }
        if (!isNegative){
            System.out.println("[POSITIVE] Series test");
        } else {
            System.out.println("[NEGATIVE] Series test");
        }
    }

    public static void longSeriesTest(String bitString) {
        int currentSeries = 1;
        char previousBit = bitString.charAt(0);
        int longestSeries = 0;

        for (int i = 1; i < bitString.length(); i++) {
            char bit = bitString.charAt(i);
            if (bit == previousBit) {
                currentSeries++;
            } else {
                longestSeries = Math.max(longestSeries, currentSeries);
                currentSeries = 0;
                previousBit = bit;
            }
        }
        System.out.println();
        if (longestSeries < 26){
            System.out.println("[POSITIVE] Long series test | Value: "+ longestSeries);
        } else {
            System.out.println("[NEGATIVE] Long series test | Value: "+ longestSeries);
        }
    }
}