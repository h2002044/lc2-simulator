package com.github.h2002044.lc2;

import java.math.BigInteger;

public class ALU {

    class Register {
    private BigInteger value = BigInteger.ZERO;
        public void setValue(BigInteger value){
            this.value = value;
        }

        public BigInteger getValue(){
            return value;
        }
    }

    private Register R0, R1, R2, R3, R4, R5, R6, R7;

    boolean flags[] = new boolean[3];

    public static final int NEGATIVE = 0;
    public static final int ZERO = 1;
    public static final int POSITIVE = 2;

    private int length;

    public ALU() {
        flags[NEGATIVE] = false;
        flags[ZERO] = false;
        flags[POSITIVE] = false;

    }

    public boolean FlagValue(int iFlag) {
        switch (iFlag) {
            case POSITIVE:
                return flags[POSITIVE];
            case NEGATIVE:
                return flags[NEGATIVE];
            case ZERO:
                return flags[ZERO];
            default:
                return false;
        }
    }

    public BigInteger readContent(int iRegister) {
        switch (iRegister) {
            case Processor.R0:
                return R0.getValue();

            case Processor.R1:
                return R1.getValue();

            case Processor.R2:
                return R2.getValue();

            case Processor.R3:
                return R3.getValue();

            case Processor.R4:
                return R4.getValue();

            case Processor.R5:
                return R5.getValue();

            case Processor.R6:
                return R6.getValue();

            case Processor.R7:
                return R7.getValue();

            default:
                return BigInteger.ZERO;
        }
    }

    public void writeContent(int iRegister, BigInteger biValue) {
        if (iRegister >= 0 && iRegister <= 7) {
            try {
                int y = biValue.compareTo(BigInteger.ZERO);

                switch (y) {
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

                switch (iRegister) {
                    case Processor.R0:
                        R0.setValue(biValue);
                        break;
                    case Processor.R1:
                        R1.setValue(biValue);
                        break;
                    case Processor.R2:
                        R2.setValue(biValue);
                        break;
                    case Processor.R3:
                        R3.setValue(biValue);
                        break;
                    case Processor.R4:
                        R4.setValue(biValue);
                        break;
                    case Processor.R5:
                        R5.setValue(biValue);
                        break;
                    case Processor.R6:
                        R6.setValue(biValue);
                        break;
                    case Processor.R7:
                        R7.setValue(biValue);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
