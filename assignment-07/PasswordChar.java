/*
 * PasswordChar
 */
class PasswordChar {
  public static final int UPPER_A_NR_VALUE = (int) 'A'; // A's numbervalue in Unicode-charsett
  public static final int LOWER_A_NR_VALUE = (int) 'a';
  public static final int ZERO_NR_VALUE = (int) '0';
  public static final int FIRST_LETTER_NR_VALUE = 33; // the first typeable char except ' ' (space is nr 32)
  public static final int LAST_LETTER_NR_VALUE = 126; // the last typeable char ('~')

  public static final int COUNT_UPPER_LETTERS = 'Z' - 'A' + 1; // from a to Z, including both A and Z
  public static final int COUNT_LOWER_LETTERS = 'z' - 'a' + 1;  // from a to z, including both a and z
  public static final int COUNT_DIGITS = '9' - '0' + 1;  // from 0 to 9, including both 0 and 9

  private static final java.util.Random randomGen = new java.util.Random();

  public static boolean isUpperLetter(char chr) {
    return chr >= 'A' && chr <= 'Z';
  }

  public static boolean isLowerLetter(char chr) {
    return chr >= 'a' && chr <= 'z';
  }

  public static boolean isDigit(char chr) {
    return chr >= '0' && chr <= '9';
  }

  public static boolean isSpecialChar(char chr) {
    return isPasswordChar(chr) && !isUpperLetter(chr) && !isLowerLetter(chr) && !isDigit(chr);
  }

  public static boolean isPasswordChar(char chr) {
     return chr >= (char) FIRST_LETTER_NR_VALUE && chr <= (char) LAST_LETTER_NR_VALUE;
  }

  /* extra */
  public static boolean isLetterOrDigit(char chr) {
      return isUpperLetter(chr) || isLowerLetter(chr) || isDigit(chr);
  }

  public static char drawUpperLetter() {
    int nr = randomGen.nextInt(COUNT_UPPER_LETTERS) + UPPER_A_NR_VALUE;
        // nextInt() returns an integer in the interval [0, COUNT_UPPER_LETTERS - 1]
    return (char) nr;
  }

  public static char drawLowerLetter() {
    int nr = randomGen.nextInt(COUNT_LOWER_LETTERS) + LOWER_A_NR_VALUE;
    // nextInt() returns an integer in the interval [0, COUNT_LOWER_LETTERS - 1]
    return (char) nr;
  }

  public static char drawDigit() {
    int nr = randomGen.nextInt(COUNT_DIGITS) + ZERO_NR_VALUE;
    // nextInt() returns an integer in the interval [0, COUNT_DIGITS - 1]
    return (char) nr;
  }

  public static char drawSpecialChar() {
    int nr = (int) UPPER_A_NR_VALUE;
    while (isLetterOrDigit((char) nr)) {
      nr = randomGen.nextInt((int) LAST_LETTER_NR_VALUE - (int) FIRST_LETTER_NR_VALUE + 1) + FIRST_LETTER_NR_VALUE;
      /* nextInt() returns an integer in the interval [0, (int) LAST_LETTER_NR_VALUE - (int) FIRST_LETTER_NR_VALUE] */
    }
    return (char) nr;
  }
} // PasswordChar
