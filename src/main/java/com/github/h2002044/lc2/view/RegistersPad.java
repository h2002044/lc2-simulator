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

public class RegistersPad extends JInternalFrame implements ActionListener {
    private String strRegisters[] = {"R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7"};

    private JButton jbRegisters[];

    public RegistersPad(float fWidthFactor, float fHeightFactor, Font fnt) {
        createButtons(fWidthFactor, fHeightFactor, fnt);
        addButtons();
    }

    private void addButtons() {
        for (int iLoop = 0; iLoop < strRegisters.length; iLoop++) {
            getContentPane().add(jbRegisters[iLoop]);
        }
    }

    private void createButtons(float fWidthFactor, float fHeightFactor, Font fnt) {
        jbRegisters = new JButton[strRegisters.length];

        int iColPos = (int) (16 * fWidthFactor);
        int iRowPos = (int) (16 * fHeightFactor);

        for (int iLoop = 0; iLoop < strRegisters.length; iLoop++) {
            jbRegisters[iLoop] = new JButton(strRegisters[iLoop]);
            jbRegisters[iLoop].setSize((int) (47 * fWidthFactor), (int) (24 * fHeightFactor));
            jbRegisters[iLoop].setActionCommand(strRegisters[iLoop]);
            jbRegisters[iLoop].setLocation((int) (iColPos), (int) (iRowPos));
            jbRegisters[iLoop].addActionListener(this);
            jbRegisters[iLoop].setFont(fnt);
            jbRegisters[iLoop].setToolTipText("REGISTER - " + strRegisters[iLoop]);

            if ((iLoop + 1) % 2 == 0) {
                iRowPos = iRowPos + (int) (37 * fHeightFactor);
                iColPos = (int) (16 * fWidthFactor);
            } else {
                iColPos = iColPos + (int) (63 * fWidthFactor);
            }
        }
    }


    /**
     * fEnableRegisters is used to <b>enable</b> the buttons in the Register Pad<br>
     */
    public void fEnableRegisters() {
        for (int iLoop = 0; iLoop < strRegisters.length; iLoop++) {
            jbRegisters[iLoop].setEnabled(true);
        }

        this.validate();
    }

    /**
     * fDisableRegisters is used to <b>disable</b> the buttons in the Registers Pad<br>
     */
    public void fDisableRegisters() {
        for (int iLoop = 0; iLoop < strRegisters.length; iLoop++) {
            jbRegisters[iLoop].setEnabled(false);
        }

        this.validate();
    }

    public void actionPerformed(ActionEvent ae) {

    }
}
