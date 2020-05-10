package com.github.h2002044.lc2;

import javax.swing.*;

public class OutputSummary {
    private JOptionPane jopFileInfo;
    private JDialog jdFileInfo;
    private JTextArea jtaFileInfo;
    private boolean bVisibleStatus = false;
    private String sChoices[] = new String[1];
    private JScrollPane jspFileInfo;

    public OutputSummary() {
        jtaFileInfo = new JTextArea(10, 70);
        jspFileInfo = new JScrollPane();
        jspFileInfo.setViewportView(jtaFileInfo);

        getChoicesString()[0] = "OK";

        jtaFileInfo.setEditable(false);
    }

    public void showOutputSummary() {
        Object objMessages[] = new Object[2];
        objMessages[0] = "E X E C U T I O N   S U M M A R Y";
        objMessages[1] = jspFileInfo;
        jopFileInfo = new JOptionPane(objMessages, JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_OPTION, null, getChoicesString(), getChoicesString()[0]);
        jdFileInfo = jopFileInfo.createDialog(null, "E X E C U T I O N   S U M M A R Y");

        jdFileInfo.setModal(true);
        jdFileInfo.show();
    }

    public void addText(String sInstruction) {
        jtaFileInfo.append(sInstruction);
    }

    public void clearText() {
        jtaFileInfo.setText("");
    }

    public void setVisibleStatus() {
        setVisibleStatus(true);
    }

    public void resetVisibleStatus() {
        setVisibleStatus(false);
    }

    public boolean getVisibleStatus() {
        return isVisibleStatus();
    }

    public boolean isVisibleStatus() {
        return bVisibleStatus;
    }

    public void setVisibleStatus(boolean bVisibleStatus) {
        this.bVisibleStatus = bVisibleStatus;
    }

    public String[] getChoicesString() {
        return sChoices;
    }

    public void setChoicesString(String[] sChoices) {
        this.sChoices = sChoices;
    }
}
