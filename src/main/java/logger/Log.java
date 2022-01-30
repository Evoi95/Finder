package logger;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

	public static final Logger logger = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );
	private FileHandler fh;
	private SimpleFormatter formatter;
	private ConsoleHandler cH;
	
	public Log() 
	{
		LogManager.getLogManager().reset();
		logger.setLevel(Level.ALL);
		
		cH = new ConsoleHandler();
		cH.setLevel(Level.SEVERE);
		logger.addHandler(cH);
		
		
		try {
			fh = new FileHandler(Logger.GLOBAL_LOGGER_NAME,true);
		}
		catch (SecurityException |IOException e) 
		{
			logger.warning("No handler file! : "+e);
		}
		logger.addHandler(fh);
		 formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
		
	} 
	
	
}
