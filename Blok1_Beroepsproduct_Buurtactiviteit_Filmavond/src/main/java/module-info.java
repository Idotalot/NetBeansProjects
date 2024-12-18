module com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond {
    requires javafx.controls;
    
    requires java.management;
    requires java.sql;
    
    requires mysql.connector.j;
    requires org.apache.commons.dbcp2;
    
    opens com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model to javafx.base;
    
    exports com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond;
}
