package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.StudentComment;

public interface StudentCommentService {
	
	public Integer addComment(StudentComment Comment);

	public StudentComment getComment(Integer id);

	public List<StudentComment> getCommentsOfTopic(Integer topicID);

	public Integer editComment(StudentComment Comment);

	public Integer deleteComment(Integer id);

}
