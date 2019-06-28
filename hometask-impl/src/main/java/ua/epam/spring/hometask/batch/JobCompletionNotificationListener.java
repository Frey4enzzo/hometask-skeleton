package ua.epam.spring.hometask.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.User;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final String SELECT_USERS_QUERY = "select * from users";
  private final JdbcTemplate jdbcTemplate;

  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");
      jdbcTemplate.query(SELECT_USERS_QUERY,
          (rs, row) -> new User(rs.getString(1), rs.getString(2), rs.getString(3)))
          .forEach(user -> log.info("Found <" + user + "> in the database."));
    }
  }
}
