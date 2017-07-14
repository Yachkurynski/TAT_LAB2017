package ftpClient.ftpClientCommands.builders;

import ftpClient.ftpClientCommands.commands.FtpClientCommand;
import ftpClient.ftpClientCommands.commands.PrintFolderContentCommand;

/**
 *
 */
public class PrintFolderContentCommandBuilder extends FtpClientCommandBuilder {
    {
        this.commandName = "print";
    }

    public PrintFolderContentCommandBuilder(FtpClientCommandBuilder nextCommandBuilder) {
        this.nextCommandBuilder = nextCommandBuilder;
    }

    public FtpClientCommand getCommand(String givenCommand, String params) {
        if (givenCommand.equalsIgnoreCase(commandName)) {
            return new PrintFolderContentCommand();
        } else if (nextCommandBuilder != null) {
            return nextCommandBuilder.getCommand(givenCommand, params);
        } else {
            throw new IllegalArgumentException(COMMAND_EXCEPTION_MSG);
        }
    }
}
