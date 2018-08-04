package com.github.h2002044.lc2;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Random;

/**
 * @ toDo
 * Make changes according to refactored classes.
 * <p>
 * Editor is used to create and layout GUI Components.<br>
 */
public class Editor extends JPanel implements ActionListener {

    private String sPreviousSegment = "SEGMENT";

    private JInternalFrame jifMnemonics;
    private JInternalFrame jifNumberPad;
    private JInternalFrame jifProgram;
    private JInternalFrame jifRegisters;
    private JInternalFrame jifMemory;
    float fWidthFactor;
    float fHeightFactor;
    private JLabel jlblZero;
    private JLabel jlblPositive;
    private JLabel jlblNegative;
    private JLabel jlblR7State;
    private JLabel jlblStartingLocation;
    private JLabel jlblInstructionBits[] = new JLabel[16];
    Font fnt;
    Font fntBlueBoldFont;
    private JTabbedPane jtpMain;
    SimulatorPanel objExecute;
    private JTextField jtbStartingLocation;
    private JButton jbRun;
    private JButton jbStep;

    private JCheckBox jcbZero;
    private JCheckBox jcbPositive;
    private JCheckBox jcbNegative;
    private JCheckBox jcbR7State;

    Storage htMemory;
    Hashtable htMnemonics;

    private int iTableRowCount = 511;
    private int iTableRowPosition = 0;

    private Execute objExe;
    private ToolBar toolBar;

    private JLabel jlblInstructionHelp;

    private String strButtonNames[] =
            {
                    "ADD", "AND", "BR", "JSR", "JSRR", "LD", "LDI", "LDR", "LEA", "NOT", "RET", "RTI", "ST", "STI", "STR", "TRAP", "CLR"
            };

    private String strMnemonicsToolTip[] = {"ADD-DestReg-SrcReg1-SrcReg2/9-BitOffset", "AND-DestReg-SrcReg1-SrcReg2/9-BitOffset", "Break-NZP-9-BitOffset", "Jump Subroutine-R7 Register Status-9-BitOffset", "Jump Subroutine-R7 Register Status-Base Register-6-BitOffset", "Load-DestReg-9-BitOffset", "Load Indirect-DestReg-9-BitOffset", "Load-DestReg-BaseReg-6-BitOffset",
            "Load Effective Address-DestReg-9-BitOffset", "NOT-DestReg-SourceReg", "Return", "RTI", "Store-DestReg-9-BitOffset", "Store Indirect-SourceReg-9-BitOffset Indirect", "Store-SourceReg-BaseReg-6-BitOffset", "TRAP", "Clear the selected Field"};

    private String strRegisters[] = {"R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7"};

    private String strNumbers[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    private JButton jbButtons[];
    private JButton jbRegisters[];
    private JButton jbNumbers[];

    private JButton jbSave;
    private JButton jbExecute;

    private JButton jbAllocateMemory;
    private JTextField jtfMemorySize;
    private JLabel jlblMemorySize;

    private JScrollPane jspProgram;

    private JTable jtProgram;

    private String strColumnNames[] = {"Address", "Mnemonic", "Opcode/Data"};
    private DefaultTableModel dtmModel = new DefaultTableModel(strColumnNames, 0);

    private int iRowCount = 0;
    private int iMemoryAddress = 0;
    private int iRowPosition = 0;
    private String sInstruction = null;


    /**
     * fDisableMnemonics is used to <b>disable</b> the buttons in the Mnemonics Pad<br>
     */
    public void fDisableMnemonics() {
        for (int iLoop = 0; iLoop < strButtonNames.length; iLoop++) {
            jbButtons[iLoop].setEnabled(false);
        }
        for (int iLoop = 0; iLoop < strNumbers.length; iLoop++) {
            jbNumbers[iLoop].setEnabled(false);
        }
        for (int iLoop = 0; iLoop < strRegisters.length; iLoop++) {
            jbRegisters[iLoop].setEnabled(true);
        }
        this.validate();
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

    /**
     * fEnableRunStatus is used to <b>enable</b> the components in the Run Status Frame<br>
     */
    public void fEnableRunStatus() {
        jlblStartingLocation.setVisible(true);
        jtbStartingLocation.setVisible(true);

        jbRun.setVisible(true);
        jbStep.setVisible(true);

        jifMemory.setTitle("E X E C U T E");
        this.validate();
    }

    /**
     * fDisableRunStatus is used to <b>disable</b> the components in the Run Status Frame<br>
     */
    public void fDisableRunStatus() {
        jlblStartingLocation.setVisible(false);
        jtbStartingLocation.setVisible(false);
        jbRun.setVisible(false);
        jbStep.setVisible(false);

        jifMemory.setTitle("");
        this.validate();

    }

    public void fEnableR7State() {
        jcbR7State.setVisible(true);
        jlblR7State.setVisible(true);

        jifMemory.setTitle("R 7 -- S T A T U S");

        this.validate();
    }

    public void fDisableR7State() {
        jcbR7State.setVisible(false);
        jlblR7State.setVisible(false);

        jifMemory.setTitle("");
        this.validate();
    }

    public void fResetR7State() {
        jcbR7State.setSelected(false);
    }

    public void fDisableEditor() {
        fDisableMnemonics();
        fDisableNumberPad();
        fDisableRegisters();
        fDisableSegments();
        fDisableRunStatus();
    }

    public void fEnableEditor() {
        fEnableMnemonics();
        fEnableNumberPad();
        fEnableRegisters();
        fEnableSegments();
        fEnableRunStatus();
    }

    public void fDisableConditions() {
        jcbNegative.setEnabled(false);
        jcbPositive.setEnabled(false);
        jcbZero.setEnabled(false);

        jcbNegative.setVisible(false);
        jcbPositive.setVisible(false);
        jcbZero.setVisible(false);

        jlblNegative.setVisible(false);
        jlblPositive.setVisible(false);
        jlblZero.setVisible(false);

        jifMemory.setTitle("");

        this.validate();
    }

    public void fEnableConditions() {
        jcbNegative.setEnabled(true);
        jcbPositive.setEnabled(true);
        jcbZero.setEnabled(true);

        jcbNegative.setVisible(true);
        jcbPositive.setVisible(true);
        jcbZero.setVisible(true);

        jlblNegative.setVisible(true);
        jlblPositive.setVisible(true);
        jlblZero.setVisible(true);

        jifMemory.setTitle("C O N D I T I O N S");

        this.validate();
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

    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }

    public void actionPerformed(ActionEvent e) {
        if (((String) jcbSegment.getSelectedItem()).equalsIgnoreCase("SEGMENT") == true) {
            JOptionPane.showMessageDialog(null, "Select a SEGMENT and start coding ", "Select a SEGMENT", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (iRowPosition == 511 && ((String) jcbSegment.getSelectedItem()).equalsIgnoreCase("0xFE")) {
            JOptionPane.showMessageDialog(null, "OUT OF MEMORY ..  CHANGE SEGMENT !!", "OUT OF MEMORY", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (iRowPosition == 512) {
            String strLocation = (String) jcbSegment.getSelectedItem();
            strLocation = strLocation.substring(2);
            BigInteger biAddressLocation = new BigInteger(strLocation, 16);
            biAddressLocation = biAddressLocation.add(BigInteger.ONE);
            fUpdateHashTable("0x" + strLocation);
            fLoadTable(biAddressLocation);
            jcbSegment.setSelectedIndex(jcbSegment.getSelectedIndex() + 1);
            iRowPosition = 0;
        }

        if (e.getActionCommand().equalsIgnoreCase("RUN") == true || e.getActionCommand().equalsIgnoreCase("STEP") == true) {
            if (jcbSegment.isEnabled() == true) {
                String sLocation = (jtbStartingLocation.getText()).trim();
                if (sLocation.length() == 4) {
                    int iLoop = 0;
                    for (iLoop = 0; iLoop < sLocation.length(); iLoop++) {
                        if ((sLocation.charAt(iLoop) >= '0' && sLocation.charAt(iLoop) <= '9') || (sLocation.charAt(iLoop) >= 'A' && sLocation.charAt(iLoop) <= 'F') || (sLocation.charAt(iLoop) >= 'a' && sLocation.charAt(iLoop) <= 'f')) {

                        } else {
                            jtbStartingLocation.setText("");
                            break;
                        }
                    }
                    if (iLoop == 4) {
                        fUpdateHashTable((String) jcbSegment.getSelectedItem());
                        Input.bRunning = true;
                        objExe = new Execute();
                        toolBar.setObjExe(objExe);
                        System.out.println("Location : " + sLocation);
                        //System.out.println("Processor : " + Processor);
                        Processor.iStartingLocation = new BigInteger(sLocation, 16).intValue();
                        Processor.objExecute = objExecute;
                        System.out.println("Processor.objExecute : " + Processor.objExecute);

                        if (e.getActionCommand().equalsIgnoreCase("STEP") == true) {
                            objExe.fSetMode("STEP");
                            toolBar.fEnableRun();
                            toolBar.fEnableStep();
                            toolBar.fEnableStop();
                        } else if (e.getActionCommand().equalsIgnoreCase("RUN") == true) {
                            objExe.fSetMode("RUN");
                            toolBar.fEnableRun();
                            toolBar.fEnableStep();
                            toolBar.fEnableStop();
                        }

                        objExe.start();
                        jtbStartingLocation.setText("");

                        fResetInstructions();
                        Input.setRun(true);
                        jtpMain.setSelectedIndex(1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Enter 4 Digit Hexadecimal Value - (eg: ffff)", "4-Digit Hex Value", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Enter 4 Digit Hexadecimal Value - (eg: ffff)", "4-Digit Hex Value", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("CLR") == true) {
            iRowCount = jtProgram.getSelectedRowCount();
            int iRowPositions[] = jtProgram.getSelectedRows();

            for (int iLoop = 0; iLoop < iRowCount; iLoop++) {
                dtmModel.setValueAt("", iRowPositions[iLoop], 1);
                dtmModel.setValueAt("0xFFFF", iRowPositions[iLoop], 2);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("POSITIVE") == true) {
            if (jcbPositive.isSelected() == true) {
                fUpdateBit(9, true);
            } else {
                fUpdateBit(9, false);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("NEGATIVE") == true) {
            if (jcbNegative.isSelected() == true) {
                fUpdateBit(11, true);
            } else {
                fUpdateBit(11, false);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("R7 STATE") == true) {
            if (jcbR7State.isSelected() == true) {
                fUpdateBit(11, true);
            } else {
                fUpdateBit(11, false);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("JSR") == true) {
            fResetInstructions();
            fUpdateBit(15, false);
            fUpdateBit(14, true);
            fUpdateBit(13, false);
            fUpdateBit(12, false);

            fSetInstructionHelp("  Select R7 State / 9 bit Offset");
            fEnableR7State();

            fDisableMnemonics();
            sInstruction = "JSR";
            fDisableSegments();
            fChangeTableSelection();
            dtmModel.setValueAt("JSR", iRowPosition, 1);
            fDisableRegisters();
            fEnableNumbers_0_1();
        } else if (e.getActionCommand().equalsIgnoreCase("JSRR") == true) {
            fResetInstructions();
            fUpdateBit(15, true);
            fUpdateBit(14, true);
            fUpdateBit(13, false);
            fUpdateBit(12, false);

            fSetInstructionHelp("  Select R7 State / Base Register");
            fEnableR7State();
            fEnableRegisters();
            fDisableMnemonics();
            sInstruction = "JSRR";
            fDisableSegments();
            fChangeTableSelection();
            dtmModel.setValueAt("JSRR", iRowPosition, 1);
        } else if (e.getActionCommand().equalsIgnoreCase("RET") == true) {
            fResetInstructions();
            fUpdateBit(15, true);
            fUpdateBit(14, true);
            fUpdateBit(13, false);
            fUpdateBit(12, true);

            if (iRowPosition != 512) {
                fChangeTableSelection();
                dtmModel.setValueAt("RET", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                iRowPosition = iRowPosition + 1;
                jlblInstructionHelp.setText(" Enter Next Instruction");
            }
        } else if (e.getActionCommand().equalsIgnoreCase("RTI") == true) {
            fResetInstructions();
            fUpdateBit(15, true);
            fUpdateBit(14, false);
            fUpdateBit(13, false);
            fUpdateBit(12, false);

            if (iRowPosition != 512) {
                fChangeTableSelection();
                dtmModel.setValueAt("RTI", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                iRowPosition = iRowPosition + 1;
            } else {
                fChangeTableSelection();
                dtmModel.setValueAt("RTI", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                jlblInstructionHelp.setText("Start Coding from Next Segment");
                fDisableMnemonics();
            }
        } else if (e.getActionCommand().equalsIgnoreCase("ZERO") == true) {
            if (jcbZero.isSelected() == true) {
                fUpdateBit(10, true);
            } else {
                fUpdateBit(10, false);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("ADD") == true) {

            fResetInstructions();
            fDisableMnemonics();
            fEnableRegisters(); //To get Source Register
            sInstruction = "ADD-DES";
            fSetInstructionHelp("  Select Destination Register");
            fChangeTableSelection();
            dtmModel.setValueAt("ADD", iRowPosition, 1);
            fDisableSegments();

            fUpdateBit(12, true);
        } else if (e.getActionCommand().equalsIgnoreCase("BR") == true) {
            fResetInstructions();
            fDisableMnemonics();
            fEnableNumbers_0_1();
            fEnableConditions();
            fDisableRegisters();
            sInstruction = "BR";
            fSetInstructionHelp("  Select Condition Codes / 9 bit Offset");
            fChangeTableSelection();
            dtmModel.setValueAt("BR", iRowPosition, 1);
            fDisableSegments();
        } else if (e.getActionCommand().equalsIgnoreCase("AND") == true) {
            fResetInstructions();
            fDisableMnemonics();
            fEnableRegisters(); //To get Source Register
            sInstruction = "AND-DES";
            fSetInstructionHelp("  Select Destination Register");
            fChangeTableSelection();
            dtmModel.setValueAt("AND", iRowPosition, 1);
            fDisableSegments();

            fUpdateBit(12, true);
            fUpdateBit(14, true);
        } else if (e.getActionCommand().equalsIgnoreCase("LD") == true) {
            fResetInstructions();
            fDisableMnemonics();
            fEnableRegisters();
            sInstruction = "LD";
            fSetInstructionHelp("  Select Destination Register");
            fChangeTableSelection();
            dtmModel.setValueAt("LD", iRowPosition, 1);
            fDisableSegments();

            fUpdateBit(13, true);
        } else if (e.getActionCommand().equalsIgnoreCase("LDI") == true) {
            fResetInstructions();
            fDisableMnemonics();
            fEnableRegisters();
            sInstruction = "LDI";
            fSetInstructionHelp("  Select Destination Register");
            fChangeTableSelection();
            dtmModel.setValueAt("LDI", iRowPosition, 1);
            fDisableSegments();

            fUpdateBit(15, true);
            fUpdateBit(13, true);
        } else if (e.getActionCommand().equalsIgnoreCase("LEA") == true) {
            fResetInstructions();
            fDisableMnemonics();
            fEnableRegisters();
            sInstruction = "LEA";
            fSetInstructionHelp("  Select Destination Register");
            fChangeTableSelection();
            dtmModel.setValueAt("LEA", iRowPosition, 1);
            fDisableSegments();

            fUpdateBit(15, true);
            fUpdateBit(14, true);
            fUpdateBit(13, true);
        } else if (e.getActionCommand().equalsIgnoreCase("LDR") == true) {
            fResetInstructions();
            fDisableMnemonics();
            fEnableRegisters();
            sInstruction = "LDR";
            fSetInstructionHelp("  Select Destination Register");
            fChangeTableSelection();
            dtmModel.setValueAt("LDR", iRowPosition, 1);
            fDisableSegments();

            fUpdateBit(15, false);
            fUpdateBit(14, true);
            fUpdateBit(13, true);
            fUpdateBit(12, false);
        } else if (e.getActionCommand().equalsIgnoreCase("STR") == true) {
            fResetInstructions();
            fDisableMnemonics();
            fEnableRegisters();
            sInstruction = "STR";
            fSetInstructionHelp("  Select Source Register");
            fChangeTableSelection();
            dtmModel.setValueAt("STR", iRowPosition, 1);
            fDisableSegments();

            fUpdateBit(15, false);
            fUpdateBit(14, true);
            fUpdateBit(13, true);
            fUpdateBit(12, true);
        } else if (e.getActionCommand().equalsIgnoreCase("NOT") == true) {
            fResetInstructions();
            fDisableMnemonics();
            fEnableRegisters();
            sInstruction = "NOT";
            fSetInstructionHelp("  Select Destination Register");
            fChangeTableSelection();
            dtmModel.setValueAt("NOT", iRowPosition, 1);
            fDisableSegments();

            fUpdateBit(15, true);
            fUpdateBit(14, false);
            fUpdateBit(13, false);
            fUpdateBit(12, true);
        } else if (e.getActionCommand().equalsIgnoreCase("ST") == true) {
            fResetInstructions();
            fDisableMnemonics();
            fEnableRegisters();
            sInstruction = "ST";
            fSetInstructionHelp("  Select Source Register");
            fChangeTableSelection();
            dtmModel.setValueAt("ST", iRowPosition, 1);
            fDisableSegments();

            fUpdateBit(15, false);
            fUpdateBit(14, false);
            fUpdateBit(13, true);
            fUpdateBit(12, true);
        } else if (e.getActionCommand().equalsIgnoreCase("STI") == true) {
            fResetInstructions();
            fDisableMnemonics();
            fEnableRegisters();
            sInstruction = "STI";
            fSetInstructionHelp("  Select Source Register");
            fChangeTableSelection();
            dtmModel.setValueAt("STI", iRowPosition, 1);
            fDisableSegments();

            fUpdateBit(15, true);
            fUpdateBit(14, false);
            fUpdateBit(13, true);
            fUpdateBit(12, true);
        } else if (e.getActionCommand().equalsIgnoreCase("0") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2") == true) {
                fUpdateBit(5, true);
                fUpdateBit(4, false);

                fSetInstructionHelp("  Enter the Number");
                fDisableRegisters();
                fEnableNumberPad();
                sInstruction = sInstruction + "-FirstBit-LastNibble";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,#0", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("JSRR-OFFSET-2") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC") == true) {
                fEnableNumberPad();
                fDisableRegisters();

                fUpdateBit(5, false);
                fUpdateBit(4, false);

                fSetInstructionHelp("  Enter Next Number");
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,0", iRowPosition, 1);

                sInstruction = sInstruction + "-6";
            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("JSRR-OFFSET-2-6") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5-9") == true) {
                fDisableNumberPad();

                fUpdateBit(3, false);
                fUpdateBit(2, false);
                fUpdateBit(1, false);
                fUpdateBit(0, false);

                if (iRowPosition != 512) {
                    fEnableMnemonics();

                    sInstruction = null;
                    jlblInstructionHelp.setText("  Enter Next Instruction");
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "0", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);

                    iRowPosition = iRowPosition + 1;
                } else {
                    sInstruction = null;

                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "0", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    jlblInstructionHelp.setText("Start Coding from Next Segment");
                }
            } else if (sInstruction.equalsIgnoreCase("LD-DES") == true || sInstruction.equalsIgnoreCase("LDI-DES") == true || sInstruction.equalsIgnoreCase("LEA-DES") == true || sInstruction.equalsIgnoreCase("ST-SRC") == true || sInstruction.equalsIgnoreCase("STI-SRC") == true || sInstruction.equalsIgnoreCase("BR") == true || sInstruction.equalsIgnoreCase("JSR") == true) {
                fUpdateBit(8, false);
                fEnableNumberPad();
                if (sInstruction.equalsIgnoreCase("BR") == true) {
                    sInstruction = sInstruction + "-OFFSET-5";
                    fDisableConditions();
                    if (jcbNegative.isSelected() == true) {
                        dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "n", iRowPosition, 1);
                    }
                    if (jcbZero.isSelected() == true) {
                        dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "z", iRowPosition, 1);
                    }
                    if (jcbPositive.isSelected() == true) {
                        dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "p", iRowPosition, 1);
                    }
                    fResetConditions();
                } else if (sInstruction.equalsIgnoreCase("JSR") == true) {
                    fEnableNumberPad();
                    sInstruction = sInstruction + "-OFFSET-5";

                    fDisableR7State();

                    if (jcbR7State.isSelected() == true) {
                        fUpdateBit(11, true);
                        dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "r7", iRowPosition, 1);
                    } else {
                        fUpdateBit(11, false);
                    }
                    fResetR7State();
                } else {
                    sInstruction = sInstruction + "-OFFSET-5";
                }

                fSetInstructionHelp("  Enter the Number");
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,#0", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5") == true) {
                fUpdateBit(7, false);
                fUpdateBit(6, false);
                fUpdateBit(5, false);
                fUpdateBit(4, false);

                fSetInstructionHelp("  Enter Next Instruction");
                sInstruction = sInstruction + "-9";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "0", iRowPosition, 1);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("1") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2") == true) {
                fUpdateBit(5, true);
                fUpdateBit(4, true);

                fSetInstructionHelp("  Enter the Number");
                fDisableRegisters();
                fEnableNumberPad();
                sInstruction = sInstruction + "-FirstBit-LastNibble";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,#1", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("JSRR-OFFSET-2") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC") == true) {
                fEnableNumberPad();
                fDisableRegisters();

                fUpdateBit(5, false);
                fUpdateBit(4, true);

                fSetInstructionHelp("  Enter Next Number");
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,1", iRowPosition, 1);

                sInstruction = sInstruction + "-6";
            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("JSRR-OFFSET-2-6") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5-9") == true) {
                fDisableNumberPad();

                fUpdateBit(3, false);
                fUpdateBit(2, false);
                fUpdateBit(1, false);
                fUpdateBit(0, true);

                if (iRowPosition != 512) {
                    fEnableMnemonics();

                    sInstruction = null;
                    jlblInstructionHelp.setText("  Enter Next Instruction");
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "1", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    iRowPosition = iRowPosition + 1;
                } else {
                    sInstruction = null;
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "1", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    jlblInstructionHelp.setText("Start Coding from Next Segment");
                }
            } else if (sInstruction.equalsIgnoreCase("LD-DES") == true || sInstruction.equalsIgnoreCase("LDI-DES") == true || sInstruction.equalsIgnoreCase("LEA-DES") == true || sInstruction.equalsIgnoreCase("ST-SRC") == true || sInstruction.equalsIgnoreCase("STI-SRC") == true || sInstruction.equalsIgnoreCase("BR") == true || sInstruction.equalsIgnoreCase("JSR") == true) {
                fUpdateBit(8, true);
                fEnableNumberPad();
                if (sInstruction.equalsIgnoreCase("BR") == true) {
                    sInstruction = sInstruction + "-OFFSET-5";
                    fDisableConditions();
                    if (jcbNegative.isSelected() == true) {
                        dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "n", iRowPosition, 1);
                    }
                    if (jcbZero.isSelected() == true) {
                        dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "z", iRowPosition, 1);
                    }
                    if (jcbPositive.isSelected() == true) {
                        dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "p", iRowPosition, 1);
                    }
                    fResetConditions();
                } else if (sInstruction.equalsIgnoreCase("JSR") == true) {
                    fEnableNumberPad();
                    sInstruction = sInstruction + "-OFFSET-5";

                    fDisableR7State();

                    if (jcbR7State.isSelected() == true) {
                        fUpdateBit(11, true);
                        dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "r7", iRowPosition, 1);
                    } else {
                        fUpdateBit(11, false);
                    }
                    fResetR7State();
                } else {
                    sInstruction = sInstruction + "-OFFSET-5";
                }
                fSetInstructionHelp("  Enter the Number");
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,#1", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5") == true) {
                fUpdateBit(7, false);
                fUpdateBit(6, false);
                fUpdateBit(5, false);
                fUpdateBit(4, true);

                fSetInstructionHelp("  Enter Next Number");
                sInstruction = sInstruction + "-9";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "1", iRowPosition, 1);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("2") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("JSRR-OFFSET-2-6") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5-9") == true) {
                fDisableNumberPad();

                fUpdateBit(3, false);
                fUpdateBit(2, false);
                fUpdateBit(1, true);
                fUpdateBit(0, false);

                if (iRowPosition != 512) {
                    fEnableMnemonics();

                    sInstruction = null;
                    jlblInstructionHelp.setText("  Enter Next Instruction");
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "2", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    iRowPosition = iRowPosition + 1;
                } else {
                    sInstruction = null;
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "2", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    jlblInstructionHelp.setText("Start Coding from Next Segment");
                }
            } else if (sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5") == true) {
                fUpdateBit(7, false);
                fUpdateBit(6, false);
                fUpdateBit(5, true);
                fUpdateBit(4, false);

                fSetInstructionHelp("  Enter Next Number");
                sInstruction = sInstruction + "-9";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "2", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("JSRR-OFFSET-2") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC") == true) {
                fEnableNumberPad();
                fDisableRegisters();

                fUpdateBit(5, true);
                fUpdateBit(4, false);

                fSetInstructionHelp("  Enter Next Number");
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,2", iRowPosition, 1);

                sInstruction = sInstruction + "-6";
            }
        } else if (e.getActionCommand().equalsIgnoreCase("3") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("JSRR-OFFSET-2-6") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5-9") == true) {
                fDisableNumberPad();

                fUpdateBit(3, false);
                fUpdateBit(2, false);
                fUpdateBit(1, true);
                fUpdateBit(0, true);


                if (iRowPosition != 512) {
                    fEnableMnemonics();

                    sInstruction = null;
                    jlblInstructionHelp.setText("  Enter Next Instruction");
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "3", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    iRowPosition = iRowPosition + 1;
                } else {
                    sInstruction = null;
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "3", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    jlblInstructionHelp.setText("Start Coding from Next Segment");
                }

            } else if (sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5") == true) {
                fUpdateBit(7, false);
                fUpdateBit(6, false);
                fUpdateBit(5, true);
                fUpdateBit(4, true);

                fSetInstructionHelp("  Enter Next Number");
                sInstruction = sInstruction + "-9";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "3", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("JSRR-OFFSET-2") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC") == true) {
                fEnableNumberPad();
                fDisableRegisters();

                fUpdateBit(5, true);
                fUpdateBit(4, true);

                fSetInstructionHelp("  Enter Next Number");
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,3", iRowPosition, 1);

                sInstruction = sInstruction + "-6";
            }
        } else if (e.getActionCommand().equalsIgnoreCase("4") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("JSRR-OFFSET-2-6") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5-9") == true) {
                fDisableNumberPad();

                fUpdateBit(3, false);
                fUpdateBit(2, true);
                fUpdateBit(1, false);
                fUpdateBit(0, false);

                if (iRowPosition != 512) {
                    fEnableMnemonics();

                    sInstruction = null;
                    jlblInstructionHelp.setText("  Enter Next Instruction");
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "4", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    iRowPosition = iRowPosition + 1;
                } else {
                    sInstruction = null;
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "4", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    jlblInstructionHelp.setText("Start Coding from Next Segment");
                }
            } else if (sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5") == true) {
                fUpdateBit(7, false);
                fUpdateBit(6, true);
                fUpdateBit(5, false);
                fUpdateBit(4, false);

                fSetInstructionHelp("  Enter Next Number");
                sInstruction = sInstruction + "-9";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "4", iRowPosition, 1);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("5") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("JSRR-OFFSET-2-6") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5-9") == true) {
                fDisableNumberPad();

                fUpdateBit(3, false);
                fUpdateBit(2, true);
                fUpdateBit(1, false);
                fUpdateBit(0, true);

                if (iRowPosition != 512) {
                    fEnableMnemonics();

                    sInstruction = null;
                    jlblInstructionHelp.setText("  Enter Next Instruction");
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "5", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    iRowPosition = iRowPosition + 1;
                } else {
                    sInstruction = null;
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "5", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    jlblInstructionHelp.setText("Start Coding from Next Segment");
                }
            } else if (sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5") == true) {
                fUpdateBit(7, false);
                fUpdateBit(6, true);
                fUpdateBit(5, false);
                fUpdateBit(4, true);

                fSetInstructionHelp("  Enter Next Number");
                sInstruction = sInstruction + "-9";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "5", iRowPosition, 1);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("6") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("JSRR-OFFSET-2-6") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5-9") == true) {
                fDisableNumberPad();

                fUpdateBit(3, false);
                fUpdateBit(2, true);
                fUpdateBit(1, true);
                fUpdateBit(0, false);

                if (iRowPosition != 512) {
                    fEnableMnemonics();

                    sInstruction = null;
                    jlblInstructionHelp.setText("  Enter Next Instruction");
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "6", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    iRowPosition = iRowPosition + 1;
                } else {
                    sInstruction = null;
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "6", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    jlblInstructionHelp.setText("Start Coding from Next Segment");
                }
            } else if (sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5") == true) {
                fUpdateBit(7, false);
                fUpdateBit(6, true);
                fUpdateBit(5, true);
                fUpdateBit(4, false);

                fSetInstructionHelp("  Enter Next Number");
                sInstruction = sInstruction + "-9";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "6", iRowPosition, 1);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("7") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("JSRR-OFFSET-2-6") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5-9") == true) {
                fDisableNumberPad();

                fUpdateBit(3, false);
                fUpdateBit(2, true);
                fUpdateBit(1, true);
                fUpdateBit(0, true);

                if (iRowPosition != 512) {
                    fEnableMnemonics();

                    sInstruction = null;
                    jlblInstructionHelp.setText("  Enter Next Instruction");
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "7", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    iRowPosition = iRowPosition + 1;
                } else {
                    sInstruction = null;
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "7", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    jlblInstructionHelp.setText("Start Coding from Next Segment");
                }
            } else if (sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5") == true) {
                fUpdateBit(7, false);
                fUpdateBit(6, true);
                fUpdateBit(5, true);
                fUpdateBit(4, true);

                fSetInstructionHelp("  Enter Next Number");
                sInstruction = sInstruction + "-9";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "7", iRowPosition, 1);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("8") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("JSRR-OFFSET-2-6") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5-9") == true) {
                fDisableNumberPad();

                fUpdateBit(3, true);
                fUpdateBit(2, false);
                fUpdateBit(1, false);
                fUpdateBit(0, false);

                if (iRowPosition != 512) {
                    fEnableMnemonics();

                    sInstruction = null;
                    jlblInstructionHelp.setText("  Enter Next Instruction");
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "8", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    iRowPosition = iRowPosition + 1;
                } else {
                    sInstruction = null;
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "8", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    jlblInstructionHelp.setText("Start Coding from Next Segment");
                }
            } else if (sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5") == true) {
                fUpdateBit(7, true);
                fUpdateBit(6, false);
                fUpdateBit(5, false);
                fUpdateBit(4, false);

                fSetInstructionHelp("  Enter Next Number");
                sInstruction = sInstruction + "-9";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "8", iRowPosition, 1);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("9") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("JSRR-OFFSET-2-6") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5-9") == true) {
                fDisableNumberPad();

                fUpdateBit(3, true);
                fUpdateBit(2, false);
                fUpdateBit(1, false);
                fUpdateBit(0, true);

                if (iRowPosition != 512) {
                    fEnableMnemonics();

                    sInstruction = null;
                    jlblInstructionHelp.setText("  Enter Next Instruction");
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "9", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    iRowPosition = iRowPosition + 1;
                } else {
                    sInstruction = null;
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "9", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    jlblInstructionHelp.setText("Start Coding from Next Segment");
                }
            } else if (sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5") == true) {
                fUpdateBit(7, true);
                fUpdateBit(6, false);
                fUpdateBit(5, false);
                fUpdateBit(4, true);

                fSetInstructionHelp("  Enter Next Number");
                sInstruction = sInstruction + "-9";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "9", iRowPosition, 1);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("a") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("JSRR-OFFSET-2-6") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5-9") == true) {
                fDisableNumberPad();

                fUpdateBit(3, true);
                fUpdateBit(2, false);
                fUpdateBit(1, true);
                fUpdateBit(0, false);

                if (iRowPosition != 512) {
                    fEnableMnemonics();

                    sInstruction = null;
                    jlblInstructionHelp.setText("  Enter Next Instruction");
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "a", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    iRowPosition = iRowPosition + 1;
                } else {
                    sInstruction = null;
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "a", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    jlblInstructionHelp.setText("Start Coding from Next Segment");
                }
            } else if (sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5") == true) {
                fUpdateBit(7, true);
                fUpdateBit(6, false);
                fUpdateBit(5, true);
                fUpdateBit(4, false);

                fSetInstructionHelp("  Enter Next Number");
                sInstruction = sInstruction + "-9";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "a", iRowPosition, 1);
            }

        } else if (e.getActionCommand().equalsIgnoreCase("b") == true) {

            if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("JSRR-OFFSET-2-6") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5-9") == true) {
                fDisableNumberPad();


                fUpdateBit(3, true);
                fUpdateBit(2, false);
                fUpdateBit(1, true);
                fUpdateBit(0, true);

                if (iRowPosition != 512) {
                    fEnableMnemonics();

                    sInstruction = null;
                    jlblInstructionHelp.setText("  Enter Next Instruction");
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "b", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    iRowPosition = iRowPosition + 1;

                } else {
                    sInstruction = null;
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "b", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    jlblInstructionHelp.setText("Start Coding from Next Segment");

                }

            } else if (sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5") == true) {
                fUpdateBit(7, true);
                fUpdateBit(6, false);
                fUpdateBit(5, true);
                fUpdateBit(4, true);

                fSetInstructionHelp("  Enter Next Number");
                sInstruction = sInstruction + "-9";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "b", iRowPosition, 1);
            }

        } else if (e.getActionCommand().equalsIgnoreCase("c") == true) {

            if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("JSRR-OFFSET-2-6") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5-9") == true) {
                fDisableNumberPad();

                fUpdateBit(3, true);
                fUpdateBit(2, true);
                fUpdateBit(1, false);
                fUpdateBit(0, false);


                if (iRowPosition != 512) {
                    fEnableMnemonics();

                    sInstruction = null;
                    jlblInstructionHelp.setText("  Enter Next Instruction");
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "c", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    iRowPosition = iRowPosition + 1;

                } else {
                    sInstruction = null;
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "c", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    jlblInstructionHelp.setText("Start Coding from Next Segment");

                }

            } else if (sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5") == true) {
                fUpdateBit(7, true);
                fUpdateBit(6, true);
                fUpdateBit(5, false);
                fUpdateBit(4, false);

                fSetInstructionHelp("  Enter Next Number");
                sInstruction = sInstruction + "-9";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "c", iRowPosition, 1);
            }

        } else if (e.getActionCommand().equalsIgnoreCase("d") == true) {

            if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("JSRR-OFFSET-2-6") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5-9") == true) {
                fDisableNumberPad();

                fUpdateBit(3, true);
                fUpdateBit(2, true);
                fUpdateBit(1, false);
                fUpdateBit(0, true);


                if (iRowPosition != 512) {
                    fEnableMnemonics();

                    sInstruction = null;
                    jlblInstructionHelp.setText("  Enter Next Instruction");
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "d", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    iRowPosition = iRowPosition + 1;

                } else {
                    sInstruction = null;
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "d", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    jlblInstructionHelp.setText("Start Coding from Next Segment");

                }

            } else if (sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5") == true) {
                fUpdateBit(7, true);
                fUpdateBit(6, true);
                fUpdateBit(5, false);
                fUpdateBit(4, true);

                fSetInstructionHelp("  Enter Next Number");
                sInstruction = sInstruction + "-9";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "d", iRowPosition, 1);
            }

        } else if (e.getActionCommand().equalsIgnoreCase("e") == true) {

            if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("JSRR-OFFSET-2-6") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5-9") == true) {
                fDisableNumberPad();

                fUpdateBit(3, true);
                fUpdateBit(2, true);
                fUpdateBit(1, true);
                fUpdateBit(0, false);


                if (iRowPosition != 512) {
                    fEnableMnemonics();

                    sInstruction = null;
                    jlblInstructionHelp.setText("  Enter Next Instruction");
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "e", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    iRowPosition = iRowPosition + 1;

                } else {
                    sInstruction = null;
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "e", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    jlblInstructionHelp.setText("Start Coding from Next Segment");

                }

            } else if (sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5") == true) {
                fUpdateBit(7, true);
                fUpdateBit(6, true);
                fUpdateBit(5, true);
                fUpdateBit(4, false);

                fSetInstructionHelp("  Enter Next Number");
                sInstruction = sInstruction + "-9";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "e", iRowPosition, 1);
            }

        } else if (e.getActionCommand().equalsIgnoreCase("f") == true) {

            if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2-FirstBit-LastNibble") == true || sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5-9") == true || sInstruction.equalsIgnoreCase("JSRR-OFFSET-2-6") == true || sInstruction.equalsIgnoreCase("LDR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("STR-DES-SRC-6") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5-9") == true) {
                fDisableNumberPad();

                fUpdateBit(3, true);
                fUpdateBit(2, true);
                fUpdateBit(1, true);
                fUpdateBit(0, true);


                if (iRowPosition != 512) {
                    fEnableMnemonics();

                    sInstruction = null;
                    jlblInstructionHelp.setText("  Enter Next Instruction");
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "f", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    iRowPosition = iRowPosition + 1;

                } else {
                    sInstruction = null;
                    fEnableSegments();
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "f", iRowPosition, 1);
                    dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                    jlblInstructionHelp.setText("Start Coding from Next Segment");

                }

            } else if (sInstruction.equalsIgnoreCase("LD-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LDI-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("LEA-DES-OFFSET-5") == true || sInstruction.equalsIgnoreCase("ST-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("STI-SRC-OFFSET-5") == true || sInstruction.equalsIgnoreCase("BR-OFFSET-5") == true || sInstruction.equalsIgnoreCase("JSR-OFFSET-5") == true) {
                fUpdateBit(7, true);
                fUpdateBit(6, true);
                fUpdateBit(5, true);
                fUpdateBit(4, true);

                fSetInstructionHelp("  Enter Next Number");
                sInstruction = sInstruction + "-9";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "f", iRowPosition, 1);
            }

        } else if (e.getActionCommand().equalsIgnoreCase("R0") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES") == true) {
                fSetInstructionHelp("  Select Source Register-1");

                fUpdateBit(11, false);
                fUpdateBit(10, false);
                fUpdateBit(9, false);

                sInstruction = sInstruction + "-SRC1";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R0", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("LDR") == true || sInstruction.equalsIgnoreCase("STR") == true) {
                fSetInstructionHelp("  Select Base Register");


                fUpdateBit(11, false);
                fUpdateBit(10, false);
                fUpdateBit(9, false);

                sInstruction = sInstruction + "-DES";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R0", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("LDR-DES") == true || sInstruction.equalsIgnoreCase("STR-DES") == true) {
                fSetInstructionHelp("  Select 6 bit Offset");
                fDisableRegisters();
                fEnableNumbers_0_1_2();


                fUpdateBit(8, false);
                fUpdateBit(7, false);
                fUpdateBit(6, false);

                sInstruction = sInstruction + "-SRC";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R0", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("JSRR") == true) {
                fUpdateBit(8, false);
                fUpdateBit(7, false);
                fUpdateBit(6, false);

                fSetInstructionHelp("  Select 6 bit Offset");
                fDisableMnemonics();
                fDisableR7State();
                fResetR7State();

                fEnableNumbers_0_1_2();
                sInstruction = sInstruction + "-Offset-2";
                if (jcbR7State.isSelected() == true) {
                    fUpdateBit(11, true);
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "_r7, R0", iRowPosition, 1);
                } else {
                    fUpdateBit(11, false);
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + ", R0", iRowPosition, 1);
                }
                fDisableR7State();
                fDisableRegisters();
            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1") == true) {
                fUpdateBit(8, false);
                fUpdateBit(7, false);
                fUpdateBit(6, false);


                fSetInstructionHelp("  Select Register-2/ Imm-5 bit");
                sInstruction = sInstruction + "-SRC2";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R0", iRowPosition, 1);
                fEnableNumbers_0_1();

            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2") == true) {
                fUpdateBit(2, false);
                fUpdateBit(1, false);
                fUpdateBit(0, false);

                fUpdateBit(5, false);
                fUpdateBit(4, false);
                fUpdateBit(3, false);


                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fDisableNumberPad();
                fEnableMnemonics();

                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R0", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);

                fEnableSegments();

                iRowPosition = iRowPosition + 1;
            } else if (sInstruction.equalsIgnoreCase("LD") == true || sInstruction.equalsIgnoreCase("LDI") == true || sInstruction.equalsIgnoreCase("LEA") == true || sInstruction.equalsIgnoreCase("ST") == true || sInstruction.equalsIgnoreCase("STI") == true) {


                fUpdateBit(11, false);
                fUpdateBit(10, false);
                fUpdateBit(9, false);

                fDisableRegisters();
                fEnableNumbers_0_1();
                fSetInstructionHelp("  Enter 9 bit Offset");
                if (sInstruction.equalsIgnoreCase("ST") == true || sInstruction.equalsIgnoreCase("STI") == true) {
                    sInstruction = sInstruction + "-SRC";
                } else {
                    sInstruction = sInstruction + "-DES";
                }
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R0", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("NOT") == true) {
                fSetInstructionHelp("  Select Source Register");

                fUpdateBit(11, false);
                fUpdateBit(10, false);
                fUpdateBit(9, false);

                sInstruction = sInstruction + "-SRC";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R0", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("NOT-SRC") == true) {

                fUpdateBit(8, false);
                fUpdateBit(7, false);
                fUpdateBit(6, false);

                for (int iLoop = 5; iLoop >= 0; iLoop--) {
                    fUpdateBit(iLoop, true);
                }

                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fEnableMnemonics();
                fEnableSegments();
                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R0", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);

                fEnableSegments();

                iRowPosition = iRowPosition + 1;

            } else if (sInstruction.equalsIgnoreCase("AND-DES") == true) {
                fSetInstructionHelp("  Select Source Register-1");

                fUpdateBit(11, false);
                fUpdateBit(10, false);
                fUpdateBit(9, false);

                sInstruction = sInstruction + "-SRC1";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R0", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("AND-DES-SRC1") == true) {
                fUpdateBit(8, false);
                fUpdateBit(7, false);
                fUpdateBit(6, false);

                fSetInstructionHelp("  Select Register-2/ Imm-5 bit");
                sInstruction = sInstruction + "-SRC2";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R0", iRowPosition, 1);
                fEnableNumbers_0_1();

            } else if (sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2") == true) {
                fUpdateBit(2, false);
                fUpdateBit(1, false);
                fUpdateBit(0, false);

                fUpdateBit(5, false);
                fUpdateBit(4, false);
                fUpdateBit(3, false);


                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fDisableNumberPad();
                fEnableMnemonics();
                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R0", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                fEnableSegments();

                iRowPosition = iRowPosition + 1;
            }


        } else if (e.getActionCommand().equalsIgnoreCase("R1") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES") == true) {
                fSetInstructionHelp("  Select Source Register-1");

                fUpdateBit(11, false);
                fUpdateBit(10, false);
                fUpdateBit(9, true);

                sInstruction = sInstruction + "-SRC1";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R1", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("LDR") == true || sInstruction.equalsIgnoreCase("STR") == true) {
                fSetInstructionHelp("  Select Base Register");


                fUpdateBit(11, false);
                fUpdateBit(10, false);
                fUpdateBit(9, true);

                sInstruction = sInstruction + "-DES";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R1", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("LDR-DES") == true || sInstruction.equalsIgnoreCase("STR-DES") == true) {
                fSetInstructionHelp("  Select 6 bit Offset");
                fDisableRegisters();
                fEnableNumbers_0_1_2();


                fUpdateBit(8, false);
                fUpdateBit(7, false);
                fUpdateBit(6, true);

                sInstruction = sInstruction + "-SRC";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R1", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("JSRR") == true) {
                fUpdateBit(8, false);
                fUpdateBit(7, false);
                fUpdateBit(6, true);
                fDisableRegisters();
                fSetInstructionHelp("  Select 6 bit Offset");
                fDisableMnemonics();
                fDisableR7State();
                fResetR7State();

                fEnableNumbers_0_1_2();
                sInstruction = sInstruction + "-Offset-2";
                if (jcbR7State.isSelected() == true) {
                    fUpdateBit(11, true);
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "_r7, R1", iRowPosition, 1);
                } else {
                    fUpdateBit(11, false);
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + ", R1", iRowPosition, 1);
                }
                fDisableRegisters();
            } else if (sInstruction.equalsIgnoreCase("NOT-SRC") == true) {

                fUpdateBit(8, false);
                fUpdateBit(7, false);
                fUpdateBit(6, true);

                for (int iLoop = 5; iLoop >= 0; iLoop--) {
                    fUpdateBit(iLoop, true);
                }

                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fEnableMnemonics();

                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R1", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);

                fEnableSegments();

                iRowPosition = iRowPosition + 1;

            } else if (sInstruction.equalsIgnoreCase("NOT") == true) {
                fSetInstructionHelp("  Select Source Register");

                fUpdateBit(11, false);
                fUpdateBit(10, false);
                fUpdateBit(9, true);

                sInstruction = sInstruction + "-SRC";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R1", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1") == true) {
                fUpdateBit(8, false);
                fUpdateBit(7, false);
                fUpdateBit(6, true);

                fSetInstructionHelp("  Select Register-2/ Imm-5 bit");
                sInstruction = sInstruction + "-SRC2";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R1", iRowPosition, 1);
                fEnableNumbers_0_1();
            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2") == true) {
                fUpdateBit(2, false);
                fUpdateBit(1, false);
                fUpdateBit(0, true);

                fUpdateBit(5, false);
                fUpdateBit(4, false);
                fUpdateBit(3, false);


                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fDisableNumberPad();
                fEnableMnemonics();
                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R1", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                fEnableSegments();
                iRowPosition = iRowPosition + 1;
            } else if (sInstruction.equalsIgnoreCase("LD") == true || sInstruction.equalsIgnoreCase("LDI") == true || sInstruction.equalsIgnoreCase("LEA") == true || sInstruction.equalsIgnoreCase("ST") == true || sInstruction.equalsIgnoreCase("STI") == true) {

                fUpdateBit(11, false);
                fUpdateBit(10, false);
                fUpdateBit(9, true);

                fDisableRegisters();
                fEnableNumbers_0_1();
                fSetInstructionHelp("  Enter 9 bit Offset");
                if (sInstruction.equalsIgnoreCase("ST") == true || sInstruction.equalsIgnoreCase("STI") == true) {
                    sInstruction = sInstruction + "-SRC";
                } else {
                    sInstruction = sInstruction + "-DES";
                }

                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R1", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("AND-DES") == true) {
                fSetInstructionHelp("  Select Source Register-1");

                fUpdateBit(11, false);
                fUpdateBit(10, false);
                fUpdateBit(9, true);

                sInstruction = sInstruction + "-SRC1";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R1", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("AND-DES-SRC1") == true) {
                fUpdateBit(8, false);
                fUpdateBit(7, false);
                fUpdateBit(6, true);

                fSetInstructionHelp("  Select Register-2/ Imm-5 bit");
                sInstruction = sInstruction + "-SRC2";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R1", iRowPosition, 1);
                fEnableNumbers_0_1();
            } else if (sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2") == true) {
                fUpdateBit(2, false);
                fUpdateBit(1, false);
                fUpdateBit(0, true);

                fUpdateBit(5, false);
                fUpdateBit(4, false);
                fUpdateBit(3, false);


                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fDisableNumberPad();
                fEnableMnemonics();
                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R1", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                fEnableSegments();
                iRowPosition = iRowPosition + 1;
            }


        } else if (e.getActionCommand().equalsIgnoreCase("R2") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES") == true) {
                fUpdateBit(11, false);
                fUpdateBit(10, true);
                fUpdateBit(9, false);

                fSetInstructionHelp("  Select Source Register-1");
                sInstruction = sInstruction + "-SRC1";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R2", iRowPosition, 1);


            } else if (sInstruction.equalsIgnoreCase("LDR") == true || sInstruction.equalsIgnoreCase("STR") == true) {
                fSetInstructionHelp("  Select Base Register");


                fUpdateBit(11, false);
                fUpdateBit(10, true);
                fUpdateBit(9, false);

                sInstruction = sInstruction + "-DES";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R2", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("LDR-DES") == true || sInstruction.equalsIgnoreCase("STR-DES") == true) {
                fSetInstructionHelp("  Select 6 bit Offset");
                fDisableRegisters();
                fEnableNumbers_0_1_2();


                fUpdateBit(8, false);
                fUpdateBit(7, true);
                fUpdateBit(6, false);

                sInstruction = sInstruction + "-SRC";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R2", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("JSRR") == true) {
                fUpdateBit(8, false);
                fUpdateBit(7, true);
                fUpdateBit(6, false);
                fDisableRegisters();
                fSetInstructionHelp("  Select 6 bit Offset");
                fDisableMnemonics();
                fDisableR7State();
                fResetR7State();

                fEnableNumbers_0_1_2();
                sInstruction = sInstruction + "-Offset-2";
                if (jcbR7State.isSelected() == true) {
                    fUpdateBit(11, true);
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "_r7, R2", iRowPosition, 1);
                } else {
                    fUpdateBit(11, false);
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + ", R2", iRowPosition, 1);
                }
                fDisableRegisters();
            } else if (sInstruction.equalsIgnoreCase("NOT-SRC") == true) {

                fUpdateBit(8, false);
                fUpdateBit(7, true);
                fUpdateBit(6, false);

                for (int iLoop = 5; iLoop >= 0; iLoop--) {
                    fUpdateBit(iLoop, true);
                }

                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fEnableMnemonics();

                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R2", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);

                fEnableSegments();

                iRowPosition = iRowPosition + 1;

            } else if (sInstruction.equalsIgnoreCase("NOT") == true) {
                fSetInstructionHelp("  Select Source Register");

                fUpdateBit(11, false);
                fUpdateBit(10, true);
                fUpdateBit(9, false);

                sInstruction = sInstruction + "-SRC";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R2", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1") == true) {
                fUpdateBit(8, false);
                fUpdateBit(7, true);
                fUpdateBit(6, false);

                fSetInstructionHelp("  Select Register-2/ Imm-5 bit");
                sInstruction = sInstruction + "-SRC2";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R2", iRowPosition, 1);
                fEnableNumbers_0_1();
            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2") == true) {
                fUpdateBit(2, false);
                fUpdateBit(1, true);
                fUpdateBit(0, false);

                fUpdateBit(5, false);
                fUpdateBit(4, false);
                fUpdateBit(3, false);


                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fDisableNumberPad();
                fEnableMnemonics();
                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R2", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                fEnableSegments();
                iRowPosition = iRowPosition + 1;
            } else if (sInstruction.equalsIgnoreCase("LD") == true || sInstruction.equalsIgnoreCase("LDI") == true || sInstruction.equalsIgnoreCase("LEA") == true || sInstruction.equalsIgnoreCase("ST") == true || sInstruction.equalsIgnoreCase("STI") == true) {

                fUpdateBit(11, false);
                fUpdateBit(10, true);
                fUpdateBit(9, false);

                fDisableRegisters();
                fEnableNumbers_0_1();
                fSetInstructionHelp("  Enter 9 bit Offset");
                if (sInstruction.equalsIgnoreCase("ST") == true || sInstruction.equalsIgnoreCase("STI") == true) {
                    sInstruction = sInstruction + "-SRC";
                } else {
                    sInstruction = sInstruction + "-DES";
                }

                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R2", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("AND-DES") == true) {
                fUpdateBit(11, false);
                fUpdateBit(10, true);
                fUpdateBit(9, false);

                fSetInstructionHelp("  Select Source Register-1");
                sInstruction = sInstruction + "-SRC1";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R2", iRowPosition, 1);


            } else if (sInstruction.equalsIgnoreCase("AND-DES-SRC1") == true) {
                fUpdateBit(8, false);
                fUpdateBit(7, true);
                fUpdateBit(6, false);

                fSetInstructionHelp("  Select Register-2/ Imm-5 bit");
                sInstruction = sInstruction + "-SRC2";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R2", iRowPosition, 1);
                fEnableNumbers_0_1();
            } else if (sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2") == true) {
                fUpdateBit(2, false);
                fUpdateBit(1, true);
                fUpdateBit(0, false);

                fUpdateBit(5, false);
                fUpdateBit(4, false);
                fUpdateBit(3, false);


                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fDisableNumberPad();
                fEnableMnemonics();
                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R2", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                fEnableSegments();
                iRowPosition = iRowPosition + 1;
            }


        } else if (e.getActionCommand().equalsIgnoreCase("R3") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES") == true) {
                fUpdateBit(11, false);
                fUpdateBit(10, true);
                fUpdateBit(9, true);

                fSetInstructionHelp("  Select Source Register-1");
                sInstruction = sInstruction + "-SRC1";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R3", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("LDR") == true || sInstruction.equalsIgnoreCase("STR") == true) {
                fSetInstructionHelp("  Select Base Register");


                fUpdateBit(11, false);
                fUpdateBit(10, true);
                fUpdateBit(9, true);

                sInstruction = sInstruction + "-DES";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R3", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("LDR-DES") == true || sInstruction.equalsIgnoreCase("STR-DES") == true) {
                fSetInstructionHelp("  Select 6 bit Offset");
                fDisableRegisters();
                fEnableNumbers_0_1_2();


                fUpdateBit(8, false);
                fUpdateBit(7, true);
                fUpdateBit(6, true);

                sInstruction = sInstruction + "-SRC";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R3", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("JSRR") == true) {
                fUpdateBit(8, false);
                fUpdateBit(7, true);
                fUpdateBit(6, true);
                fDisableRegisters();
                fSetInstructionHelp("  Select 6 bit Offset");
                fDisableMnemonics();
                fDisableR7State();
                fResetR7State();

                fEnableNumbers_0_1_2();
                sInstruction = sInstruction + "-Offset-2";
                if (jcbR7State.isSelected() == true) {
                    fUpdateBit(11, true);
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "_r7, R3", iRowPosition, 1);
                } else {
                    fUpdateBit(11, false);
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + ", R3", iRowPosition, 1);
                }
                fDisableRegisters();
            } else if (sInstruction.equalsIgnoreCase("NOT-SRC") == true) {

                fUpdateBit(8, false);
                fUpdateBit(7, true);
                fUpdateBit(6, true);

                for (int iLoop = 5; iLoop >= 0; iLoop--) {
                    fUpdateBit(iLoop, true);
                }

                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fEnableMnemonics();

                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R3", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);

                fEnableSegments();

                iRowPosition = iRowPosition + 1;

            } else if (sInstruction.equalsIgnoreCase("NOT") == true) {
                fSetInstructionHelp("  Select Source Register");

                fUpdateBit(11, false);
                fUpdateBit(10, true);
                fUpdateBit(9, true);

                sInstruction = sInstruction + "-SRC";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R3", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1") == true) {
                fUpdateBit(8, false);
                fUpdateBit(7, true);
                fUpdateBit(6, true);

                fSetInstructionHelp("  Select Register-2/ Imm-5 bit");
                sInstruction = sInstruction + "-SRC2";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R3", iRowPosition, 1);
                fEnableNumbers_0_1();
            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2") == true) {
                fUpdateBit(2, false);
                fUpdateBit(1, true);
                fUpdateBit(0, true);

                fUpdateBit(5, false);
                fUpdateBit(4, false);
                fUpdateBit(3, false);


                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fDisableNumberPad();
                fEnableMnemonics();
                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R3", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                fEnableSegments();
                iRowPosition = iRowPosition + 1;
            } else if (sInstruction.equalsIgnoreCase("LD") == true || sInstruction.equalsIgnoreCase("LDI") == true || sInstruction.equalsIgnoreCase("LEA") == true || sInstruction.equalsIgnoreCase("ST") == true || sInstruction.equalsIgnoreCase("STI") == true) {

                fUpdateBit(11, false);
                fUpdateBit(10, true);
                fUpdateBit(9, true);

                fDisableRegisters();
                fEnableNumbers_0_1();
                fSetInstructionHelp("  Enter 9 bit Offset");
                if (sInstruction.equalsIgnoreCase("ST") == true || sInstruction.equalsIgnoreCase("STI") == true) {
                    sInstruction = sInstruction + "-SRC";
                } else {
                    sInstruction = sInstruction + "-DES";
                }

                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R3", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("AND-DES") == true) {
                fUpdateBit(11, false);
                fUpdateBit(10, true);
                fUpdateBit(9, true);

                fSetInstructionHelp("  Select Source Register-1");
                sInstruction = sInstruction + "-SRC1";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R3", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("AND-DES-SRC1") == true) {
                fUpdateBit(8, false);
                fUpdateBit(7, true);
                fUpdateBit(6, true);

                fSetInstructionHelp("  Select Register-2/ Imm-5 bit");
                sInstruction = sInstruction + "-SRC2";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R3", iRowPosition, 1);
                fEnableNumbers_0_1();
            } else if (sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2") == true) {
                fUpdateBit(2, false);
                fUpdateBit(1, true);
                fUpdateBit(0, true);

                fUpdateBit(5, false);
                fUpdateBit(4, false);
                fUpdateBit(3, false);


                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fDisableNumberPad();
                fEnableMnemonics();
                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R3", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                fEnableSegments();
                iRowPosition = iRowPosition + 1;
            }


        } else if (e.getActionCommand().equalsIgnoreCase("R4") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES") == true) {
                fUpdateBit(11, true);
                fUpdateBit(10, false);
                fUpdateBit(9, false);

                fSetInstructionHelp("  Select Source Register-1");
                sInstruction = sInstruction + "-SRC1";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R4", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("LDR") == true || sInstruction.equalsIgnoreCase("STR") == true) {
                fSetInstructionHelp("  Select Base Register");


                fUpdateBit(11, true);
                fUpdateBit(10, false);
                fUpdateBit(9, false);

                sInstruction = sInstruction + "-DES";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R4", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("LDR-DES") == true || sInstruction.equalsIgnoreCase("STR-DES") == true) {
                fSetInstructionHelp("  Select 6 bit Offset");
                fDisableRegisters();
                fEnableNumbers_0_1_2();


                fUpdateBit(8, true);
                fUpdateBit(7, false);
                fUpdateBit(6, false);

                sInstruction = sInstruction + "-SRC";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R4", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("JSRR") == true) {
                fUpdateBit(8, true);
                fUpdateBit(7, false);
                fUpdateBit(6, false);
                fDisableRegisters();
                fSetInstructionHelp("  Select 6 bit Offset");
                fDisableMnemonics();
                fDisableR7State();
                fResetR7State();

                fEnableNumbers_0_1_2();
                sInstruction = sInstruction + "-Offset-2";
                if (jcbR7State.isSelected() == true) {
                    fUpdateBit(11, true);
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "_r7, R4", iRowPosition, 1);
                } else {
                    fUpdateBit(11, false);
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + ", R4", iRowPosition, 1);
                }
                fDisableRegisters();
            } else if (sInstruction.equalsIgnoreCase("NOT-SRC") == true) {

                fUpdateBit(8, true);
                fUpdateBit(7, false);
                fUpdateBit(6, false);

                for (int iLoop = 5; iLoop >= 0; iLoop--) {
                    fUpdateBit(iLoop, true);
                }

                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fEnableMnemonics();

                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R4", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);

                fEnableSegments();

                iRowPosition = iRowPosition + 1;

            } else if (sInstruction.equalsIgnoreCase("NOT") == true) {
                fSetInstructionHelp("  Select Source Register");

                fUpdateBit(11, true);
                fUpdateBit(10, false);
                fUpdateBit(9, false);

                sInstruction = sInstruction + "-SRC";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R4", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1") == true) {
                fUpdateBit(8, true);
                fUpdateBit(7, false);
                fUpdateBit(6, false);

                fSetInstructionHelp("  Select Register-2/ Imm-5 bit");
                sInstruction = sInstruction + "-SRC2";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R4", iRowPosition, 1);
                fEnableNumbers_0_1();
            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2") == true) {
                fUpdateBit(2, true);
                fUpdateBit(1, false);
                fUpdateBit(0, false);

                fUpdateBit(5, false);
                fUpdateBit(4, false);
                fUpdateBit(3, false);


                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fDisableNumberPad();
                fEnableMnemonics();
                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R4", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                fEnableSegments();
                iRowPosition = iRowPosition + 1;
            } else if (sInstruction.equalsIgnoreCase("LD") == true || sInstruction.equalsIgnoreCase("LDI") == true || sInstruction.equalsIgnoreCase("LEA") == true || sInstruction.equalsIgnoreCase("ST") == true || sInstruction.equalsIgnoreCase("STI") == true) {

                fUpdateBit(11, true);
                fUpdateBit(10, false);
                fUpdateBit(9, false);

                fDisableRegisters();
                fEnableNumbers_0_1();
                fSetInstructionHelp("  Enter 9 bit Offset");
                if (sInstruction.equalsIgnoreCase("ST") == true || sInstruction.equalsIgnoreCase("STI") == true) {
                    sInstruction = sInstruction + "-SRC";
                } else {
                    sInstruction = sInstruction + "-DES";
                }

                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R4", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("AND-DES") == true) {
                fUpdateBit(11, true);
                fUpdateBit(10, false);
                fUpdateBit(9, false);

                fSetInstructionHelp("  Select Source Register-1");
                sInstruction = sInstruction + "-SRC1";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R4", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("AND-DES-SRC1") == true) {
                fUpdateBit(8, true);
                fUpdateBit(7, false);
                fUpdateBit(6, false);

                fSetInstructionHelp("  Select Register-2/ Imm-5 bit");
                sInstruction = sInstruction + "-SRC2";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R4", iRowPosition, 1);
                fEnableNumbers_0_1();
            } else if (sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2") == true) {
                fUpdateBit(2, true);
                fUpdateBit(1, false);
                fUpdateBit(0, false);

                fUpdateBit(5, false);
                fUpdateBit(4, false);
                fUpdateBit(3, false);


                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fDisableNumberPad();
                fEnableMnemonics();
                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R4", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                fEnableSegments();
                iRowPosition = iRowPosition + 1;
            }


        } else if (e.getActionCommand().equalsIgnoreCase("R5") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES") == true) {
                fUpdateBit(11, true);
                fUpdateBit(10, false);
                fUpdateBit(9, true);

                fSetInstructionHelp("  Select Source Register-1");
                sInstruction = sInstruction + "-SRC1";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R5", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("LDR") == true || sInstruction.equalsIgnoreCase("STR") == true) {
                fSetInstructionHelp("  Select Base Register");


                fUpdateBit(11, true);
                fUpdateBit(10, false);
                fUpdateBit(9, true);

                sInstruction = sInstruction + "-DES";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R5", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("LDR-DES") == true || sInstruction.equalsIgnoreCase("STR-DES") == true) {
                fSetInstructionHelp("  Select 6 bit Offset");
                fDisableRegisters();
                fEnableNumbers_0_1_2();


                fUpdateBit(8, true);
                fUpdateBit(7, false);
                fUpdateBit(6, true);

                sInstruction = sInstruction + "-SRC";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R5", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("JSRR") == true) {
                fUpdateBit(8, true);
                fUpdateBit(7, false);
                fUpdateBit(6, true);
                fDisableRegisters();
                fSetInstructionHelp("  Select 6 bit Offset");
                fDisableMnemonics();
                fDisableR7State();
                fResetR7State();

                fEnableNumbers_0_1_2();
                sInstruction = sInstruction + "-Offset-2";
                if (jcbR7State.isSelected() == true) {
                    fUpdateBit(11, true);
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "_r7, R5", iRowPosition, 1);
                } else {
                    fUpdateBit(11, false);
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + ", R5", iRowPosition, 1);
                }
                fDisableRegisters();
            } else if (sInstruction.equalsIgnoreCase("NOT-SRC") == true) {

                fUpdateBit(8, true);
                fUpdateBit(7, false);
                fUpdateBit(6, true);

                for (int iLoop = 5; iLoop >= 0; iLoop--) {
                    fUpdateBit(iLoop, true);
                }

                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fEnableMnemonics();

                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R5", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);

                fEnableSegments();

                iRowPosition = iRowPosition + 1;

            } else if (sInstruction.equalsIgnoreCase("NOT") == true) {
                fSetInstructionHelp("  Select Source Register");

                fUpdateBit(11, true);
                fUpdateBit(10, false);
                fUpdateBit(9, true);

                sInstruction = sInstruction + "-SRC";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R5", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1") == true) {
                fUpdateBit(8, true);
                fUpdateBit(7, false);
                fUpdateBit(6, true);

                fSetInstructionHelp("  Select Register-2/ Imm-5 bit");
                sInstruction = sInstruction + "-SRC2";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R5", iRowPosition, 1);
                fEnableNumbers_0_1();
            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2") == true) {
                fUpdateBit(2, true);
                fUpdateBit(1, false);
                fUpdateBit(0, true);

                fUpdateBit(5, false);
                fUpdateBit(4, false);
                fUpdateBit(3, false);


                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fDisableNumberPad();
                fEnableMnemonics();
                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R5", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                fEnableSegments();
                iRowPosition = iRowPosition + 1;
            } else if (sInstruction.equalsIgnoreCase("LD") == true || sInstruction.equalsIgnoreCase("LDI") == true || sInstruction.equalsIgnoreCase("LEA") == true || sInstruction.equalsIgnoreCase("ST") == true || sInstruction.equalsIgnoreCase("STI") == true) {

                fUpdateBit(11, true);
                fUpdateBit(10, false);
                fUpdateBit(9, true);

                fDisableRegisters();
                fEnableNumbers_0_1();
                fSetInstructionHelp("  Enter 9 bit Offset");
                if (sInstruction.equalsIgnoreCase("ST") == true || sInstruction.equalsIgnoreCase("STI") == true) {
                    sInstruction = sInstruction + "-SRC";
                } else {
                    sInstruction = sInstruction + "-DES";
                }

                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R5", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("AND-DES") == true) {
                fUpdateBit(11, true);
                fUpdateBit(10, false);
                fUpdateBit(9, true);

                fSetInstructionHelp("  Select Source Register-1");
                sInstruction = sInstruction + "-SRC1";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R5", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("AND-DES-SRC1") == true) {
                fUpdateBit(8, true);
                fUpdateBit(7, false);
                fUpdateBit(6, true);

                fSetInstructionHelp("  Select Register-2/ Imm-5 bit");
                sInstruction = sInstruction + "-SRC2";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R5", iRowPosition, 1);
                fEnableNumbers_0_1();
            } else if (sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2") == true) {
                fUpdateBit(2, true);
                fUpdateBit(1, false);
                fUpdateBit(0, true);

                fUpdateBit(5, false);
                fUpdateBit(4, false);
                fUpdateBit(3, false);


                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fDisableNumberPad();
                fEnableMnemonics();
                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R5", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                fEnableSegments();
                iRowPosition = iRowPosition + 1;
            }


        } else if (e.getActionCommand().equalsIgnoreCase("R6") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES") == true) {
                fUpdateBit(11, true);
                fUpdateBit(10, true);
                fUpdateBit(9, false);

                fSetInstructionHelp("  Select Source Register-1");
                sInstruction = sInstruction + "-SRC1";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R6", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("LDR") == true || sInstruction.equalsIgnoreCase("STR") == true) {
                fSetInstructionHelp("  Select Base Register");


                fUpdateBit(11, true);
                fUpdateBit(10, true);
                fUpdateBit(9, false);

                sInstruction = sInstruction + "-DES";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R6", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("LDR-DES") == true || sInstruction.equalsIgnoreCase("STR-DES") == true) {
                fSetInstructionHelp("  Select 6 bit Offset");
                fDisableRegisters();
                fEnableNumbers_0_1_2();


                fUpdateBit(8, true);
                fUpdateBit(7, true);
                fUpdateBit(6, false);

                sInstruction = sInstruction + "-SRC";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R6", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("JSRR") == true) {
                fUpdateBit(8, true);
                fUpdateBit(7, true);
                fUpdateBit(6, false);
                fDisableRegisters();
                fSetInstructionHelp("  Select 6 bit Offset");
                fDisableMnemonics();

                fEnableNumbers_0_1_2();
                sInstruction = sInstruction + "-Offset-2";
                if (jcbR7State.isSelected() == true) {
                    fUpdateBit(11, true);
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "_r7, R6", iRowPosition, 1);
                } else {
                    fUpdateBit(11, false);
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + ", R6", iRowPosition, 1);
                }
                fDisableRegisters();
                fDisableR7State();
                fResetR7State();

            } else if (sInstruction.equalsIgnoreCase("NOT-SRC") == true) {

                fUpdateBit(8, true);
                fUpdateBit(7, true);
                fUpdateBit(6, false);

                for (int iLoop = 5; iLoop >= 0; iLoop--) {
                    fUpdateBit(iLoop, true);
                }

                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fEnableMnemonics();

                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R6", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);

                fEnableSegments();

                iRowPosition = iRowPosition + 1;

            } else if (sInstruction.equalsIgnoreCase("NOT") == true) {
                fSetInstructionHelp("  Select Source Register");

                fUpdateBit(11, true);
                fUpdateBit(10, true);
                fUpdateBit(9, false);

                sInstruction = sInstruction + "-SRC";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R6", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1") == true) {

                fUpdateBit(8, true);
                fUpdateBit(7, true);
                fUpdateBit(6, false);

                fSetInstructionHelp("  Select Register-2/ Imm-5 bit");
                sInstruction = sInstruction + "-SRC2";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R6", iRowPosition, 1);
                fEnableNumbers_0_1();
            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2") == true) {
                fUpdateBit(2, true);
                fUpdateBit(1, true);
                fUpdateBit(0, false);

                fUpdateBit(5, false);
                fUpdateBit(4, false);
                fUpdateBit(3, false);


                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fDisableNumberPad();
                fEnableMnemonics();
                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R6", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                fEnableSegments();
                iRowPosition = iRowPosition + 1;
            } else if (sInstruction.equalsIgnoreCase("LD") == true || sInstruction.equalsIgnoreCase("LDI") == true || sInstruction.equalsIgnoreCase("LEA") == true || sInstruction.equalsIgnoreCase("ST") == true || sInstruction.equalsIgnoreCase("STI") == true) {

                fUpdateBit(11, true);
                fUpdateBit(10, true);
                fUpdateBit(9, false);

                fDisableRegisters();
                fEnableNumbers_0_1();
                fSetInstructionHelp("  Enter 9 bit Offset");
                if (sInstruction.equalsIgnoreCase("ST") == true || sInstruction.equalsIgnoreCase("STI") == true) {
                    sInstruction = sInstruction + "-SRC";
                } else {
                    sInstruction = sInstruction + "-DES";
                }

                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R6", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("AND-DES") == true) {
                fUpdateBit(11, true);
                fUpdateBit(10, true);
                fUpdateBit(9, false);

                fSetInstructionHelp("  Select Source Register-1");
                sInstruction = sInstruction + "-SRC1";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R6", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("AND-DES-SRC1") == true) {

                fUpdateBit(8, true);
                fUpdateBit(7, true);
                fUpdateBit(6, false);

                fSetInstructionHelp("  Select Register-2/ Imm-5 bit");
                sInstruction = sInstruction + "-SRC2";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R6", iRowPosition, 1);
                fEnableNumbers_0_1();
            } else if (sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2") == true) {
                fUpdateBit(2, true);
                fUpdateBit(1, true);
                fUpdateBit(0, false);

                fUpdateBit(5, false);
                fUpdateBit(4, false);
                fUpdateBit(3, false);


                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fDisableNumberPad();
                fEnableMnemonics();
                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R6", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                fEnableSegments();
                iRowPosition = iRowPosition + 1;
            }


        } else if (e.getActionCommand().equalsIgnoreCase("R7") == true) {
            if (sInstruction.equalsIgnoreCase("ADD-DES") == true) {
                fUpdateBit(11, true);
                fUpdateBit(10, true);
                fUpdateBit(9, true);

                fSetInstructionHelp("  Select Source Register-1");
                sInstruction = sInstruction + "-SRC1";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R7", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("LDR") == true || sInstruction.equalsIgnoreCase("STR") == true) {
                fSetInstructionHelp("  Select Base Register");


                fUpdateBit(11, true);
                fUpdateBit(10, true);
                fUpdateBit(9, true);

                sInstruction = sInstruction + "-DES";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R7", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("LDR-DES") == true || sInstruction.equalsIgnoreCase("STR-DES") == true) {
                fSetInstructionHelp("  Select 6 bit Offset");
                fDisableRegisters();
                fEnableNumbers_0_1_2();


                fUpdateBit(8, true);
                fUpdateBit(7, true);
                fUpdateBit(6, true);

                sInstruction = sInstruction + "-SRC";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R7", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("JSRR") == true) {
                fUpdateBit(8, true);
                fUpdateBit(7, true);
                fUpdateBit(6, true);
                fDisableRegisters();
                fSetInstructionHelp("  Select 6 bit Offset");
                fDisableMnemonics();
                fDisableR7State();
                fResetR7State();

                fEnableNumbers_0_1_2();
                sInstruction = sInstruction + "-Offset-2";
                if (jcbR7State.isSelected() == true) {
                    fUpdateBit(11, true);
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + "_r7, R7", iRowPosition, 1);
                } else {
                    fUpdateBit(11, false);
                    dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + ", R7", iRowPosition, 1);
                }
                fDisableRegisters();
            } else if (sInstruction.equalsIgnoreCase("NOT-SRC") == true) {

                fUpdateBit(8, true);
                fUpdateBit(7, true);
                fUpdateBit(6, true);

                for (int iLoop = 5; iLoop >= 0; iLoop--) {
                    fUpdateBit(iLoop, true);
                }

                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fEnableMnemonics();

                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R7", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);

                fEnableSegments();

                iRowPosition = iRowPosition + 1;
            } else if (sInstruction.equalsIgnoreCase("NOT") == true) {
                fSetInstructionHelp("  Select Source Register");

                fUpdateBit(11, true);
                fUpdateBit(10, true);
                fUpdateBit(9, true);

                sInstruction = sInstruction + "-SRC";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R7", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1") == true) {
                fUpdateBit(8, true);
                fUpdateBit(7, true);
                fUpdateBit(6, true);

                fSetInstructionHelp("  Select Register-2/ Imm-5 bit");
                sInstruction = sInstruction + "-SRC2";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R7", iRowPosition, 1);
                fEnableNumbers_0_1();
            } else if (sInstruction.equalsIgnoreCase("ADD-DES-SRC1-SRC2") == true) {
                fUpdateBit(2, true);
                fUpdateBit(1, true);
                fUpdateBit(0, true);

                fUpdateBit(5, false);
                fUpdateBit(4, false);
                fUpdateBit(3, false);


                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fDisableNumberPad();
                fEnableMnemonics();
                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R7", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                fEnableSegments();
                iRowPosition = iRowPosition + 1;
            } else if (sInstruction.equalsIgnoreCase("LD") == true || sInstruction.equalsIgnoreCase("LDI") == true || sInstruction.equalsIgnoreCase("LEA") == true || sInstruction.equalsIgnoreCase("ST") == true || sInstruction.equalsIgnoreCase("STI") == true) {

                fUpdateBit(11, true);
                fUpdateBit(10, true);
                fUpdateBit(9, true);

                fDisableRegisters();
                fEnableNumbers_0_1();
                fSetInstructionHelp("  Enter 9 bit Offset");
                if (sInstruction.equalsIgnoreCase("ST") == true || sInstruction.equalsIgnoreCase("STI") == true) {
                    sInstruction = sInstruction + "-SRC";
                } else {
                    sInstruction = sInstruction + "-DES";
                }

                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R7", iRowPosition, 1);
            } else if (sInstruction.equalsIgnoreCase("AND-DES") == true) {
                fUpdateBit(11, true);
                fUpdateBit(10, true);
                fUpdateBit(9, true);

                fSetInstructionHelp("  Select Source Register-1");
                sInstruction = sInstruction + "-SRC1";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R7", iRowPosition, 1);

            } else if (sInstruction.equalsIgnoreCase("AND-DES-SRC1") == true) {
                fUpdateBit(8, true);
                fUpdateBit(7, true);
                fUpdateBit(6, true);

                fSetInstructionHelp("  Select Register-2/ Imm-5 bit");
                sInstruction = sInstruction + "-SRC2";
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R7", iRowPosition, 1);
                fEnableNumbers_0_1();
            } else if (sInstruction.equalsIgnoreCase("AND-DES-SRC1-SRC2") == true) {
                fUpdateBit(2, true);
                fUpdateBit(1, true);
                fUpdateBit(0, true);

                fUpdateBit(5, false);
                fUpdateBit(4, false);
                fUpdateBit(3, false);


                fSetInstructionHelp("  Enter Next Instruction");
                fDisableRegisters();
                fDisableNumberPad();
                fEnableMnemonics();
                sInstruction = null;
                dtmModel.setValueAt((String) dtmModel.getValueAt(iRowPosition, 1) + " ,R7", iRowPosition, 1);
                dtmModel.setValueAt("0x" + fExtractOpcode().toString(16).toUpperCase(), iRowPosition, 2);
                fEnableSegments();
                iRowPosition = iRowPosition + 1;
            }
        }
    }

    public Editor(JTabbedPane jtpMain, SimulatorPanel objExecute, FlashScreen objFlashScreen, float fWidthFactor, float fHeightFactor, Font fnt, Font fntBlueBoldFont) {
        setLayout(null);

        this.jtpMain = jtpMain;
        this.objExecute = objExecute;
        this.fnt = fnt;
        this.fntBlueBoldFont = fntBlueBoldFont;
        this.fWidthFactor = fWidthFactor;
        this.fHeightFactor = fHeightFactor;

        jifMnemonics = new JInternalFrame("M N E M O N I C S", false, false, false, false);
        jifNumberPad = new JInternalFrame("H E X - - P A D", false, false, false, false);
        jifProgram = new JInternalFrame("P R O G R A M", false, false, false, false);
        jifRegisters = new JInternalFrame("R E G I S T E R S", false, false, false, false);
        jifMemory = new JInternalFrame("C O N D I T I O N S", false, false, false, false);


        jifMnemonics.setToolTipText("M N E M O N I C S   P A D");

        jifNumberPad.setToolTipText("N U M B E R   P A D");
        jifRegisters.setToolTipText("R E G I S T E R S   P A D");
        jifProgram.setToolTipText("P R O G R A M   A R E A");

        objFlashScreen.fSetText("S E T T I N G    D I M E N S I O N S  . . .");
        fDelay();

        objFlashScreen.fSetText("I N I T I A L I S I N G     M E M O R Y  . . .");
        fDelay();
        fInitialiseRAM(objFlashScreen);

        jlblNegative = new JLabel("NEGATIVE");
        jlblNegative.setSize((int) (100 * fWidthFactor), (int) (30 * fHeightFactor));
        jlblNegative.setLocation((int) (20 * fWidthFactor), (int) (40 * fHeightFactor));
        jlblNegative.setFont(fnt);

        jcbNegative = new JCheckBox();
        jcbNegative.setSize((int) (50 * fWidthFactor), (int) (30 * fHeightFactor));
        jcbNegative.setLocation((int) (100 * fWidthFactor), (int) (40 * fHeightFactor));
        jcbNegative.addActionListener(this);
        jcbNegative.setActionCommand("NEGATIVE");
        jcbNegative.setFont(fnt);

        jlblZero = new JLabel("ZERO");
        jlblZero.setSize((int) (100 * fWidthFactor), (int) (30 * fHeightFactor));
        jlblZero.setLocation((int) (20 * fWidthFactor), (int) (80 * fHeightFactor));
        jlblZero.setFont(fnt);


        jcbZero = new JCheckBox();
        jcbZero.setSize((int) (50 * fWidthFactor), (int) (30 * fHeightFactor));
        jcbZero.setLocation((int) (100 * fWidthFactor), (int) (80 * fHeightFactor));
        jcbZero.addActionListener(this);
        jcbZero.setActionCommand("ZERO");
        jcbZero.setFont(fnt);


        jlblPositive = new JLabel("POSITIVE");
        jlblPositive.setSize((int) (100 * fWidthFactor), (int) (30 * fHeightFactor));
        jlblPositive.setLocation((int) (20 * fWidthFactor), (int) (120 * fHeightFactor));
        jlblPositive.setFont(fnt);

        jcbPositive = new JCheckBox();
        jcbPositive.setSize((int) (50 * fWidthFactor), (int) (30 * fHeightFactor));
        jcbPositive.setLocation((int) (100 * fWidthFactor), (int) (120 * fHeightFactor));
        jcbPositive.addActionListener(this);
        jcbPositive.setActionCommand("POSITIVE");
        jcbPositive.setFont(fnt);

        jlblR7State = new JLabel("R7 STATUS");
        jlblR7State.setSize((int) (70 * fWidthFactor), (int) (20 * fHeightFactor));
        jlblR7State.setLocation((int) (20 * fWidthFactor), (int) (60 * fHeightFactor));
        jlblR7State.setVisible(false);
        jlblR7State.setFont(fnt);


        jcbR7State = new JCheckBox();
        jcbR7State.setSize((int) (30 * fWidthFactor), (int) (20 * fWidthFactor));
        jcbR7State.setLocation((int) (90 * fWidthFactor), (int) (60 * fHeightFactor));
        jcbR7State.addActionListener(this);
        jcbR7State.setActionCommand("R7 STATUS");
        jcbR7State.setVisible(false);
        jcbR7State.setFont(fnt);


        jlblStartingLocation = new JLabel("Location");
        jlblStartingLocation.setSize((int) (60 * fWidthFactor), (int) (20 * fHeightFactor));
        jlblStartingLocation.setLocation((int) (20 * fWidthFactor), (int) (40 * fHeightFactor));
        jlblStartingLocation.setVisible(false);
        jlblStartingLocation.setFont(fnt);

        jtbStartingLocation = new JTextField(4);
        jtbStartingLocation.setSize((int) (30 * fWidthFactor), (int) (20 * fHeightFactor));
        jtbStartingLocation.setLocation((int) (100 * fWidthFactor), (int) (40 * fHeightFactor));
        jtbStartingLocation.setVisible(false);
        jtbStartingLocation.setToolTipText("ENTER 4 DIGIT HEX. VALUE");
        jtbStartingLocation.setFont(fnt);

        jbRun = new JButton("RUN");
        jbRun.setActionCommand("RUN");


        jbRun.setToolTipText("R U N   M O D E");
        jbRun.addActionListener(this);
        jbRun.setSize((int) (60 * fWidthFactor), (int) (20 * fHeightFactor));
        jbRun.setLocation((int) (10 * fWidthFactor), (int) (80 * fHeightFactor));
        jbRun.setVisible(false);
        jbRun.setFont(new Font("ARIAL", Font.PLAIN, (int) (8 * fHeightFactor)));

        jbStep = new JButton("STEP");
        jbStep.setActionCommand("STEP");
        jbStep.setToolTipText("S T E P   M O D E");
        jbStep.addActionListener(this);
        jbStep.setSize((int) (60 * fWidthFactor), (int) (20 * fHeightFactor));
        jbStep.setLocation((int) (80 * fWidthFactor), (int) (80 * fHeightFactor));
        jbStep.setVisible(false);
        jbStep.setFont(new Font("ARIAL", Font.PLAIN, (int) (8 * fHeightFactor)));

        fAddInstructionBits(this);
        fAddInstructionHelp(this);
        fAddSegment(this);


        jbButtons = new JButton[strButtonNames.length];
        jbRegisters = new JButton[strRegisters.length];
        jbNumbers = new JButton[strNumbers.length];

        jspProgram = new JScrollPane();


        objFlashScreen.fSetText("S E T T I N G    P R O G R A M    U I  . . .");
        fDelay();
        jtProgram = new JTable(dtmModel);
        jtProgram.setToolTipText("C O D E   A R E A");
        TableColumnModel tcmProgram = jtProgram.getColumnModel();
        TableColumn tcFirst = tcmProgram.getColumn(0);
        TableColumn tcSecond = tcmProgram.getColumn(1);
        TableColumn tcThird = tcmProgram.getColumn(2);

        tcFirst.setPreferredWidth((int) (80 * fWidthFactor));
        tcSecond.setPreferredWidth((int) (200 * fWidthFactor));
        tcThird.setPreferredWidth((int) (120 * fWidthFactor));


        jtProgram.setRowHeight((int) (fnt.getSize() + 5));

        fSetInstructionColumn(jtProgram.getColumnModel().getColumn(2));
        fSetMnemonicsAndAddressColumn(jtProgram.getColumnModel().getColumn(1));
        fSetMnemonicsAndAddressColumn(jtProgram.getColumnModel().getColumn(0));


        jtProgram.setFont(fnt);

        jtProgram.setName(" P R O G R A M ");

        jtProgram.setEnabled(true);


        jtProgram.addMouseListener(
                new MouseListener() {
                    public void mouseClicked(MouseEvent e) {
                        /**
                         * Invoked when a mouse button has been pressed on a component.
                         *
                         */

                    }

                    public void mousePressed(MouseEvent e) {
                        /**
                         * Invoked when a mouse button has been released on a component.
                         */

                    }

                    public void mouseReleased(MouseEvent e) {
                        /**
                         * Invoked when the mouse enters a component.
                         */
                        iRowPosition = jtProgram.getSelectedRow();

                        String sColumnValue = (String) jtProgram.getValueAt(iRowPosition, 2);

                        sColumnValue = sColumnValue.substring(2);

                        if (sColumnValue.length() > 0) {
                            fShowBitWiseValue(sColumnValue);
                        } else {
                            fResetInstructions();
                        }


                        if (KeyEvent.KEY_PRESSED == KeyEvent.VK_CONTROL) {
                            String sValue = (String) jtProgram.getValueAt(iRowPosition, 2);
                            String sNewValue = "0000";

                            //sValue=sValue.substring(2);
                            if (sValue.length() > 2) {
                                sNewValue = fValidateHexValue(sValue);
                            } else {
                                sNewValue = fValidateHexValue("0");
                            }

                            jtProgram.setValueAt(sNewValue, iRowPosition, 2);

                        }
                    }

                    public void mouseEntered(MouseEvent e) {
                        /**
                         * Invoked when the mouse exits a component.
                         */
                    }

                    public void mouseExited(MouseEvent e) {
                    }

                }
        );

        jtProgram.addKeyListener(new KeyListener() {
                                     public void keyTyped(KeyEvent e) {
                                     }

                                     public void keyReleased(KeyEvent e) {

                                     }

                                     public void keyPressed(KeyEvent e) {

                                         if ((jtProgram.getSelectedColumn() == 2 && jtProgram.getSelectedRow() == iRowCount)) {

                                             if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
                                                 iRowCount = iRowCount + 1;

                                                 String strData[] = {new Integer(iMemoryAddress + jtProgram.getSelectedRow() + 1).toString(), "", ""};
                                                 dtmModel.insertRow(iRowCount, strData);

                                                 dtmModel.fireTableDataChanged();

                                                 jtProgram.validate();
                                             }
                                         }

                                         if (e.getKeyCode() == KeyEvent.VK_HOME) {
                                             for (int iLoop = 0; iLoop < jtProgram.getRowCount(); iLoop++) {
                                                 System.out.print(jtProgram.getValueAt(iLoop, 0) + "\t");
                                                 System.out.print(jtProgram.getValueAt(iLoop, 1) + "\t");
                                                 System.out.println(jtProgram.getValueAt(iLoop, 2));
                                             }
                                         }
                                     }
                                 }
        );
        jspProgram.setViewportView(jtProgram);

        int iColPos = (int) (16 * fWidthFactor);
        int iRowPos = (int) (16 * fHeightFactor);

        objFlashScreen.fSetText("S E T T I N G    M N E M O N I C S   P A D  . . .");
        fDelay();

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

        iColPos = (int) (16 * fWidthFactor);
        iRowPos = (int) (16 * fHeightFactor);

        objFlashScreen.fSetText("S E T T I N G    R E G I S T E R S   P A D  . . .");
        fDelay();
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

        iRowPos = (int) (16 * fHeightFactor);
        iColPos = (int) (16 * fWidthFactor);

        objFlashScreen.fSetText("S E T T I N G    N U M B E R    P A D  . . .");
        fDelay();

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

        jifMnemonics.getContentPane().setLayout(null);
        jifNumberPad.getContentPane().setLayout(null);
        jifProgram.getContentPane().setLayout(new BorderLayout());
        jifRegisters.getContentPane().setLayout(null);
        jifMemory.getContentPane().setLayout(null);

        jifMnemonics.setLocation((int) (68 * fWidthFactor), (int) (38 * fHeightFactor));
        jifMnemonics.setSize((int) (220 * fWidthFactor), (int) (220 * fHeightFactor));

        for (int iLoop = 0; iLoop < strButtonNames.length; iLoop++) {
            jifMnemonics.getContentPane().add(jbButtons[iLoop]);
        }

        jifRegisters.setLocation((int) (310 * fWidthFactor), (int) (38 * fHeightFactor));
        jifRegisters.setSize((int) (150 * fWidthFactor), (int) (220 * fHeightFactor));


        for (int iLoop = 0; iLoop < strRegisters.length; iLoop++) {
            jifRegisters.getContentPane().add(jbRegisters[iLoop]);
        }

        jifNumberPad.setLocation((int) (68 * fWidthFactor), (int) (270 * fHeightFactor));
        jifNumberPad.setSize((int) (220 * fWidthFactor), (int) (220 * fHeightFactor));

        jifMemory.setLocation((int) (310 * fWidthFactor), (int) (270 * fHeightFactor));
        jifMemory.setSize((int) (150 * fWidthFactor), (int) (220 * fHeightFactor));

        jifMemory.getContentPane().add(jlblNegative);
        jifMemory.getContentPane().add(jlblPositive);
        jifMemory.getContentPane().add(jlblZero);
        jifMemory.getContentPane().add(jlblR7State);
        jifMemory.getContentPane().add(jcbNegative);
        jifMemory.getContentPane().add(jcbPositive);
        jifMemory.getContentPane().add(jcbZero);
        jifMemory.getContentPane().add(jcbR7State);

        jifMemory.getContentPane().add(jlblStartingLocation);
        jifMemory.getContentPane().add(jtbStartingLocation);
        jifMemory.getContentPane().add(jbRun);
        jifMemory.getContentPane().add(jbStep);

        //jifMemory.setTitle("E X E C U T E");

        for (int iLoop = 0; iLoop < strNumbers.length; iLoop++) {
            jifNumberPad.getContentPane().add(jbNumbers[iLoop]);
        }
        jifProgram.setLocation((int) (485 * fWidthFactor), (int) (38 * fHeightFactor));
        jifProgram.setSize((int) (219 * fWidthFactor), (int) (450 * fHeightFactor));

        jifRegisters.setVisible(true);
        jifNumberPad.setVisible(true);
        jifMnemonics.setVisible(true);
        jifProgram.setVisible(true);
        jifMemory.setVisible(true);

        jifProgram.getContentPane().add(jspProgram, BorderLayout.CENTER);

        add(jifMnemonics);
        add(jifRegisters);
        add(jifNumberPad);
        add(jifProgram);
        add(jifMemory);

        fDisableNumberPad();
        fDisableConditions();
        fDisableR7State();
        fDisableRunStatus();
        fDisableRegisters();

        validate();
    }

    private String fValidateHexValue(String sPreviousValue) {

        String sNewValue = JOptionPane.showInputDialog(null, "Enter 4 digit Hex Value", "Value", JOptionPane.PLAIN_MESSAGE);
        if (sNewValue.length() > 0) {
            sNewValue = sNewValue.toUpperCase();
        }
        //sNewValue =sNewValue.trim();
        if (sNewValue.length() > 4) {

            JOptionPane.showMessageDialog(null, "Enter 4 Digit Hex Value !! ", "I N V A L I D  D A T A", JOptionPane.ERROR_MESSAGE);
            return sPreviousValue;
        } else {
            if (sNewValue.length() <= 0) {
                JOptionPane.showMessageDialog(null, "Enter 4 Digit Hex Value !! ", "I N V A L I D  D A T A", JOptionPane.ERROR_MESSAGE);
                return sPreviousValue;
            } else {
                for (int iLoop = 0; iLoop < sNewValue.length(); iLoop++) {
                    if (sNewValue.charAt(iLoop) == 'A' || sNewValue.charAt(iLoop) == 'B' || sNewValue.charAt(iLoop) == 'C' || sNewValue.charAt(iLoop) == 'D' || sNewValue.charAt(iLoop) == 'E' || sNewValue.charAt(iLoop) == 'F' || sNewValue.charAt(iLoop) == '1' || sNewValue.charAt(iLoop) == '2' || sNewValue.charAt(iLoop) == '3' || sNewValue.charAt(iLoop) == '4' || sNewValue.charAt(iLoop) == '5' || sNewValue.charAt(iLoop) == '6' || sNewValue.charAt(iLoop) == '7' || sNewValue.charAt(iLoop) == '8' || sNewValue.charAt(iLoop) == '9' || sNewValue.charAt(iLoop) == '0') {

                    } else {
                        JOptionPane.showMessageDialog(null, "Enter 4 Digit Hex Value !! ", "I N V A L I D  D A T A", JOptionPane.ERROR_MESSAGE);
                        return sPreviousValue;
                    }

                }
                return "0x" + sNewValue;
            }
        }
    }


    private void fSetMnemonicsAndAddressColumn(TableColumn tcColumn) {
        DisabledColumnCell objDisabledCell = new DisabledColumnCell();

        tcColumn.setCellEditor(new DefaultCellEditor(objDisabledCell));

        DefaultTableCellRenderer dtcrRenderer =
                new DefaultTableCellRenderer();

        tcColumn.setCellRenderer(dtcrRenderer);

    }

    private void fSetInstructionColumn(TableColumn tcInstructionColumn) {
        InstructionColumnCell objInstructionCell = new InstructionColumnCell(8);
        tcInstructionColumn.setCellEditor(new DefaultCellEditor(objInstructionCell));

        DefaultTableCellRenderer dtcrRenderer =
                new DefaultTableCellRenderer();


        tcInstructionColumn.setCellRenderer(dtcrRenderer);


    }

    private void fUpdateBit(int iBitPosition, boolean bValue) {
        if (bValue == true) {
            jlblInstructionBits[15 - iBitPosition].setText("1");
        } else {
            jlblInstructionBits[15 - iBitPosition].setText("0");
        }
    }

    private void fSetInstructionBits(Hashtable htBits) {
        int iLoop;
        int iBitValue = 0;

        for (iLoop = 0; iLoop < 16; iLoop++) {
            //iBitValue = (int)(htBits.get((int)(iLoop));
            if (iBitValue == 0) {
                jlblInstructionBits[iLoop].setText("0");
            } else {
                jlblInstructionBits[iLoop].setText("1");
            }

        }
    }


    private void fAddInstructionBits(JPanel jpInput) {
        JPanel jpInstructionsPanel = new JPanel();

        jpInstructionsPanel.setLayout(null);
        jpInstructionsPanel.setSize((int) (220 * fWidthFactor), (int) (20 * fHeightFactor));
        jpInstructionsPanel.setLocation((int) (70 * fWidthFactor), (int) (10 * fHeightFactor));


        int iX = (int) (0 * fWidthFactor);
        int iY = (int) (5 * fHeightFactor);

        Border brInstructionLabel = BorderFactory.createLineBorder(Color.black);

        for (int iLoop = 0; iLoop < jlblInstructionBits.length; iLoop++) {
            jlblInstructionBits[iLoop] = new JLabel("0");
            jlblInstructionBits[iLoop].setToolTipText("I N S T R U C T I O N    F O R M A T");
            jlblInstructionBits[iLoop].setSize((int) (12 * fWidthFactor), (int) (10 * fHeightFactor));
            jlblInstructionBits[iLoop].setLocation(iX = iX + (int) (13 * fWidthFactor), (int) iY);

            jlblInstructionBits[iLoop].setForeground(Color.black);

            //jlblInstructionBits[iLoop].setFont(new Font("Arial",Font.BOLD,10));
            jlblInstructionBits[iLoop].setFont(fnt);
            jpInstructionsPanel.add(jlblInstructionBits[iLoop]);

        }
        jpInstructionsPanel.setBorder(brInstructionLabel);
        jpInput.add(jpInstructionsPanel);
    }

    private void fSetInstructionHelp(String sHelp) {
        jlblInstructionHelp.setText(sHelp);
    }

    private void fAddInstructionHelp(JPanel jpInput) {
        Border brInstructionLabel = BorderFactory.createLineBorder(Color.black);


        jlblInstructionHelp = new JLabel();
        jlblInstructionHelp.setFont(fntBlueBoldFont);

        jlblInstructionHelp.setSize((int) (150 * fWidthFactor), (int) (20 * fHeightFactor));
        jlblInstructionHelp.setLocation((int) (310 * fWidthFactor), (int) (10 * fHeightFactor));
        jlblInstructionHelp.setToolTipText("N E X T   S T E P");
        jlblInstructionHelp.setBorder(brInstructionLabel);

        jpInput.add(jlblInstructionHelp);

    }

    final JComboBox jcbSegment = new JComboBox();
    JLabel jlblSegment = new JLabel("Current Segment");

    private void fAddSegment(JPanel jpInput) {
        //jcbSegment = new JComboBox();
        jlblSegment.setSize((int) (100 * fWidthFactor), (int) (20 * fHeightFactor));
        //jlblSegment.setLocation((int)(490*fWidthFactor),(int)(110*fHeightFactor));
        jlblSegment.setLocation((int) (490 * fWidthFactor), (int) (10 * fHeightFactor));
        jlblSegment.setFont(fnt);

        jcbSegment.setSize((int) (100 * fWidthFactor), (int) (20 * fHeightFactor));
        //jcbSegment.setLocation((int) (590 * fWidthFactor), (int) (110 * fHeightFactor));
        jcbSegment.setLocation((int) (590 * fWidthFactor), (int) (10 * fHeightFactor));

        jcbSegment.setFont(fnt);
        jcbSegment.addItem("SEGMENT");

        for (int iLoop = 0; iLoop < 256; iLoop += 2) {
            jcbSegment.addItem("0x" + (new BigInteger(new Integer(iLoop).toString())).toString(16));
            jcbSegment.setActionCommand("0x" + (new BigInteger(new Integer(iLoop).toString())).toString(16));
        }


        jcbSegment.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent ae) {
                                             //System.out.println(jcbSegment.getSelectedItem());
                                             String sSegment = (String) jcbSegment.getSelectedItem();
                                             if (sSegment.equalsIgnoreCase("SEGMENT") == false) {
                                                 sSegment = sSegment.substring(2);
                                                 //System.out.println(sSegment);
                                                 fUpdateHashTable(getPreviousSegment());
                                                 setPreviousSegment("0x" + sSegment);
                                                 fLoadTable(new BigInteger(sSegment, 16));
                                                 fChangeTableSelection();
                                             } else {
                                                 fUpdateHashTable(getPreviousSegment());
                                                 setPreviousSegment("SEGMENT");
                                                 fUnloadTable();
                                             }
                                         }
                                     }

        );


        jpInput.add(jlblSegment);
        jpInput.add(jcbSegment);
    }


    public void fInitialiseRAM(FlashScreen objFlashScreen) {

        Execute obje = new Execute();
        htMemory = new Storage();

        htMnemonics = new Hashtable(65535);


        int iIncrement = 1;


        for (int iLoop = 0; iLoop < 65535; iLoop++) {

            if (objFlashScreen != null && objFlashScreen.fGetLoadingStatus() == true) {
                if (iLoop == (int) ((iIncrement / 100.0f) * 65535.0f)) {

                    objFlashScreen.fSetText("M E M O R Y    I N I T I A L I S A T I O N :  " + new Integer(iIncrement).toString() + "  %   C O M P L E T E");
                    objFlashScreen.fSetValue(iIncrement);
                    objFlashScreen.validate();

                    iIncrement = iIncrement + 1;
                }
            }

            htMemory.putData((new BigInteger("FFFF", 16)), (new BigInteger(new Integer(iLoop).toString())));
            htMnemonics.put((new BigInteger(new Integer(iLoop).toString())), "");
        }
    }

    public void fUpdateHashTable(String sSegment) {
        boolean bValidNumber = false;
        if (!(sSegment.equalsIgnoreCase("SEGMENT") == true)) {
            sSegment = sSegment.substring(2);
            String sAddress = "";
            String sData;
            BigInteger biSegment = new BigInteger(sSegment, 16);
            BigInteger biAddress = BigInteger.ZERO;
            BigInteger biTempValue = BigInteger.ZERO;
            BigInteger biData;
            biTempValue = biSegment;
            biSegment = biSegment.multiply(new BigInteger(new Integer(256).toString()));

            if (dtmModel.getRowCount() == 512) {
                for (int iLoop = 0; iLoop < 512; iLoop++) {
                    sData = (String) dtmModel.getValueAt(iLoop, 2);

                    biAddress = biSegment.add(new BigInteger(new Integer(iLoop).toString()));
                    if (sData == null) {
                        sData = "0";
                    }
                    if (sData.length() > 2) {
                        sData = sData.substring(2);
                    }
                    if (sData.length() > 4) {
                        sData = sData.substring(0, 4);
                        JOptionPane.showMessageDialog(null, "Data ( " + dtmModel.getValueAt(iLoop, 2) + " ) at Memory Location : 0x" + biAddress.toString(16) + " is beyond limit. \n Data Truncated to 4 Digits ", "Data Truncated", JOptionPane.ERROR_MESSAGE);
                    }


                    for (int iLoop1 = 0; iLoop1 < sData.length(); iLoop1++) {
                        if ((sData.charAt(iLoop1) == '0' || sData.charAt(iLoop1) == '1' || sData.charAt(iLoop1) == '2' || sData.charAt(iLoop1) == '3' || sData.charAt(iLoop1) == '4' || sData.charAt(iLoop1) == '5' || sData.charAt(iLoop1) == '6' || sData.charAt(iLoop1) == '7' || sData.charAt(iLoop1) == '8' || sData.charAt(iLoop1) == '9' || sData.charAt(iLoop1) == 'a' || sData.charAt(iLoop1) == 'A' || sData.charAt(iLoop1) == 'b' || sData.charAt(iLoop1) == 'B' || sData.charAt(iLoop1) == 'c' || sData.charAt(iLoop1) == 'C' || sData.charAt(iLoop1) == 'd' || sData.charAt(iLoop1) == 'D' || sData.charAt(iLoop1) == 'e' || sData.charAt(iLoop1) == 'E' || sData.charAt(iLoop1) == 'f' || sData.charAt(iLoop1) == 'F')) {
                            bValidNumber = true;
                        } else {
                            bValidNumber = false;
                            break;
                        }
                    }
                    if (!(biTempValue.intValue() == 254 && iLoop > 510)) {
                        if (bValidNumber == true) {
                            sAddress = (String) dtmModel.getValueAt(iLoop, 0);
                            sAddress = sAddress.substring(2);

                            htMemory.putData(new BigInteger(sData, 16), new BigInteger(sAddress, 16));

                            sData = (String) dtmModel.getValueAt(iLoop, 1);
                            if (sData == null) {
                                sData = "";
                            }

                            htMnemonics.put(new BigInteger(sAddress, 16), sData);
                        } else {
                            sData = "FFFF";
                            JOptionPane.showMessageDialog(null, "Data ( " + (String) dtmModel.getValueAt(iLoop, 2) + " ) at Memory Location 0x" + sAddress + " is not Valid \n Data Set to 0xFFFF", "Invalid Data ", JOptionPane.ERROR_MESSAGE);
                            htMemory.putData(new BigInteger(sData, 16), new BigInteger(sAddress, 16));
                        }
                    }
                }
            }
        }
    }

    private void fUnloadTable() {
        dtmModel.setNumRows(0);
    }

    private void fResetInstructions() {
        for (int iLoop = 0; iLoop < 16; iLoop++) {
            fUpdateBit(iLoop, false);
        }
    }

    private void fShowBitWiseValue(String sValue) {
        BigInteger biValue = new BigInteger(sValue, 16);
        String sBinaryValue = biValue.toString(2);

        int iLoop = 0;
        int iLength = sBinaryValue.length();

        for (iLoop = 15; iLoop >= iLength; iLoop--) {
            sBinaryValue = "0" + sBinaryValue;
        }

        for (iLoop = 0; iLoop <= 15; iLoop++) {
            if (sBinaryValue.charAt(iLoop) == '1') {
                fUpdateBit(15 - iLoop, true);
            } else {
                fUpdateBit(15 - iLoop, false);
            }
        }
    }

    private void fResetConditions() {
        jcbNegative.setSelected(false);
        jcbPositive.setSelected(false);
        jcbZero.setSelected(false);
    }

    private void fDisableSegments() {
        jcbSegment.setEnabled(false);

        jtProgram.setEnabled(false);
        fDisableRunStatus();
    }

    private void fEnableSegments() {
        jcbSegment.setEnabled(true);

        jtProgram.setEnabled(true);
        fEnableRunStatus();
    }

    private BigInteger fExtractOpcode() {
        String sOpcode = "";
        for (int iLoop = 0; iLoop < 16; iLoop++) {
            sOpcode = sOpcode + jlblInstructionBits[iLoop].getText();
        }
        return new BigInteger(sOpcode, 2);
    }

    public void fLoadTable(BigInteger biSegment) {
        BigInteger biAddress;
        String sAddress;
        BigInteger biData;
        BigInteger biTempValue = BigInteger.ZERO;
        String sData = "";
        BigInteger biOffset = new BigInteger(9, new Random());
        jtProgram.setFont(fnt);

        fDisableConditions();
        fDisableNumberPad();
        fDisableRegisters();
        fEnableMnemonics();

        if (!(biSegment.intValue() > 255 && biSegment.intValue() < 0)) {
            iRowPosition = 0;
            dtmModel.setRowCount(0);
            dtmModel.setRowCount(512);

            biTempValue = biSegment;
            biSegment = biSegment.multiply(new BigInteger(new Integer(256).toString()));
            try {
                for (int iLoop = 0; iLoop < 512; iLoop++) {
                    biOffset = new BigInteger(new Integer(iLoop).toString());

                    biAddress = biSegment.add(biOffset);

                    if (!(biTempValue.intValue() == 254 && iLoop > 510)) {
                        BigInteger biTemp = htMemory.getData(biAddress);

                        sData = biTemp.toString(16);

                        sData = sData.toUpperCase();
                        dtmModel.setValueAt("0x" + sData.toUpperCase(), iLoop, 2);
                        dtmModel.setValueAt("0x" + biAddress.toString(16).toUpperCase(), iLoop, 0);

                        dtmModel.setValueAt((String) htMnemonics.get(biAddress), iLoop, 1);
                    }
                }

                fChangeTableSelection();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void fChangeTableSelection() {
        jtProgram.changeSelection(iRowPosition, 0, false, false);

    }

    public void fUpdateTableAfterRun() {

        BigInteger biSegment = BigInteger.ZERO;
        String sSelectedSegment = (String) jcbSegment.getSelectedItem();
        if (!(sSelectedSegment.equalsIgnoreCase("SEGMENT") == true)) {
            sSelectedSegment = sSelectedSegment.substring(2);

            biSegment = new BigInteger(sSelectedSegment, 16);

            fLoadTable(biSegment);
        }
    }

    public void fShowFileInfo(Hashtable htSegmentValue) {
        JTextArea jtaFileInfo = new JTextArea(5, 10);

        JScrollPane jspFileInfo = new JScrollPane();
        jspFileInfo.setViewportView(jtaFileInfo);

        String sChoices[] = new String[1];
        sChoices[0] = "O-K";

        jtaFileInfo.setEditable(false);


        System.out.println(htSegmentValue.size() + "HashTable Keys");
        for (int iLoop = 0; iLoop < htSegmentValue.size(); iLoop++) {
            jtaFileInfo.append("\t" + ((String) (htSegmentValue.get(new Integer(iLoop)))).toUpperCase() + "\n");
        }

        if (htSegmentValue.size() > 0) {
            Object objMessages[] = new Object[2];
            objMessages[0] = "File Successfully Loaded.\nFile has Data in Following Segments";
            objMessages[1] = jspFileInfo;
            ImageIcon iiIcon = new ImageIcon(this.getClass().getClassLoader().getResource("Icons/Information.jpg"));
            JOptionPane jopFileInfo = new JOptionPane(objMessages, JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_OPTION, iiIcon, sChoices, sChoices[0]);
            JDialog jdFileInfo = jopFileInfo.createDialog(null, "F I L E  I N F O");
            jdFileInfo.show();

        } else {
            ImageIcon iiIconData = new ImageIcon(this.getClass().getClassLoader().getResource("Icons/Information.jpg"));
            JOptionPane.showMessageDialog(null, "File Successfully Loaded.\nFile has no Data", "File Loaded", JOptionPane.INFORMATION_MESSAGE, iiIconData);
        }

    }

    public void fOpenFile2HashTable() {

        Hashtable htSegmentValue = new Hashtable(512);
        String sPreviousSegmentValue = "Test";
        String sCurrentSegmentValue = "Trial";
        int iTotalSegments = 0;

        int iSaveStatus = JOptionPane.showConfirmDialog(null, "S A V E  F I L E ", "SAVE FILE", JOptionPane.YES_NO_CANCEL_OPTION);

        if (iSaveStatus == JOptionPane.YES_OPTION) {
            Runnable SavingEvent = new Runnable() {
                public void run() {
                    fSaveHashTable2File();
                }
            };

            Thread SavingThread = new Thread(SavingEvent, "Saving EventThread for Open File");
            SavingThread.start();
        } else if (iSaveStatus == JOptionPane.CANCEL_OPTION) {
            return;
        }

        fInitialiseRAM(null);

        File fileLC2 = null;
        try {
            fileLC2 = new File("abc.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFileChooser jfcSaveFile = new JFileChooser();
        jfcSaveFile.setCurrentDirectory(new File(this.getClass().getClassLoader().getResource("test.LC2").getFile()));

        FileReader in;

        BigInteger biValue = BigInteger.ZERO;
        String sValue = "";
        String sMnemonics = "";
        String sInstruction = "";
        BigInteger biInstruction = BigInteger.ZERO;
        String sFileName = "";

        FileInputStream fis;
        DataInputStream dis = null;

        ExampleFileFilter ffFilter = new ExampleFileFilter(new String("LC2"), "LC-2 FILES");
        jfcSaveFile.setFileFilter(ffFilter);
        int iState = jfcSaveFile.showOpenDialog(null);

        if (iState == JFileChooser.APPROVE_OPTION) {
            fileLC2 = jfcSaveFile.getSelectedFile();

            sFileName = fileLC2.getName();

            if (!(sFileName.indexOf(".LC2", sFileName.length() - 4) >= 0 || sFileName.indexOf(".lc2", sFileName.length() - 4) >= 0)) {
                JOptionPane.showMessageDialog(null, "( " + sFileName + " ) NOT A VALID FILE !!", "INVALID FILENAME", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                if (fileLC2.exists() == false) {
                    JOptionPane.showMessageDialog(null, "FILE ( " + fileLC2.getName() + " ) DOESNOT EXIST !!", "INVALID FILENAME", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            try {
                fis = new FileInputStream(fileLC2);
                dis = new DataInputStream(fis);

                while (true) {
                    sValue = dis.readUTF();
                    sMnemonics = dis.readUTF();
                    sInstruction = dis.readUTF();

                    if (sValue.length() == 1) {
                        sValue = "000" + sValue;
                    } else if (sValue.length() == 2) {
                        sValue = "00" + sValue;
                    } else if (sValue.length() == 3) {
                        sValue = "0" + sValue;
                    }

                    sPreviousSegmentValue = sCurrentSegmentValue;
                    sCurrentSegmentValue = sValue.substring(0, 2);

                    if (sPreviousSegmentValue.equalsIgnoreCase(sCurrentSegmentValue) == false) {
                        htSegmentValue.put(new Integer(iTotalSegments), sCurrentSegmentValue);
                        iTotalSegments = iTotalSegments + 1;
                    }

                    if (sMnemonics.equalsIgnoreCase("NULL") == true) {
                        sMnemonics = "";
                    }
                    htMemory.putData(new BigInteger(sInstruction, 16), new BigInteger(sValue, 16));
                    htMnemonics.put(new BigInteger(sValue, 16), sMnemonics);
                }
            } catch (EOFException eofe) {
                System.out.println("EOF Exception");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "UNABLE TO OPEN FILE", "UNABLE TO OPEN FILE !!", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    dis.close();
                } catch (Exception e) {
                }
            }
            fShowFileInfo(htSegmentValue);
        }

    }


    public void fSaveHashTable2File() {
        JFileChooser jfcSaveFile = new JFileChooser();
        ExampleFileFilter ffFilter = new ExampleFileFilter(new String("LC2"), "LC-2 FILES");
        jfcSaveFile.setFileFilter(ffFilter);


        File fileLC2 = null;
        try {
            fileLC2 = new File("abc.txt");
        } catch (Exception e) {
        }

        jfcSaveFile.setCurrentDirectory(fileLC2);
        int iState = jfcSaveFile.showSaveDialog(null);

        fileLC2 = null;
        DataOutputStream dos;

        BigInteger biValue = BigInteger.ZERO;
        String sMnemonics = "";
        String sInstruction = "";
        BigInteger biInstruction = BigInteger.ZERO;
        FileOutputStream fos;

        if (iState == JFileChooser.APPROVE_OPTION) {

            fileLC2 = jfcSaveFile.getSelectedFile();
            String sFileName = fileLC2.getAbsolutePath();

            if (sFileName.indexOf(".LC2", sFileName.length() - 4) >= 0 || sFileName.indexOf(".lc2", sFileName.length() - 4) >= 0) {
                fileLC2 = new File(sFileName);
            } else {
                fileLC2 = new File(sFileName + ".LC2");
            }

            if (fileLC2.exists() == true) {
                int iMessageOption = JOptionPane.showConfirmDialog(null, "FILE ( " + fileLC2.getName() + " ) ALREADY EXISTS !! OVERWRITE ??", "FILE EXISTS", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (iMessageOption == JOptionPane.YES_OPTION) {
                    if (fileLC2.canWrite() == false) {
                        JOptionPane.showMessageDialog(null, "READ ONLY FILE ( " + fileLC2.getName() + " ) !!\n TRY SAVING WITH DIFFERENT FILE NAME", "READ ONLY ", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else if (iMessageOption == JOptionPane.NO_OPTION) {
                    return;
                } else {
                    return;
                }
            }
            try {
                fos = new FileOutputStream(fileLC2);
                dos = new DataOutputStream(fos);
                final ProgressBar objProgressBar = new ProgressBar("File Save Status", 100, fWidthFactor, fHeightFactor);
                objProgressBar.show();
                objProgressBar.validate();

                int iXPosition = (int) (fWidthFactor * 800);
                int iYPosition = (int) (fHeightFactor * 600);

                int iWidthStart = (int) (200 * fWidthFactor);
                int iHeightStart = (int) (40 * fHeightFactor);

                iWidthStart = ((int) (iXPosition / 2.0f)) - ((int) (iWidthStart / 2.0f));
                iHeightStart = ((int) (iYPosition / 2.0f)) - ((int) (iHeightStart / 2.0f));

                objProgressBar.setBounds((int) (iWidthStart), (int) (iHeightStart), (int) (200 * fWidthFactor), (int) (25 * fHeightFactor));

                int iIncrement = 1;
                for (int iLoop = 0; iLoop < 65535; iLoop++) {
                    biValue = new BigInteger(new Integer(iLoop).toString());
                    sMnemonics = (String) htMnemonics.get(biValue);

                    if (iLoop == (int) ((iIncrement / 100.0f) * 65535.0f)) {
                        try {
                            objProgressBar.iValue = iIncrement;
                            objProgressBar.sProgressText = "File Save Status : " + new Integer(iIncrement).toString() + " % Complete";

                            Runnable setValueAtProgressBar = new Runnable() {
                                public void run() {
                                    objProgressBar.fSetValue();
                                    objProgressBar.validate();
                                }
                            };

                            SwingUtilities.invokeLater(setValueAtProgressBar);

                        } catch (Exception e) {
                            System.out.println("SWING UTILITY EXCEPTION");
                        }

                        iIncrement = iIncrement + 1;
                    }

                    if (sMnemonics == null) {
                        sMnemonics = "NULL";
                    }

                    biInstruction = (BigInteger) htMemory.getData(biValue);
                    sInstruction = biInstruction.toString(16);

                    if (sMnemonics.length() > 0 || (!(biInstruction.toString(16).equalsIgnoreCase("FFFF") == true))) {
                        dos.writeUTF(biValue.toString(16));
                        dos.writeUTF(sMnemonics);
                        dos.writeUTF(sInstruction);
                    }
                }

                objProgressBar.setVisible(false);
                objProgressBar.hide();
                dos.close();
                ImageIcon iiIcon = new ImageIcon(this.getClass().getClassLoader().getResource("Icons/Information.jpg"));
                JOptionPane.showMessageDialog(null, "FILE ( " + fileLC2.getName() + " ) SAVED", "FILE SUCCESSFULLY SAVED", JOptionPane.INFORMATION_MESSAGE, iiIcon);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "FILE ( " + fileLC2.getName() + " ) NOT SAVED", "FILE NOT SAVED !!", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    public void fDelay() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * getPreviousSegment is used to get the value of the Previous Segment that was active<br>
     *
     * @ return sPreviousSegment of the screen (String) <BR>
     */
    public String getPreviousSegment() {
        return sPreviousSegment;
    }

    /**
     * setPreviousSegment is used to set the value of the Previous Segment that was active<br>
     *
     * @ param sPreviousSegment of the screen (String) <BR>
     */
    public void setPreviousSegment(String sPreviousSegment) {
        this.sPreviousSegment = sPreviousSegment;
    }
}
