package database;

import entity.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UtenteDao {

    private static Statement st = null ;
    private static String query ;
    private static ResultSet rs;
    private static PreparedStatement prepQ = null;
    private static Connection conn;
    private static int max;
    private static String r;
    private static boolean state=false;
    private static String useDb="USE ISPW;";
    private Utente u ;

    public boolean createUtente()
    {
        return false;
    }

    public boolean modifUtente()
    {
        return false;
    }

    public boolean deleteUtente(Utente user)
    {
        String email = user.getEmail();
        String ruolo=user.getIdRuolo();
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
                Log.logger.log(Level.INFO,"cancello utente user .{0}" ,user.getIdRuolo());
                return true;

            }
            else if (ConnToDb.connection()  && ruolo.equals("a"))
            {
                conn = ConnToDb.generalConnection();
                st=conn.createStatement();
                query=useDb;
                st.executeQuery(query);
                query="DELETE FROM users WHERE idUser = '"+user.getIdU() +"'";
                st.executeUpdate(query);
                Log.logger.log(Level.INFO,"cancello utente admin .{0}" ,user.getIdRuolo());

                return true;

            }



        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.getMessage();



            }
        }
        return false;
    }

    public Utente getData()
    {
        u.getRuolo();
        u.getName();
        u.getName();
        u.getEmail();
        u.getPassword();
        u.getIdUser();
        return u;
    }


}
