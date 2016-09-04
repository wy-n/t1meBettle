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
import java.util.ArrayList;
import java.util.List;

public class TrackerFile {
   public static final String T1B_FILENAME = "infos.t1b";
   private transient File m_trackerFile;
   private List<String> m_versionList;
    
   public TrackerFile(final String trackerPath) {
      this.m_trackerFile = new File(trackerPath, T1B_FILENAME);
   }

   public void addVersion(final String versionName) {
      if (this.m_versionList.contains(versionName)) {
         // A Set will erase the data
         throw new IllegalArgumentException("This version already exists.");
      }

      this.m_versionList.add(versionName);
   }
}
