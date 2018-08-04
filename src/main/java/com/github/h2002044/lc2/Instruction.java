package com.github.h2002044.lc2;

import java.util.BitSet;

public class Instruction {
    private BitSet code = new BitSet();

    public BitSet getData() {
        return (getCode());
    }

    public void putData(BitSet d) {
        setCode(d);
    }

    public BitSet getCode() {
        return code;
    }

    public void setCode(BitSet code) {
        this.code = code;
    }
}