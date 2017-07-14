package ftpClient.ftpClientCommands.builders;

import ftpClient.ftpClientCommands.commands.ConnectToServerCommand;
import ftpClient.ftpClientCommands.commands.FtpClientCommand;

/**
 *
 */
public class ConnectToServerCommandBuilder extends FtpClientCommandBuilder {
    private String serverURL;
    private String login;
    private String password;

    {
        this.commandName = "connect";
        this.amountOfParams = 3;
    }

    public ConnectToServerCommandBuilder(FtpClientCommandBuilder nextCommandBuilder) {
        this.nextCommandBuilder = nextCommandBuilder;
    }

    /**
     * Creates command according to given command name.
     *
     * @param givenCommand given command name.
     * @param params parameters for command creating.
     * @return command.
     */
    public FtpClientCommand getCommand(String givenCommand, String params) {
        if (givenCommand.equalsIgnoreCase(commandName)) {
            parseParameters(params);

            return new ConnectToServerCommand(serverURL, login, password);
        } else if (nextCommandBuilder != null) {
            return nextCommandBuilder.getCommand(givenCommand, params);
        } else {
            throw new IllegalArgumentException(COMMAND_EXCEPTION_MSG);
        }
    }

    @Override
    protected void parseParameters(String params) {
        if (params != null && params.length() != ZERO_PARAMS_LENGTH) {
            String[] parameters = params.split(PARAMS_SEPARATOR);

            if (parameters.length != amountOfParams) {
                throw new IllegalArgumentException(PARAMS_EXCEPTION_MSG + commandName);
            }
            serverURL = parameters[0];
            login = parameters[1];
            password = parameters[2];
        }
    }
}
