package ftpClient.ftpClientCommands.builders;

import ftpClient.ftpClientCommands.commands.FtpClientCommand;

/**
 *
 */
public abstract class FtpClientCommandBuilder {
    protected static final int ZERO_PARAMS_LENGTH = 0;
    protected static final String PARAMS_SEPARATOR = " ";
    protected static final String PARAMS_EXCEPTION_MSG = "Error! Wrong parameters for command: ";
    protected static final String COMMAND_EXCEPTION_MSG = "The command doesn't exist!";

    protected FtpClientCommandBuilder nextCommandBuilder;
    protected String commandName;
    protected int amountOfParams;

    public abstract FtpClientCommand getCommand(String givenCommand, String params);

    protected void parseParameters(String params) {
        System.out.println(commandName + "command is not defined..");
    }
}
