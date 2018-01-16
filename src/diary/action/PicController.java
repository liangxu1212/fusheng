package diary.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import diary.util.MyJSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by MSI on 2017/11/8.
 */
@Controller
@RequestMapping(value="/pics")
public class PicController {
    @RequestMapping(params = "method=computePics", method = RequestMethod.POST)
    public void computePics(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String shell = "cmd /c start python /home/jindiwei/Changhai/deploy_interface.py /home/jindiwei/Changhai/pics";//需要执行的命令
        try {
            File flag=new File("/home/jindiwei/Changhai/flag.txt");
            if(flag.exists())flag.delete();
            File f=new File("/home/jindiwei/Changhai/result.txt");
            f.delete();
            f.createNewFile();
            Process p = Runtime.getRuntime().exec(shell);//调用控制台执行shell
        } catch (Exception e) {

        }
        JSONObject myJSON=new JSONObject();
        PrintWriter writer=response.getWriter();
        myJSON.put("message", "computing");
        writer.println(myJSON.toJSONString());
        writer.flush();
    }
    @RequestMapping(params = "method=getPicsResult",method=RequestMethod.POST)
    public void getPicsResult(HttpServletRequest request,HttpServletResponse response) throws IOException {
        JSONObject myJSON=new JSONObject();
        PrintWriter writer=response.getWriter();
        File flag=new File("/home/jindiwei/Changhai/flag.txt");
        if(flag.exists()){
            HashMap<Integer,Integer> results=new HashMap<>();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("result.txt")),
                        "UTF-8"));
                String lineTxt = null;
                int count=0;
                while ((lineTxt = br.readLine()) != null) {
                    int num,result;
                    count++;
                    String[] temp=lineTxt.split("\\|:\\|");
                    result=Integer.parseInt(temp[1]);
                    results.put(count,result);
                }
                br.close();
                JSONArray array=new JSONArray();
                for(int i=1;i<=results.size();i++){
                    array.add(results.get(i));
                }
                myJSON.put("results", array);
                myJSON.put("over","yes");
                writer.println(myJSON.toJSONString());
                writer.flush();

            } catch (Exception e) {
                System.err.println("read errors :" + e);
            }
        }else{
            myJSON.put("over","no");
            writer.println(myJSON.toJSONString());
            writer.flush();
        }
    }
    private void sendBadRequest(MyJSON myJSON, PrintWriter writer){
        myJSON.setStatus("400");
        writer.println(myJSON.toJSONString());
        writer.flush();
    }
}
