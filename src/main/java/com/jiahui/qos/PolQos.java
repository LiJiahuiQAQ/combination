package com.jiahui.qos;

import com.jiahui.geneticAlgorithm.GeneticAlgorithmTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: combination
 * @description: 用于聚合qos
 * @author: Li Jiahui
 * @create: 2019-06-03 11:58
 **/
public class PolQos {

    public static Qos qos [][];

    public static int serviceNum= GeneticAlgorithmTest.WS_NUM; //聚合数

    public static int combinateNum=GeneticAlgorithmTest.DIG_NUM; //每个微服务拥有的备份

    public static float wAv=1;

    public static float wTh=1000;

    public static float wCo=1;

    public static float wRt=1;

    public static void main(String args[]){
        prepare();
        List<Float> list=new ArrayList<Float>();

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                for(int k=0;k<4;k++){
                    for(int m=0;m<4;m++){
                        System.out.println(20-polAll(new int[]{i, j, k, m}));
                        list.add( (20-polAll(new int[]{i, j, k, m})));
                    }
                }
            }
            System.out.println();
        }


        float max=list.get(0);
        for(int i=0;i<list.size();i++){
            if(list.get(i)>max){
                max=list.get(i);
            }
        }

        System.out.println("max: "+max);

    }
    public static void prepare(){
        NormalizeQos.normalizeAll();
        qos=new Qos[combinateNum][serviceNum];

        for(int i=0;i<combinateNum;i++){
            for(int j=0;j<serviceNum;j++){
                qos[i][j]=new Qos();
            }
        }
        matrixToQos();

    }

    public static void matrixToQos(){
        for(int i=0;i<combinateNum;i++){
            for(int j=0;j<serviceNum;j++){
                qos[i][j].setAv(ReadQos.av[i][j]);
                qos[i][j].setCo(ReadQos.co[i][j]);
                qos[i][j].setRt(ReadQos.rt[i][j]);
                qos[i][j].setTh(ReadQos.th[i][j]);
            }
        }
    }

    public static float polAv(Qos [] qoslist){
        float qosAv=1;
        for(int i=0;i<qoslist.length;i++){
            qosAv*=qoslist[i].getAv();
        }
        return qosAv;
    }

    public static float polAll(int[] intlist){
        float qosNum=0;
        Qos[] qoslist=new Qos[4];

        qoslist[0]=qos[intlist[0]][0];
        qoslist[1]=qos[intlist[1]][1];
        qoslist[2]=qos[intlist[2]][2];
        qoslist[3]=qos[intlist[3]][3];



        qosNum=polAv(qoslist)*wAv+polCo(qoslist)*wCo+polRt(qoslist)*wRt+polTh(qoslist)*wTh;
        return qosNum;
    }

    public static float polTh(Qos [] qoslist){
        float qosTh=qoslist[0].getTh();

        for(int i=0;i<qoslist.length;i++){
            qosTh=qosTh<qoslist[i].getTh()?qosTh:qoslist[i].getTh();
        }

        return qosTh;

    }

    public static float polRt(Qos [] qoslist){
        float qosRt=0;

        for(int i=0;i<qoslist.length;i++){
            qosRt+=qoslist[i].getRt();
        }

        return qosRt;

    }

    public static float polCo(Qos [] qoslist){
        float qosCo=0;

        for(int i=0;i<qoslist.length;i++){
            qosCo+=qoslist[i].getCo();
        }

        return qosCo;
    }
}
