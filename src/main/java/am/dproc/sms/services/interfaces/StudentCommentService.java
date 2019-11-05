package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.StudentComment;

public interface StudentCommentService {
	
	Integer addComment(StudentComment Comment);

	StudentComment getComment(Integer id);

	List<StudentComment> getCommentsOfTopic(Integer topicID);

	Integer updateComment(StudentComment Comment);

	Integer deleteComment(Integer id);

}
