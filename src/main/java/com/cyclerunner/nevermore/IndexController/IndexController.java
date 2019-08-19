package com.cyclerunner.nevermore.IndexController;

import com.cyclerunner.nevermore.mapper.UserMapper;
import com.cyclerunner.nevermore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;

    //        @RequestMapping("/")
//    public String hello(@RequestParam(name = "name",required=false) String name, Model model) {
//        model.addAttribute("name",name);
//        return "index";
//    }
    @RequestMapping("/")
    public String hello(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    User user = userMapper.findByToken(cookie.getValue());
                    if (user != null) {
                        request.getSession().setAttribute("user",user);
                        break;
                    }
                }
            }
        }

        return "index";
    }
}
