package ftpClient.ftpClientCommands.builders;

import ftpClient.ftpClientCommands.commands.FtpClientCommand;
import ftpClient.ftpClientCommands.commands.GoOutTheFolderCommand;

/**
 *
 */
public class GoOutTheFolderCommandBuilder extends FtpClientCommandBuilder {
    {
        this.commandName = "goout";
    }

    public GoOutTheFolderCommandBuilder(FtpClientCommandBuilder nextCommandBuilder) {
        this.nextCommandBuilder = nextCommandBuilder;
    }

    public FtpClientCommand getCommand(String givenCommand, String params) {
        if (givenCommand.equalsIgnoreCase(commandName)) {
            return new GoOutTheFolderCommand();
        } else if (nextCommandBuilder != null) {
            return nextCommandBuilder.getCommand(givenCommand, params);
        } else {
            throw new IllegalArgumentException(COMMAND_EXCEPTION_MSG);
        }
    }
}
