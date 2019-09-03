package am.dproc.sms.models;

import java.util.List;

public class QuestionBean {

	private Integer id;
	private String content;
	private Integer testId;
	private List<AnswerBean> answers;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<AnswerBean> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerBean> answers) {
		this.answers = answers;
	}
	public Integer getTestId() {
		return testId;
	}
	public void setTestId(Integer testId) {
		this.testId = testId;
	}

}

