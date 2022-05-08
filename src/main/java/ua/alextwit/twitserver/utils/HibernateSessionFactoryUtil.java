package ua.alextwit.twitserver.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ua.alextwit.twitserver.model.entity.Twit;

public final class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Twit.class);
            try {
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                        applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (HibernateException e) {
                throw new RuntimeException(e.getLocalizedMessage(), e);
            }
        }
        return sessionFactory;
    }
}
