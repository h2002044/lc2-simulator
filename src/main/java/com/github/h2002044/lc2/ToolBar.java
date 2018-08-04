package com.github.h2002044.lc2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

/**
 * Class ToolBar is used to set the tool bar.<br>
 * The toolbar has the options File Open/New/Save options, Help Tutor/Context options, Execute Run/Debug options<br>
 */

public class ToolBar extends JToolBar implements ActionListener {

    private JButton jbNew;              //New Button
    private JButton jbOpen;             //Open Button
    private JButton jbSave;
    private JButton jbExecute;
    private JButton jbStep;
    private JButton jbOutputSummary;
    private JButton jbStop;
    private Editor objEditor;
    private Execute objExe;
    private SimulatorPanel objExecute;

    /**
     * Constructor for the class ToolBar, which internally calls fSetToolBar()<br>
     */
    public ToolBar(float widthFactor, float heightFactor, Editor editor, Execute objExe, SimulatorPanel objExecute) {
        fSetToolBar(widthFactor, heightFactor);
        this.objEditor = editor;
        this.objExe = objExe;
        this.objExecute = objExecute;
    }

    /**
     * fDisableRun is used to disable the button "RUN" in the toolbar.<br>
     */
    public void fDisableRun() {
        jbExecute.setEnabled(false);
    }

    /**
     * fEnableRun is used to enable the button "RUN" in the toolbar.<br>
     */
    public void fEnableRun() {
        jbExecute.setEnabled(true);
    }


    /**
     * fDisableStep is used to disable the button "STEP" in the toolbar.<br>
     */
    public void fDisableStep() {
        jbStep.setEnabled(false);
    }

    /**
     * fEnableStep is used to enable the button "STEP" in the toolbar.<br>
     */
    public void fEnableStep() {
        jbStep.setEnabled(true);
    }

    /**
     * fEnableStop is used to enable the button "STOP" in the toolbar.<br>
     */
    public void fDisableStop() {
        jbStop.setEnabled(false);
    }


    /**
     * fDisableStop is used to disable the button "STOP" in the toolbar.<br>
     */
    public void fEnableStop() {
        jbStop.setEnabled(true);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == jbNew) {
            int iSaveStatus = JOptionPane.showConfirmDialog(null, "S A V E  F I L E ", "SAVE FILE", JOptionPane.YES_NO_CANCEL_OPTION);
            if (iSaveStatus == JOptionPane.YES_OPTION) {
                Runnable SavingEvent = new Runnable() {
                    public void run() {
                        objEditor.fSaveHashTable2File();
                    }
                };

                Thread SavingThread = new Thread(SavingEvent, "Saving EventThread for New File");
                SavingThread.start();
            } else if (iSaveStatus == JOptionPane.CANCEL_OPTION) {
                return;
            }

            objEditor.fInitialiseRAM(null);
            String sSegment = (String) objEditor.jcbSegment.getSelectedItem();
            if (sSegment.equalsIgnoreCase("SEGMENT") == false) {
                sSegment = sSegment.substring(2);
                objEditor.fLoadTable(new BigInteger(sSegment, 16));
            }
        } else if (source == jbOpen) {
            String sSegment = (String) objEditor.jcbSegment.getSelectedItem();

            if (!(sSegment.equalsIgnoreCase("SEGMENT") == true)) {
                objEditor.fUpdateHashTable(sSegment);
            }

            objEditor.fOpenFile2HashTable();

            sSegment = (String) objEditor.jcbSegment.getSelectedItem();
            objEditor.fEnableRunStatus();
            if (sSegment.equalsIgnoreCase("SEGMENT") == false) {
                sSegment = sSegment.substring(2);
                objEditor.fLoadTable(new BigInteger(sSegment, 16));
            }
        } else if (source == jbSave) {
            String sSegment = (String) objEditor.jcbSegment.getSelectedItem();

            if (!(sSegment.equalsIgnoreCase("SEGMENT") == true)) {
                objEditor.fUpdateHashTable(sSegment);
            }

            Runnable SavingEvent = new Runnable() {
                public void run() {
                    objEditor.fSaveHashTable2File();
                }
            };

            Thread SavingThread = new Thread(SavingEvent, "Saving EventThread for Save File");
            SavingThread.start();
        } else if (source == jbStep) {
            objExe.fSetMode("STEP");
            objExe.fNotify();
        } else if (source == jbStop) {
            objExe.fSetMode("STOP");
            objExe.fNotify();
        } else if (source == jbExecute) {
            objExe.fSetMode("RUN");
            objExe.fNotify();
        } else if (source == jbOutputSummary) {
            objExecute.objOutputSummary.showOutputSummary();
        }


    }

    /**
     * fSetToolBar is used to initialise and set the toolbar with buttons<br>
     */
    private void fSetToolBar(float fWidthFactor, float fHeightFactor) {
        setOrientation(JToolBar.VERTICAL);

        addSeparator(new Dimension((int) (10 * fWidthFactor), (int) (100 * fHeightFactor)));

        ImageIcon imgNew = new ImageIcon(this.getClass().getClassLoader().getResource("Icons/NEW.gif"));
        jbNew = new JButton(imgNew);
        jbNew.setToolTipText("N E W");
        add(jbNew);

        jbNew.addActionListener(this);


        addSeparator(new Dimension((int) (10 * fWidthFactor), (int) (10 * fHeightFactor)));


        ImageIcon imgOpen = new ImageIcon(this.getClass().getClassLoader().getResource("Icons/OPEN.gif"));
        jbOpen = new JButton(imgOpen);
        jbOpen.setToolTipText("O P E N");
        add(jbOpen);
        jbOpen.addActionListener(this);


        addSeparator(new Dimension((int) (10 * fWidthFactor), (int) (10 * fHeightFactor)));
        ImageIcon imgSave = new ImageIcon(this.getClass().getClassLoader().getResource("Icons/SAVE.gif"));
        jbSave = new JButton(imgSave);
        jbSave.setToolTipText("S A V E");
        add(jbSave);

        jbSave.addActionListener(this);

        addSeparator(new Dimension((int) (10 * fWidthFactor), (int) (10 * fHeightFactor)));
        ImageIcon imgExecute = new ImageIcon(this.getClass().getClassLoader().getResource("Icons/Exe.gif"));
        jbExecute = new JButton(imgExecute);
        jbExecute.setToolTipText("R U N");
        jbExecute.addActionListener(this);
        add(jbExecute);

        addSeparator(new Dimension((int) (10 * fWidthFactor), (int) (10 * fHeightFactor)));
        ImageIcon imgStep = new ImageIcon(this.getClass().getClassLoader().getResource("Icons/STEP.gif"));
        jbStep = new JButton(imgStep);
        jbStep.setToolTipText("S T E P");
        add(jbStep);

        jbStep.addActionListener(this);

        addSeparator(new Dimension((int) (10 * fWidthFactor), (int) (10 * fHeightFactor)));
        ImageIcon imgStop = new ImageIcon(this.getClass().getClassLoader().getResource("Icons/STOP.gif"));
        jbStop = new JButton(imgStop);
        jbStop.setToolTipText("S T O P");
        add(jbStop);
        jbStop.addActionListener(this);


        addSeparator(new Dimension((int) (10 * fWidthFactor), (int) (10 * fHeightFactor)));
        ImageIcon imgOutputSummary = new ImageIcon(this.getClass().getClassLoader().getResource("Icons/EDIT.gif"));
        jbOutputSummary = new JButton(imgOutputSummary);
        jbOutputSummary.setToolTipText("O U T P U T    S U M M A R Y");
        add(jbOutputSummary);
        jbOutputSummary.addActionListener(this);

        addSeparator(new Dimension((int) (10 * fWidthFactor), (int) (10 * fHeightFactor)));
    }

    public Execute getObjExe() {
        return objExe;
    }

    public void setObjExe(Execute objExe) {
        this.objExe = objExe;
    }
}
