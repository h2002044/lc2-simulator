package com.github.h2002044.lc2.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ toDo
 * <p>
 * Action Listener implementation
 */

public class NumberPad extends JInternalFrame implements ActionListener {
    private String strNumbers[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
    private JButton jbNumbers[];


    public NumberPad(float fWidthFactor, float fHeightFactor, Font fnt) {
        createButtons(fHeightFactor, fWidthFactor, fnt);
        addButtons();
    }

    private void createButtons(float fHeightFactor, float fWidthFactor, Font fnt) {
        jbNumbers = new JButton[strNumbers.length];

        int iRowPos = (int) (16 * fHeightFactor);
        int iColPos = (int) (16 * fWidthFactor);

        for (int iLoop = 0; iLoop < strNumbers.length; iLoop++) {
            jbNumbers[iLoop] = new JButton(strNumbers[iLoop]);
            jbNumbers[iLoop].setSize((int) (55 * fWidthFactor), (int) (24 * fHeightFactor));
            jbNumbers[iLoop].setActionCommand(strNumbers[iLoop]);
            jbNumbers[iLoop].setLocation((int) (iColPos), (int) (iRowPos));
            jbNumbers[iLoop].addActionListener(this);

            jbNumbers[iLoop].setFont(fnt);

            if ((iLoop + 1) % 3 == 0) {
                iRowPos = iRowPos + (int) (27 * fHeightFactor);
                iColPos = (int) (16 * fWidthFactor);
            } else {
                iColPos = iColPos + (int) (63 * fWidthFactor);
            }
        }
    }

    private void addButtons() {
        for (int iLoop = 0; iLoop < strNumbers.length; iLoop++) {
            getContentPane().add(jbNumbers[iLoop]);
        }
    }

    public void fEnableNumbers_0_1() {
        jbNumbers[0].setEnabled(true);
        jbNumbers[1].setEnabled(true);
    }


    public void fEnableNumbers_0_1_2() {
        jbNumbers[0].setEnabled(true);
        jbNumbers[1].setEnabled(true);
        jbNumbers[2].setEnabled(true);
        jbNumbers[3].setEnabled(true);
    }


    /**
     * fDisableNumberPad is used to <b>disable</b> the buttons in the Number Pad<br>
     */
    public void fDisableNumberPad() {
        for (int iLoop = 0; iLoop < strNumbers.length; iLoop++) {
            jbNumbers[iLoop].setEnabled(false);
        }
        this.validate();
    }

    /**
     * fEnableNumberPad is used to <b>enable</b> the buttons in the Number Pad<br>
     */
    public void fEnableNumberPad() {
        for (int iLoop = 0; iLoop < strNumbers.length; iLoop++) {
            jbNumbers[iLoop].setEnabled(true);
        }

        this.validate();
    }


    public void actionPerformed(ActionEvent ae) {

    }
}
