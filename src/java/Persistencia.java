
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//Para conectar con la base de datos
public class Persistencia {
    
   private Connection cn; //para la conexion
   private ResultSet rs; //para recibir los datos que trae la bd
   private PreparedStatement ps; //recibe la orden del select o de la consulta antes de hacer esa devolucion
   private ResultSetMetaData rsm; //recibir los datos de la consulta
   
   public String servidor, basededatos, usuario, clave, ejecutar; //para traer los datos del servidor, conexion con bd, usuario de conexion con la bd, ejecutar la conexion
   
   
   public Connection conectarse(){  //para ejecutar la conexion
       
       try {
           Class.forName("com.mysql.jdbc.Driver"); //que driver usamos
      
   
   servidor = "localhost:3306/"; //remotemysql.com
   basededatos = "proyectocac2021";
   usuario = "root";
   clave = "";
           
   
   
        cn=DriverManager.getConnection("jdbc:mysql://" + servidor + basededatos+"?autoReconnect=true&useSSL=false" + usuario + clave);  //forma de conexion
   
    } catch (ClassNotFoundException ex) {
           Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
       }
       return cn;
       
   }
        
    //********************************************************************************************
   
    //Una vez hecha la conexion, realizar la consulta a la bd para poder  validar el usuario que estamos ingresando
   
    public ResultSet consultaSQL(String busqueda){
        
       try {
           ps = conectarse().prepareStatement(busqueda); //se ejecuta el metodo conectarse y le envia la consulta atraves de la variable busqueda
      
        rs = ps.executeQuery(); //ejecuta la query con el rs
        rsm = rs.getMetaData(); //trae los metadatos de la query
        
         } catch (SQLException ex) {
           Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
       }
       
        return rs;
        
    }
   
    
}
