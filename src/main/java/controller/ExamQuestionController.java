package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Answer;
import model.Question;
import service.ExamQuestionService;

@WebServlet("/")
public class ExamQuestionController extends HttpServlet {
	
	private ExamQuestionService examQuestionService;
	
	public void init() {
		examQuestionService = new ExamQuestionService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertQuestions(request, response);
				break;
			case "/delete":
				deleteQuestions(request, response);
				break;
			case "/update":
				updateQuestions(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			default:
				listQuestions(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listQuestions(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Question> listQuestions = examQuestionService.selectAllQuestions();
		
		for (Question question : listQuestions) {
            System.out.println(question.getQuestion());
            //System.out.println(question.getAnswer().getAnswer1());
        }
		
		request.setAttribute("listQuestions", listQuestions);
		RequestDispatcher dispatcher = request.getRequestDispatcher("questions-list.jsp");
		dispatcher.forward(request, response);
	}

	private void insertQuestions(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException{
				
		int examid = Integer.parseInt(request.getParameter("examid"));
		int examquestionid = Integer.parseInt(request.getParameter("examquestionid"));
		String question = request.getParameter("question");
		
		Answer answer = new Answer();
		String answers = request.getParameter("answer");
		String[] answersList = answers.split(",");
		
		answer.setAnswer1(answersList[0]);
		answer.setAnswer2(answersList[1]);
		answer.setAnswer3(answersList[2]);
		answer.setAnswer4(answersList[3]);
		
		Date createdTime = new Date();
		String createdBy = request.getParameter("createdBy");
		
		Question newQuestion = new Question(examid, examquestionid, question,answer, createdTime,createdBy);
		examQuestionService.insertQuestion(newQuestion);
		response.sendRedirect("list");
	}

	private void deleteQuestions(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		int examquestionid = Integer.parseInt(request.getParameter("id"));
		examQuestionService.deleteQuestions(examquestionid);
		response.sendRedirect("list");
	}
	
	private void updateQuestions(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException,ServletException {
		
		int examid = Integer.parseInt(request.getParameter("examid"));
		int examquestionid = Integer.parseInt(request.getParameter("examquestionid"));		
		String question = request.getParameter("question");
		
		Answer answer = new Answer();
		String answers = request.getParameter("answer");
		String[] answersList = answers.split(",");
		
		answer.setAnswer1(answersList[0]);
		answer.setAnswer2(answersList[1]);
		answer.setAnswer3(answersList[2]);
		answer.setAnswer4(answersList[3]);		
		
		
		Date createdTime = new Date();
		String createdBy = request.getParameter("createdBy");
		
		Question updateQuestion = new Question(examid, examquestionid, question,answer, createdTime,createdBy);		
		
		examQuestionService.updateQuestion(updateQuestion);
		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int examquestionid = Integer.parseInt(request.getParameter("id"));
		Question existingQuestion = examQuestionService.selectQuestion(examquestionid);
		existingQuestion.setExamquestionid(examquestionid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("question-form.jsp");
		request.setAttribute("question", existingQuestion);
		dispatcher.forward(request, response);

	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("question-form.jsp");
		dispatcher.forward(request, response);
	}
	
}
