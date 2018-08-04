package com.github.h2002044.lc2;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: R. Jagadish Prasath
 * Date: Nov 12, 2004
 * Time: 6:47:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class FlashScreen extends JWindow {
    private JProgressBar jpbBar;
    private JPanel jpProgressBar;
    private JPanel jpScreen;

    private int iValue = 0;
    private String sProgressText = "";
    private boolean bStatus = true;
    private boolean bFirstLoading = true;

    public boolean fGetLoadingStatus() {
        return bFirstLoading;
    }

    public void fResetLoadingStatus() {
        bFirstLoading = false;
    }

    public void fSetLoadingStatus(boolean bLoadingStatus) {
        this.bFirstLoading = bLoadingStatus;
    }

    public void fSetFinishedStatus(boolean bStatus) {
        this.bStatus = bStatus;
    }

    public FlashScreen(String sText, int iMaxValue, float heightFactor) {
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        getContentPane().setLayout(null);
        jpProgressBar = new JPanel();

        JLabel jlblLogo = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("Icons/MainBig.jpg")));

        jpProgressBar.setSize(350, 275);
        jpProgressBar.setLocation(0, 0);
        jpProgressBar.add(jlblLogo);

        jpbBar = new JProgressBar(0, 100);

        jpbBar.setSize(350, 25);
        jpbBar.setLocation(0, 275);
        jpbBar.setBackground(new Color(204, 204, 204));
        jpbBar.setFont(new Font("ARIAL", Font.BOLD, (int) (10 * heightFactor)));

        getContentPane().add(jpProgressBar);
        getContentPane().add(jpbBar);

        jpbBar.setStringPainted(true);

    }

    public void fSetText(String sText) {
        jpbBar.setString(sText);
        jpbBar.setStringPainted(true);
        jpbBar.validate();
    }

    public void fSetValue(int value) {
        iValue = value;
        jpbBar.setString(sProgressText);
        jpbBar.setValue(iValue);
        jpbBar.setStringPainted(true);
        jpbBar.validate();

        if (iValue == 100) {
            this.setCursor(new Cursor(Cursor.CUSTOM_CURSOR));
        }
    }
}
