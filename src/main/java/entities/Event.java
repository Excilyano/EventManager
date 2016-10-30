package entities;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Steeve Sinigaglia on 17/10/2016.
 */
@Table(name = "EVENT")
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "starting_date", nullable = false)
    private Date startingDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "isHidden")
    private boolean hidden;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "events", cascade = CascadeType.ALL)
    private Set<User> participants;

    /**
     * Default Constructor
     */
    public Event() {
        this.participants = new HashSet<>();
    }

    /**
     * Minimal Constructor
     *
     * @param title        title of the event
     * @param location	   the location of the event
     * @param startingDate the starting date of the event
     * @param endDate      the end date of the event
     * @param creator      the creator (User) of the event
     */
    public Event(String title, String location, Date startingDate, Date endDate, User creator) {
        this.title = title;
        this.location = location;
        this.startingDate = startingDate;
        this.endDate = endDate;
        this.creator = creator;

        this.participants = new HashSet<>();
        this.participants.add(creator);
        creator.addEvent(this);
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
        this.participants.add(creator);
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
        this.participants.stream().forEach(e -> e.addEvent(this));
    }

    public void addParticipant(User user) {
        participants.add(user);
        user.addEvent(this);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", startingDate=" + startingDate +
                ", endDate=" + endDate +
                ", hidden=" + hidden +
                ", creator=" + creator +
                ", participants=" + participants +
                '}';
    }
}
