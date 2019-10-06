package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.StudentComment;;

public interface StudentCommentDAO {
	
	Integer addComment(StudentComment comment);

	StudentComment getComment(Integer id);

	List<StudentComment> getCommentsOfTopic(Integer topicID);

	Integer editComment(Integer id, String comment);

	Integer deleteComment(Integer id);

}
