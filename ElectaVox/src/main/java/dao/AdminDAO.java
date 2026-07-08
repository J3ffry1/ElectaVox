/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author USER
 */
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.ConexionMongo;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class AdminDAO {

    private MongoCollection<Document> coleccion;

    public AdminDAO() {
        MongoDatabase db = ConexionMongo.conectar();
        coleccion = db.getCollection("admin");
    }

    public boolean existeAdmin(String cedula) {
        Document documento = coleccion.find(eq("cedula", cedula)).first();
        return documento != null;
    }
}