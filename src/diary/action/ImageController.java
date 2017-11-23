package diary.action;

import MY_ULTRASONIC.Ultrasonic;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import diary.bean.Images;
import diary.dao.ImageDao;
import diary.util.UltrasonicUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by MSI on 2017/11/12.
 */
@Controller
@RequestMapping("/image")
public class ImageController {
    private ImageDao imageDao;
    @Resource
    public void setImageDao(ImageDao imageDao){this.imageDao=imageDao;}


    @RequestMapping(value="/clean",method = RequestMethod.POST)
    public void clean (HttpServletRequest request,HttpServletResponse response) throws IOException {
        imageDao.deleteAll();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",200);
        PrintWriter writer=response.getWriter();
        writer.write(jsonObject.toJSONString());
        writer.flush();
    }
    @RequestMapping(value="/addImage",method=RequestMethod.POST)
    public void addImage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String imageUrl=request.getParameter("image_url");
        String tumourUrl=request.getParameter("tumour_url");
        String fatUrl=request.getParameter("fat_url");
        String ultrasonicResult=request.getParameter("ultrasonic_result");
        String tumourResult=request.getParameter("tumour_result");
        String theriomaResult=request.getParameter("therioma_result");
        PrintWriter writer=response.getWriter();
        JSONObject jsonObject=new JSONObject();
        Images origin=imageDao.findImageByUrl(imageUrl);
        if(origin!=null||imageUrl==null||tumourUrl==null||fatUrl==null||ultrasonicResult==null||tumourResult==null||theriomaResult==null){
            jsonObject.put("status",400);
            writer.write(jsonObject.toJSONString());
            writer.flush();
            return;
        }
        Images images=new Images(imageUrl,tumourUrl,fatUrl,ultrasonicResult,tumourResult,theriomaResult);
        imageDao.addImages(images);
        jsonObject.put("status",200);
        writer.write(jsonObject.toJSONString());
        writer.flush();
    }
    @RequestMapping(value="/updateImage",method=RequestMethod.POST)
    public void updateImage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String imageUrl=request.getParameter("image_url");
        String tumourUrl=request.getParameter("tumour_url");
        String fatUrl=request.getParameter("fat_url");
        String ultrasonicResult=request.getParameter("ultrasonic_result");
        String tumourResult=request.getParameter("tumour_result");
        String theriomaResult=request.getParameter("therioma_result");
        PrintWriter writer=response.getWriter();
        JSONObject jsonObject=new JSONObject();
        Images origin=imageDao.findImageByUrl(imageUrl);
        if(imageUrl==null||tumourUrl==null||fatUrl==null||ultrasonicResult==null||tumourResult==null||theriomaResult==null){
            jsonObject.put("status",400);
            writer.write(jsonObject.toJSONString());
            writer.flush();
            return;
        }
        Images images=new Images(imageUrl,tumourUrl,fatUrl,ultrasonicResult,tumourResult,theriomaResult);
        if(origin==null){
            imageDao.updateImages(images);
            jsonObject.put("existed",0);
        }else{
            imageDao.updateImage(images);
            jsonObject.put("existed",1);
        }

        jsonObject.put("status",200);
        writer.write(jsonObject.toJSONString());
        writer.flush();
    }
    @RequestMapping(value="/queryImage",method = RequestMethod.POST)
    public void queryImage(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String imageUrl=request.getParameter("image_url");
        PrintWriter writer=response.getWriter();
        JSONObject jsonObject=new JSONObject();
        if(imageUrl==null){
            jsonObject.put("status",400);
            writer.write(jsonObject.toJSONString());
            writer.flush();
            return;
        }
        jsonObject.put("status",200);
        Images images=imageDao.findImageByUrl(imageUrl);
        if(images==null){
            jsonObject.put("existed",0);
            writer.write(jsonObject.toJSONString());
            writer.flush();
        }else{
            jsonObject.put("existed",1);
            jsonObject.put("data",images);
            writer.write(jsonObject.toJSONString());
            writer.flush();
        }

    }
    @RequestMapping(value="/listen",method= RequestMethod.POST)
    public void listen(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String shell = "cmd /c start D:\\apache-tomcat-7.0.75-sota\\webapps\\backend\\FolderListener.exe";
        try {
            Process p=Runtime.getRuntime().exec(shell);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",200);
        PrintWriter writer=response.getWriter();
        writer.write(jsonObject.toJSONString());
        writer.flush();
    }
    @RequestMapping(value="/stopListen",method=RequestMethod.POST)
    public void stop(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String shell = "cmd /c start taskkill /f /t /im FolderListener.exe";
        try {
            Process p=Runtime.getRuntime().exec(shell);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",200);
        PrintWriter writer=response.getWriter();
        writer.write(jsonObject.toJSONString());
        writer.flush();
    }
    @RequestMapping(value="/checkListen",method=RequestMethod.POST)
    public void checkListen(HttpServletRequest request,HttpServletResponse response) throws IOException {
        PrintWriter writer=response.getWriter();
        JSONObject jsonObject=new JSONObject();
        JSONArray array=UltrasonicUtil.dcm2jpg();
        jsonObject.put("status",200);
        jsonObject.put("data",array);
        writer.write(jsonObject.toJSONString());
        writer.flush();
    }
    @RequestMapping(value="/ultrasonic",method=RequestMethod.POST)
    public void ultrasonic(HttpServletRequest request,HttpServletResponse response) throws IOException {
        PrintWriter writer=response.getWriter();
        String imagePath=request.getParameter("image_path");
        String x1=request.getParameter("x1");String x2=request.getParameter("x2");
        String x3=request.getParameter("x3");String x4=request.getParameter("x4");
        String y1=request.getParameter("y1");String y2=request.getParameter("y2");
        String y3=request.getParameter("y3");String y4=request.getParameter("y4");
        String xf1=request.getParameter("xf1");String xf2=request.getParameter("xf2");
        String yf1=request.getParameter("yf1");String yf2=request.getParameter("yf2");
        JSONObject jsonObject=new JSONObject();
        if(imagePath==null||imagePath==""||x1==null||x1==""||x2==null||x2==""||x3==null||x3==""||x4==null||x4==""
                ||y1==null||y1==""||y2==null||y2==""||y3==null||y3==""||y4==null||y4==""
                ||xf1==null||xf1==""||xf2==null||xf2==""||yf1==null||yf1==""||yf2==null||yf2==""){
            jsonObject.put("status",400);
            writer.write((jsonObject.toJSONString()));
            writer.flush();
            return;
        }
        File f=new File(imagePath);
        String name=f.getName();
        jsonObject.put("status",200);
        Object[] result= UltrasonicUtil.ultrasonic(imagePath,name,x1,x2,x3,x4,y1,y2,y3,y4,xf1,xf2,yf1,yf2);
        jsonObject.put("auto_seg",new File("").getAbsolutePath()+"\\finalimg\\seg-"+name+".jpg");
        jsonObject.put("fat",new File("").getAbsolutePath()+"\\finalimg\\fat-"+name+".jpg");
        jsonObject.put("ultrasonic_result",result[0].toString());
        jsonObject.put("tumour_result",result[1].toString());
        jsonObject.put("therioma_result",result[2].toString());
        writer.write(jsonObject.toJSONString());
        writer.flush();
    }

}
