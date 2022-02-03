package entity;

public class Annuncio {

    private int idAnnuncio;
    private String nomeAnnuncio;
    private String breveDescrizione;
    private String lungaDescrizione;
    private String gioco;
    private int voiceChat;
    private int creator;

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

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public Annuncio()
    {
        idAnnuncio = -1;
        nomeAnnuncio = null;
        breveDescrizione = null;
        lungaDescrizione = null;
        gioco = null;
        voiceChat = -1;
        creator = -1 ;
    }

    public Annuncio(int id,String name,String shortDesc,String longDesc, String game, int voice, int crea)
    {
        idAnnuncio = id;
        nomeAnnuncio = name;
        breveDescrizione = shortDesc;
        lungaDescrizione = longDesc;
        gioco = game;
        voiceChat = voice;
        creator = crea;
    }
}
