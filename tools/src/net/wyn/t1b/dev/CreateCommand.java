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

import java.io.File;
import java.util.Scanner;

public class CreateCommand {
    private static final String CMD_PKG_PATH = "src/net/wyn/t1b/ui/commands";

    public static void main(final String[] args) {
	/* TODO : modify the Makefile for including the class */
	Scanner scanner = new Scanner(System.in);
	System.out.print("New command's name ? > ");
	final String cmd = scanner.next();
	if (cmd.isEmpty() || cmd.contains(' ')) {
	    System.out.println("The command's name can't contains spaces.");
	    return;
	}
	final cmdClassName = this.transformToClassName(cmd);
	final File[] cmdClasses = new File(System.getProperty("user.dir"),
					   CMD_PKG_PATH).listFiles();
	
	for (final File cmdClass : cmdClasses) {
	    if (cmdClass.getName().equals(new StringBuilder(cmdClassName)
					  .append(".java").toString())) {
		System.out.println("A command with this name already exists.");
		return;
	    }
	}

	this.createCmdClass(cmd);
	this.updateMakefileWith(cmd);
    }

    private String transformToClassName(final String cmd) {
	final StringBuilder sbResult = new StringBuilder();
	boolean toUpper = false;
	sbResult.append(Character.toUpperCase(cmd.charAt(0)));
	for (int i=1; i < cmd.length() ; i++) {
	    if ('-' == cmd.charAt(i) || '_' == cmd.charAt(i)) {
		toUpper = true;
	    } else if (toUpper) {
		sbResult.append(Character.toUpperCase(cmd.charAt(i)));
		toUpper = false;
	    } else {
		sbResult.append(cmd.charAt(i));
	    }
	}

	sbResult.append("Command");
	return sbResult.toString();
    }

    private void createCmdClass(final String cmd) {
	final String SEP = System.getProperty("line.separator");
	final String className = this.transformToClassName(cmd);
	final StringBuilder classFileName = new StringBuilder();
	classFileName.append(System.getProperty("user.dir"));
	classFileName.append(CMD_PKG_PATH);
	classFileName.append(className);
	classFileName.append(".java");

	final StringBuilder classContent = new StringBuilder();
	classContent
	    .append("/*******************************************************************************").append(SEP)
	    .append("*                                 t1meBettle").append(SEP)
	    .append("*                           A command-line bugtracker").append(SEP)
	    .append("*").append(SEP)
	    .append("*    Copyright (C) 2016 by Sylvain Nieuwlandt").append(SEP)
	    .append("*").append(SEP)
	    .append("*    This program is free software: you can redistribute it and/or modify").append(SEP)
	    .append("*    it under the terms of the GNU General Public License as published by").append(SEP)
	    .append("*    the Free Software Foundation, either version 3 of the License, or").append(SEP)
	    .append("*    (at your option) any later version.").append(SEP)
	    .append("*").append(SEP)
	    .append("*    This program is distributed in the hope that it will be useful,").append(SEP)
	    .append("*    but WITHOUT ANY WARRANTY; without even the implied warranty of").append(SEP)
	    .append("*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the").append(SEP)
	    .append("*    GNU General Public License for more details.").append(SEP)
	    .append("*").append(SEP)
	    .append("*    You should have received a copy of the GNU General Public License").append(SEP)
	    .append("*    along with this program.  If not, see <http://www.gnu.org/licenses/>.").append(SEP)
	    .append("*******************************************************************************/").append(SEP)
	    .append("package net.wyn.t1b.ui.commands;").append(SEP)
	    .append(SEP)
	    .append("import net.wyn.t1b.ui.AbstractCommand;").append(SEP)
	    .append(SEP)
	    .append("public class ").append(className).append(" extends AbstractCommand {").append(SEP)
	    .append("   @Override").append(SEP)
	    .append("   public String getName() {").append(SEP)
	    .append("      return \"").append(cmd).append("\";").append(SEP)
	    .append("   }").append(SEP)
	    .append(SEP)
	    .append("   @Override").append(SEP)
	    .append("   public void execute() {").append(SEP)
	    .append("      /* TODO Generated method : Not implemented.").append(SEP)
	    .append("   }").append(SEP)
	    .append("}").append(SEP);

	final FileWriter fw = new FileWriter(new File(classFileName));
	fw.write(classContent.toString().getBytes());
	fw.close();
    }
    
    private void updateMakefileWith(cmd) {
	/* TODO */
    }
}
