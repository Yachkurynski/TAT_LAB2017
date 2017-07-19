package com.epam.ftpClient.ftp_client_commands.builders;

import com.epam.ftpClient.ftp_client_commands.commands.FtpClientCommand;

/**
 * Parent class for all the command builders.
 */
public abstract class FtpClientCommandBuilder {
  protected static final int ZERO_PARAMS_LENGTH = 0;
  protected static final String ARGS_SEPARATOR = " ";
  protected static final String PARAMS_EXCEPTION_MSG = "Error! Wrong parameters for command: ";
  protected static final String COMMAND_EXCEPTION_MSG = "The command doesn't exist!";

  protected FtpClientCommandBuilder nextCommandBuilder;
  protected String[] consoleArguments;
  protected String commandName;
  protected String givenCommand;
  protected String usage;
  protected String helpParamsForUsage;
  protected int amountOfParams;

  /**
   * Returns Command according to given string command in argument.
   *
   * @param argument given string with command and parameters.
   * @return Command according to given string command.
   */
  public abstract FtpClientCommand getCommand(String argument);

  /**
   * Prints how-to-use instruction to console.
   */
  public abstract void printUsage();

  /**
   * Sets parameters needed for creating Command.
   */
  protected void setParameters() {
    System.out.println("Empty method without realisation!");
  }

  /**
   * Separates string command from parameters which are need for creating it.
   * @param args given string with command and parameters.
   */
  protected void separateCommandFromParams(String args) {
    if (args != null && args.length() != ZERO_PARAMS_LENGTH) {
      this.consoleArguments = args.split(ARGS_SEPARATOR);
      this.givenCommand = consoleArguments[0];
    }
  }
}
