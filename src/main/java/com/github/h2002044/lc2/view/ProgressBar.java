package com.github.h2002044.lc2.view;

import javax.swing.*;
import java.awt.*;

/**
 * To display Progress Bar when the application starts.
 *
 * @author Jagadish Prasath
 */
public class ProgressBar extends JWindow {
    JProgressBar jpbBar;
    JPanel jpProgressBar;
    JButton jb;
    int iValue = 0;
    String sProgressText = "";
    boolean bStatus = true;

    public void fSetFinishedStatus(boolean bStatus) {
        this.bStatus = bStatus;
    }

    /**
     * @param sText        Text to be displayed
     * @param iMaxValue    % Completed Value
     * @param widthFactor  For Screen Resolution
     * @param heightFactor For Screen Resolution
     */
    public ProgressBar(String sText, int iMaxValue, float widthFactor, float heightFactor) {
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        getContentPane().setLayout(null);

        jpbBar = new JProgressBar(0, 100);

        jpbBar.setSize((int) (200 * widthFactor), (int) (25 * heightFactor));
        jpbBar.setLocation((int) (0 * widthFactor), (int) (0 * heightFactor));
        jpbBar.setBackground(Color.lightGray);

        jpbBar.setBorderPainted(true);

        getContentPane().add(jpbBar);

        jpbBar.setStringPainted(true);

    }

    public void fSetValue() {
        jpbBar.setString(sProgressText);
        jpbBar.setValue(iValue);
        jpbBar.setStringPainted(true);
        jpbBar.validate();

        if (iValue == 100) {
            this.setCursor(new Cursor(Cursor.CUSTOM_CURSOR));
        }
    }
}
