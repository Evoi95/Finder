package database;

import entity.Utente;
import logger.Log;

import java.sql.*;
import java.util.logging.Level;

public class UtenteDao {

    private static Statement st ;
    private static String query ;
    private static ResultSet rs;
    private static Connection conn;
    private static String r;
    private static boolean state;
    private static PreparedStatement prepQ;
    private static String useDb="USE Finder;";

    public static boolean createUtente(Utente u) throws SQLException {

        try
        {
            if (ConnToDb.connection())
            {
                conn = ConnToDb.generalConnection();
                st=conn.createStatement();
                query=useDb;
                st.executeQuery(query);
                query= "INSERT INTO `finder`.`utenti`" +
                        "(`Nome`," +
                        "`Email`," +
                        "`pwd`," +
                        "`NickName`)" +
                        " Values (?,?,?,?);";
                prepQ = ConnToDb.conn.prepareStatement(query);
                prepQ.setString(1,u.getName());
                prepQ.setString(2,u.getEmail());
                prepQ.setString(3,u.getPassword());
                prepQ.setString(4,u.getNickName());
                prepQ.executeUpdate();

                state= true;
            }
            else {
                Log.logger.log(java.util.logging.Level.SEVERE,"Errore in inserimento utente");
            }
        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
        // errore
        finally {
            conn.close();
        }
        return state;
    }

    public boolean modifUtente()
    {
        return false;
    }

    public static boolean deleteUtente(Utente user)
    {
        String email = user.getEmail();
        String ruolo = user.getRuolo();
        try
        {
            if (ConnToDb.connection()  && ruolo.equals("U"))
            {
                conn = ConnToDb.generalConnection();
                st=conn.createStatement();
                query=useDb;
                st.executeQuery(query);
                query="DELETE FROM users WHERE Email = '"+ email +"'";
                st.executeUpdate(query);
                Log.logger.log(Level.INFO,"cancello utente user .{0}" ,user.getIdUser());
                return true;

            }
            else if (ConnToDb.connection()  && ruolo.equals("a"))
            {
                conn = ConnToDb.generalConnection();
                st=conn.createStatement();
                query=useDb;
                st.executeQuery(query);
                query="DELETE FROM utenti WHERE idUser = '"+user.getIdUser() +"'";
                st.executeUpdate(query);
                Log.logger.log(Level.INFO,"cancello utente admin .{0}" ,user.getIdUser());

                return true;
            }
        }
        catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return false;
    }

    public static int checkUser(Utente u) throws SQLException
    {
        String email = u.getEmail();
        int id;
        try
        {
            conn = ConnToDb.generalConnection();
            st=conn.createStatement();
            query=useDb;
            st.executeQuery(query);
            query="SELECT idUser FROM ispw.users where Email = '"+email+"' ;";
            rs = st.executeQuery(query);
            if(rs.next())
            {
                id=rs.getInt(1);
                if(id>0)
                {
                    Log.logger.log(Level.INFO,"utente trovato .{0}",u.getEmail());
                    return 1;
                }
            }
            else
            {
                return 0; // false
            }
        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
        finally {
            conn.close();
        }
        return -1 ;
    }


    public static Utente pickData(Utente u)
    {
        String email = u.getEmail();
        try
        {

            conn = ConnToDb.generalConnection();
            st=conn.createStatement();
            query=useDb;
            st.executeQuery(query);
            query="SELECT `idRuolo`,"
                    + "    `Nome`,"
                    + "    `Email`,"
                    + "    `pwd`,"
                    + "    `NickName`,"
                    + "FROM users where Email = '"+email+"' ;";
            rs = st.executeQuery(query);
            while(rs.next())
            {
                // setto i vari dati
                u.setIdUser(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setNickName(rs.getString(5));
                return u;

            }
        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
        finally{
            try {
                conn.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        // errore
        return u;
    }

    public static String getRuolo (Utente u)
    {

        String email = u.getEmail();
        try
        {

            conn = ConnToDb.generalConnection();
            st=conn.createStatement();
            query=useDb;
            st.executeQuery(query);
            query="SELECT idRuolo FROM finder.utenti where Email = '"+email+"' ;";
            rs = st.executeQuery(query);
            if(rs.next())
            {
                r =rs.getString(1);
                u.setRuolo(r);

            }
            else
            {
                return null; // Errore
            }


        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
        // errore
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.getMessage();


            }
        }

        return r;

    }



}
