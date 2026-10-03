package utils;

import java.awt.*;
import java.util.SplittableRandom;

public final class Utils {

    public Utils() {
        throw new UnsupportedOperationException("Utility class can't be instantiated");
    }

    public static int weightedRandomInt(double[] weights, SplittableRandom random) {

        //double sum = Arrays.stream(weights).sum();

        double sum = 0;

        for (double weight : weights) {
            sum += weight;
        }

        if (sum == 0) {
            throw new RuntimeException("sum of weights = 0");
        }

        double curr = 0;
        double target = random.nextDouble(0, sum);

        for (int i = 1; i < weights.length + 1; i++) {
            if (curr < target) {
                curr += weights[i - 1];
                if (curr >= target) {
                    return i - 1;
                }
            }
        }
        return 0;
    }

    public static Color[] getOpacityLUT(Color color) {
        Color[] colorLUT = new Color[255];
        for (int i = 0; i < colorLUT.length; i++) {
            colorLUT[i] = new Color(color.getRed(), color.getGreen(), color.getBlue(), i);
        }
        return colorLUT;
    }
}
