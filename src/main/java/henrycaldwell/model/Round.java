package henrycaldwell.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single round of a blackjack game, managing the players,
 * the dealer, and their hands.
 */
public class Round {

  private List<Player> players; // The list of players participating in this round.
  private Dealer dealer; // The dealer for this round of the game.

  /**
   * Constructs a round with the specified number of players.
   * Initializes each player with a new hand and creates a dealer.
   *
   * @param initialPlayers The number of players in the round.
   * @throws IllegalArgumentException if the number of players is negative.
   */
  public Round(int initialPlayers) {
    if (initialPlayers < 0) {
      throw new IllegalArgumentException("Number of players cannot be negative.");
    }

    this.players = new ArrayList<>();
    this.dealer = new Dealer();

    for (int playerIndex = 0; playerIndex < initialPlayers; playerIndex++) {
      List<Hand> hands = new ArrayList<>();
      hands.add(new Hand());
      players.add(new Player(hands));
    }
  }

  /**
   * Checks if all player hands are resolved, meaning no further action is
   * required from the players. A hand is considered resolved if it is a
   * blackjack, has a score of 21, has busted (score over 21), or is surrendered.
   *
   * @return {@code true} if all hands are resolved; {@code false} otherwise.
   */
  public boolean allHandsResolved() {
    for (Player player : players) {
      for (Hand hand : player.getHands()) {
        int playerScore = hand.evaluateHand();

        if (!(playerScore == 21 && hand.getSize() == 2) && // Not a blackjack
            playerScore < 21 && // Score less than 21
            playerScore != 0) { // Not surrendered
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Retrieves the list of players in the round.
   *
   * @return A new list containing the players.
   */
  public List<Player> getPlayers() {
    return new ArrayList<>(players);
  }

  /**
   * Retrieves the dealer of the round.
   *
   * @return The dealer.
   */
  public Dealer getDealer() {
    return dealer;
  }

  /**
   * Retrieves the number of players currently in the round.
   *
   * @return The number of players.
   */
  public int getNumPlayers() {
    return players.size();
  }

  /**
   * Provides a string representation of the current state of the round,
   * including all player hands and the dealer's hand.
   *
   * @return A string representing the current state of the round.
   */
  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();

    for (int currentPlayer = 0; currentPlayer < players.size(); currentPlayer++) {
      output.append("Player ").append(currentPlayer + 1)
          .append(":\n").append(players.get(currentPlayer)).append("\n");
    }

    output.append("Dealer:\nHand: ").append(dealer);
    return output.toString();
  }
}
