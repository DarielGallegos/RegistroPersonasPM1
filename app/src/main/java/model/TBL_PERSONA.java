package model;

public class TBL_PERSONA {
        public static final String DB_NAME = "QUIZ1_8";
        public static final Integer VERSION = 1;
        public static final String TBL_PERSONAS = "persona";
        public static final String ID = "id";

        public static final String NOMBRES = "nombres";
        public static final String APELLIDOS = "apellidos";
        public static final String EDAD = "edad";
        public static final String EMAIL = "email";
        public static final String DIRECCION = "direccion";

        //DLL
        public static final String CREATE_TABLE = "CREATE TABLE " + TBL_PERSONAS +
                " ( id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombres TEXT NOT NULL," +
                "apellidos TEXT NOT NULL," +
                "edad INT NOT NULL," +
                "email TEXT NOT NULL," +
                "direccion TEXT NOT NULL )";

        public static final String EMPLOYEE_GET_ALL = "SELECT * FROM " + TBL_PERSONAS;
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS" + TBL_PERSONAS;
        public static final String EMPLOYEE_GET_BY_ID = "SELECT * FROM " + TBL_PERSONAS + " WHERE id = ?";

        public static final String EMPLOYEE_UPDATE = "UPDATE " + TBL_PERSONAS + " SET " ;
        public static final String EMPLOYEE_DELETE = "DELETE FROM " + TBL_PERSONAS + " WHERE id = ?";
}