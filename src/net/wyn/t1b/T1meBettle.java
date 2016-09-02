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

package net.wyn.t1b;

import net.wyn.t1b.ui.Dispatcher;
import net.wyn.t1b.ui.AbstractCommand;

public class T1meBettle {
    public static void main(final String args[]) {
	final Dispatcher dispatcher = new Dispatcher();
	final AbstractCommand command = dispatcher.dispatch(args);
	command.execute();
    }
}
