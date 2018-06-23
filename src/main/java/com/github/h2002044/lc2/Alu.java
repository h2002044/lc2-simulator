package com.github.h2002044.lc2;

import java.math.BigInteger;

public class Alu
{
    protected BigInteger R0 = BigInteger.ZERO;
    protected BigInteger R1 = BigInteger.ZERO;
    protected BigInteger R2 = BigInteger.ZERO;
    protected BigInteger R3 = BigInteger.ZERO;
    protected BigInteger R4 = BigInteger.ZERO;
    protected BigInteger R5 = BigInteger.ZERO;
    protected BigInteger R6 = BigInteger.ZERO;
    protected BigInteger R7 = BigInteger.ZERO;
    boolean flags[] = new boolean[3];

    public static final int NEGATIVE = 0;
    public static final int ZERO = 1;
    public static final int POSITIVE= 2;

    private int length;

    public Alu()
    {
        flags[NEGATIVE] = false;
        flags[ZERO] = false;
        flags[POSITIVE] = false;

    }

    public boolean FlagValue(int iFlag)
    {
        switch(iFlag)
        {
            case POSITIVE:return flags[POSITIVE];
            case NEGATIVE: return flags[NEGATIVE];
            case ZERO: return flags[ZERO];
            default : return false;
        }
    }
    public BigInteger readContents(int iRegister)
    {
        switch(iRegister)
        {
            case Processor.R0:
                return R0;

            case Processor.R1:
                return R1;

            case Processor.R2:
                return R2;

            case Processor.R3:
                return R3;

            case Processor.R4:
                return R4;

            case Processor.R5:
                return R5;

            case Processor.R6:
                return R6;

            case Processor.R7:
                return R7;

            default :
                return BigInteger.ZERO;
        }
    }

    public void writeContents(int iRegister, BigInteger biValue)
    {
        if(iRegister >=0 && iRegister <=7)
        {
            try
            {
                int y = biValue.compareTo(BigInteger.ZERO);

                switch (y)
                {
                    case 0:
                        flags[ZERO] = true;
                        flags[NEGATIVE] = false;
                        flags[POSITIVE] = false;
                        break;
                    case 1:
                        flags[POSITIVE] = true;
                        flags[ZERO] = false;
                        flags[NEGATIVE] = false;
                        break;
                    case -1:
                        flags[NEGATIVE] = true;
                        flags[ZERO] = false;
                        flags[POSITIVE] = false;
                        break;
                }

                switch(iRegister)
                {
                    case Processor.R0:
                        R0 = biValue;
                        break;
                    case Processor.R1:
                        R1 = biValue;
                        break;
                    case Processor.R2:
                        R2 = biValue;
                        break;
                    case Processor.R3:
                        R3 = biValue;
                        break;
                    case Processor.R4:
                        R4 = biValue;
                        break;
                    case Processor.R5:
                        R5 = biValue;
                        break;
                    case Processor.R6:
                        R6 = biValue;
                        break;
                    case Processor.R7:
                        R7 = biValue;
                        break;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void Initialise()
    {
        R0 = R1 = R2 = R3 = R4 = R5 = R6 = R7 =  BigInteger.ZERO;
    }
    public int getLength()
    {
        return length;
    }
    public void setLength(int length)
    {
        this.length = length;
    }
}
