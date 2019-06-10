package com.jiahui.geneticAlgorithm;

import com.jiahui.qos.PolQos;

/**
 * @author ：Li Jiahui
 * @date ：Created in 2019/06/09 20:00
 * @description：测试用类
 * @modified By：
 * @version: 1.0
 */
public class GeneticAlgorithmTest extends GeneticAlgorithm{
    public static final int DIG_NUM=3;
    public static final int WS_NUM=;
    public static final int NUM = 1 << DIG_NUM*WS_NUM;

    public GeneticAlgorithmTest() {
        super(DIG_NUM*WS_NUM);
    }


    @Override
    public double changeX(Chromosome chro) {
        // TODO Auto-generated method stub
        return chro.getNum();
    }

    @Override
    public double caculateY(double x) {
        // TODO Auto-generated method stub
        String[] select=new String[WS_NUM];
        String str=binaryToDecimal((int)x);

        System.out.print(str+" ");

        int qos[] =new int[WS_NUM];

        for(int i=0;i<WS_NUM;i++){
            select[i]=str.substring(i*DIG_NUM,(i+1)*DIG_NUM);
        }
//
//        select[0]=str.substring(0,2);
//        select[1]=str.substring(2,4);
//        select[2]=str.substring(4,6);
//        select[3]=str.substring(6,8);

        for(int i=0;i<WS_NUM;i++){
            qos[i]=binaryToInt(select[i]);
        }


        return WS_NUM*5-PolQos.polAll(qos);
   //     return 300-x;
    }

    public String binaryToDecimal(int n){
        String str="";
        for(int i = DIG_NUM*WS_NUM-1;i >= 0; i--)
            str+=n >>> i & 1;
        return str;
    }


    public int  binaryToInt(String s) {

        int num = 0;
        for (int i=0;i<DIG_NUM;i++) {
            num <<= 1;
            if (s.charAt(i)=='1') {
                num += 1;
            }
        }
        return num;
}

    public static void main(String[] args) {
        PolQos.prepare();
        GeneticAlgorithmTest test = new GeneticAlgorithmTest();
        test.caculte();
    }

}
