package com.web3.blockToolBox.discord.service;

import java.math.BigDecimal;

public interface ComputeService {

    BigDecimal computeLoss(BigDecimal ratioX, BigDecimal ratioY);

    BigDecimal computeLossForOriginal(BigDecimal ratioX, BigDecimal ratioY);

    BigDecimal computeProfit(BigDecimal ratioX, BigDecimal ratioY);

    BigDecimal computeNewMarketValue(BigDecimal capital, BigDecimal ratioX, BigDecimal ratioY);
}
