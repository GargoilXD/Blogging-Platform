module org.blogging.platform {
    requires javafx.controls;
    requires org.mongodb.driver.sync.client;
    requires java.sql;
    requires com.zaxxer.hikari;
    requires org.blogging.platform;
    requires de.mkammerer.argon2.nolibs;
    exports org.blogging.platform;
    exports org.blogging.platform.Exceptions;
}
