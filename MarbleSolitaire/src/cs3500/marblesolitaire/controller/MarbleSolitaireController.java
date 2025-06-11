package cs3500.marblesolitaire.controller;


/**
 * interface to represent controller of marble solitaire game.
 */
public interface MarbleSolitaireController {

  /**
   * Plays a new game of marble Solitaire.
   * @throws IllegalStateException if controller is unable to read input or transmit output
   */
  void playGame() throws IllegalStateException;
}
