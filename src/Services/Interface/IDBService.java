package Services.Interface;

import Models.Source.UserDetails;
import Models.Source.HistoryItem;

import java.util.List;

public interface IDBService {

    /**
     * on this function -
     * call to function openDataBase for open insulin database.
     * find the user on database with email and password same email and password for this user (find the user if is exits on database).
     * call to function closeDataBase for close insylin database.
     *
     * @param userDetails is the user (mean all details of user).
     * @return email for this user from database(MongoDB) if user is exits.
     */
    public String getEmail(UserDetails userDetails);

    /**
     * on this function -
     * call to function openDataBase for open insulin database.
     * find all list history for this user (user with param userId) and save all in list with type HistoryItem.
     * call to function closeDataBase for close insylin database.
     *
     * @param userId is email/username of user.
     * @return list all history of tests for calculating the amount of insulin injection.
     */
    public List<HistoryItem> getUserHistory(String userId);

    /**
     * on this function -
     * call to function openDataBase for open insulin database.
     * SAVE Or ADD the Details with specific calculation on database.
     * call to function closeDataBase for close insylin database.
     *
     * @param userId is email/username of user.
     * @param item is Details with specific calculation.
     */
    public void addHistoryItem(String userId, HistoryItem item);

    /**
     * on this function -
     * call to function openDataBase for open insulin database.
     * this function counts how much user with username have in history collection
     * call to function closeDataBase for close insylin database.
     *
     * @param username of user
     * @return True if this user have histories on database else false
     */
    public boolean isUserExist(String username);

    /**
     * on this function -
     * call to function openDataBase for open insulin database.
     * it finds the user have username/email same on database.
     * it Chicks if this user exists on database
     * call to function closeDataBase for close insylin database.
     *
     * @param userDetails is the user (mean all details of user).
     * @return True if this user exits on database else false
     */
    public boolean isUserExist(UserDetails userDetails);

    /**
     * on this function -
     * call to function openDataBase for open insulin database.
     * SAVE Or ADD user on database.
     * call to function closeDataBase for close insylin database.
     *
     * @param userDetails is the user (all Details of user).
     * @return True if the user is added to a database or False if not (for most, the user with same email/username exists).
     */
    public boolean addUser(UserDetails userDetails);

    /**
     * on this function -
     * call to function openDataBase for open insulin database.
     * find user with userId from a database if exits and return her name (firstName with Lastname)
     * call to function closeDataBase for close insylin database.
     *
     * @param userId is email/ username of user
     * @return fist Name + " " + last Name
     */
    public String getName(String userId);
}
