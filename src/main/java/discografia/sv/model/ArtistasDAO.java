package discografia.sv.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistasDAO extends AppConnection {

    /**
     * Inserta un nuevo artista en la base de datos.
     */
	public void insert(artistas artista) throws SQLException {
	    connect();
	    try {
	        pstmt = conn.prepareStatement("INSERT INTO artistas (nombre_artista, descripcion) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
	        pstmt.setString(1, artista.getNombre_artista());
	        pstmt.setString(2, artista.getDescripcion());
	        pstmt.executeUpdate();

	        ResultSet keys = pstmt.getGeneratedKeys();
	        if (keys.next()) {
	            int id = keys.getInt(1);
	            artista.setId_artista(id);
	        }
	    } finally {
	        close();
	    }
	}

	public void update(artistas artista) throws SQLException {
	    connect();
	    try {
	        pstmt = conn.prepareStatement("UPDATE artistas SET nombre_artista = ?, descripcion = ? WHERE id_artista = ?");
	        pstmt.setString(1, artista.getNombre_artista());
	        pstmt.setString(2, artista.getDescripcion());
	        pstmt.setInt(3, artista.getId_artista());
	        int rowsUpdated = pstmt.executeUpdate();
	        if (rowsUpdated == 0) {
	            throw new SQLException("La actualización falló, ningún registro fue actualizado.");
	        }
	    } finally {
	        close();
	    }
	}

    /**
     * Elimina un artista de la base de datos por su ID.
     *
     * @param id El ID del artista a eliminar.
     * @throws SQLException
     */
    public void delete(int id) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("DELETE FROM artistas WHERE id_artista = ?");
        pstmt.setInt(1, id);
        pstmt.execute();
        close();
    }

    /**
     * Obtiene todos los artistas de la base de datos.
     *
     * @return Una lista de objetos Artistas.
     * @throws SQLException
     */
    public List<artistas> findAll() throws SQLException {
        connect();
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery("SELECT * FROM artistas");
        List<artistas> artistasList = new ArrayList();

        while (resultSet.next()) {
            artistas artista = new artistas();
            artista.setId_artista(resultSet.getInt("id_artista"));
            artista.setNombre_artista(resultSet.getString("nombre_artista"));
            artista.setDescripcion(resultSet.getString("descripcion"));
            artistasList.add(artista);
        }

        close();
        return artistasList;
    }

    /**
     * Obtiene un artista de la base de datos por su ID.
     *
     * @param id El ID del artista a buscar.
     * @return El objeto Artistas encontrado o null si no se encuentra.
     * @throws SQLException
     */
    public artistas findById(int id) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("SELECT * FROM artistas WHERE id_artista = ?");
        pstmt.setInt(1, id);
        resultSet = pstmt.executeQuery();

        artistas artista = null;
        if (resultSet.next()) {
            artista = new artistas();
            artista.setId_artista(resultSet.getInt("id_artista"));
            artista.setNombre_artista(resultSet.getString("nombre_artista"));
            artista.setDescripcion(resultSet.getString("descripcion"));
        }

        close();
        return artista;
    }
}