package discografia.sv.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DiscosDAO extends AppConnection {

	
	public void insert(Discos disco) throws SQLException {
		connect();
		try {
			pstmt = conn.prepareStatement("INSERT INTO discos (nombre_disco, id_artista, numero_canciones, precio) VALUES (?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, disco.getNom_disco());
			pstmt.setInt(2, disco.getId_artista());
			pstmt.setInt(3, disco.getNum_canciones());
			pstmt.setDouble(4, disco.getPrecio());
			pstmt.executeUpdate();

			ResultSet keys = pstmt.getGeneratedKeys();
			if (keys.next()) {
				int id = keys.getInt(1);
				disco.setId_discos(id);
			}
		} finally {
			close();
		}
	}
	
	

    public void update(Discos disco) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("UPDATE discos SET nombre_disco = ?, id_artista = ?, numero_canciones = ?, precio = ? WHERE id_disco = ?");
        pstmt.setString(1, disco.getNom_disco());
        pstmt.setInt(2, disco.getId_artista());
        pstmt.setInt(3, disco.getNum_canciones());
        pstmt.setDouble(4, disco.getPrecio());
        pstmt.setInt(5, disco.getId_discos());
        pstmt.executeUpdate();
        close();
    }

    public void delete(int id) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("DELETE FROM discos WHERE id_disco = ?");
        pstmt.setInt(1, id);
        pstmt.execute();
        close();
    }

    public List<Discos> findAll() throws SQLException {
        connect();
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery("SELECT id_disco, nombre_disco, id_artista, numero_canciones, precio FROM discos");
        ArrayList<Discos> discos = new ArrayList();

        while (resultSet.next()) {
            Discos disco = new Discos();
            disco.setId_discos(resultSet.getInt(1));
            disco.setNom_disco(resultSet.getString(2));
            disco.setId_artista(resultSet.getInt(3));
            disco.setNum_canciones(resultSet.getInt(4));
            disco.setPrecio(resultSet.getDouble(5));
            discos.add(disco);
        }

        close();
        return discos;
    }

    public Discos findById(int id) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("SELECT id_disco, nombre_disco, id_artista, numero_canciones, precio FROM discos WHERE id_disco = ?");
        pstmt.setInt(1, id);
        resultSet = pstmt.executeQuery();
        Discos disco = null;

        while (resultSet.next()) {
            disco = new Discos();
            disco.setId_discos(resultSet.getInt(1));
            disco.setNom_disco(resultSet.getString(2));
            disco.setId_artista(resultSet.getInt(3));
            disco.setNum_canciones(resultSet.getInt(4));
            disco.setPrecio(resultSet.getDouble(5));
        }

        close();
        return disco;
    }
}
