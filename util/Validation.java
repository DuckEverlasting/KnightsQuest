package util;

public class Validation {
  public static int validateInteger(int num, int min, int max) {
    if (num < min) num = min;
    if (num > max) num = max;
    return num;
  }

  public static int validateInteger(int num, int max) {
    if (num < 0) num = 0;
    if (num > max) num = max;
    return num;
  }

  public static int validateInteger(int num) {
    if (num < 0) num = 0;
    return num;
  }
}