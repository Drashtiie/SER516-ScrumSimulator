package se.bettercode.scrum.backlog;

import se.bettercode.utils.Selectable;

public class SelectableBacklogs extends Selectable<Backlog> {

    public SelectableBacklogs() {
        Backlog smallBacklog = new SmallBacklog();
        Backlog wellSlicedBacklog = new WellSlicedBacklog();
        Backlog poorlySlicedBacklog = new PoorlySlicedBacklog();
        put(smallBacklog.getName(), smallBacklog);
        put(wellSlicedBacklog.getName(), wellSlicedBacklog);
        put(poorlySlicedBacklog.getName(), poorlySlicedBacklog);
    }

}
