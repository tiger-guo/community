package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionExtMapper {
    int incView(Question question);
    int incCount(Question question);
    List<Question> selectRelated(Question question);
}