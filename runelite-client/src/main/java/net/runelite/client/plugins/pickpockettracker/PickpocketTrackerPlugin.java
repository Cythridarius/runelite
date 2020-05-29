package net.runelite.client.plugins.pickpockettracker;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@PluginDescriptor(
        name = "Pickpocket Tracker",
        description = "Show pickpocketing statistics",
        tags = {"thieving", "pickpocketing", "master farmer", "skilling", "overlay", "money making"}
)

@Slf4j
public class PickpocketTrackerPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private ItemManager itemManager;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private PickpocketTrackerOverlay overlay;

    @Getter(AccessLevel.PACKAGE)
    private PickpocketTrackerSession session;

    @Override
    protected void startUp() throws Exception
    {
        session = null;
        overlayManager.add(overlay);
        log.info("Starting up");
    }

    @Override
    protected void shutDown() throws Exception
    {
        log.info("Shutting down");
        session = null;
    }

    @Subscribe
    public void onChatMessage(ChatMessage event)
    {
        if (event.getType() != ChatMessageType.SPAM)
        {
            return;
        }

        final String message = event.getMessage();

        if (message.startsWith("You attempt to pick the"))
        {
            if (session == null)
            {
                session = new PickpocketTrackerSession();
            }
        }
    }
}
