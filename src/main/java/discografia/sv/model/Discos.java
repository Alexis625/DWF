package discografia.sv.model;

public class Discos {
	
	
	private int id_discos;
	private String nombre_disco;
	private int id_artista;
	private int num_canciones;
	private double precio;
	private artistas artistas;
	
	
	public int getId_discos() {
		return id_discos;
	}
	public void setId_discos(int id_discos) {
		this.id_discos = id_discos;
	}
	public String getNombre_disco() {
		return nombre_disco;
	}
	public void setNombre_disco(String nombre_disco) {
		this.nombre_disco = nombre_disco;
	}
	public int getId_artista() {
		return id_artista;
	}
	public void setId_artista(int id_artista) {
		this.id_artista = id_artista;
	}
	public int getNum_canciones() {
		return num_canciones;
	}
	public void setNum_canciones(int num_canciones) {
		this.num_canciones = num_canciones;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public artistas getArtistas() {
		return artistas;
	}
	public void setArtistas(artistas artistas) {
		this.artistas = artistas;
	}
	
}
