package dao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.ConexionMongo;
import modelo.Estudiante;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class EstudianteDAO {

    private MongoCollection<Document> coleccion;

    public EstudianteDAO() {
        MongoDatabase db = ConexionMongo.conectar();
        coleccion = db.getCollection("estudiantes");
    }

    public Estudiante buscarPorCedula(String cedula) {
        Document documento = coleccion.find(eq("cedula", cedula)).first();

        if (documento == null) {
            return null;
        }

       return new Estudiante(
        documento.getString("cedula"),
        documento.getString("nombres"),
        documento.getString("apellidos"),
        documento.getString("correo"),
        documento.getString("curso"),
        documento.getString("genero")
);
    }

    public boolean guardar(Estudiante estudiante) {
        try {
            Document doc = new Document()
        .append("cedula", estudiante.getCedula())
        .append("nombres", estudiante.getNombres())
        .append("apellidos", estudiante.getApellidos())
        .append("correo", estudiante.getCorreo())
        .append("curso", estudiante.getCurso())
        .append("genero", estudiante.getGenero());

            coleccion.insertOne(doc);

            System.out.println("Estudiante guardado en MongoDB: " + estudiante.getCedula());

            return true;

        } catch (MongoException e) {
            System.out.println("Error al guardar estudiante: " + e.getMessage());
            return false;
        }
    }
}