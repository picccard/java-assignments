import java.text.MessageFormat;
import java.lang.StringBuilder;
import java.util.Arrays;
import java.io.Serializable;

public class Subject implements Serializable {
    private final String subjectCode;
    private final String subjectName;
    private final int credits;
    private char[] results;

    public Subject(String subjectCode, String subjectName, int credits, char[] results) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
        this.results = results;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(MessageFormat.format("Subject Code: {0}\nSubject Name: {1}\nCredits: {2}\nTotal Results: {3} {4}\n", subjectCode, subjectName, credits, results.length, Arrays.toString(results)));
        /*for (int i = 0; i < results.length; i++) {
            sb.append(results[i]);
            if (i < results.length-1) {
                sb.append(", ");
            }
        }*/
        return sb.toString();
    }
}
