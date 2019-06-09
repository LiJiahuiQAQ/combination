package com.jiahui.qos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: combination
 * @description: 用来计算一共有多少个qos数量
 * @author: Li Jiahui
 * @create: 2019-05-25 14:08
 **/
public class CountQos {

    public static void main(String args[]){

        List<String> list=new ArrayList<String>();
        Scanner scanner=new Scanner(System.in);

        while(true){
            String s=scanner.next();
            if(s.equals("#")){
                break;
            }
            list.add(s);
        }

        System.out.println(list.size());


    }


}
