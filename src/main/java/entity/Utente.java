package entity;

public class Utente {

    private int idUser;
    private String name;
    private String email;
    private String password;
    private String nickName;
    private String role;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRuolo() {
        return role;
    }

    public void setRuolo(String role) {
        this.role = role;
    }

    public Utente() {
        this.idUser = -1;
        this.name =null;
        this.email=null;
        this.password=null;
        this.nickName=null;
        this.role =null;
    }

    public Utente(int id, String nome, String emails, String passwords, String nickname, String ruolo) {
        this.idUser = id;
        this.name = nome;
        this.email= emails;
        this.password= passwords;
        this.nickName= nickname;
        this.role=  ruolo;
    }



}
