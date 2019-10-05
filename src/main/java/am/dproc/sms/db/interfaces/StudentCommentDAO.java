package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.StudentComment;;

public interface StudentCommentDAO {

	public StudentComment getComment(Integer id);

	public List<StudentComment> getCommentsOfTopic(Integer topicID);

	public Integer deleteComment(Integer id);

	public Integer addComment(StudentComment comment);

	public Integer editComment(Integer id, String comment);

}
