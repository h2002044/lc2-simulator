package com.github.h2002044.lc2;

import java.math.BigInteger;
import java.util.BitSet;

/**
 * Class Processor does the processing of all the instructions specified by the programmer.
 */

public class Processor {
    Instruction instruction = new Instruction();
    private com.github.h2002044.lc2.ALU ALU;
    private ControlUnit objCU;
    private Storage objMemory;
    private MAR objMAR;
    private MDR objMDR;

    final int PARITY_BIT = 1;
    final int CARRY_BIT = 3;
    final int ZERO_BIT = 5;
    final int SIGN_BIT = 7;

    public static int iStartingLocation;
    public static SimulatorPanel objExecute;


    public static final int R0 = 0;
    public static final int R1 = 1;
    public static final int R2 = 2;
    public static final int R3 = 3;
    public static final int R4 = 4;
    public static final int R5 = 5;
    public static final int R6 = 6;
    public static final int R7 = 7;
    public static final int PC = 8;


    public Processor() {
        ALU = new ALU();
        objCU = new ControlUnit();
        objMemory = new Storage();
        objMAR = new MAR();
        objMDR = new MDR();
    }

    public BigInteger getData(BigInteger biLocation) {
        return objMemory.getData(biLocation);
    }

    public void putData(BigInteger biData, BigInteger biLocation) {
        objMemory.putData(biData, biLocation);
    }

    public void IncrementPC() {
        objCU.InrPC();
    }

    public void MovToPC(BigInteger biValue) {
        objCU.MovToPC(biValue);
    }

    public void MovToSP(BigInteger biValue) {
        objCU.MovToSP(biValue);
    }

    public BigInteger MovFromPC() {
        return objCU.MovFromPC();
    }

    public BigInteger MovFromSP() {
        return objCU.MovFromSP();
    }

    public boolean FlagValue(int iFlag) {
        return ALU.FlagValue(iFlag);
    }

    public BigInteger readContents(int iRegister) {
        switch (iRegister) {
            case Processor.R0:
            case Processor.R1:
            case Processor.R2:
            case Processor.R3:
            case Processor.R4:
            case Processor.R5:
            case Processor.R6:
            case Processor.R7:
                return ALU.readContent(iRegister);
            case Processor.PC:
                return objCU.MovFromPC();
            default:
                return BigInteger.ZERO;
        }
    }

    public void writeContents(int iRegister, BigInteger biValue) {
        switch (iRegister) {
            case Processor.R0:
            case Processor.R1:
            case Processor.R2:
            case Processor.R3:
            case Processor.R4:
            case Processor.R5:
            case Processor.R6:
            case Processor.R7:
                ALU.writeContent(iRegister, biValue);
                break;
            case Processor.PC:
                MovToPC(biValue);
                break;
        }
    }


    public BitSet StringtoBitSet(String sData) {
        BitSet biTemp = new BitSet(16);

        for (int i = 0; i < sData.length(); i++) {
            if (sData.charAt(i) == '1') {
                biTemp.set(i);
            }
        }
        return biTemp;
    }

    public void fANDI(BigInteger reg, BigInteger sr1, BigInteger data) {
        objCU.setRorW(true);
        objCU.setMemorIO(true);
        String tempStr = data.toString(2);
        String temp = new String();
        BigInteger tempData = BigInteger.ZERO;
        if (data.toString(2).length() > 5)/*if data is too long*/
            tempStr = data.toString(2).substring(data.toString(2).length() - 5, data.toString().length());


        else if (data.toString(2).length() < 5)/*if data is too short*/ {
            for (int i = 0; i < (5 - data.toString(2).length()); i++)
                tempStr = "0" + tempStr;

        }
        /*if data is excatly 5 bits*/
        else
            tempStr = data.toString(2);
        int Stuff = tempStr.length();
        if (tempStr.charAt(0) == '1') {
            for (int i = 0; i < (16 - Stuff); i++)
                tempStr = "1" + tempStr;
        } else if (tempStr.charAt(0) == '0') {
            for (int i = 0; i < (16 - Stuff); i++)
                tempStr = "0" + tempStr;
        } else
            System.out.println("Unable to convert to Binary");


        if (sr1.intValue() >= Processor.R0 && sr1.intValue() <= Processor.R7) {
            temp = get3DigitRegisterCode(sr1.intValue()) + "1" + data.toString(2);
            instruction.putData(this.StringtoBitSet(temp));
            tempData = readContents(sr1.intValue()).and(new BigInteger(tempStr, 2));
        } else {
            temp = get3DigitRegisterCode(Processor.R0) + "1" + data.toString(2);
            instruction.putData(this.StringtoBitSet(temp));
            tempData = readContents(Processor.R0).and(new BigInteger(tempStr, 2));
        }

        if (reg.intValue() >= Processor.R0 && reg.intValue() <= Processor.R7) {
            writeContents(reg.intValue(), tempData);
            temp = "0101" + get3DigitRegisterCode(reg.intValue()) + temp;
        } else {
            temp = "0101" + "000" + temp;
        }

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H       O P E R A N D    -- BEGIN");

        objExecute.objExecutionSummary.fAddText("\n Source Register is R-" + sr1.intValue());
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H       O P E R A N D -- END");

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- BEGIN");

        objExecute.objExecutionSummary.fAddText("\n Destination Register is R-" + reg.intValue());
        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- END");

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        objExecute.objDataFlow.fExecuteforAlu(reg.intValue(), sr1.intValue(), 9);
    }

    public void fANDR(BigInteger reg, BigInteger sr1, BigInteger sr2) {
        // Add with register, contents of register.
        objCU.setRorW(true);
        objCU.setMemorIO(true);
        BigInteger Data = BigInteger.ZERO;
        String temp = new String();

        int iSourceRegister = Processor.R0;
        int iDestinationRegister = Processor.R0;

        if (sr1.intValue() >= Processor.R0 && sr1.intValue() <= Processor.R7) {
            temp = get3DigitRegisterCode(sr1.intValue()) + "000";
        } else {
            temp = get3DigitRegisterCode(Processor.R0) + "000";
        }


        if (sr2.intValue() >= Processor.R0 && sr2.intValue() <= Processor.R7) {
            temp += get3DigitRegisterCode(sr2.intValue());
        } else {
            temp += get3DigitRegisterCode(Processor.R0);
        }

        System.out.println("TEMP Value in newCode : " + temp);
        instruction.putData(this.StringtoBitSet(temp));
        Data = readContents(sr1.intValue()).and(readContents(sr2.intValue()));


        if (reg.intValue() >= Processor.R0 && reg.intValue() <= Processor.R7) {
            writeContents(reg.intValue(), Data);
            temp = "0101" + get3DigitRegisterCode(reg.intValue()) + temp;
        } else {
            writeContents(Processor.R0, Data);
            temp = "0101" + get3DigitRegisterCode(Processor.R0) + temp;
        }

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H       O P E R A N D    -- BEGIN");

        objExecute.objExecutionSummary.fAddText("\n Source Register are R-" + sr1.intValue() + "  R-" + sr2.intValue());

        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H       O P E R A N D -- ENDS");

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- BEGIN");
        objExecute.objExecutionSummary.fAddText("\nExecute for Register R- " + reg.intValue());
        objExecute.objDataFlow.fExecuteforAlu(reg.intValue(), sr1.intValue(), sr2.intValue());

        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
    }

    public void LEA(BigInteger reg, BigInteger data, BigInteger PC) {
        objCU.setRorW(true);
        objCU.setMemorIO(true);
        String temp = new String();
        String tempPC = PC.toString(2);
        /*IF PROGRAM COUNTER IS NOT 16 BITS THEN CONVERT IT*/
        for (int iloop = 0; iloop < (16 - PC.toString(2).length()); iloop++) {
            tempPC = "0" + tempPC;
        }
        if (tempPC.length() >= 7)
            tempPC = tempPC.substring(0, 7) + data.toString(2);
        else
            tempPC = tempPC + data.toString(2);

        System.out.println(tempPC);

        BigInteger Calculateddata = new BigInteger(tempPC, 2);


        if (reg.intValue() >= Processor.R0 && reg.intValue() <= Processor.R7) {
            writeContents(reg.intValue(), Calculateddata);
            temp = "1110" + get3DigitRegisterCode(reg.intValue()) + data.toString(2);
            instruction.putData(StringtoBitSet(temp));
        } else {
            writeContents(Processor.R0, Calculateddata);
            temp = "1110" + get3DigitRegisterCode(Processor.R0) + data.toString(2);
            instruction.putData(StringtoBitSet(temp));
        }

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H   O P E R A N D -- BEGIN");
        objExecute.objExecutionSummary.fAddText("\nData Offset is " + data.toString(16) + "(Hex)");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H   O P E R A N D -- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- BEGIN");
        objExecute.objExecutionSummary.fAddText("\nDestination Register is R" + reg.intValue());

        objExecute.objDataFlow.ExecuteforLEA(reg.intValue());

        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
    }

    public void LDR(BigInteger reg1, BigInteger reg2, BigInteger offset) {                             /* LDR Instruction*/
        objCU.setRorW(true);
        objCU.setMemorIO(true);
        BigInteger temp1;

        String temp = new String("0110");
        int iSourceRegister = Processor.R0;
        int iDestinationRegister = Processor.R0;

        if (reg1.intValue() >= Processor.R0 && reg1.intValue() <= Processor.R7) {
            temp += get3DigitRegisterCode(reg1.intValue());
        } else {
            temp += get3DigitRegisterCode(Processor.R0);
        }

        if (reg2.intValue() >= Processor.R0 && reg2.intValue() <= Processor.R7) {
            temp += get3DigitRegisterCode(reg2.intValue());
        } else {
            temp += get3DigitRegisterCode(Processor.R0);
        }

        temp += offset.toString(2);
        instruction.putData(this.StringtoBitSet(temp));
        temp1 = objMemory.getData(readContents(iDestinationRegister).add(offset));
        writeContents(iSourceRegister, temp1);

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H    O P E R A N D -- BEGIN");

        objExecute.objExecutionSummary.fAddText("\nBase Register is R-" + reg2.intValue() + " and offest" + offset.intValue() + "(Hex)");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H    O P E R A N D -- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- BEGIN");

        objExecute.objExecutionSummary.fAddText("\n Destination Register is R-" + reg2.intValue());
        objExecute.objDataFlow.fExecuteforLDR(reg1.intValue(), reg2.intValue());

        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
    }

    public void AddR(BigInteger reg, BigInteger sr1, BigInteger sr2) {
        // Add with register, contents of register.
        objCU.setRorW(true);
        objCU.setMemorIO(true);
        BigInteger biValue;
        BigInteger Data = BigInteger.ZERO;

        String temp = new String();

        if (sr1.intValue() >= Processor.R0 && sr1.intValue() <= Processor.R7) {
            temp += get3DigitRegisterCode(sr1.intValue());
            temp += "000";

            if (sr2.intValue() >= Processor.R0 && sr2.intValue() <= Processor.R7) {
                temp += get3DigitRegisterCode(sr2.intValue());
            } else {
                temp += "000";
            }
        } else {
            temp += "000";
        }

        instruction.putData(this.StringtoBitSet(temp));
        Data = readContents(sr1.intValue()).add(readContents(sr2.intValue()));

        if (reg.intValue() >= Processor.R0 && reg.intValue() <= Processor.R7) {
            writeContents(reg.intValue(), Data);
            temp = "0001" + get3DigitRegisterCode(reg.intValue()) + temp;
        } else {
            temp = "0001" + "000" + temp;
        }

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H       O P E R A N D S    -- BEGIN");
        objExecute.objExecutionSummary.fAddText("\n Fetch operands  for ADDR R" + sr1.intValue() + " and R" + sr2.intValue());
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H       O P E R A N D S    -- END");

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- BEGIN");
        objExecute.objExecutionSummary.fAddText("\nExecute for ADDR for register R" + reg.intValue());

        objExecute.objDataFlow.fExecuteforAlu(reg.intValue(), sr1.intValue(), sr2.intValue());

        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
    }

    public void AddI(BigInteger reg, BigInteger sr1, BigInteger data) {
        //  Add Immediate
        objCU.setRorW(true);
        objCU.setMemorIO(true);
        String tempStr = data.toString(2);
        String temp = new String();
        BigInteger tempData = BigInteger.ZERO;


        if (data.toString(2).length() > 5)/*if data is too long*/
            tempStr = data.toString(2).substring(data.toString(2).length() - 5, data.toString().length());


        else if (data.toString(2).length() < 5)/*if data is too short*/ {
            for (int i = 0; i < (5 - data.toString(2).length()); i++)
                tempStr = "0" + tempStr;

        }
        /*if data is excatly 5 bits*/
        else
            tempStr = data.toString(2);
        int stuff = tempStr.length();
        if (tempStr.charAt(0) == '1') {
            for (int i = 0; i < (16 - stuff); i++)
                tempStr = "1" + tempStr;
        } else if (tempStr.charAt(0) == '0') {
            for (int i = 0; i < (16 - stuff); i++)
                tempStr = "0" + tempStr;
        } else
            System.out.println("Unable to convert to Binary");

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\n F E T C H    O P E R A N D S -- BEGIN");
        objExecute.objExecutionSummary.fAddText("\nThe Registers are Identified");

        temp = get4DigitRegisterCode(sr1.intValue()) + data.toString(2);
        instruction.putData(this.StringtoBitSet(temp));
        objExecute.objDataFlow.fFetchOperandsforReg(sr1.intValue());

        objExecute.objExecutionSummary.fAddHeadingText("\n F E T C H    O P E R A N D S -- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        try {
            if (reg.intValue() >= Processor.R0 && reg.intValue() <= Processor.R7) {
                writeContents(reg.intValue(), tempData);
                temp = "0101" + get3DigitRegisterCode(reg.intValue()) + temp;
            }

            objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
            objExecute.objExecutionSummary.fAddHeadingText("\n E X E C U T E   O P E R A T I O N - START");
            objExecute.objExecutionSummary.fAddText("\nExecute  ADDI for Register R" + reg.intValue());
            objExecute.objDataFlow.fExecuteforAlu(reg.intValue(), sr1.intValue(), 9);

            objExecute.objExecutionSummary.fAddHeadingText("\n E X E C U T E   O P E R A T I O N - END");
            objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RET() {

        String code = "1101000000000000";

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H       O P E R A N D    -- BEGIN");

        objExecute.objExecutionSummary.fAddText("Source Register is R-7");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H       O P E R A N D -- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");


        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- BEGIN");
        MovToPC(readContents(Processor.R7));
        objExecute.objDataFlow.fExecuteforRet();

        objExecute.objExecutionSummary.fAddText("\nNew PC Value is " + readContents(Processor.R7).toString(16) + "(Hex)");

        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");


    }

    public void JMP(boolean N, boolean Z, boolean P, BigInteger offset, BigInteger PC) {
        // Jump.
        String finalStr;
        String subStr = offset.toString(2);
        String temp = PC.toString(2);
        String code;

        /*IF PROGRAM COUNTER IS NOT 16 BITS THEN CONVERT IT*/
        for (int iloop = 0; iloop < (16 - PC.toString(2).length()); iloop++) {
            temp = "0" + temp;
        }
        /*IF OFFSET IS NOT 9 BITS CONVERT IT INTO 9 BITS*/
        for (int iloop = 0; iloop < (9 - offset.toString(2).length()); iloop++) {
            subStr = "0" + subStr;
        }
        finalStr = temp.substring(0, 7) + subStr;

        makeDelay(1000);

        objExecute.objDataFlow.fExecuteforPC(false);
        objExecute.objDataFlow.fSetPC();

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H       O P E R A N D    -- BEGIN");

        objExecute.objExecutionSummary.fAddText("\n offset is" + offset.toString(16) + "(Hex)");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H       O P E R A N D -- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- BEGIN");

        objExecute.objExecutionSummary.fAddText("\nNew PC Value is " + new BigInteger(finalStr, 2).toString(16).toUpperCase());

        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        if (N == true && FlagValue(com.github.h2002044.lc2.ALU.NEGATIVE) == true) {
            MovToPC(new BigInteger(finalStr, 2));
            objExecute.objDataFlow.fSetFlags(true, false, false);
        }
        if (Z == true && FlagValue(com.github.h2002044.lc2.ALU.ZERO) == true) {
            objExecute.objDataFlow.fSetFlags(false, true, false);
            MovToPC(new BigInteger(finalStr, 2));
        }
        if (P == true && FlagValue(com.github.h2002044.lc2.ALU.POSITIVE) == true) {
            objExecute.objDataFlow.fSetFlags(false, false, true);
            MovToPC(new BigInteger(finalStr, 2));
        }
        makeDelay(1000);
    }

    private void makeDelay(int iMilliSec) {
        iMilliSec = iMilliSec > 0 ? iMilliSec : 500;

        try {
            Thread.sleep(iMilliSec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LD(BigInteger reg, BigInteger offset, BigInteger PC) {
        String code = new String();
        String finalStr;
        String subStr = offset.toString(2);
        String temp = PC.toString(2);
        /*IF PROGRAM COUNTER IS NOT 16 BITS THEN CONVERT IT*/

        for (int iloop = 0; iloop < (16 - PC.toString(2).length()); iloop++) {
            temp = "0" + temp;
        }
        /*IF OFFSET IS NOT 9 BITS CONVERT IT INTO 9 BITS*/
        for (int iloop = 0; iloop < (9 - offset.toString(2).length()); iloop++) {
            subStr = "0" + subStr;
        }
        finalStr = temp.substring(0, 7) + subStr;
        code = subStr;

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H    O P E R A N D -- BEGIN");

        objExecute.objExecutionSummary.fAddText("\nLD Instruction wit Offset-" + offset.toString(16) + "(Hex)");

        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H    O P E R A N D -- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        if (reg.intValue() >= Processor.R0 && reg.intValue() <= Processor.R7) {
            code = "0010" + get3DigitRegisterCode(reg.intValue()) + subStr;
            writeContents(reg.intValue(), objMemory.getData(new BigInteger(finalStr, 2)));
        }

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- BEGIN");

        objExecute.objExecutionSummary.fAddText("\nLD Instruction with register value R-" + reg.intValue());

        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
    }

    public void LDI(BigInteger reg, BigInteger offset, BigInteger PC) {
        String code;
        String finalStr;
        String subStr = offset.toString(2);
        String temp = PC.toString(2);
        /*IF PROGRAM COUNTER IS NOT 16 BITS THEN CONVERT IT*/
        for (int iloop = 0; iloop < (16 - PC.toString(2).length()); iloop++) {
            temp = "0" + temp;
        }
        /*IF OFFSET IS NOT 9 BITS CONVERT IT INTO 9 BITS*/
        for (int iloop = 0; iloop < (9 - offset.toString(2).length()); iloop++) {
            subStr = "0" + subStr;
        }
        finalStr = temp.substring(0, 7) + subStr;
        code = subStr;
        BigInteger addr = objMemory.getData(new BigInteger(finalStr, 2));

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\n E V A L U A T E    A D D R E S S  -- BEGIN");

        objExecute.objDataFlow.fEvaluateAddress();

        objExecute.objExecutionSummary.fAddText("\nNew PC Value is " + addr.toString(16).toUpperCase() + "(Hex)");

        objExecute.objExecutionSummary.fAddHeadingText("\n E V A L U A T E    A D D R E S S  -- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        if (reg.intValue() >= Processor.R0 && reg.intValue() <= Processor.R7) {
            code = "1010" + get3DigitRegisterCode(reg.intValue()) + subStr;
            writeContents(reg.intValue(), objMemory.getData(addr));
        }

        objExecute.objDataFlow.fFetchOperandsforMemory();

        objExecute.objDataFlow.fExecuteforLD(reg.intValue());
    }

    public void STI(BigInteger reg, BigInteger offset, BigInteger PC) {
        String code;
        String finalStr;
        String subStr = offset.toString(2);
        String temp = PC.toString(2);
        /*IF PROGRAM COUNTER IS NOT 16 BITS THEN CONVERT IT*/
        for (int iloop = 0; iloop < (16 - PC.toString(2).length()); iloop++) {
            temp = "0" + temp;
        }
        /*IF OFFSET IS NOT 9 BITS CONVERT IT INTO 9 BITS*/
        for (int iloop = 0; iloop < (9 - offset.toString(2).length()); iloop++) {
            subStr = "0" + subStr;
        }

        finalStr = temp.substring(0, 7) + subStr;
        code = subStr;
        BigInteger addr = objMemory.getData(new BigInteger(finalStr, 2));

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nE V A L U A T E   A D D R E S S -- BEGIN");

        objExecute.objDataFlow.fEvaluateAddress();

        objExecute.objExecutionSummary.fAddText("\nNew PC Value is " + addr.toString(16).toUpperCase() + "\n");
        objExecute.objExecutionSummary.fAddHeadingText("\nE V A L U A T E   A D D R E S S -- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        objExecute.objDataFlow.fFetchOperandsforMemory();

        if (reg.intValue() >= Processor.R0 && reg.intValue() <= Processor.R7) {
            code = "1011" + get3DigitRegisterCode(reg.intValue()) + subStr;
            objMemory.putData(readContents(reg.intValue()), addr);
        }

        objExecute.objDataFlow.fExecuteforST(reg.intValue());
    }

    public void ST(BigInteger reg, BigInteger offset, BigInteger PC) {
        String code = new String();
        String finalStr;
        String subStr = offset.toString(2);
        String temp = PC.toString(2);
        /*IF PROGRAM COUNTER IS NOT 16 BITS THEN CONVERT IT*/
        for (int iloop = 0; iloop < (16 - PC.toString(2).length()); iloop++) {
            temp = "0" + temp;
        }
        /*IF OFFSET IS NOT 9 BITS CONVERT IT INTO 9 BITS*/
        for (int iloop = 0; iloop < (9 - offset.toString(2).length()); iloop++) {
            subStr = "0" + subStr;
        }
        finalStr = temp.substring(0, 7) + subStr;
        code = subStr;

        objExecute.objDataFlow.fFetchOperandsforMemory();


        if (reg.intValue() >= Processor.R0 && reg.intValue() <= Processor.R7) {
            code = "0011" + get3DigitRegisterCode(reg.intValue()) + subStr;
            objMemory.putData(readContents(reg.intValue()), new BigInteger(finalStr, 2));
            objExecute.objDataFlow.fExecuteforST(reg.intValue());
        }

    }

    public void STR(BigInteger reg1, BigInteger reg2, BigInteger offset) {                             /* LDR Instruction*/
        objCU.setRorW(true);
        objCU.setMemorIO(true);

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H      O P E R A N D S -BEGIN");
        objExecute.objExecutionSummary.fAddText("\nBase Register R" + reg2.intValue() + "  and offset  " + offset.toString(16) + "(Hex)");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H      O P E R A N D S -END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        String temp = new String("0110");
//        int iSourceRegister = Processor.R0;
//        int iDestinationRegister = Processor.R0;

        if (reg1.intValue() >= Processor.R0 && reg1.intValue() <= Processor.R7) {
            temp += get3DigitRegisterCode(reg1.intValue());
        }

        if (reg2.intValue() >= Processor.R0 && reg2.intValue() <= Processor.R7) {
            temp += get3DigitRegisterCode(reg2.intValue());
        }

        temp += offset.toString(2);
        instruction.putData(this.StringtoBitSet(temp));
        objMemory.putData(readContents(reg1.intValue()), readContents(reg2.intValue()).add(offset));

        objExecute.objDataFlow.fExecuterforSTR(reg1.intValue(), reg2.intValue());

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- BEGIN");
        objExecute.objExecutionSummary.fAddText("\nSourceRegister R" + reg1.intValue());

        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
    }

    public void JSR(boolean L, BigInteger offset, BigInteger PC) {              /*DONE ,EXCEPT CODE TO BE GENERATED*/
        // Jump.
        String finalStr;
        String subStr = offset.toString(2);
        String temp = PC.toString(2);
        /*IF PROGRAM COUNTER IS NOT 16 BITS THEN CONVERT IT*/
        for (int iloop = 0; iloop < (16 - PC.toString(2).length()); iloop++) {
            temp = "0" + temp;
        }
        /*IF OFFSET IS NOT 9 BITS CONVERT IT INTO 9 BITS*/
        for (int iloop = 0; iloop < (9 - offset.toString(2).length()); iloop++) {
            subStr = "0" + subStr;
        }
        finalStr = temp.substring(0, 7) + subStr;

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H      O P E R A N D S      F O R  J S R-BEGIN");
        objExecute.objExecutionSummary.fAddText("\n offset is" + offset.toString(16) + "(Hex)");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H      O P E R A N D S      F O R  J S R-END");

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N -BEGIN");
        if (L == true) {
            writeContents(Processor.R7, readContents(Processor.PC));
            objExecute.objExecutionSummary.fAddText("\nSaving Contents of PC in R7");
        }
        objExecute.objExecutionSummary.fAddText("\nNew Contents of PC is " + new BigInteger(finalStr).toString(16));

        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    I N S T R U C T I O N  -END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        MovToPC(new BigInteger(finalStr, 2));
    }

    public void JSRR(boolean L, BigInteger reg, BigInteger offset) {              /*DONE ,EXCEPT CODE TO BE GENERATED*/
        // Jump.
        String finalStr;
        String subStr = offset.toString(2);
        BigInteger temp1 = BigInteger.ZERO;
        String temp = new String();
        String Ltemp = new String();
        /*IF OFFSET IS NOT 5 BITS CONVERT IT INTO 5 BITS*/
        for (int iloop = 0; iloop < (5 - offset.toString(2).length()); iloop++) {
            subStr = "0" + subStr;
        }

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nE V A L U A T E   A D D R E S S -- BEGIN");

        objExecute.objDataFlow.fEvaluateAddress();

        objExecute.objExecutionSummary.fAddText("\nRegister R-" + reg.intValue() + " identified ");
        objExecute.objExecutionSummary.fAddHeadingText("\nE V A L U A T E   A D D R E S S -- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H      O P E R A N D S -BEGIN");
        objExecute.objExecutionSummary.fAddText("\nBase Register R" + reg.intValue() + "  and offset  " + offset.toString(16) + "(Hex)");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H      O P E R A N D S -END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        if (L == true) {
            writeContents(Processor.R7, readContents(Processor.PC));
            Ltemp = "1";
            objExecute.objExecutionSummary.fAddHeadingText("\nContents of PC saved in R7");
        }

        if (reg.intValue() >= Processor.R0 && reg.intValue() <= Processor.R7) {
            temp = "1100" + Ltemp + "00" + get3DigitRegisterCode(reg.intValue()) + offset.toString(2);
            instruction.putData(this.StringtoBitSet(temp));
            temp1 = readContents(reg.intValue()).add(offset);
        }

        objExecute.objDataFlow.fHighlightReg(reg.intValue());
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E  -BEGINS");
        objExecute.objExecutionSummary.fAddText("\nNew PC value is " + temp1.toString(16) + "(Hex)");
        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E  -ENDS");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objDataFlow.fExecuteforPC(L);

        MovToPC(temp1);
    }


    public void NOT(BigInteger desReg, BigInteger sr1) {
        BigInteger temp = BigInteger.ZERO;
        String code = new String();

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H     O P E R A N D -- BEGIN");

        objExecute.objExecutionSummary.fAddText("\nRegister R " + sr1.intValue() + " is Identified as source register");

        objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H    O P E R A N D-- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

        if (sr1.intValue() >= Processor.R0 && sr1.intValue() <= Processor.R7) {
            temp = readContents(sr1.intValue());
            code = get3DigitRegisterCode(sr1.intValue()) + "111111";
        }

        if (desReg.intValue() >= Processor.R0 && desReg.intValue() <= Processor.R7) {
            writeContents(desReg.intValue(), temp.not());
            code = "1001" + get3DigitRegisterCode(desReg.intValue()) + code;
        }

        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E     O P E R A N D -- BEGIN");

        objExecute.objDataFlow.fExecuteforAlu(desReg.intValue(), sr1.intValue(), -1);
        objExecute.objExecutionSummary.fAddText("\n Destination Register is R " + desReg.intValue());

        objExecute.objExecutionSummary.fAddHeadingText("\nE X E C U T E    O P E R A N D-- END");
        objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");
    }

    public String get3DigitRegisterCode(int iRegister) {
        BigInteger biRegister = new BigInteger(new Integer(iRegister).toString());
        String s3DigitCode = biRegister.toString(2);

        while (s3DigitCode.length() < 3) {
            s3DigitCode = "0" + s3DigitCode;
        }
        return s3DigitCode;
    }

    public String get4DigitRegisterCode(int iRegister) {
        BigInteger biRegister = new BigInteger(new Integer(iRegister).toString());
        String s4DigitCode = biRegister.toString(2);

        while (s4DigitCode.length() < 4) {
            s4DigitCode = "0" + s4DigitCode;
        }
        return s4DigitCode;
    }

}