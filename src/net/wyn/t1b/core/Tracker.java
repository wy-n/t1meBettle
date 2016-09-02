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
package net.wyn.t1b.core;

import java.io.File;
import java.io.IOException;

import net.wyn.t1b.core.exception.TrackerAlreadyExistsException;

public class Tracker {
    private static final String T1B_FOLDER = ".t1meBettle";
    private String m_trackerParent;
    
    public Tracker(final String cwd) {
	this.m_trackerParent = "";
	
	File currentFile = new File(cwd);
	do {
	    currentFile = new File(currentFile, T1B_FOLDER);
	    if (currentFile.exists() && currentFile.isDirectory()) {
		try {
		    this.m_trackerParent = currentFile.getCanonicalPath();
		} catch (final IOException ex) {
		    System.err.println("Unable to get the directory's information");
		    ex.printStackTrace(System.err);
		}
	    }
	    currentFile = currentFile.getParentFile().getParentFile();
	} while (this.m_trackerParent.isEmpty() && null != currentFile);

	// If the trackerParent is empty, then the wanted directory is
	// considered as the parent
	if (this.m_trackerParent.isEmpty()) {
	    this.m_trackerParent = cwd;
	}
    }

    public void create() throws TrackerAlreadyExistsException {
	if (this.m_trackerParent.endsWith(T1B_FOLDER)) {
	    throw new TrackerAlreadyExistsException();
	}
	
	final File trackerDir = new File(this.m_trackerParent, T1B_FOLDER);
	trackerDir.mkdir();
    }
}