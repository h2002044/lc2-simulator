package com.github.h2002044.lc2.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Input Class is used to initialise the GUI for LC-2 Simulator.<br>
 * The GUI has 2 parts, Editor Pane, Simulator Pane.<br>
 * Editor Window is used to make LC-2 Programs, Options like Saving, Editing, Opening, Creating New files are available.<br>
 * Simulator Window shows the run time simulation of Data Flow for every instruction involved in the program.<br>
 */

public class SimulatorWindow extends JFrame {
    Container contentpane;
    JTabbedPane editorPane;
    JTabbedPane simulationPane;
    Editor editor;

    private static boolean bRun = false;
    private static boolean bRunning = false;
    boolean bWindowClosing = false;
    int iOption = -1;

    SimulatorPanel simulatorPanel;
    // Storage objStorage;

    float widthFactor;
    float heightFactor;

    private int iScreenWidth;
    private int iScreenHeight;

    ToolBar editorToolBar;
    FlashScreen flashScreen;

    Font fnt;
    Font fntBlueBoldFont;

    final static SimulatorWindow SIMULATOR_WINDOW;

    static {
        SIMULATOR_WINDOW = new SimulatorWindow();
    }

    public static SimulatorWindow getWindow() {
        return SIMULATOR_WINDOW;
    }

    /**
     * Input Class is used to initialise the GUI for LC-2 Simulator.<br>
     * The GUI has 2 parts, Editor Pane, Simulator Pane.<br>
     * Editor Window is used to make LC-2 Programs, Options like Saving, Editing, Opening, Creating New files are available.<br>
     * Simulator Window shows the run time simulation of Data Flow for every instruction involved in the program.<br>
     */

    private SimulatorWindow() {
        setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("Icons/Icon.gif")).getImage());
        setTitle(" L C -- 2  S I M U L A T O R   [ B I T S - P I L A N I ]");
        getContentPane().setLayout(new BorderLayout());

        fSetDimension();

        flashScreen = new FlashScreen("INITIALISING . . . .", 100, heightFactor);

        Dimension dmnScreen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int panelWidth = (int) Math.round(dmnScreen.getWidth() * 0.2);
        int panelHeight = (int) Math.round(dmnScreen.getHeight() * 0.2);

        int xCoordinate = (int) (widthFactor * 800);
        int yCoordinate = (int) (heightFactor * 600);

        int widthStart = panelWidth;
        int heightStart = panelHeight;

        widthStart = ((int) (xCoordinate / 2.0f)) - ((int) (widthStart / 2.0f));
        heightStart = ((int) (yCoordinate / 2.0f)) - ((int) (heightStart / 2.0f));

        flashScreen.setBounds(widthStart, heightStart, panelWidth, panelHeight + 25);
        flashScreen.setVisible(true);
        flashScreen.validate();

        delay();

        editorPane = new JTabbedPane();

        flashScreen.fSetText("I N I T I A L I S I N G     E D I T O R  . . .");
        delay();
        simulatorPanel = new SimulatorPanel();
        editor = new Editor(editorPane, simulatorPanel, flashScreen, widthFactor, heightFactor, fnt, fntBlueBoldFont);


        flashScreen.fSetText("I N I T I A L I S I N G     T O O L B A R . . .");

        delay();

        editorToolBar = new ToolBar(widthFactor, heightFactor, editor, null, simulatorPanel);
        editor.setToolBar(editorToolBar);
        editorToolBar.fDisableRun();
        editorToolBar.fDisableStep();
        editorToolBar.fDisableStop();

        editorToolBar.setSize((int) (50 * widthFactor), (int) (300 * heightFactor));
        editorToolBar.setLocation((int) (10 * widthFactor), (int) (35 * heightFactor));
        getContentPane().add(editorToolBar, BorderLayout.WEST);

        editorPane.addTab("C O D E - - E D I T O R", editor);
        editorPane.setTabPlacement(1);


        editorPane.addChangeListener(
            new TabbedPaneChangeListener());


        flashScreen.fSetText("I N I T I A L I S I N G    S I M U L A T O R . . .");
        delay();

        editorPane.addTab("S I M U L A T O R", simulatorPanel);

        getContentPane().add(editorPane, BorderLayout.CENTER);

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new MyWindowAdapter());

        if (flashScreen.fGetLoadingStatus() == true) {
            flashScreen.setVisible(false);
            flashScreen.hide();
            flashScreen.fResetLoadingStatus();
        }
        editor.fEnableRunStatus();
    }

    public static boolean isbRunning() {
        return bRunning;
    }

    public static void setbRunning(boolean bRunning) {
        SimulatorWindow.bRunning = bRunning;
    }

    /**
     * fDelay is used to give delay while displaying Flash Screen.<br>
     * The Flash Screen displays the components that are getting initialised sequentially<br>
     */

    public void delay() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * processWindowEvent is used to handle the events that are generated on Window.<br>
     * This function will be executed by the AWT Event Thread<br>
     */

    protected void processWindowEvent(WindowEvent e) {
        if (bWindowClosing == true) {
            bWindowClosing = false;

            if (iOption == JOptionPane.YES_OPTION) {
                setVisible(false);
                System.out.println("YES OPTION");
                System.exit(0);
            } else if (iOption == JOptionPane.NO_OPTION) {
                setVisible(false);
                System.out.println("NO OPTION");
                System.exit(0);
            } else if (iOption == JOptionPane.CANCEL_OPTION) {
                iOption = -1;
                System.out.println("CANCEL OPTION");
                return;
            }


        }

        super.processWindowEvent(e);
    }


    /**
     * fSetMenuBar() is used to set the menu.
     * This menubar has File Open/New/Save options, Help Tutor/Context options, Execute Run/Debug options
     */

    private JMenuBar fSetMenuBar() {
        JMenuBar menuBar = new JMenuBar();

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

        menuBar.add(jmFile);

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

        menuBar.add(jmProgram);

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

        menuBar.add(jmHelp);

        return (menuBar);

    }

    /**
     * fSetDimension is used to find out the screen widht/height and resolution so as to arrange the GUI components properly<br>
     */
    public void fSetDimension() {

        Dimension dmnScreen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        setScreenWidth(dmnScreen.width);
        setScreenHeight(dmnScreen.height);

        widthFactor = ((float) getScreenWidth() / 800);
        heightFactor = ((float) getScreenHeight() / 600);

        fnt = new Font("ARIAL", Font.PLAIN, (int) (10 * heightFactor));
        fntBlueBoldFont = new Font("ARIAL", Font.BOLD, (int) (10 * heightFactor));


        System.out.println("Width Factor : " + widthFactor);
        System.out.println("Height Factor : " + heightFactor);
    }

    /**
     * fInitialiseInput is used to initialise the components for EDITOR<br>
     */
    public void fInitialiseInput() {
        editor = new Editor(editorPane, simulatorPanel, flashScreen, widthFactor, heightFactor, fnt, fntBlueBoldFont);


        editorPane.addTab("C O D E -- E D I T O R", editor);
        editorPane.validate();
    }


    /**
     * isRun is a STATIC function which returns a boolean value indicating whether the program is under execution or not<br>
     *
     * @ return status of the program running or not running.<br>
     */
    public static boolean isRun() {
        return bRun;
    }

    /**
     * fSetRun is a STATIC function which is used to set the running status of the program to false/true<br>
     *
     * @ param Status of the program running/not running.<br>
     */
    public static void setRun(boolean bRun) {
        SimulatorWindow.bRun = bRun;
    }

    /**
     * getScreenWidth is used to return the width of the screen<br>
     *
     * @ return width of the screen (int)<br>
     */
    public int getScreenWidth() {
        return iScreenWidth;
    }

    /**
     * setScreenWidth is used to set the width of the screen<br>
     *
     * @ param width of the screen (int) <BR>
     */
    public void setScreenWidth(int iScreenWidth) {
        this.iScreenWidth = iScreenWidth;
    }

    /**
     * getScreenHeight is used to return the height of the screen<br>
     *
     * @ return height of the screen (int)<br>
     */
    public int getScreenHeight() {
        return iScreenHeight;
    }

    /**
     * setScreenHeight is used to set the Height of the screen<br>
     *
     * @ param Height of the screen (int) <BR>
     */
    public void setScreenHeight(int iScreenHeight) {
        this.iScreenHeight = iScreenHeight;
    }


    private class TabbedPaneChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent ce) {
            if (isRun() == true) {
                int iIndex = editorPane.getSelectedIndex();
                if (iIndex == 0) {
                    editorPane.setSelectedIndex(1);
                }
            } else {
                if (isbRunning() == true) {
                    editor.fUpdateTableAfterRun();
                    setbRunning(false);
                    editorToolBar.fDisableRun();
                    editorToolBar.fDisableStep();
                    editorToolBar.fDisableStop();
                }
            }
        }
    }

    private class MyWindowAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            int iSaveStatus = JOptionPane.showConfirmDialog(null, "S A V E  F I L E ", "SAVE  FILE", JOptionPane.YES_NO_CANCEL_OPTION);
            if (iSaveStatus == JOptionPane.YES_OPTION) {
                editor.fSaveHashTable2File();

                bWindowClosing = true;
                iOption = iSaveStatus;
                setVisible(false);
                System.exit(0);
            } else if (iSaveStatus == JOptionPane.CANCEL_OPTION) {
                bWindowClosing = true;
                iOption = iSaveStatus;
            } else if (iSaveStatus == JOptionPane.NO_OPTION) {
                bWindowClosing = true;
                iOption = iSaveStatus;

                setVisible(false);
                System.exit(0);
            }
        }
    }
}