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
package net.wyn.t1b.core.serializers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.wyn.t1b.core.TrackerFile;

public class TrackerSerializer {
   public boolean exists(final String filePath) {
       return new File(filePath).exists();
   }
   
   public TrackerFile load(final String filePath) throws IOException {
       final FileInputStream fis = new FileInputStream(filePath);
       final ObjectInputStream ois = new ObjectInputStream(fis);
       try {
	   final TrackerFile trackerFile = (TrackerFile) ois.readObject();
	   trackerFile.setTrackerFile(new File(filePath));
	   return trackerFile;
       } catch (final ClassNotFoundException ex) {
	   System.out.println("Bad format for the tracker's information.");
	   ex.printStackTrace(System.err);
	   return new TrackerFile(filePath);
       }
   }
   
    public void save(final TrackerFile trackerFile) throws IOException {
	final FileOutputStream fos = new FileOutputStream(trackerFile.getFilePath());
	final ObjectOutputStream oos = new ObjectOutputStream(fos);
	oos.writeObject(trackerFile);
   }
}
