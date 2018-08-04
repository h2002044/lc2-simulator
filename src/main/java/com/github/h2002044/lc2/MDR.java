package com.github.h2002044.lc2;

import java.math.BigInteger;

public class MDR {
    private BigInteger data;

    public MDR() {
        setData(BigInteger.ZERO);
    }

    public BigInteger getData() {
        return (data);
    }

    public void putData(BigInteger d) {
        setData(d);
    }

    void setData(BigInteger data) {
        this.data = data;
    }
}
