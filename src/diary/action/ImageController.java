package diary.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import diary.bean.Images;
import diary.dao.ImageDao;
import org.python.antlr.ast.Print;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
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
        String shell = "cmd /c start E:\\sota\\Diary\\FolderListener.exe";
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
}
