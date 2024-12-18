module com.mycompany.blok1_fgt1_fysio {
    requires javafx.controls;
    
    requires java.management;
    requires java.sql;
    
    requires mysql.connector.j;
    requires org.apache.commons.dbcp2;
    
    opens com.mycompany.blok1_fgt1_fysio.Fysio to javafx.base;
    opens com.mycompany.blok1_fgt1_fysio.Patient to javafx.base;
    opens com.mycompany.blok1_fgt1_fysio.Afspraak to javafx.base;

    
    exports com.mycompany.blok1_fgt1_fysio;
}
