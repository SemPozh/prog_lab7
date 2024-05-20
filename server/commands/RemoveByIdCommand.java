package laba6.server.commands;

import laba6.client.validators.IDValidator;
import laba6.common.data.Organization;
import laba6.common.exeptions.CollectionIsEmptyException;
import laba6.common.exeptions.InvalidObjectFieldException;
import laba6.common.exeptions.OrganizationNotFoundException;
import laba6.common.exeptions.WrongAmountOfElementsException;
import laba6.server.modules.CollectionManager;
import laba6.server.modules.ResponseOutputer;

/**
 * Command 'remove_by_id'. Removes the element by its ID.
 */
public class RemoveByIdCommand extends AbstractCommand {

    public RemoveByIdCommand() {
        super("remove_by_id", "<ID>", "remove an element from a collection by ID");
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument, CollectionManager collectionManager) {
        try {
            if (stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            IDValidator idValidator = new IDValidator();
            Integer id = idValidator.validate(stringArgument);
            Organization organization = collectionManager.getById(id);
            if (organization == null) throw new OrganizationNotFoundException();
            collectionManager.removeFromCollection(organization);
            ResponseOutputer.appendln("Organization deleted successfully");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.appenderror("Collection is empty");
        } catch (InvalidObjectFieldException exception) {
            ResponseOutputer.appenderror("ID must be represented by a number!");
        } catch (OrganizationNotFoundException exception) {
            ResponseOutputer.appenderror("There is no organization with this ID in the collection!");
        }
        return false;
    }
}