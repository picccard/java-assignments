/*
 * Passordtegn
 */
class Passordtegn {
  public static final int STOR_AS_TALLVERDI = (int) 'A'; // A's tallverdi i Unicode-tegnsettet (s. 62, 921)
  public static final int LITEN_AS_TALLVERDI = (int) 'a';
  public static final int NULLS_TALLVERDI = (int) '0';
  public static final int FØRSTETEGNSTALLVERDI = 33; // den første av de skrivbare unntatt ' ' (mellomrom som er nr 32)
  public static final int SISTETEGNSTALLVERDI = 126; // den siste av de skrivbare ('~')

  public static final int ANTALL_STORE_BOKSTAVER = 'Z' - 'A' + 1; // f.o.m. A t.o.m. Z
  public static final int ANTALL_SMÅ_BOKSTAVER = 'z' - 'a' + 1;  // f.o.m. a t.o.m. z
  public static final int ANTALL_SIFRE = '9' - '0' + 1;  // f.o.m. 0 t.o.m. 9

  private static final java.util.Random randomGen = new java.util.Random(); // Se delkapittel 8.4 og s. 275

  public static boolean erStorBokstav(char tegn) {
    return tegn >= 'A' && tegn <= 'Z';
  }

  public static boolean erLitenBokstav(char tegn) {
    return tegn >= 'a' && tegn <= 'z';
  }

  public static boolean erSiffer(char tegn) {
    return tegn >= '0' && tegn <= '9';
  }

  public static boolean erSpesialtegn(char tegn) {
    return erPassordtegn(tegn) && !erStorBokstav(tegn) && !erLitenBokstav(tegn) && !erSiffer(tegn);
  }

  public static boolean erPassordtegn(char tegn) {
     return tegn >= (char) FØRSTETEGNSTALLVERDI && tegn <= (char) SISTETEGNSTALLVERDI;
  }

  /* ekstra */
  public static boolean erBokstavEllerSiffer(char tegn) {
    return erStorBokstav(tegn) || erLitenBokstav(tegn) || erSiffer(tegn);
  }

  public static char trekkStorBokstav() {
    int tall = randomGen.nextInt(ANTALL_STORE_BOKSTAVER) + STOR_AS_TALLVERDI;
        // nextInt() returnerer et heltall i intervallet [0, ANTALL_STORE_BOKSTAVER - 1]
    return (char) tall;
  }

  public static char trekkLitenBokstav() {
    int tall = randomGen.nextInt(ANTALL_SMÅ_BOKSTAVER) + LITEN_AS_TALLVERDI;
        // nextInt() returnerer et heltall i intervallet [0, ANTALL_SMÅ_BOKSTAVER - 1]
    return (char) tall;
  }

  public static char trekkSiffer() {
    int tall = randomGen.nextInt(ANTALL_SIFRE) + NULLS_TALLVERDI;
        // nextInt() returnerer et heltall i intervallet [0, ANTALL_SIFRE - 1]
    return (char) tall;
  }

  public static char trekkSpesialtegn() {
    int tall = (int) STOR_AS_TALLVERDI;
    while (erBokstavEllerSiffer((char) tall)) {
      tall = randomGen.nextInt((int) SISTETEGNSTALLVERDI - (int) FØRSTETEGNSTALLVERDI + 1) + FØRSTETEGNSTALLVERDI;
      /* nextInt() returnerer et heltall i intervallet [0, (int) SISTETEGNSTALLVERDI - (int) FØRSTETEGNSTALLVERDI] */
    }
    return (char) tall;
  }
} // Passordtegn
