package spreadsheet;

/**
 * SpreadCommand interface to take in command.
 */
public interface SpreadCommand extends SpreadSheet {
  void accept(Commands c);
}
