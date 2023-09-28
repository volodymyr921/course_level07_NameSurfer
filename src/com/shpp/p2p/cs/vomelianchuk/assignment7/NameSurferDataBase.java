package com.shpp.p2p.cs.vomelianchuk.assignment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NameSurferDataBase implements NameSurferConstants {

    private final ArrayList<NameSurferEntry> surferEntries = new ArrayList<>();

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     *
     * @param filename The name of the information file
     */
    public NameSurferDataBase(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while(true) {
                String line = br.readLine();
                if (line == null) break;
                surferEntries.add(new NameSurferEntry(line));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     *
     * @param name The name of the person whose information we are looking for
     */
    public NameSurferEntry findEntry(String name) {
        for (NameSurferEntry entry : surferEntries) {
            if (entry.getName().equalsIgnoreCase(name)) {
                return entry;
            }
        }
        return null;
    }
}

