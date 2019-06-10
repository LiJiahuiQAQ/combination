package com.jiahui.tools;

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

    public static void main(String args[]){

        DButil db=new DButil();




    }


}
