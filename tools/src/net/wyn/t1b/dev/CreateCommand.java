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

package net.wyn.t1b.dev;

import java.util.Scanner;

public class CreateCommand {
    public static void main(final String[] args) {
	/* TODO : ask user for the new command name */
	/* TODO : check the format of the command's name. */
	/* TODO : check if this command not already exists */
	/* TODO : if not, create the cmd class */
	/* TODO : modify the Makefile for including the class */
	Scanner scanner = new Scanner(System.in);
	System.out.print("New command's name ? > ");
	final String cmd = scanner.next();
	System.out.println("Command name is : " + cmd);
    }
}
