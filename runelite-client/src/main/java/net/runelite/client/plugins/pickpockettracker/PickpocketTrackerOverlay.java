package net.runelite.client.plugins.pickpockettracker;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayMenuEntry;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;

import static net.runelite.api.MenuAction.RUNELITE_OVERLAY;
import static net.runelite.api.MenuAction.RUNELITE_OVERLAY_CONFIG;
import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;

@Slf4j
public class PickpocketTrackerOverlay extends OverlayPanel
{
    static final String PICKPOCKETING_RESET = "Reset";

    private final Client client;
    private final PickpocketTrackerPlugin plugin;
    private final PickpocketTrackerConfig config;

    @Inject
    private PickpocketTrackerOverlay(Client client, PickpocketTrackerPlugin plugin, PickpocketTrackerConfig config)
    {
        super(plugin);
        setPosition(OverlayPosition.TOP_LEFT);
        this.client = client;
        this.plugin = plugin;
        this.config = config;
        getMenuEntries().add(new OverlayMenuEntry(RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "Pickpocketing Overlay"));
        getMenuEntries().add(new OverlayMenuEntry(RUNELITE_OVERLAY, PICKPOCKETING_RESET, "Pickpocketing Overlay"));
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        PickpocketTrackerSession session = plugin.getSession();
        log.info("PEEPEEPOOPOO");

        if (session == null)
        {
            return null;
        }

        //TODO: Implement some logic
        log.info("QWEQWEQWEQWEQWE");
        panelComponent.getChildren().add(TitleComponent.builder()
            .text("Pickpocketing")
            .color(Color.GREEN)
            .build());

        return super.render(graphics);
    }
}
