package com.github.h2002044.lc2.view;

import com.github.h2002044.lc2.Processor;

import java.awt.*;

public class DataFlow {
    private Processor objProcessor_Dataflow = new Processor();

    Color pastColor = Color.lightGray;

    Color presentColor = new Color(255, 117, 117);
    Color pastOutlineColor = Color.black;

    Color presentOutlineColor = new Color(0, 0, 0);

    private final static int HALF_SECOND = 500;
    private final static int SECOND = 500;
    private Circuit objSimulate;
    private FlowSummary objExecutionSummary;

    public DataFlow(Circuit objSimulate, FlowSummary objExecutionSummary) {
        this.objSimulate = objSimulate;
        this.objExecutionSummary = objExecutionSummary;
    }

    public void fSetPastProcessColor() {


    }

    public void fFetch() {   /*   During the micro Instruction*/

        /*First the Control logic is Activated*/

        objSimulate.objFP.cControlLogicFill = presentColor;
        objSimulate.objFP.cControlLogicOutline = presentOutlineColor;
        objSimulate.drawControlLogic(objSimulate.getGraphics());

        objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecutionSummary.fAddHeadingText("\nF E T C H    I N S T R U C T I O N -- START");
        objExecutionSummary.fAddText("\nFirst Machine cycle has started");
        objExecutionSummary.fAddText("\nControl logic gives signal to read Next Address from PC");


        objSimulate.objFP.cPCFill = presentColor;
        objSimulate.objFP.cPCOutline = presentOutlineColor;
        objSimulate.drawProgramCounter(objSimulate.getGraphics());

        makeDelay(SECOND);
        objSimulate.objFP.cPCFill = pastColor;
        objSimulate.objFP.cPCOutline = pastOutlineColor;
        objSimulate.drawProgramCounter(objSimulate.getGraphics());


        objSimulate.objFP.cPC2BusFill = presentColor;
        objSimulate.objFP.cPC2BusOutline = presentOutlineColor;
        objSimulate.PC2Bus(objSimulate.getGraphics());

        makeDelay(SECOND);


        objSimulate.objFP.cPC2BusFill = pastColor;
        objSimulate.objFP.cPC2BusOutline = pastOutlineColor;
        objSimulate.PC2Bus(objSimulate.getGraphics());

        objSimulate.objFP.cPC2Plus_1_Fill = presentColor;
        objSimulate.objFP.cPC2Plus_1_Outline = presentOutlineColor;
        objSimulate.PC2Plus1(objSimulate.getGraphics());


        makeDelay(SECOND);

        objSimulate.objFP.cPC2Plus_1_Fill = pastColor;
        objSimulate.objFP.cPC2Plus_1_Outline = pastOutlineColor;
        objSimulate.PC2Plus1(objSimulate.getGraphics());


        objSimulate.objFP.cPlusOneFill = presentColor;
        objSimulate.objFP.cPlusOneOutline = presentOutlineColor;
        objSimulate.drawPlusOne(objSimulate.getGraphics());

        makeDelay(SECOND);


        objSimulate.objFP.cPlusOneFill = pastColor;
        objSimulate.objFP.cPlusOneOutline = pastOutlineColor;
        objSimulate.drawPlusOne(objSimulate.getGraphics());

        objSimulate.objFP.cPlus_1_2PCFill = presentColor;
        objSimulate.objFP.cPlus_1_2PCOutline = presentOutlineColor;
        objSimulate.Plus12PCMux(objSimulate.getGraphics());


        makeDelay(SECOND);

        objSimulate.objFP.cPlus_1_2PCFill = pastColor;
        objSimulate.objFP.cPlus_1_2PCOutline = pastOutlineColor;
        objSimulate.Plus12PCMux(objSimulate.getGraphics());


        objSimulate.objFP.cPCMuxFill = presentColor;
        objSimulate.objFP.cPCMuxOutline = presentOutlineColor;
        objSimulate.drawPC_MUX(objSimulate.getGraphics());

        makeDelay(SECOND);


        objSimulate.objFP.cPCMuxFill = pastColor;
        objSimulate.objFP.cPCMuxOutline = pastOutlineColor;
        objSimulate.drawPC_MUX(objSimulate.getGraphics());

        objSimulate.objFP.cPCMux2PCFill = presentColor;
        objSimulate.objFP.cPCMux2PCOutline = presentOutlineColor;
        objSimulate.PCMux2PC(objSimulate.getGraphics());

        makeDelay(SECOND);

        objSimulate.objFP.cPCMux2PCFill = pastColor;
        objSimulate.objFP.cPCMux2PCOutline = pastOutlineColor;
        objSimulate.PCMux2PC(objSimulate.getGraphics());


        objSimulate.objFP.cPCFill = presentColor;
        objSimulate.objFP.cPCOutline = presentOutlineColor;
        objSimulate.drawProgramCounter(objSimulate.getGraphics());

        makeDelay(SECOND);


        objSimulate.objFP.cPCFill = pastColor;
        objSimulate.objFP.cPCOutline = pastOutlineColor;
        objSimulate.drawProgramCounter(objSimulate.getGraphics());


        objSimulate.objFP.cBUSFill = presentColor;
        objSimulate.objFP.cBUSOutline = presentOutlineColor;
        objSimulate.drawBUS(objSimulate.getGraphics());

        makeDelay(SECOND);


        objSimulate.objFP.cBUSFill = pastColor;
        objSimulate.objFP.cBUSOutline = pastOutlineColor;
        objSimulate.drawBUS(objSimulate.getGraphics());


        objSimulate.objFP.cBus2MARFill = presentColor;
        objSimulate.objFP.cBus2MAROutline = presentOutlineColor;
        objSimulate.bus2MAR(objSimulate.getGraphics());

        objExecutionSummary.fAddText("\nBus puts the Address to M.A.R");

        makeDelay(SECOND);


        objSimulate.objFP.cBus2MARFill = pastColor;
        objSimulate.objFP.cBus2MAROutline = pastOutlineColor;
        objSimulate.bus2MAR(objSimulate.getGraphics());


        objSimulate.objFP.cMARFill = presentColor;
        objSimulate.objFP.cMAROutline = presentOutlineColor;
        objSimulate.drawMAR(objSimulate.getGraphics());
        makeDelay(SECOND);

        objSimulate.objFP.cMARFill = pastColor;
        objSimulate.objFP.cMAROutline = pastOutlineColor;
        objSimulate.drawMAR(objSimulate.getGraphics());


        objSimulate.objFP.cMAR2MemoryFill = presentColor;
        objSimulate.objFP.cMAR2MemoryOutline = presentOutlineColor;
        objSimulate.MAR2Memory(objSimulate.getGraphics());
        objExecutionSummary.fAddText("\nData is searched in Memory from address in M.A.R");

        makeDelay(SECOND);

        objSimulate.objFP.cMAR2MemoryFill = pastColor;
        objSimulate.objFP.cMAR2MemoryOutline = pastOutlineColor;
        objSimulate.MAR2Memory(objSimulate.getGraphics());


        objSimulate.objFP.cMemoryFill = presentColor;
        objSimulate.objFP.cMemoryOutline = presentOutlineColor;
        objSimulate.drawMemory(objSimulate.getGraphics());
        objExecutionSummary.fAddText("\nData from memory is written to M.D.R");

        makeDelay(SECOND);

        objSimulate.objFP.cMemoryFill = pastColor;
        objSimulate.objFP.cMemoryOutline = pastOutlineColor;
        objSimulate.drawMemory(objSimulate.getGraphics());

        objSimulate.objFP.cMemory2MDRFill = presentColor;
        objSimulate.objFP.cMemory2MDROutline = presentOutlineColor;
        objSimulate.Memory2MDR(objSimulate.getGraphics());

        objSimulate.objFP.cMDRFill = presentColor;
        objSimulate.objFP.cMDROutline = presentOutlineColor;
        objSimulate.drawMDR(objSimulate.getGraphics());
        objExecutionSummary.fAddText("\nM.D.R puts the Data on the bus");

        makeDelay(SECOND);


        objSimulate.objFP.cMemory2MDRFill = pastColor;
        objSimulate.objFP.cMemory2MDROutline = pastOutlineColor;
        objSimulate.Memory2MDR(objSimulate.getGraphics());

        objSimulate.objFP.cMDRFill = pastColor;
        objSimulate.objFP.cMDROutline = pastOutlineColor;
        objSimulate.drawMDR(objSimulate.getGraphics());

        objSimulate.objFP.cMDR2BusFill = presentColor;
        objSimulate.objFP.cMDR2BusOutline = presentOutlineColor;
        objSimulate.MDR2Bus(objSimulate.getGraphics());

        makeDelay(SECOND);


        objSimulate.objFP.cMDR2BusFill = pastColor;
        objSimulate.objFP.cMDR2BusOutline = pastOutlineColor;
        objSimulate.MDR2Bus(objSimulate.getGraphics());

        objSimulate.objFP.cBUSFill = presentColor;
        objSimulate.objFP.cBUSOutline = presentOutlineColor;
        objSimulate.drawBUS(objSimulate.getGraphics());

        makeDelay(SECOND);


        objSimulate.objFP.cBUSFill = pastColor;
        objSimulate.objFP.cBUSOutline = pastOutlineColor;
        objSimulate.drawBUS(objSimulate.getGraphics());


        objSimulate.objFP.cBus2IRFill = presentColor;
        objSimulate.objFP.cBus2IROutline = presentOutlineColor;
        objSimulate.bus2IR(objSimulate.getGraphics());

        objSimulate.objFP.cIRFill = presentColor;
        objSimulate.objFP.cIROutline = presentOutlineColor;
        objSimulate.drawInstructionRegister(objSimulate.getGraphics());
        objExecutionSummary.fAddText("\nData is written from Bus to I.R");

        makeDelay(SECOND);

        objSimulate.objFP.cBus2IRFill = pastColor;
        objSimulate.objFP.cBus2IROutline = pastOutlineColor;
        objSimulate.bus2IR(objSimulate.getGraphics());


        objSimulate.objFP.cIRFill = pastColor;
        objSimulate.objFP.cIROutline = pastOutlineColor;
        objSimulate.drawInstructionRegister(objSimulate.getGraphics());

        objSimulate.objFP.cControlLogicFill = pastColor;
        objSimulate.objFP.cControlLogicOutline = pastOutlineColor;

        objSimulate.drawControlLogic(objSimulate.getGraphics());
    }

    private void makeDelay(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {

        }
    }

    public void fDecode() {
        makeDelay(SECOND);


        objSimulate.objFP.cControlLogicFill = presentColor;
        objSimulate.objFP.cControlLogicOutline = presentOutlineColor;

        objSimulate.drawControlLogic(objSimulate.getGraphics());
        objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecutionSummary.fAddHeadingText("\nD E C O D E    I N S T R U C T I O N  -- START");
        objExecutionSummary.fAddText("\nThe Instruction is being Decoded by the Internal Decoder in Control Logic");


        makeDelay(SECOND);


        /*After*/
        objSimulate.objFP.cControlLogicFill = pastColor;
        objSimulate.objFP.cControlLogicOutline = pastOutlineColor;

        objSimulate.drawControlLogic(objSimulate.getGraphics());


    }

    public void fEvaluateAddress() {
        objSimulate.objFP.cControlLogicFill = presentColor;
        objSimulate.objFP.cControlLogicOutline = presentOutlineColor;

        objSimulate.drawControlLogic(objSimulate.getGraphics());


        objExecutionSummary.fAddText("\nControl Logic is Evaluating Address of the Operands");

        makeDelay(SECOND);


        objSimulate.objFP.cControlLogicFill = pastColor;
        objSimulate.objFP.cControlLogicOutline = pastOutlineColor;

        objSimulate.drawControlLogic(objSimulate.getGraphics());


    }

    public void fFetchOperandsforMemory() {
        //fFetch();
        /*   During the micro Instruction*/

        objSimulate.objFP.cControlLogicFill = presentColor;
        objSimulate.objFP.cControlLogicOutline = presentOutlineColor;

        objSimulate.drawControlLogic(objSimulate.getGraphics());

        objSimulate.objFP.cIRFill = presentColor;
        objSimulate.objFP.cIROutline = presentOutlineColor;
        objSimulate.drawInstructionRegister(objSimulate.getGraphics());
        objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecutionSummary.fAddHeadingText("\nF E T C H    O P E R A N D S  -- START");
        objExecutionSummary.fAddText("\nI.R Places the operand address in ZEXT");

        objSimulate.objFP.cIR2ZEXT_1BusFill = presentColor;
        objSimulate.objFP.cIR2ZEXT_1BusOutline = presentOutlineColor;
        objSimulate.IR2Zext2(objSimulate.getGraphics());

        makeDelay(SECOND);

        objSimulate.objFP.cIRFill = pastColor;
        objSimulate.objFP.cIROutline = pastOutlineColor;
        objSimulate.drawInstructionRegister(objSimulate.getGraphics());

        //objSimulate.objFP.cIR

        objSimulate.objFP.cIR2ZEXT_1BusFill = pastColor;
        objSimulate.objFP.cIR2ZEXT_1BusOutline = pastColor;
        objSimulate.IR2Zext2(objSimulate.getGraphics());


        objSimulate.objFP.cZEXT_2Fill = presentColor;
        objSimulate.objFP.cZEXT_2Outline = presentOutlineColor;
        objSimulate.drawZEXT_2(objSimulate.getGraphics());
        objExecutionSummary.fAddText("\nZEXT Places the operand address in MARMUX");

        objSimulate.objFP.cZext2MarMuxFill = presentColor;
        objSimulate.objFP.cZext2MarMuxOutline = presentOutlineColor;
        objSimulate.drawZEXT_2(objSimulate.getGraphics());

        makeDelay(SECOND);

        objSimulate.objFP.cZEXT_2Fill = pastColor;
        objSimulate.objFP.cZEXT_2Outline = pastOutlineColor;
        objSimulate.drawZEXT_2(objSimulate.getGraphics());

        objSimulate.objFP.cZext2MarMuxFill = pastColor;
        objSimulate.objFP.cZext2MarMuxOutline = pastOutlineColor;
        objSimulate.Zext2MarMux(objSimulate.getGraphics());


        objSimulate.objFP.cMARMuxFill = presentColor;
        objSimulate.objFP.cMARMuxOutline = presentOutlineColor;
        objSimulate.drawMAR_MUX(objSimulate.getGraphics());


        objSimulate.objFP.cMarMux2BusFill = presentColor;
        objSimulate.objFP.cMarMux2BusOutline = presentOutlineColor;
        objSimulate.MAR_MUX2Bus(objSimulate.getGraphics());

        objExecutionSummary.fAddText("\nMARMUX Places the operand address in bus");

        makeDelay(SECOND);

        objSimulate.objFP.cMARMuxFill = pastColor;
        objSimulate.objFP.cMARMuxOutline = pastOutlineColor;
        objSimulate.drawMAR_MUX(objSimulate.getGraphics());

        objSimulate.objFP.cMarMux2BusFill = pastColor;
        objSimulate.objFP.cMarMux2BusOutline = pastOutlineColor;
        objSimulate.MAR_MUX2Bus(objSimulate.getGraphics());

        objSimulate.objFP.cBUSFill = presentColor;
        objSimulate.objFP.cBUSOutline = presentOutlineColor;
        objSimulate.drawBUS(objSimulate.getGraphics());

        makeDelay(SECOND);


        objSimulate.objFP.cBUSFill = pastColor;
        objSimulate.objFP.cBUSOutline = pastOutlineColor;
        objSimulate.drawBUS(objSimulate.getGraphics());


        objSimulate.objFP.cBus2MARFill = presentColor;
        objSimulate.objFP.cBus2MAROutline = presentOutlineColor;
        objSimulate.bus2MAR(objSimulate.getGraphics());

        objExecutionSummary.fAddText("\nThe operand address is stored in M.A.R from bus");


        objSimulate.objFP.cMARFill = presentColor;
        objSimulate.objFP.cMAROutline = presentOutlineColor;
        objSimulate.drawMAR(objSimulate.getGraphics());


        objSimulate.objFP.cBus2MARFill = pastColor;
        objSimulate.objFP.cBus2MAROutline = pastOutlineColor;
        objSimulate.bus2MAR(objSimulate.getGraphics());


        objSimulate.objFP.cMARFill = pastColor;
        objSimulate.objFP.cMAROutline = pastOutlineColor;
        objSimulate.drawMAR(objSimulate.getGraphics());


        objSimulate.objFP.cMAR2MemoryFill = presentColor;
        objSimulate.objFP.cMAR2MemoryOutline = presentOutlineColor;
        objSimulate.MAR2Memory(objSimulate.getGraphics());

        objSimulate.objFP.cMemoryFill = presentColor;
        objSimulate.objFP.cMemoryOutline = presentOutlineColor;
        objSimulate.drawMemory(objSimulate.getGraphics());
        objExecutionSummary.fAddText("\nThe operand is obtained from Memory");
        makeDelay(SECOND);


        objSimulate.objFP.cMAR2MemoryFill = pastColor;
        objSimulate.objFP.cMAR2MemoryOutline = pastOutlineColor;
        objSimulate.MAR2Memory(objSimulate.getGraphics());

        objSimulate.objFP.cMemoryFill = pastColor;
        objSimulate.objFP.cMemoryOutline = pastOutlineColor;
        objSimulate.drawMemory(objSimulate.getGraphics());


        objSimulate.objFP.cMemory2MDRFill = presentColor;
        objSimulate.objFP.cMemory2MDROutline = presentOutlineColor;
        objSimulate.Memory2MDR(objSimulate.getGraphics());

        objSimulate.objFP.cMDRFill = presentColor;
        objSimulate.objFP.cMDROutline = presentOutlineColor;
        objSimulate.drawMDR(objSimulate.getGraphics());
        objExecutionSummary.fAddText("\nThe operand is stored in M.D.R");
        makeDelay(SECOND);


        objSimulate.objFP.cMemory2MDRFill = pastColor;
        objSimulate.objFP.cMemory2MDROutline = pastOutlineColor;
        objSimulate.Memory2MDR(objSimulate.getGraphics());

        objSimulate.objFP.cMDRFill = pastColor;
        objSimulate.objFP.cMDROutline = pastOutlineColor;
        objSimulate.drawMDR(objSimulate.getGraphics());

        objSimulate.objFP.cControlLogicFill = pastColor;
        objSimulate.objFP.cControlLogicOutline = pastOutlineColor;
        objSimulate.drawControlLogic(objSimulate.getGraphics());


        objExecutionSummary.fAddHeadingText("\n\nF E T C H    O P E R A N D S  -- END");
        objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

    }


    public void fFetchOperandsforReg(int registerNo) {
        objSimulate.objFP.cControlLogicFill = presentColor;
        objSimulate.objFP.cControlLogicOutline = presentOutlineColor;
        objSimulate.drawControlLogic(objSimulate.getGraphics());

        objSimulate.objFP.setRegisterOutline(registerNo, presentOutlineColor);
        objSimulate.objFP.setRegisterFill(registerNo, presentColor);
        objSimulate.fDrawRegister(registerNo, objSimulate.getGraphics());
        objExecutionSummary.fAddText("\nData from " + registerNo);

        makeDelay(1000);

        objSimulate.objFP.setRegisterFill(registerNo, pastColor);
        objSimulate.objFP.setRegisterOutline(registerNo, pastOutlineColor);
        objSimulate.fDrawRegister(registerNo, objSimulate.getGraphics());
    }


    public void fExecuteforAlu(int desRegValue, int sourceReg1, int sourceReg2) {

        if (sourceReg2 <= 7) {
            this.fHighlightReg(sourceReg1);
            this.fHighlightReg2ALU(false);
            this.fHighlightReg(sourceReg2);
            this.fHighlightReg2ALU(false);
        } else {
            if (sourceReg2 >= 0)
                this.fDrawIR2ALU();

            this.fHighlightReg(sourceReg1);
            this.fHighlightReg2ALU(false);
        }


        this.fDrawALU2reg(desRegValue);

    }


    private void fDrawALU2reg(int reg) {
        try {

            objSimulate.objFP.cALUFill = presentColor;
            objSimulate.objFP.cALUOutline = presentOutlineColor;
            objSimulate.drawALU(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cALU2BusFill = pastColor;
            objSimulate.objFP.cALU2BusOutline = pastOutlineColor;
            objSimulate.ALU2Bus(objSimulate.getGraphics());


            objSimulate.objFP.cALU2BusFill = presentColor;
            objSimulate.objFP.cALU2BusOutline = presentOutlineColor;
            objSimulate.ALU2Bus(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cALU2BusFill = pastColor;
            objSimulate.objFP.cALU2BusOutline = pastOutlineColor;
            objSimulate.ALU2Bus(objSimulate.getGraphics());

            objSimulate.objFP.cBUSFill = presentColor;
            objSimulate.objFP.cBUSOutline = presentOutlineColor;
            objSimulate.drawBUS(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cBUSFill = pastColor;
            objSimulate.objFP.cBUSOutline = pastOutlineColor;
            objSimulate.drawBUS(objSimulate.getGraphics());

            objSimulate.objFP.cBus2RegisterFill = presentColor;
            objSimulate.objFP.cBus2RegisterOutline = presentOutlineColor;
            objSimulate.bus2Register(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cBus2RegisterFill = pastColor;
            objSimulate.objFP.cBus2RegisterOutline = pastOutlineColor;
            objSimulate.bus2Register(objSimulate.getGraphics());

            /* objSimulate.objFP.cRegContainerFill=presentColor;
            objSimulate.objFP.cRegContainerOutline=presentOutlineColor;
            objSimulate.fRe(objSimulate.getGraphics(),presentOutlineColor);
            */

            this.fHighlightReg(reg);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void fDrawIR2ALU() {
        try {
            objSimulate.objFP.cIRFill = presentColor;
            objSimulate.objFP.cIROutline = presentOutlineColor;
            objSimulate.drawInstructionRegister(objSimulate.getGraphics());

            makeDelay(SECOND);
            objSimulate.objFP.cIRFill = pastColor;
            objSimulate.objFP.cIROutline = pastOutlineColor;
            objSimulate.drawInstructionRegister(objSimulate.getGraphics());

            objSimulate.objFP.cIR2SextFill = presentColor;
            objSimulate.objFP.cIR2SextOutline = presentOutlineColor;
            objSimulate.IR2Sext(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cIR2SextFill = pastColor;
            objSimulate.objFP.cIR2SextOutline = pastOutlineColor;
            objSimulate.IR2Sext(objSimulate.getGraphics());

            objSimulate.objFP.cSEXTFill = presentColor;
            objSimulate.objFP.cSEXTOutline = presentOutlineColor;
            objSimulate.drawSEXT(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cSEXTFill = pastColor;
            objSimulate.objFP.cSEXTOutline = pastOutlineColor;
            objSimulate.drawSEXT(objSimulate.getGraphics());


            objSimulate.objFP.cSext2ALUInputFill = presentColor;
            objSimulate.objFP.cSext2ALUInputOutline = presentOutlineColor;
            objSimulate.Sext2ALUInput(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cSext2ALUInputFill = pastColor;
            objSimulate.objFP.cSext2ALUInputOutline = pastOutlineColor;
            objSimulate.Sext2ALUInput(objSimulate.getGraphics());

            objSimulate.objFP.cALUInputFill = presentColor;
            objSimulate.objFP.cALUInputOutline = presentOutlineColor;
            objSimulate.drawALUInput(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cALUInputFill = pastColor;
            objSimulate.objFP.cALUInputOutline = pastOutlineColor;
            objSimulate.drawALUInput(objSimulate.getGraphics());

            objSimulate.objFP.cInputFill = pastColor;
            objSimulate.objFP.cALUInputOutline = pastOutlineColor;
            objSimulate.drawALUInput(objSimulate.getGraphics());

            makeDelay(SECOND);

            //objSimulate.objFP.c


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void fHighlightReg(int reg) {
        objSimulate.objFP.setRegisterFill(reg, presentColor);
        objSimulate.objFP.setRegisterOutline(reg, presentOutlineColor);
        objSimulate.fDrawRegister(reg, objSimulate.getGraphics());

        makeDelay(SECOND);

        objSimulate.objFP.setRegisterFill(reg, pastColor);
        objSimulate.objFP.setRegisterOutline(reg, pastOutlineColor);
        objSimulate.fDrawRegister(reg, objSimulate.getGraphics());
    }


    public void fExecuteforSTI(int regValue) {


    }


    public void fExecuterforSTR(int reg1, int reg2) {
        try {
            fHighlightReg(reg2);

            // fHighlightReg2MAR();
            fHighlightreg2Plus();

            objSimulate.objFP.cIRFill = presentColor;
            objSimulate.objFP.cIROutline = presentOutlineColor;
            objSimulate.drawInstructionRegister(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cIRFill = pastColor;
            objSimulate.objFP.cIROutline = pastOutlineColor;
            objSimulate.drawInstructionRegister(objSimulate.getGraphics());


            objSimulate.objFP.cIR2ZEXT_1BusFill = presentColor;
            objSimulate.objFP.cIR2ZEXT_1BusOutline = presentOutlineColor;
            objSimulate.IR2Zext_1(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cIR2ZEXT_1BusFill = pastColor;
            objSimulate.objFP.cIR2ZEXT_1BusOutline = pastOutlineColor;
            objSimulate.IR2Zext_1(objSimulate.getGraphics());


            objSimulate.objFP.cIR2Zext2Fill = presentColor;
            objSimulate.objFP.cIR2Zext2Outline = presentOutlineColor;
            objSimulate.IR2Zext2(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cIR2Zext2Fill = pastColor;
            objSimulate.objFP.cIR2Zext2Outline = pastOutlineColor;
            objSimulate.IR2Zext2(objSimulate.getGraphics());

            objSimulate.objFP.cZEXT_1Fill = presentColor;
            objSimulate.objFP.cZEXT_1Outline = presentOutlineColor;
            objSimulate.drawZEXT_1(objSimulate.getGraphics());


            makeDelay(SECOND);


            objSimulate.objFP.cZEXT_1Fill = pastColor;
            objSimulate.objFP.cZEXT_1Outline = pastOutlineColor;
            objSimulate.drawZEXT_1(objSimulate.getGraphics());


            objSimulate.objFP.cZext2PlusFill = presentColor;
            objSimulate.objFP.cZext2PlusOutline = presentOutlineColor;
            objSimulate.Zext2Plus(objSimulate.getGraphics());

            makeDelay(SECOND);


            objSimulate.objFP.cZext2PlusFill = pastColor;
            objSimulate.objFP.cZext2PlusOutline = pastOutlineColor;
            objSimulate.Zext2Plus(objSimulate.getGraphics());


            objSimulate.objFP.cPlusFill = presentColor;
            objSimulate.objFP.cPlusOutline = presentOutlineColor;
            objSimulate.drawPLUS(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cPlusFill = pastColor;
            objSimulate.objFP.cPlusOutline = pastOutlineColor;
            objSimulate.drawPLUS(objSimulate.getGraphics());


            fDrawPLus2Mar();

            objSimulate.objFP.cMARFill = presentColor;
            objSimulate.objFP.cMAROutline = presentOutlineColor;
            objSimulate.drawMAR(objSimulate.getGraphics());

            objSimulate.objFP.cMAR2MemoryFill = presentColor;
            objSimulate.objFP.cMAR2MemoryOutline = presentOutlineColor;
            objSimulate.MAR2Memory(objSimulate.getGraphics());

            fHighlightReg(reg1);

            fExecuteRegister2Memory();

            objSimulate.objFP.cMARFill = pastColor;
            objSimulate.objFP.cMAROutline = pastOutlineColor;
            objSimulate.drawMAR(objSimulate.getGraphics());

            objSimulate.objFP.cMAR2MemoryFill = pastColor;
            objSimulate.objFP.cMAR2MemoryOutline = pastOutlineColor;
            objSimulate.MAR2Memory(objSimulate.getGraphics());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void ExecuteforLEA(int reg) {
        try {
            this.fHighlightIR2AT();
            makeDelay(SECOND);

            objSimulate.objFP.cAt2MarMuxFill = presentColor;
            objSimulate.objFP.cAt2MarMuxOutline = presentOutlineColor;
            objSimulate.At2MarMux(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cAt2MarMuxFill = pastColor;
            objSimulate.objFP.cAt2MarMuxOutline = pastOutlineColor;
            objSimulate.At2MarMux(objSimulate.getGraphics());

            objSimulate.objFP.cMARMuxFill = presentColor;
            objSimulate.objFP.cMARMuxOutline = presentOutlineColor;
            objSimulate.drawMAR_MUX(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cMARMuxFill = pastColor;
            objSimulate.objFP.cMARMuxOutline = pastOutlineColor;
            objSimulate.drawMAR_MUX(objSimulate.getGraphics());

            objSimulate.objFP.cMarMux2BusFill = presentColor;
            objSimulate.objFP.cMarMux2BusOutline = presentOutlineColor;
            objSimulate.MAR_MUX2Bus(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cMarMux2BusFill = pastColor;
            objSimulate.objFP.cMarMux2BusOutline = pastOutlineColor;
            objSimulate.MAR_MUX2Bus(objSimulate.getGraphics());


            objSimulate.objFP.cBUSFill = presentColor;
            objSimulate.objFP.cBUSOutline = presentOutlineColor;
            objSimulate.drawBUS(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cBUSFill = pastColor;
            objSimulate.objFP.cBUSOutline = pastOutlineColor;
            objSimulate.drawBUS(objSimulate.getGraphics());

            objSimulate.objFP.cBus2RegisterFill = presentColor;
            objSimulate.objFP.cBus2RegisterOutline = presentOutlineColor;
            objSimulate.bus2Register(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cBus2RegisterFill = pastColor;
            objSimulate.objFP.cBus2RegisterOutline = pastOutlineColor;
            objSimulate.bus2Register(objSimulate.getGraphics());

            this.fHighlightReg(reg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void fExecuteforLDR(int reg1, int reg2) {

        try {

            fHighlightReg(reg2);

            fHighlightReg2MAR();


            objSimulate.objFP.cMARFill = presentColor;
            objSimulate.objFP.cMAROutline = presentColor;
            objSimulate.drawMAR(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cMARFill = pastColor;
            objSimulate.objFP.cMAROutline = pastColor;
            objSimulate.drawMAR(objSimulate.getGraphics());

            objSimulate.objFP.cMAR2MemoryFill = presentColor;
            objSimulate.objFP.cMAR2MemoryOutline = presentColor;
            objSimulate.MAR2Memory(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cMAR2MemoryFill = pastColor;
            objSimulate.objFP.cMAR2MemoryOutline = pastColor;
            objSimulate.MAR2Memory(objSimulate.getGraphics());

            objSimulate.objFP.cMemoryFill = presentColor;
            objSimulate.objFP.cMemoryOutline = presentColor;
            objSimulate.drawMemory(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cMemoryFill = pastColor;
            objSimulate.objFP.cMemoryOutline = pastColor;
            objSimulate.drawMemory(objSimulate.getGraphics());


            objSimulate.objFP.cMemory2MDRFill = presentColor;
            objSimulate.objFP.cMemory2MDROutline = presentColor;
            objSimulate.Memory2MDR(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cMemory2MDRFill = pastColor;
            objSimulate.objFP.cMemory2MDROutline = pastColor;
            objSimulate.Memory2MDR(objSimulate.getGraphics());

            objSimulate.objFP.cMDRFill = presentColor;
            objSimulate.objFP.cMDROutline = presentColor;
            objSimulate.drawMDR(objSimulate.getGraphics());


            makeDelay(SECOND);

            objSimulate.objFP.cMDRFill = pastColor;
            objSimulate.objFP.cMDROutline = pastColor;
            objSimulate.drawMDR(objSimulate.getGraphics());

            objSimulate.objFP.cMDR2BusFill = presentColor;
            objSimulate.objFP.cMDR2BusOutline = presentColor;
            objSimulate.MDR2Bus(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cMDR2BusFill = pastColor;
            objSimulate.objFP.cMDR2BusOutline = pastColor;
            objSimulate.MDR2Bus(objSimulate.getGraphics());


            this.fDrawMDR2REG(reg1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fHighlightReg2MAR() throws InterruptedException {
        fHighlightreg2Plus();

        fDrawPLus2Mar();


    }

    private void fDrawPLus2Mar() throws InterruptedException {
        objSimulate.objFP.cPlus2MarMuxFill = presentColor;
        objSimulate.objFP.cPlus2MarMuxOutline = presentOutlineColor;
        objSimulate.plus2MarMux(objSimulate.getGraphics());

        makeDelay(SECOND);

        objSimulate.objFP.cPlus2MarMuxFill = pastColor;
        objSimulate.objFP.cPlus2MarMuxOutline = pastOutlineColor;
        objSimulate.plus2MarMux(objSimulate.getGraphics());

        objSimulate.objFP.cMARMuxFill = presentColor;
        objSimulate.objFP.cMARMuxOutline = presentOutlineColor;
        objSimulate.drawMAR_MUX(objSimulate.getGraphics());

        makeDelay(SECOND);


        objSimulate.objFP.cMARMuxFill = pastColor;
        objSimulate.objFP.cMARMuxOutline = pastOutlineColor;
        objSimulate.drawMAR_MUX(objSimulate.getGraphics());


        objSimulate.objFP.cMarMux2BusFill = presentColor;
        objSimulate.objFP.cMarMux2BusOutline = presentOutlineColor;
        objSimulate.MAR_MUX2Bus(objSimulate.getGraphics());


        makeDelay(SECOND);

        objSimulate.objFP.cMarMux2BusFill = pastColor;
        objSimulate.objFP.cMarMux2BusOutline = pastOutlineColor;
        objSimulate.MAR_MUX2Bus(objSimulate.getGraphics());


        objSimulate.objFP.cBUSFill = presentColor;
        objSimulate.objFP.cBUSOutline = presentOutlineColor;
        objSimulate.drawBUS(objSimulate.getGraphics());

        makeDelay(SECOND);

        objSimulate.objFP.cBUSFill = pastColor;
        objSimulate.objFP.cBUSOutline = pastOutlineColor;
        objSimulate.drawBUS(objSimulate.getGraphics());


        this.fDrawBus2Mar();
    }

    private void fHighlightreg2Plus() throws InterruptedException {
        objSimulate.objFP.cReg2PCMuxFill = presentColor;
        objSimulate.objFP.cReg2PCMuxOutline = presentOutlineColor;
        objSimulate.reg2PCMux(objSimulate.getGraphics());

        makeDelay(SECOND);

        objSimulate.objFP.cReg2PCMuxFill = pastColor;
        objSimulate.objFP.cReg2PCMuxOutline = pastOutlineColor;
        objSimulate.reg2PCMux(objSimulate.getGraphics());

        objSimulate.objFP.cReg2PlusFill = presentColor;
        objSimulate.objFP.cReg2PlusOutline = presentOutlineColor;
        objSimulate.reg2Plus(objSimulate.getGraphics());

        makeDelay(SECOND);

        objSimulate.objFP.cReg2PlusFill = pastColor;
        objSimulate.objFP.cReg2PlusOutline = pastOutlineColor;
        objSimulate.reg2Plus(objSimulate.getGraphics());


        objSimulate.objFP.cPlusFill = presentColor;
        objSimulate.objFP.cPlusOutline = presentOutlineColor;
        objSimulate.drawPLUS(objSimulate.getGraphics());

        makeDelay(SECOND);

        objSimulate.objFP.cPlusFill = pastColor;
        objSimulate.objFP.cPlusOutline = pastOutlineColor;
        objSimulate.drawPLUS(objSimulate.getGraphics());


    }


    public void fExecuteforLD(int regValue) {
        try {

            objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
            objExecutionSummary.fAddHeadingText("\nE X E C U T E  O P E R A T I O N -- BEGIN");


            fExecuteforMemory();
            objSimulate.objFP.cMARFill = presentColor;
            objSimulate.objFP.cMAROutline = presentOutlineColor;
            objSimulate.drawMAR(objSimulate.getGraphics());

            objSimulate.objFP.cMAR2MemoryFill = presentColor;
            objSimulate.objFP.cMAR2MemoryOutline = presentOutlineColor;
            objSimulate.MAR2Memory(objSimulate.getGraphics());


            makeDelay(SECOND);
            //fHighlightReg(regValue);

            //fExecuteRegister2Memory();

            objSimulate.objFP.cMARFill = pastColor;
            objSimulate.objFP.cMAROutline = pastOutlineColor;
            objSimulate.drawMAR(objSimulate.getGraphics());

            objSimulate.objFP.cMAR2MemoryFill = pastColor;
            objSimulate.objFP.cMAR2MemoryOutline = pastOutlineColor;
            objSimulate.MAR2Memory(objSimulate.getGraphics());


            objSimulate.objFP.cMemory2MDRFill = presentColor;
            objSimulate.objFP.cMemory2MDROutline = presentOutlineColor;
            objSimulate.Memory2MDR(objSimulate.getGraphics());


            makeDelay(SECOND);

            objSimulate.objFP.cMemory2MDRFill = pastColor;
            objSimulate.objFP.cMemory2MDROutline = pastOutlineColor;
            objSimulate.Memory2MDR(objSimulate.getGraphics());

            objSimulate.objFP.cMDRFill = presentColor;
            objSimulate.objFP.cMDROutline = presentOutlineColor;
            objSimulate.drawMDR(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cMDRFill = pastColor;
            objSimulate.objFP.cMDROutline = pastOutlineColor;
            objSimulate.drawMDR(objSimulate.getGraphics());

            this.fDrawMDR2REG(regValue);


            objExecutionSummary.fAddHeadingText("\nE X E C U T E  O P E R A T I O N -- END");
            objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void fDrawMDR2REG(int reg) {
        try {
            objSimulate.objFP.cBUSFill = presentColor;
            objSimulate.objFP.cBUSOutline = presentOutlineColor;
            objSimulate.drawBUS(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cBUSFill = pastColor;
            objSimulate.objFP.cBUSOutline = pastOutlineColor;
            objSimulate.drawBUS(objSimulate.getGraphics());

            objSimulate.objFP.cBus2RegisterFill = presentColor;
            objSimulate.objFP.cBus2RegisterOutline = presentOutlineColor;
            objSimulate.bus2Register(objSimulate.getGraphics());


            makeDelay(SECOND);

            objSimulate.objFP.cBus2RegisterFill = pastColor;
            objSimulate.objFP.cBus2RegisterOutline = pastOutlineColor;
            objSimulate.bus2Register(objSimulate.getGraphics());

            this.fHighlightReg(reg);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void fExecuteforST(int regValue) {

        try {

            objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
            objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- BEGIN");


            fExecuteforMemory();

            objExecutionSummary.fAddText("Stores contents of R" + regValue + "in the Memory");
            fDrawBus2Mar();
            makeDelay(SECOND);
            //fDrawMar2memory();
            objSimulate.objFP.cMARFill = presentColor;
            objSimulate.objFP.cMAROutline = presentOutlineColor;
            objSimulate.drawMAR(objSimulate.getGraphics());

            objSimulate.objFP.cMAR2MemoryFill = presentColor;
            objSimulate.objFP.cMAR2MemoryOutline = presentOutlineColor;
            objSimulate.MAR2Memory(objSimulate.getGraphics());


            fHighlightReg(regValue);

            fExecuteRegister2Memory();

            objSimulate.objFP.cMARFill = pastColor;
            objSimulate.objFP.cMAROutline = pastOutlineColor;
            objSimulate.drawMAR(objSimulate.getGraphics());

            objSimulate.objFP.cMAR2MemoryFill = pastColor;
            objSimulate.objFP.cMAR2MemoryOutline = pastOutlineColor;
            objSimulate.MAR2Memory(objSimulate.getGraphics());

            objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N-- END");
            objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fDrawMar2memory() throws InterruptedException {
        objSimulate.objFP.cMARFill = presentColor;
        objSimulate.objFP.cMAROutline = presentOutlineColor;
        objSimulate.drawMAR(objSimulate.getGraphics());
        makeDelay(SECOND);


        objSimulate.objFP.cMARFill = pastColor;
        objSimulate.objFP.cMAROutline = pastOutlineColor;
        objSimulate.drawMAR(objSimulate.getGraphics());


        objSimulate.objFP.cMAR2MemoryFill = presentColor;
        objSimulate.objFP.cMAR2MemoryOutline = presentOutlineColor;

        objSimulate.MAR2Memory(objSimulate.getGraphics());


        makeDelay(SECOND);


        objSimulate.objFP.cMAR2MemoryFill = pastColor;
        objSimulate.objFP.cMAR2MemoryOutline = pastOutlineColor;

        objSimulate.MAR2Memory(objSimulate.getGraphics());


        objSimulate.objFP.cMemoryFill = presentColor;
        objSimulate.objFP.cMemoryOutline = presentOutlineColor;
        objSimulate.drawMemory(objSimulate.getGraphics());

        makeDelay(SECOND);


        objSimulate.objFP.cMemoryFill = pastColor;
        objSimulate.objFP.cMemoryOutline = pastOutlineColor;
        objSimulate.drawMemory(objSimulate.getGraphics());
    }

    private void fDrawBus2Mar() throws InterruptedException {
        objSimulate.objFP.cBus2MARFill = presentColor;
        objSimulate.objFP.cBus2MAROutline = presentOutlineColor;
        objSimulate.bus2MAR(objSimulate.getGraphics());


        makeDelay(SECOND);

        objSimulate.objFP.cBus2MARFill = pastColor;
        objSimulate.objFP.cBus2MAROutline = pastOutlineColor;
        objSimulate.bus2MAR(objSimulate.getGraphics());


    }

    private void fExecuteRegister2Memory() {
        try {
            objSimulate.objFP.cReg2ALU_2_Fill = presentColor;
            objSimulate.objFP.cReg2ALU_2_Outline = presentOutlineColor;
            objSimulate.reg2ALU_2(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cReg2ALU_2_Fill = pastColor;
            objSimulate.objFP.cReg2ALU_2_Outline = pastOutlineColor;
            objSimulate.reg2ALU_2(objSimulate.getGraphics());

            objSimulate.objFP.cALUFill = presentColor;
            objSimulate.objFP.cALUOutline = presentOutlineColor;
            objSimulate.drawALU(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cALUFill = pastColor;
            objSimulate.objFP.cALUOutline = pastOutlineColor;
            objSimulate.drawALU(objSimulate.getGraphics());

            objSimulate.objFP.cALU2BusFill = presentColor;
            objSimulate.objFP.cALU2BusOutline = presentOutlineColor;
            objSimulate.ALU2Bus(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cALU2BusFill = pastColor;
            objSimulate.objFP.cALU2BusOutline = pastOutlineColor;
            objSimulate.ALU2Bus(objSimulate.getGraphics());

            objSimulate.objFP.cBUSFill = presentColor;
            objSimulate.objFP.cBUSOutline = presentOutlineColor;
            objSimulate.drawBUS(objSimulate.getGraphics());

            makeDelay(SECOND);
            objSimulate.objFP.cBUSFill = pastColor;
            objSimulate.objFP.cBUSOutline = pastOutlineColor;
            objSimulate.drawBUS(objSimulate.getGraphics());

            objSimulate.objFP.cBus2MDRFill = presentColor;
            objSimulate.objFP.cBus2MDROutline = presentOutlineColor;
            objSimulate.bus2MDR(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cBus2MDRFill = pastColor;
            objSimulate.objFP.cBus2MDROutline = pastOutlineColor;
            objSimulate.bus2MDR(objSimulate.getGraphics());


            fDrawMDR2Memory();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fDrawMDR2Memory() throws InterruptedException {

        objSimulate.objFP.cMDRFill = presentColor;
        objSimulate.objFP.cMDROutline = presentOutlineColor;
        objSimulate.drawMDR(objSimulate.getGraphics());

        makeDelay(SECOND);

        objSimulate.objFP.cMDRFill = pastColor;
        objSimulate.objFP.cMDROutline = pastOutlineColor;
        objSimulate.drawMDR(objSimulate.getGraphics());


        objSimulate.objFP.cMDR2MemoryFill = presentColor;
        objSimulate.objFP.cMDR2MemoryOutline = presentOutlineColor;
        objSimulate.MDR2Memory(objSimulate.getGraphics());

        makeDelay(SECOND);

        objSimulate.objFP.cMDR2MemoryFill = pastColor;
        objSimulate.objFP.cMDR2MemoryOutline = pastOutlineColor;
        objSimulate.MDR2Memory(objSimulate.getGraphics());

        objSimulate.objFP.cMemoryFill = presentColor;
        objSimulate.objFP.cMemoryOutline = presentOutlineColor;
        objSimulate.drawMemory(objSimulate.getGraphics());

        makeDelay(SECOND);

        objSimulate.objFP.cMemoryFill = pastColor;
        objSimulate.objFP.cMemoryOutline = pastOutlineColor;
        objSimulate.drawMemory(objSimulate.getGraphics());

    }


    public void fExecuteforMemory() {
        try {


            //objExecutionSummary.fAddText("\nExecute for memory");

            fHighlightIR2AT();


            objSimulate.objFP.cAt2MarMuxFill = presentColor;
            objSimulate.objFP.cAt2MarMuxOutline = presentOutlineColor;
            objSimulate.At2MarMux(objSimulate.getGraphics());


            makeDelay(SECOND);


            objSimulate.objFP.cAt2MarMuxFill = pastColor;
            objSimulate.objFP.cAt2MarMuxOutline = pastOutlineColor;
            objSimulate.At2MarMux(objSimulate.getGraphics());

            objSimulate.objFP.cMARMuxFill = presentColor;
            objSimulate.objFP.cMARMuxOutline = presentOutlineColor;
            objSimulate.drawMAR_MUX(objSimulate.getGraphics());

            makeDelay(SECOND);


            objSimulate.objFP.cMARMuxFill = pastColor;
            objSimulate.objFP.cMARMuxOutline = pastOutlineColor;
            objSimulate.drawMAR_MUX(objSimulate.getGraphics());

            objSimulate.objFP.cMarMux2BusFill = presentColor;
            objSimulate.objFP.cMarMux2BusOutline = presentOutlineColor;
            objSimulate.MAR_MUX2Bus(objSimulate.getGraphics());


            makeDelay(SECOND);

            objSimulate.objFP.cMarMux2BusFill = pastColor;
            objSimulate.objFP.cMarMux2BusOutline = pastOutlineColor;
            objSimulate.MAR_MUX2Bus(objSimulate.getGraphics());


            objSimulate.objFP.cBUSFill = presentColor;
            objSimulate.objFP.cBUSOutline = presentOutlineColor;
            objSimulate.drawBUS(objSimulate.getGraphics());

            makeDelay(SECOND);


            objSimulate.objFP.cBUSFill = pastColor;
            objSimulate.objFP.cBUSOutline = pastOutlineColor;
            objSimulate.drawBUS(objSimulate.getGraphics());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fHighlightIR2AT() throws InterruptedException {
        objSimulate.objFP.cIRFill = presentColor;
        objSimulate.objFP.cIROutline = presentOutlineColor;
        objSimulate.drawInstructionRegister(objSimulate.getGraphics());


        objSimulate.objFP.cIR2AtFill = presentColor;
        objSimulate.objFP.cIR2AtOutline = presentOutlineColor;
        objSimulate.IR2At(objSimulate.getGraphics());


        objSimulate.objFP.cIR2ZEXT_1BusFill = presentColor;
        objSimulate.objFP.cIR2ZEXT_1BusOutline = presentOutlineColor;
        objSimulate.IR2Zext_1(objSimulate.getGraphics());


        objSimulate.objFP.cPCFill = presentColor;
        objSimulate.objFP.cPCOutline = presentOutlineColor;
        objSimulate.drawProgramCounter(objSimulate.getGraphics());

        objSimulate.objFP.cPC2AtFill = presentColor;
        objSimulate.objFP.cPC2AtOutline = presentOutlineColor;
        objSimulate.PC2At(objSimulate.getGraphics());


        Thread.sleep(2000);


        objSimulate.objFP.cIRFill = pastColor;
        objSimulate.objFP.cIROutline = pastOutlineColor;
        objSimulate.drawInstructionRegister(objSimulate.getGraphics());

        objSimulate.objFP.cIR2ZEXT_1BusFill = pastColor;
        objSimulate.objFP.cIR2ZEXT_1BusOutline = pastOutlineColor;
        objSimulate.IR2Zext_1(objSimulate.getGraphics());


        objSimulate.objFP.cIR2AtFill = pastColor;
        objSimulate.objFP.cIR2AtOutline = pastOutlineColor;
        objSimulate.IR2At(objSimulate.getGraphics());

        objSimulate.objFP.cPCFill = pastColor;
        objSimulate.objFP.cPCOutline = pastOutlineColor;
        objSimulate.drawProgramCounter(objSimulate.getGraphics());

        objSimulate.objFP.cPC2AtFill = pastColor;
        objSimulate.objFP.cPC2AtOutline = pastOutlineColor;
        objSimulate.PC2At(objSimulate.getGraphics());


        objSimulate.objFP.cATFill = presentColor;
        objSimulate.objFP.cATOutline = presentOutlineColor;
        objSimulate.drawAT(objSimulate.getGraphics());


        makeDelay(SECOND);


        objSimulate.objFP.cATFill = pastColor;
        objSimulate.objFP.cATOutline = pastOutlineColor;
        objSimulate.drawAT(objSimulate.getGraphics());
    }


    public void fExecuteforRet() {
        try {
            this.fHighlightReg(7);
            objSimulate.objFP.cReg2PCMuxFill = presentColor;
            objSimulate.objFP.cReg2PCMuxOutline = presentOutlineColor;
            objSimulate.reg2PCMux(objSimulate.getGraphics());


            makeDelay(SECOND);


            objSimulate.objFP.cReg2PCMuxFill = pastColor;
            objSimulate.objFP.cReg2PCMuxOutline = pastOutlineColor;
            objSimulate.reg2PCMux(objSimulate.getGraphics());

            objSimulate.objFP.cPCMuxFill = presentColor;
            objSimulate.objFP.cPCMuxOutline = presentOutlineColor;
            objSimulate.drawPC_MUX(objSimulate.getGraphics());

            Thread.sleep(SECOND);

            objSimulate.objFP.cPCMuxFill = pastColor;
            objSimulate.objFP.cPCMuxOutline = pastOutlineColor;
            objSimulate.drawPC_MUX(objSimulate.getGraphics());

            objSimulate.objFP.cPCMux2PCFill = presentColor;
            objSimulate.objFP.cPCMux2PCOutline = presentOutlineColor;
            objSimulate.PCMux2PC(objSimulate.getGraphics());

            Thread.sleep(SECOND);

            objSimulate.objFP.cPCMux2PCFill = pastColor;
            objSimulate.objFP.cPCMux2PCOutline = pastOutlineColor;
            objSimulate.PCMux2PC(objSimulate.getGraphics());

            objSimulate.objFP.cPCFill = presentColor;
            objSimulate.objFP.cPCOutline = presentOutlineColor;
            objSimulate.drawProgramCounter(objSimulate.getGraphics());

            Thread.sleep(SECOND);

            objSimulate.objFP.cPCFill = presentColor;
            objSimulate.objFP.cPCOutline = presentOutlineColor;
            objSimulate.drawProgramCounter(objSimulate.getGraphics());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fExecuteforPC(boolean storePC) {
        try {
            if (storePC == true) {
                objSimulate.objFP.cPCFill = presentColor;
                objSimulate.objFP.cPCOutline = presentOutlineColor;
                objSimulate.drawProgramCounter(objSimulate.getGraphics());

                makeDelay(SECOND);
                objSimulate.objFP.cPCFill = pastColor;
                objSimulate.objFP.cPCOutline = pastOutlineColor;
                objSimulate.drawProgramCounter(objSimulate.getGraphics());

                objSimulate.objFP.cPC2BusFill = presentColor;
                objSimulate.objFP.cPC2BusOutline = presentOutlineColor;
                objSimulate.PC2Bus(objSimulate.getGraphics());

                makeDelay(SECOND);

                objSimulate.objFP.cPC2BusFill = pastColor;
                objSimulate.objFP.cPC2BusOutline = pastOutlineColor;
                objSimulate.PC2Bus(objSimulate.getGraphics());

                objSimulate.objFP.cBUSFill = presentColor;
                objSimulate.objFP.cBUSOutline = presentOutlineColor;
                objSimulate.drawBUS(objSimulate.getGraphics());

                makeDelay(SECOND);


                objSimulate.objFP.cBUSFill = pastColor;
                objSimulate.objFP.cBUSOutline = pastOutlineColor;
                objSimulate.drawBUS(objSimulate.getGraphics());

                objSimulate.objFP.cBus2RegisterFill = presentColor;
                objSimulate.objFP.cBus2RegisterOutline = presentOutlineColor;
                objSimulate.bus2Register(objSimulate.getGraphics());

                makeDelay(SECOND);


                objSimulate.objFP.cBus2RegisterFill = pastColor;
                objSimulate.objFP.cBus2RegisterOutline = pastOutlineColor;
                objSimulate.bus2Register(objSimulate.getGraphics());

                this.fHighlightReg(7);
            }

            this.fHighlightIR2AT();

            objSimulate.objFP.cAt2PCMuxFill = presentColor;
            objSimulate.objFP.cAt2PCMuxOutline = presentOutlineColor;
            objSimulate.At2PCMux(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cAt2PCMuxFill = pastColor;
            objSimulate.objFP.cAt2PCMuxOutline = pastOutlineColor;
            objSimulate.At2PCMux(objSimulate.getGraphics());

            objSimulate.objFP.cPCMuxFill = presentColor;
            objSimulate.objFP.cPCMuxOutline = presentOutlineColor;
            objSimulate.drawPC_MUX(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cPCMuxFill = pastColor;
            objSimulate.objFP.cPCMuxOutline = pastOutlineColor;
            objSimulate.drawPC_MUX(objSimulate.getGraphics());

            objSimulate.objFP.cPCMux2PCFill = presentColor;
            objSimulate.objFP.cPCMux2PCOutline = presentOutlineColor;
            objSimulate.PCMux2PC(objSimulate.getGraphics());

            makeDelay(SECOND);

            objSimulate.objFP.cPCMux2PCFill = pastColor;
            objSimulate.objFP.cPCMux2PCOutline = pastOutlineColor;
            objSimulate.PCMux2PC(objSimulate.getGraphics());

            objSimulate.objFP.cPCFill = presentColor;
            objSimulate.objFP.cPCOutline = presentOutlineColor;

            objSimulate.drawProgramCounter(objSimulate.getGraphics());


            makeDelay(SECOND);


            objSimulate.objFP.cPCFill = pastColor;
            objSimulate.objFP.cPCOutline = pastOutlineColor;

            objSimulate.drawProgramCounter(objSimulate.getGraphics());

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        drawPC();
        showPC();

        */
    }


    public void fSetPC() {
        objSimulate.objFP.cPCFill = presentColor;
        objSimulate.objFP.cPCOutline = presentOutlineColor;

        try {

            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        objSimulate.objFP.cPCFill = pastColor;
        objSimulate.objFP.cPCOutline = pastOutlineColor;

    }

    public void fSetFlags(boolean N, boolean Z, boolean P) {
        if (N == true) {
            objSimulate.objFP.cNegativeFill = presentColor;
            objSimulate.objFP.cNegativeOutline = presentOutlineColor;
            makeDelay(1000);

            objSimulate.objFP.cNegativeFill = pastColor;
            objSimulate.objFP.cNegativeOutline = pastOutlineColor;
        }

        if (Z == true) {
            objSimulate.objFP.cNegativeFill = presentColor;
            objSimulate.objFP.cNegativeOutline = presentOutlineColor;

            makeDelay(1000);

            objSimulate.objFP.cNegativeFill = pastColor;
            objSimulate.objFP.cNegativeOutline = pastOutlineColor;
        }

        if (P == true) {
            objSimulate.objFP.cNegativeFill = presentColor;
            objSimulate.objFP.cNegativeOutline = presentOutlineColor;
            makeDelay(1000);

            objSimulate.objFP.cNegativeFill = pastColor;
            objSimulate.objFP.cNegativeOutline = pastOutlineColor;
        }


    }

    private boolean fHighlightReg2ALU(boolean firstpath) {
        try {
            if (firstpath == false) {
                objSimulate.objFP.cReg2ALU_1_Fill = presentColor;
                objSimulate.objFP.cReg2ALU_1_Outline = presentOutlineColor;
                objSimulate.reg2ALU_1(objSimulate.getGraphics());

                makeDelay(SECOND);

                objSimulate.objFP.cReg2ALU_1_Fill = pastColor;
                objSimulate.objFP.cReg2ALU_1_Outline = pastOutlineColor;
                objSimulate.reg2ALU_1(objSimulate.getGraphics());

                firstpath = true;
            } else {
                objSimulate.objFP.cReg2ALUInputFill = presentColor;
                objSimulate.objFP.cReg2ALUInputOutline = presentOutlineColor;
                objSimulate.register2ALUInput(objSimulate.getGraphics());

                makeDelay(SECOND);

                objSimulate.objFP.cReg2ALUInputFill = pastColor;
                objSimulate.objFP.cReg2ALUInputOutline = pastOutlineColor;
                objSimulate.register2ALUInput(objSimulate.getGraphics());

                objSimulate.objFP.cALUInputFill = presentColor;
                objSimulate.objFP.cALUInputOutline = presentOutlineColor;
                objSimulate.drawALUInput(objSimulate.getGraphics());

                makeDelay(SECOND);

                objSimulate.objFP.cALUInputFill = pastColor;
                objSimulate.objFP.cALUInputOutline = pastOutlineColor;
                objSimulate.drawALUInput(objSimulate.getGraphics());


                objSimulate.objFP.cReg2ALU_2_Fill = presentColor;
                objSimulate.objFP.cReg2ALU_2_Outline = presentOutlineColor;
                objSimulate.reg2ALU_2(objSimulate.getGraphics());

                makeDelay(SECOND);

                objSimulate.objFP.cReg2ALU_2_Fill = pastColor;
                objSimulate.objFP.cReg2ALU_2_Outline = pastOutlineColor;
                objSimulate.reg2ALU_2(objSimulate.getGraphics());

                objSimulate.objFP.cALUFill = presentColor;
                objSimulate.objFP.cALUOutline = presentOutlineColor;
                objSimulate.drawALU(objSimulate.getGraphics());

                makeDelay(SECOND);

                objSimulate.objFP.cALUFill = pastColor;
                objSimulate.objFP.cALUOutline = pastOutlineColor;
                objSimulate.drawALU(objSimulate.getGraphics());

            }


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            return firstpath;
        }
    }

    public Processor getObjProcessor_Dataflow() {
        return objProcessor_Dataflow;
    }

    public void setObjProcessor_Dataflow(Processor objProcessor_Dataflow) {
        this.objProcessor_Dataflow = objProcessor_Dataflow;
    }
}