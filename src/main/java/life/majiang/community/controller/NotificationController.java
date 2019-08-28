package life.majiang.community.controller;

import life.majiang.community.dto.NotificationDTO;
import life.majiang.community.enums.NotificationTypeEnum;
import life.majiang.community.model.User;
import life.majiang.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/28 17:10
 */
@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request, @PathVariable("id")Long id){

        User user = (User) request.getSession().getAttribute("user");
        NotificationDTO notificationDTO = notificationService.read(id, user);

        if(NotificationTypeEnum.REPLY_COMMENT.getName() == notificationDTO.getType() ||
            NotificationTypeEnum.REPLY_QUESTION.getName() == notificationDTO.getType()){
            return "redirect:/question/" + notificationDTO.getOuterId();
        }
        return "question";
    }

}
