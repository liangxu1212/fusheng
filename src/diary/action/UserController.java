package diary.action;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import diary.bean.User;
import diary.dao.UserDao;
import diary.util.Encoder;
import diary.util.MyJSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private UserDao userDao;
    @Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(params = "method=login", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        System.out.println("password: " + password + "account:" + account);
        User user = userDao.login(account, password);
        System.out.println(user);
        MyJSON myJSON = new MyJSON();
        PrintWriter writer = response.getWriter();
        if (user == null) {
            myJSON.setStatus("400");
            writer.println(myJSON.toJSONString());
            writer.flush();
            return;
        }

        request.getSession().setAttribute("user", user);
        myJSON.setStatus("200");
        myJSON.putData("account", user.getAccount());
        writer.println(myJSON.toJSONString());
        writer.flush();
    }


    private void sendBadRequest(MyJSON myJSON,PrintWriter writer){
        myJSON.setStatus("400");
        writer.println(myJSON.toJSONString());
        writer.flush();
    }
}