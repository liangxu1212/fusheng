package diary.util;

import org.python.core.*;
import org.python.util.PythonInterpreter;

/**
 * Created by MSI on 2017/11/4.
 */
public class TestPython {
    public static void main(String args[])
    {

//        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.exec("days=('mod','Tue','Wed','Thu','Fri','Sat','Sun'); ");
//        interpreter.exec("print days[1];");
        PythonInterpreter interpreter = new PythonInterpreter();
        PySystemState sys = Py.getSystemState();
        sys.path.add("D:\\Python\\Python36\\Lib\\site-packages\\numpy");
        interpreter.exec("import sys");
        interpreter.exec("print(sys.path)");
        interpreter.execfile("./deploy_interface.py");
        PyFunction func = (PyFunction)interpreter.get("init",PyFunction.class);
        PyFunction func2 = (PyFunction)interpreter.get("processWCE",PyFunction.class);


        PyObject pyobj = func.__call__();
        PyObject pyobj2=func2.__call__(new PyString("d:/fusheng/test.jpg"));
        System.out.println("anwser = " + pyobj2.toString());

    }//main
}
