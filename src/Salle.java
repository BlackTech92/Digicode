
public class Salle {

	private int idSalle;
	private String typeSalle;
	private String code;
	private int etage;
	private String adresse;
	private String ville;
	private int codePostale;
	private int capaciteMax;

	public Salle(int idSalle, String typeSalle, String code, int etage, String adresse, String ville, int codePostale,
			int capaciteMax) {
		this.idSalle = idSalle;
		this.typeSalle = typeSalle;
		this.code = code;
		this.etage = etage;
		this.adresse = adresse;
		this.ville = ville;
		this.codePostale = codePostale;
		this.capaciteMax = capaciteMax;
	}

	@Override
	public String toString() {
		return "Salle n°" + idSalle;
	}

	public int getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}

	public String getTypeSalle() {
		return typeSalle;
	}

	public void setTypeSalle(String typeSalle) {
		this.typeSalle = typeSalle;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getEtage() {
		return etage;
	}

	public void setEtage(int etage) {
		this.etage = etage;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getCodePostale() {
		return codePostale;
	}

	public void setCodePostale(int codePostale) {
		this.codePostale = codePostale;
	}

	public int getCapaciteMax() {
		return capaciteMax;
	}

	public void setCapaciteMax(int capaciteMax) {
		this.capaciteMax = capaciteMax;
	}

}
