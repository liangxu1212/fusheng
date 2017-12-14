package diary.action;

import com.alibaba.fastjson.JSONObject;
import diary.util.UltrasonicUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by MSI on 2017/11/26.
 */
@Controller
@MultipartConfig
@RequestMapping("/image")
public class UploadController {
    @RequestMapping(value="upload", method = RequestMethod.POST)
    public void upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter writer=response.getWriter();
        JSONObject jsonObject=new JSONObject();
        System.out.println(request.getParameter("filename"));
        String filename=new File(request.getParameter("filename")).getName();
        System.out.println(filename);
        if(filename.endsWith(".dcm")){
            File f=new File("c:/apache-tomcat-7.0.75-sota/webapps/frontend/imageStorage/"+filename);
            file.transferTo(f);
            jsonObject.put("status",200);
            jsonObject.put("path",f.getAbsolutePath());
            writer.write(jsonObject.toJSONString());
            writer.flush();
        }else{
            jsonObject.put("status",400);
            writer.write(jsonObject.toJSONString());
            writer.flush();
        }




    }
}