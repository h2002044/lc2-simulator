package com.github.h2002044.lc2.view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Created by IntelliJ IDEA.
 * User: R. Jagadish Prasath
 * Date: Nov 12, 2004
 * Time: 6:47:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class FlashScreen extends JWindow {
    private JProgressBar progressBar;
    private JPanel progressBarPanel;
    private JPanel screen;

    private int progressValue = 0;
    private String progressText = "";
    private boolean finishedLoading = true;
    private boolean loadingInProgress = true;

    public boolean fGetLoadingStatus() {
        return loadingInProgress;
    }

    public void fResetLoadingStatus() {
        loadingInProgress = false;
    }

    public void fSetLoadingStatus(boolean bLoadingStatus) {
        this.loadingInProgress = bLoadingStatus;
    }

    public void fSetFinishedStatus(boolean bStatus) {
        this.finishedLoading = bStatus;
    }

    public FlashScreen(String sText, int iMaxValue, float heightFactor) {
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        getContentPane().setLayout(null);
        progressBarPanel = new JPanel();


        Dimension dmnScreen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int panelWidth = (int) Math.round(dmnScreen.getWidth() * 0.2);
        int panelHeight = (int) Math.round(dmnScreen.getHeight() * 0.2);

        progressBarPanel.setSize(panelWidth, panelHeight);
        progressBarPanel.setLocation(0, 0);

        progressBar = new JProgressBar(0, 100);
        int progressBarHeight = (int) Math.round(panelHeight * 0.1);
        progressBar.setSize(new Dimension(panelWidth, progressBarHeight));
        progressBar.setLocation(0, panelHeight - 5);
        progressBar.setBackground(new Color(204, 204, 204));
        progressBar.setForeground(new Color(0, 0, 0));
        int fontSize = (int) Math.round(progressBarHeight * 0.5);
        Font font = new Font("ARIAL", Font.BOLD, fontSize);
        progressBar.setFont(font);

        JLabel logoLabel = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("Icons/processor.jpg")));
        logoLabel.setSize(panelWidth, panelHeight - progressBarHeight);
        progressBarPanel.add(logoLabel);

        getContentPane().add(progressBarPanel);
        getContentPane().add(progressBar);

        progressBar.setStringPainted(true);
    }

    public void fSetText(String sText) {
        progressBar.setString(sText);
        progressBar.setStringPainted(true);
        progressBar.validate();
    }

    public void fSetValue(int value) {
        this.progressValue = value;
        progressBar.setString(progressText);
        progressBar.setValue(this.progressValue);
        progressBar.setStringPainted(true);
        progressBar.validate();

        if (this.progressValue == 100) {
            this.setCursor(new Cursor(Cursor.CUSTOM_CURSOR));
        }
    }
}
