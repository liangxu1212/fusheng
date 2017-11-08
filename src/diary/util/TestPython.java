package diary.util;

import org.python.core.*;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by MSI on 2017/11/4.
 */
public class TestPython {
    public static void main(String args[])
    {
        String shell = "cmd /c start python d:\\fusheng\\test\\deploy_interface.py d:\\fusheng\\pics";//需要执行的命令
//        String arg="d:\\fusheng\\pics";
//        String cmd="python";
//        String file="d:\\fusheng\\test\\deploy_interface.py";
//        String[] shell=new String[]{
//                cmd,file,arg
//        };
        try {
            File f=new File("result.txt");
            f.delete();
            f.createNewFile();
            Process p = Runtime.getRuntime().exec(shell);//调用控制台执行shell
        } catch (Exception e) {
        }
    }
}//main

