package ftpClient.ftpClientCommands.builders;

import ftpClient.ftpClientCommands.commands.DownloadFileCommand;
import ftpClient.ftpClientCommands.commands.FtpClientCommand;

/**
 * Builds Command which allows to download files from ftp server.
 */
public class DownloadFileCommandBuilder extends FtpClientCommandBuilder {
  private String fileName;

  {
    this.helpParamsForUsage = " <file_name>";
    this.commandName = "download";
    this.amountOfParams = 2;
  }

  public DownloadFileCommandBuilder(FtpClientCommandBuilder nextCommandBuilder) {
    this.nextCommandBuilder = nextCommandBuilder;
  }

  /**
   * Returns DownloadFileCommand according to given string command in argument or invokes the same
   * method for next builder in the chain.
   *
   * @param argument given string with command and parameters.
   * @return Command according to given string command.
   */
  public FtpClientCommand getCommand(String argument) {
    separateCommandFromParams(argument);

    if (givenCommand.equalsIgnoreCase(commandName)) {
      setParameters();

      return new DownloadFileCommand(fileName);
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
    givenCommand = consoleArguments[0];
    fileName = consoleArguments[1];
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
