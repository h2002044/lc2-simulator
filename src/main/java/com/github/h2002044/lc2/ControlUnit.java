package com.github.h2002044.lc2;

import java.math.BigInteger;
import java.util.Random;

public class ControlUnit {

    /*THIS CLASS SIMULATES CONTROL UNIT OF PROCESSOR.IT DELEGATES ALL THE OPERATIONS USING PROGRAM COUNTER & STACK POINTER*/

    private boolean readorwrite;
    private boolean memorio;
    private BigInteger SP = BigInteger.ZERO;
    private BigInteger PC = new BigInteger("0");
    private BigInteger biFlagRegister;

    private int length;


    public void Initialise() {
        setReadorwrite(true);
        setMemorio(true);

        setSP(new BigInteger(8 * getLength(), new Random(8 * getLength())));
        setPC(new BigInteger(8 * getLength(), new Random(8 * getLength())));

        setSP(BigInteger.ZERO);
        setPC(BigInteger.ZERO);
    }


    public boolean getRorW() {
        return isReadorwrite();
    }

    public boolean getMemorIO() {
        return isMemorio();
    }

    /**
     * Set Read or Write Option
     *
     * @ param boolean, If TRUE->WRITE If FALSE->READ
     */
    public void setRorW(boolean a) {
        setReadorwrite(a);
    }

    /**
     * Set Memory or IO Option
     *
     * @ param boolean, If TRUE->MEMORY If FALSE->I/O
     */
    public void setMemorIO(boolean a) {
        setMemorio(a);
    }

    /**
     * Get Data from Stack Pointer
     *
     * @ returns BigInteger
     */

    public BigInteger MovFromSP() {
        return (getSP());
    }


    public void MovToSP(BigInteger x) {
        setSP(x);
    }


    public BigInteger MovFromPC() {
        return (getPC());
    }


    public void MovToPC(BigInteger x) {
        setPC(x);
    }


    public void InrPC() {
        setPC(getPC().add(BigInteger.ONE));
    }

    public void InrSP() {
        setSP(getSP().add(BigInteger.ONE));
    }

    public void DcrSP() {
        setSP(getSP().subtract(BigInteger.ONE));
    }


    public void DcrPC() {
        setPC(getPC().subtract(BigInteger.ONE));
    }

    public boolean isReadorwrite() {
        return readorwrite;
    }

    public void setReadorwrite(boolean readorwrite) {
        this.readorwrite = readorwrite;
    }

    public boolean isMemorio() {
        return memorio;
    }

    public void setMemorio(boolean memorio) {
        this.memorio = memorio;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public BigInteger getBiFlagRegister() {
        return biFlagRegister;
    }

    public void setBiFlagRegister(BigInteger biFlagRegister) {
        this.biFlagRegister = biFlagRegister;
    }

    public BigInteger getSP() {
        return SP;
    }

    public void setSP(BigInteger SP) {
        this.SP = SP;
    }

    public BigInteger getPC() {
        return PC;
    }

    public void setPC(BigInteger PC) {
        this.PC = PC;
    }
}