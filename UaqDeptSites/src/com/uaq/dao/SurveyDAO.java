package com.uaq.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uaq.command.SurveyCommand;
import com.uaq.exception.DAOException;
import com.uaq.exception.UAQException;
import com.uaq.exception.UAQFaultCode;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.OptionResultVO;
import com.uaq.vo.QuizVO;

/**
 * This class is used to manipulate the survey data in the database.
 * 
 * @author mraheem
 * 
 */
@Repository("surveyDAO")
public class SurveyDAO implements BaseDAO<SurveyCommand, Boolean> {

	private static transient UAQLogger logger = new UAQLogger(SurveyDAO.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 
	 */
	@Override
	public Boolean execute(SurveyCommand surveyCommand) throws UAQException {

		logger.debug("SurveyCommand for name" + surveyCommand.getFormAssetId());

		StringBuilder query = new StringBuilder();

		query.append(" INSERT INTO SURVEY values(?,?,?,?) ");

		try {

			jdbcTemplate.update(query.toString(), new Object[] { surveyCommand.getSessionId(), surveyCommand.getFormAssetId(), surveyCommand.getSite(), surveyCommand.isPoll() });

			// saving all options for each question
			for (QuizVO quiz : surveyCommand.getQuestionAnswers()) {
				if (quiz.getAnswer() != null) {
					batchUpdateSurveyFormFieldOptions(surveyCommand, quiz.getQuestion(), quiz.getAnswer());
				}
			}

		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			throw new DAOException(UAQFaultCode.SESSION_PK, dataIntegrityViolationException);
		} catch (Exception e) {
			throw new UAQException(e.getMessage());
		}

		logger.debug("Survey successfull for name" + surveyCommand.getFormAssetId());

		return true;
	}

	/**
	 * insert child table records in bulk, SURVEY_FORM_FIELD
	 * 
	 * @param quenstionAnswers
	 * @param fk
	 * @throws DAOException
	 */
	private void batchUpdateSurveyFormFieldOptions(final SurveyCommand surveyCommand, final String formFieldId, final String[] options) throws DAOException {
		try {
			String sql = "INSERT INTO SURVEY_FORM_FIELD_OPTION VALUES (?, ?, ?, ?)";

			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					String optionId = options[i];
					ps.setString(1, surveyCommand.getSessionId());
					ps.setString(2, surveyCommand.getFormAssetId());
					ps.setString(3, formFieldId);
					ps.setString(4, optionId);
				}

				@Override
				public int getBatchSize() {
					return options.length;
				}
			});
		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			throw new DAOException(UAQFaultCode.USER_NAME_PK, dataIntegrityViolationException);
		}
	}

	/**
	 * This method is used to get poll results, in percentages
	 * 
	 * @return
	 */

	public List<OptionResultVO> getPollsResults() {

		logger.enter("getPollsResults");

		List<OptionResultVO> results = new ArrayList<OptionResultVO>();

		StringBuilder query = new StringBuilder("");
		query.append("select b.form_ID,b.form_field_id, b.answer,b.AnswerCount/a.TotalCount*100.0 || '%' percentage from (");
		query.append(" select sffo.form_ID,sffo.form_field_id, count(*) as TotalCount from SURVEY_FORM_FIELD_OPTION sffo");
		query.append(" left join survey s");
		query.append(" on sffo.session_id = s.session_id and s.is_poll = 0 and s.site = 'EGovernment'");
		query.append(" GROUP BY sffo.form_ID,sffo.form_field_id) a");
		query.append(" left join (select sffo.form_ID,sffo.form_field_id, sffo.answer, count(*) as AnswerCount from SURVEY_FORM_FIELD_OPTION sffo");
		query.append(" left join survey s on sffo.session_id = s.session_id and s.is_poll = 0 and s.site = 'EGovernment'                            ");
		query.append(" GROUP BY sffo.form_ID,sffo.form_field_id, sffo.answer)b");
		query.append(" on a.form_ID = b.form_ID and a.form_field_id = b.form_field_id");

		try {

			results = jdbcTemplate.query(query.toString(), new Object[] {}, new OptionResultMapper());

		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}

		logger.exit("getPollsResults");

		return results;
	}

}
