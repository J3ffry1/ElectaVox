package dao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.ConexionMongo;
import modelo.Candidato;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class CandidatoDAO {

    private MongoCollection<Document> coleccion;

    public CandidatoDAO() {

        MongoDatabase db = ConexionMongo.conectar();

        coleccion = db.getCollection("candidatos");

    }

    public Candidato buscarPorCedula(String cedula) {

        Document documento = coleccion.find(eq("cedula", cedula)).first();

        if (documento == null) {
            return null;
        }

        return new Candidato(
                documento.getString("cedula"),
                documento.getString("nombres"),
                documento.getString("apellidos"),
                documento.getString("lista"),
                documento.getString("propuestas"),
                documento.getString("fotografia")
        );

    }

    public boolean guardar(Candidato candidato) {

        try {

            Document doc = new Document()
                    .append("cedula", candidato.getCedula())
                    .append("nombres", candidato.getNombres())
                    .append("apellidos", candidato.getApellidos())
                    .append("lista", candidato.getLista())
                    .append("propuestas", candidato.getPropuestas())
                    .append("fotografia", candidato.getFotografia());

            coleccion.insertOne(doc);

            System.out.println("Candidato guardado correctamente.");

            return true;

        } catch (MongoException e) {

            System.out.println("Error: " + e.getMessage());

            return false;

        }

    }

}