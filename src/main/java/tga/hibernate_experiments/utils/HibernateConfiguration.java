package tga.hibernate_experiments.utils;

public enum HibernateConfiguration {
    no_l2_cache     ("hibernate-no-cache.properties"),
    native_ehcache_2("hibernate-ehCache2.properties"),
    jCache_ehCache_3("hibernate-jCache-ehCache3.properties");

    public final String hibernatePropertiesFile;

    HibernateConfiguration(String hibernatePropertiesFile) {
        this.hibernatePropertiesFile = hibernatePropertiesFile;
    }
}
