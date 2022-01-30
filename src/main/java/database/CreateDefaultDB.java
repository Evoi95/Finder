package database;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import logger.Log;

public class CreateDefaultDB 
{
	private static Statement st = null ;
	private static String query ;
	 private static String creaEditore="Create table if not exists EDITORE("
				+ "idEditor int primary key not null auto_increment,"
				+ "idUser int, casaEditrice VARCHAR (200), "
				+ "FOREIGN KEY (idUser) REFERENCES USERS(idUser));";
	 private static String creaScrittore="Create table  if not exists SCRITTORI("
				+ "idScrittore int primary key not null auto_increment,"
				+ "idUser int, editoreAssociato int, "
				+ "FOREIGN KEY (editoreAssociato) REFERENCES EDITORE(idEditor) ,"
				+ "FOREIGN KEY (idUser) REFERENCES USERS(idUser) );";
	 private static String creaGiornale="Create table if not exists GIORNALE("
				+ "titolo VARCHAR(200),tipologia Varchar(60),"
				+ "lingua varchar(10),"
				+ "editore varchar(200) ,"
				+ "dataPubblicazione date,"
				+ "copiRim int, "
				+ "disp int,"
				+ "prezzo float,"
				+ "id int primary key not null auto_increment);";
	 private static String creaRivista="Create table if not exists RIVISTA("
				+ "titolo VARCHAR(200),tipologia Varchar(60),"
				+ "autore varchar(200), lingua varchar(10),"
				+ "editore varchar(200) ,"
				+ "Descrizione text, dataPubblicazione date,"
				+ "disp int,"
				+ "prezzo float,"
				+ "copieRimanenti int,"
				+ "id int primary key not null auto_increment);";
	 private static String creaCartaCredito="Create table if not exists cartacredito ( "
				+ "nomeP VARCHAR(10),cognomeP  Varchar(20),"
				+ "codiceCarta varchar(20),"
				+ "scad date ,"
				+ "codicePin varchar(5) ,"
				+ "ammontare float );";
	 private static String creaFattura="Create table if not exists FATTURA("
				+ "nome varchar(10),cognome varchar(10),"
				+ "via varchar(50),"
				+ "comunicazoni text,"
				+ "id int auto_increment not null  primary key,"
				+ "ammontare float);";
private static String creaAmministratore="Create table  if not exists AMMINISTRATORE("
		+ "idAdmin int primary key not null auto_increment,"
		+ "idUser int,"
		+ "FOREIGN KEY (idUser) REFERENCES USERS(idUser));";
private static String creaLibro="Create table  if not exists LIBRO("
		+ "titolo VARCHAR(200), numeroPagine int,"
		+ "Cod_isbn varchar(10) not null unique,"
		+ "editore varchar(200),"
		+ "autore varchar(200), lingua varchar(10),"
		+ "categoria Varchar(60), dataPubblicazione date,"
		+ "recensione text, copieVendute int, breveDescrizione text,"
		+ "disp int, prezzo float,"
		+ "copieRimanenti int,"
		+ "idProd int primary key auto_increment);";

private static String creaPagamento="create table if not exists pagamento("
		+ "id_op int primary key auto_increment,"
		+ "metodo varchar(10),esito int ,"
		+ "nomeUtente varchar(10),spesaTotale float,"
		+ "eMail varchar(100 ),"
		+ "tipoAcquisto varchar(20),"
		+ "idProd int )";



	
	public static void createDefaultDB () throws SQLException, FileNotFoundException
	{
		boolean status=false;
		try 
		{
			status = ConnToDb.initailConnection() && !ConnToDb.connection() ; 
			// status = 1 se c'e' workbench ma non il db 
			// status = 0 se c'e' tutto
			// Se non trovo il db chiamo questa funzione che lo crea
			if(status) 
			{
				//
				st = ConnToDb.conn.createStatement();
				query="CREATE DATABASE IF NOT EXISTS ispw ";
				st.execute(query);
				query = "USE ispw ";
				st.execute(query);
				Log.logger.log(Level.INFO,"Connesso a mysql workbench, ma non ho torvato il database 'ispw'"
						+ "Database creato "
						+ " Chiamo la Stored Procedure, per creare le tabelle");
				
				query=	"CREATE TABLE if not exists USERS(idUser INT primary key not null auto_increment,idRuolo VARCHAR(1) NOT NULL DEFAULT 'U',Nome VARCHAR(40),Cognome VARCHAR(40),Email VARCHAR(50) UNIQUE ,pwd VARCHAR(16),descrizione text,DataDiNascita date);";
				st.executeUpdate(query);
				
				st.executeUpdate(creaAmministratore);			
					
				st.executeUpdate(creaEditore);	
				
				st.executeUpdate(creaScrittore);				
				
				st.executeUpdate(creaLibro);				
						
				st.executeUpdate(creaPagamento);
				
				st.executeUpdate(creaRivista);
				
				st.executeUpdate(creaGiornale);
				
				st.executeUpdate(creaCartaCredito);
				
				st.executeUpdate(creaFattura);
			
				query= "Create table if not exists NEGOZIO"
						+ "( idNegozio int not null auto_increment primary key, "
						+ "nome VARCHAR(100) , via VARCHAR(100),"
						+ " isValid boolean, isOpen BOOLEAN"
						+ ");";
				st.executeUpdate(query);

				Log.logger.log(Level.INFO,"Tabelle create!");
				
				if (PopulateDefaultDb.populateDefaultDb()) {
					Log.logger.log(Level.INFO,"Tabella populata con valori di default");
					if (Boolean.TRUE.equals(status))
					{
						ConnToDb.conn.close();
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
		
		
	}
	
	public static boolean createTrigger() throws SQLException 
	{
			 String qTrigger ;




		try 
		{		st = ConnToDb.conn.createStatement();

				query = "USE ispw ";
				st.execute(query);
				//0 regolare 1 irregolare
				qTrigger= "delimiter //"
						+ "create trigger pagaFattura after insert on fattura "
						+ "for each row "
						+ "begin  "
						+ "insert into  pagamento values(0,'fattura',0,new.nome,new.ammontare); "
						+ "end; //";
				
				 st = ConnToDb.conn.prepareStatement(qTrigger);

				
				Log.logger.log(Level.INFO,"Trigger pagamento triggerato");
				
				qTrigger= "delimiter //"
						+ "create trigger pagaCartaCredito after insert on cartacredito "
						+ "for each row "
						+ "begin "
						+ "insert into  pagamento values(0,'cartac',0,new.nomeP,new.ammontare);"
						+ "end; //";
				st = ConnToDb.conn.prepareStatement(qTrigger);	
				st.execute(qTrigger);	
				Log.logger.log(Level.INFO,"Trigger cartaDiCredito triggerato");
				// TO DO : Mancano altri trigger degli utenti etc...
				st.close();
				return true;
			
		}
		catch(SQLException e1) 
		{
			e1.printStackTrace();
			Log.logger.log(Level.WARNING,"ERRORE DI SQL ");
		}
		finally {
			st.close();
		}
		
		return false;
	}
	private CreateDefaultDB() {
		Log.logger.log(Level.INFO,"Creo db di default");

		
	}
}
