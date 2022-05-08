package ua.alextwit.twitserver.model.dao;

import lombok.NonNull;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.alextwit.twitserver.model.dto.PostsAndHeartsDto;
import ua.alextwit.twitserver.model.entity.Twit;
import ua.alextwit.twitserver.utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TwitDaoImpl implements Dao<Twit> {
    private static volatile TwitDaoImpl twitDao;
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    private TwitDaoImpl() {
    }

    public synchronized static TwitDaoImpl getTwitDao() {
        if (twitDao == null)
            twitDao = new TwitDaoImpl();
        return twitDao;
    }

    @Override
    public Twit save(@NonNull Twit twit) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            twit.setId((Long) session.save(twit));
            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException("Can't save Twit by id:" + twit.getId(), e);
        }
        return twit;
    }

    @Override
    public Optional<Twit> findById(@NonNull Long id) {
        Twit twit;
        try (Session session = sessionFactory.openSession()) {
            twit = session.get(Twit.class, id);
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find Twit by id:" + id, e);
        }
        return Optional.ofNullable(twit);
    }

    @Override
    public List<Twit> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Twit> twits = session.createQuery("FROM Twit", Twit.class);
            return twits.list();
        } catch (HibernateException e) {
            throw new RuntimeException("Can't get all Twit", e);
        }
    }

    @Override
    public Twit update(@NonNull Twit updatedTwit) {
        try (Session session = sessionFactory.openSession()) {
            Twit extractedTwit = session.get(Twit.class, updatedTwit.getId());
            if (extractedTwit == null)
                throw new RuntimeException("Can't find the Twit with id '" + updatedTwit.getId() +
                        "' to update");
            Transaction transaction = session.beginTransaction();
            updatingTwit(updatedTwit, extractedTwit);
            session.update(extractedTwit);
            transaction.commit();
            return extractedTwit;
        } catch (HibernateException e) {
            throw new RuntimeException("Can't update Twit by id:" + updatedTwit.getId(), e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Can't update Twit because there is no such id:" +
                    updatedTwit.getId(), e);
        }
    }

    private void updatingTwit(@NonNull Twit updatedTwit, Twit extractedTwit) {
        extractedTwit.setMessage(updatedTwit.getMessage())
                .setHeart(updatedTwit.getHeart())
                .setImportant(updatedTwit.getImportant());
    }

    public List<Twit> updateAll(@NonNull List<Twit> updatedTwits) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Twit> newTwits = new ArrayList<>();
            updatedTwits.forEach(updatedTwit -> {
                try {
                    Twit extractedTwit = session.get(Twit.class, updatedTwit.getId());
                    newTwits.add(extractedTwit);
                    updatingTwit(updatedTwit, extractedTwit);
                    session.update(extractedTwit);
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Can't update Twit because there is no such id:" +
                            updatedTwit.getId(), e);
                } catch (HibernateException e) {
                    throw new RuntimeException("Can't update Twit by id:" + updatedTwit.getId(), e);
                }
            });
            transaction.commit();
            return newTwits;
        } catch (HibernateException e) {
            throw new RuntimeException("Can't update Twits", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            var twit = session.get(Twit.class, id);
            if (twit == null)
                throw new RuntimeException("Can't find the Twit with id '" + id +
                        "' to delete");
            session.remove(twit);
            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException("Can't delete Twit by id:" + id, e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Can't delete Twit because there is no such id:" + id, e);
        }
    }

    public PostsAndHeartsDto getPostCount() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("SELECT COUNT (*), COUNT (heart) FROM Twit");
            Long[] o = (Long[]) query.getSingleResult();
            return new PostsAndHeartsDto().setPosts(o[0]).setHearts(o[1]);
        } catch (HibernateException e) {
            throw new RuntimeException("Can't get posts and hearts from Twit", e);
        }
    }
}
