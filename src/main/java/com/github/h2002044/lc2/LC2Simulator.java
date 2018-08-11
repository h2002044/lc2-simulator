package com.github.h2002044.lc2;

import com.github.h2002044.lc2.view.Input;
import com.github.h2002044.lc2.view.SimulatorPanel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class LC2Simulator {

    Input objInput;

    public LC2Simulator() {
        objInput = Input.getInput();
        objInput.setSize(1200, 750);
        objInput.setBounds(0, 0, 1000, 750);
        objInput.setVisible(true);
        objInput.show();
    }

    /**
     * main function to start the application
     */

    public static void main(String a[]) {
        new LC2Simulator();
    }
}


