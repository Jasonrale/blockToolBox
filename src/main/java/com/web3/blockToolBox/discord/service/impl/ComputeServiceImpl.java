package com.web3.blockToolBox.discord.service.impl;

import com.web3.blockToolBox.discord.service.ComputeService;
import com.web3.blockToolBox.utils.MathUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

@Service
public class ComputeServiceImpl implements ComputeService {

    /**
     * 计算相对于市价的损失（无常损失）
     *
     * @param ratioX
     * @param ratioY
     * @return
     */
    public BigDecimal computeLoss(BigDecimal ratioX, BigDecimal ratioY) {
        BigDecimal one = BigDecimal.valueOf(1);
        BigDecimal two = BigDecimal.valueOf(2);

        return two.multiply(MathUtil.sqrt((ratioX.add(one)).multiply(ratioY.add(one))))
                .divide(two.add(ratioX).add(ratioY), 4, HALF_UP).subtract(one);
    }

    /**
     * 计算无常损失相对于原价的比率
     *
     * @param ratioX
     * @param ratioY
     * @return
     */
    public BigDecimal computeLossForOriginal(BigDecimal ratioX, BigDecimal ratioY) {
        BigDecimal one = BigDecimal.valueOf(1);
        BigDecimal two = BigDecimal.valueOf(2);

        return MathUtil.sqrt((ratioX.add(one)).multiply(ratioY.add(one))).subtract(ratioX.add(ratioY).divide(two, HALF_UP)).subtract(one).setScale(4, HALF_UP);
    }

    /**
     * 计算扣除无常损失后相对于原价的收益
     *
     * @param ratioX
     * @param ratioY
     * @return
     */
    public BigDecimal computeProfit(BigDecimal ratioX, BigDecimal ratioY) {
        BigDecimal one = BigDecimal.valueOf(1);

        return MathUtil.sqrt((ratioX.add(one)).multiply(ratioY.add(one))).subtract(one).setScale(4, HALF_UP);
    }

    /**
     * 计算新的市值
     *
     * @param capital
     * @param ratioX
     * @param ratioY
     * @return
     */
    @Override
    public BigDecimal computeNewMarketValue(BigDecimal capital, BigDecimal ratioX, BigDecimal ratioY) {
        return computeProfit(ratioX, ratioY).multiply(capital).setScale(4, HALF_UP);
    }

    public static void main(String[] args) {
        ComputeServiceImpl service = new ComputeServiceImpl();
        System.out.println(service.computeProfit(BigDecimal.valueOf(-0.9), BigDecimal.valueOf(0)));
    }


}
