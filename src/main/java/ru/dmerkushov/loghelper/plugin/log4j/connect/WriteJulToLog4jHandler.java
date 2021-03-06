/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmerkushov.loghelper.plugin.log4j.connect;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import ru.dmerkushov.loghelper.plugin.log4j.LogHelperLog4jUtils;

/**
 * Simple handler for sending the logging to Log4j logging system
 *
 * @author Dmitriy Merkushov
 */
public class WriteJulToLog4jHandler extends Handler {
	
	@Override
	public synchronized void publish (LogRecord record) {
		if (!isLoggable (record)) {
			return;
		}

		org.apache.log4j.Logger log4jLogger = org.apache.log4j.Logger.getLogger (record.getLoggerName () + "_" + toStringAsObject ());
		
		org.apache.log4j.Level log4jLevel = LogHelperLog4jUtils.getLog4jLevelFromJUL (record.getLevel ());
		
		Throwable thrown = record.getThrown ();
		
		String message = getFormatter ().format (record);
		
		if (thrown == null) {
			log4jLogger.log (log4jLevel, message);
		} else {
			log4jLogger.log (log4jLevel, message, thrown);
		}
	}

	@Override
	public void flush () {
	}

	@Override
	public void close () throws SecurityException {
	}
	
	/**
	 * Returning the same result as {@link java.lang.Object}'s toString() method,
	 * because there may be ancestors of WriteJulToLog4jHandler
	 * @return the same as {@link java.lang.Object}'s toString() method
	 */
	public String toStringAsObject () {
		return getClass().getName() + "@" + Integer.toHexString(hashCode());
	}
}
