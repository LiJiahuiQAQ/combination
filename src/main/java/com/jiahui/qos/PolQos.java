package com.jiahui.qos;

/**
 * @program: combination
 * @description: 用于聚合qos
 * @author: Li Jiahui
 * @create: 2019-06-03 11:58
 **/
public class PolQos {

    public static Qos qos [][];

    public static int serviceNum=4; //聚合数

    public static int combinateNum=4; //每个微服务拥有的备份

    public static float wAv=1;

    public static float wTh=1000;

    public static float wCo=1;

    public static float wRt=1;

    public static void main(String args[]){
        prepare();
        System.out.println(polAll(qos[0]));
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

    public static float polAll(Qos[] qoslist){
        float qos=0;
        System.out.println("av:  "+polAv(qoslist)*wAv);
        System.out.println("co:   "+polCo(qoslist)*wCo);
        System.out.println("rt:   "+polRt(qoslist)*wRt);
        System.out.println("th:   "+polTh(qoslist)*wTh);

        qos=polAv(qoslist)*wAv+polCo(qoslist)*wCo+polRt(qoslist)*wRt+polTh(qoslist)*wTh;
        return qos;
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
