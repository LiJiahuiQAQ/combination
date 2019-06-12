package com.jiahui.figure;

import com.jiahui.tools.DButil;

import java.util.List;
import java.util.Map;

/**
 * @author ：Li Jiahui
 * @date ：Created in 2019/06/11 9:30
 * @description：画图画图，别吵吵，吵吵把火的嗷呜
 * @modified By：
 * @version:
 */
public class Figure {

    public static void main(String args[]){

        DButil db=new DButil();
        List<Map<String ,Object>> list=db.queryToList("select * from data");
        for(int i=0;i<list.size();i++){
            System.out.print((Float) list.get(i).get("y")-1250+",");
        }

    }

}
