package com.cyclerunner.nevermore.IndexController;

import com.cyclerunner.nevermore.mapper.QuestionMapper;
import com.cyclerunner.nevermore.model.Question;
import com.cyclerunner.nevermore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @RequestMapping(method = RequestMethod.GET)
    public String publish() {
        System.out.println("---- get ----");
        return "publish";
    }

    @PostMapping()
    public String doPublish(HttpServletRequest request, Model model,
                            @RequestParam("title") String title,
                            @RequestParam("desc") String desc,
                            @RequestParam("tag") String tag) {
        System.out.println("---- post ----");
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user.toString());
        if (user == null) {
            model.addAttribute("error", "请登录");
            return "publish";
        }

        Question question = new Question();
        question.setTag(tag);
        question.setTitle(title);
        question.setDesc(desc);
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modify(System.currentTimeMillis());
        question.setCreator(user.getId());
        System.out.println(question.toString());
        questionMapper.addQuestion(question);
        return "redirect:/";
    }
}
