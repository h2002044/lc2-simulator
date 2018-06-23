package com.github.h2002044.lc2;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

/**
 *  Input Class is used to initialise the GUI for LC-2 Simulator.<br>
 *  The GUI has 2 parts, Editor Pane, Simulator Pane.<br>
 *  Editor Window is used to make LC-2 Programs, Options like Saving, Editing, Opening, Creating New files are available.<br>
 *  Simulator Window shows the run time simulation of Data Flow for every instruction involved in the program.<br>
 */

public class Input extends JFrame
{
    Container contentpane;
    JTabbedPane jtpMain;
    JTabbedPane jtpSimulate;
    Editor objEditor;

    private static boolean bRun = false;
    static boolean bRunning = false;
    boolean bWindowClosing = false;
    int iOption = -1;

    SimulatorPanel objExecute;
    // Storage objStorage;

    float fWidthFactor;
    float fHeightFactor;

    private int iScreenWidth;
    private int iScreenHeight;

    ToolBar jtbEditor;
    FlashScreen objFlashScreen;

    Font fnt;
    Font fntBlueBoldFont;



    /**
     *  Input Class is used to initialise the GUI for LC-2 Simulator.<br>
     *  The GUI has 2 parts, Editor Pane, Simulator Pane.<br>
     *  Editor Window is used to make LC-2 Programs, Options like Saving, Editing, Opening, Creating New files are available.<br>
     *  Simulator Window shows the run time simulation of Data Flow for every instruction involved in the program.<br>
     */

    public Input()
    {
        setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("Icons/Icon.gif")).getImage());
        setTitle(" L C -- 2  S I M U L A T O R   [ B I T S - P I L A N I ]");
        getContentPane().setLayout(new BorderLayout());

        fSetDimension();

        objFlashScreen = new FlashScreen("INITIALISING . . . .", 100, fHeightFactor);
        objFlashScreen.setSize(350,300);


        int iXPosition = (int) (fWidthFactor * 800);
        int iYPosition = (int) (fHeightFactor * 600);

        int iWidthStart = (int) (350);
        int iHeightStart = (int) (300);

        iWidthStart = ((int) (iXPosition / 2.0f)) - ((int) (iWidthStart / 2.0f));
        iHeightStart = ((int) (iYPosition / 2.0f)) - ((int) (iHeightStart / 2.0f));

        objFlashScreen.setBounds((int) (iWidthStart), (int) (iHeightStart), (int) (350), (int) (300));
        objFlashScreen.setVisible(true);
        objFlashScreen.show();
        objFlashScreen.validate();

        fDelay();

        jtpMain = new JTabbedPane();

        objFlashScreen.fSetText("I N I T I A L I S I N G     E D I T O R  . . .");
        fDelay();
        objExecute = new SimulatorPanel();
        objEditor = new Editor( jtpMain, objExecute,  objFlashScreen, fWidthFactor, fHeightFactor, fnt, fntBlueBoldFont);


        objFlashScreen.fSetText("I N I T I A L I S I N G     T O O L B A R . . .");

        fDelay();
        
        jtbEditor = new ToolBar(fWidthFactor, fHeightFactor, objEditor, null, objExecute);
        objEditor.setToolBar(jtbEditor);
        jtbEditor.fDisableRun();
        jtbEditor.fDisableStep();
        jtbEditor.fDisableStop();

        jtbEditor.setSize((int) (50 * fWidthFactor), (int) (300 * fHeightFactor));
        jtbEditor.setLocation((int) (10 * fWidthFactor), (int) (35 * fHeightFactor));
        getContentPane().add(jtbEditor, BorderLayout.WEST);

        jtpMain.addTab("C O D E - - E D I T O R", objEditor);
        jtpMain.setTabPlacement(1);


        jtpMain.addChangeListener(
                new TabbedPaneChangeListener());


        objFlashScreen.fSetText("I N I T I A L I S I N G    S I M U L A T O R . . .");
        fDelay();

        jtpMain.addTab("S I M U L A T O R", objExecute);

        getContentPane().add(jtpMain, BorderLayout.CENTER);

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new MyWindowAdapter());

        if (objFlashScreen.fGetLoadingStatus() == true)
        {
            objFlashScreen.setVisible(false);
            objFlashScreen.hide();
            objFlashScreen.fResetLoadingStatus();
        }
        objEditor.fEnableRunStatus();
    }

    /**
     *  fDelay is used to give delay while displaying Flash Screen.<br>
     *  The Flash Screen displays the components that are getting initialised sequentially<br>
     */

    public void fDelay()
    {
        try
        {
            Thread.sleep(100);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     *  processWindowEvent is used to handle the events that are generated on Window.<br>
     *  This function will be executed by the AWT Event Thread<br>
     */

    protected void processWindowEvent(WindowEvent e)
    {
        if (bWindowClosing == true)
        {
            bWindowClosing = false;

            if (iOption == JOptionPane.YES_OPTION)
            {
                setVisible(false);
                System.out.println("YES OPTION");
                System.exit(0);
            }
            else if (iOption == JOptionPane.NO_OPTION)
            {
                setVisible(false);
                System.out.println("NO OPTION");
                System.exit(0);
            }
            else if (iOption == JOptionPane.CANCEL_OPTION)
            {
                iOption = -1;
                System.out.println("CANCEL OPTION");
                return;
            }


        }

        super.processWindowEvent(e);
    }


    /**
     *  fSetMenuBar() is used to set the menu.
     *  This menubar has File Open/New/Save options, Help Tutor/Context options, Execute Run/Debug options
     */

    private JMenuBar fSetMenuBar()
    {
        JMenuBar jmbEditor = new JMenuBar();

        JMenu jmFile = new JMenu("File");
        jmFile.setFont(fnt);
        jmFile.setMnemonic('F');

        JMenuItem jmiNew = new JMenuItem("New");
        jmiNew.setMnemonic('N');
        jmFile.add(jmiNew);

        JMenuItem jmiOpen = new JMenuItem("Open");
        jmiNew.setMnemonic('O');
        jmFile.add(jmiOpen);

        JMenuItem jmiSave = new JMenuItem("Save");
        jmiNew.setMnemonic('S');
        jmFile.add(jmiSave);

        jmFile.addSeparator();

        JMenuItem jmiExit = new JMenuItem("Exit");
        jmiNew.setMnemonic('X');
        jmFile.add(jmiExit);

        jmbEditor.add(jmFile);

        JMenu jmProgram = new JMenu("Program");
        jmFile.setMnemonic('P');

        JMenuItem jmiExecute = new JMenuItem("Execute");
        jmiNew.setMnemonic('E');
        jmProgram.add(jmiExecute);

        JMenuItem jmiStep = new JMenuItem("Step");
        jmiNew.setMnemonic('S');
        jmProgram.add(jmiStep);

        JMenuItem jmiStop = new JMenuItem("Stop");
        jmiNew.setMnemonic('P');
        jmProgram.add(jmiStop);

        jmbEditor.add(jmProgram);

        JMenu jmHelp = new JMenu("Help");
        jmHelp.setMnemonic('H');

        JMenuItem jmiTutor = new JMenuItem("Tutor");
        jmiNew.setMnemonic('T');
        jmHelp.add(jmiTutor);

        JMenuItem jmiAbout = new JMenuItem("About");
        jmiAbout.setMnemonic('A');
        jmHelp.add(jmiAbout);

        JMenuItem jmiConfiguration = new JMenuItem("Configuration");
        jmiConfiguration.setMnemonic('C');
        jmHelp.add(jmiConfiguration);

        jmbEditor.add(jmHelp);

        return (jmbEditor);

    }

    /**
     *  fSetDimension is used to find out the screen widht/height and resolution so as to arrange the GUI components properly<br>
     */
    public void fSetDimension()
    {

        Dimension dmnScreen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        setScreenWidth(dmnScreen.width);
        setScreenHeight(dmnScreen.height);

        fWidthFactor = ((float) getScreenWidth() / 800);
        fHeightFactor = ((float) getScreenHeight() / 600);

        fnt = new Font("ARIAL", Font.PLAIN, (int) (10 * fHeightFactor));
        fntBlueBoldFont = new Font("ARIAL", Font.BOLD, (int) (10 * fHeightFactor));


        System.out.println("Width Factor : " + fWidthFactor);
        System.out.println("Height Factor : " + fHeightFactor);
    }

    /**
     *  fInitialiseInput is used to initialise the components for EDITOR<br>
     */
    public void fInitialiseInput()
    {
        objEditor = new Editor(jtpMain, objExecute, objFlashScreen, fWidthFactor, fHeightFactor, fnt,fntBlueBoldFont);


        jtpMain.addTab("C O D E -- E D I T O R", objEditor);
        jtpMain.validate();
    }


    /**
     *  isRun is a STATIC function which returns a boolean value indicating whether the program is under execution or not<br>
     *  @ return status of the program running or not running.<br>
     */
    public static boolean isRun()
    {
        return bRun;
    }

    /**
     *  fSetRun is a STATIC function which is used to set the running status of the program to false/true<br>
     *  @ param Status of the program running/not running.<br>
     */
    public static void setRun(boolean bRun)
    {
        Input.bRun = bRun;
    }

    /**
     *  getScreenWidth is used to return the width of the screen<br>
     *  @ return width of the screen (int)<br>
     */
    public int getScreenWidth()
    {
        return iScreenWidth;
    }

    /**
     *  setScreenWidth is used to set the width of the screen<br>
     *  @ param width of the screen (int) <BR>
     */
    public void setScreenWidth(int iScreenWidth)
    {
        this.iScreenWidth = iScreenWidth;
    }

    /**
     *  getScreenHeight is used to return the height of the screen<br>
     *  @ return height of the screen (int)<br>
     */
    public int getScreenHeight()
    {
        return iScreenHeight;
    }

    /**
     *  setScreenHeight is used to set the Height of the screen<br>
     *  @ param Height of the screen (int) <BR>
     */
    public void setScreenHeight(int iScreenHeight)
    {
        this.iScreenHeight = iScreenHeight;
    }



    private class TabbedPaneChangeListener implements ChangeListener
    {
        public void stateChanged(ChangeEvent ce)
        {
            if (isRun() == true)
            {
                int iIndex = jtpMain.getSelectedIndex();
                if (iIndex == 0)
                {
                    jtpMain.setSelectedIndex(1);
                }
            }
            else
            {
                if (bRunning == true)
                {
                    objEditor.fUpdateTableAfterRun();
                    bRunning = false;
                    jtbEditor.fDisableRun();
                    jtbEditor.fDisableStep();
                    jtbEditor.fDisableStop();
                }
            }
        }
    }

    private class MyWindowAdapter extends WindowAdapter
    {
        public void windowClosing(WindowEvent we)
        {
            int iSaveStatus = JOptionPane.showConfirmDialog(null, "S A V E  F I L E ", "SAVE  FILE", JOptionPane.YES_NO_CANCEL_OPTION);
            if (iSaveStatus == JOptionPane.YES_OPTION)
            {
                objEditor.fSaveHashTable2File();

                bWindowClosing = true;
                iOption = iSaveStatus;
                setVisible(false);
                System.exit(0);
            }
            else if (iSaveStatus == JOptionPane.CANCEL_OPTION)
            {
                bWindowClosing = true;
                iOption = iSaveStatus;
            }
            else if (iSaveStatus == JOptionPane.NO_OPTION)
            {
                bWindowClosing = true;
                iOption = iSaveStatus;

                setVisible(false);
                System.exit(0);
            }
       }
    }
}