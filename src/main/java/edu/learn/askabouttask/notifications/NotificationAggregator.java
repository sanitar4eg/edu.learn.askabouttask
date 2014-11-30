package edu.learn.askabouttask.notifications;

import java.util.ArrayList;
import java.util.Collection;

import edu.learn.askabouttask.console.controller.MainAction;
import edu.learn.askabouttask.console.controller.StartAction;

public final class NotificationAggregator implements NotifySystem {

	private Collection<NotifySystem> backendSystems = new ArrayList<NotifySystem>();

	private NotificationAggregator(Collection<NotifySystem> backendSystems) {
		this.backendSystems = backendSystems;
	}

	public static NotificationAggregator getAggregator(Collection<NotifySystem> backendSystems) {
		return new NotificationAggregator(backendSystems);
	}

	@Override
	public void notifyStartAction(StartAction action) {
		for (NotifySystem ns : backendSystems) {
			ns.notifyStartAction(action);
		}
	}

	@Override
	public void notifyMainAction(MainAction action, Object arg) {
		for (NotifySystem ns : backendSystems) {
			ns.notifyMainAction(action, arg);
		}
	}
}
