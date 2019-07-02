package com.example.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Performer")
public class Performer {
	private Long performer_id;
	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "events_performer", joinColumns = { @JoinColumn(name = "performer_id") }, inverseJoinColumns = { @JoinColumn(name = "event_id") })
	private Set<Events> events_perf = new HashSet<>();

	public void addEvent(Events event) {
		if (event == null)
			throw new NullPointerException();
		events_perf.add(event);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "performer_id")
	public Long getPerformer_id() {
		return performer_id;
	}

	public void setPerformer_id(Long performer_id) {
		this.performer_id = performer_id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "events_performer", joinColumns = { @JoinColumn(name = "performer_id") }, inverseJoinColumns = { @JoinColumn(name = "event_id") })
	public Set<Events> getEvents_perf() {
		return events_perf;
	}

	public void setEvents_perf(Set<Events> events_perf) {
		this.events_perf = events_perf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((performer_id == null) ? 0 : performer_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Performer other = (Performer) obj;
		if (performer_id == null) {
			if (other.performer_id != null)
				return false;
		} else if (!performer_id.equals(other.performer_id))
			return false;
		return true;
	}

}
