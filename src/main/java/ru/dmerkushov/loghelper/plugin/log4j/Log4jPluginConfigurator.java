/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmerkushov.loghelper.plugin.log4j;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.dmerkushov.loghelper.configure.PluginConfigurator;

/**
 *
 * @author Dmitriy Merkushov
 */
public class Log4jPluginConfigurator extends PluginConfigurator {
	
	Node configuration;

	@Override
	public void setConfiguration (Node configuration) {
		this.configuration = configuration;
	}

	@Override
	public boolean configurePlugin () {
		NodeList configSubnodes = configuration.getChildNodes ();
		
		boolean success = false;

		for (int subnodeIndex = 0; subnodeIndex < configSubnodes.getLength (); subnodeIndex++) {
			Node subnode = configSubnodes.item (subnodeIndex);

			String subnodeName = subnode.getNodeName ().trim ().toLowerCase ();

			if (subnodeName.equals ("config-xml")) {
				String log4jConfigFilename = subnode.getAttributes ().getNamedItem ("filename").getTextContent ();
				org.apache.log4j.xml.DOMConfigurator.configure (log4jConfigFilename);
				success = true;
			} else if (subnodeName.equals ("config-properties")) {
				String log4jConfigFilename = subnode.getAttributes ().getNamedItem ("filename").getTextContent ();
				org.apache.log4j.PropertyConfigurator.configure (log4jConfigFilename);
				success = true;
			} else if (subnodeName.equals ("config-basic")) {
				org.apache.log4j.BasicConfigurator.configure ();
				success = true;
			}
		}
		
		return success;
	}
}
