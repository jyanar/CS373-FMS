package cs373.facilities.model.scheduling;

import java.util.List;
import java.time.LocalDateTime;

public interface ISchedule {

	public int getId();
	public void setId(int id);
    public void setBeginningOfTime(LocalDateTime time);
    public LocalDateTime getBeginningOfTime();
    public void addEvent(IEvent event);
    public String getEventListString();
    public List<IEvent> getEventList();
    public void setEventList(List<IEvent> eventList);
    public int getNumEvents();

    public void removeEvents(LocalDateTime start, LocalDateTime stop);

    public boolean checkVacancyDuringInterval(LocalDateTime start, LocalDateTime stop);

    public LocalDateTime findVacantInterval(int hours, LocalDateTime beginningOfTime);
}
