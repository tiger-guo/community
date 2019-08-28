package life.majiang.community.service;

import life.majiang.community.dto.NotificationDTO;
import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.enums.NotificationStatusEnum;
import life.majiang.community.enums.NotificationTypeEnum;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.mapper.CommentMapper;
import life.majiang.community.mapper.NotificationMapper;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Notification;
import life.majiang.community.model.NotificationExample;
import life.majiang.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/28 14:58
 */
@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CommentMapper commentMapper;

    public PaginationDTO list(Long userId, Integer page, Integer size) {

        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId);
        Integer totalCount = (int)notificationMapper.countByExample(notificationExample);

        paginationDTO.setPagination(totalCount, page, size);
        if(page<1)
            page=1;
        if(page>paginationDTO.getTotalPage())
            page=paginationDTO.getTotalPage();
        Integer offset = size*(page-1);

        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));

        if(notifications.size() == 0){
            return paginationDTO;
        }

        ArrayList<NotificationDTO> notificationDTOList = new ArrayList<>();

        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = getNotificationDTO(notification);
            notificationDTOList.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOList);
        return paginationDTO;
    }

    private NotificationDTO getNotificationDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setGmtCreate(notification.getGmtCreate());
        notificationDTO.setOuterId(notification.getOuterid());
        notificationDTO.setType(NotificationTypeEnum.nameOfType(notification.getType()));
        notificationDTO.setStatus(notification.getStatus());
        User notifier = userMapper.selectByPrimaryKey(notification.getNotifier());
        notificationDTO.setNotifier(notifier);

        notificationDTO.setOuterTitle(questionMapper.selectByPrimaryKey(notification.getOuterid()).getTitle());

        notificationDTO.setId(notification.getId());
        return notificationDTO;
    }

    public Long unreadCount(Long id) {
        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(id).andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(example);
    }

    public Notification getById(Long id) {
        return notificationMapper.selectByPrimaryKey(id);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);

        if(notification == null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICANTION_NOT_FOUND);
        }else if(notification.getReceiver().longValue() != user.getId().longValue()){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FATL);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = getNotificationDTO(notification);
        return notificationDTO;
    }
}
