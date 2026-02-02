module org.blogging.platform {
    requires java.sql;
    requires com.zaxxer.hikari;

    requires de.mkammerer.argon2.nolibs;

    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires com.sun.jna;
    requires io.github.cdimascio.dotenv.java;

    opens org.blogging.platform to javafx.fxml;
    exports org.blogging.platform;

    opens org.blogging.platform.Controllers to javafx.fxml;
    exports org.blogging.platform.Controllers;

    opens org.blogging.platform.Services to javafx.fxml;
    exports org.blogging.platform.Services;

    exports org.blogging.platform.DataAccessors.Exceptions;
    exports org.blogging.platform.Controllers.Exceptions;

    opens org.blogging.platform.Controllers.Exceptions to javafx.fxml;
    exports org.blogging.platform.Utilities;

    opens org.blogging.platform.Utilities to javafx.fxml;
    exports org.blogging.platform.DataTransporters;
    exports org.blogging.platform.Models to org.mongodb.bson;
    exports org.blogging.platform.Controllers.Intefaces;
    opens org.blogging.platform.Controllers.Intefaces to javafx.fxml;
}
