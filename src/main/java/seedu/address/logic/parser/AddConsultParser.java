package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddConsultCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.Day;
import seedu.address.model.consultation.Time;
import seedu.address.model.consultation.Type;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;


public class AddConsultParser implements Parser<AddConsultCommand> {
    @Override
    public AddConsultCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_TIME, PREFIX_ADDRESS,
                        PREFIX_TYPE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATE, PREFIX_TIME, PREFIX_ADDRESS,
                PREFIX_TYPE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddConsultCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Day date = ParserUtil.parseDay(argMultimap.getValue(PREFIX_DATE).get());
        Time time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        Address location = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Type type = ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get());
        Consultation consultation = new Consultation(name, date, time, location, type);

        return new AddConsultCommand(consultation);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

