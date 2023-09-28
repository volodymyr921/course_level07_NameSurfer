package com.shpp.p2p.cs.vomelianchuk.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implement the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {
    // saves the database
    private final NameSurferDataBase dataBase = new NameSurferDataBase(NAMES_DATA_FILE);

    private JTextField textName;
    private JButton graphButton;
    private JButton clearButton;

    private NameSurferGraph graph;

    /**
     * This method has the responsibility for reading in the database
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        JLabel labelName = new JLabel("Name:");
        add(labelName, NORTH);

        textName = new JTextField(40);
        textName.addActionListener(this);
        add(textName, NORTH);

        graphButton = createButton("Graph");
        clearButton = createButton("Clear");

        graph = new NameSurferGraph();
        add(graph);
    }

    /**
     * This method creates a button and adds it to the screen
     *
     * @param textButton The name of the button
     */
    private JButton createButton(String textButton) {
        JButton button = new JButton(textButton);
        button.addActionListener(this);
        add(button, NORTH);
        return button;
    }

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == graphButton || e.getSource() == textName) {
            graph.addEntry(dataBase.findEntry(textName.getText()));
        } else if (e.getSource() == clearButton) {
            graph.clear();
        }
    }
}
