package com.develpoment.gobolabali.fundamentalstatistic.Helpers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Created by Rian on 12/07/2018.
 */

public class CSVWritter {
    public static final char DEFAULT_ESCAPE_CHARACTER = '\"';
    public static final String DEFAULT_LINE_END = "\n";
    public static final char DEFAULT_QUOTE_CHARACTER = '\"';
    public static final char DEFAULT_SEPARATOR = ',';
    public static final char NO_ESCAPE_CHARACTER = '\u0000';
    public static final char NO_QUOTE_CHARACTER = '\u0000';
    private char escapechar;
    private String lineEnd;
    private PrintWriter pw;
    private char quotechar;
    private char separator;

    public CSVWritter(Writer writer) {
        this(writer, DEFAULT_SEPARATOR, '\"', '\"', DEFAULT_LINE_END);
    }

    public CSVWritter(Writer writer, char separator, char quotechar, char escapechar, String lineEnd) {
        this.pw = new PrintWriter(writer);
        this.separator = separator;
        this.quotechar = quotechar;
        this.escapechar = escapechar;
        this.lineEnd = lineEnd;
    }

    public void writeNext(String[] nextLine) {
        if (nextLine != null) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < nextLine.length; i++) {
                if (i != 0) {
                    sb.append(separator);
                }
                String nextElement = nextLine[i];
                if (nextElement != null) {
                    if (quotechar != '\u0000') {
                        sb.append(quotechar);
                    }
                    for (int j = 0; j < nextElement.length(); j++) {
                        char nextChar = nextElement.charAt(j);
                        if (escapechar != '\u0000' && nextChar == quotechar) {
                            sb.append(escapechar).append(nextChar);
                        } else if (escapechar == '\u0000' || nextChar != escapechar) {
                            sb.append(nextChar);
                        } else {
                            sb.append(escapechar).append(nextChar);
                        }
                    }
                    if (quotechar != '\u0000') {
                        sb.append(quotechar);
                    }
                }
            }
            sb.append(lineEnd);
            pw.write(sb.toString());
        }
    }

    public void flush() throws IOException {
        pw.flush();
    }

    public void close() throws IOException {
        pw.flush();
        pw.close();
    }
}

