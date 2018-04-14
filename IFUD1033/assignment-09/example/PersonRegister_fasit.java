/**
 * Personregister.java   E.L.
 * Filen inneholder eksempelkoden i leksjon 9, Videregående programmering.
 *
 * Først ligger klassene Fag og Person. Deretter følger to subklasser til Person: Ansatt og Student.
 * Deretter følger klassen PersonRegister, som også inneholder en enkel testklient.
 */

/**
 * KLASSEN FAG.
 * Et objekt av klassen fag inneholder fagnr. og karakter.
 * Det er ikke lagt inn noe kontroll av gyldighet av data, verken for fagnr. eller karakter.
 */
class Fag {
  public static final char KARAKTER_IKKE_SATT = 'S';
  private final String fagnr;
  private char karakter = KARAKTER_IKKE_SATT;

  public Fag(String fagnr) {
    this.fagnr = fagnr;
  }

  public String getFagnr() {
    return fagnr;
  }

  public void setKarakter(char nyKarakter) {
    karakter = nyKarakter;
  }

  public char getKarakter() {
    return karakter;
  }
}

/**
 * KLASSEN PERSON.
 * Et objekt av klassen Person inneholder informasjon om fødselsnummer, navn og adresse.
 * Det er ikke lagt inn kontroll av gyldighet av data.
 * Klassen er immutabel.
 */
class Person {
  private final long fnr;
  private final String navn;
  private final String adresse;

  public Person(long fnr, String navn, String adresse) {
    this.fnr = fnr;
    this.navn = navn;
    this.adresse = adresse;
  }

  public long getFnr() {
    return fnr;
  }

  public String getNavn() {
    return navn;
  }

  public String getAdresse() {
    return adresse;
  }
}

/**
 * KLASSEN ANSATT.
 * Et ansattobjekt er et personobjekt med tilleggsinformasjon om lønnstrinn.
 */
class Ansatt extends Person {
  private int lønnstrinn;

  public Ansatt(long fnr, String navn, String adresse, int lønnstrinn) {
    super(fnr, navn, adresse);
    this.lønnstrinn = lønnstrinn;
  }

  public int getLønnstrinn() {
    return lønnstrinn;
  }

  public void setLønnstrinn(int nyttLønnstrinn) {
    lønnstrinn = nyttLønnstrinn;
  }
}

/**
 * KLASSEN STUDENT.
 * Et studentobjekt er et personobjekt med tilleggsinformasjon om studentnummer og
 * alle fagene studenten har tatt eller skal ta. Listen med fag kan utvides.
 * Det er videre mulig å registrere og hente ut karakteren i et bestemt fag.
 */
class Student extends Person {
  public static final char FAGNR_IKKE_REGISTRERT = 'Y';

  private final long studnr;
  private java.util.ArrayList<Fag> alleFagene = new java.util.ArrayList<Fag>();

  public Student(long fnr, String navn, String adresse, long studnr) {
    super(fnr, navn, adresse);
    this.studnr = studnr;
  }

  public long getStudnr() {
    return studnr;
  }

  /**
   * Metoden tar fagnummer som argument.
   * Den registrerer nytt fag med dette nummeret, dersom det ikke er registrert fra før.
   * Metoden returnerer en referanse til fagobjektet, uansett om det er nytt eller ikke.
   */
  public Fag registrerNyttFag(String fagnr) {
    Fag f = finnFag(fagnr);
    if (f == null) { // intet fag med dette fagnr er registrert fra før
      Fag nyttFag = new Fag(fagnr);
      alleFagene.add(nyttFag);
      return nyttFag;
    }
    return f;
  }

  /**
   * Metoden finner karakteren i et bestemt fag.
   */
  public char finnKarakter(String fagnr) {
    for (Fag etFag : alleFagene) {
      String fnr = etFag.getFagnr();
      if (fnr.equals(fagnr)) {
        return etFag.getKarakter();
      }
    }
    return FAGNR_IKKE_REGISTRERT; // har gått gjennom alle og ikke funnet faget
  }

  /**
   * Metoden registrerer karakter i et bestemt fag.
   * Det er ingen kontroll av at karakteren er gyldig.
   * Hvis fag med den oppgitte koden ikke er registrert fra før,
   * så blir det registrert.
   */
  public void settKarakter(String fagnr, char nyKarakter) {
    Fag f = registrerNyttFag(fagnr);  // registrering bare hvis ikke registrert fra før
    f.setKarakter(nyKarakter);
  }

  /**
   * Metoden leter opp fag med det oppgitte nummer.
   * Returnerer null hvis fag ikke finnes.
   */
  public Fag finnFag(String fagnr) {
    for (Fag etFag : alleFagene) {
      String fnr = etFag.getFagnr();
      if (fnr.equals(fagnr)) {
        return etFag;
      }
    }
    return null; // har gått gjennom alle og ikke funnet faget
  }
}

/**
 * KLASSEN PERSONREGISTER.
 * Klassen Personregister administrerer en oversikt over ansatte og studenter.
 * Det er mulig å registrere nye personer og å søke opp registrerte personer.
 *
 * Bare for studenter: Mulig å registrere fag og karakterer samt å søke opp karakterer.
 * Bare for ansatte: Det er mulig å endre lønnen.
 */

class Personregister {
  public static final char PERSON_FINS_IKKE = 'X';

  private java.util.ArrayList<Person> allePersonene = new java.util.ArrayList<Person>();

  /**
   * Metoden registrerer ny person dersom person med det oppgitte nummer ikke fins fra før.
   * I siste tilfelle returnerer metoden false.
   */
  public boolean registrerNyPerson(Person nyPerson) {
    Person p = finnPerson(nyPerson.getFnr());
    if (p == null) { // ingen person med dette fnr er registrert fra før
      allePersonene.add(nyPerson);
      return true;
    }
    return false;
  }

  /**
   * Metoden søker opp person, gitt nummer.
   * Metoden returnerer null dersom person ikke fins.
   */
  public Person finnPerson(long fnr) {
    for (Person p : allePersonene) {
      if (p.getFnr() == fnr) {
        return p;
      }
    }
    return null; // har gått gjennom alle og ikke funnet personen
  }

  /**
   * Metoden endrer lønnen til en bestemt person.
   * Returnerer false hvis ingen ansatt med det oppgitte nummeret er registrert.
   */
  public boolean endreLønn(long fnrAns, int nyttLønnstrinn) {
    Person p = finnPerson(fnrAns);
    if (p != null && p instanceof Ansatt) {
      Ansatt ans = (Ansatt) p;
      ans.setLønnstrinn(nyttLønnstrinn);
      return true;
    }
    return false;
  }

  /**
   * Metoden registrerer nytt fag for en bestemt student.
   * Returnerer false hvis ingen student med det oppgitte nummeret er registrert.
   */
  public boolean registrerNyttFag(long fnrStud, String nyttFag) {
    Person pers = finnPerson(fnrStud);
    if (pers != null && pers instanceof Student) {
      Student stud = (Student) pers;
      stud.registrerNyttFag(nyttFag);
      return true;
    }
    return false;  // ugyldig studentnr
  }

  /**
   * Metoden setter karakter i et bestemt fag for en bestemt student.
   * Returnerer false hvis ingen student med det oppgitte nummeret er registrert.
   */
  public boolean settKarakter(long fnrStud, String fagnr, char nyKarakter) {
    Person pers = finnPerson(fnrStud);
    if (pers != null && pers instanceof Student) {
      Student stud = (Student) pers;
      stud.settKarakter(fagnr, nyKarakter);
      return true;
    }
    return false;  // ugyldig studentnr
  }

  /**
   * Metoden returnrerer karakteren i et bestemt fag for en bestemt student.
   * Returnerer konstanten Personregister.PERSON_FINS_IKKE hvis ingen student
   * med det oppgitte nummeret er registrert.
   */
  public char finnKarakter(long fnrStud, String fagnr) {
    Person pers = finnPerson(fnrStud);
    if (pers != null && pers instanceof Student) {
      Student stud = (Student) pers;
      return stud.finnKarakter(fagnr);
    }
    return PERSON_FINS_IKKE;
  }

  /**
   * Her kommer en meget ufullstendig test klient.
   */
  public static void main(String[] args) {
    Student student1 = new Student(12106078756L, "Ole Pettersen",
      "Storgt 3, 7001 Trondheim", 1234567L);
    Student student2 = new Student(12125678756L, "Per Hansen",
          "Storgt 13, 7001 Trondheim", 1234557L);
    Ansatt læreren = new Ansatt(15107078056L, "Hanne Hansen",
      "Storgt 13, 7001 Trondheim", 50);
    Personregister registeret = new Personregister();
    registeret.registrerNyPerson(læreren);
    registeret.registrerNyPerson(student1);
    registeret.registrerNyPerson(student2);
    registeret.registrerNyttFag(12106078756L, "HiST-LO172D"); // student1
    registeret.registrerNyttFag(12125678756L, "HiST-LO451D"); // student2
    student2.registrerNyttFag("HiST-LO451D");
    student1.settKarakter("HiST-LO451D", 'C');
    student2.settKarakter("HiST-LO451D", 'B');
    registeret.settKarakter(12106078756L, "HiST-LO445D", 'F');

    char søk1 = registeret.finnKarakter(12125678756L, "HiST-LO172D");  // ugyldig fagnr for denne studenten
    char søk2 = registeret.finnKarakter(12125678756L, "HiST-LO451D");
    char søk3 = registeret.finnKarakter(12106078756L, "HiST-LO451D");
    char søk4 = registeret.finnKarakter(12106078756L, "HiST-LO445D");
    registeret.endreLønn(15107078056L, 52);  // endrer lærerlønnen via registeret

    if (søk1 == 'Y' && søk2 == 'B' && søk3 == 'C' && søk4 == 'F' && læreren.getLønnstrinn() == 52) {
      System.out.println("Testkjøring ok.");
    } else {
      System.out.println("Testkjøring ikke ok. Skal være Y B C F 52, det var: "
            + søk1 + " " + søk2 + " " + søk3 + " " + søk4 + " " + læreren.getLønnstrinn());
    }
  }
}