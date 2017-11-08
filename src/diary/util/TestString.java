package diary.util;

/**
 * Created by MSI on 2017/11/8.
 */
public class TestString {
    public static void main(String[] args){
        String test="videopics23.jpg|:|1";
        String[] temp=test.split("\\.");
        System.out.println(temp[0].split("videopics")[1]);

    }
}
