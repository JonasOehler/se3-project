package librarymanagementsystem.db;

import librarymanagementsystem.models.Book;
import librarymanagementsystem.models.Media;
import librarymanagementsystem.models.Movie;
import librarymanagementsystem.utils.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.service.spi.ServiceException;

import java.time.LocalDate;
import java.util.List;

public class SQLManager {
    private List queryResultList;

    public SQLManager() {

    }

    HQLManager HQLManager = new HQLManager();
    private static final Logger log = LogManager.getLogger(SQLManager.class);

    public List getQueryResult(String query) {
        try (Session session = HibernateUtil.getCurrentSessionFromConfig()) {
            session.beginTransaction();
            queryResultList = session.createQuery(query).list();
            session.getTransaction().commit();
            return queryResultList;
        } catch (ServiceException e) {
            log.error("Database may already in use");
        }
        return queryResultList;
    }
    public void insertQuery(Media mediaToBeIssued, long mediaId, long userId, LocalDate issueDate, LocalDate returnDate){
        try (Session session = HibernateUtil.getCurrentSessionFromConfig()) {
            session.beginTransaction();
            Query query = null;
            if (mediaToBeIssued.getClass() == Book.class) {
                query = session.createNativeQuery ("INSERT INTO ISSUED_BOOKS (BOOK_ID, USER_ID, ISSUE_DATE, RETURN_DATE) VALUES (?, ?, ?, ?)");
            } else if (mediaToBeIssued.getClass() == Movie.class){
                query = session.createNativeQuery ("INSERT INTO ISSUED_MOVIES (MOVIE_ID, USER_ID, ISSUE_DATE, RETURN_DATE) VALUES (?, ?, ?, ?)");
            }
            query.setParameter(1,mediaId);
            query.setParameter(2,userId);
            query.setParameter(3,issueDate);
            query.setParameter(4,returnDate);
            int rowsAffected = query.executeUpdate();
            log.info(rowsAffected);
            session.getTransaction().commit();

        } catch (ServiceException e) {
            log.error("Database may already be in use");
        }
    }

    public void updateQuery(Media mediaToBeReturned, long userId, long mediaId){
        try (Session session = HibernateUtil.getCurrentSessionFromConfig()) {
            session.beginTransaction();
            Query query = null;
            if (mediaToBeReturned.getClass() == Book.class) {
                query = session.createNativeQuery ("UPDATE ISSUED_BOOKS SET RETURNED = true WHERE USER_ID = :value1 AND BOOK_ID = :value2");
                HQLManager.updateMediaInDb(mediaToBeReturned);
            } else if (mediaToBeReturned.getClass() == Movie.class){
                query = session.createNativeQuery ("UPDATE ISSUED_MOVIES SET RETURNED = true WHERE USER_ID = :value1 AND MOVIE_ID = :value2");
                HQLManager.updateMediaInDb(mediaToBeReturned);
            }
            query.setParameter("value1",userId);
            query.setParameter("value2", mediaId);
            int rowsAffected = query.executeUpdate();
            log.info(rowsAffected);
            session.getTransaction().commit();

        } catch (ServiceException e) {
            log.error("Database may already be in use");
        }
    }
}
