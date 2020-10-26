package com.osimatic.androidtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import android.os.Bundle;

import com.alamkanak.weekview.WeekView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	private WeekView.SimpleAdapter<Planification> adapter;
	private PlanningViewModel planningViewModel;
	private WeekView mWeekView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mWeekView = findViewById(R.id.weekView);

		planningViewModel = new PlanningViewModel();
		adapter = new WeekView.SimpleAdapter<>();
		mWeekView.setAdapter(adapter);

		planningViewModel.events.observe(this, new Observer<List<Planification>>() {
			@Override
			public void onChanged(List<Planification> events) {
				adapter.submit(events);
			}
		});

		planningViewModel.fetchEvents();
	}

	public static class PlanningViewModel extends ViewModel {
		MutableLiveData<List<Planification>> events = new MutableLiveData<>();

		public void fetchEvents() {
			List<Planification> eventsList = new ArrayList<Planification>();
			
			String timeZone;
			Calendar startDate;
			Calendar endDate;

			// 2020-26-10 09:00 -> 17:00 UTC
			// no specified timeZone : event is displayed
			startDate = CalendarHelper.timestampToCalendar(Long.parseLong("1603702800")*1000);
			endDate = CalendarHelper.timestampToCalendar(Long.parseLong("1603731600")*1000);
			eventsList.add(new Planification("Event 1", startDate, endDate));

			// 2020-27-10 09:00 -> 17:00 UTC
			timeZone  = "GMT"; // event is displayed
			startDate = CalendarHelper.timestampToCalendar(Long.parseLong("1603789200")*1000, timeZone);
			endDate = CalendarHelper.timestampToCalendar(Long.parseLong("1603818000")*1000, timeZone);
			eventsList.add(new Planification("Event 2", startDate, endDate));

			// 2020-28-10 09:00 -> 17:00 UTC
			timeZone  = "GMT+2"; // event is NOT displayed
			startDate = CalendarHelper.timestampToCalendar(Long.parseLong("1603875600")*1000, timeZone);
			endDate = CalendarHelper.timestampToCalendar(Long.parseLong("1603904400")*1000, timeZone);
			eventsList.add(new Planification("Event 3", startDate, endDate));

			// 2020-29-10 09:00 -> 17:00 UTC
			timeZone  = "Europe/Paris"; // event is NOT displayed
			startDate = CalendarHelper.timestampToCalendar(Long.parseLong("1603962000")*1000, timeZone);
			endDate = CalendarHelper.timestampToCalendar(Long.parseLong("1603990800")*1000, timeZone);
			eventsList.add(new Planification("Event 4", startDate, endDate));

			if (events == null) {
				events = new MutableLiveData<>();
			}
			events.setValue(eventsList);
		}
	}

}