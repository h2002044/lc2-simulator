package com.github.h2002044.lc2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ toDo
 * <p>
 * Action Listener implementation
 */
public class MnemonicsPad extends JInternalFrame implements ActionListener {
    private String strButtonNames[] =
            {
                    "ADD", "AND", "BR", "JSR", "JSRR", "LD", "LDI", "LDR", "LEA", "NOT", "RET", "RTI", "ST", "STI", "STR",
                    "TRAP", "CLR"
            };

    private String strMnemonicsToolTip[] =
            {
                    "ADD-DestReg-SrcReg1-SrcReg2/9-BitOffset", "AND-DestReg-SrcReg1-SrcReg2/9-BitOffset", "Break-NZP-9-BitOffset",
                    "Jump Subroutine-R7 Register Status-9-BitOffset", "Jump Subroutine-R7 Register Status-Base Register-6-BitOffset",
                    "Load-DestReg-9-BitOffset", "Load Indirect-DestReg-9-BitOffset", "Load-DestReg-BaseReg-6-BitOffset", "Load Effective Address-DestReg-9-BitOffset",
                    "NOT-DestReg-SourceReg", "Return", "RTI", "Store-DestReg-9-BitOffset", "Store Indirect-SourceReg-9-BitOffset Indirect",
                    "Store-SourceReg-BaseReg-6-BitOffset", "TRAP", "Clear the selected Field"
            };
    private JButton jbButtons[];

    public MnemonicsPad(float fWidthFactor, float fHeightFactor, Font fnt) {
        createButtons(fWidthFactor, fHeightFactor, fnt);
        addButtons();
    }

    private void addButtons() {
        for (int iLoop = 0; iLoop < strButtonNames.length; iLoop++) {
            getContentPane().add(jbButtons[iLoop]);
        }
    }

    private void createButtons(float fWidthFactor, float fHeightFactor, Font fnt) {
        jbButtons = new JButton[strButtonNames.length];

        int iColPos = (int) (16 * fWidthFactor);
        int iRowPos = (int) (16 * fHeightFactor);

        for (int iLoop = 0; iLoop < strButtonNames.length; iLoop++) {
            jbButtons[iLoop] = new JButton(strButtonNames[iLoop]);
            jbButtons[iLoop].setSize((int) (55 * fWidthFactor), (int) (24 * fHeightFactor));
            jbButtons[iLoop].setActionCommand(strButtonNames[iLoop]);
            jbButtons[iLoop].setLocation((int) (iColPos), (int) (iRowPos));
            jbButtons[iLoop].addActionListener(this);
            jbButtons[iLoop].setToolTipText(strMnemonicsToolTip[iLoop]);

            if (strButtonNames[iLoop].equalsIgnoreCase("JSRR") || strButtonNames[iLoop].equalsIgnoreCase("TRAP") || strButtonNames[iLoop].equalsIgnoreCase("NOT")) {
                jbButtons[iLoop].setFont(new Font("ARIAL", Font.PLAIN, (int) (8 * fHeightFactor)));
            } else {
                jbButtons[iLoop].setFont(fnt);
            }
            if ((iLoop + 1) % 3 == 0) {
                iRowPos = iRowPos + (int) (27 * fHeightFactor);
                iColPos = (int) (16 * fWidthFactor);
            } else {
                iColPos = iColPos + (int) (63 * fWidthFactor);
            }
        }
    }

    /**
     * fEnableMnemonics is used to <b>enable</b> the buttons in the Mnemonics Pad<br>
     */
    public void fEnableMnemonics() {
        for (int iLoop = 0; iLoop < strButtonNames.length; iLoop++) {
            jbButtons[iLoop].setEnabled(true);
        }
        this.validate();
    }

    public void fDisableMnemonics() {
        for (int iLoop = 0; iLoop < strButtonNames.length; iLoop++) {
            jbButtons[iLoop].setEnabled(false);
        }
        this.validate();
    }

    public void actionPerformed(ActionEvent ae) {

    }

}
