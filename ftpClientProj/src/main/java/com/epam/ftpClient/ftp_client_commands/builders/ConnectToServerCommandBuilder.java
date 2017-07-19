package com.epam.ftpClient.ftp_client_commands.builders;

import com.epam.ftpClient.ftp_client_commands.Commands;
import com.epam.ftpClient.ftp_client_commands.commands.ConnectToServerCommand;
import com.epam.ftpClient.ftp_client_commands.commands.FtpClientCommand;

/**
 * Builds command which allows to connect to the ftp server.
 */
public class ConnectToServerCommandBuilder extends FtpClientCommandBuilder {
  private String serverURL;
  private String login;
  private String password;

  public ConnectToServerCommandBuilder(FtpClientCommandBuilder nextCommandBuilder) {
    this.nextCommandBuilder = nextCommandBuilder;
    this.helpParamsForUsage = " <server_URL> <login> <password>";
    this.commandName = Commands.CONNECT.name();
    this.amountOfParams = 4;
  }

  /**
   * Returns ConnectToServerCommand according to given string command in argument or invokes the same method
   * for next builder in the chain.
   *
   * @param argument given string with command and parameters.
   * @return Command according to given string command.
   */
  @Override
  public FtpClientCommand getCommand(String argument) {
    separateCommandFromParams(argument);

    if (givenCommand.equalsIgnoreCase(commandName)) {
      setParameters();

      return new ConnectToServerCommand(serverURL, login, password);
    } else if (nextCommandBuilder != null) {
      return nextCommandBuilder.getCommand(argument);
    } else {
      throw new IllegalArgumentException(COMMAND_EXCEPTION_MSG);
    }
  }

  /**
   * Sets parameters needed for creating this Command.
   */
  @Override
  protected void setParameters() {
    if (consoleArguments.length != amountOfParams) {
      throw new IllegalArgumentException(PARAMS_EXCEPTION_MSG + commandName);
    }
    serverURL = consoleArguments[1];
    login = consoleArguments[2];
    password = consoleArguments[3];
  }

  /**
   * Prints how-to-use instruction to console.
   */
  @Override
  public void printUsage() {
    this.usage = commandName + helpParamsForUsage;
    System.out.println(usage);

    if (nextCommandBuilder != null) {
      nextCommandBuilder.printUsage();
    }
  }
}
