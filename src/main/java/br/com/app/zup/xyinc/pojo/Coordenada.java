package br.com.app.zup.xyinc.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Guilherme Parreira
 *
 */
@Entity
@Table(name="coordenada")
public class Coordenada implements Serializable{

	private static final long serialVersionUID = 3031847342083223097L;
	
	
	
	public Coordenada() {
	}
	
	public Coordenada(@NotNull(message = "Obrigatório informar o Nome") String nome,
			@NotNull(message = "Obrigatório informar a Latitude") Integer latitude,
			@NotNull(message = "Obrigatório informar a Longitude") Integer longitude) {
		this.nome = nome;
		this.latitude = latitude;
		this.longitude = longitude;
	}



	@Id
	@Column(name="id_coordenada", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCoordenada;
	
	@Column(name="nome", nullable=false)
	@NotNull(message="Obrigatório informar o Nome")
	private String nome;
	
	@Column(name="latitude", nullable=false)
	@NotNull(message="Obrigatório informar a Latitude")
	private Integer latitude;
	
	@Column(name="longitude", nullable=false)
	@NotNull(message="Obrigatório informar a Longitude")
	private Integer longitude;
	
	
	public Long getIdCoordenada() {
		return idCoordenada;
	}
	public void setIdCoordenada(Long idCoordenada) {
		this.idCoordenada = idCoordenada;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getLatitude() {
		return latitude;
	}
	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}
	public Integer getLongitude() {
		return longitude;
	}
	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getIdCoordenada() == null) ? 0 : getIdCoordenada().hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Coordenada))
			return false;
		Coordenada other = (Coordenada) obj;
		if (getIdCoordenada() == null) {
			if (other.getIdCoordenada() != null)
				return false;
		} else if (!getIdCoordenada().equals(other.getIdCoordenada()))
			return false;
		return true;
	}
	
	
	
	
}
