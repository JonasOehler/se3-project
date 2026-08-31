package librarymanagementsystem.db;

import librarymanagementsystem.models.*;
import librarymanagementsystem.utils.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.service.spi.ServiceException;

public class HQLManager {
    private static final Logger log = LogManager.getLogger(HQLManager.class);
    public void persistUser(User user) {
        try (Session session = HibernateUtil.getCurrentSessionFromConfig()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
            log.info("user was persisted");
        } catch (ServiceException e) {
            log.error("Database may already be in use");
        }catch (org.hibernate.exception.DataException e){
            log.error("User input is to long");
        }
    }

    public void persistMedia(Media media) {
        try (Session session = HibernateUtil.getCurrentSessionFromConfig()) {
            session.beginTransaction();
            session.persist(media);
            session.getTransaction().commit();
            log.info("media was persisted");
        } catch (ServiceException e) {
            log.error("Database may already be in use" +e);
        } catch (org.hibernate.exception.DataException e) {
            log.error("Media input is to long");
        }
    }

    public Media getMediaFromDb(MediaType type, long id) {
        Media fetchedMedia = null;
        try (Session session = HibernateUtil.getCurrentSessionFromConfig()) {
            session.beginTransaction();
            switch (type) {
                case BOOK:
                    fetchedMedia = session.get(Book.class, id);
                    break;
                case MOVIE:
                    fetchedMedia = session.get(Movie.class, id);
                    break;
            }
            session.getTransaction().commit();
            log.info("got media");
            return fetchedMedia;
        } catch (ServiceException e) {
            log.error("Database may already be in use");
        }
        return fetchedMedia;
    }
    public User getUserFromDb(long id) {
        User fetchedUser = null;
        try (Session session = HibernateUtil.getCurrentSessionFromConfig()) {
            session.beginTransaction();
            fetchedUser = session.get(User.class, id);
            session.getTransaction().commit();
            log.info("got user");
            return fetchedUser;
        } catch (ServiceException e) {
            log.error("Database may already be in use");
        }
        return fetchedUser;
    }

    public void deleteMediaFromDb(MediaType type, long id) {
        Media media = getMediaFromDb(type, id);
        try (Session session = HibernateUtil.getCurrentSessionFromConfig()) {
            session.beginTransaction();
            session.remove(media);
            session.getTransaction().commit();
            log.info("media was removed");
        } catch (ServiceException e) {
            log.error("Database may already be in use");
        }
    }
    public void updateMediaInDb(Media media) {
        try (Session session = HibernateUtil.getCurrentSessionFromConfig()){
            session.beginTransaction();
            session.update(media);
            session.getTransaction().commit();
        }catch (ServiceException e){
            log.error("Database may already in use");
        } catch (org.hibernate.exception.DataException e) {
            log.error("Media input is to long");
        }
    }
}
