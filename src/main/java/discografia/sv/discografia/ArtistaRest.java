package discografia.sv.discografia;

import discografia.sv.model.ArtistasDAO;
import discografia.sv.model.artistas;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("artistas")
public class ArtistaRest {

    ArtistasDAO artistasDAO = new ArtistasDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArtistas() {
        try {
            List<artistas> artistas = artistasDAO.findAll();
            return Response.status(200).entity(artistas).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(500).entity("Error al obtener artistas").build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArtistaById(@PathParam("id") int id) {
        try {
            artistas artista = artistasDAO.findById(id);
            if (artista == null) {
                return Response.status(404).entity("Artista no encontrado").build();
            }
            return Response.status(200).entity(artista).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(500).entity("Error al obtener el artista").build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertArtista(@FormParam("nombre_artista") String nombre, @FormParam("descripcion") String descripcion) {
        try {
            artistas artista = new artistas();
            artista.setNombre_artista(nombre);
            artista.setDescripcion(descripcion);
            artistasDAO.insert(artista);
            return Response.status(201).entity(artista).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(500).entity("Error al insertar artista").build();
        }
    }
    
    
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateArtista(@PathParam("id") int id, @FormParam("nombre_artista") String nombre, @FormParam("descripcion") String descripcion ) {
        try {
        	artistas artista = new artistas();
            artista.setId_artista(id);
            artista.setDescripcion(descripcion);
            artista.setNombre_artista(nombre);
            artistasDAO.update(artista);
            return Response.status(200).entity(artista).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(500).entity("Error al actualizar artista").build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteArtista(@PathParam("id") int id) {
        try {
            artistasDAO.delete(id);
            return Response.status(200).entity("Artista eliminado").build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(500).entity("Error al eliminar artista").build();
        }
    }
}
