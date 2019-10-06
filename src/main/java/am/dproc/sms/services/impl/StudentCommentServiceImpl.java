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
	StudentCommentDAO studentCommentDAO;

	@Override
	public Integer addComment(StudentComment comment) {
		if (comment.getComment() == null || comment.getComment().equals("")) {
			return -1;
		}
		return studentCommentDAO.addComment(comment);
	}

	@Override
	public StudentComment getComment(Integer id) {
		return studentCommentDAO.getComment(id);
	}

	@Override
	public List<StudentComment> getCommentsOfTopic(Integer topicID) {
		return studentCommentDAO.getCommentsOfTopic(topicID);
	}

	@Override
	public Integer editComment(StudentComment comment) {
		boolean bool = false;

		if (comment.getComment() != null) {
			if (this.studentCommentDAO.editComment(comment.getId(), comment.getComment()) == 0) {
				return -1;
			}
			bool = true;
		}
		return bool ? 1 : 0;
	}

	@Override
	public Integer deleteComment(Integer id) {
		return studentCommentDAO.deleteComment(id);
	}

}
