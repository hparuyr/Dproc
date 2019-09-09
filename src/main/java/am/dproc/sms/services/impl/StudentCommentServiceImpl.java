package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.StudentCommentDAO;
import am.dproc.sms.models.StudentComment;
import am.dproc.sms.services.interfaces.StudentCommentService;

@Service
public class StudentCommentServiceImpl implements StudentCommentService {
	
	@Autowired
	StudentCommentDAO comment;

	@Override
	public StudentComment getComment(Integer id) {
		return comment.getComment(id);
	}

	@Override
	public List<StudentComment> getCommentsOfTopic(Integer topicID) {
		return comment.getCommentsOfTopic(topicID);
	}

	@Override
	public Integer deleteComment(Integer id) {
		return comment.deleteComment(id);
	}

	@Override
	public Integer addComment(StudentComment comment) {
		if (comment.getComment() == null || comment.getComment() == "") {
			return 0;
		}
		return this.comment.addComment(comment);
	}

	@Override
	public Integer editComment(StudentComment comment) {
		return this.comment.editComment(comment.getId(), comment.getComment());
	}
	
	

}
