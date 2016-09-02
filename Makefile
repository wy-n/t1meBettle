################################################################################
#                                 t1meBettle
#                           A command-line bugtracker
#
#    Copyright (C) 2016 by Sylvain Nieuwlandt
#
#    This program is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    This program is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with this program.  If not, see <http://www.gnu.org/licenses/>.
################################################################################

################################################################################
# EXECUTABLE INFORMTIONS
################################################################################
EXEC_NAME=t1b
VERSION=0.0.1
DEBUG=yes
JAVA_VERSION=1.8
MAIN_CLASS=net/wyn/t1b/T1meBettle

################################################################################
# DIRECTORY STRUCTURE
################################################################################
SRC_DIR=src/
CLASS_DIR=classes/
TOOLS_DIR=tools/
TOOLS_SRC_DIR=$(TOOLS_DIR)src/

################################################################################
# TOOLS CONFIGURATION
################################################################################
JC=javac
JFLAGS=-Werror -d $(CLASS_DIR) -Xlint -Xlint:all -encoding utf8
JFLAGS += -source $(JAVA_VERSION) -target $(JAVA_VERSION)
JFLAGS += -classpath $(CLASS_DIR)
JVM=java
JVMFLAGS=
JAR=jar
RM=rm -f
MKDIR=mkdir -p

################################################################################
# SET DEBUG OPTIONS
################################################################################
ifeq ($(DEBUG), yes)
	EXEC_FULLNAME=$(EXEC_NAME)_v$(VERSION)-SNAPSHOT
	JFLAGS += -g
else
	EXEC_FULLNAME=$(EXEC_NAME)_v$(VERSION)
endif

################################################################################
# DEFINITIONS OF ALL TARGETS
################################################################################
.PHONY: all clean prepare run command

################################################################################
# BUILD MAGIC
################################################################################
all: prepare $(CLASS_DIR)$(MAIN_CLASS).class

$(CLASS_DIR)$(MAIN_CLASS).class: $(SRC_DIR)$(MAIN_CLASS).java \
                                 $(CLASS_DIR)net/wyn/t1b/ui/Dispatcher.class \
                                 $(CLASS_DIR)net/wyn/t1b/ui/AbstractCommand.class
	$(JC) $(JFLAGS) $<

$(CLASS_DIR)net/wyn/t1b/ui/Dispatcher.class: $(SRC_DIR)net/wyn/t1b/ui/Dispatcher.java \
                                         $(CLASS_DIR)net/wyn/t1b/ui/AbstractCommand.class \
                          $(CLASS_DIR)net/wyn/t1b/ui/commands/InitCommand.class \
                                         $(CLASS_DIR)net/wyn/t1b/ui/UsageCommand.class 
	$(JC) $(JFLAGS) $<	

$(CLASS_DIR)net/wyn/t1b/ui/AbstractCommand.class: $(SRC_DIR)net/wyn/t1b/ui/AbstractCommand.java
	$(JC) $(JFLAGS) $<

$(CLASS_DIR)net/wyn/t1b/ui/UsageCommand.class: $(SRC_DIR)net/wyn/t1b/ui/UsageCommand.java \
                                           $(CLASS_DIR)net/wyn/t1b/ui/AbstractCommand.class
	$(JC) $(JFLAGS) $<

$(CLASS_DIR)net/wyn/t1b/ui/commands/InitCommand.class: $(SRC_DIR)net/wyn/t1b/ui/commands/InitCommand.java \
                                                       $(CLASS_DIR)net/wyn/t1b/core/Tracker.class \
                                                       $(CLASS_DIR)net/wyn/t1b/core/exception/TrackerAlreadyExistsException.class \
                        $(CLASS_DIR)net/wyn/t1b/ui/AbstractCommand.class
	$(JC) $(JFLAGS) $<

$(CLASS_DIR)net/wyn/t1b/core/Tracker.class: $(SRC_DIR)net/wyn/t1b/core/Tracker.java \
                                            $(CLASS_DIR)net/wyn/t1b/core/exception/TrackerAlreadyExistsException.class
	$(JC) $(JFLAGS) $<

$(CLASS_DIR)net/wyn/t1b/core/exception/TrackerAlreadyExistsException.class: $(SRC_DIR)net/wyn/t1b/core/exception/TrackerAlreadyExistsException.java
	$(JC) $(JFLAGS) $< 

################################################################################
# DEVELOPMENT TOOLS
################################################################################
command: $(TOOLS_DIR)CreateCommand.jar
	@$(JVM) -jar $^

$(TOOLS_DIR)CreateCommand.jar:$(TOOLS_SRC_DIR)net/wyn/t1b/dev/CreateCommand.java
	@$(MKDIR) $(TOOLS_DIR)CreateCommand
	@$(JC) -d $(TOOLS_DIR)CreateCommand $<		
	@echo "Main-Class: net.wyn.t1b.dev.CreateCommand" > $(TOOLS_DIR)CreateCommand/manifest.txt
	@cd $(TOOLS_DIR)CreateCommand && $(JAR) -cfm ../CreateCommand.jar manifest.txt net/wyn/t1b/dev/*.class
	@$(RM) -r $(TOOLS_DIR)CreateCommand

################################################################################
# BUILD UTILITIES
################################################################################
prepare:
	@$(MKDIR) $(CLASS_DIR)

clean:
	$(RM) -r $(CLASS_DIR)

run:
	@cd $(CLASS_DIR) && \
	$(JVM) $(JVMFLAGS) $(subst /,., $(MAIN_CLASS)) ${ARGS}

