/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

/**
 *
 * @author USER
 */

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class InicializadorMongo {

    public static void inicializar() {
        try {
            MongoDatabase db = ConexionMongo.conectar();

            MongoCollection<Document> admin = db.getCollection("admin");
            MongoCollection<Document> estudiantes = db.getCollection("estudiantes");

            crearIndices(admin, estudiantes);
            crearAdministradores(admin);

            System.out.println("Base de datos ElectaVox inicializada correctamente.");

        } catch (MongoException e) {
            System.out.println("Error al inicializar MongoDB: " + e.getMessage());
        }
    }

    private static void crearIndices(
            MongoCollection<Document> admin,
            MongoCollection<Document> estudiantes) {

        try {
            admin.createIndex(
                    Indexes.ascending("cedula"),
                    new IndexOptions().unique(true)
            );

            estudiantes.createIndex(
                    Indexes.ascending("cedula"),
                    new IndexOptions().unique(true)
            );

        } catch (MongoException e) {
            System.out.println("Los índices ya existen o no se pudieron crear.");
        }
    }

    private static void crearAdministradores(MongoCollection<Document> admin) {
        insertarAdminSiNoExiste(
                admin,
                "2300414147",
                "Iker",
                "Guallo"
        );

        insertarAdminSiNoExiste(
                admin,
                "2350511111",
                "Jeffry",
                "Vera"
        );
    }

    private static void insertarAdminSiNoExiste(
            MongoCollection<Document> admin,
            String cedula,
            String nombres,
            String apellidos) {

        Document existente = admin.find(eq("cedula", cedula)).first();

        if (existente == null) {
            Document documento = new Document()
                    .append("cedula", cedula)
                    .append("nombres", nombres)
                    .append("apellidos", apellidos)
                    .append("rol", "ADMIN");

            admin.insertOne(documento);

            System.out.println("Admin creado: " + cedula);
        } else {
            System.out.println("Admin ya existe: " + cedula);
        }
    }
}