package com.shpp.p2p.cs.vomelianchuk.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    ArrayList<NameSurferEntry> entries = new ArrayList<>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
    }

    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        entries.clear();
        update();
    }

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     *
     * @param entry  The information about the name and popularity to be added to the graph
     */
    public void addEntry(NameSurferEntry entry) {
        if (entry != null) {
            entries.add(entry);
            update();
        }
    }

    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();

        double distanceBetweenLines = ((double) getWidth() - START_DISTANCE_LEFT) / NDECADES;

        createAllLines(distanceBetweenLines);
        createLabelsDecade(distanceBetweenLines);
        for (int i = 0; i < entries.size(); i++) {
            drawGraph(entries.get(i), distanceBetweenLines, getColorGraph(i));
        }
    }

    /**
     * Creates a grid graphic from lines
     *
     * @param distance The distance between lines
     */
    private void createAllLines(double distance) {
        // creates vertical lines
        for (int i = 0; i < NDECADES; i++) {
            createLine(
                    START_DISTANCE_LEFT + (i * distance), getHeight(),
                    START_DISTANCE_LEFT + (i * distance), 0.0, Color.BLACK
            );
        }

        // creates horizontal lines
        createLine(
                START_DISTANCE_LEFT, GRAPH_MARGIN_SIZE,
                getWidth(), GRAPH_MARGIN_SIZE, Color.BLACK
        );
        createLine(
                START_DISTANCE_LEFT, getHeight() - GRAPH_MARGIN_SIZE,
                getWidth(), getHeight() - GRAPH_MARGIN_SIZE, Color.BLACK
        );
    }

    /**
     * Creates a line with the given coordinates and color
     *
     * @param x1 The x coordinate of the beginning of the line
     * @param y1 The y coordinate of the beginning of the line
     * @param x2 The x coordinate of the end of the line
     * @param y2 The y coordinate of the end of the line
     * @param color The color of line
     */
    private void createLine(double x1, double y1, double x2, double y2, Color color) {
        GLine line = new GLine(x1, y1, x2, y2);
        line.setColor(color);
        add(line);
    }

    /**
     * This method adds a label about the decade year
     *
     * @param distance The distance between lines
     */
    private void createLabelsDecade(double distance) {
        for (int i = 0; i < NDECADES; i++) {
            String decade = String.valueOf(START_DECADE + (i * TEN_YEARS));
            createLabel(decade, START_DISTANCE_LEFT + (i * distance), getHeight(), "Times-18", Color.BLACK);
        }
    }

    /**
     * Creates a label in the given coordinates with the given font and color
     *
     * @param text The text contained in the label
     * @param x The x coordinate of the label
     * @param y The y coordinate of the label
     * @param font The font name and size
     * @param color The color of label
     */
    private void createLabel(String text, double x, double y, String font, Color color) {
        GLabel label = new GLabel(text, x, y);
        label.setLocation(x, y - label.getDescent());
        label.setFont(font);
        label.setColor(color);
        add(label);
    }

    /**
     * This method draws a graph for a specific surfer entry
     *
     * @param entry The surfer entry
     * @param distance The distance between lines
     * @param color The color of graph
     */
    private void drawGraph(NameSurferEntry entry, double distance, Color color) {
        for (int i = 0; i < NDECADES; i++) {
            if (i != NDECADES - 1) {
                createLine(
                        START_DISTANCE_LEFT + i * distance,
                        getDecadeY(entry, START_DECADE + (i * TEN_YEARS)),
                        START_DISTANCE_LEFT + (i + 1) * distance,
                        getDecadeY(entry, (START_DECADE + TEN_YEARS) + (i * TEN_YEARS)),
                        color
                );
            }

            String rank = String.valueOf(entry.getRank(START_DECADE + i * TEN_YEARS));
            String textLabel = getTextLabel(entry, rank);
            createLabel(
                    textLabel,
                    START_DISTANCE_LEFT + i * distance,
                    getDecadeY(entry, START_DECADE + (i * TEN_YEARS)),
                    "Times-10", color
            );
        }
    }



    /**
     * This method returns the value in coordinates for the given decade
     *
     * @param surferEntry The surfer entry
     * @param decade The year of the decade
     * @return The y coordinate
     */
    private double getDecadeY(NameSurferEntry surferEntry, int decade) {
        int rank = surferEntry.getRank(decade);
        if (rank == 0) {
            return getHeight() - GRAPH_MARGIN_SIZE;
        }
        return (((double) (getHeight() - GRAPH_MARGIN_SIZE * 2) * rank) / MAX_RANK) + GRAPH_MARGIN_SIZE;
    }

    /**
     * This method returns the color depending on the graph index
     *
     * @param i The index of graph
     * @return The color of graph
     */
    private Color getColorGraph(int i) {
        return switch (i % COUNT_COLOR) {
            case 0 -> Color.BLUE;
            case 1 -> Color.RED;
            case 2 -> Color.MAGENTA;
            default -> Color.BLACK;
        };
    }

    /**
     * Returns the text that will be displayed next to the graph value
     * depending on the surfer entry and rank
     *
     * @param entry The surfer entry
     * @param rank The rank of the name in a certain decade
     * @return Returns the text (name and rank of name)
     */
    private String getTextLabel(NameSurferEntry entry, String rank) {
        if (rank.equals("0")) {
            rank = "*";
        }
        return entry.getName() + " " + rank;
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
