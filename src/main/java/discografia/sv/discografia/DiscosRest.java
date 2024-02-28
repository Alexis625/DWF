package discografia.sv.discografia;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import discografia.sv.model.Discos;
import discografia.sv.model.DiscosDAO;

@Path("discos")
public class DiscosRest {

    DiscosDAO discosDAO = new DiscosDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscos() {
        try {
            List<Discos> discos = discosDAO.findAll();
            return Response.status(200).entity(discos).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(500).entity("Error al obtener discos").build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscoById(@PathParam("id") int id) {
        try {
            Discos disco = discosDAO.findById(id);
            if (disco == null) {
                return Response.status(404).entity("Disco no encontrado").build();
            }
            return Response.status(200).entity(disco).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(500).entity("Error al obtener el disco").build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertDisco(@FormParam("nom_disco") String nom_disco, @FormParam("id_artista") int id_artista,
    		@FormParam("num_canciones") int num_canciones,@FormParam("precio") double precio) {
        try {
        	Discos discos=new Discos();
        	discos.setId_artista(id_artista);
			discos.setNom_disco(nom_disco);
        	discos.setNum_canciones(num_canciones);
        	discos.setPrecio(precio);
        	
            discosDAO.insert(discos);
            return Response.status(201).entity(discos).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(500).entity("Error al insertar disco").build();
        }
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDisco(@PathParam("id") int id, @FormParam("nom_disco") String nom_disco, @FormParam("id_artista") int id_artista,
    		@FormParam("num_canciones") int num_canciones,@FormParam("precio") double precio) {
        try {
        	Discos disco=new Discos();
            disco.setId_discos(id);
            disco.setId_artista(id_artista);
			disco.setNom_disco(nom_disco);
        	disco.setNum_canciones(num_canciones);
        	disco.setPrecio(precio);
            discosDAO.update(disco);
            return Response.status(200).entity(disco).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(500).entity("Error al actualizar disco").build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDisco(@PathParam("id") int id) {
        try {
            discosDAO.delete(id);
            return Response.status(200).entity("Disco eliminado").build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(500).entity("Error al eliminar disco").build();
        }
    }
}
