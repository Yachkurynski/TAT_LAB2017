package ftpClient.ftpClientCommands.builders;

import ftpClient.ftpClientCommands.commands.FtpClientCommand;
import ftpClient.ftpClientCommands.commands.GoIntoFolderCommand;

/**
 * Builds Command which allows to open some folder in current directory.
 */
public class GoIntoFolderCommandBuilder extends FtpClientCommandBuilder {
  private String folderName;

  {
    this.helpParamsForUsage = " <destination_folder_name>";
    this.commandName = "gointo";
    this.amountOfParams = 2;
  }

  public GoIntoFolderCommandBuilder(FtpClientCommandBuilder nextCommandBuilder) {
    this.nextCommandBuilder = nextCommandBuilder;
  }

  /**
   * Returns GoIntoFolderCommand according to given string command in argument or invokes the same
   * method for next builder in the chain.
   *
   * @param argument given string with command and parameters.
   * @return Command according to given string command.
   */
  public FtpClientCommand getCommand(String argument) {
    separateCommandFromParams(argument);

    if (givenCommand.equalsIgnoreCase(commandName)) {
      setParameters();

      return new GoIntoFolderCommand(folderName);
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
    folderName = consoleArguments[1];
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
