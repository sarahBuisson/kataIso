/*******
 * Read input from System.in
 * Use System.out.println to ouput your result.
 * Use:
 *  IsoContestBase.localEcho( variable)
 * to display variable in a dedicated area.
 * ***/
package com.isograd.exercise;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;

public class IsoContest {


    public static void mainContent(Scanner sc) throws Exception {
        String line;

        while (sc.hasNextLine()) {
            line = sc.nextLine();
            /* Lisez les données et effectuez votre traitement */
            sout(line);
        }
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }


    public static void main(String[] argv) throws Exception {

        Scanner sc = new Scanner(System.in);
        mainContent(sc);


    }

    public static void sout(Object message) {
        if (debug)
            System.out.println("sout" + message);
        out.println(message);

    }

    public static void log(Object message) {

        if (debug)
            out.println("log" + message.toString());

    }

    public static boolean debug = false;

    public static PrintStream out = System.out;

    public static void initDebug(PrintStream outs) {
        debug = true;
        out = outs;

    }
}