package ftpClient.ftpClientCommands.builders;

import ftpClient.ftpClientCommands.commands.DownloadFileCommand;
import ftpClient.ftpClientCommands.commands.FtpClientCommand;

/**
 *
 */
public class DownloadFileCommandBuilder extends FtpClientCommandBuilder {
    private String fileName;

    {
        this.commandName = "download";
        this.amountOfParams = 1;
    }

    public DownloadFileCommandBuilder(FtpClientCommandBuilder nextCommandBuilder) {
        this.nextCommandBuilder = nextCommandBuilder;
    }

    public FtpClientCommand getCommand(String givenCommand, String params) {
        if (givenCommand.equalsIgnoreCase(commandName)) {
            parseParameters(params);

            return new DownloadFileCommand(fileName);
        } else if (nextCommandBuilder != null) {
            return nextCommandBuilder.getCommand(givenCommand, params);
        } else {
            throw new IllegalArgumentException(COMMAND_EXCEPTION_MSG);
        }
    }

    protected void parseParameters(String params) {
        if (params != null && params.length() != ZERO_PARAMS_LENGTH) {
            fileName = params.trim();
        }
    }
}
