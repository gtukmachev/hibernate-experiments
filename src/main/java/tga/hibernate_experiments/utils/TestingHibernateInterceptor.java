package tga.hibernate_experiments.utils;


import org.hibernate.*;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Iterator;

public class TestingHibernateInterceptor extends EmptyInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TestsWithHibernate.class);

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        log.trace("hib.onLoad({}, {})", entity, id);
        return false;
    }

    @Override
    public Object instantiate(String entityName, EntityMode entityMode, Serializable id) throws CallbackException {
        log.trace("hib.instantiate(entityName = '{}', entityMode = '{}', id = '{}')", entityName, entityMode, id);
        return null;
    }

    @Override
    public String onPrepareStatement(String sql) {
        log.trace("hib.onPrepareStatement(sql = '{}')", sql);
        // ...
        return sql;
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        log.trace("hib.onDelete");
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        log.trace("hib.onFlushDirty");
        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        log.trace("hib.onSave");
        return super.onSave(entity, id, state, propertyNames, types);
    }

    @Override
    public void postFlush(Iterator entities) {
        log.trace("hib.postFlush");
        super.postFlush(entities);
    }

    @Override
    public void preFlush(Iterator entities) {
        log.trace("hib.preFlush");
        super.preFlush(entities);
    }

    @Override
    public Boolean isTransient(Object entity) {
        log.trace("hib.isTransient");
        return super.isTransient(entity);
    }

    @Override
    public int[] findDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        log.trace("hib.findDirty");
        return super.findDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    @Override
    public String getEntityName(Object object) {
        log.trace("hib.getEntityName");
        return super.getEntityName(object);
    }

    @Override
    public Object getEntity(String entityName, Serializable id) {
        log.trace("hib.getEntity");
        return super.getEntity(entityName, id);
    }

    @Override
    public void afterTransactionBegin(Transaction tx) {
        log.trace("hib.");
        super.afterTransactionBegin(tx);
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {
        log.trace("hib.afterTransactionCompletion");
        super.afterTransactionCompletion(tx);
    }

    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        log.trace("hib.beforeTransactionCompletion");
        super.beforeTransactionCompletion(tx);
    }

    @Override
    public void onCollectionRemove(Object collection, Serializable key) throws CallbackException {
        log.trace("hib.onCollectionRemove");
        super.onCollectionRemove(collection, key);
    }

    @Override
    public void onCollectionRecreate(Object collection, Serializable key) throws CallbackException {
        log.trace("hib.onCollectionRecreate");
        super.onCollectionRecreate(collection, key);
    }

    @Override
    public void onCollectionUpdate(Object collection, Serializable key) throws CallbackException {
        log.trace("hib.onCollectionUpdate");
        super.onCollectionUpdate(collection, key);
    }
}
