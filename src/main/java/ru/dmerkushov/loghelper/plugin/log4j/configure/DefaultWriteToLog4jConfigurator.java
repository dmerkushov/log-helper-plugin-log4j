/*
 * Copyright 2014 dmerkushov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.dmerkushov.loghelper.plugin.log4j.configure;

import java.util.logging.Handler;
import java.util.logging.Logger;
import org.w3c.dom.Node;
import ru.dmerkushov.loghelper.LoggerWrapper;
import ru.dmerkushov.loghelper.configure.loggerwrapper.LoggerWrapperConfigurator;
import ru.dmerkushov.loghelper.handler.DailyRollingFileHandler;
import ru.dmerkushov.loghelper.plugin.log4j.connect.WriteJulToLog4jHandler;

/**
 * Configures LoggerWrapper to use only WriteJulToLog4jHandler
 *
 * @author Dmitriy Merkushov
 */
public class DefaultWriteToLog4jConfigurator extends LoggerWrapperConfigurator {
	
	public DefaultWriteToLog4jConfigurator (LoggerWrapper loggerWrapper, Node configuration) {
		super (loggerWrapper, configuration);
	}


	/**
	 * Configures LoggerWrapper to pass all messages to Apache Log4j.
	 * @return always <code>true</code> (means the call is ever succesful)
	 *
	 * @see DailyRollingFileHandler#DailyRollingFileHandler(java.lang.String)
	 * @see DailyRollingFileHandler#DailyRollingFileHandler()
	 */
	@Override
	public boolean configure () {
		Logger logger = getLoggerWrapper ().getLogger ();
		Handler[] handlers = logger.getHandlers ();

		for (Handler handler : handlers) {
			logger.removeHandler (handler);
		}
		
		WriteJulToLog4jHandler wjtl4j = new WriteJulToLog4jHandler ();
		
		logger.addHandler (wjtl4j);
		
		return true;
	}

}
