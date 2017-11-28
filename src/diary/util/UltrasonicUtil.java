package diary.util;

import FAT.Fat;
import MY_MAIN_LFPA.AutoSeg;
import com.alibaba.fastjson.JSONArray;
import com.mathworks.toolbox.javabuilder.MWCharArray;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import dicom2jpg.Dcm2jpg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by MSI on 2017/11/16.
 */
public class UltrasonicUtil {
    public static Object[] ultrasonic(String imagePath,String imgName,String x1,String x2,String x3,String x4,String y1,String y2,String y3,String y4,
                                      String xf1,String xf2,String yf1,String yf2){
        MWNumericArray a = null;MWNumericArray m = null;MWNumericArray n = null;MWNumericArray p = null;MWNumericArray q = null;
        MWNumericArray b = null;
        MWCharArray c = null;MWCharArray c2 = null;
        Object[] result= null;
        MY_ULTRASONIC.Ultrasonic ultrasonic=null;
        try{
            double[] aaa={Double.valueOf(x1),Double.valueOf(x2),Double.valueOf(x3),Double.valueOf(x4)};
            double[] bbb={Double.valueOf(y1),Double.valueOf(y2),Double.valueOf(y3),Double.valueOf(y4)};

            a = new MWNumericArray(aaa, MWClassID.DOUBLE);
            b = new MWNumericArray(bbb, MWClassID.DOUBLE);
            c = new MWCharArray(imagePath);
            c2=new MWCharArray(imgName);
            m= new MWNumericArray(Double.valueOf(xf1),MWClassID.DOUBLE);
            n=new MWNumericArray(Double.valueOf(xf2),MWClassID.DOUBLE);
            p=new MWNumericArray(Double.valueOf(yf1),MWClassID.DOUBLE);
            q=new MWNumericArray(Double.valueOf(yf2),MWClassID.DOUBLE);
            ultrasonic=new MY_ULTRASONIC.Ultrasonic();
            result=ultrasonic.MY_ULTRASONIC(3,c,c2,a,b,m,n,p,q);
            System.out.println(result[0]);
            System.out.println(result[1]);
            System.out.println(result[2]);
            File origin=new File("D:\\apache-tomcat-7.0.75-sota\\bin\\finalimg");
            File move=new File("D:\\apache-tomcat-7.0.75-sota\\webapps\\frontend\\imageResults");
            File[] files=origin.listFiles();
            for(File img:files){
                moveFile(img.getAbsolutePath(),move.getAbsolutePath()+"\\"+img.getName());
            }
        }catch (Exception e) {
// TODO: handle exception
            System.out.println("Exception! "+e.toString());
            e.printStackTrace();
        }
        return result;
    }
    public static JSONArray dcm2jpg() {
        JSONArray array=new JSONArray();
        String storage="D:\\apache-tomcat-7.0.75-sota\\webapps\\frontend\\imageStorage";
        File path=new File(storage);
        File[] pics=path.listFiles();
        for(File img:pics){
            if(img.getName().endsWith(".jpg")){
                array.add(img.getName());
            }
        }
        return  array;
    }
    public static void newpic(String imagePath){
        JSONArray array=new JSONArray();
        Dcm2jpg dcm2jpg= null;
        try {
            dcm2jpg = new Dcm2jpg();
        } catch (MWException e) {
            e.printStackTrace();
        }
        File f2=new File(imagePath);
        MWCharArray c=new MWCharArray(f2.getParent());
        MWCharArray c2=new MWCharArray(f2.getName());
        try {
            dcm2jpg.dicom2jpg(c,c2);
        } catch (MWException e) {
            e.printStackTrace();
        }
    }
    /**
     *  删除文件
     *  @param  filePathAndName  String  文件路径及名称  如c:/fqf.txt
     *  @return  boolean
     */
    public static void  delFile(String  filePathAndName) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            java.io.File myDelFile = new java.io.File(filePath);
            myDelFile.delete();

        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();

        }
    }
        /**
         *  复制单个文件
         *  @param  oldPath  String  原文件路径  如：c:/fqf.txt
         *  @param  newPath  String  复制后路径  如：f:/fqf.txt
         *  @return  boolean
         */
    public static void  copyFile(String  oldPath,  String  newPath)  {
        try  {
//           int  bytesum  =  0;
            int  byteread  =  0;
            File  oldfile  =  new  File(oldPath);
            if  (oldfile.exists())  {  //文件存在时
                InputStream inStream  =  new FileInputStream(oldPath);  //读入原文件
                FileOutputStream fs  =  new  FileOutputStream(newPath);
                byte[]  buffer  =  new  byte[1444];
//               int  length;
                while  (  (byteread  =  inStream.read(buffer))  !=  -1)  {
//                   bytesum  +=  byteread;  //字节数  文件大小
//                   System.out.println(bytesum);
                    fs.write(buffer,  0,  byteread);
                }
                fs.close();
                inStream.close();
            }
        }
        catch  (Exception  e)  {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }
    /**
     *  移动文件到指定目录
     *  @param  oldPath  String  如：c:/fqf.txt
     *  @param  newPath  String  如：d:/fqf.txt
     */
    public static void  moveFile(String  oldPath,  String  newPath)  {
        copyFile(oldPath,  newPath);
        delFile(oldPath);

    }

    public static String autoseg(String imagePath, String name, String x1, String x2, String x3, String x4, String y1, String y2, String y3, String y4) {
        String str="";
        MWNumericArray a = null;
        MWNumericArray b = null;
        MWCharArray c = null;MWCharArray c2 = null;
        Object[] result= null;
        AutoSeg autoSeg=null;
        try{
            double[] aaa={Double.valueOf(x1),Double.valueOf(x2),Double.valueOf(x3),Double.valueOf(x4)};
            double[] bbb={Double.valueOf(y1),Double.valueOf(y2),Double.valueOf(y3),Double.valueOf(y4)};
            for(double d :aaa)System.out.println(d);
            System.out.println(imagePath);
            System.out.println(name);
            a = new MWNumericArray(aaa, MWClassID.DOUBLE);
            b = new MWNumericArray(bbb, MWClassID.DOUBLE);
            c = new MWCharArray(imagePath);
            c2=new MWCharArray(name);
            autoSeg=new AutoSeg();
            result=autoSeg.MY_MAIN_LFPA(4,c,c2,a,b);
            System.out.println(result[3]);
            File f=new File(result[3].toString());
            str="imageResults/"+f.getName();
            File origin=new File("D:\\apache-tomcat-7.0.75-sota\\bin\\finalimg");
            File move=new File("D:\\apache-tomcat-7.0.75-sota\\webapps\\frontend\\imageResults");
            File[] files=origin.listFiles();
            for(File img:files){
                moveFile(img.getAbsolutePath(),move.getAbsolutePath()+"\\"+img.getName());
            }
        }catch (Exception e) {
// TODO: handle exception
            System.out.println("Exception! "+e.toString());
            e.printStackTrace();
        }
        return str;
    }

    public static String fatseg(String imagePath, String name, String xf1, String xf2, String yf1, String yf2) {
        String str="";
        MWNumericArray xl = null;
        MWNumericArray xr = null;
        MWNumericArray yt = null;
        MWNumericArray yb = null;
        MWCharArray c = null;MWCharArray c2 = null;
        Object[] result= null;
        Fat fat=null;
        try{
            xl= new MWNumericArray(Double.valueOf(xf1),MWClassID.DOUBLE);
            xr=new MWNumericArray(Double.valueOf(xf2),MWClassID.DOUBLE);
            yt=new MWNumericArray(Double.valueOf(yf1),MWClassID.DOUBLE);
            yb=new MWNumericArray(Double.valueOf(yf2),MWClassID.DOUBLE);
            c = new MWCharArray(imagePath);
            c2=new MWCharArray(name);
            fat=new Fat();
            result=fat.FAT(1,c,c2,xl,xr,yt,yb);
            System.out.println(result[0]);
            File f=new File(result[0].toString());
            str="imageResults/"+f.getName();
            File origin=new File("D:\\apache-tomcat-7.0.75-sota\\bin\\finalimg");
            File move=new File("D:\\apache-tomcat-7.0.75-sota\\webapps\\frontend\\imageResults");
            File[] files=origin.listFiles();
            for(File img:files){
                moveFile(img.getAbsolutePath(),move.getAbsolutePath()+"\\"+img.getName());
            }
        }catch (Exception e) {
// TODO: handle exception
            System.out.println("Exception! "+e.toString());
            e.printStackTrace();
        }
        return str;
    }
}
