package life.majiang.community.controller;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/16 22:10
 */
@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String submit(@RequestParam("title")String title,
                         @RequestParam("description")String description,
                         @RequestParam("tag")String tag,
                         @RequestParam(name = "id", required = false)Integer id,
                         HttpServletRequest request, Model model){

        model.addAttribute("title", title);
        model.addAttribute("tag", tag);
        model.addAttribute("description", description);

        if (title==null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description==null || description == "") {
            model.addAttribute("error", "正文不能为空");
            return "publish";
        }
        if (tag==null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = (User)request.getSession().getAttribute("user");

        if (user==null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id")Integer id, Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("id", question.getId());
        return "publish";
    }
}
