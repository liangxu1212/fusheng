package diary.util;


import MY_ULTRASONIC.Ultrasonic;
import com.mathworks.toolbox.javabuilder.*;

/**
 * Created by MSI on 2017/11/4.
 */
public class TestPython {
    public static void main(String[] args) throws Exception{
// TODO Auto-generated method stub
        diary.util.Ultrasonic.ultrasonic("d:/fusheng/GUI/PICimg/002","164",
                "203","203","250","508","472","573","519",
                "518","620","94","133");
//        MWNumericArray a = null;MWNumericArray m = null;MWNumericArray n = null;MWNumericArray p = null;MWNumericArray q = null;
//        MWNumericArray b = null;
//        MWCharArray c = null;
//        Object[] result= null;
//        Ultrasonic ultrasonic=null;
//        try{
//            double[] aaa={164,203,203,250};
//            double[] bbb={508,472,573,519};
//
//            a = new MWNumericArray(aaa,MWClassID.DOUBLE);
//            b = new MWNumericArray(bbb, MWClassID.DOUBLE);
//            c = new MWCharArray("d:\\fusheng\\GUI\\PICimg\\002");
//
//            m= new MWNumericArray(Double.valueOf("518"),MWClassID.DOUBLE);
//            n=new MWNumericArray(Double.valueOf("620"),MWClassID.DOUBLE);
//            p=new MWNumericArray(Double.valueOf("94"),MWClassID.DOUBLE);
//            q=new MWNumericArray(Double.valueOf("133"),MWClassID.DOUBLE);
//            c=new MWCharArray("d:\\fusheng\\GUI\\PICimg\\002");
//            ultrasonic=new Ultrasonic();
//            result=ultrasonic.MY_ULTRASONIC(3,c,a,b,m,n,p,q);
//            System.out.println(result[0]);
//            System.out.println(result[1]);
//            System.out.println(result[2]);
//
//        }catch (Exception e) {
//// TODO: handle exception
//            System.out.println("Exception! "+e.toString());
//            e.printStackTrace();
//        }finally{
////free native resoures
//            MWArray.disposeArray(a);
//            MWArray.disposeArray(b);
//            MWArray.disposeArray(c);
//            MWArray.disposeArray(m);
//            MWArray.disposeArray(n);
//            MWArray.disposeArray(p);
//            MWArray.disposeArray(q);
//
//            MWArray.disposeArray(result);
//            ultrasonic.dispose();
//        }

    }
    public static void test(){
        MWNumericArray a = null;MWNumericArray m = null;MWNumericArray n = null;MWNumericArray p = null;MWNumericArray q = null;
        MWNumericArray b = null;
        MWCharArray c = null;
        Object[] result= null;
        Ultrasonic ultrasonic=null;
        try{
            double[] aaa={164,203,203,250};
            double[] bbb={508,472,573,519};

            a = new MWNumericArray(aaa,MWClassID.DOUBLE);
            b = new MWNumericArray(bbb, MWClassID.DOUBLE);
            c = new MWCharArray("d:\\fusheng\\GUI\\PICimg\\002");

            m= new MWNumericArray(Double.valueOf("518"),MWClassID.DOUBLE);
            n=new MWNumericArray(Double.valueOf("620"),MWClassID.DOUBLE);
            p=new MWNumericArray(Double.valueOf("94"),MWClassID.DOUBLE);
            q=new MWNumericArray(Double.valueOf("133"),MWClassID.DOUBLE);
            c=new MWCharArray("d:\\fusheng\\GUI\\PICimg\\002");
            ultrasonic=new Ultrasonic();
            result=ultrasonic.MY_ULTRASONIC(3,c,a,b,m,n,p,q);
            System.out.println(result[0]);
            System.out.println(result[1]);
            System.out.println(result[2]);

        }catch (Exception e) {
// TODO: handle exception
            System.out.println("Exception! "+e.toString());
            e.printStackTrace();
        }finally{
//free native resoures
            MWArray.disposeArray(a);
            MWArray.disposeArray(b);
            MWArray.disposeArray(c);
            MWArray.disposeArray(m);
            MWArray.disposeArray(n);
            MWArray.disposeArray(p);
            MWArray.disposeArray(q);

            MWArray.disposeArray(result);
            ultrasonic.dispose();
        }
    }
}//main

