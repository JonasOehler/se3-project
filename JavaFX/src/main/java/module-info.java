module librarymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires org.apache.logging.log4j;
    requires AnimateFX;
    requires atlantafx.base;


    opens librarymanagementsystem to javafx.fxml, org.hibernate.orm.core;
    exports librarymanagementsystem;
    exports librarymanagementsystem.models;
    opens librarymanagementsystem.models to javafx.fxml, org.hibernate.orm.core;
    exports librarymanagementsystem.controllers;
    opens librarymanagementsystem.controllers to javafx.fxml, org.hibernate.orm.core;
    exports librarymanagementsystem.utils;
    opens librarymanagementsystem.utils to javafx.fxml, org.hibernate.orm.core;

}