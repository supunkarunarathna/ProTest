package service;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Answer;
import model.Question;

public class ExamQuestionService {

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/unisoft", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public List<Question> selectAllQuestions() throws JsonMappingException, JsonProcessingException {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Question> questions = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement("select * from questions");) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int examid = rs.getInt("examId");
				int examquestionid = rs.getInt("examquestionid");

				// Pass the question id and get answers
				// List<Answer> answers = getAnswers(examquestionid);

				String jsonData = rs.getString("answer");
				ObjectMapper objectMapper = new ObjectMapper();
				Answer answer = objectMapper.readValue(jsonData, Answer.class);

				String question = rs.getString("question");
				Date createdTime = rs.getDate("createdTime");
				String createdBy = rs.getString("createdBy");
				questions.add(new Question(examid, examquestionid, question, answer, createdTime, createdBy));
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		return questions;
	}

	// Insert question
	public void insertQuestion(Question question) throws SQLException, JsonProcessingException {
		System.out.println("INSERT_QUESTIONS_SQL");
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO questions (examId, examquestionid, question, answer, createdTime, createdBy) VALUES (?, ?, ?, ?, ?, ?)")) {
			preparedStatement.setInt(1, question.getExamid());
			preparedStatement.setInt(2, question.getExamquestionid());
			preparedStatement.setString(3, question.getQuestion());

			ObjectMapper objectMapper = new ObjectMapper();
			String jsonStringAnswer = objectMapper.writeValueAsString(question.getAnswer());
			preparedStatement.setString(4, jsonStringAnswer);

			
			preparedStatement.setDate(5, new java.sql.Date(question.getCreatedTime().getTime()));
			preparedStatement.setString(6, question.getCreatedBy());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Update question
	public boolean updateQuestion(Question question) throws SQLException, JsonProcessingException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"update questions set examId = ?, answer = ?,question= ?,createdTime = ?, createdBy = ? where examquestionid = ?;");) {
			// System.out.println("updated USer:"+statement);
			statement.setInt(1, question.getExamid());

			ObjectMapper objectMapper = new ObjectMapper();
			String jsonStringAnswer = objectMapper.writeValueAsString(question.getAnswer());
			statement.setString(2, jsonStringAnswer);

			statement.setString(3, question.getQuestion());
			statement.setDate(4, new java.sql.Date(question.getCreatedTime().getTime()));
			statement.setString(5, question.getCreatedBy());
			statement.setInt(6, question.getExamquestionid());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public boolean deleteQuestions(int examQuestionid) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM questions WHERE examquestionid = ?");) {
			statement.setInt(1, examQuestionid);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public Question selectQuestion(int examquestionid) throws JsonMappingException, JsonProcessingException {
		Question question = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement("select examId,answer,question,createdBy from questions where examquestionid =?");) {
			preparedStatement.setInt(1, examquestionid);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {				
				int examid = rs.getInt("examId");
				
				String jsonData = rs.getString("answer");
				ObjectMapper objectMapper = new ObjectMapper();
				Answer answer = objectMapper.readValue(jsonData, Answer.class);

				String questionName = rs.getString("question");
				String createdBy = rs.getString("createdBy");
				
				question = new Question(examid, answer, questionName, createdBy);
				
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return question;
	}

}
