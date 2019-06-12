package com.jiahui.tools;

import static com.jiahui.geneticAlgorithm.GeneticAlgorithmTest.DIG_NUM;
import static com.jiahui.geneticAlgorithm.GeneticAlgorithmTest.WS_NUM;

/**
 * @author ：Li Jiahui
 * @date ：Created in 2019/06/10 15:39
 * @description：就随便扯扯犊子的东西
 * @modified By：
 * @version:
 */
public class Test {
    public String binaryToDecimal(int n){
        String str="";
        for(int i = 7;i >= 0; i--)
            str+=n >>> i & 1;
        return str;
    }

    public int  binaryToInt(String s) {

        int num = 0;
        for (int i=0;i<2;i++) {
            num <<= 1;
            if (s.charAt(i)=='1') {
                num += 1;
            }
        }
        return num;
    }

    public static  float 狗东西(float um){

        return (float) (4*      (um/Math.pow(2,DIG_NUM*WS_NUM))   *           (1-(um/Math.pow(2,DIG_NUM*WS_NUM)))       *             Math.pow(2,DIG_NUM*WS_NUM));
    }

    public static void main(String args[]){

        System.out.println(Math.random());




    }


}
