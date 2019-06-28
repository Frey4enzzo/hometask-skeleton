package ua.epam.spring.hometask.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import ua.epam.spring.hometask.domain.User;

@Slf4j
public class UserItemProcessor implements ItemProcessor<User, User>{

  @Override
  public User process(final User user) throws Exception {
    User transormedUser =
        new User(user.getFirstName().toUpperCase(), user.getLastName().toUpperCase(), user.getEmail().toUpperCase());
    log.info("Converting (" + user + ") into (" + transormedUser + ")");
    return transormedUser;
  }
}
