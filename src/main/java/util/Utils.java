package util;

public class Utils {
    public static boolean isIn(int[] array, int value) {
        for (int i : array) {
            if (i == value) return true;
        }
        return false;
    }
}
