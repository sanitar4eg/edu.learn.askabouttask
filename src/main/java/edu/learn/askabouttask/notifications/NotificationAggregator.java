package edu.learn.askabouttask.notifications;

import java.util.ArrayList;
import java.util.Collection;

import edu.learn.askabouttask.console.controller.MainAction;
import edu.learn.askabouttask.console.controller.StartAction;

public final class NotificationAggregator implements NotificationSystem {

	private Collection<NotificationSystem> backendSystems = new ArrayList<NotificationSystem>();

	private NotificationAggregator(Collection<NotificationSystem> backendSystems) {
		this.backendSystems = backendSystems;
	}

	public static NotificationAggregator getAggregator(Collection<NotificationSystem> backendSystems) {
		return new NotificationAggregator(backendSystems);
	}

	@Override
	public void notifyStartAction(StartAction action) {
		for (NotificationSystem ns : backendSystems) {
			ns.notifyStartAction(action);
		}
	}

	@Override
	public void notifyMainAction(MainAction action, Object arg) {
		for (NotificationSystem ns : backendSystems) {
			ns.notifyMainAction(action, arg);
		}
	}
}
