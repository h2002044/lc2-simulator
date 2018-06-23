
package com.github.h2002044.lc2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.*;
import java.awt.*;
import java.math.BigInteger;
import java.util.Random;
/**
 * @ toDo
 *
 * 1) Action Listener implementation
 * 2) Check Mouse Event
 */
public class ProgramPad extends JScrollPane
{
    private JTable jtProgram;

    private String strColumnNames[] = {"Address", "Mnemonic", "Opcode/Data"};
    private DefaultTableModel dtmModel = new DefaultTableModel(strColumnNames, 0);

    private int iRowCount = 0;
    private int iMemoryAddress = 0;
    private int iRowPosition = 0;
    private String sInstruction = null;

    public ProgramPad(float fWidthFactor, float fHeightFactor, Font fnt)
    {
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
                new MouseAdapter()
                {

                    public void mouseReleased(MouseEvent e)
                    {
                        iRowPosition = jtProgram.getSelectedRow();

                        String sColumnValue = (String) jtProgram.getValueAt(iRowPosition, 2);

                        sColumnValue = sColumnValue.substring(2);

                        if (sColumnValue.length() > 0)
                        {
                            /*COMMENTED*/
                            //fShowBitWiseValue(sColumnValue);
                            //Observer
                        }
                        else
                        {
                            /*COMMENTED*/
                            //fResetInstructions();
                            //Observer
                        }


                        if (KeyEvent.KEY_PRESSED == KeyEvent.VK_CONTROL)
                        {
                            String sValue = (String) jtProgram.getValueAt(iRowPosition, 2);
                            String sNewValue = "0000";

                            if (sValue.length() > 2)
                            {
                                sNewValue = fValidateHexValue(sValue);
                            }
                            else
                            {
                                sNewValue = fValidateHexValue("0");
                            }

                            jtProgram.setValueAt(sNewValue, iRowPosition, 2);

                        }
                    }
                }
        );

        jtProgram.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                if ((jtProgram.getSelectedColumn() == 2 && jtProgram.getSelectedRow() == iRowCount))
                {
                    if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER)
                    {
                        iRowCount = iRowCount + 1;

                        String strData[] = {new Integer(iMemoryAddress + jtProgram.getSelectedRow() + 1).toString(), "", ""};
                        dtmModel.insertRow(iRowCount, strData);

                        dtmModel.fireTableDataChanged();

                        jtProgram.validate();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_HOME)
                {
                    for (int iLoop = 0; iLoop < jtProgram.getRowCount(); iLoop++)
                    {
                        System.out.print(jtProgram.getValueAt(iLoop, 0) + "\t");
                        System.out.print(jtProgram.getValueAt(iLoop, 1) + "\t");
                        System.out.println(jtProgram.getValueAt(iLoop, 2));
                    }
                }
            }
        }
        );

        setViewportView(jtProgram);
    }


    private void fSetInstructionColumn(TableColumn tcInstructionColumn)
    {
        InstructionColumnCell objInstructionCell = new InstructionColumnCell(8);
        tcInstructionColumn.setCellEditor(new DefaultCellEditor(objInstructionCell));

        DefaultTableCellRenderer dtcrRenderer =
                new DefaultTableCellRenderer();

        tcInstructionColumn.setCellRenderer(dtcrRenderer);
    }


    private String fValidateHexValue(String sPreviousValue)
    {

        String sNewValue = JOptionPane.showInputDialog(null, "Enter 4 digit Hex Value", "Value", JOptionPane.PLAIN_MESSAGE);
        if (sNewValue.length() > 0)
        {
            sNewValue = sNewValue.toUpperCase();
        }

        if (sNewValue.length() > 4)
        {

            JOptionPane.showMessageDialog(null, "Enter 4 Digit Hex Value !! ", "I N V A L I D  D A T A", JOptionPane.ERROR_MESSAGE);
            return sPreviousValue;
        }
        else
        {
            if (sNewValue.length() <= 0)
            {
                JOptionPane.showMessageDialog(null, "Enter 4 Digit Hex Value !! ", "I N V A L I D  D A T A", JOptionPane.ERROR_MESSAGE);
                return sPreviousValue;
            }
            else
            {
                for (int iLoop = 0; iLoop < sNewValue.length(); iLoop++)
                {
                    if (sNewValue.charAt(iLoop) == 'A' || sNewValue.charAt(iLoop) == 'B' || sNewValue.charAt(iLoop) == 'C' || sNewValue.charAt(iLoop) == 'D' || sNewValue.charAt(iLoop) == 'E' || sNewValue.charAt(iLoop) == 'F' || sNewValue.charAt(iLoop) == '1' || sNewValue.charAt(iLoop) == '2' || sNewValue.charAt(iLoop) == '3' || sNewValue.charAt(iLoop) == '4' || sNewValue.charAt(iLoop) == '5' || sNewValue.charAt(iLoop) == '6' || sNewValue.charAt(iLoop) == '7' || sNewValue.charAt(iLoop) == '8' || sNewValue.charAt(iLoop) == '9' || sNewValue.charAt(iLoop) == '0')
                    {

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Enter 4 Digit Hex Value !! ", "I N V A L I D  D A T A", JOptionPane.ERROR_MESSAGE);
                        return sPreviousValue;
                    }

                }
                return "0x" + sNewValue;
            }
        }
    }
/**
 * @ toDo
 * 1) Call the commented four functions in editor function
 * 2) Send fnt as Parameter
 * 3) Send htMemory, htMnemonics
 *
 * @param biSegment
 */
    public void fLoadTable(BigInteger biSegment)
    {
        BigInteger biAddress;
        String sAddress;
        BigInteger biData;
        BigInteger biTempValue = BigInteger.ZERO;
        String sData = "";
        BigInteger biOffset = new BigInteger(9, new Random());

/*COMMENTED
        jtProgram.setFont(fnt);

        fDisableConditions();
        fDisableNumberPad();
        fDisableRegisters();
        fEnableMnemonics();
*/

        if (!(biSegment.intValue() > 255 && biSegment.intValue() < 0))
        {
            iRowPosition = 0;
            dtmModel.setRowCount(0);
            dtmModel.setRowCount(512);

            biTempValue = biSegment;
            biSegment = biSegment.multiply(new BigInteger(new Integer(256).toString()));
            try
            {
                for (int iLoop = 0; iLoop < 512; iLoop++)
                {
                    biOffset = new BigInteger(new Integer(iLoop).toString());

                    biAddress = biSegment.add(biOffset);

                    if (!(biTempValue.intValue() == 254 && iLoop > 510))
                    {
//COMMENTED                        BigInteger biTemp = htMemory.getData(biAddress);

//COMMENTED                        sData = biTemp.toString(16);

                        sData = sData.toUpperCase();
                        dtmModel.setValueAt("0x" + sData.toUpperCase(), iLoop, 2);
                        dtmModel.setValueAt("0x" + biAddress.toString(16).toUpperCase(), iLoop, 0);

//COMMENTED                        dtmModel.setValueAt((String) htMnemonics.get(biAddress), iLoop, 1);
                    }
                }

                fChangeTableSelection();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
    private void fChangeTableSelection()
    {
        jtProgram.changeSelection(iRowPosition, 0, false, false);

    }
    private void fSetMnemonicsAndAddressColumn(TableColumn tcColumn)
    {
        DisabledColumnCell objDisabledCell = new DisabledColumnCell();

        tcColumn.setCellEditor(new DefaultCellEditor(objDisabledCell));

        DefaultTableCellRenderer dtcrRenderer =
                new DefaultTableCellRenderer();

        tcColumn.setCellRenderer(dtcrRenderer);

    }
}
