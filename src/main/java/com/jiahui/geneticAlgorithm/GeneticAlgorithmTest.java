package com.jiahui.geneticAlgorithm;

/**
 * @author ：Li Jiahui
 * @date ：Created in 2019/06/09 20:00
 * @description：测试用类
 * @modified By：
 * @version: 1.0
 */
public class GeneticAlgorithmTest extends GeneticAlgorithm{
    public static final int NUM = 1 << 24;

    public GeneticAlgorithmTest() {
        super(24);
    }

    @Override
    public double changeX(Chromosome chro) {
        // TODO Auto-generated method stub
        return ((1.0 * chro.getNum() / NUM) * 100) + 6;
    }

    @Override
    public double caculateY(double x) {
        // TODO Auto-generated method stub
        return 100 - Math.log(x);
    }

    public static void main(String[] args) {
        GeneticAlgorithmTest test = new GeneticAlgorithmTest();
        test.caculte();
    }

}
