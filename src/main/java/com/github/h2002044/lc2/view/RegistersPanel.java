package com.github.h2002044.lc2.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

/**
 * clsRegistersPanel class is used to display All registers, Flags, PC in run time and also option to modify the values of Registers, PC after every instruction<br>
 */
public class RegistersPanel extends JPanel implements ActionListener {

    //JPanel jpRegisters = new JPanel();

    private String sRegisters[] = {"R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7", "PC", "Zero", "+ve", "-ve"};
    private JButton jbRegisters[] = new JButton[getRegistersString().length];
    private JLabel jlblRegisters[] = new JLabel[getRegistersString().length];


    private Border brBlackline = BorderFactory.createLineBorder(Color.black);
    private Border brTitledBorder = BorderFactory.createTitledBorder(getBlackline(), "   R E G I S T E R S    ");

    public RegistersPanel(float fWidthFactor, float fHeightFactor, Font fntButtons) {

        setLayout(null);
        setSize((int) (500 * fWidthFactor), (int) (125 * fWidthFactor));

        setBorder(getTitledBorder());
        setToolTipText("R E G I S T E R S   P A N E L");
        int iRowPosition = 20;
        int iColPosition = 10;
        int iRowIncrementFactor = 35;
        int iColIncrementFactor = 30;

        String sRegisterToolTipText[] = {"R-0 Register", "R-1 Register", "R-2 Register", "R-3 Register", "R-4 Register", "R-5 Register", "R-6 Register", "R-7 Register", "Program Counter", "Zero Flag", "Positive Flag", "Negative Flag"};
        for (int iLoop = 0; iLoop < getRegistersString().length; iLoop++) {

            getRegistersLabels()[iLoop] = new JLabel(getRegistersString()[iLoop]);
            getRegistersLabels()[iLoop].setSize((int) (30 * fWidthFactor), (int) (25 * fHeightFactor));
            getRegistersLabels()[iLoop].setLocation((int) (iColPosition * fWidthFactor), (int) (iRowPosition * fHeightFactor));

            add(getRegistersLabels()[iLoop]);

            iColPosition = (int) ((iColPosition + iColIncrementFactor));

            getRegistersButtons()[iLoop] = new JButton("0");
            getRegistersButtons()[iLoop].setToolTipText(sRegisterToolTipText[iLoop]);

            getRegistersButtons()[iLoop].setSize((int) (60 * fWidthFactor), (int) (25 * fHeightFactor));
            getRegistersButtons()[iLoop].setLocation((int) (iColPosition * fWidthFactor), (int) (iRowPosition * fHeightFactor));

            getRegistersButtons()[iLoop].addActionListener(this);

            getRegistersButtons()[iLoop].setActionCommand(getRegistersLabels()[iLoop].getText().toUpperCase());
            getRegistersButtons()[iLoop].setFont(fntButtons);


            add(getRegistersButtons()[iLoop]);

            iColPosition = (int) ((iColPosition + iColIncrementFactor + 50));

            if ((iLoop + 1) % 4 == 0) {
                iRowPosition = (int) ((iRowPosition + iRowIncrementFactor));
                iColPosition = 10;
            }
        }
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand().toUpperCase();

        if (command.equals("RO") || command.equals("R1") || command.equals("R2") || command.equals("R3")
                || command.equals("R4") || command.equals("R5") || command.equals("R6") || command.equals("R7")
                || command.equals("PC")) {
            fValidateHexValue(ae);
        }

    }

    /**
     * Return value of R0 Register <br>
     *
     * @ return R0 Value <br>
     */
    public BigInteger fGetR0() {
        return new BigInteger(getRegistersButtons()[0].getText(), 16);
    }

    /**
     * Return value of R1 Register <br>
     *
     * @ return R1 Value <br>
     */
    public BigInteger fGetR1() {
        return new BigInteger(getRegistersButtons()[1].getText(), 16);
    }

    /**
     * Return value of R2 Register <br>
     *
     * @ return R2 Value <br>
     */
    public BigInteger fGetR2() {
        return new BigInteger(getRegistersButtons()[2].getText(), 16);
    }

    /**
     * Return value of R3 Register <br>
     *
     * @ return R3 Value <br>
     */
    public BigInteger fGetR3() {
        return new BigInteger(getRegistersButtons()[3].getText(), 16);
    }

    /**
     * Return value of R4 Register <br>
     *
     * @ return R4 Value <br>
     */
    public BigInteger fGetR4() {
        return new BigInteger(getRegistersButtons()[4].getText(), 16);
    }

    /**
     * Return value of R5 Register <br>
     *
     * @ return R5 Value <br>
     */
    public BigInteger fGetR5() {
        return new BigInteger(getRegistersButtons()[5].getText(), 16);
    }

    /**
     * Return value of R6 Register <br>
     *
     * @ return R6 Value <br>
     */
    public BigInteger fGetR6() {
        return new BigInteger(getRegistersButtons()[6].getText(), 16);
    }

    /**
     * Return value of R7 Register <br>
     *
     * @ return R7 Value <br>
     */
    public BigInteger fGetR7() {
        return new BigInteger(getRegistersButtons()[7].getText(), 16);
    }

    /**
     * Return value of Program Counter <br>
     *
     * @ return PC Value <br>
     */
    public BigInteger fGetPC() {
        return new BigInteger(getRegistersButtons()[8].getText(), 16);
    }


    /**
     * fDisableRegisters to disable the Registers during execution of Instruction<br>
     */
    public void fDisableRegisters() {
        for (int iLoop = 0; iLoop < getRegistersButtons().length; iLoop++) {
            getRegistersButtons()[iLoop].setEnabled(false);
        }
    }

    /**
     * fEnableRegisters to Enable the Registers after execution of Instruction<br>
     */
    public void fEnableRegisters() {
        for (int iLoop = 0; iLoop < getRegistersButtons().length; iLoop++) {
            getRegistersButtons()[iLoop].setEnabled(true);
        }
    }


    /**
     * fResetRegisters to Reset the value of registers to 0 before the execution of every program<br>
     */
    public void fResetRegisters() {
        for (int iLoop = 0; iLoop < getRegistersButtons().length; iLoop++) {
            getRegistersButtons()[iLoop].setText("0");
        }
    }

    /**
     * fValidteHexValue is used to check whether the input given by user is Hex Value or not<br>
     */
    private void fValidateHexValue(ActionEvent ae) {
        //String sPreviousValue = "";
        String sPreviousValue = ((JButton) (ae.getSource())).getText().trim().toUpperCase();
        //String sNewValue = "";
        String sNewValue = JOptionPane.showInputDialog(null, "Enter Value for Register ( 4 digit Hex )", "Value", JOptionPane.PLAIN_MESSAGE);

        if (sNewValue == null)
            return;

        if (sNewValue.length() > 0) {
            sNewValue = sNewValue.toUpperCase();
        }

        if (sNewValue.length() > 4) {
            JOptionPane.showMessageDialog(null, "Enter 4 Digit Hex Value !! ", "I N V A L I D  D A T A", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            if (sNewValue.length() <= 0) {
                ((JButton) (ae.getSource())).setText(sPreviousValue);
            } else {
                for (int iLoop = 0; iLoop < sNewValue.length(); iLoop++) {
                    if (sNewValue.charAt(iLoop) == 'A' || sNewValue.charAt(iLoop) == 'B' || sNewValue.charAt(iLoop) == 'C' || sNewValue.charAt(iLoop) == 'D' || sNewValue.charAt(iLoop) == 'E' || sNewValue.charAt(iLoop) == 'F' || sNewValue.charAt(iLoop) == '1' || sNewValue.charAt(iLoop) == '2' || sNewValue.charAt(iLoop) == '3' || sNewValue.charAt(iLoop) == '4' || sNewValue.charAt(iLoop) == '5' || sNewValue.charAt(iLoop) == '6' || sNewValue.charAt(iLoop) == '7' || sNewValue.charAt(iLoop) == '8' || sNewValue.charAt(iLoop) == '9' || sNewValue.charAt(iLoop) == '0') {

                    } else {
                        JOptionPane.showMessageDialog(null, "Enter 4 Digit Hex Value !! ", "I N V A L I D  D A T A", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                }
                ((JButton) (ae.getSource())).setText(sNewValue);
            }
        }
    }

    public void fSetRegisterValue(int iRegister, BigInteger biValue) {
        if (iRegister >= 0 && iRegister <= 11)
            getRegistersButtons()[iRegister].setText(biValue.toString(16).toUpperCase());
    }

    /**
     * fResetRegisterValue() is used to reset the value of registers<br>
     */
    public void fResetRegisterValue() {
        for (int iLoop = 0; iLoop < getRegistersButtons().length; iLoop++) {
            getRegistersButtons()[iLoop].setText("0");
        }

    }

    /**
     * fGetRegisterValue is used to get the value of register.<br>
     *
     * @ param Register Number<br>
     * @ return New value for the register<br>
     */
    public BigInteger fGetRegisterValue(int iRegister) {
        return new BigInteger(new String(getRegistersButtons()[iRegister].getText().trim()), 16);
    }

    /**
     * fGetRegistersString() is used to get Register values<br>
     *
     * @ return String array value<br>
     */
    public String[] getRegistersString() {
        return sRegisters;
    }

    /**
     * fSetRegistersString() is used to get Register values<br>
     *
     * @ param String array value<br>
     */
    public void setRegistersString(String[] sRegisters) {
        this.sRegisters = sRegisters;
    }

    /**
     * fGetRegistersButtons() is used to get Register Button References<br>
     *
     * @ return Button array value<br>
     */
    public JButton[] getRegistersButtons() {
        return jbRegisters;
    }

    public void setRegistersButtons(JButton[] jbRegisters) {
        this.jbRegisters = jbRegisters;
    }

    public JLabel[] getRegistersLabels() {
        return jlblRegisters;
    }

    public void setRegistersLabels(JLabel[] jlblRegisters) {
        this.jlblRegisters = jlblRegisters;
    }

    public Border getBlackline() {
        return getBlacklineBorder();
    }

    public void setBrBlackline(Border brBlackline) {
        this.setBlacklineBorder(brBlackline);
    }

    public Border getTitledBorder() {
        return brTitledBorder;
    }

    public void setBrTitledBorder(Border brTitledBorder) {
        this.setTitledBorder(brTitledBorder);
    }

    public Border getBlacklineBorder() {
        return brBlackline;
    }

    public void setBlacklineBorder(Border brBlackline) {
        this.brBlackline = brBlackline;
    }

    public void setTitledBorder(Border brTitledBorder) {
        this.brTitledBorder = brTitledBorder;
    }
}
