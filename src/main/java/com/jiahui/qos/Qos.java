package com.jiahui.qos;

/**
 * @program: combination
 * @description: Qos Bean
 * @author: Li Jiahui
 * @create: 2019-05-25 14:34
 **/
public class Qos {

    private float rt;//反应时间
    private float co;//价格
    private float th;//吞吐量
    private float av;//可用性

    public float getRt() {
        return rt;
    }

    public void setRt(float rt) {
        this.rt = rt;
    }

    public float getCo() {
        return co;
    }

    public void setCo(float co) {
        this.co = co;
    }

    public float getTh() {
        return th;
    }

    public void setTh(float th) {
        this.th = th;
    }

    public float getAv() {
        return av;
    }

    public void setAv(float av) {
        this.av = av;
    }
}
