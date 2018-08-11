package com.github.h2002044.lc2;

import java.math.BigInteger;
import java.util.HashMap;

public class Mnemonics {

    private static final Mnemonics mnemonics = new Mnemonics();

    private HashMap<BigInteger, String> map = new HashMap();

    public static Mnemonics getMnemonics(){
        return mnemonics;
    }

    public void put(BigInteger key, String value){
        map.put(key, value);
    }

    public String get(BigInteger key){
        return map.getOrDefault(key, "");
    }


}
