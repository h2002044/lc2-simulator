package com.github.h2002044.lc2;

import java.math.BigInteger;
import java.util.Hashtable;

public class Storage {
    public static Hashtable ram = new Hashtable(65535);

    public BigInteger getData(BigInteger a) {
        try {
            if (a.intValue() > 2147483647) {
                System.out.println("Integer conversion error");
                System.exit(0);
            }
            return ((BigInteger) ram.get(a));
        } catch (ArrayIndexOutOfBoundsException aie) {
            return (new BigInteger("0"));
        } catch (Exception e) {
            e.printStackTrace();
            return (new BigInteger("0"));
        }
    }

    public void putData(BigInteger data, BigInteger loc) {
        ram.put(loc, data);
    }
}