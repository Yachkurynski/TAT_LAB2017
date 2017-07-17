package com.epam.ftpClient.ftpClientCommands.builders;

import com.epam.ftpClient.ftpClientCommands.Commands;
import com.epam.ftpClient.ftpClientCommands.commands.FtpClientCommand;
import com.epam.ftpClient.ftpClientCommands.commands.GoOutTheFolderCommand;

/**
 * Builds Command which allows to go out the current directory.
 */
public class GoOutTheFolderCommandBuilder extends FtpClientCommandBuilder {
  {
    this.commandName = Commands.GOOUT.name();
  }

  public GoOutTheFolderCommandBuilder(FtpClientCommandBuilder nextCommandBuilder) {
    this.nextCommandBuilder = nextCommandBuilder;
  }

  /**
   * Returns GoOutTheFolderCommand according to given string command in argument or invokes the
   * same method for next builder in the chain.
   *
   * @param argument given string with command and parameters.
   * @return Command according to given string command.
   */
  public FtpClientCommand getCommand(String argument) {
    separateCommandFromParams(argument);

    if (givenCommand.equalsIgnoreCase(commandName)) {
      return new GoOutTheFolderCommand();
    } else if (nextCommandBuilder != null) {
      return nextCommandBuilder.getCommand(argument);
    } else {
      throw new IllegalArgumentException(COMMAND_EXCEPTION_MSG);
    }
  }

  /**
   * Prints how-to-use instruction to console.
   */
  @Override
  public void printUsage() {
    this.usage = commandName;
    System.out.println(usage);

    if (nextCommandBuilder != null) {
      nextCommandBuilder.printUsage();
    }
  }
}
