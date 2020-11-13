package tga.hibernate_experiments.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.Serializable;
import java.util.List;
import java.util.function.Supplier;

public class TestsWithHibernate {

    private final List<Class<?>> entityClasses;
    private static final Logger log = LoggerFactory.getLogger(TestsWithHibernate.class);

    protected SessionFactory sessionFactory;
    protected Session session;
    protected final boolean isCacheEnabled;

    public TestsWithHibernate(
            List<Class<?>> entityClasses,
            boolean isCacheEnabled
    ) {
        this.entityClasses = entityClasses;
        this.isCacheEnabled = isCacheEnabled;
    }

    @Before
    public void setUp() {
        MDC.clear(); MDC.put("lp", "");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .loadProperties(isCacheEnabled ? "hibernate-cache.properties" : "hibernate-no-cache.properties")
                .build();

        MetadataSources sources = new MetadataSources(registry);
        for(Class<?> entityClass : entityClasses) sources.addAnnotatedClass( entityClass );

        MetadataBuilder metadataBuilder = sources.getMetadataBuilder();
        Metadata metadata = metadataBuilder.build();

        // A SessionFactory is set up once for an application!
        SessionFactoryBuilder sessionFactoryBuilder  = metadata.getSessionFactoryBuilder();

        // Adding an interceptor
        sessionFactoryBuilder.applyInterceptor(new TestingHibernateInterceptor());

        sessionFactory = sessionFactoryBuilder.build();

        openSessionAndStartTransaction();
    }


    protected void openSessionAndStartTransaction() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    protected void commitTransactionAndCloseSession() {
        session.getTransaction().commit();
        session.close();
    }

    protected void commitAndReopenSession() {
        commitTransactionAndCloseSession();
        openSessionAndStartTransaction();
        log.info("### Commit and reopen a new 'hibernate session' is done #################################### ");
    }

    @After
    public void close() {
        commitTransactionAndCloseSession();
    }

    protected <T> T getById(Class<T> clz, Serializable id) {
        return withLog("session.get("+(clz.getSimpleName())+", "+id+")", () -> session.get(clz, id) );
    }
    protected <T> T withLog(String operation, Supplier<T> action) {
        log.info(operation+"....");
        T obj = null;
        try {
            obj = action.get();
        } finally {
            log.info(operation+".... done");
        }
        return obj;
    }



}
