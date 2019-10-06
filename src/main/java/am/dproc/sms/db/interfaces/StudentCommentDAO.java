package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.StudentComment;;

public interface StudentCommentDAO {
	
	public Integer addComment(StudentComment comment);

	public StudentComment getComment(Integer id);

	public List<StudentComment> getCommentsOfTopic(Integer topicID);

	public Integer editComment(Integer id, String comment);

	public Integer deleteComment(Integer id);

}
