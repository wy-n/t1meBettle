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
package net.wyn.t1b.ui.commands;

import net.wyn.t1b.ui.AbstractCommand;
import net.wyn.t1b.core.Tracker;
import net.wyn.t1b.core.exception.TrackerAlreadyExistsException;

public class InitCommand extends AbstractCommand {
   @Override
   public String getName() {
      return "init";
   }

   @Override
   public void execute(final String[] args) {
       final Tracker tracker = new Tracker(System.getProperty("user.dir"));
       try {
	   tracker.create();
	   System.out.println("This folder is now under tracking.");
       } catch (final TrackerAlreadyExistsException ex) {
	   System.out.println("This folder, or one of its parent, is already under tracking.");
       }
   }
}
