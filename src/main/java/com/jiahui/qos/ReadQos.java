package com.jiahui.qos;

import java.io.*;
import java.util.ArrayList;

/**
 * @program: combination
 * @description: 读取在resource中的qos
 * @author: Li Jiahui
 * @create: 2019-05-25 14:55
 **/
public class ReadQos {

   static float [][] rt =new float[339][5825];
   static float [][] th =new float[339][5825];
   static float [][] av =new float[339][5825];
   static float [][] co =new float[339][5825];

   static String rtName="E:\\Dataset\\rtMatrix.txt";
   static String thName="E:\\Dataset\\tpMatrix.txt";
   static String ulName="E:\\Dataset\\userlist.txt";
   static String wlName="E:\\Dataset\\wslist.txt";

   public static void coLine(){

      float [][] wlLaLong=new float[2][5825];
      float [][] ulLaLong=new float[2][339];

      File filename = new File(wlName); // 要读取以上路径的input。txt文件
      InputStreamReader reader; // 建立一个输入流对象reader

      {
         try {
            reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line ="";
            //第一二行为行标和分隔符
            br.readLine();
            br.readLine();

            int num=0;

            while (true) {
               line= br.readLine(); // 一次读入一行数据

//               System.out.println(line);

               if(line==null){
                  break;
               }
               String [] str=line.trim().split("\t");
               wlLaLong[0][num]=str[7].trim().equals("null")?0:Float.parseFloat(str[7].trim());
               wlLaLong[1][num]=str[8].trim().equals("null")?0:Float.parseFloat(str[8].trim());
               num++;

            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }






      filename = new File(ulName); // 要读取以上路径的input。txt文件

      {
         try {
            reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line ="";
            //第一二行为行标和分隔符
            br.readLine();
            br.readLine();

            int num=0;

            while (true) {
               line= br.readLine(); // 一次读入一行数据

//               System.out.println(line);

               if(line==null){
                  break;
               }
               String [] str=line.trim().split("\t");
               ulLaLong[0][num]=str[5].trim().equals("null")?0:Float.parseFloat(str[5].trim());
               ulLaLong[1][num]=str[6].trim().equals("null")?0:Float.parseFloat(str[6].trim());
               num++;

            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

//      for(int i=0;i<339;i++){
//          System.out.println(ulLaLong[0][i]+" "+i+" "+ulLaLong[1][i]);
//      }
//
//      for (int j=0;j<5825;j++){
//         System.out.println(wlLaLong[0][j]+"  "+wlLaLong[1][j]);
//      }

      for(int i=0;i<339;i++){
         for(int j=0;j<5825;j++) {
            co[i][j] = (float)Math.sqrt(Math.pow(ulLaLong[0][i] - wlLaLong[0][j], 2) + Math.pow(ulLaLong[1][i] - wlLaLong[1][j],2));
         }
      }



   }

   public static void avLine(){
      thLine();
      rtLine();
      for(int i=0;i<339;i++){
         float count=0;
         for(int j=0;j<5825;j++){
            count+=(rt[i][j]==-1?1:0 +th[i][j]==-1?1:0);
         }
         av[i][0]=1-(count/11650);
      }

      for(int i=0;i<339;i++){
         for(int j=1;j<5825;j++){
           av[i][j]=av[i][0];
         }
      }


   }



   public static void thLine(){


      File filename = new File(thName); // 要读取以上路径的input。txt文件
      InputStreamReader reader; // 建立一个输入流对象reader

      {
         try {
            reader = new InputStreamReader(new FileInputStream(filename));


            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line ="";
            int i=0;
            while (true) {
               line= br.readLine(); // 一次读入一行数据
               if(line==null){
                  break;
               }
               stringToTp(line,i);
               i++;

            }
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }


   }

   public static void stringToTp(String str,int line){

      float[] num=new float[5825];
      String [] strings=str.trim().split("\\s+");
      for(int i=0;i<strings.length;i++){
         num[i]=Float.parseFloat(strings[i].trim());
      }
      th[line]=num;

//      for(int i=0;i<rt[line].length;i++){
//         System.out.print(rt[line][i]+" ");
//      }
//      System.out.println();
   }

   public static void rtLine(){


      File filename = new File(rtName); // 要读取以上路径的input。txt文件
      InputStreamReader reader; // 建立一个输入流对象reader

      String rtLine = "";

      {
         try {
            reader = new InputStreamReader(new FileInputStream(filename));


            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line ="";
            int i=0;
            while (true) {
               line= br.readLine(); // 一次读入一行数据
               if(line==null){
                  break;
               }
               stringToRt(line,i);
               i++;

            }
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   public static void stringToRt(String str,int line){

      float[] num=new float[5825];
      String [] strings=str.trim().split("\\s+");
      for(int i=0;i<strings.length;i++){
         num[i]=Float.parseFloat(strings[i].trim());
      }
      rt[line]=num;

//      for(int i=0;i<rt[line].length;i++){
//         System.out.print(rt[line][i]+" ");
//      }
//      System.out.println();
   }


   public static void main(String args[]){

   }

}
