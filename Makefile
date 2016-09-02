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

EXEC_NAME=t1b
VERSION=0.0.1
DEBUG=yes
JAVA_VERSION=1.8
MAIN_CLASS=net/wyn/T1meBettle

SRC_DIR=src/
CLASS_DIR=classes/

JC=javac
JFLAGS=-Werror -d $(CLASS_DIR) -Xlint -Xlint:all -encoding utf8
JFLAGS += -source $(JAVA_VERSION) -target $(JAVA_VERSION)
JVM=java
JVMFLAGS=

RM=rm -f
MKDIR=mkdir -p

ifeq ($(DEBUG), yes)
	EXEC_FULLNAME=$(EXEC_NAME)_v$(VERSION)-SNAPSHOT
	JFLAGS += -g
else
	EXEC_FULLNAME=$(EXEC_NAME)_v$(VERSION)
endif

.PHONY: all clean prepare run

all: prepare $(CLASS_DIR)$(MAIN_CLASS).class

$(CLASS_DIR)$(MAIN_CLASS).class: $(SRC_DIR)$(MAIN_CLASS).java
	$(JC) $(JFLAGS) $^


prepare:
	@$(MKDIR) $(CLASS_DIR)

clean:
	$(RM) -r $(CLASS_DIR)

run:
	@cd $(CLASS_DIR) && \
	$(JVM) $(JVMFLAGS) $(subst /,., $(MAIN_CLASS))

