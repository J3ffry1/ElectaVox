package conexion;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConexionMongo {

    private static final String URI =
            "mongodb://localhost:27017";

    private static MongoDatabase database;

    public static MongoDatabase conectar() {

        if(database == null){

            MongoClient cliente =
                    MongoClients.create(URI);

            database =
                    cliente.getDatabase("ElectaVox");
            
            System.out.println("Conectado a MongoDB, base de datos: "
                    + database.getName());
        }

        return database;
    }
}