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

public class UsageCommand extends AbstractCommand {
    private static String SEP = System.getProperty("line.separator");

    @Override
    public String getName() {
	return "Usage";
    }
    
    @Override
    public void execute() {
	final StringBuilder usageSb = new StringBuilder();
	usageSb.append("usage : t1b [cmd] [-h | --help] [-v | --version]")
	    .append(SEP);
	usageSb.append("Where [cmd] can be : ").append(SEP);
	usageSb.append("\t- init").append(SEP);
	usageSb.append("\t- create-version <version_name>").append(SEP);
	usageSb.append("\t- add").append(SEP);
	usageSb.append("\t- show <id>").append(SEP);
	usageSb.append("\t- list [--status=stat] [--maxresult=n]").append(SEP);
	usageSb.append("\t- add-comment <id>").append(SEP);
	usageSb.append("\t- remove-comment <id>").append(SEP);
	usageSb.append("\t- change-status <id>").append(SEP);
	usageSb.append("\t- update [--field=field]").append(SEP);
	usageSb.append("\t- assign <id>").append(SEP);
	usageSb.append("\t- search [--field=field]").append(SEP);
	usageSb.append("\t- clone <id>").append(SEP);
	System.out.println(usageSb.toString());
    }
}
