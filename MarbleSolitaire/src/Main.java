import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.SwingGuiView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

public class Main {
  public static void main(String[] args) {
    // FILL IN HERE
    MarbleSolitaireModel e = new EnglishSolitaireModel();
    MarbleSolitaireGuiView v = new SwingGuiView(e);
    Readable r = new InputStreamReader(System.in);
    v.refresh();
    //   MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(e, v, r);

  }
}
