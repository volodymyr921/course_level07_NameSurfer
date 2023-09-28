package com.shpp.p2p.cs.vomelianchuk.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

public class NameSurferEntry implements NameSurferConstants {

    private final String name;
    private final int[] rankDecades = new int[NDECADES];

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     *
     * @param line The line of information about a particular name and its popularity
     */
    public NameSurferEntry(String line) {
        String[] lineInformation = line.split(" ");

        name = lineInformation[0];
        for (int i = 1; i <= NDECADES; i++) {
            rankDecades[i - 1] = Integer.parseInt(lineInformation[i]);
        }
    }

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     *
     * @param decade Full year of a particular decade [1900, 2010]
     */
    public int getRank(int decade) {
        int index = (decade - START_DECADE) / TEN_YEARS;
        return rankDecades[index];
    }

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(name);
        stringBuilder.append(" [");
        for (int i = 0; i < NDECADES; i++) {
            stringBuilder.append(rankDecades[i]);
            if (i != NDECADES - 1) {
                stringBuilder.append(" ");
            }
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}

