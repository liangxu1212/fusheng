package diary.util;

import com.mathworks.toolbox.javabuilder.MWCharArray;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

/**
 * Created by MSI on 2017/11/16.
 */
public class Ultrasonic {
    public static Object[] ultrasonic(String imagePath,String x1,String x2,String x3,String x4,String y1,String y2,String y3,String y4,
                                      String xf1,String xf2,String yf1,String yf2){
        MWNumericArray a = null;MWNumericArray m = null;MWNumericArray n = null;MWNumericArray p = null;MWNumericArray q = null;
        MWNumericArray b = null;
        MWCharArray c = null;
        Object[] result= null;
        MY_ULTRASONIC.Ultrasonic ultrasonic=null;
        try{
            double[] aaa={Double.valueOf(x1),Double.valueOf(x2),Double.valueOf(x3),Double.valueOf(x4)};
            double[] bbb={Double.valueOf(y1),Double.valueOf(y2),Double.valueOf(y3),Double.valueOf(y4)};

            a = new MWNumericArray(aaa, MWClassID.DOUBLE);
            b = new MWNumericArray(bbb, MWClassID.DOUBLE);
            c = new MWCharArray(imagePath);
            m= new MWNumericArray(Double.valueOf(xf1),MWClassID.DOUBLE);
            n=new MWNumericArray(Double.valueOf(xf2),MWClassID.DOUBLE);
            p=new MWNumericArray(Double.valueOf(yf1),MWClassID.DOUBLE);
            q=new MWNumericArray(Double.valueOf(yf2),MWClassID.DOUBLE);
            ultrasonic=new MY_ULTRASONIC.Ultrasonic();
            result=ultrasonic.MY_ULTRASONIC(3,c,a,b,m,n,p,q);
            System.out.println(result[0]);
            System.out.println(result[1]);
            System.out.println(result[2]);

        }catch (Exception e) {
// TODO: handle exception
            System.out.println("Exception! "+e.toString());
            e.printStackTrace();
        }
        return result;
    }
}
