/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmerkushov.loghelper.plugin.log4j;

import java.util.logging.Level;

/**
 *
 * @author Dmitriy Merkushov
 */
public class LogHelperLog4jUtils {

	/**
	 * Get a Log4j logging level the same as given JUL level
	 *
	 * @param level
	 * @return
	 */
	public static org.apache.log4j.Level getLog4jLevelFromJUL (java.util.logging.Level level) {
		org.apache.log4j.Level log4jLevel;
		if ((level.intValue () >= Level.SEVERE.intValue ()) && (level.intValue () < Level.OFF.intValue ())) {
			log4jLevel = org.apache.log4j.Level.ERROR;
		} else if (level.intValue () >= Level.WARNING.intValue ()) {
			log4jLevel = org.apache.log4j.Level.WARN;
		} else if (level.intValue () >= Level.INFO.intValue ()) {
			log4jLevel = org.apache.log4j.Level.INFO;
		} else if (level.intValue () >= Level.CONFIG.intValue ()) {
			log4jLevel = org.apache.log4j.Level.DEBUG;
		} else if (level.intValue () >= Level.FINEST.intValue ()) {
			log4jLevel = org.apache.log4j.Level.TRACE;
		} else if (level.intValue () < Level.FINEST.intValue ()) {
			log4jLevel = org.apache.log4j.Level.ALL;
		} else {
			log4jLevel = org.apache.log4j.Level.OFF;
		}
		return log4jLevel;
	}

	/**
	 * Get a JUL logging level the same as given Log4j level
	 *
	 * @param log4jLevel
	 * @return
	 */
	public static Level getJULLevelFromLog4j (org.apache.log4j.Level log4jLevel) {
		int log4jLevelInt = log4jLevel.toInt ();
		Level julLevel;
		if (log4jLevelInt >= org.apache.log4j.Level.FATAL_INT) {
			julLevel = Level.SEVERE;
		} else if (log4jLevelInt >= org.apache.log4j.Level.ERROR_INT) {
			julLevel = Level.SEVERE;
		} else if (log4jLevelInt >= org.apache.log4j.Level.WARN_INT) {
			julLevel = Level.WARNING;
		} else if (log4jLevelInt >= org.apache.log4j.Level.INFO_INT) {
			julLevel = Level.INFO;
		} else if (log4jLevelInt >= org.apache.log4j.Level.DEBUG_INT) {
			julLevel = Level.FINE;
		} else if (log4jLevelInt >= org.apache.log4j.Level.TRACE_INT) {
			julLevel = Level.FINER;
		} else {
			julLevel = Level.FINEST;
		}

		return julLevel;
	}
}
