package cs3500.marblesolitaire.controller;

import java.util.List;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;

public class ControllerFeaturesImpl implements ControllerFeatures{
  MarbleSolitaireModel model;
  MarbleSolitaireGuiView view;
  List<Integer> positions;

  public ControllerFeaturesImpl(MarbleSolitaireModel model, MarbleSolitaireGuiView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void getInput(int x, int y) {
    positions.add(x);
    positions.add(y);
    if (positions.size() == 4) {
      model.move(positions.get(0), positions.get(1), positions.get(2), positions.get(3));
      positions.clear();
    }
  }
}
