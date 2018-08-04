package com.github.h2002044.lc2;

import javax.swing.*;
import java.awt.*;

public class Circuit extends JPanel {
    private int iWidth;
    private int iHeight;

    private float widthFactor;
    private float heightFactor;


    private boolean bPaint = false;
    FlowProperties objFP = new FlowProperties();
    private Font fntSmall;
    private Font fntNormal;

    Font arialBold_14_Font = new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor));
    Font arialPlain_10_Font = new Font("ARIAL", Font.PLAIN, (int) (10 * heightFactor));
    Font arialBold_12_Font = new Font("ARIAL", Font.BOLD, (int) (12 * heightFactor));

    private void init() {
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        setToolTipText("D A T A   F L O W   P A T H");

        Dimension dmnScreen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        iWidth = dmnScreen.width;
        iHeight = dmnScreen.height;

        if (bPaint == false) {
            widthFactor = ((float) iWidth / 800);
            heightFactor = ((float) iHeight / 600);

            widthFactor = widthFactor * 0.7f;
            heightFactor = heightFactor * 0.7f;

            System.out.println("Width Factor : " + widthFactor);
            System.out.println("Height Factor : " + heightFactor);

            setPreferredSize(new Dimension((int) (800 * widthFactor), (int) (600 * heightFactor)));
            bPaint = true;
        }
        setVisible(true);
        show();
    }


    private void drawGrid(Graphics g) {
        setBackground(Color.lightGray);

        g.setColor(Color.lightGray);
        g.setColor(new Color(239, 239, 239));


        g.fillRect(0, 0, (int) (800 * widthFactor), (int) (600 * heightFactor));

        g.setColor(new Color(230, 230, 222));

        for (int iLoop = 0; iLoop <= 600 * heightFactor; iLoop = iLoop + (int) (25 * heightFactor)) {
            g.drawLine(0, iLoop, (int) (800 * heightFactor), iLoop);
        }

        for (int iInnerLoop = 0; iInnerLoop <= 800 * widthFactor; iInnerLoop = iInnerLoop + (int) (25 * widthFactor)) {
            g.drawLine(iInnerLoop, 0, iInnerLoop, (int) (600 * widthFactor));
        }

        g.setColor(Color.black);

    }

    public void paint(Graphics g) {
        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));

        init();

        drawGrid(g);

        drawRegisters(g);

        drawMemory(g);
        drawMAR(g);
        drawMDR(g);

        drawInput(g);
        drawOutput(g);

        drawKBSR(g);
        drawKBDR(g);

        drawCRTDR(g);
        drawCRTSR(g);

        drawControlLogic(g);

        drawInstructionRegister(g);

        drawNegative(g);
        drawZero(g);
        drawPositive(g);


        drawALU(g);
        drawALUInput(g);

        drawMAR_MUX(g);
        drawPC_MUX(g);

        drawSEXT(g);
        drawZEXT_1(g);
        drawZEXT_2(g);
        drawPlusOne(g);
        drawPLUS(g);
        drawProgramCounter(g);
        drawAT(g);
        drawLogic(g);

        drawBUS(g);

        reg2ALU_1(g);

        reg2ALU_2(g);

        register2ALUInput(g);

        ALU2Bus(g);

        bus2Register(g);

        bus2CRTDR(g);

        bus2CRTSR(g);

        CRTSR2Bus(g);

        bus2KBDR(g);

        KBSR2Bus(g);


        bus2KBSR(g);

        bus2MAR(g);

        MDR2Bus(g);

        bus2MDR(g);

        bus2IR(g);


        IR2ControlLogic(g);

        negative2ControlLogic(g);

        zero2ControlLogic(g);

        positive2ControlLogic(g);

        MAR_MUX2Bus(g);

        PC2Bus(g);

        IR2Zext_1(g);

        Zext2Plus(g);

        IR2Sext(g);

        Zext2MarMux(g);

        plus2MarMux(g);

        reg2PCMux(g);

        reg2Plus(g);

        IR2Zext2(g);

        PC2Plus1(g);

        Sext2ALUInput(g);

        At2MarMux(g);

        PC2At(g);

        IR2At(g);


        MAR2Memory(g);


        MAR2Input(g);

        Mar2Output(g);

        MDR2Memory(g);

        Memory2MDR(g);

        At2PCMux(g);

        PCMux2PC(g);

        Plus12PCMux(g);

        Bus2PCMux(g);

        Bus2Logic(g);

        logic2Positive(g);

        logic2Zero(g);

        logic2Negative(g);
    }


    private void MAR2Input(Graphics g) {
        g.setColor(objFP.cMAR2InputOutline);

        g.drawLine((int) (245 * widthFactor), (int) (450 * heightFactor), (int) (400 * widthFactor), (int) (450 * heightFactor)); //MAR to Input
        fDrawFilledTriangle(g, objFP.cMAR2InputOutline, (int) (400 * widthFactor), (int) (450 * heightFactor), (int) (390 * widthFactor), (int) (445 * heightFactor), (int) (390 * widthFactor), (int) (455 * heightFactor));
        g.fillOval((int) (243 * widthFactor), (int) (447 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor)); //MAR

        g.setColor(objFP.cMAR2InputFill);

        g.drawLine((int) (245 * widthFactor), (int) (450 * heightFactor), (int) (400 * widthFactor), (int) (450 * heightFactor)); //MAR to Input
        fDrawFilledTriangle(g, objFP.cMAR2InputFill, (int) (400 * widthFactor), (int) (450 * heightFactor), (int) (390 * widthFactor), (int) (445 * heightFactor), (int) (390 * widthFactor), (int) (455 * heightFactor));
        g.fillOval((int) (243 * widthFactor), (int) (447 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor)); //MAR
    }

    public void MAR2Memory(Graphics g) {
        g.setColor(objFP.cMAR2MemoryOutline);

        g.drawLine((int) (200 * widthFactor), (int) (450 * heightFactor), (int) (245 * widthFactor), (int) (450 * heightFactor)); // MAR to Memory
        fDrawFilledTriangle(g, objFP.cMAR2MemoryOutline, (int) (200 * widthFactor), (int) (450 * heightFactor), (int) (210 * widthFactor), (int) (445 * heightFactor), (int) (210 * widthFactor), (int) (455 * heightFactor));
        g.fillOval((int) (243 * widthFactor), (int) (447 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor)); //MAR

        g.setColor(objFP.cMAR2MemoryFill);

        g.drawLine((int) (200 * widthFactor), (int) (450 * heightFactor), (int) (245 * widthFactor), (int) (450 * heightFactor)); // MAR to Memory
        fDrawFilledTriangle(g, objFP.cMAR2MemoryFill, (int) (200 * widthFactor), (int) (450 * heightFactor), (int) (210 * widthFactor), (int) (445 * heightFactor), (int) (210 * widthFactor), (int) (455 * heightFactor));
        g.fillOval((int) (243 * widthFactor), (int) (447 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor)); //MAR
    }

    public void drawBUS(Graphics g) {
        g.setColor(objFP.cBUSOutline);

        g.fillRect((int) (20 * widthFactor), (int) (400 * heightFactor), (int) (700 * widthFactor), (int) (6 * heightFactor));
        g.fillRect((int) (20 * widthFactor), (int) (20 * heightFactor), (int) (700 * widthFactor), (int) (6 * heightFactor));
        g.fillRect((int) (715 * widthFactor), (int) (20 * heightFactor), (int) (6 * widthFactor), (int) (385 * heightFactor));

        //Arrow 1

        fDrawFilledTriangle(g, objFP.cBUSOutline, (int) (10 * widthFactor), (int) (23 * heightFactor), (int) (26 * widthFactor), (int) (15 * heightFactor), (int) (26 * widthFactor), (int) (31 * heightFactor));
        fDrawFilledTriangle(g, objFP.cBUSOutline, (int) (10 * widthFactor), (int) (403 * heightFactor), (int) (26 * widthFactor), (int) (395 * heightFactor), (int) (26 * widthFactor), (int) (411 * heightFactor));

        g.setColor(objFP.cBUSFill);

        //bus
        g.fillRect((int) (20 * widthFactor), (int) (400 * heightFactor), (int) (700 * widthFactor), (int) (6 * heightFactor));
        g.fillRect((int) (20 * widthFactor), (int) (20 * heightFactor), (int) (700 * widthFactor), (int) (6 * heightFactor));
        g.fillRect((int) (715 * widthFactor), (int) (20 * heightFactor), (int) (6 * widthFactor), (int) (385 * heightFactor));

        //Arrow 1

        fDrawFilledTriangle(g, objFP.cBUSFill, (int) (10 * widthFactor), (int) (23 * heightFactor), (int) (26 * widthFactor), (int) (15 * heightFactor), (int) (26 * widthFactor), (int) (31 * heightFactor));
        fDrawFilledTriangle(g, objFP.cBUSFill, (int) (10 * widthFactor), (int) (403 * heightFactor), (int) (26 * widthFactor), (int) (395 * heightFactor), (int) (26 * widthFactor), (int) (411 * heightFactor));
    }

    public void MAR_MUX2Bus(Graphics g) {
        g.setColor(objFP.cMarMux2BusOutline);

        g.drawLine((int) (70 * widthFactor), (int) (25 * heightFactor), (int) (70 * widthFactor), (int) (70 * heightFactor));  // MARMUX to BUS
        fDrawFilledTriangle(g, objFP.cMarMux2BusOutline, (int) (70 * widthFactor), (int) (25 * heightFactor), (int) (65 * widthFactor), (int) (35 * heightFactor), (int) (75 * widthFactor), (int) (35 * heightFactor));

        g.setColor(objFP.cMarMux2BusFill);
        g.drawLine((int) (70 * widthFactor), (int) (25 * heightFactor), (int) (70 * widthFactor), (int) (70 * heightFactor));  // MARMUX to BUS
        fDrawFilledTriangle(g, objFP.cMarMux2BusFill, (int) (70 * widthFactor), (int) (25 * heightFactor), (int) (65 * widthFactor), (int) (35 * heightFactor), (int) (75 * widthFactor), (int) (35 * heightFactor));
    }

    public void reg2ALU_2(Graphics g) {
        g.setColor(objFP.cReg2ALU_2_Outline);
        //ALU INPUT 2
        g.drawLine((int) (455 * widthFactor), (int) (265 * heightFactor), (int) (455 * widthFactor), (int) (300 * heightFactor));
        fDrawFilledTriangle(g, objFP.cReg2ALU_2_Outline, (int) (455 * widthFactor), (int) (300 * heightFactor), (int) (450 * widthFactor), (int) (290 * heightFactor), (int) (460 * widthFactor), (int) (290 * heightFactor));

        g.setColor(objFP.cReg2ALU_2_Fill);
        //ALU INPUT 2
        g.drawLine((int) (455 * widthFactor), (int) (265 * heightFactor), (int) (455 * widthFactor), (int) (300 * heightFactor));
        fDrawFilledTriangle(g, objFP.cReg2ALU_2_Fill, (int) (455 * widthFactor), (int) (300 * heightFactor), (int) (450 * widthFactor), (int) (290 * heightFactor), (int) (460 * widthFactor), (int) (290 * heightFactor));
    }

    public void reg2ALU_1(Graphics g) {
        g.setColor(objFP.cReg2ALU_1_Outline);
        //ALU INPUT 1
        g.drawLine((int) (520 * widthFactor), (int) (210 * heightFactor), (int) (520 * widthFactor), (int) (300 * heightFactor));
        fDrawFilledTriangle(g, objFP.cReg2ALU_1_Outline, (int) (520 * widthFactor), (int) (300 * heightFactor), (int) (515 * widthFactor), (int) (290 * heightFactor), (int) (525 * widthFactor), (int) (290 * heightFactor));
        g.fillOval((int) (517 * widthFactor), (int) (222 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));   //REG to ALU

        g.setColor(objFP.cReg2ALU_1_Fill);
        //ALU INPUT 1
        g.drawLine((int) (520 * widthFactor), (int) (210 * heightFactor), (int) (520 * widthFactor), (int) (300 * heightFactor));
        fDrawFilledTriangle(g, objFP.cReg2ALU_1_Fill, (int) (520 * widthFactor), (int) (300 * heightFactor), (int) (515 * widthFactor), (int) (290 * heightFactor), (int) (525 * widthFactor), (int) (290 * heightFactor));
        g.fillOval((int) (517 * widthFactor), (int) (222 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));   //REG to ALU
    }

    private void bus2CRTSR(Graphics g) {
        g.setColor(objFP.cBus2CRTSROutline);

        g.drawLine((int) (630 * widthFactor), (int) (405 * heightFactor), (int) (630 * widthFactor), (int) (430 * heightFactor)); //CRTSR to Bus
        fDrawFilledTriangle(g, objFP.cBus2CRTSROutline, (int) (630 * widthFactor), (int) (430 * heightFactor), (int) (625 * widthFactor), (int) (425 * heightFactor), (int) (635 * widthFactor), (int) (425 * heightFactor));

        g.setColor(objFP.cBus2CRTSRFill);
        g.drawLine((int) (630 * widthFactor), (int) (405 * heightFactor), (int) (630 * widthFactor), (int) (430 * heightFactor)); //CRTSR to Bus
        fDrawFilledTriangle(g, objFP.cBus2CRTSRFill, (int) (630 * widthFactor), (int) (430 * heightFactor), (int) (625 * widthFactor), (int) (425 * heightFactor), (int) (635 * widthFactor), (int) (425 * heightFactor));
    }

    public void register2ALUInput(Graphics g) {
        g.setColor(objFP.cReg2ALUInputOutline);

        //input of ALU INPUT 2  from Register
        g.drawLine((int) (465 * widthFactor), (int) (210 * heightFactor), (int) (465 * widthFactor), (int) (250 * heightFactor));
        fDrawFilledTriangle(g, objFP.cReg2ALUInputOutline, (int) (465 * widthFactor), (int) (250 * heightFactor), (int) (460 * widthFactor), (int) (245 * heightFactor), (int) (470 * widthFactor), (int) (245 * heightFactor));


        g.setColor(objFP.cReg2ALUInputFill);
        //input of ALU INPUT 2  from Register
        g.drawLine((int) (465 * widthFactor), (int) (210 * heightFactor), (int) (465 * widthFactor), (int) (250 * heightFactor));
        fDrawFilledTriangle(g, objFP.cReg2ALUInputFill, (int) (465 * widthFactor), (int) (250 * heightFactor), (int) (460 * widthFactor), (int) (245 * heightFactor), (int) (470 * widthFactor), (int) (245 * heightFactor));
    }

    private void IR2ControlLogic(Graphics g) {
        g.setColor(objFP.cIR2ControlLogicOutline);

        g.drawLine((int) (75 * widthFactor), (int) (280 * heightFactor), (int) (75 * widthFactor), (int) (310 * heightFactor)); // IR to Control Logic
        fDrawFilledTriangle(g, objFP.cIR2ControlLogicOutline, (int) (75 * widthFactor), (int) (280 * heightFactor), (int) (70 * widthFactor), (int) (290 * heightFactor), (int) (80 * widthFactor), (int) (290 * heightFactor));

        g.setColor(objFP.cIR2ControlLogicFill);
        g.drawLine((int) (75 * widthFactor), (int) (280 * heightFactor), (int) (75 * widthFactor), (int) (310 * heightFactor)); // IR to Control Logic
        fDrawFilledTriangle(g, objFP.cIR2ControlLogicFill, (int) (75 * widthFactor), (int) (280 * heightFactor), (int) (70 * widthFactor), (int) (290 * heightFactor), (int) (80 * widthFactor), (int) (290 * heightFactor));
    }

    public void bus2IR(Graphics g) {
        g.setColor(objFP.cBus2IROutline);
        g.drawLine((int) (75 * widthFactor), (int) (400 * heightFactor), (int) (75 * widthFactor), (int) (335 * heightFactor)); //IR input from BUS
        fDrawFilledTriangle(g, objFP.cBus2IROutline, (int) (75 * widthFactor), (int) (335 * heightFactor), (int) (70 * widthFactor), (int) (345 * heightFactor), (int) (80 * widthFactor), (int) (345 * heightFactor));

        g.setColor(objFP.cBus2IRFill);
        g.drawLine((int) (75 * widthFactor), (int) (400 * heightFactor), (int) (75 * widthFactor), (int) (335 * heightFactor)); //IR input from BUS
        fDrawFilledTriangle(g, objFP.cBus2IRFill, (int) (75 * widthFactor), (int) (335 * heightFactor), (int) (70 * widthFactor), (int) (345 * heightFactor), (int) (80 * widthFactor), (int) (345 * heightFactor));
    }

    public void bus2MDR(Graphics g) {
        g.setColor(objFP.cBus2MDROutline);

        g.drawLine((int) (75 * widthFactor), (int) (405 * heightFactor), (int) (75 * widthFactor), (int) (420 * heightFactor));   //Bus to MDR IN
        fDrawFilledTriangle(g, objFP.cBus2MDROutline, (int) (75 * widthFactor), (int) (420 * heightFactor), (int) (70 * widthFactor), (int) (415 * heightFactor), (int) (80 * widthFactor), (int) (415 * heightFactor));

        g.setColor(objFP.cBus2MDRFill);
        g.drawLine((int) (75 * widthFactor), (int) (405 * heightFactor), (int) (75 * widthFactor), (int) (420 * heightFactor));   //Bus to MDR IN
        fDrawFilledTriangle(g, objFP.cBus2MDRFill, (int) (75 * widthFactor), (int) (420 * heightFactor), (int) (70 * widthFactor), (int) (415 * heightFactor), (int) (80 * widthFactor), (int) (415 * heightFactor));
    }

    public void MDR2Bus(Graphics g) {
        g.setColor(objFP.cMDR2BusOutline);

        g.drawLine((int) (55 * widthFactor), (int) (405 * heightFactor), (int) (55 * widthFactor), (int) (420 * heightFactor));   //MDR to Bus OUT
        fDrawFilledTriangle(g, objFP.cMDR2BusOutline, (int) (55 * widthFactor), (int) (405 * heightFactor), (int) (50 * widthFactor), (int) (410 * heightFactor), (int) (60 * widthFactor), (int) (410 * heightFactor));

        g.setColor(objFP.cMDR2BusFill);

        g.drawLine((int) (55 * widthFactor), (int) (405 * heightFactor), (int) (55 * widthFactor), (int) (420 * heightFactor));   //MDR to Bus OUT
        fDrawFilledTriangle(g, objFP.cMDR2BusFill, (int) (55 * widthFactor), (int) (405 * heightFactor), (int) (50 * widthFactor), (int) (410 * heightFactor), (int) (60 * widthFactor), (int) (410 * heightFactor));
    }

    public void ALU2Bus(Graphics g) {
        g.setColor(objFP.cALU2BusOutline);
        g.drawLine((int) (485 * widthFactor), (int) (330 * heightFactor), (int) (485 * widthFactor), (int) (400 * heightFactor)); //ALU to BUS
        fDrawFilledTriangle(g, objFP.cALU2BusOutline, (int) (485 * widthFactor), (int) (400 * heightFactor), (int) (480 * widthFactor), (int) (390 * heightFactor), (int) (490 * widthFactor), (int) (390 * heightFactor));

        g.setColor(objFP.cALU2BusFill);

        g.drawLine((int) (485 * widthFactor), (int) (330 * heightFactor), (int) (485 * widthFactor), (int) (400 * heightFactor)); //ALU to BUS
        fDrawFilledTriangle(g, objFP.cALU2BusFill, (int) (485 * widthFactor), (int) (400 * heightFactor), (int) (480 * widthFactor), (int) (390 * heightFactor), (int) (490 * widthFactor), (int) (390 * heightFactor));
    }

    public void bus2MAR(Graphics g) {
        g.setColor(objFP.cBus2MAROutline);
        g.drawLine((int) (245 * widthFactor), (int) (405 * heightFactor), (int) (245 * widthFactor), (int) (420 * heightFactor)); //MAR to Bus
        fDrawFilledTriangle(g, objFP.cBus2MAROutline, (int) (245 * widthFactor), (int) (420 * heightFactor), (int) (240 * widthFactor), (int) (415 * heightFactor), (int) (250 * widthFactor), (int) (415 * heightFactor));

        g.setColor(objFP.cBus2MARFill);
        g.drawLine((int) (245 * widthFactor), (int) (405 * heightFactor), (int) (245 * widthFactor), (int) (420 * heightFactor)); //MAR to Bus
        fDrawFilledTriangle(g, objFP.cBus2MARFill, (int) (245 * widthFactor), (int) (420 * heightFactor), (int) (240 * widthFactor), (int) (415 * heightFactor), (int) (250 * widthFactor), (int) (415 * heightFactor));
    }

    public void IR2Sext(Graphics g) {
        g.setColor(objFP.cIR2SextOutline);
        g.drawLine((int) (35 * widthFactor), (int) (210 * heightFactor), (int) (280 * widthFactor), (int) (210 * heightFactor));  // SEXT TO IR Line.
        fDrawFilledTriangle(g, objFP.cIR2SextOutline, (int) (280 * widthFactor), (int) (210 * heightFactor), (int) (275 * widthFactor), (int) (205 * heightFactor), (int) (275 * widthFactor), (int) (215 * heightFactor));
        g.fillOval((int) (32 * widthFactor), (int) (222 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));    //IR to SEXT
        g.fillOval((int) (72 * widthFactor), (int) (297 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));   //IR to ZEXT

        g.setColor(objFP.cIR2SextFill);
        g.drawLine((int) (35 * widthFactor), (int) (210 * heightFactor), (int) (280 * widthFactor), (int) (210 * heightFactor));  // SEXT TO IR Line.
        fDrawFilledTriangle(g, objFP.cIR2SextFill, (int) (280 * widthFactor), (int) (210 * heightFactor), (int) (275 * widthFactor), (int) (205 * heightFactor), (int) (275 * widthFactor), (int) (215 * heightFactor));
        g.fillOval((int) (32 * widthFactor), (int) (222 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));    //IR to SEXT
        g.fillOval((int) (72 * widthFactor), (int) (297 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));   //IR to ZEXT
    }

    private void bus2KBSR(Graphics g) {
        g.setColor(objFP.cBus2KBSROutline);

        g.drawLine((int) (415 * widthFactor), (int) (405 * heightFactor), (int) (415 * widthFactor), (int) (430 * heightFactor));  //Bus 2 KBSR 1
        fDrawFilledTriangle(g, objFP.cBus2KBSROutline, (int) (415 * widthFactor), (int) (430 * heightFactor), (int) (410 * widthFactor), (int) (425 * heightFactor), (int) (420 * widthFactor), (int) (425 * heightFactor));

        g.setColor(objFP.cBus2KBSRFill);
        g.drawLine((int) (415 * widthFactor), (int) (405 * heightFactor), (int) (415 * widthFactor), (int) (430 * heightFactor));  //Bus 2 KBSR 1
        fDrawFilledTriangle(g, objFP.cBus2KBSRFill, (int) (415 * widthFactor), (int) (430 * heightFactor), (int) (410 * widthFactor), (int) (425 * heightFactor), (int) (420 * widthFactor), (int) (425 * heightFactor));
    }

    private void KBSR2Bus(Graphics g) {
        g.setColor(objFP.cKBSR2BusOutline);
        g.drawLine((int) (435 * widthFactor), (int) (405 * heightFactor), (int) (435 * widthFactor), (int) (430 * heightFactor));  //KBSR 2 Bus
        fDrawFilledTriangle(g, objFP.cKBSR2BusOutline, (int) (435 * widthFactor), (int) (405 * heightFactor), (int) (430 * widthFactor), (int) (410 * heightFactor), (int) (440 * widthFactor), (int) (410 * heightFactor));

        g.setColor(objFP.cKBSR2BusFill);
        g.drawLine((int) (435 * widthFactor), (int) (405 * heightFactor), (int) (435 * widthFactor), (int) (430 * heightFactor));  //KBSR 2 Bus
        fDrawFilledTriangle(g, objFP.cKBSR2BusFill, (int) (435 * widthFactor), (int) (405 * heightFactor), (int) (430 * widthFactor), (int) (410 * heightFactor), (int) (440 * widthFactor), (int) (410 * heightFactor));
    }

    private void bus2KBDR(Graphics g) {
        g.setColor(objFP.cBus2KBSROutline);
        g.drawLine((int) (495 * widthFactor), (int) (405 * heightFactor), (int) (495 * widthFactor), (int) (430 * heightFactor));  //Bus 2 KBDR
        fDrawFilledTriangle(g, objFP.cBus2KBSROutline, (int) (495 * widthFactor), (int) (405 * heightFactor), (int) (490 * widthFactor), (int) (410 * heightFactor), (int) (500 * widthFactor), (int) (410 * heightFactor));

        g.setColor(objFP.cBus2KBSRFill);

        g.drawLine((int) (495 * widthFactor), (int) (405 * heightFactor), (int) (495 * widthFactor), (int) (430 * heightFactor));  //Bus 2 KBDR
        fDrawFilledTriangle(g, objFP.cBus2KBSRFill, (int) (495 * widthFactor), (int) (405 * heightFactor), (int) (490 * widthFactor), (int) (410 * heightFactor), (int) (500 * widthFactor), (int) (410 * heightFactor));
    }

    private void CRTSR2Bus(Graphics g) {
        g.setColor(objFP.cCRTSR2BusOutline);

        g.drawLine((int) (655 * widthFactor), (int) (405 * heightFactor), (int) (655 * widthFactor), (int) (430 * heightFactor)); //CRTSR to BUS
        fDrawFilledTriangle(g, objFP.cCRTSR2BusOutline, (int) (655 * widthFactor), (int) (405 * heightFactor), (int) (650 * widthFactor), (int) (410 * heightFactor), (int) (660 * widthFactor), (int) (410 * heightFactor));

        g.setColor(objFP.cCRTSR2BusFill);
        g.drawLine((int) (655 * widthFactor), (int) (405 * heightFactor), (int) (655 * widthFactor), (int) (430 * heightFactor)); //CRTSR to BUS
        fDrawFilledTriangle(g, objFP.cCRTSR2BusFill, (int) (655 * widthFactor), (int) (405 * heightFactor), (int) (650 * widthFactor), (int) (410 * heightFactor), (int) (660 * widthFactor), (int) (410 * heightFactor));
    }

    private void bus2CRTDR(Graphics g) {
        g.setColor(objFP.cBus2CRTDROutline);

        g.drawLine((int) (575 * widthFactor), (int) (405 * heightFactor), (int) (575 * widthFactor), (int) (430 * heightFactor)); //CRTDR
        fDrawFilledTriangle(g, objFP.cBus2CRTDROutline, (int) (575 * widthFactor), (int) (430 * heightFactor), (int) (570 * widthFactor), (int) (425 * heightFactor), (int) (580 * widthFactor), (int) (425 * heightFactor));

        g.setColor(objFP.cBus2CRTDRFill);

        g.drawLine((int) (575 * widthFactor), (int) (405 * heightFactor), (int) (575 * widthFactor), (int) (430 * heightFactor)); //CRTDR
        fDrawFilledTriangle(g, objFP.cBus2CRTDRFill, (int) (575 * widthFactor), (int) (430 * heightFactor), (int) (570 * widthFactor), (int) (425 * heightFactor), (int) (580 * widthFactor), (int) (425 * heightFactor));
    }

    public void bus2Register(Graphics g) {
        g.setColor(objFP.cBus2RegisterOutline);
        g.drawLine((int) (500 * widthFactor), (int) (25 * heightFactor), (int) (500 * widthFactor), (int) (90 * heightFactor));  //REg to Bus
        fDrawFilledTriangle(g, objFP.cBus2RegisterOutline, (int) (500 * widthFactor), (int) (90 * heightFactor), (int) (495 * widthFactor), (int) (80 * heightFactor), (int) (505 * widthFactor), (int) (80 * heightFactor));

        g.setColor(objFP.cBus2RegisterFill);

        g.drawLine((int) (500 * widthFactor), (int) (25 * heightFactor), (int) (500 * widthFactor), (int) (90 * heightFactor));  //REg to Bus
        fDrawFilledTriangle(g, objFP.cBus2RegisterFill, (int) (500 * widthFactor), (int) (90 * heightFactor), (int) (495 * widthFactor), (int) (80 * heightFactor), (int) (505 * widthFactor), (int) (80 * heightFactor));
    }

    private void logic2Negative(Graphics g) {
        g.setColor(objFP.cLogic2NegativeOutline);

        g.drawLine((int) (183 * widthFactor), (int) (335 * heightFactor), (int) (183 * widthFactor), (int) (375 * heightFactor));   //Negative to Logic
        g.drawLine((int) (300 * widthFactor), (int) (375 * heightFactor), (int) (183 * widthFactor), (int) (375 * heightFactor));   //Negative to Logic
        fDrawFilledTriangle(g, objFP.cLogic2NegativeOutline, (int) (183 * widthFactor), (int) (335 * heightFactor), (int) (178 * widthFactor), (int) (345 * heightFactor), (int) (188 * widthFactor), (int) (345 * heightFactor));

        g.setColor(objFP.cLogic2NegativeFill);

        g.drawLine((int) (183 * widthFactor), (int) (335 * heightFactor), (int) (183 * widthFactor), (int) (375 * heightFactor));   //Negative to Logic
        g.drawLine((int) (300 * widthFactor), (int) (375 * heightFactor), (int) (183 * widthFactor), (int) (375 * heightFactor));   //Negative to Logic
        fDrawFilledTriangle(g, objFP.cLogic2NegativeFill, (int) (183 * widthFactor), (int) (335 * heightFactor), (int) (178 * widthFactor), (int) (345 * heightFactor), (int) (188 * widthFactor), (int) (345 * heightFactor));
    }

    private void logic2Zero(Graphics g) {
        g.setColor(objFP.cLogic2ZeroOutline);

        g.drawLine((int) (300 * widthFactor), (int) (365 * heightFactor), (int) (223 * widthFactor), (int) (365 * heightFactor));   //Zero to Logic
        g.drawLine((int) (223 * widthFactor), (int) (335 * heightFactor), (int) (223 * widthFactor), (int) (365 * heightFactor));   //Zero to Logic
        fDrawFilledTriangle(g, objFP.cLogic2ZeroOutline, (int) (223 * widthFactor), (int) (335 * heightFactor), (int) (218 * widthFactor), (int) (345 * heightFactor), (int) (228 * widthFactor), (int) (345 * heightFactor));

        g.setColor(objFP.cLogic2ZeroFill);
        g.drawLine((int) (300 * widthFactor), (int) (365 * heightFactor), (int) (223 * widthFactor), (int) (365 * heightFactor));   //Zero to Logic
        g.drawLine((int) (223 * widthFactor), (int) (335 * heightFactor), (int) (223 * widthFactor), (int) (365 * heightFactor));   //Zero to Logic
        fDrawFilledTriangle(g, objFP.cLogic2ZeroFill, (int) (223 * widthFactor), (int) (335 * heightFactor), (int) (218 * widthFactor), (int) (345 * heightFactor), (int) (228 * widthFactor), (int) (345 * heightFactor));
    }

    private void logic2Positive(Graphics g) {
        g.setColor(objFP.cLogic2PositiveOutline);
        g.drawLine((int) (263 * widthFactor), (int) (335 * heightFactor), (int) (263 * widthFactor), (int) (355 * heightFactor));   //Positive to Logic
        g.drawLine((int) (300 * widthFactor), (int) (355 * heightFactor), (int) (263 * widthFactor), (int) (355 * heightFactor));  //Positive to Logic
        fDrawFilledTriangle(g, objFP.cLogic2PositiveOutline, (int) (263 * widthFactor), (int) (335 * heightFactor), (int) (258 * widthFactor), (int) (345 * heightFactor), (int) (268 * widthFactor), (int) (345 * heightFactor));

        g.setColor(objFP.cLogic2PositiveFill);

        g.drawLine((int) (263 * widthFactor), (int) (335 * heightFactor), (int) (263 * widthFactor), (int) (355 * heightFactor));   //Positive to Logic
        g.drawLine((int) (300 * widthFactor), (int) (355 * heightFactor), (int) (263 * widthFactor), (int) (355 * heightFactor));  //Positive to Logic
        fDrawFilledTriangle(g, objFP.cLogic2PositiveFill, (int) (263 * widthFactor), (int) (335 * heightFactor), (int) (258 * widthFactor), (int) (345 * heightFactor), (int) (268 * widthFactor), (int) (345 * heightFactor));
    }

    private void Bus2Logic(Graphics g) {
        g.setColor(objFP.cBus2LogicOutline);

        g.drawLine((int) (350 * widthFactor), (int) (365 * heightFactor), (int) (375 * widthFactor), (int) (365 * heightFactor));  //Logic to bus
        g.drawLine((int) (375 * widthFactor), (int) (400 * heightFactor), (int) (375 * widthFactor), (int) (365 * heightFactor));  //Logic to bus
        fDrawFilledTriangle(g, objFP.cBus2LogicOutline, (int) (350 * widthFactor), (int) (365 * heightFactor), (int) (360 * widthFactor), (int) (370 * heightFactor), (int) (360 * widthFactor), (int) (360 * heightFactor));

        g.setColor(objFP.cBus2LogicFill);
        g.drawLine((int) (350 * widthFactor), (int) (365 * heightFactor), (int) (375 * widthFactor), (int) (365 * heightFactor));  //Logic to bus
        g.drawLine((int) (375 * widthFactor), (int) (400 * heightFactor), (int) (375 * widthFactor), (int) (365 * heightFactor));  //Logic to bus
        fDrawFilledTriangle(g, objFP.cBus2LogicFill, (int) (350 * widthFactor), (int) (365 * heightFactor), (int) (360 * widthFactor), (int) (370 * heightFactor), (int) (360 * widthFactor), (int) (360 * heightFactor));
    }

    private void Bus2PCMux(Graphics g) {
        g.setColor(objFP.cBus2PCMuxOutline);

        g.drawLine((int) (375 * widthFactor), (int) (25 * heightFactor), (int) (375 * widthFactor), (int) (160 * heightFactor));    //PCMUX to bus
        g.drawLine((int) (265 * widthFactor), (int) (130 * heightFactor), (int) (265 * widthFactor), (int) (160 * heightFactor));   //PCMUX to bus
        g.drawLine((int) (265 * widthFactor), (int) (160 * heightFactor), (int) (375 * widthFactor), (int) (160 * heightFactor));   //PCMUX to bus
        fDrawFilledTriangle(g, objFP.cBus2PCMuxOutline, (int) (265 * widthFactor), (int) (130 * heightFactor), (int) (260 * widthFactor), (int) (135 * heightFactor), (int) (270 * widthFactor), (int) (135 * heightFactor));

        g.setColor(objFP.cBus2PCMuxFill);
        g.drawLine((int) (375 * widthFactor), (int) (25 * heightFactor), (int) (375 * widthFactor), (int) (160 * heightFactor));    //PCMUX to bus
        g.drawLine((int) (265 * widthFactor), (int) (130 * heightFactor), (int) (265 * widthFactor), (int) (160 * heightFactor));   //PCMUX to bus
        g.drawLine((int) (265 * widthFactor), (int) (160 * heightFactor), (int) (375 * widthFactor), (int) (160 * heightFactor));   //PCMUX to bus
        fDrawFilledTriangle(g, objFP.cBus2PCMuxFill, (int) (265 * widthFactor), (int) (130 * heightFactor), (int) (260 * widthFactor), (int) (135 * heightFactor), (int) (270 * widthFactor), (int) (135 * heightFactor));
    }

    public void Plus12PCMux(Graphics g) {
        g.setColor(objFP.cPlus_1_2PCOutline);
        g.drawLine((int) (285 * widthFactor), (int) (130 * heightFactor), (int) (285 * widthFactor), (int) (150 * heightFactor));  //PCMUX to +1
        g.drawLine((int) (330 * widthFactor), (int) (150 * heightFactor), (int) (330 * widthFactor), (int) (110 * heightFactor)); //PCMUX to +1
        g.drawLine((int) (285 * widthFactor), (int) (150 * heightFactor), (int) (330 * widthFactor), (int) (150 * heightFactor));  //PCMUX to +1
        fDrawFilledTriangle(g, objFP.cPlus_1_2PCOutline, (int) (285 * widthFactor), (int) (130 * heightFactor), (int) (280 * widthFactor), (int) (135 * heightFactor), (int) (290 * widthFactor), (int) (135 * heightFactor));

        g.setColor(objFP.cPlus_1_2PCFill);

        g.drawLine((int) (285 * widthFactor), (int) (130 * heightFactor), (int) (285 * widthFactor), (int) (150 * heightFactor));  //PCMUX to +1
        g.drawLine((int) (330 * widthFactor), (int) (150 * heightFactor), (int) (330 * widthFactor), (int) (110 * heightFactor)); //PCMUX to +1
        g.drawLine((int) (285 * widthFactor), (int) (150 * heightFactor), (int) (330 * widthFactor), (int) (150 * heightFactor));  //PCMUX to +1
        fDrawFilledTriangle(g, objFP.cPlus_1_2PCFill, (int) (285 * widthFactor), (int) (130 * heightFactor), (int) (280 * widthFactor), (int) (135 * heightFactor), (int) (290 * widthFactor), (int) (135 * heightFactor));
    }

    public void PCMux2PC(Graphics g) {
        g.setColor(objFP.cPCMux2PCOutline);

        g.drawLine((int) (265 * widthFactor), (int) (110 * heightFactor), (int) (265 * widthFactor), (int) (90 * heightFactor));   //PC to PCMUX
        fDrawFilledTriangle(g, objFP.cPCMux2PCOutline, (int) (265 * widthFactor), (int) (90 * heightFactor), (int) (260 * widthFactor), (int) (95 * heightFactor), (int) (270 * widthFactor), (int) (95 * heightFactor));

        g.setColor(objFP.cPCMux2PCFill);
        g.drawLine((int) (265 * widthFactor), (int) (110 * heightFactor), (int) (265 * widthFactor), (int) (90 * heightFactor));   //PC to PCMUX
        fDrawFilledTriangle(g, objFP.cPCMux2PCFill, (int) (265 * widthFactor), (int) (90 * heightFactor), (int) (260 * widthFactor), (int) (95 * heightFactor), (int) (270 * widthFactor), (int) (95 * heightFactor));
    }

    public void At2PCMux(Graphics g) {
        g.setColor(objFP.cAt2PCMuxOutline);

        g.drawLine((int) (160 * widthFactor), (int) (100 * heightFactor), (int) (160 * widthFactor), (int) (140 * heightFactor));  //@ to PCMUX
        g.drawLine((int) (235 * widthFactor), (int) (140 * heightFactor), (int) (235 * widthFactor), (int) (130 * heightFactor));  //@ to PCMUX
        g.drawLine((int) (160 * widthFactor), (int) (140 * heightFactor), (int) (235 * widthFactor), (int) (140 * heightFactor));  //@ to PCMUX
        fDrawFilledTriangle(g, objFP.cAt2PCMuxOutline, (int) (235 * widthFactor), (int) (130 * heightFactor), (int) (230 * widthFactor), (int) (135 * heightFactor), (int) (240 * widthFactor), (int) (135 * heightFactor));
        g.fillOval((int) (157 * widthFactor), (int) (100 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor)); //@ PCMUX - MARMUX

        g.setColor(objFP.cAt2PCMuxFill);

        g.drawLine((int) (160 * widthFactor), (int) (100 * heightFactor), (int) (160 * widthFactor), (int) (140 * heightFactor));  //@ to PCMUX
        g.drawLine((int) (235 * widthFactor), (int) (140 * heightFactor), (int) (235 * widthFactor), (int) (130 * heightFactor));  //@ to PCMUX
        g.drawLine((int) (160 * widthFactor), (int) (140 * heightFactor), (int) (235 * widthFactor), (int) (140 * heightFactor));  //@ to PCMUX
        fDrawFilledTriangle(g, objFP.cAt2PCMuxFill, (int) (235 * widthFactor), (int) (130 * heightFactor), (int) (230 * widthFactor), (int) (135 * heightFactor), (int) (240 * widthFactor), (int) (135 * heightFactor));
        g.fillOval((int) (157 * widthFactor), (int) (100 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor)); //@ PCMUX - MARMUX
    }

    public void Memory2MDR(Graphics g) {
        g.setColor(objFP.cMemory2MDROutline);
        g.drawLine((int) (55 * widthFactor), (int) (440 * heightFactor), (int) (55 * widthFactor), (int) (465 * heightFactor));    //Memory to MDR
        g.drawLine((int) (55 * widthFactor), (int) (465 * heightFactor), (int) (100 * widthFactor), (int) (465 * heightFactor));  //Memory to MDR
        fDrawFilledTriangle(g, objFP.cMemory2MDROutline, (int) (55 * widthFactor), (int) (440 * heightFactor), (int) (50 * widthFactor), (int) (450 * heightFactor), (int) (60 * widthFactor), (int) (450 * heightFactor));

        g.setColor(objFP.cMemory2MDRFill);

        g.drawLine((int) (55 * widthFactor), (int) (440 * heightFactor), (int) (55 * widthFactor), (int) (465 * heightFactor));    //Memory to MDR
        g.drawLine((int) (55 * widthFactor), (int) (465 * heightFactor), (int) (100 * widthFactor), (int) (465 * heightFactor));  //Memory to MDR
        fDrawFilledTriangle(g, objFP.cMemory2MDRFill, (int) (55 * widthFactor), (int) (440 * heightFactor), (int) (50 * widthFactor), (int) (450 * heightFactor), (int) (60 * widthFactor), (int) (450 * heightFactor));
    }

    public void MDR2Memory(Graphics g) {
        g.setColor(objFP.cMDR2MemoryOutline);
        g.drawLine((int) (75 * widthFactor), (int) (450 * heightFactor), (int) (100 * widthFactor), (int) (450 * heightFactor));  //MDR to memory
        g.drawLine((int) (75 * widthFactor), (int) (440 * heightFactor), (int) (75 * widthFactor), (int) (450 * heightFactor));  //MDR to Memory
        fDrawFilledTriangle(g, objFP.cMDR2MemoryOutline, (int) (100 * widthFactor), (int) (450 * heightFactor), (int) (90 * widthFactor), (int) (445 * heightFactor), (int) (90 * widthFactor), (int) (455 * heightFactor));

        g.setColor(objFP.cMDR2MemoryFill);

        g.drawLine((int) (75 * widthFactor), (int) (450 * heightFactor), (int) (100 * widthFactor), (int) (450 * heightFactor));  //MDR to memory
        g.drawLine((int) (75 * widthFactor), (int) (440 * heightFactor), (int) (75 * widthFactor), (int) (450 * heightFactor));  //MDR to Memory
        fDrawFilledTriangle(g, objFP.cMDR2MemoryFill, (int) (100 * widthFactor), (int) (450 * heightFactor), (int) (90 * widthFactor), (int) (445 * heightFactor), (int) (90 * widthFactor), (int) (455 * heightFactor));
    }

    private void Mar2Output(Graphics g) {
        g.setColor(objFP.cMAR2OutputOutline);
        g.drawLine((int) (245 * widthFactor), (int) (440 * heightFactor), (int) (245 * widthFactor), (int) (500 * heightFactor)); //MAR  to Output
        g.drawLine((int) (245 * widthFactor), (int) (500 * heightFactor), (int) (575 * widthFactor), (int) (500 * heightFactor));   // MAR to Output
        g.drawLine((int) (575 * widthFactor), (int) (460 * heightFactor), (int) (575 * widthFactor), (int) (500 * heightFactor));   // MAR to output
        fDrawFilledTriangle(g, objFP.cMAR2OutputOutline, (int) (575 * widthFactor), (int) (460 * heightFactor), (int) (570 * widthFactor), (int) (470 * heightFactor), (int) (580 * widthFactor), (int) (470 * heightFactor));
        g.fillOval((int) (243 * widthFactor), (int) (447 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor)); //MAR

        g.setColor(objFP.cMAR2OutputFill);

        g.drawLine((int) (245 * widthFactor), (int) (440 * heightFactor), (int) (245 * widthFactor), (int) (500 * heightFactor)); //MAR  to Output
        g.drawLine((int) (245 * widthFactor), (int) (500 * heightFactor), (int) (575 * widthFactor), (int) (500 * heightFactor));   // MAR to Output
        g.drawLine((int) (575 * widthFactor), (int) (460 * heightFactor), (int) (575 * widthFactor), (int) (500 * heightFactor));   // MAR to output
        fDrawFilledTriangle(g, objFP.cMAR2OutputFill, (int) (575 * widthFactor), (int) (460 * heightFactor), (int) (570 * widthFactor), (int) (470 * heightFactor), (int) (580 * widthFactor), (int) (470 * heightFactor));
        g.fillOval((int) (243 * widthFactor), (int) (447 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor)); //MAR
    }

    public void IR2At(Graphics g) {
        g.setColor(objFP.cIR2AtOutline);

        g.drawLine((int) (35 * widthFactor), (int) (225 * heightFactor), (int) (125 * widthFactor), (int) (225 * heightFactor));   //@ to IR
        g.drawLine((int) (125 * widthFactor), (int) (225 * heightFactor), (int) (125 * widthFactor), (int) (60 * heightFactor));     //@ to IR
        g.drawLine((int) (125 * widthFactor), (int) (60 * heightFactor), (int) (155 * widthFactor), (int) (60 * heightFactor));  //@ to IR
        g.drawLine((int) (155 * widthFactor), (int) (60 * heightFactor), (int) (155 * widthFactor), (int) (70 * heightFactor));  //@ to IR
        fDrawFilledTriangle(g, objFP.cIR2AtOutline, (int) (155 * widthFactor), (int) (70 * heightFactor), (int) (150 * widthFactor), (int) (65 * heightFactor), (int) (160 * widthFactor), (int) (65 * heightFactor));
        g.fillOval((int) (32 * widthFactor), (int) (207 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor)); // IR to @
        g.fillOval((int) (72 * widthFactor), (int) (297 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));   //IR to ZEXT

        g.setColor(objFP.cIR2AtFill);

        g.drawLine((int) (35 * widthFactor), (int) (225 * heightFactor), (int) (125 * widthFactor), (int) (225 * heightFactor));   //@ to IR
        g.drawLine((int) (125 * widthFactor), (int) (225 * heightFactor), (int) (125 * widthFactor), (int) (60 * heightFactor));     //@ to IR
        g.drawLine((int) (125 * widthFactor), (int) (60 * heightFactor), (int) (155 * widthFactor), (int) (60 * heightFactor));  //@ to IR
        g.drawLine((int) (155 * widthFactor), (int) (60 * heightFactor), (int) (155 * widthFactor), (int) (70 * heightFactor));  //@ to IR
        fDrawFilledTriangle(g, objFP.cIR2AtFill, (int) (155 * widthFactor), (int) (70 * heightFactor), (int) (150 * widthFactor), (int) (65 * heightFactor), (int) (160 * widthFactor), (int) (65 * heightFactor));
        g.fillOval((int) (32 * widthFactor), (int) (207 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor)); // IR to @
        g.fillOval((int) (72 * widthFactor), (int) (297 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));   //IR to ZEXT
    }

    public void PC2At(Graphics g) {
        g.setColor(objFP.cPC2AtOutline);
        g.drawLine((int) (175 * widthFactor), (int) (60 * heightFactor), (int) (265 * widthFactor), (int) (60 * heightFactor));  //@ to PC
        g.drawLine((int) (175 * widthFactor), (int) (60 * heightFactor), (int) (175 * widthFactor), (int) (70 * heightFactor));   //@ to PC
        fDrawFilledTriangle(g, objFP.cPC2AtOutline, (int) (175 * widthFactor), (int) (70 * heightFactor), (int) (170 * widthFactor), (int) (65 * heightFactor), (int) (180 * widthFactor), (int) (65 * heightFactor));

        g.setColor(objFP.cPC2AtFill);

        g.drawLine((int) (175 * widthFactor), (int) (60 * heightFactor), (int) (265 * widthFactor), (int) (60 * heightFactor));  //@ to PC
        g.drawLine((int) (175 * widthFactor), (int) (60 * heightFactor), (int) (175 * widthFactor), (int) (70 * heightFactor));   //@ to PC
        fDrawFilledTriangle(g, objFP.cPC2AtFill, (int) (175 * widthFactor), (int) (70 * heightFactor), (int) (170 * widthFactor), (int) (65 * heightFactor), (int) (180 * widthFactor), (int) (65 * heightFactor));
    }

    public void At2MarMux(Graphics g) {
        g.setColor(objFP.cAt2MarMuxOutline);
        g.drawLine((int) (90 * widthFactor), (int) (90 * heightFactor), (int) (90 * widthFactor), (int) (103 * heightFactor));     // @ , MARMX
        g.drawLine((int) (160 * widthFactor), (int) (103 * heightFactor), (int) (160 * widthFactor), (int) (90 * heightFactor));   // @, MARMX
        g.drawLine((int) (90 * widthFactor), (int) (103 * heightFactor), (int) (160 * widthFactor), (int) (103 * heightFactor));   //@ ,MARMX
        fDrawFilledTriangle(g, objFP.cAt2MarMuxOutline, (int) (90 * widthFactor), (int) (90 * heightFactor), (int) (85 * widthFactor), (int) (95 * heightFactor), (int) (95 * widthFactor), (int) (95 * heightFactor));
        g.fillOval((int) (157 * widthFactor), (int) (100 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor)); //@ PCMUX - MARMUX


        g.setColor(objFP.cAt2MarMuxFill);

        g.drawLine((int) (90 * widthFactor), (int) (90 * heightFactor), (int) (90 * widthFactor), (int) (103 * heightFactor));     // @ , MARMX
        g.drawLine((int) (160 * widthFactor), (int) (103 * heightFactor), (int) (160 * widthFactor), (int) (90 * heightFactor));   // @, MARMX
        g.drawLine((int) (90 * widthFactor), (int) (103 * heightFactor), (int) (160 * widthFactor), (int) (103 * heightFactor));   //@ ,MARMX
        fDrawFilledTriangle(g, objFP.cAt2MarMuxFill, (int) (90 * widthFactor), (int) (90 * heightFactor), (int) (85 * widthFactor), (int) (95 * heightFactor), (int) (95 * widthFactor), (int) (95 * heightFactor));
        g.fillOval((int) (157 * widthFactor), (int) (100 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor)); //@ PCMUX - MARMUX
    }

    public void Sext2ALUInput(Graphics g) {
        g.setColor(objFP.cSext2ALUInputOutline);
        g.drawLine((int) (330 * widthFactor), (int) (210 * heightFactor), (int) (350 * widthFactor), (int) (210 * heightFactor)); // ALU Input 1 to SEXT HL
        g.drawLine((int) (350 * widthFactor), (int) (235 * heightFactor), (int) (350 * widthFactor), (int) (210 * heightFactor)); // ALU Input 1 to SEXT VL
        g.drawLine((int) (445 * widthFactor), (int) (235 * heightFactor), (int) (350 * widthFactor), (int) (235 * heightFactor)); // ALU Input 1 to SEXT HL
        g.drawLine((int) (445 * widthFactor), (int) (235 * heightFactor), (int) (445 * widthFactor), (int) (250 * heightFactor)); // ALU Input 1 to SEXT VL
        fDrawFilledTriangle(g, objFP.cSext2ALUInputOutline, (int) (445 * widthFactor), (int) (250 * heightFactor), (int) (440 * widthFactor), (int) (245 * heightFactor), (int) (450 * widthFactor), (int) (245 * heightFactor));

        g.setColor(objFP.cSext2ALUInputFill);

        g.drawLine((int) (330 * widthFactor), (int) (210 * heightFactor), (int) (350 * widthFactor), (int) (210 * heightFactor)); // ALU Input 1 to SEXT HL
        g.drawLine((int) (350 * widthFactor), (int) (235 * heightFactor), (int) (350 * widthFactor), (int) (210 * heightFactor)); // ALU Input 1 to SEXT VL
        g.drawLine((int) (445 * widthFactor), (int) (235 * heightFactor), (int) (350 * widthFactor), (int) (235 * heightFactor)); // ALU Input 1 to SEXT HL
        g.drawLine((int) (445 * widthFactor), (int) (235 * heightFactor), (int) (445 * widthFactor), (int) (250 * heightFactor)); // ALU Input 1 to SEXT VL
        fDrawFilledTriangle(g, objFP.cSext2ALUInputFill, (int) (445 * widthFactor), (int) (250 * heightFactor), (int) (440 * widthFactor), (int) (245 * heightFactor), (int) (450 * widthFactor), (int) (245 * heightFactor));
    }

    public void PC2Plus1(Graphics g) {
        g.setColor(objFP.cPC2Plus_1_Outline);
        g.drawLine((int) (265 * widthFactor), (int) (60 * heightFactor), (int) (330 * widthFactor), (int) (60 * heightFactor));  // +1 to PC H L
        g.drawLine((int) (330 * widthFactor), (int) (90 * heightFactor), (int) (330 * widthFactor), (int) (60 * heightFactor));   // +1 to PC V L
        fDrawFilledTriangle(g, objFP.cPC2Plus_1_Outline, (int) (330 * widthFactor), (int) (90 * heightFactor), (int) (325 * widthFactor), (int) (85 * heightFactor), (int) (335 * widthFactor), (int) (85 * heightFactor));

        g.setColor(objFP.cPC2Plus_1_Fill);

        g.drawLine((int) (265 * widthFactor), (int) (60 * heightFactor), (int) (330 * widthFactor), (int) (60 * heightFactor));  // +1 to PC H L
        g.drawLine((int) (330 * widthFactor), (int) (90 * heightFactor), (int) (330 * widthFactor), (int) (60 * heightFactor));   // +1 to PC V L
        fDrawFilledTriangle(g, objFP.cPC2Plus_1_Fill, (int) (330 * widthFactor), (int) (90 * heightFactor), (int) (325 * widthFactor), (int) (85 * heightFactor), (int) (335 * widthFactor), (int) (85 * heightFactor));
    }

    public void IR2Zext2(Graphics g) {
        g.setColor(objFP.cIR2Zext2Outline);

        g.drawLine((int) (95 * widthFactor), (int) (180 * heightFactor), (int) (95 * widthFactor), (int) (190 * heightFactor));  //IR to ZEXT 2 V L
        g.drawLine((int) (35 * widthFactor), (int) (190 * heightFactor), (int) (95 * widthFactor), (int) (190 * heightFactor));         //IR to ZEXT 2 H L
        fDrawFilledTriangle(g, objFP.cIR2Zext2Outline, (int) (95 * widthFactor), (int) (180 * heightFactor), (int) (90 * widthFactor), (int) (185 * heightFactor), (int) (100 * widthFactor), (int) (185 * heightFactor));
        g.fillOval((int) (32 * widthFactor), (int) (187 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));     //IR to ZEXT 2
        g.fillOval((int) (72 * widthFactor), (int) (297 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));   //IR to ZEXT

        g.setColor(objFP.cIR2Zext2Fill);

        g.drawLine((int) (95 * widthFactor), (int) (180 * heightFactor), (int) (95 * widthFactor), (int) (190 * heightFactor));  //IR to ZEXT 2 V L
        g.drawLine((int) (35 * widthFactor), (int) (190 * heightFactor), (int) (95 * widthFactor), (int) (190 * heightFactor));         //IR to ZEXT 2 H L
        fDrawFilledTriangle(g, objFP.cIR2Zext2Fill, (int) (95 * widthFactor), (int) (180 * heightFactor), (int) (90 * widthFactor), (int) (185 * heightFactor), (int) (100 * widthFactor), (int) (185 * heightFactor));
        g.fillOval((int) (32 * widthFactor), (int) (187 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));     //IR to ZEXT 2
        g.fillOval((int) (72 * widthFactor), (int) (297 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));   //IR to ZEXT
    }

    public void reg2Plus(Graphics g) {
        g.setColor(objFP.cReg2PlusOutline);

        g.drawLine((int) (100 * widthFactor), (int) (150 * heightFactor), (int) (250 * widthFactor), (int) (150 * heightFactor));  // + to PCMUX/ALU/Reg  H L
        g.drawLine((int) (100 * widthFactor), (int) (150 * heightFactor), (int) (100 * widthFactor), (int) (140 * heightFactor));  // + to PCMUX/ALU/Reg  V L
        fDrawFilledTriangle(g, objFP.cReg2PlusOutline, (int) (100 * widthFactor), (int) (140 * heightFactor), (int) (95 * widthFactor), (int) (145 * heightFactor), (int) (105 * widthFactor), (int) (145 * heightFactor));
        g.fillOval((int) (247 * widthFactor), (int) (147 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));     //PCMUX to PLUS
        g.fillOval((int) (517 * widthFactor), (int) (222 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));   //REG to ALU

        g.setColor(objFP.cReg2PlusFill);

        g.drawLine((int) (100 * widthFactor), (int) (150 * heightFactor), (int) (250 * widthFactor), (int) (150 * heightFactor));  // + to PCMUX/ALU/Reg  H L
        g.drawLine((int) (100 * widthFactor), (int) (150 * heightFactor), (int) (100 * widthFactor), (int) (140 * heightFactor));  // + to PCMUX/ALU/Reg  V L
        fDrawFilledTriangle(g, objFP.cReg2PlusFill, (int) (100 * widthFactor), (int) (140 * heightFactor), (int) (95 * widthFactor), (int) (145 * heightFactor), (int) (105 * widthFactor), (int) (145 * heightFactor));
        g.fillOval((int) (247 * widthFactor), (int) (147 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));     //PCMUX to PLUS
        g.fillOval((int) (517 * widthFactor), (int) (222 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));   //REG to ALU
    }

    public void reg2PCMux(Graphics g) {
        g.setColor(objFP.cReg2PCMuxOutline);

        g.drawLine((int) (520 * widthFactor), (int) (225 * heightFactor), (int) (400 * widthFactor), (int) (225 * heightFactor));  // REG/ALU to PCMUX H L
        g.drawLine((int) (400 * widthFactor), (int) (225 * heightFactor), (int) (400 * widthFactor), (int) (175 * heightFactor));  // REG/ALU to PCMUX V L
        g.drawLine((int) (250 * widthFactor), (int) (130 * heightFactor), (int) (250 * widthFactor), (int) (175 * heightFactor));  // REG/ALU to PCMUX V L
        g.drawLine((int) (250 * widthFactor), (int) (175 * heightFactor), (int) (400 * widthFactor), (int) (175 * heightFactor));  // REG/ALU to PCMUX H L
        fDrawFilledTriangle(g, objFP.cReg2PCMuxOutline, (int) (250 * widthFactor), (int) (130 * heightFactor), (int) (245 * widthFactor), (int) (135 * heightFactor), (int) (255 * widthFactor), (int) (135 * heightFactor));
        g.fillOval((int) (247 * widthFactor), (int) (147 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));     //PCMUX to PLUS
        g.fillOval((int) (517 * widthFactor), (int) (222 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));   //REG to ALU

        g.setColor(objFP.cReg2PCMuxFill);

        g.drawLine((int) (520 * widthFactor), (int) (225 * heightFactor), (int) (400 * widthFactor), (int) (225 * heightFactor));  // REG/ALU to PCMUX H L
        g.drawLine((int) (400 * widthFactor), (int) (225 * heightFactor), (int) (400 * widthFactor), (int) (175 * heightFactor));  // REG/ALU to PCMUX V L
        g.drawLine((int) (250 * widthFactor), (int) (130 * heightFactor), (int) (250 * widthFactor), (int) (175 * heightFactor));  // REG/ALU to PCMUX V L
        g.drawLine((int) (250 * widthFactor), (int) (175 * heightFactor), (int) (400 * widthFactor), (int) (175 * heightFactor));  // REG/ALU to PCMUX H L
        fDrawFilledTriangle(g, objFP.cReg2PCMuxFill, (int) (250 * widthFactor), (int) (130 * heightFactor), (int) (245 * widthFactor), (int) (135 * heightFactor), (int) (255 * widthFactor), (int) (135 * heightFactor));
        g.fillOval((int) (247 * widthFactor), (int) (147 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));     //PCMUX to PLUS
        g.fillOval((int) (517 * widthFactor), (int) (222 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));   //REG to ALU
    }

    public void plus2MarMux(Graphics g) {
        g.setColor(objFP.cPlus2MarMuxOutline);

        g.drawLine((int) (95 * widthFactor), (int) (120 * heightFactor), (int) (95 * widthFactor), (int) (110 * heightFactor));  // PLUS to MARMUX  V L
        g.drawLine((int) (70 * widthFactor), (int) (110 * heightFactor), (int) (95 * widthFactor), (int) (110 * heightFactor));  // PLUS to MARMUX  H L
        g.drawLine((int) (70 * widthFactor), (int) (90 * heightFactor), (int) (70 * widthFactor), (int) (110 * heightFactor));    //Plus to MARMUX  V L
        fDrawFilledTriangle(g, objFP.cPlus2MarMuxOutline, (int) (70 * widthFactor), (int) (90 * heightFactor), (int) (65 * widthFactor), (int) (95 * heightFactor), (int) (75 * widthFactor), (int) (95 * heightFactor));

        g.setColor(objFP.cPlus2MarMuxFill);

        g.drawLine((int) (95 * widthFactor), (int) (120 * heightFactor), (int) (95 * widthFactor), (int) (110 * heightFactor));  // PLUS to MARMUX  V L
        g.drawLine((int) (70 * widthFactor), (int) (110 * heightFactor), (int) (95 * widthFactor), (int) (110 * heightFactor));  // PLUS to MARMUX  H L
        g.drawLine((int) (70 * widthFactor), (int) (90 * heightFactor), (int) (70 * widthFactor), (int) (110 * heightFactor));    //Plus to MARMUX  V L
        fDrawFilledTriangle(g, objFP.cPlus2MarMuxFill, (int) (70 * widthFactor), (int) (90 * heightFactor), (int) (65 * widthFactor), (int) (95 * heightFactor), (int) (75 * widthFactor), (int) (95 * heightFactor));
    }

    public void Zext2MarMux(Graphics g) {
        g.setColor(objFP.cZext2MarMuxOutline);

        g.drawLine((int) (35 * widthFactor), (int) (110 * heightFactor), (int) (35 * widthFactor), (int) (140 * heightFactor));  // ZEXT to MARMUX  V L
        g.drawLine((int) (35 * widthFactor), (int) (110 * heightFactor), (int) (50 * widthFactor), (int) (110 * heightFactor));   // ZEXT to MARMUX  H L
        g.drawLine((int) (50 * widthFactor), (int) (90 * heightFactor), (int) (50 * widthFactor), (int) (110 * heightFactor));  // ZEXT to MARMUX  V L
        fDrawFilledTriangle(g, objFP.cZext2MarMuxOutline, (int) (50 * widthFactor), (int) (90 * heightFactor), (int) (45 * widthFactor), (int) (95 * heightFactor), (int) (55 * widthFactor), (int) (95 * heightFactor));

        g.setColor(objFP.cZext2MarMuxFill);

        g.drawLine((int) (35 * widthFactor), (int) (110 * heightFactor), (int) (35 * widthFactor), (int) (140 * heightFactor));  // ZEXT to MARMUX  V L
        g.drawLine((int) (35 * widthFactor), (int) (110 * heightFactor), (int) (50 * widthFactor), (int) (110 * heightFactor));   // ZEXT to MARMUX  H L
        g.drawLine((int) (50 * widthFactor), (int) (90 * heightFactor), (int) (50 * widthFactor), (int) (110 * heightFactor));  // ZEXT to MARMUX  V L
        fDrawFilledTriangle(g, objFP.cZext2MarMuxFill, (int) (50 * widthFactor), (int) (90 * heightFactor), (int) (45 * widthFactor), (int) (95 * heightFactor), (int) (55 * widthFactor), (int) (95 * heightFactor));
    }

    public void Zext2Plus(Graphics g) {
        g.setColor(objFP.cZext2PlusOutline);

        g.drawLine((int) (85 * widthFactor), (int) (140 * heightFactor), (int) (85 * widthFactor), (int) (160 * heightFactor));   //ZEXT TO PLUS
        fDrawFilledTriangle(g, objFP.cZext2PlusOutline, (int) (85 * widthFactor), (int) (140 * heightFactor), (int) (80 * widthFactor), (int) (145 * heightFactor), (int) (90 * widthFactor), (int) (145 * heightFactor));

        g.setColor(objFP.cZext2PlusFill);

        g.drawLine((int) (85 * widthFactor), (int) (140 * heightFactor), (int) (85 * widthFactor), (int) (160 * heightFactor));   //ZEXT TO PLUS
        fDrawFilledTriangle(g, objFP.cZext2PlusFill, (int) (85 * widthFactor), (int) (140 * heightFactor), (int) (80 * widthFactor), (int) (145 * heightFactor), (int) (90 * widthFactor), (int) (145 * heightFactor));
    }

    public void IR2Zext_1(Graphics g) {
        g.setColor(objFP.cIR2ZEXT_1BusOutline);

        g.drawLine((int) (35 * widthFactor), (int) (160 * heightFactor), (int) (35 * widthFactor), (int) (300 * heightFactor)); // IR to ZEXT 1  - V L
        g.drawLine((int) (35 * widthFactor), (int) (300 * heightFactor), (int) (75 * widthFactor), (int) (300 * heightFactor));  // IR to ZEXT 1 - H L
        fDrawFilledTriangle(g, objFP.cIR2ZEXT_1BusOutline, (int) (35 * widthFactor), (int) (160 * heightFactor), (int) (30 * widthFactor), (int) (165 * heightFactor), (int) (40 * widthFactor), (int) (165 * heightFactor));
        g.fillOval((int) (72 * widthFactor), (int) (297 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));   //IR to ZEXT

        g.setColor(objFP.cIR2ZEXT_1BusFill);

        g.drawLine((int) (35 * widthFactor), (int) (160 * heightFactor), (int) (35 * widthFactor), (int) (300 * heightFactor)); // IR to ZEXT 1  - V L
        g.drawLine((int) (35 * widthFactor), (int) (300 * heightFactor), (int) (75 * widthFactor), (int) (300 * heightFactor));  // IR to ZEXT 1 - H L
        fDrawFilledTriangle(g, objFP.cIR2ZEXT_1BusFill, (int) (35 * widthFactor), (int) (160 * heightFactor), (int) (30 * widthFactor), (int) (165 * heightFactor), (int) (40 * widthFactor), (int) (165 * heightFactor));
        g.fillOval((int) (72 * widthFactor), (int) (297 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor));   //IR to ZEXT
    }

    public void PC2Bus(Graphics g) {
        g.setColor(objFP.cPC2BusOutline);

        g.drawLine((int) (265 * widthFactor), (int) (25 * heightFactor), (int) (265 * widthFactor), (int) (70 * heightFactor));  //PC To BUS
        fDrawFilledTriangle(g, objFP.cPC2BusOutline, (int) (265 * widthFactor), (int) (25 * heightFactor), (int) (260 * widthFactor), (int) (35 * heightFactor), (int) (270 * widthFactor), (int) (35 * heightFactor));
        g.fillOval((int) (262 * widthFactor), (int) (57 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor)); //  PC to  BUS

        g.setColor(objFP.cPC2BusFill);

        g.drawLine((int) (265 * widthFactor), (int) (25 * heightFactor), (int) (265 * widthFactor), (int) (70 * heightFactor));  //PC To BUS
        fDrawFilledTriangle(g, objFP.cPC2BusFill, (int) (265 * widthFactor), (int) (25 * heightFactor), (int) (260 * widthFactor), (int) (35 * heightFactor), (int) (270 * widthFactor), (int) (35 * heightFactor));
        g.fillOval((int) (262 * widthFactor), (int) (57 * heightFactor), (int) (6 * widthFactor), (int) (6 * heightFactor)); //  PC to  BUS
    }

    private void positive2ControlLogic(Graphics g) {
        g.setColor(objFP.cPositive2ControlLogicOutline);

        g.drawLine((int) (263 * widthFactor), (int) (280 * heightFactor), (int) (263 * widthFactor), (int) (310 * heightFactor));  //P  to Control Logic
        fDrawFilledTriangle(g, objFP.cPositive2ControlLogicOutline, (int) (263 * widthFactor), (int) (280 * heightFactor), (int) (258 * widthFactor), (int) (290 * heightFactor), (int) (268 * widthFactor), (int) (290 * heightFactor));

        g.setColor(objFP.cPositive2ControlLogicFill);

        g.drawLine((int) (263 * widthFactor), (int) (280 * heightFactor), (int) (263 * widthFactor), (int) (310 * heightFactor));  //P  to Control Logic
        fDrawFilledTriangle(g, objFP.cPositive2ControlLogicFill, (int) (263 * widthFactor), (int) (280 * heightFactor), (int) (258 * widthFactor), (int) (290 * heightFactor), (int) (268 * widthFactor), (int) (290 * heightFactor));
    }

    private void zero2ControlLogic(Graphics g) {
        g.setColor(objFP.cZero2ControlLogicOutline);

        g.drawLine((int) (223 * widthFactor), (int) (280 * heightFactor), (int) (223 * widthFactor), (int) (310 * heightFactor));  //Z   to Control Logic
        fDrawFilledTriangle(g, objFP.cZero2ControlLogicOutline, (int) (223 * widthFactor), (int) (280 * heightFactor), (int) (218 * widthFactor), (int) (290 * heightFactor), (int) (228 * widthFactor), (int) (290 * heightFactor));

        g.setColor(objFP.cZeroControlLogicFill);

        g.drawLine((int) (223 * widthFactor), (int) (280 * heightFactor), (int) (223 * widthFactor), (int) (310 * heightFactor));  //Z   to Control Logic
        fDrawFilledTriangle(g, objFP.cZeroControlLogicFill, (int) (223 * widthFactor), (int) (280 * heightFactor), (int) (218 * widthFactor), (int) (290 * heightFactor), (int) (228 * widthFactor), (int) (290 * heightFactor));
    }

    private void negative2ControlLogic(Graphics g) {
        g.setColor(objFP.cNegative2ControlLogicOutline);

        g.drawLine((int) (183 * widthFactor), (int) (280 * heightFactor), (int) (183 * widthFactor), (int) (310 * heightFactor)); //N  to Control Logic
        fDrawFilledTriangle(g, objFP.cNegative2ControlLogicOutline, (int) (183 * widthFactor), (int) (280 * heightFactor), (int) (178 * widthFactor), (int) (290 * heightFactor), (int) (188 * widthFactor), (int) (290 * heightFactor));

        g.setColor(objFP.cNegative2ControlLogicFill);

        g.drawLine((int) (183 * widthFactor), (int) (280 * heightFactor), (int) (183 * widthFactor), (int) (310 * heightFactor)); //N  to Control Logic
        fDrawFilledTriangle(g, objFP.cNegative2ControlLogicFill, (int) (183 * widthFactor), (int) (280 * heightFactor), (int) (178 * widthFactor), (int) (290 * heightFactor), (int) (188 * widthFactor), (int) (290 * heightFactor));
    }

    public void update(Graphics g) {
        System.out.println("UPDATE CALLED");
        paint(g);
    }


    public void drawPlusOne(Graphics g) {
        g.setColor(objFP.cPlusOneFill);
        g.fillRect((int) (320 * widthFactor), (int) (90 * heightFactor), (int) (20 * widthFactor), (int) (20 * heightFactor));

        g.setColor(objFP.cPlusOneOutline);
        g.drawRect((int) (320 * widthFactor), (int) (90 * heightFactor), (int) (20 * widthFactor), (int) (20 * heightFactor));

        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("+1", (int) (322 * widthFactor), (int) (102 * heightFactor));
    }


    public void drawProgramCounter(Graphics g) {
        g.setColor(objFP.cPCFill);
        g.fillRect((int) (240 * widthFactor), (int) (70 * heightFactor), (int) (50 * widthFactor), (int) (20 * heightFactor));

        g.setColor(objFP.cPCOutline);
        g.drawRect((int) (240 * widthFactor), (int) (70 * heightFactor), (int) (50 * widthFactor), (int) (20 * heightFactor));

        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("PC", (int) (260 * widthFactor), (int) (85 * heightFactor));
    }

    public void drawAT(Graphics g) {
        g.setColor(objFP.cATFill);
        g.fillRect((int) (150 * widthFactor), (int) (70 * heightFactor), (int) (30 * widthFactor), (int) (20 * heightFactor));

        g.setColor(objFP.cATOutline);
        g.drawRect((int) (150 * widthFactor), (int) (70 * heightFactor), (int) (30 * widthFactor), (int) (20 * heightFactor));

        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("@", (int) (160 * widthFactor), (int) (82 * heightFactor));
    }

    public void drawPLUS(Graphics g) {
        g.setColor(objFP.cPlusFill);
        g.fillRect((int) (80 * widthFactor), (int) (120 * heightFactor), (int) (30 * widthFactor), (int) (20 * heightFactor));

        g.setColor(objFP.cPlusOutline);
        g.drawRect((int) (80 * widthFactor), (int) (120 * heightFactor), (int) (30 * widthFactor), (int) (20 * heightFactor));

        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("+", (int) (92 * widthFactor), (int) (134 * heightFactor));
    }

    public void drawZEXT_2(Graphics g) {
        g.setColor(objFP.cZEXT_2Fill);
        g.fillRect((int) (15 * widthFactor), (int) (140 * heightFactor), (int) (35 * widthFactor), (int) (20 * heightFactor));

        g.setColor(objFP.cZEXT_2Outline);
        g.drawRect((int) (15 * widthFactor), (int) (140 * heightFactor), (int) (35 * widthFactor), (int) (20 * heightFactor));

        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("ZEXT", (int) (18 * widthFactor), (int) (154 * heightFactor));
    }

    public void drawZEXT_1(Graphics g) {
        g.setColor(objFP.cZEXT_1Fill);
        g.fillRect((int) (75 * widthFactor), (int) (160 * heightFactor), (int) (35 * widthFactor), (int) (20 * heightFactor));

        g.setColor(objFP.cZEXT_1Outline);
        g.drawRect((int) (75 * widthFactor), (int) (160 * heightFactor), (int) (35 * widthFactor), (int) (20 * heightFactor));

        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("ZEXT", (int) (78 * widthFactor), (int) (174 * heightFactor));
    }

    public void drawSEXT(Graphics g) {
        g.setColor(objFP.cSEXTFill);
        g.fillRect((int) (280 * widthFactor), (int) (200 * heightFactor), (int) (50 * widthFactor), (int) (20 * heightFactor));

        g.setColor(objFP.cSEXTOutline);
        g.drawRect((int) (280 * widthFactor), (int) (200 * heightFactor), (int) (50 * widthFactor), (int) (20 * heightFactor));

        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("SEXT", (int) (290 * widthFactor), (int) (215 * heightFactor));
    }

    public void drawPC_MUX(Graphics g) {
        fDrawFilledParallelogram(g, objFP.cPCMuxFill, (int) (240 * widthFactor), (int) (110 * heightFactor), (int) (285 * widthFactor), (int) (110 * heightFactor), (int) (230 * widthFactor), (int) (130 * heightFactor), (int) (295 * widthFactor), (int) (130 * heightFactor));
        fDrawParallelogram(g, objFP.cPCMuxOutline, (int) (240 * widthFactor), (int) (110 * heightFactor), (int) (285 * widthFactor), (int) (110 * heightFactor), (int) (230 * widthFactor), (int) (130 * heightFactor), (int) (295 * widthFactor), (int) (130 * heightFactor));

        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("PCMUX", (int) (239 * widthFactor), (int) (125 * heightFactor));
    }

    public void drawMAR_MUX(Graphics g) {
        fDrawFilledParallelogram(g, objFP.cMARMuxFill, (int) (40 * widthFactor), (int) (90 * heightFactor), (int) (100 * widthFactor), (int) (90 * heightFactor), (int) (50 * widthFactor), (int) (70 * heightFactor), (int) (90 * widthFactor), (int) (70 * heightFactor));
        fDrawParallelogram(g, objFP.cMARMuxOutline, (int) (40 * widthFactor), (int) (90 * heightFactor), (int) (100 * widthFactor), (int) (90 * heightFactor), (int) (50 * widthFactor), (int) (70 * heightFactor), (int) (90 * widthFactor), (int) (70 * heightFactor));

        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("MARMUX", (int) (5 * widthFactor), (int) (65 * heightFactor));
    }


    public void drawALUInput(Graphics g) {
        fDrawFilledParallelogram(g, objFP.cALUInputFill, (int) (435 * widthFactor), (int) (250 * heightFactor), (int) (475 * widthFactor), (int) (250 * heightFactor), (int) (445 * widthFactor), (int) (265 * heightFactor), (int) (465 * widthFactor), (int) (265 * heightFactor));
        fDrawParallelogram(g, objFP.cALUInputOutline, (int) (435 * widthFactor), (int) (250 * heightFactor), (int) (475 * widthFactor), (int) (250 * heightFactor), (int) (445 * widthFactor), (int) (265 * heightFactor), (int) (465 * widthFactor), (int) (265 * heightFactor));
    }

    private void drawLogic(Graphics g) {


        g.setColor(objFP.cLogicFill);
        g.fillRect((int) (300 * widthFactor), (int) (350 * heightFactor), (int) (50 * widthFactor), (int) (30 * heightFactor)); //LOGIC

        g.setColor(objFP.cLogicOutline);
        g.drawRect((int) (300 * widthFactor), (int) (350 * heightFactor), (int) (50 * widthFactor), (int) (30 * heightFactor)); //LOGIC

        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("LOGIC", (int) (305 * widthFactor), (int) (370 * heightFactor));

    }

    private void drawPositive(Graphics g) {


        g.setColor(objFP.cPositiveFill);
        g.fillRect((int) (250 * widthFactor), (int) (310 * heightFactor), (int) (25 * widthFactor), (int) (25 * heightFactor)); //Positive

        g.setColor(objFP.cPositiveOutline);
        g.drawRect((int) (250 * widthFactor), (int) (310 * heightFactor), (int) (25 * widthFactor), (int) (25 * heightFactor)); //Positive

        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("P", (int) (257 * widthFactor), (int) (327 * heightFactor));
    }


    private void drawZero(Graphics g) {


        g.setColor(objFP.cZeroFill);
        g.fillRect((int) (210 * widthFactor), (int) (310 * heightFactor), (int) (25 * widthFactor), (int) (25 * heightFactor));  //Zero

        g.setColor(objFP.cZeroOutline);
        g.drawRect((int) (210 * widthFactor), (int) (310 * heightFactor), (int) (25 * widthFactor), (int) (25 * heightFactor));  //Zero

        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("Z", (int) (217 * widthFactor), (int) (327 * heightFactor));
    }


    private void drawNegative(Graphics g) {


        g.setColor(objFP.cNegativeFill);
        g.fillRect((int) (170 * widthFactor), (int) (310 * heightFactor), (int) (25 * widthFactor), (int) (25 * heightFactor)); //Negative

        g.setColor(objFP.cNegativeOutline);
        g.drawRect((int) (170 * widthFactor), (int) (310 * heightFactor), (int) (25 * widthFactor), (int) (25 * heightFactor)); //Negative

        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("N", (int) (177 * widthFactor), (int) (327 * heightFactor));
    }


    public void drawInstructionRegister(Graphics g) {


        g.setColor(objFP.cIRFill);
        g.fillRect((int) (30 * widthFactor), (int) (310 * heightFactor), (int) (100 * widthFactor), (int) (25 * heightFactor)); //IR

        g.setColor(objFP.cIROutline);
        g.drawRect((int) (30 * widthFactor), (int) (310 * heightFactor), (int) (100 * widthFactor), (int) (25 * heightFactor)); //IR

        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("I R", (int) (70 * widthFactor), (int) (330 * heightFactor));
    }


    private void drawCRTSR(Graphics g) {


        g.setColor(objFP.cCRTSRFill);

        g.fillRect((int) (625 * widthFactor), (int) (430 * heightFactor), (int) (35 * widthFactor), (int) (20 * heightFactor)); //CRTSR

        g.setColor(objFP.cCRTSROutline);

        g.drawRect((int) (625 * widthFactor), (int) (430 * heightFactor), (int) (35 * widthFactor), (int) (20 * heightFactor)); //CRTSR
    }

    private void drawCRTDR(Graphics g) {


        g.setColor(objFP.cCRTDRFill);
        g.fillRect((int) (560 * widthFactor), (int) (430 * heightFactor), (int) (35 * widthFactor), (int) (20 * heightFactor)); //CRTDR

        g.setColor(objFP.cCRTDROutline);
        g.drawRect((int) (560 * widthFactor), (int) (430 * heightFactor), (int) (35 * widthFactor), (int) (20 * heightFactor)); //CRTDR
    }


    private void drawKBDR(Graphics g) {


        g.setColor(objFP.cKBDRFill);
        g.fillRect((int) (475 * widthFactor), (int) (430 * heightFactor), (int) (35 * widthFactor), (int) (20 * heightFactor)); //KBDR

        g.setColor(objFP.cKBDROutline);
        g.drawRect((int) (475 * widthFactor), (int) (430 * heightFactor), (int) (35 * widthFactor), (int) (20 * heightFactor)); //KBDR
    }


    private void drawKBSR(Graphics g) {

        g.setColor(objFP.cKBSRFill);

        g.fillRect((int) (410 * widthFactor), (int) (430 * heightFactor), (int) (35 * widthFactor), (int) (20 * heightFactor)); //KBSR

        g.setColor(objFP.cKBSROutline);

        g.drawRect((int) (410 * widthFactor), (int) (430 * heightFactor), (int) (35 * widthFactor), (int) (20 * heightFactor)); //KBSR
    }

    public void drawControlLogic(Graphics g) {

        g.setColor(objFP.cControlLogicFill);

        g.fillRect((int) (50 * widthFactor), (int) (250 * heightFactor), (int) (230 * widthFactor), (int) (30 * heightFactor)); //Control Logic

        g.setColor(objFP.cControlLogicOutline);

        g.drawRect((int) (50 * widthFactor), (int) (250 * heightFactor), (int) (230 * widthFactor), (int) (30 * heightFactor)); //Control Logic
        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("CONTROL LOGIC", (int) (105 * widthFactor), (int) (270 * heightFactor));
    }


    private void drawInput(Graphics g) {


        g.setColor(objFP.cInputFill);
        g.fillRect((int) (400 * widthFactor), (int) (420 * heightFactor), (int) (120 * widthFactor), (int) (40 * heightFactor)); //Input

        g.setColor(objFP.cInputOutline);
        g.drawRect((int) (400 * widthFactor), (int) (420 * heightFactor), (int) (120 * widthFactor), (int) (40 * heightFactor)); //Input

        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("INPUT", (int) (445 * widthFactor), (int) (480 * heightFactor));
    }

    private void drawOutput(Graphics g) {

        g.setColor(objFP.cOutputFill);

        g.fillRect((int) (550 * widthFactor), (int) (420 * heightFactor), (int) (120 * widthFactor), (int) (40 * heightFactor)); //Output

        g.setColor(objFP.cOutputOutline);

        g.drawRect((int) (550 * widthFactor), (int) (420 * heightFactor), (int) (120 * widthFactor), (int) (40 * heightFactor)); //Output
        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("OUTPUT", (int) (590 * widthFactor), (int) (480 * heightFactor));
    }

    public void drawMAR(Graphics g) {

        g.setColor(objFP.cMARFill);

        g.fillRect((int) (230 * widthFactor), (int) (420 * heightFactor), (int) (30 * widthFactor), (int) (20 * heightFactor)); //Memory Address Register

        g.setColor(objFP.cMAROutline);

        g.drawRect((int) (230 * widthFactor), (int) (420 * heightFactor), (int) (30 * widthFactor), (int) (20 * heightFactor)); //Memory Address Register
        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("MAR", (int) (270 * widthFactor), (int) (435 * heightFactor));
    }

    public void drawMDR(Graphics g) {

        g.setColor(objFP.cMDRFill);

        g.fillRect((int) (50 * widthFactor), (int) (420 * heightFactor), (int) (30 * widthFactor), (int) (20 * heightFactor)); //Memory Address Register

        g.setColor(objFP.cMDROutline);

        g.drawRect((int) (50 * widthFactor), (int) (420 * heightFactor), (int) (30 * widthFactor), (int) (20 * heightFactor)); //Memory Address Register
        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("MDR", (int) (15 * widthFactor), (int) (435 * heightFactor));
    }

    public void drawMemory(Graphics g) {

        g.setColor(objFP.cMemoryFill);

        g.fillRect((int) (100 * widthFactor), (int) (440 * heightFactor), (int) (100 * widthFactor), (int) (30 * heightFactor)); //Memory

        g.setColor(objFP.cMemoryOutline);

        g.drawRect((int) (100 * widthFactor), (int) (440 * heightFactor), (int) (100 * widthFactor), (int) (30 * heightFactor)); //Memory
        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("MEMORY", (int) (125 * widthFactor), (int) (460 * heightFactor));
    }

    public void fDrawMaximized() {
        try {
            if ((widthFactor * 2.0f * 0.7f < 25)) {
                widthFactor = widthFactor * 2.0f * 0.7f;
                heightFactor = heightFactor * 2.0f * 0.7f;

                setPreferredSize(new Dimension((int) (800 * widthFactor), (int) (600 * heightFactor)));
                repaint();
                revalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fDrawMinimized() {
        try {
            if ((widthFactor * 2.0f * 0.7f > 0.2f)) {
                widthFactor = (widthFactor) / (2.0f * 0.7f);
                heightFactor = (heightFactor) / (2.0f * 0.7f);

                setPreferredSize(new Dimension((int) (800 * widthFactor), (int) (600 * heightFactor)));
                repaint();
                revalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void drawALU(Graphics g) {
        //To get Screen Resolution.

        Polygon pALU = new Polygon();
        pALU.addPoint((int) (435 * widthFactor), (int) (300 * heightFactor));
        pALU.addPoint((int) (475 * widthFactor), (int) (300 * heightFactor));

        pALU.addPoint((int) (485 * widthFactor), (int) (310 * heightFactor));
        pALU.addPoint((int) (495 * widthFactor), (int) (300 * heightFactor));

        pALU.addPoint((int) (535 * widthFactor), (int) (300 * heightFactor));
        pALU.addPoint((int) (510 * widthFactor), (int) (330 * heightFactor));

        pALU.addPoint((int) (460 * widthFactor), (int) (330 * heightFactor));

        g.setColor(objFP.cALUFill);

        g.fillPolygon(pALU);

        g.setColor(objFP.cALUOutline);

        g.drawPolygon(pALU);

        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("ALU", (int) (472 * widthFactor), (int) (325 * heightFactor));
    }

    public void fDrawRegister(int registerNo, Graphics g) {
        switch (registerNo) {
            case 0:
                fDrawRegister00(g);
                break;
            case 1:
                fDrawRegister01(g);
                break;
            case 2:
                fDrawRegister02(g);
                break;
            case 3:
                fDrawRegister03(g);
                break;
            case 4:
                fDrawRegister04(g);
                break;
            case 5:
                fDrawRegister05(g);
                break;
            case 6:
                fDrawRegister06(g);
                break;
            case 7:
                fDrawRegister07(g);
                break;
        }
    }

    private void fDrawRegister00(Graphics g) {
        g.setColor(objFP.cRegister00Fill);

        g.fillRect((int) (450 * widthFactor), (int) (100 * heightFactor), (int) (50 * widthFactor), (int) (25 * heightFactor));  // register 1

        g.setColor(objFP.cRegister00Outline);

        g.drawRect((int) (450 * widthFactor), (int) (100 * heightFactor), (int) (50 * widthFactor), (int) (25 * heightFactor));  // register 1

        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("R0", (int) (465 * widthFactor), (int) (120 * heightFactor));
    }

    private void fDrawRegister02(Graphics g) {
        g.setColor(objFP.cRegister02Fill);

        g.fillRect((int) (450 * widthFactor), (int) (125 * heightFactor), (int) (50 * widthFactor), (int) (25 * heightFactor));  // register 3

        g.setColor(objFP.cRegister02Outline);

        g.drawRect((int) (450 * widthFactor), (int) (125 * heightFactor), (int) (50 * widthFactor), (int) (25 * heightFactor));  // register 3
        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("R2", (int) (465 * widthFactor), (int) (145 * heightFactor));
    }


    private void fDrawRegister04(Graphics g) {
        g.setColor(objFP.cRegister04Fill);

        g.fillRect((int) (450 * widthFactor), (int) (150 * heightFactor), (int) (50 * widthFactor), (int) (25 * heightFactor));  // register 5

        g.setColor(objFP.cRegister04Outline);

        g.drawRect((int) (450 * widthFactor), (int) (150 * heightFactor), (int) (50 * widthFactor), (int) (25 * heightFactor));  // register 5
        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("R4", (int) (465 * widthFactor), (int) (170 * heightFactor));
    }

    private void fDrawRegister06(Graphics g) {
        g.setColor(objFP.cRegister06Fill);

        g.fillRect((int) (450 * widthFactor), (int) (175 * heightFactor), (int) (50 * widthFactor), (int) (25 * heightFactor));  // register 7

        g.setColor(objFP.cRegister06Outline);

        g.drawRect((int) (450 * widthFactor), (int) (175 * heightFactor), (int) (50 * widthFactor), (int) (25 * heightFactor));  // register 7
        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("R6", (int) (465 * widthFactor), (int) (195 * heightFactor));
    }


    private void fDrawRegister01(Graphics g) {
        g.setColor(objFP.cRegister01Fill);

        g.fillRect((int) (500 * widthFactor), (int) (100 * heightFactor), (int) (50 * widthFactor), (int) (25 * heightFactor));  // register 2

        g.setColor(objFP.cRegister01Outline);

        g.drawRect((int) (500 * widthFactor), (int) (100 * heightFactor), (int) (50 * widthFactor), (int) (25 * heightFactor));  // register 2
        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("R1", (int) (515 * widthFactor), (int) (120 * heightFactor));
    }


    private void fDrawRegister03(Graphics g) {
        g.setColor(objFP.cRegister03Fill);

        g.fillRect((int) (500 * widthFactor), (int) (125 * heightFactor), (int) (50 * widthFactor), (int) (25 * heightFactor));  // register 4

        g.setColor(objFP.cRegister03Outline);

        g.drawRect((int) (500 * widthFactor), (int) (125 * heightFactor), (int) (50 * widthFactor), (int) (25 * heightFactor));  // register 4
        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("R3", (int) (515 * widthFactor), (int) (145 * heightFactor));
    }

    private void fDrawRegister05(Graphics g) {
        g.setColor(objFP.cRegister05Fill);

        g.fillRect((int) (500 * widthFactor), (int) (150 * heightFactor), (int) (50 * widthFactor), (int) (25 * heightFactor));  // register 6

        g.setColor(objFP.cRegister05Outline);

        g.drawRect((int) (500 * widthFactor), (int) (150 * heightFactor), (int) (50 * widthFactor), (int) (25 * heightFactor));  // register 6
        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("R5", (int) (515 * widthFactor), (int) (170 * heightFactor));
    }

    private void fDrawRegister07(Graphics g) {
        g.setColor(objFP.cRegister07Fill);

        g.fillRect((int) (500 * widthFactor), (int) (175 * heightFactor), (int) (50 * widthFactor), (int) (25 * heightFactor));  // register 8

        g.setColor(objFP.cRegister07Outline);

        g.drawRect((int) (500 * widthFactor), (int) (175 * heightFactor), (int) (50 * widthFactor), (int) (25 * heightFactor));  // register 8
        g.setFont(new Font("ARIAL", Font.BOLD, (int) (14 * heightFactor)));
        g.drawString("R7", (int) (515 * widthFactor), (int) (195 * heightFactor));
    }


    private void drawRegisters(Graphics g) {
        g.setColor(objFP.cRegistersFill);

        g.fillRect((int) (440 * widthFactor), (int) (90 * heightFactor), (int) (120 * widthFactor), (int) (120 * heightFactor)); // registers

        g.setColor(objFP.cRegistersOutline);

        g.drawRect((int) (440 * widthFactor), (int) (90 * heightFactor), (int) (120 * widthFactor), (int) (120 * heightFactor)); // registers
        fDrawRegister00(g);
        fDrawRegister01(g);
        fDrawRegister02(g);
        fDrawRegister03(g);
        fDrawRegister04(g);
        fDrawRegister05(g);
        fDrawRegister06(g);
        fDrawRegister07(g);
    }

    private void fDrawFilledParallelogram(Graphics g, Color c, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        g.setColor(c);

        Polygon pgnParallelogram = new Polygon();
        pgnParallelogram.addPoint(x1, y1);
        pgnParallelogram.addPoint(x2, y2);
        pgnParallelogram.addPoint(x4, y4);
        pgnParallelogram.addPoint(x3, y3);

        g.fillPolygon(pgnParallelogram);
    }

    private void fDrawParallelogram(Graphics g, Color c, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        g.setColor(c);

        Polygon pgnParallelogram = new Polygon();
        pgnParallelogram.addPoint(x1, y1);
        pgnParallelogram.addPoint(x2, y2);
        pgnParallelogram.addPoint(x4, y4);
        pgnParallelogram.addPoint(x3, y3);

        g.drawPolygon(pgnParallelogram);
    }


    private void fDrawTriangle(Graphics g, Color c, int x1, int y1, int x2, int y2, int x3, int y3) {
        g.setColor(c);

        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x3, y3);
        g.drawLine(x1, y1, x3, y3);
    }

    private void fDrawFilledTriangle(Graphics g, Color c, int x1, int y1, int x2, int y2, int x3, int y3) {
        fDrawTriangle(g, c, x1, y1, x2, y2, x3, y3);

        Polygon pTriangle = new Polygon();
        pTriangle.addPoint(x1, y1);
        pTriangle.addPoint(x2, y2);
        pTriangle.addPoint(x3, y3);

        g.setColor(c);
        g.fillPolygon(pTriangle);
    }

    public void fResetFlow() {
        objFP.cRegister00Outline = Color.black; //Color.black;
        objFP.cRegister00Fill = Color.white;

        objFP.cRegister01Outline = Color.black;
        objFP.cRegister01Fill = Color.white;

        objFP.cRegister02Outline = Color.black;
        objFP.cRegister02Fill = Color.white;

        objFP.cRegister03Outline = Color.black;
        objFP.cRegister03Fill = Color.white;

        objFP.cRegister04Outline = Color.black;
        objFP.cRegister04Fill = Color.white;

        objFP.cRegister05Outline = Color.black;
        objFP.cRegister05Fill = Color.white;

        objFP.cRegister06Outline = Color.black;
        objFP.cRegister06Fill = Color.white;

        objFP.cRegister07Outline = Color.black;
        objFP.cRegister07Fill = Color.white;

        objFP.cRegContainerOutline = Color.black;
        objFP.cRegContainerFill = Color.white;

        objFP.cMemoryOutline = Color.black;
        objFP.cMemoryFill = Color.white;

        objFP.cMAROutline = Color.black;
        objFP.cMARFill = Color.white;

        objFP.cMDROutline = Color.black;
        objFP.cMDRFill = Color.white;

        objFP.cInputOutline = Color.black;
        objFP.cInputFill = Color.white;

        objFP.cKBSROutline = Color.black;
        objFP.cKBSRFill = Color.white;

        objFP.cCRTDROutline = Color.black;
        objFP.cCRTDRFill = Color.white;


        objFP.cControlLogicOutline = Color.black;
        objFP.cControlLogicFill = Color.white;


        objFP.cIROutline = Color.black;
        objFP.cIRFill = Color.white;


        objFP.cNegativeOutline = Color.black;
        objFP.cNegativeFill = Color.white;

        objFP.cZeroOutline = Color.black;
        objFP.cZeroFill = Color.white;

        objFP.cPositiveOutline = Color.black;
        objFP.cPositiveFill = Color.white;

        objFP.cALUOutline = Color.black;
        objFP.cALUFill = Color.white;

        objFP.cMARMuxOutline = Color.black;
        objFP.cMARMuxFill = Color.white;

        objFP.cSEXTOutline = Color.black;
        objFP.cSEXTFill = Color.white;

        objFP.cZEXT_1Outline = Color.black;
        objFP.cZEXT_1Fill = Color.white;

        objFP.cZEXT_2Outline = Color.black;
        objFP.cZEXT_2Fill = Color.white;

        objFP.cPlusOneOutline = Color.black;
        objFP.cPlusOneFill = Color.white;

        objFP.cPlusOutline = Color.black;
        objFP.cPlusFill = Color.white;

        objFP.cPCOutline = Color.black;
        objFP.cPCFill = Color.white;

        objFP.cATOutline = Color.black;
        objFP.cATFill = Color.white;

        objFP.cLogicOutline = Color.black;
        objFP.cLogicFill = Color.white;

        objFP.cBUSOutline = Color.white;
        objFP.cBUSFill = Color.black;

        objFP.cReg2ALU_1_Outline = Color.white;
        objFP.cReg2ALU_1_Fill = Color.black;


        objFP.cReg2ALU_2_Outline = Color.white;
        objFP.cReg2ALU_2_Fill = Color.black;

        objFP.cReg2ALUInputOutline = Color.white;
        objFP.cReg2ALUInputFill = Color.black;

        objFP.cALU2BusOutline = Color.white;
        objFP.cALU2BusFill = Color.black;

        objFP.cBus2RegisterOutline = Color.white;
        objFP.cBus2RegisterFill = Color.black;

        objFP.cBus2CRTDROutline = Color.white;
        objFP.cBus2CRTDRFill = Color.black;

        objFP.cBus2CRTSROutline = Color.white;
        objFP.cBus2CRTSRFill = Color.black;

        objFP.cCRTSR2BusOutline = Color.white;
        objFP.cCRTSR2BusFill = Color.black;

        objFP.cKBSR2BusOutline = Color.white;
        objFP.cKBSR2BusFill = Color.black;


        objFP.cBus2KBSROutline = Color.white;
        objFP.cBus2KBSRFill = Color.black;

        objFP.cBus2MAROutline = Color.white;
        objFP.cBus2MARFill = Color.black;

        objFP.cMDR2BusOutline = Color.white;
        objFP.cMDR2BusFill = Color.black;


        objFP.cBus2MDROutline = Color.white;
        objFP.cBus2MDRFill = Color.black;

        objFP.cBus2IROutline = Color.white;
        objFP.cBus2IRFill = Color.black;

        objFP.cIR2ControlLogicOutline = Color.white;
        objFP.cIR2ControlLogicFill = Color.black;

        objFP.cNegative2ControlLogicOutline = Color.white;
        objFP.cNegative2ControlLogicFill = Color.black;

        objFP.cZero2ControlLogicOutline = Color.white;
        objFP.cZeroControlLogicFill = Color.black;

        objFP.cPositive2ControlLogicOutline = Color.white;
        objFP.cPositive2ControlLogicFill = Color.black;

        objFP.cMarMux2BusOutline = Color.white;
        objFP.cMarMux2BusFill = Color.black;

        objFP.cPC2BusOutline = Color.white;
        objFP.cPC2BusFill = Color.black;

        objFP.cIR2ZEXT_1BusOutline = Color.white;
        objFP.cIR2ZEXT_1BusFill = Color.black;

        objFP.cIR2SextOutline = Color.white;
        objFP.cIR2SextFill = Color.black;

        objFP.cZext2PlusOutline = Color.white;
        objFP.cZext2PlusFill = Color.black;

        objFP.cZext2MarMuxOutline = Color.white;
        objFP.cZext2MarMuxFill = Color.black;

        objFP.cPlus2MarMuxOutline = Color.white;
        objFP.cPlus2MarMuxFill = Color.black;

        objFP.cReg2PCMuxOutline = Color.white;
        objFP.cReg2PCMuxFill = Color.black;

        objFP.cReg2PlusOutline = Color.white;
        objFP.cReg2PlusFill = Color.black;

        objFP.cIR2Zext2Outline = Color.white;
        objFP.cIR2Zext2Fill = Color.black;

        objFP.cPC2Plus_1_Outline = Color.white;
        objFP.cPC2Plus_1_Fill = Color.black;

        objFP.cSext2ALUInputOutline = Color.white;
        objFP.cSext2ALUInputFill = Color.black;

        objFP.cAt2MarMuxOutline = Color.white;
        objFP.cAt2MarMuxFill = Color.black;

        objFP.cPC2AtOutline = Color.white;
        objFP.cPC2AtFill = Color.black;

        objFP.cIR2AtOutline = Color.white;
        objFP.cIR2AtFill = Color.black;

        objFP.cMAR2MemoryOutline = Color.white;
        objFP.cMAR2MemoryFill = Color.black;

        objFP.cMAR2InputOutline = Color.white;
        objFP.cMAR2InputFill = Color.black;

        objFP.cMAR2OutputOutline = Color.white;
        objFP.cMAR2OutputFill = Color.black;

        objFP.cMDR2MemoryOutline = Color.white;
        objFP.cMDR2MemoryFill = Color.black;

        objFP.cMemory2MDROutline = Color.white;
        objFP.cMemory2MDRFill = Color.black;

        objFP.cAt2PCMuxOutline = Color.white;
        objFP.cAt2PCMuxFill = Color.black;

        objFP.cPCMux2PCOutline = Color.white;
        objFP.cPCMux2PCFill = Color.black;

        objFP.cPlus_1_2PCOutline = Color.white;
        objFP.cPlus_1_2PCFill = Color.black;

        objFP.cBus2PCMuxOutline = Color.white;
        objFP.cBus2PCMuxFill = Color.black;

        objFP.cLogic2PositiveOutline = Color.white;
        objFP.cLogic2PositiveFill = Color.black;

        objFP.cBus2LogicOutline = Color.white;
        objFP.cBus2LogicFill = Color.black;

        objFP.cLogic2ZeroOutline = Color.white;
        objFP.cLogic2ZeroFill = Color.black;

        objFP.cLogic2NegativeOutline = Color.white;
        objFP.cLogic2NegativeFill = Color.black;

        objFP.cPCMuxOutline = Color.black;
        objFP.cPCMuxFill = Color.white;

        objFP.cALUInputOutline = Color.black;
        objFP.cALUInputFill = Color.white;

        objFP.cCRTSROutline = Color.black;
        objFP.cCRTSRFill = Color.white;

        objFP.cKBDROutline = Color.black;
        objFP.cKBDRFill = Color.white;

        objFP.cOutputOutline = Color.black;
        objFP.cOutputFill = Color.white;

        objFP.cRegistersOutline = Color.black;
        objFP.cRegistersFill = Color.white;
    }
}
