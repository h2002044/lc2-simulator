package com.github.h2002044.lc2;

import com.github.h2002044.lc2.view.SimulatorWindow;
import com.github.h2002044.lc2.view.SimulatorPanel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

/**
 * Class Execute does the execution of individual instructions specified by the programmer.
 */

public class Execute extends Thread {

    Processor objProcessor;

    private int iRegisterSize = 0;
    private int iCURegisterSize = 0;
    private int iMemorySize = 0;
    private int iLocation = 0;

    private String sMode = "RUN";

    public static int iThreadNo = 1;


    public Execute() {
        objProcessor = new Processor();
    }

    public void Program() {
        String strInput = "";
        System.out.print("Location:");

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
            BigInteger x = new BigInteger(rd.readLine());

            BigInteger opcode = BigInteger.ONE;
            while (true) {
                System.out.print("\n" + x + ":\t");
                strInput = rd.readLine();

                if ((strInput.equalsIgnoreCase("EXIT"))) {
                    opcode = new BigInteger("1111111111111111", 2);
                    objProcessor.putData(opcode, x);
                    x = x.add(BigInteger.ONE);
                    break;
                } else {
                    opcode = new BigInteger(strInput, 2);
                    objProcessor.putData(opcode, x);
                    x = x.add(BigInteger.ONE);
                }
            }
        } catch (IOException e) {
            System.out.println(e.toString());

        }
    }

    /* MODIFIED-JAGADISH */
    public void fopCode(String opcode, BigInteger PC, SimulatorPanel objExecute) {
        try {
            objExecute.objDataFlow.fDecode();

            objExecute.objExecutionSummary.fAddText("\nThe Code Decoded is " + (new BigInteger(opcode.substring(0, 4), 2)).toString(16).toUpperCase() + "(Hex)");
            objExecute.objExecutionSummary.fAddHeadingText("\nD E C O D E   I N S T R U C T I O N -- END");
            objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");


            BigInteger tempOpcode = new BigInteger(opcode.substring(0, 4), 2);
            switch (tempOpcode.intValue()) {
                case 0:
                    boolean N = false, Z = false, P = false;
                    if (opcode.charAt(4) == '1')
                        N = true;
                    if (opcode.charAt(5) == '1')
                        Z = true;
                    if (opcode.charAt(6) == '1')
                        P = true;

                    objProcessor.JMP(N, Z, P, new BigInteger(opcode.substring(7), 2), PC.subtract(BigInteger.ONE));
                    break;

                case 1:
                    if (opcode.charAt(10) == '1') {
                        objProcessor.AddI(new BigInteger(opcode.substring(4, 7), 2), new BigInteger(opcode.substring(7, 10), 2), new BigInteger(opcode.substring(11), 2));
                        break;
                    } else if (opcode.charAt(10) == '0') {
                        objProcessor.AddR(new BigInteger(opcode.substring(4, 7), 2), new BigInteger(opcode.substring(7, 10), 2), new BigInteger(opcode.substring(13), 2));
                        break;
                    } else {
                        System.out.println("Error in parsing ADD operation");
                        break;
                    }

                case 2:
                    objProcessor.LD(new BigInteger(opcode.substring(4, 7), 2), new BigInteger(opcode.substring(7), 2), PC.subtract(BigInteger.ONE));
                    break;

                case 3:
                    objProcessor.ST(new BigInteger(opcode.substring(4, 7), 2), new BigInteger(opcode.substring(7), 2), PC.subtract(BigInteger.ONE));
                    break;
                case 4:
                    boolean L = false;
                    if (opcode.charAt(4) == '1') L = true;
                    objProcessor.JSR(L, new BigInteger(opcode.substring(7), 2), PC.subtract(BigInteger.ONE));
                    break;
                case 5:
                    if (opcode.charAt(10) == '1') {
                        objProcessor.fANDI(new BigInteger(opcode.substring(4, 7), 2), new BigInteger(opcode.substring(7, 10), 2), new BigInteger(opcode.substring(11), 2));
                        break;
                    } else if (opcode.charAt(10) == '0') {
                        objProcessor.fANDR(new BigInteger(opcode.substring(4, 7), 2), new BigInteger(opcode.substring(7, 10), 2), new BigInteger(opcode.substring(13), 2));
                        break;
                    } else {
                        System.out.println("Error in parsing ADD operation");
                        break;
                    }
                case 6:
                    objProcessor.LDR(new BigInteger(opcode.substring(4, 7), 2), new BigInteger(opcode.substring(7, 10), 2), new BigInteger(opcode.substring(10), 2));
                    break;
                case 7:
                    objProcessor.STR(new BigInteger(opcode.substring(4, 7), 2), new BigInteger(opcode.substring(7, 10), 2), new BigInteger(opcode.substring(10), 2));
                    break;
                case 9:
                    objProcessor.NOT(new BigInteger(opcode.substring(4, 7), 2), new BigInteger(opcode.substring(7, 10), 2));
                    break;
                case 10:
                    objProcessor.LDI(new BigInteger(opcode.substring(4, 7), 2), new BigInteger(opcode.substring(7), 2), PC.subtract(BigInteger.ONE));
                    break;
                case 11:
                    objProcessor.STI(new BigInteger(opcode.substring(4, 7), 2), new BigInteger(opcode.substring(7), 2), PC.subtract(BigInteger.ONE));
                    break;
                case 12:
                    boolean L1 = false;
                    if (opcode.charAt(4) == '1') L1 = true;
                    objProcessor.JSRR(L1, new BigInteger(opcode.substring(7, 10), 2), new BigInteger(opcode.substring(10, 16), 2));
                    break;
                case 13:
                    objProcessor.RET();
                    break;
                case 14:
                    objProcessor.LEA(new BigInteger(opcode.substring(4, 7), 2), new BigInteger(opcode.substring(7), 2), PC.subtract(BigInteger.ONE));
                    break;
                default:
                    System.out.println("Error in parsing the code");
                    break;
            }
            /* MODIFIED-JAGADISH */
            this.Show(objExecute);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* MODIFIED-JAGADISH */
    public void Exec(BigInteger loc, SimulatorPanel objExecute) {

        try {
            initializeExecutor(objExecute);

            System.out.println(loc.toString(16) + " loc in EXEC");
            objProcessor.MovToPC(loc);

            String operand = objProcessor.getData(loc).toString(2);
            String temp = operand;

            for (int i = 0; i < (16 - operand.length()); i++) {
                temp = "0" + temp;
            }

            while (!(temp.equalsIgnoreCase("1111111111111111") == true)) {
                objExecute.objRegisters.fDisableRegisters();
                /* MODIFIED-JAGADISH */
                objExecute.objDataFlow.fFetch();
                objExecute.objExecutionSummary.fAddText("\nThe Instruction code fetched is  " + (new BigInteger(temp, 2)).toString(16).toUpperCase());
                objExecute.objExecutionSummary.fAddHeadingText("\nF E T C H    I N S T R U C T I O N -- END");
                objExecute.objExecutionSummary.fAddHeadingText("\n--------------------------------------------------------------------");

                objProcessor.IncrementPC();
                fopCode(temp, objProcessor.readContents(Processor.PC), objExecute);

                objExecute.objSimulate.fResetFlow();
                objExecute.objSimulate.repaint();

                makeDelay(500);

                /* MODIFIED-JAGADISH */

                if (fGetMode().equalsIgnoreCase("STEP") == true) {
                    objExecute.objRegisters.fEnableRegisters();
                    fWait();
                }

                if (fGetMode().equalsIgnoreCase("RUN") == true) {

                }

                if (fGetMode().equalsIgnoreCase("STOP") == true) {
                    objExecute.objRegisters.fEnableRegisters();
                    break;
                }


                objProcessor.writeContents(Processor.R0, objExecute.objRegisters.fGetRegisterValue(Processor.R0));
                objProcessor.writeContents(Processor.R1, objExecute.objRegisters.fGetRegisterValue(Processor.R1));
                objProcessor.writeContents(Processor.R2, objExecute.objRegisters.fGetRegisterValue(Processor.R2));
                objProcessor.writeContents(Processor.R3, objExecute.objRegisters.fGetRegisterValue(Processor.R3));
                objProcessor.writeContents(Processor.R4, objExecute.objRegisters.fGetRegisterValue(Processor.R4));
                objProcessor.writeContents(Processor.R5, objExecute.objRegisters.fGetRegisterValue(Processor.R5));
                objProcessor.writeContents(Processor.R6, objExecute.objRegisters.fGetRegisterValue(Processor.R6));
                objProcessor.writeContents(Processor.R7, objExecute.objRegisters.fGetRegisterValue(Processor.R7));

                BigInteger biPCValue = objProcessor.readContents(Processor.PC);
                biPCValue = biPCValue.subtract(BigInteger.ONE);

                biPCValue = biPCValue.subtract(BigInteger.ONE);


                objProcessor.MovToPC(objExecute.objRegisters.fGetRegisterValue(Processor.PC));

                operand = objProcessor.getData(objProcessor.readContents(Processor.PC)).toString(2);

                temp = operand;
                for (int i = 0; i < (16 - operand.length()); i++) {
                    temp = "0" + temp;
                }
            }
            objExecute.objRegisters.fEnableRegisters();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makeDelay(int iMilliSec) {
        iMilliSec = iMilliSec > 0 ? iMilliSec : 500;

        try {
            Thread.sleep(iMilliSec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeExecutor(SimulatorPanel objExecute) {
        System.out.println("Execute : " + objExecute);
        objExecute.objExecutionSummary.setTextPaneSummary("");
        objExecute.objRegisters.fResetRegisters();
        objExecute.objOutputSummary.clearText();
        objExecute.objOutputSummary.addText("  R0  \t  R1  \t  R2  \t  R3  \t  R4  \t  R5  \t  R6  \t  R7  \t  PC  \t  N  \t  Z  \t  P  \n\n");
    }

    public void fSetMode(String sMode) {
        if (sMode.equalsIgnoreCase("STOP") == true) {
            this.setMode("STOP");
        } else if (sMode.equalsIgnoreCase("STEP") == true) {
            this.setMode("STEP");
        } else if (sMode.equalsIgnoreCase("RUN") == true) {
            this.setMode("RUN");
        } else if (sMode.equalsIgnoreCase("MICROSTEP") == true) {
            this.setMode("MICROSTEP");
        }

    }

    public String fGetMode() {
        return getMode();
    }

    public synchronized void fNotify() {
        try {
            notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void fWait() {
        try {
            Thread.currentThread().wait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* MODIFIED-JAGADISH */
    public void Show(SimulatorPanel objExecute) {

        System.out.println("R0\t\tR1\t\tR2\t\tR3\t\tR4\t\tR5\t\tR6\t\tR7\t\tPC\t\tSP\t\tN\tZ\tP");
        System.out.println(objProcessor.readContents(Processor.R0).intValue() + "\t\t" +
                objProcessor.readContents(Processor.R1).intValue() + "\t\t" + objProcessor.readContents(Processor.R2).intValue() +
                "\t\t" + objProcessor.readContents(Processor.R3).intValue() + "\t\t" + objProcessor.readContents(Processor.R4).intValue() +
                "\t\t" + objProcessor.readContents(Processor.R5).intValue() + "\t\t" + objProcessor.readContents(Processor.R6).intValue() +
                "\t\t" + objProcessor.readContents(Processor.R7).intValue() + "\t\t" + objProcessor.readContents(Processor.PC).intValue() +
                "\t\t" + objProcessor.MovFromSP().intValue() + "\t\t" + objProcessor.FlagValue(ALU.NEGATIVE) + "\t" +
                objProcessor.FlagValue(ALU.ZERO) +
                "\t" + objProcessor.FlagValue(ALU.POSITIVE));

        String sRegisters[] = new String[9];
        sRegisters[Processor.R0] = objProcessor.readContents(Processor.R0).toString(16);
        sRegisters[Processor.R1] = objProcessor.readContents(Processor.R1).toString(16);
        sRegisters[Processor.R2] = objProcessor.readContents(Processor.R2).toString(16);
        sRegisters[Processor.R3] = objProcessor.readContents(Processor.R3).toString(16);
        sRegisters[Processor.R4] = objProcessor.readContents(Processor.R4).toString(16);
        sRegisters[Processor.R5] = objProcessor.readContents(Processor.R5).toString(16);
        sRegisters[Processor.R6] = objProcessor.readContents(Processor.R6).toString(16);
        sRegisters[Processor.R7] = objProcessor.readContents(Processor.R7).toString(16);
        sRegisters[Processor.PC] = objProcessor.readContents(Processor.PC).toString(16);


        for (int iLoop = 0; iLoop <= 8; iLoop++) {
            if (sRegisters[iLoop].length() == 1) {
                sRegisters[iLoop] = "000" + sRegisters[iLoop];
            } else if (sRegisters[iLoop].length() == 2) {
                sRegisters[iLoop] = "00" + sRegisters[iLoop];
            } else if (sRegisters[iLoop].length() == 3) {
                sRegisters[iLoop] = "0" + sRegisters[iLoop];
            }
        }
        objExecute.objOutputSummary.addText(sRegisters[0] + "\t" + sRegisters[1] + "\t" + sRegisters[2] + "\t" +
                sRegisters[3] + "\t" + sRegisters[4] + "\t" + sRegisters[5] + "\t" + sRegisters[6] + "\t" +
                sRegisters[7] + "\t" + sRegisters[8] + "\t" + objProcessor.FlagValue(ALU.NEGATIVE) + "\t" + objProcessor.FlagValue(ALU.ZERO) + "\t" +
                objProcessor.FlagValue(ALU.POSITIVE) + "\n\n");

        System.out.println("");

        objExecute.objRegisters.fSetRegisterValue(Processor.R0, objProcessor.readContents(Processor.R0));
        objExecute.objRegisters.fSetRegisterValue(Processor.R1, objProcessor.readContents(Processor.R1));
        objExecute.objRegisters.fSetRegisterValue(Processor.R2, objProcessor.readContents(Processor.R2));
        objExecute.objRegisters.fSetRegisterValue(Processor.R3, objProcessor.readContents(Processor.R3));
        objExecute.objRegisters.fSetRegisterValue(Processor.R4, objProcessor.readContents(Processor.R4));
        objExecute.objRegisters.fSetRegisterValue(Processor.R5, objProcessor.readContents(Processor.R5));
        objExecute.objRegisters.fSetRegisterValue(Processor.R6, objProcessor.readContents(Processor.R6));
        objExecute.objRegisters.fSetRegisterValue(Processor.R7, objProcessor.readContents(Processor.R7));

        objExecute.objRegisters.fSetRegisterValue(Processor.PC, objProcessor.readContents(Processor.PC));

        int iPositive = 0;
        int iNegative = 0;
        int iZero = 0;

        iNegative = (objProcessor.FlagValue(ALU.NEGATIVE) == true) ? 1 : 0;
        iZero = (objProcessor.FlagValue(ALU.ZERO) == true) ? 1 : 0;
        iPositive = (objProcessor.FlagValue(ALU.POSITIVE) == true) ? 1 : 0;

        objExecute.objRegisters.fSetRegisterValue(9, new BigInteger(new Integer(iZero).toString()));
        objExecute.objRegisters.fSetRegisterValue(10, new BigInteger(new Integer(iPositive).toString()));
        objExecute.objRegisters.fSetRegisterValue(11, new BigInteger(new Integer(iNegative).toString()));
    }

    public void run() {
        makeDelay(1000);
        Exec(new BigInteger(new Integer(Processor.getiStartingLocation()).toString()), Processor.getObjExecute());

        SimulatorWindow.setRun(false);
    }

    public void fopenfile(String Filename) {
        try {
            File ifileopen = new File(Filename);
            OutputStreamWriter streamWriter = new OutputStreamWriter(new FileOutputStream(ifileopen), "UTF_8");
            String tempFileData = new String();
            //String strLoc = new String();
            for (int iloop = 0; iloop <= 65535; iloop++) {
                //  strLoc=new Integer(iloop).toString();
                tempFileData = objProcessor.getData(new BigInteger(new Integer(iloop).toString())).toString(16);
                if (tempFileData.equalsIgnoreCase("FFFF") == false) {
                    streamWriter.write(new Integer(iloop).toString() + "\t" + tempFileData);

                }
            }
            streamWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getRegisterSize() {
        return iRegisterSize;
    }

    public void setRegisterSize(int iRegisterSize) {
        this.iRegisterSize = iRegisterSize;
    }

    public int getCURegisterSize() {
        return iCURegisterSize;
    }

    public void setCURegisterSize(int iCURegisterSize) {
        this.iCURegisterSize = iCURegisterSize;
    }

    public int getMemorySize() {
        return iMemorySize;
    }

    public void setMemorySize(int iMemorySize) {
        this.iMemorySize = iMemorySize;
    }

    public int getLocation() {
        return iLocation;
    }

    public void setLocation(int iLocation) {
        this.iLocation = iLocation;
    }

    public String getMode() {
        return sMode;
    }

    public void setMode(String sMode) {
        this.sMode = sMode;
    }
}
