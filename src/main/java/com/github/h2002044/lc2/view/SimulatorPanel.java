package com.github.h2002044.lc2.view;

import com.github.h2002044.lc2.OutputSummary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * SimulatorPanel class is used to create GUI for Registers, Execution Summary, Simulation.<br>
 */
public class SimulatorPanel extends JPanel implements ActionListener {

    public Circuit objSimulate;
    private JPanel jpResize;
    private JButton jbMax;
    private JButton jbMin;
    private JScrollPane jspSimulate;
    private JScrollPane jspContainer;

    public RegistersPanel objRegisters;
    private Font fntButtons;

    public DataFlow objDataFlow;
    public OutputSummary objOutputSummary;

    public FlowSummary objExecutionSummary;

    public float fWidthFactor;
    public float fHeightFactor;

    /**
     * fSetDimension() is used to find out the Width, Height of the screen, so as to set the resolution factor<br>
     */
    public void fSetDimension() {
        int iWidth;
        int iHeight;

        Dimension dmnScreen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        iWidth = dmnScreen.width;
        iHeight = dmnScreen.height;

        fWidthFactor = ((float) iWidth / 800);
        fHeightFactor = ((float) iHeight / 600);
    }

    public SimulatorPanel() {
        fSetDimension();
        setLayout(null);
        objSimulate = new Circuit();


        fntButtons = new Font("ARIAL", Font.PLAIN, (int) (10 * fHeightFactor));

        jspContainer = new JScrollPane();
        jspContainer.setSize((int) (525 * fWidthFactor), (int) (375 * fHeightFactor));
        jspContainer.setLocation((int) (0 * fWidthFactor), (int) (22 * fHeightFactor));

        jspSimulate = new JScrollPane();

        jspSimulate.setViewportView(objSimulate);

        jspContainer.setViewportView(jspSimulate);

        objOutputSummary = new OutputSummary();
        jpResize = new JPanel();
        jpResize.setSize((int) (120 * fWidthFactor), (int) (20 * fHeightFactor));
        jpResize.setLocation((int) (0 * fWidthFactor), (int) (0 * fHeightFactor));
        jpResize.setLayout(null);

        jbMax = new JButton("+");
        jbMax.setActionCommand("+");
        jbMax.setToolTipText("M A X I M I S E");
        jbMax.setSize((int) (45 * fWidthFactor), (int) (20 * fHeightFactor));
        jbMax.setLocation((int) (0 * fWidthFactor), (int) (0 * fHeightFactor));
        jbMax.addActionListener(this);
        jpResize.add(jbMax);


        jbMin = new JButton("-");
        jbMin.setActionCommand("-");
        jbMin.setToolTipText("M I N I M I S E");
        jbMin.setSize((int) (45 * fWidthFactor), (int) (20 * fHeightFactor));
        jbMin.setLocation((int) (55 * fWidthFactor), (int) (0 * fHeightFactor));
        jbMin.addActionListener(this);
        jpResize.add(jbMin);

        objRegisters = new RegistersPanel(fWidthFactor, fHeightFactor, fntButtons);

        objRegisters.setSize((int) (500 * fWidthFactor), (int) (125 * fHeightFactor));
        objRegisters.setLocation((int) (10 * fWidthFactor), (int) (400 * fHeightFactor));


        objExecutionSummary = new FlowSummary(fWidthFactor, fHeightFactor);
        objExecutionSummary.setSize((int) (250 * fWidthFactor), (int) (480 * fHeightFactor));
        objExecutionSummary.setLocation((int) (530 * fWidthFactor), (int) (25 * fHeightFactor));

        objDataFlow = new DataFlow(objSimulate, objExecutionSummary);

        add(objExecutionSummary);

        add(jpResize);
        add(jspContainer);
        add(objRegisters);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equalsIgnoreCase("+") == true) {
            objSimulate.fDrawMaximized();
            jspSimulate.setSize((int) (fWidthFactor * 800), (int) (fHeightFactor * 600));
            jspSimulate.validate();
            jspContainer.validate();
            validate();
        } else if (ae.getActionCommand().equalsIgnoreCase("-") == true) {
            objSimulate.fDrawMinimized();
            jspSimulate.setSize((int) (fWidthFactor * 800), (int) (fHeightFactor * 600));
            jspSimulate.validate();
            jspContainer.validate();
            validate();
        }
    }
}

