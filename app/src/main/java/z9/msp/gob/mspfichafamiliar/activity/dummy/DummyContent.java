package z9.msp.gob.mspfichafamiliar.activity.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.persistencia.entity.Personas;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Personas> ITEMS = new ArrayList<Personas>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Personas> ITEM_MAP = new HashMap<String, Personas>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(Personas item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getNumCedula(), item);
    }

    private static Personas createDummyItem(int position) {
        return new Personas("Henry "+position,String.valueOf(position),"18/07/1993", R.drawable.persona);
    }


}
