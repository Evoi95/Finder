package database;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import logger.Log;

public class CreateDefaultDB 
{

	public static void createDefaultDB () throws SQLException, FileNotFoundException
	{
		Statement st = null ;
		String query ;
		boolean status=false;

		try 
		{
			status = ConnToDb.initailConnection() && !ConnToDb.connection() ; 
			// status = 1 se c'e' workbench ma non il db 
			// status = 0 se c'e' tutto
			// Se non trovo il db chiamo questa funzione che lo crea
			if(status) 
			{
				st = ConnToDb.conn.createStatement();
				query="CREATE DATABASE IF NOT EXISTS finder ";
				st.execute(query);
				query = "USE finder ";
				st.execute(query);
				Log.logger.log(Level.INFO,"Connesso a mysql workbench, ma non ho torvato il database 'ispw'"
						+ "Database creato "
						+ " Chiamo la Stored Procedure, per creare le tabelle");

				query=	"CREATE TABLE IF NOT EXISTS UTENTI" +
						"(idUser INT primary key not null auto_increment," +
						"Nome VARCHAR(40), " +
						"Email VARCHAR(50), pwd VARCHAR(16)," +
						"NickName VARCHAR(10)," +
						"idRuolo VARCHAR(1) NOT NULL DEFAULT 'U') ; ";
				st.executeUpdate(query);

				query= "CREATE TABLE IF NOT EXISTS GAMELIST" +
						"(" +
						"idGame INT primary key not null auto_increment," +
						"nomeGioco VARCHAR(100) not null" +
						");";
				st.executeUpdate(query);
				query = "CREATE table if not exists annuncio" +
						"(" +
						" idAnnucio int primary key not null auto_increment," +
						" nomeAnnuncio VARCHAR(100), " +
						" shortDescription varchar(100), longDescription text," +
						" voiceChat int ," +
						" gameC int not null, creator int not null," +
						" foreign key (creator) references UTENTI(idUser)," +
						" foreign key (gameC) references GAMELIST(idgame)" +
						");";
				st.executeUpdate(query);



				Log.logger.log(Level.INFO,"Tabelle create!");

				if (PopulateDefaultDb.populateDefaultDb()) {
					Log.logger.log(Level.INFO,"Tabella populata con valori di default");
					if (Boolean.TRUE.equals(status))
					{
						Log.logger.log(Level.INFO,"Trigger creati e connesione chiusa col db");
						Log.logger.log(Level.INFO,"Trovato database e connesso senza problemi! Buone madonne!");
						ConnToDb.conn.close();
					}
					else
					{
						Log.logger.log(Level.WARNING,"Ops..! qualcosa è andato storto nella creazione dei trigger !");
					}
				}
				else
				{
					Log.logger.log(Level.WARNING,"Ops..! qualcosa è andato storto nel populare il database!");
				}
			}
			
			// Se trovo tutto  chiudo la connesione e vado avanti con l'esecuzione del programma
			
			// Se qualcosa non va chiudo la connessione e vado nel cacth
			else 
			{
				Log.logger.log(Level.WARNING,"Ops..! qualcosa è andato storto nella connesione al database!");
				ConnToDb.conn.close();		

			}
		}
		catch(SQLException e1) 
		{
			e1.printStackTrace();
			Log.logger.log(Level.WARNING,"ERRORE DI SQL ");
		}
		finally {
			ConnToDb.conn.close();
		}

		
		
	}
	

	private CreateDefaultDB() {
		Log.logger.log(Level.INFO,"Creo db di default");

		
	}
}
