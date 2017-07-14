package ftpClient.ftpClientCommands.builders;

import ftpClient.ftpClientCommands.commands.FtpClientCommand;
import ftpClient.ftpClientCommands.commands.PrintFolderContentCommand;

/**
 * Builds Command which allows to print content of the current directory.
 */
public class PrintFolderContentCommandBuilder extends FtpClientCommandBuilder {
  {
    this.commandName = "print";
  }

  public PrintFolderContentCommandBuilder(FtpClientCommandBuilder nextCommandBuilder) {
    this.nextCommandBuilder = nextCommandBuilder;
  }

  /**
   * Returns PrintFolderContentCommand according to given string command in argument or invokes
   * the same method for next builder in the chain.
   *
   * @param argument given string with command and parameters.
   * @return Command according to given string command.
   */
  public FtpClientCommand getCommand(String argument) {
    separateCommandFromParams(argument);

    if (givenCommand.equalsIgnoreCase(commandName)) {
      return new PrintFolderContentCommand();
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
