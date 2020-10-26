package com.osimatic.androidtest;

import android.graphics.Color;

import com.alamkanak.weekview.WeekViewDisplayable;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

public class Planification implements WeekViewDisplayable<Planification> {
	private int eventId;
	private String eventName;
	private Calendar startDate;
	private Calendar endDate;

	Planification(String eventName, Calendar startDate, Calendar endDate)  {
		this.eventId = Planification.getNewEventId();
		this.eventName = eventName + " (from "+CalendarHelper.formatDateTime(startDate, Locale.getDefault())+" to "+CalendarHelper.formatDateTime(endDate, Locale.getDefault())+")";
		this.startDate = startDate;
		this.endDate = endDate;
	}

	private static int getNewEventId() {
		Random r = new Random();
		return r.nextInt((10000000 - 1) + 1) + 1;
	}

	@Override
	public WeekViewEvent<Planification> toWeekViewEvent() {
		WeekViewEvent.Style style = new WeekViewEvent.Style.Builder()
				.setBackgroundColor(Color.parseColor("#403075"))
				.setTextStrikeThrough(false)
				.build();

		//this.startDate.setTimeZone(TimeZone.getTimeZone("GMT"));
		//this.endDate.setTimeZone(TimeZone.getTimeZone("GMT"));
		return new WeekViewEvent.Builder<>(this)
			.setId(this.eventId)
			.setTitle(this.eventName)
			.setStartTime(this.startDate)
			.setEndTime(this.endDate)
			.setStyle(style)
			.build()
		;
	}
}
