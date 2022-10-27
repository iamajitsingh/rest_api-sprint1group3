package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.model.ServiceRating.Rating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ServiceRating {
	public enum Rating { Worst, Poor, Good, Great }
	@Id
	@GeneratedValue
	private int serviceRatingId;
	@OneToOne
	@JoinColumn(name="fk_request_id")
	private Request request;
	private Rating rating;
	
	
}
