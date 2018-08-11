package com.github.h2002044.lc2.view;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;

public class FlowSummary extends JPanel {
    JTextPane jtpSummary;
    JScrollPane jspSummary;

    private SimpleAttributeSet sasStyle;
    private DefaultStyledDocument dsdDoc;

    public FlowSummary(float fWidthFactor, float fHeightFactor) {
        setLayout(null);
        setDsdDoc(new DefaultStyledDocument());

        setSasStyle(new SimpleAttributeSet());
        StyleConstants.setFontFamily(getSasStyle(), "ARIAL");
        StyleConstants.setFontSize(getSasStyle(), (int) (10 * fHeightFactor));
        StyleConstants.setBackground(getSasStyle(), Color.yellow);
        StyleConstants.setForeground(getSasStyle(), Color.red);
        StyleConstants.setBold(getSasStyle(), true);
        StyleConstants.setUnderline(getSasStyle(), true);
        StyleConstants.setItalic(getSasStyle(), true);


        jtpSummary = new JTextPane();
        jtpSummary.setDocument(getDsdDoc());
        jtpSummary.setToolTipText("I N S T R U C T I O N    E X E C U T I O N    S U M M A R Y");
        initStylesForTextPane(fWidthFactor, fHeightFactor);

        jspSummary = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspSummary.setSize((int) (250 * fWidthFactor), (int) (480 * fHeightFactor));
        jspSummary.setLocation((int) (0 * fWidthFactor), (int) (0 * fHeightFactor));
        jtpSummary.setEditable(false);
        jtpSummary.setBackground(new Color(255, 230, 230));

        jspSummary.setViewportView(jtpSummary);
        add(jspSummary);
    }

    public void fAddHeadingText(String sText) {
        String sStyle = "heading";
        try {
            Document doc = jtpSummary.getDocument();
            doc.insertString(0, sText, jtpSummary.getStyle(sStyle));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setTextPaneSummary(String text){
        this.jtpSummary.setText(text);
    }

    public void fAddText(String sText) {

        String sStyle = "normal";
        try {
            Document doc = jtpSummary.getDocument();
            doc.insertString(0, sText, jtpSummary.getStyle(sStyle));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void initStylesForTextPane(float fWidthFactor, float fHeightFactor) {
        //Initialize some styles.
        Style def = StyleContext.getDefaultStyleContext().
                getStyle(StyleContext.DEFAULT_STYLE);

        Style regular = jtpSummary.addStyle("regular", def);
        StyleConstants.setFontFamily(def, "ARIAL");
        StyleConstants.setBackground(def, new Color(184, 213, 222));


        Style s = jtpSummary.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);

        s = jtpSummary.addStyle("bold", regular);
        StyleConstants.setBold(s, true);

        s = jtpSummary.addStyle("small", regular);
        StyleConstants.setFontSize(s, 10);

        s = jtpSummary.addStyle("large", regular);
        StyleConstants.setFontSize(s, 16);

        s = jtpSummary.addStyle("underlined", regular);
        StyleConstants.setUnderline(s, true);

        s = jtpSummary.addStyle("red", regular);
        StyleConstants.setForeground(s, Color.red);

        s = jtpSummary.addStyle("heading", regular);
        Color cColor = new Color(21, 34, 89);

        StyleConstants.setFontFamily(s, "ARIAL NARROW");
        StyleConstants.setForeground(s, cColor);
        StyleConstants.setFontSize(s, (int) (12 * fHeightFactor));
        StyleConstants.setBold(s, true);

        s = jtpSummary.addStyle("normal", regular);

        Color cNormal = new Color(17, 106, 74);

        StyleConstants.setForeground(s, cNormal);
        StyleConstants.setFontFamily(s, "ARIAL NARROW");
        StyleConstants.setFontSize(s, (int) (10 * fHeightFactor));
        StyleConstants.setBold(s, false);
        StyleConstants.setUnderline(s, false);
        StyleConstants.setItalic(s, false);
    }

    public SimpleAttributeSet getSasStyle() {
        return sasStyle;
    }

    public void setSasStyle(SimpleAttributeSet sasStyle) {
        this.sasStyle = sasStyle;
    }

    public DefaultStyledDocument getDsdDoc() {
        return dsdDoc;
    }

    public void setDsdDoc(DefaultStyledDocument dsdDoc) {
        this.dsdDoc = dsdDoc;
    }
}
