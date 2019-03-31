package ua.epam.spring.hometask.service;


import ua.epam.spring.hometask.domain.User;

/**
 * @author Yuriy_Tkach
 */
public interface UserService extends AbstractDomainObjectService<User> {

    /**
     * Finding user by email
     * 
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
    public User getUserByEmail(String email);

}
