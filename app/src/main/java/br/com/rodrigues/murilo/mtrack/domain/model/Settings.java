package br.com.rodrigues.murilo.mtrack.domain.model;

public class Settings {
    private int idSettings;
    private String transporterCode;
    private String urlEdata;

    public Settings() { }

    public int getIdSettings() {
        return idSettings;
    }

    public void setIdSettings(int idSettings) {
        this.idSettings = idSettings;
    }

    public String getTransporterCode() {
        return transporterCode;
    }

    public void setTransporterCode(String transporterCode) {
        this.transporterCode = transporterCode;
    }

    public String getUrlEdata() {
        return urlEdata;
    }

    public void setUrlEdata(String urlEdata) {
        this.urlEdata = urlEdata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Settings settings = (Settings) o;

        return idSettings == settings.idSettings;

    }

    @Override
    public int hashCode() {
        return idSettings;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "idSettings=" + idSettings +
                ", transporterCode='" + transporterCode + '\'' +
                ", urlEdata='" + urlEdata + '\'' +
                '}';
    }
}
