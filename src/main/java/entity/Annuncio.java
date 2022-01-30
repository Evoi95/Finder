package entity;

public class Annuncio {

    private int idAnnuncio;
    private String nomeAnnuncio;
    private String breveDescrizione;
    private String lungaDescrizione;
    private String gioco;
    private int voiceChat;

    public int getIdAnnuncio() {
        return idAnnuncio;
    }

    public void setIdAnnuncio(int idAnnuncio) {
        this.idAnnuncio = idAnnuncio;
    }

    public String getNomeAnnuncio() {
        return nomeAnnuncio;
    }

    public void setNomeAnnuncio(String nomeAnnuncio) {
        this.nomeAnnuncio = nomeAnnuncio;
    }

    public String getBreveDescrizione() {
        return breveDescrizione;
    }

    public void setBreveDescrizione(String breveDescrizione) {
        this.breveDescrizione = breveDescrizione;
    }

    public String getLungaDescrizione() {
        return lungaDescrizione;
    }

    public void setLungaDescrizione(String lungaDescrizione) {
        this.lungaDescrizione = lungaDescrizione;
    }

    public String getGioco() {
        return gioco;
    }

    public void setGioco(String gioco) {
        this.gioco = gioco;
    }


    public int getVoiceChat() {
        return voiceChat;
    }

    public void setVoiceChat(int voiceChat) {
        this.voiceChat = voiceChat;
    }



}
