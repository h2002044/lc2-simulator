package com.github.h2002044.lc2;

import java.awt.*;

public class FlowProperties
{
    public void setRegisterOutline(int registerNo, Color outlineColor)
    {
        switch(registerNo)
        {
            case 0:
                cRegister00Outline = outlineColor;
                break;
            case 1:
                cRegister01Outline = outlineColor;
                break;
            case 2:
                cRegister02Outline = outlineColor;
                break;
            case 3:
                cRegister03Outline = outlineColor;
                break;
            case 4:
                cRegister04Outline = outlineColor;
                break;
            case 5:
                cRegister05Outline = outlineColor;
                break;
            case 6:
                cRegister06Outline = outlineColor;
                break;
            case 7:
                cRegister07Outline = outlineColor;
                break;
        }

    }

    public void setRegisterFill(int registerNo, Color fillColor)
    {
        switch(registerNo)
        {
            case 0:
                cRegister00Fill = fillColor;
                break;
            case 1:
                cRegister01Fill = fillColor;
                break;
            case 2:
                cRegister02Fill = fillColor;
                break;
            case 3:
                cRegister03Fill = fillColor;
                break;
            case 4:
                cRegister04Fill = fillColor;
                break;
            case 5:
                cRegister05Fill = fillColor;
                break;
            case 6:
                cRegister06Fill = fillColor;
                break;
            case 7:
                cRegister07Fill = fillColor;
                break;
        }

    }

    Color cRegister00Outline = Color.black;
    Color cRegister00Fill = Color.white;

    Color cRegister01Outline = Color.black;
    Color cRegister01Fill = Color.white;

    Color cRegister02Outline = Color.black;
    Color cRegister02Fill = Color.white;

    Color cRegister03Outline = Color.black;
    Color cRegister03Fill = Color.white;

    Color cRegister04Outline = Color.black;
    Color cRegister04Fill = Color.white;

    Color cRegister05Outline = Color.black;
    Color cRegister05Fill = Color.white;

    Color cRegister06Outline = Color.black;
    Color cRegister06Fill = Color.white;

    Color cRegister07Outline = Color.black;
    Color cRegister07Fill = Color.white;

    Color cRegContainerOutline = Color.black;
    Color cRegContainerFill = Color.white;

    Color cMemoryOutline = Color.black;
    Color cMemoryFill = Color.white;

    Color cMAROutline = Color.black;
    Color cMARFill = Color.white;

    Color cMDROutline = Color.black;
    Color cMDRFill = Color.white;

    Color cInputOutline = Color.black;
    Color cInputFill = Color.white;

    Color cKBSROutline = Color.black;
    Color cKBSRFill = Color.white;

    Color cCRTDROutline = Color.black;
    Color cCRTDRFill = Color.white;


    Color cControlLogicOutline = Color.black;
    Color cControlLogicFill = Color.white;


    Color cIROutline = Color.black;
    Color cIRFill = Color.white;


    Color cNegativeOutline = Color.black;
    Color cNegativeFill = Color.white;

    Color cZeroOutline = Color.black;
    Color cZeroFill = Color.white;

    Color cPositiveOutline = Color.black;
    Color cPositiveFill = Color.white;

    Color cALUOutline = Color.black;
    Color cALUFill = Color.white;

    Color cMARMuxOutline = Color.black;
    Color cMARMuxFill = Color.white;

    Color cSEXTOutline = Color.black;
    Color cSEXTFill = Color.white;

    Color cZEXT_1Outline = Color.black;
    Color cZEXT_1Fill = Color.white;

    Color cZEXT_2Outline = Color.black;
    Color cZEXT_2Fill = Color.white;

    Color cPlusOneOutline = Color.black;
    Color cPlusOneFill = Color.white;

    Color cPlusOutline = Color.black;
    Color cPlusFill = Color.white;

    Color cPCOutline = Color.black;
    Color cPCFill = Color.white;

    Color cATOutline = Color.black;
    Color cATFill = Color.white;

    Color cLogicOutline = Color.black;
    Color cLogicFill = Color.white;

    Color cBUSOutline = Color.white;
    Color cBUSFill = Color.black;

    Color cReg2ALU_1_Outline = Color.white;
    Color cReg2ALU_1_Fill = Color.black;


    Color cReg2ALU_2_Outline = Color.white;
    Color cReg2ALU_2_Fill = Color.black;

    Color cReg2ALUInputOutline = Color.white;
    Color cReg2ALUInputFill = Color.black;

    Color cALU2BusOutline = Color.white;
    Color cALU2BusFill = Color.black;

    Color cBus2RegisterOutline = Color.white;
    Color cBus2RegisterFill = Color.black;

    Color cBus2CRTDROutline = Color.white;
    Color cBus2CRTDRFill = Color.black;

    Color cBus2CRTSROutline = Color.white;
    Color cBus2CRTSRFill = Color.black;

    Color cCRTSR2BusOutline = Color.white;
    Color cCRTSR2BusFill = Color.black;

    Color cKBSR2BusOutline = Color.white;
    Color cKBSR2BusFill = Color.black;


    Color cBus2KBSROutline = Color.white;
    Color cBus2KBSRFill = Color.black;

    Color cBus2MAROutline = Color.white;
    Color cBus2MARFill = Color.black;

    Color cMDR2BusOutline = Color.white;
    Color cMDR2BusFill = Color.black;


    Color cBus2MDROutline = Color.white;
    Color cBus2MDRFill = Color.black;

    Color cBus2IROutline = Color.white;
    Color cBus2IRFill = Color.black;

    Color cIR2ControlLogicOutline = Color.white;
    Color cIR2ControlLogicFill = Color.black;

    Color cNegative2ControlLogicOutline = Color.white;
    Color cNegative2ControlLogicFill = Color.black;

    Color cZero2ControlLogicOutline = Color.white;
    Color cZeroControlLogicFill = Color.black;

    Color cPositive2ControlLogicOutline = Color.white;
    Color cPositive2ControlLogicFill = Color.black;

    Color cMarMux2BusOutline = Color.white;
    Color cMarMux2BusFill = Color.black;

    Color cPC2BusOutline = Color.white;
    Color cPC2BusFill = Color.black;

    Color cIR2ZEXT_1BusOutline = Color.white;
    Color cIR2ZEXT_1BusFill = Color.black;

    Color cIR2SextOutline = Color.white;
    Color cIR2SextFill = Color.black;

    Color cZext2PlusOutline = Color.white;
    Color cZext2PlusFill = Color.black;

    Color cZext2MarMuxOutline = Color.white;
    Color cZext2MarMuxFill = Color.black;

    Color cPlus2MarMuxOutline = Color.white;
    Color cPlus2MarMuxFill = Color.black;

    Color cReg2PCMuxOutline = Color.white;
    Color cReg2PCMuxFill = Color.black;

    Color cReg2PlusOutline = Color.white;
    Color cReg2PlusFill = Color.black;

    Color cIR2Zext2Outline = Color.white;
    Color cIR2Zext2Fill = Color.black;

    Color cPC2Plus_1_Outline = Color.white;
    Color cPC2Plus_1_Fill = Color.black;

    Color cSext2ALUInputOutline = Color.white;
    Color cSext2ALUInputFill = Color.black;

    Color cAt2MarMuxOutline = Color.white;
    Color cAt2MarMuxFill = Color.black;

    Color cPC2AtOutline = Color.white;
    Color cPC2AtFill = Color.black;

    Color cIR2AtOutline = Color.white;
    Color cIR2AtFill = Color.black;

    Color cMAR2MemoryOutline = Color.white;
    Color cMAR2MemoryFill = Color.black;

    Color cMAR2InputOutline = Color.white;
    Color cMAR2InputFill = Color.black;

    Color cMAR2OutputOutline = Color.white;
    Color cMAR2OutputFill = Color.black;

    Color cMDR2MemoryOutline = Color.white;
    Color cMDR2MemoryFill = Color.black;

    Color cMemory2MDROutline = Color.white;
    Color cMemory2MDRFill = Color.black;

    Color cAt2PCMuxOutline = Color.white;
    Color cAt2PCMuxFill = Color.black;

    Color cPCMux2PCOutline = Color.white;
    Color cPCMux2PCFill = Color.black;

    Color cPlus_1_2PCOutline = Color.white;
    Color cPlus_1_2PCFill = Color.black;

    Color cBus2PCMuxOutline = Color.white;
    Color cBus2PCMuxFill = Color.black;

    Color cLogic2PositiveOutline = Color.white;
    Color cLogic2PositiveFill = Color.black;

    Color cBus2LogicOutline = Color.white;
    Color cBus2LogicFill = Color.black;

    Color cLogic2ZeroOutline = Color.white;
    Color cLogic2ZeroFill = Color.black;

    Color cLogic2NegativeOutline = Color.white;
    Color cLogic2NegativeFill = Color.black;

    Color cPCMuxOutline = Color.black;
    Color cPCMuxFill = Color.white;
    //cPCMuxFill

    Color cALUInputOutline = Color.black;
    Color cALUInputFill = Color.white;

    Color cCRTSROutline = Color.black;
    Color cCRTSRFill = Color.white;

    Color cKBDROutline = Color.black;
    Color cKBDRFill = Color.white;

    Color cOutputOutline = Color.black;
    Color cOutputFill = Color.white;


    Color cRegistersOutline = Color.black;
    Color cRegistersFill = Color.white;
}