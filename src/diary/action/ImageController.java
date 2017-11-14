package diary.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by MSI on 2017/11/12.
 */
@Controller
@RequestMapping("/image")
public class ImageController {
    private static int flag=0;
    @RequestMapping(params = "method=listen" ,method= RequestMethod.POST)
    public void listen(){

    }
    @RequestMapping(params = "method=stopListen",method=RequestMethod.POST)
    public void stop(){
        flag=1;
    }
}
