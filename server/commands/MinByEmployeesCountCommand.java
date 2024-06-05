package laba7.server.commands;

import laba7.common.data.User;
import laba7.common.exeptions.CollectionIsEmptyException;
import laba7.common.exeptions.WrongAmountOfElementsException;
import laba7.server.modules.CollectionManager;
import laba7.server.modules.ResponseOutputer;

/**
 * Command 'max_by_melee_weapon'. Prints the element of the collection with maximum melee weapon.
 */
public class MinByEmployeesCountCommand extends AbstractCommand {

    public MinByEmployeesCountCommand() {
        super("max_by_melee_weapon", "", "display the element whose employees count field value is minimal");
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument, CollectionManager collectionManager, User user) {
        try {
            if (!stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            ResponseOutputer.appendln(collectionManager.minByEmployeesCount());
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.appenderror("The collection is empty!");
        }
        return true;
    }
}