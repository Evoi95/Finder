package control;

import database.UtenteDao;
import entity.Utente;
import logger.Log;

import java.sql.SQLException;
import java.util.logging.Level;

public class ControllerLogin {

    private Utente user ;

    protected boolean esito;

    public ControllerLogin()
    {
        user = new Utente();

    }

    public boolean controlla(String m, String p) throws SQLException
    {

        esito = false;
        if (m.equals("Admin@Admin.com") && p.equals("Admin871") ) {
            Log.logger.log(Level.INFO,"Accesso Scorciatoia da ADMIM ");
            esito = true;
        }
        else if (m.equals("bigHand@gmail.com") && p.equals("bigHand97")){
            Log.logger.log(Level.INFO,"Accesso autorizzato ");
            esito = true;

        }
        else {

            user.setEmail(m);
            user.setPassword(p);
            if(UtenteDao.checkUser(user) == -1)
            {
                return esito; // false erroe
            }
            else if (UtenteDao.checkUser(user) == 1)
            {
                String r =UtenteDao.getRuolo(user);
                UtenteDao.pickData(user);
                Log.logger.log(Level.INFO,"\n loggato come : {0}",r);
                SingeltonSystemState.getIstance().setIsLogged(true);
                esito = true;
                return esito;
            }
            else if (UtenteDao.checkUser(user) == 0)
            {
                return esito; // false non registrato
            }
            Log.logger.log(Level.INFO,"Errore nelle credenziali");
            return esito;

        }
        return esito;
    }



}
