package interfaces;

import entities.Thief;
import items.Item;

public interface Stealable {
    void onStolen(Thief stealer);
}
