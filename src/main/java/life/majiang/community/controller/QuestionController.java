package life.majiang.community.controller;

import life.majiang.community.dto.CommentBackDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.enums.CommentTypeEnum;
import life.majiang.community.mapper.QuestionExtMapper;
import life.majiang.community.service.CommentService;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/22 21:29
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Long id, Model model){

        QuestionDTO questionDTO = questionService.getById(id);
        List<CommentBackDTO> comments =  commentService.listByParentIdAndType(id, CommentTypeEnum.QUESTION);
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        questionService.incView(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
}
