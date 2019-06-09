package com.jiahui.qos;

import com.jiahui.tools.FileUtils;

import java.awt.peer.SystemTrayPeer;

/**
 * @program: combination
 * @description: 对qos的归一化
 * @author: Li Jiahui
 * @create: 2019-05-25 14:32
 **/
public class NormalizeQos {

    public static void main(String args[]){
        normalizeAll();

    }

    public static void normalizeAll(){
        ReadQos.coLine();
        ReadQos.rtLine();
        ReadQos.thLine();
        ReadQos.avLine();
        normalizeType1(ReadQos.av);
        normalizeType1(ReadQos.th);
        normalizeType2(ReadQos.co);
        normalizeType2(ReadQos.rt);

    }

    public static float maxOne(float matrix[][]){
        float max=matrix[0][0];

        for(int i=0;i<339;i++){
            for(int j=0;j<5825;j++){
                if(matrix[i][j]>max){
                    max=matrix[i][j];
                }
            }
        }
        return max;
    }

    public static float minOne(float matrix[][]){
        float min=matrix[0][0];

        for(int i=0;i<339;i++){
            for (int j=0;j<5825;j++){
                if(matrix[i][j]<min){
                    min=matrix[i][j];
                }
            }
        }

        return min;
    }

    public static void normalizeType1(float matrix[][]){
        float max=maxOne(matrix);
        float min=minOne(matrix);

        if(max==min){
            for(int i=0;i<339;i++){
                for(int j=0;j<5825;j++){
                    matrix[i][j]=0;
                }
            }
        }else {
            for(int i=0;i<339;i++){
                for(int j=0;j<5825;j++){
                    matrix[i][j]=(matrix[i][j]-min)/(max-min);
                }
            }
        }

    }

    public static void normalizeType2(float matrix[][]){
        float max=maxOne(matrix);
        float min=minOne(matrix);

        if(min==max){
            for(int i=0;i<339;i++){
                for(int j=0;j<5825;j++){
                    matrix[i][j]=1;
                }
            }
        }else {
            for(int i=0;i<339;i++){
                for(int j=0;j<5825;j++){
                    matrix[i][j]=(max-matrix[i][j])/(max-min);
                }
            }
        }


    }

}
