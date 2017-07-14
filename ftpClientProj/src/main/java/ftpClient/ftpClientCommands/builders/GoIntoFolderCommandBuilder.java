package ftpClient.ftpClientCommands.builders;

import ftpClient.ftpClientCommands.commands.FtpClientCommand;
import ftpClient.ftpClientCommands.commands.GoIntoFolderCommand;

/**
 *
 */
public class GoIntoFolderCommandBuilder extends FtpClientCommandBuilder {
    private String folderName;

    {
        this.commandName = "gointo";
        this.amountOfParams = 1;
    }

    public GoIntoFolderCommandBuilder(FtpClientCommandBuilder nextCommandBuilder) {
        this.nextCommandBuilder = nextCommandBuilder;
    }

    public FtpClientCommand getCommand(String givenCommand, String params) {
        if (givenCommand.equalsIgnoreCase(commandName)) {
            parseParameters(params);

            return new GoIntoFolderCommand(folderName);
        } else if (nextCommandBuilder != null) {
            return nextCommandBuilder.getCommand(givenCommand, params);
        } else {
            throw new IllegalArgumentException(COMMAND_EXCEPTION_MSG);
        }
    }

    protected void parseParameters(String params) {
        if (params != null && params.length() != ZERO_PARAMS_LENGTH) {
            folderName =  params.trim();
        }
    }
}
