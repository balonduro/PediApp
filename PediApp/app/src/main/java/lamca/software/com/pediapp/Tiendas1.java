package lamca.software.com.pediapp;

/**
 * Created by CarlosAugusto on 16/04/2015.
 */
public class Tiendas1 {

    private String idEmpresa;
    private String nomEmpresa;

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNomEmpresa() {
        return nomEmpresa;
    }

    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    @Override
    public String toString() {
        return nomEmpresa;
    }
}
