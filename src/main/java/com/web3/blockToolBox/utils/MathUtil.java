package com.web3.blockToolBox.utils;

import java.math.BigDecimal;

public class MathUtil {
    public static BigDecimal sqrt(BigDecimal value) {
        BigDecimal x = BigDecimal.valueOf(Math.sqrt(value.doubleValue()));
        return x.add(BigDecimal.valueOf(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
    }
}
