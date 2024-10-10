package model;

import java.util.Date;
import java.util.List;


public class Question {
	
	private int examid;
	private int examquestionid;
	private String question;
	private Answer answer;
	private Date createdTime;
	private String createdBy;
	
	public Question(int examid, int examquestionid, String question, Answer answer, Date createdTime, String createdBy) {
		super();
		this.examid = examid;
		this.examquestionid = examquestionid;
		this.question = question;
		this.answer = answer;
		this.createdTime = createdTime;
		this.createdBy = createdBy;
	}
	
	public Question(int examid, Answer answer, String questionName, String createdBy) {
		this.examid = examid;
		this.question = questionName;
		this.answer = answer;
		this.createdBy = createdBy;
	}

	public int getExamid() {
		return examid;
	}
	public void setExamid(int examid) {
		this.examid = examid;
	}
	public int getExamquestionid() {
		return examquestionid;
	}
	public void setExamquestionid(int examquestionid) {
		this.examquestionid = examquestionid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	

}
