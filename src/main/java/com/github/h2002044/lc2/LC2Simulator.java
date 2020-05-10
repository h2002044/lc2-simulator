package com.github.h2002044.lc2;

import com.github.h2002044.lc2.view.SimulatorWindow;

import java.awt.Dimension;
import java.awt.Toolkit;

public class LC2Simulator {

    SimulatorWindow objSimulatorWindow;

    public LC2Simulator() {
        objSimulatorWindow = SimulatorWindow.getWindow();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        objSimulatorWindow.setSize(screenSize.width, screenSize.height);
        objSimulatorWindow.setBounds(0, 0, screenSize.width-10, screenSize.height-10);
        objSimulatorWindow.setVisible(true);
        objSimulatorWindow.show();
    }

    /**
     * main function to start the application
     */

    public static void main(String a[]) {
        new LC2Simulator();
    }
}


