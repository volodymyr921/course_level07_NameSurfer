package com.shpp.p2p.cs.vomelianchuk.assignment7;

/*
 * File: NameSurferConstants.java
 * ------------------------------
 * This file declares several constants that are shared by the
 * different modules in the NameSurfer application.  Any class
 * that implements this interface can use these constants.
 */

public interface NameSurferConstants {

    /**
     * The width of the application window
     */
    public static final int APPLICATION_WIDTH = 800;

    /**
     * The height of the application window
     */
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * The name of the file containing the data
     */
    public static final String NAMES_DATA_FILE = "names-data.txt";

    /**
     * The first decade in the database
     */
    public static final int START_DECADE = 1900;

    /**
     * The number of decades
     */
    public static final int NDECADES = 12;

    /**
     * The maximum rank in the database
     */
    public static final int MAX_RANK = 1000;

    /**
     * The number of pixels to reserve at the top and bottom
     */
    public static final int GRAPH_MARGIN_SIZE = 20;

    /**
     * The number of years between decades
     */
    public static final int TEN_YEARS = 10;

    /**
     * The starting position of the vertical line on the left of the screen
     */
    public static final int START_DISTANCE_LEFT = 2;

    /**
     * The number of graph colors
     */
    public static final int COUNT_COLOR = 4;
}