/*******************************************************************************
*                                 t1meBettle
*                           A command-line bugtracker
*
*    Copyright (C) 2016 by Sylvain Nieuwlandt
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*******************************************************************************/
package net.wyn.t1b.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Dispatcher {
    private String m_classesRootPath;
    private List<AbstractCommand> m_availableCommands;

    public Dispatcher() {
	this.m_availableCommands = new ArrayList<AbstractCommand>();

	Enumeration<URL> urls = null;
	try {
	    urls = getClass().getClassLoader().getResources("");
	    if (null != urls && urls.hasMoreElements()) {
		this.m_classesRootPath = urls.nextElement().getFile();
		this.loadClassesOf(this.m_classesRootPath);
	    }
	} catch (final IOException ex) {
	    System.err.println("Unable to load the commands.");
	    ex.printStackTrace(System.err);
	}
    }
    
    public AbstractCommand dispatch(final String[] args) {
	AbstractCommand result = new UsageCommand();

	if (args.length > 0) {
	    for (final AbstractCommand cmd : this.m_availableCommands) {
		if (cmd.getName().equals(args[0])) {
		    result = cmd;
		    break;
		}
	    }
	}
	
	return result;
    }

    private void loadClassesOf(final String path) throws IOException {
	final File pathFile = new File(path);
	for (final File subfile : pathFile.listFiles()) {
	    if (subfile.isDirectory()) {
		this.loadClassesOf(subfile.getCanonicalPath());
	    } else if (subfile.getName().endsWith(".class")) {
		String className = subfile.getCanonicalPath();
		className = className.replace(this.m_classesRootPath, "");
		className = className.replace(".class", "");
		className = className.replace(File.separatorChar, '.');
		try {
		    final Class<?> clazz = getClass().getClassLoader().loadClass(className);
		    if (!AbstractCommand.class.equals(clazz) && AbstractCommand.class.isAssignableFrom(clazz)) {
			this.m_availableCommands.add((AbstractCommand) clazz.newInstance());
		    }
		} catch (final ReflectiveOperationException ex) {
		    System.err.println("Unable to load command.");
		    ex.printStackTrace(System.err);
		}
	    }
	}
    }
}
